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
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.evasive.me.cosmicPrisonsCore.CosmicPrisonsCore;
import org.evasive.me.cosmicPrisonsCore.enchanting.EnchantFunctions;
import org.evasive.me.cosmicPrisonsCore.enchanting.mining.PickaxeEnchants;
import org.evasive.me.cosmicPrisonsCore.keys.ItemKeyFunctions;
import org.evasive.me.cosmicPrisonsCore.mining.data.MiningBlockData;
import org.evasive.me.cosmicPrisonsCore.mining.ores.OreCreator;
import org.evasive.me.cosmicPrisonsCore.mining.ores.OreType;
import org.evasive.me.cosmicPrisonsCore.mining.records.BlockPos;
import org.evasive.me.cosmicPrisonsCore.utils.ComponentUtils;

import java.util.*;

import static org.evasive.me.cosmicPrisonsCore.keys.ItemKeyFunctions.*;

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
        if(!hasPickaxe(mainItem.getItemMeta()))
            return;
        if(isEnergyFull(mainItem.getItemMeta()))
            return;
        if(getLevelRequirement(mainItem.getItemMeta()) > CosmicPrisonsCore.playerLevelManager.getPlayerData(player.getUniqueId()).getLevel()){
            player.sendTitlePart(TitlePart.TITLE, ComponentUtils.legacy("&cRequires Mining Level " + "&f&l" + getLevelRequirement(mainItem.getItemMeta())));
            return;
        }


        Block block = CosmicPrisonsCore.selectedBlockMap.getBlock(player.getUniqueId());

        if(block == null)
            return;
        if(!blocks.contains(block.getType()))
            return;

        BlockPos blockPos = BlockPos.fromBlock(block);

        if(!CosmicPrisonsCore.miningMap.containsPlayerAtLocation(blockPos, player.getUniqueId())){
            CosmicPrisonsCore.miningMap.addBlockPos(blockPos, player.getUniqueId(), new MiningBlockData(0, 0, CosmicPrisonsCore.animationIds.getUniqueAnimationId()));
            return;
        }

        //MOVE TO ADD BLOCK PROGRESS


        addBlockProgress(player, block);
        if(OreType.valueOf(block.getType().name()).getOreCreator().mineableLevel() < CosmicPrisonsCore.getPlayerLevelManager().getPlayerData(player.getUniqueId()).getLevel())
            addSurroundingBlockProgress(player, block);
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
        ItemStack pickaxe = player.getInventory().getItemInMainHand();
        Bukkit.getScheduler().runTask(CosmicPrisonsCore.getCore(), () -> {
            if(!CosmicPrisonsCore.miningMap.containsBlockLocation(BlockPos.fromBlock(block)))
                return;
            new EnchantFunctions().handleBreakingEnchants(player, pickaxe.getItemMeta());
            Material tempMaterial = block.getType();
            block.setType(Material.STONE);
            resetBlockAnimations(block);
            new MiningFunctions().BreakBlock(player, tempMaterial, BlockPos.fromBlock(block), pickaxe);
            CosmicPrisonsCore.miningMap.removeBlockPos(BlockPos.fromBlock(block));
            CosmicPrisonsCore.selectedBlockMap.removePlayer(player.getUniqueId());
        });
    }

    public void addBlockProgress(Player player, Block block){
        float progress;
        if(OreType.valueOf(block.getType().name()).getOreCreator().mineableLevel() > CosmicPrisonsCore.playerLevelManager.getPlayerData(player.getUniqueId()).getLevel()){
            progress = new MiningFunctions().getBaseSpeed(player.getInventory().getItemInMainHand(), block.getType());
        }else{
            progress = new MiningFunctions().calculateEfficiency(player, player.getInventory().getItemInMainHand(), block.getType()); // Amount being added to block change with enchants / buffs
        }

        CosmicPrisonsCore.miningMap.IncreaseBlockBreak(BlockPos.fromBlock(block), player.getUniqueId(), progress);
        sendAnimationPacket(player, block, (byte) (CosmicPrisonsCore.miningMap.getBlockProgress(BlockPos.fromBlock(block), player.getUniqueId()) / (100 / 10)));
        if(CosmicPrisonsCore.miningMap.getBlockProgress(BlockPos.fromBlock(block), player.getUniqueId()) >= 100){
            breakBlock(player, block);
        }
    }

    public void addSurroundingBlockProgress(Player player, Block block){
        //NEED TO SUBTRACT XP FROM THESE BLOCKS????
        if(!new EnchantFunctions().checkFractureChance(player.getInventory().getItemInMainHand().getItemMeta()))
            return;

        for(Block nearbyBlock : getFractureBlocks(block)){
            BlockPos blockPos = BlockPos.fromBlock(nearbyBlock);

            if(!CosmicPrisonsCore.miningMap.containsPlayerAtLocation(blockPos, player.getUniqueId())){
                CosmicPrisonsCore.miningMap.addBlockPos(blockPos, player.getUniqueId(), new MiningBlockData(0, 0, CosmicPrisonsCore.animationIds.getUniqueAnimationId()));
            }
            addBlockProgress(player, nearbyBlock);
        }
    }

    public List<Block> getFractureBlocks(Block block){
        List<Block> blockList = new ArrayList<>();
        int centerX = block.getX();
        int centerY = block.getY();
        int centerZ = block.getZ();
        World world = block.getWorld();

        for (int x = centerX - 1; x <= centerX + 1; x++) {
            for (int y = centerY - 1; y <= centerY + 1; y++) {
                for (int z = centerZ - 1; z <= centerZ + 1; z++) {
                    if (x == centerX && y == centerY && z == centerZ)
                        continue;
                    Block nearbyBlock = world.getBlockAt(x, y, z);
                    if(nearbyBlock.getType() != block.getType() && nearbyBlock.getType() != OreType.valueOf(block.getType().name()).getOreCreator().getRefinedMaterial())
                        continue;

                    BlockFace[] faces = {
                            BlockFace.UP, BlockFace.DOWN,
                            BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST
                    };

                    boolean exposedToAir = false;
                    for (BlockFace face : faces) {
                        if (nearbyBlock.getRelative(face).getType() == Material.AIR) {
                            exposedToAir = true;
                            break;
                        }
                    }


                    if (!exposedToAir)
                        continue;

                    blockList.add(nearbyBlock);
                }
            }
        }
        return blockList;
    }

}
