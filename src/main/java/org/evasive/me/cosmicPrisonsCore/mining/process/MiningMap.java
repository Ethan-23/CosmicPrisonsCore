package org.evasive.me.cosmicPrisonsCore.mining.process;

import org.evasive.me.cosmicPrisonsCore.CosmicPrisonsCore;
import org.evasive.me.cosmicPrisonsCore.mining.records.BlockPos;

import java.util.*;

public class MiningMap {
    private final Map<BlockPos, Map<UUID, MiningBlockData>> miningMap = new HashMap<>();

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

    public void increaseBlockBreak(BlockPos blockPos, UUID uuid, float progress) {
        if(!miningMap.containsKey(blockPos))
            return;
        Map<UUID, MiningBlockData> tempMap = miningMap.get(blockPos);
        MiningBlockData miningBlockData = tempMap.get(uuid);
        if(getTotalProgress(blockPos, uuid) < 100)
            miningBlockData.setProgress(miningBlockData.getProgress() + progress);
        tempMap.put(uuid, miningBlockData);
        miningMap.put(blockPos, tempMap);
    }

    public void increaseBlockFractureBreak(BlockPos blockPos, UUID uuid, float progress) {
        if(!miningMap.containsKey(blockPos))
            return;
        Map<UUID, MiningBlockData> tempMap = miningMap.get(blockPos);
        MiningBlockData miningBlockData = tempMap.get(uuid);
        if(getTotalProgress(blockPos, uuid) < 100)
            miningBlockData.setFractureProgress(miningBlockData.getFractureProgress() + progress);
        tempMap.put(uuid, miningBlockData);
        miningMap.put(blockPos, tempMap);
    }

    public float getBlockProgress(BlockPos blockPos, UUID uuid) {
        return miningMap.get(blockPos).get(uuid).getProgress();
    }

    public float getFractureProgress(BlockPos blockPos, UUID uuid){
        return miningMap.get(blockPos).get(uuid).getFractureProgress();
    }

    public float getTotalProgress(BlockPos blockPos, UUID uuid){
        return getBlockProgress(blockPos, uuid) + getFractureProgress(blockPos, uuid);
    }

    public int getPlayerBlockAnimationId(BlockPos blockPos, UUID uuid) {
        return miningMap.get(blockPos).get(uuid).getAnimationId();
    }

    public Map<UUID, MiningBlockData> getPlayerBlockProgress(BlockPos blockPos) {
        return miningMap.get(blockPos);
    }
}
