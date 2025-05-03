package org.evasive.me.cosmicPrisonsCore.mining.process;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.evasive.me.cosmicPrisonsCore.CosmicPrisonsCore;
import org.evasive.me.cosmicPrisonsCore.mining.records.BlockPos;
import org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.PickaxeEnchantFunctions;

import java.util.Map;
import java.util.UUID;

import static org.evasive.me.cosmicPrisonsCore.mining.ores.functions.OreFunctions.getOreCreatorFromBlock;

public class MiningBlockBreak {

    public void breakBlock(Player player, Block block) {
        Bukkit.getScheduler().runTask(CosmicPrisonsCore.getCore(), () -> {
            if(!isBlockValid(block)) return;
            resetBlockAnimations(block);
            removeBlockFromMaps(player, block);
            new MiningRewards().handleBlockRewards(player, player.getInventory().getItemInMainHand(), getOreCreatorFromBlock(block));
            new PickaxeEnchantFunctions().handleBreakingEnchants(player, player.getInventory().getItemInMainHand().getItemMeta());
            handleRespawn(block);
            playBreakSound(player);
            block.setType(Material.STONE);
        });
    }

    public boolean isBlockValid(Block block){
        return CosmicPrisonsCore.miningMap.containsBlockLocation(BlockPos.fromBlock(block));
    }

    public void resetBlockAnimations(Block block){
        Map<UUID, MiningBlockData> players = CosmicPrisonsCore.miningMap.getPlayerBlockProgress(BlockPos.fromBlock(block));
        for (Map.Entry<UUID, MiningBlockData> entry : players.entrySet()) {
            if (Bukkit.getPlayer(entry.getKey()) != null) new MiningBlockProgress().sendAnimationPacket(Bukkit.getPlayer(entry.getKey()), block, (byte) -1);
        }
    }

    public void removeBlockFromMaps(Player player, Block block){
        CosmicPrisonsCore.miningMap.removeBlockPos(BlockPos.fromBlock(block));
        CosmicPrisonsCore.selectedBlockMap.removePlayer(player.getUniqueId());
    }

    public void handleRespawn(Block block){
        new MiningBlockRespawn().respawnBlock(block);
    }

    public void playBreakSound(Player player){
        player.playSound(player.getLocation(), Sound.BLOCK_STONE_BREAK, 1, 1);
    }

}
