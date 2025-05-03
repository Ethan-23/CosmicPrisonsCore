package org.evasive.me.cosmicPrisonsCore.mining.process;

import com.github.retrooper.packetevents.event.PacketListenerAbstract;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.protocol.player.DiggingAction;
import com.github.retrooper.packetevents.util.Vector3i;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientPlayerDigging;
import net.kyori.adventure.title.TitlePart;
import org.bukkit.GameMode;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.evasive.me.cosmicPrisonsCore.CosmicPrisonsCore;
import org.evasive.me.cosmicPrisonsCore.mining.ores.OreType;
import org.evasive.me.cosmicPrisonsCore.mining.records.BlockPos;
import org.evasive.me.cosmicPrisonsCore.utils.ComponentUtils;

import static org.evasive.me.cosmicPrisonsCore.keys.ItemKeyFunctions.*;
import static org.evasive.me.cosmicPrisonsCore.keys.ItemKeyFunctions.getLevelRequirement;

public class MiningPacketEvents extends PacketListenerAbstract {

    @Override
    public void onPacketReceive(PacketReceiveEvent event) {
        Player player = event.getPlayer();

        handleDiggingPacket(event);

        if(!isMiningAttemptValid(player, event)) return;

        Block block = CosmicPrisonsCore.selectedBlockMap.getBlock(player.getUniqueId());

        if(!validateSelectedBlock(block)) return;

        handleMiningProgress(player, block);
    }

    public boolean checkForCorrectGameMode(Player player){
        return player.getGameMode().equals(GameMode.SURVIVAL);
    }

    public void handleDiggingPacket(PacketReceiveEvent event){

        if(event.getPacketType() != PacketType.Play.Client.PLAYER_DIGGING) return;

        Player player = event.getPlayer();

        if(!checkForCorrectGameMode(player)) return;

        WrapperPlayClientPlayerDigging digging = new WrapperPlayClientPlayerDigging(event);
        Vector3i blockPosition = digging.getBlockPosition();
        Block block = player.getWorld().getBlockAt(blockPosition.getX(), blockPosition.getY(), blockPosition.getZ());

        if(digging.getAction() != DiggingAction.START_DIGGING) return;
        if (!checkForCorrectBlockType(block)) return;

        event.setCancelled(true);
        CosmicPrisonsCore.selectedBlockMap.addPlayer(player.getUniqueId(), block);
    }

    public boolean isMiningAttemptValid(Player player, PacketReceiveEvent event){
        return validateTargetBlock(player, event) && validateItemInHand(player, player.getInventory().getItemInMainHand()) && checkForCorrectGameMode(player);
    }

    public boolean validateTargetBlock(Player player, PacketReceiveEvent event){
        if(event.getPacketId() != 0x3A || player.getTargetBlockExact(5) == null || player.getGameMode() != GameMode.SURVIVAL || !CosmicPrisonsCore.selectedBlockMap.hasPlayer(player.getUniqueId()))
            return false;
        return CosmicPrisonsCore.selectedBlockMap.getBlock(player.getUniqueId()).getLocation().equals(player.getTargetBlockExact(5).getLocation());
    }

    public boolean validateItemInHand(Player player, ItemStack itemStack){
        ItemMeta itemMeta = itemStack.getItemMeta();
        if(!itemStack.hasItemMeta() || itemMeta == null || !hasPickaxe(itemStack.getItemMeta()) || isEnergyFull(itemStack.getItemMeta()))
            return false;
        if(getLevelRequirement(itemStack.getItemMeta()) > CosmicPrisonsCore.playerLevelManager.getPlayerData(player.getUniqueId()).getLevel()){
            player.sendTitlePart(TitlePart.TITLE, ComponentUtils.legacy(String.format("&cRequires Mining Level &f&l%d", getLevelRequirement(itemStack.getItemMeta()))));
            return false;
        }
        return true;
    }

    private boolean validateSelectedBlock(Block block) {
        return block != null && checkForCorrectBlockType(block);
    }

    public void handleMiningProgress(Player player, Block block){
        checkBlockExists(player,block);

        if(!validateSelectedBlock(block)) return;

        new MiningBlockProgress().addBlockProgress(player, block);
        new MiningBlockProgress().checkForFracture(player, player.getInventory().getItemInMainHand(), block);
    }

    public void handleFractureProgress(Player player, Block block){

        checkBlockExists(player,block);

        if(!validateSelectedBlock(block)) return;

        new MiningBlockProgress().addBlockFractureProgress(player, block);
    }

    public void checkBlockExists(Player player, Block block){
        BlockPos blockPos = BlockPos.fromBlock(block);
        if(!CosmicPrisonsCore.miningMap.containsPlayerAtLocation(blockPos, player.getUniqueId())){
            CosmicPrisonsCore.miningMap.addBlockPos(blockPos, player.getUniqueId(), new MiningBlockData(0, 0f, 0f, CosmicPrisonsCore.animationIds.getUniqueAnimationId()));
        }
    }

    public boolean checkForCorrectBlockType(Block block){
        try {
            OreType.valueOf(block.getType().name());
            return true;
        }catch (IllegalArgumentException e){
            return false;
        }
    }







}
