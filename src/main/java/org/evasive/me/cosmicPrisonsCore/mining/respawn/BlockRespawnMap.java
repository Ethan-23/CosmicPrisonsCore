package org.evasive.me.cosmicPrisonsCore.mining.respawn;

import java.util.*;

public class BlockRespawnMap {

    private final TreeMap<Long, Set<BlockRespawnData>> respawnMap = new TreeMap<>();

    public void addBlockRespawn(Long time, BlockRespawnData blockRespawnData){
        Set<BlockRespawnData> tempSet;

        if(!respawnMap.containsKey(time/1000))
            tempSet = new HashSet<>();
        else
            tempSet = respawnMap.get(time/1000);

        if(tempSet.contains(blockRespawnData))
            return;

        tempSet.add(blockRespawnData);
        respawnMap.put(time/1000, tempSet);
    }

    public void removeRespawnTime(Long time){
        respawnMap.remove(time/1000);
    }

    public Set<BlockRespawnData> getRespawnSet(Long time){
        return respawnMap.get(time/1000);
    }

    public boolean hasThisTime(long time) {
        return respawnMap.containsKey(time/1000);
    }

    public boolean isEmpty() {
        return respawnMap.isEmpty();
    }

    public long firstKey() {
        return respawnMap.firstKey();
    }

    public void forceRespawn(){
        for (Map.Entry<Long, Set<BlockRespawnData>> entry : respawnMap.entrySet()) {
            Set<BlockRespawnData> value = entry.getValue();
            new BlockRespawner().respawnSet(value);
        }
    }
}
