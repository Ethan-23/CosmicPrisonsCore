package org.evasive.me.cosmicPrisonsCore.mining.respawn;

import org.bukkit.block.Block;
import org.bukkit.scheduler.BukkitRunnable;
import org.evasive.me.cosmicPrisonsCore.CosmicPrisonsCore;
import org.evasive.me.cosmicPrisonsCore.mining.process.MiningBlockBreak;
import org.evasive.me.cosmicPrisonsCore.mining.process.MiningBlockRespawn;
import org.evasive.me.cosmicPrisonsCore.mining.ores.OreType;
import org.evasive.me.cosmicPrisonsCore.mining.records.BlockPos;

import java.util.Set;

public class BlockRespawner {

    public void respawnChecker(){
        new BukkitRunnable() {
            @Override
            public void run() {
                long time = System.currentTimeMillis();
                if(!CosmicPrisonsCore.blockRespawnMap.hasThisTime(time))
                    return;
                while(!CosmicPrisonsCore.blockRespawnMap.isEmpty() && CosmicPrisonsCore.blockRespawnMap.firstKey() <= (time/1000)){
                    long key = CosmicPrisonsCore.blockRespawnMap.firstKey();
                    Set<BlockRespawnData> tempRespawnData = CosmicPrisonsCore.blockRespawnMap.getRespawnSet(key*1000);
                    if(tempRespawnData == null)
                        return;
                    respawnSet(tempRespawnData);
                    CosmicPrisonsCore.blockRespawnMap.removeRespawnTime(key*1000);
                }
            }
        }.runTaskTimer(CosmicPrisonsCore.core, 0L, 20L); // delay = 0, repeat every 20 ticks (1 second)
    }

    public void respawnSet(Set<BlockRespawnData> blockRespawnDataSet){

        for (BlockRespawnData blockRespawnData : blockRespawnDataSet) {
            BlockPos blockPos = blockRespawnData.getBlockPos();
            Block block = BlockPos.getLocation(blockPos).getBlock();
            block.setType(blockRespawnData.getMaterial());
            if(OreType.valueOf(blockRespawnData.getMaterial().name()).ordinal() % 2 != 0)
                new MiningBlockRespawn().clearRefinedBlock(block);
            if(CosmicPrisonsCore.miningMap.containsBlockLocation(blockPos)){
                //If this block is broken need to remove the respawn being added
                new MiningBlockBreak().resetBlockAnimations(block);
                CosmicPrisonsCore.miningMap.removeBlockPos(blockPos);
            }
        }
    }

}
