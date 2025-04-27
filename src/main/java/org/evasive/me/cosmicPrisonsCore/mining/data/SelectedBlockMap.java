package org.evasive.me.cosmicPrisonsCore.mining.data;

import org.bukkit.block.Block;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SelectedBlockMap {

    private Map<UUID, Block> selectedBlock = new HashMap<>();

    public void addPlayer(UUID uuid, Block block){
        selectedBlock.put(uuid, block);
    }

    public boolean hasPlayer(UUID uuid){
        return selectedBlock.containsKey(uuid);
    }

    public void removePlayer(UUID uuid){
        selectedBlock.remove(uuid);
    }

    public Block getBlock(UUID uuid) {
        return selectedBlock.get(uuid);
    }
}
