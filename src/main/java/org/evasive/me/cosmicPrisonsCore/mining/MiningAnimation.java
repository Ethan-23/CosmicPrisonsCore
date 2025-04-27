package org.evasive.me.cosmicPrisonsCore.mining;

import com.github.retrooper.packetevents.PacketEvents;
import com.github.retrooper.packetevents.event.PacketListenerAbstract;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.protocol.player.DiggingAction;
import com.github.retrooper.packetevents.util.Vector3i;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientPlayerDigging;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerBlockBreakAnimation;
import net.kyori.adventure.title.TitlePart;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.evasive.me.cosmicPrisonsCore.CosmicPrisonsCore;
import org.evasive.me.cosmicPrisonsCore.keys.ItemKeyFunctions;
import org.evasive.me.cosmicPrisonsCore.mining.data.MiningBlockData;
import org.evasive.me.cosmicPrisonsCore.mining.records.BlockPos;
import org.evasive.me.cosmicPrisonsCore.utils.ComponentUtils;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class MiningAnimation extends PacketListenerAbstract {

    public Set<Material> blocks = Set.of(Material.COAL_ORE, Material.COAL_BLOCK, Material.IRON_ORE, Material.IRON_BLOCK, Material.LAPIS_ORE, Material.REDSTONE_ORE, Material.GOLD_ORE, Material.DIAMOND_ORE, Material.EMERALD_ORE);



    @Override
    public void onPacketReceive(PacketReceiveEvent event) {
        Player player = event.getPlayer();
        //player.sendMessage("PacketID: " + event.getPacketId() + "NAME: " + event.getPacketType().getName());
        //0x36 = Arm Swing Packet

        if(event.getPacketType() == PacketType.Play.Client.PLAYER_DIGGING){

            WrapperPlayClientPlayerDigging digging = new WrapperPlayClientPlayerDigging(event);
            Vector3i blockPosition = digging.getBlockPosition();
            Block block = player.getWorld().getBlockAt(blockPosition.getX(), blockPosition.getY(), blockPosition.getZ());
            if(digging.getAction() != DiggingAction.START_DIGGING)
                return;
            if(!blocks.contains(block.getType()))
                return;
            CosmicPrisonsCore.selectedBlockMap.addPlayer(player.getUniqueId(), block);
            return;
        }

        if(event.getPacketId() != 0x3A)
            return;
        if(player.getTargetBlockExact(5) == null)
            return;
        if(player.getGameMode() != GameMode.SURVIVAL)
            return;
        if(!CosmicPrisonsCore.selectedBlockMap.hasPlayer(player.getUniqueId()))
            return;
        if(!CosmicPrisonsCore.selectedBlockMap.getBlock(player.getUniqueId()).getLocation().equals(player.getTargetBlockExact(5).getLocation()))
            return;
        ItemStack mainItem = player.getInventory().getItemInMainHand();
        if(!mainItem.hasItemMeta())
            return;
        ItemMeta itemMeta = mainItem.getItemMeta();
        if(itemMeta == null)
            return;
        ItemKeyFunctions pickaxeFunctions = new ItemKeyFunctions();
        if(pickaxeFunctions.hasPickaxe(mainItem.getItemMeta()) && pickaxeFunctions.isEnergyFull(mainItem.getItemMeta()))
            return;
        if(pickaxeFunctions.getLevelRequirement(mainItem.getItemMeta()) > CosmicPrisonsCore.playerLevelManager.getPlayerData(player.getUniqueId()).getLevel()){
            player.sendTitlePart(TitlePart.TITLE, ComponentUtils.legacy("&cRequires Mining Level " + "&f&l" + pickaxeFunctions.getLevelRequirement(mainItem.getItemMeta())));
            return;
        }


        Block block = CosmicPrisonsCore.selectedBlockMap.getBlock(player.getUniqueId());
        assert block != null;

        BlockPos blockPos = BlockPos.fromBlock(block);

        if(!CosmicPrisonsCore.miningMap.containsPlayerAtLocation(blockPos, player.getUniqueId())){
            CosmicPrisonsCore.miningMap.addBlockPos(blockPos, player.getUniqueId(), new MiningBlockData(0, 0, CosmicPrisonsCore.animationIds.getUniqueAnimationId()));
            return;
        }

        if(CosmicPrisonsCore.miningMap.getBlockProgress(blockPos, player.getUniqueId()) >= 100){
            breakBlock(player, block);
            return;
        }

        addBlockProgress(player, block);

    }



    public void sendAnimationPacket(Player player, Block block, byte blockProgress){
        if(blockProgress>9)
            blockProgress = 9;
        WrapperPlayServerBlockBreakAnimation progressAnimation = new WrapperPlayServerBlockBreakAnimation(
                CosmicPrisonsCore.miningMap.getPlayerBlockAnimationId(BlockPos.fromBlock(block), player.getUniqueId()),
                new Vector3i(block.getX(), block.getY(), block.getZ()),
                blockProgress
        );
        PacketEvents.getAPI().getPlayerManager().sendPacket(player, progressAnimation);
    }

    public void resetBlockAnimations(Block block){
        Map<UUID, MiningBlockData> temp = CosmicPrisonsCore.miningMap.getPlayerBlockProgress(BlockPos.fromBlock(block));

        for (Map.Entry<UUID, MiningBlockData> entry : temp.entrySet()) {
            sendAnimationPacket(Bukkit.getPlayer(entry.getKey()), block, (byte) -1);
        }
    }

    public void breakBlock(Player player, Block block){
        Bukkit.getScheduler().runTask(CosmicPrisonsCore.getCore(), () -> {
            if(!CosmicPrisonsCore.miningMap.containsBlockLocation(BlockPos.fromBlock(block)))
                return;
            Material tempMaterial = block.getType();
            block.setType(Material.STONE);
            resetBlockAnimations(block);
            new MiningFunctions().BreakBlock(player, tempMaterial, BlockPos.fromBlock(block));
            CosmicPrisonsCore.miningMap.removeBlockPos(BlockPos.fromBlock(block));
            CosmicPrisonsCore.selectedBlockMap.removePlayer(player.getUniqueId());
        });
    }

    public void addBlockProgress(Player player, Block block){

        float progress = new MiningFunctions().calculateEfficiency(player, player.getInventory().getItemInMainHand(), block.getType()); // Amount being added to block change with enchants / buffs
        CosmicPrisonsCore.miningMap.IncreaseBlockBreak(BlockPos.fromBlock(block), player.getUniqueId(), progress);
        sendAnimationPacket(player, block, (byte) (CosmicPrisonsCore.miningMap.getBlockProgress(BlockPos.fromBlock(block), player.getUniqueId()) / (100 / 10)));
    }

}
