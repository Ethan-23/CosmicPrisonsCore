package org.evasive.me.cosmicPrisonsCore.mining;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.scheduler.BukkitRunnable;
import org.evasive.me.cosmicPrisonsCore.CosmicPrisonsCore;
import org.evasive.me.cosmicPrisonsCore.mining.data.BlockRespawnData;
import org.evasive.me.cosmicPrisonsCore.mining.records.BlockPos;

import java.util.Set;

public class BlockRespawner {

    public void respawnChecker(){
        new BukkitRunnable() {
            @Override
            public void run() {
                // Your repeating code here
                long time = System.currentTimeMillis();
                if(!CosmicPrisonsCore.blockRespawnMap.hasThisTime(time))
                    return;
                while(!CosmicPrisonsCore.blockRespawnMap.isEmpty() && CosmicPrisonsCore.blockRespawnMap.firstKey() <= (time/1000)){
                    long key = CosmicPrisonsCore.blockRespawnMap.firstKey();
                    Set<BlockRespawnData> tempRespawnData = CosmicPrisonsCore.blockRespawnMap.getRespawnSet(key*1000);
                    if(tempRespawnData == null)
                        return;
                    for (BlockRespawnData item : tempRespawnData) {
                        BlockPos blockPos = item.getBlockPos();
                        Block block = BlockPos.getLocation(blockPos).getBlock();
                        block.setType(item.getMaterial());
                    }
                    CosmicPrisonsCore.blockRespawnMap.removeRespawnTime(key*1000);
                }
            }
        }.runTaskTimer(CosmicPrisonsCore.core, 0L, 20L); // delay = 0, repeat every 20 ticks (1 second)
    }

}
