package org.evasive.me.cosmicPrisonsCore.mining.process;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.evasive.me.cosmicPrisonsCore.CosmicPrisonsCore;
import org.evasive.me.cosmicPrisonsCore.mining.ores.OreCreator;
import org.evasive.me.cosmicPrisonsCore.mining.ores.OreType;
import org.evasive.me.cosmicPrisonsCore.mining.records.BlockPos;
import org.evasive.me.cosmicPrisonsCore.mining.respawn.BlockRespawnData;

import static org.evasive.me.cosmicPrisonsCore.mining.ores.functions.OreFunctions.getOreCreatorFromBlock;

public class MiningBlockRespawn {

    public void respawnBlock(Block block){
        OreCreator oreCreator = getOreCreatorFromBlock(block);
        checkForRefined(oreCreator, BlockPos.fromBlock(block));
        handleBlockRespawn(OreType.valueOf(oreCreator.getRespawnMaterial().name()).getOreCreator(), BlockPos.fromBlock(block));
    }

    public void clearRefinedBlock(Block block){
        OreCreator oreCreator = getOreCreatorFromBlock(block);
        handleBlockRespawn(oreCreator, BlockPos.fromBlock(block));
    }

    private void handleBlockRespawn(OreCreator oreCreator, BlockPos blockPos) {
        Material oreSpawn = isRefined(blockPos, oreCreator) ? oreCreator.getRefinedMaterial() : oreCreator.getRespawnMaterial();
        CosmicPrisonsCore.blockRespawnMap.addBlockRespawn(System.currentTimeMillis() + (1000L * oreCreator.getRespawnTime()), new BlockRespawnData(oreSpawn, oreCreator.getRefinedMaterial(), blockPos));
    }

    private boolean isRefined(BlockPos blockPos, OreCreator oreCreator) {
        return Math.random() > 0.99f && Bukkit.getWorld(blockPos.worldName()).getBlockAt(blockPos.x(), blockPos.y(), blockPos.z()).getType() != oreCreator.getRefinedMaterial();
    }

    public void checkForRefined(OreCreator oreCreator, BlockPos blockPos){
        if(!oreCreator.getMaterial().equals(oreCreator.getRefinedMaterial()))
            return;
        cancelRefinedTimer(oreCreator, blockPos);
    }

    private void cancelRefinedTimer(OreCreator oreCreator, BlockPos blockPos) {
        long startTime = System.currentTimeMillis();
        BlockRespawnData blockRespawnData = new BlockRespawnData(oreCreator.getRespawnMaterial(), oreCreator.getRefinedMaterial(), blockPos);
        for (long currentTime = startTime; currentTime < startTime + 10000; currentTime+=1000) {
            if(!CosmicPrisonsCore.blockRespawnMap.hasThisTime(currentTime))
                continue;
            if(!CosmicPrisonsCore.blockRespawnMap.getRespawnSet(currentTime).contains(blockRespawnData))
                continue;
            CosmicPrisonsCore.blockRespawnMap.getRespawnSet(currentTime).remove(blockRespawnData);
            break;
        }
    }

}
