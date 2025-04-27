package org.evasive.me.cosmicPrisonsCore.mining.data;

import org.bukkit.Bukkit;
import org.evasive.me.cosmicPrisonsCore.CosmicPrisonsCore;
import org.evasive.me.cosmicPrisonsCore.mining.records.BlockPos;

import java.util.*;

public class MiningMap {
    private Map<BlockPos, Map<UUID, MiningBlockData>> miningMap = new HashMap<>();

    public void addBlockPos(BlockPos blockPos, UUID uuid, MiningBlockData miningBlockData){
        Map<UUID, MiningBlockData> tempMap;

        if(!miningMap.containsKey(blockPos))
            tempMap = new HashMap<>();
        else
            tempMap = miningMap.get(blockPos);

        if(tempMap.containsKey(uuid))
            return;

        tempMap.put(uuid, miningBlockData);
        miningMap.put(blockPos, tempMap);
    }


    public void removeBlockPos(BlockPos blockPos){
        //Clear all player mining packets that were sent by sending a 0 packet to list of player names
        //Or maybe to all if its faster
        for (Map.Entry<UUID, MiningBlockData> entry : miningMap.get(blockPos).entrySet()) {
            CosmicPrisonsCore.animationIds.releaseAnimationId(entry.getValue().getAnimationId());
        }
        miningMap.remove(blockPos);
    }

    public boolean containsBlockLocation(BlockPos blockPos){
        return (miningMap.containsKey(blockPos));
    }

    public boolean containsPlayerAtLocation(BlockPos blockPos, UUID uniqueId) {
        if(miningMap.containsKey(blockPos))
            return miningMap.get(blockPos).containsKey(uniqueId);
        return false;
    }

    public void IncreaseBlockBreak(BlockPos blockPos, UUID uniqueId, float progress) {
        Map<UUID, MiningBlockData> tempMap = miningMap.get(blockPos);
        MiningBlockData miningBlockData = tempMap.get(uniqueId);
        if(miningBlockData.getProgress() < 100)
            miningBlockData.setProgress(miningBlockData.getProgress() + progress);
        tempMap.put(uniqueId, miningBlockData);
        miningMap.put(blockPos, tempMap);
    }

    public float getBlockProgress(BlockPos blockPos, UUID uniqueId) {
        return miningMap.get(blockPos).get(uniqueId).getProgress();
    }

    public int getPlayerBlockAnimationId(BlockPos blockPos, UUID uniqueId) {
        return miningMap.get(blockPos).get(uniqueId).getAnimationId();
    }

    public Map<UUID, MiningBlockData> getPlayerBlockProgress(BlockPos blockPos) {
        return miningMap.get(blockPos);
    }
}
