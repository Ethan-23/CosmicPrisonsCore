package org.evasive.me.cosmicPrisonsCore.mining.ores;

import org.bukkit.Material;

public class RedstoneBlock extends RedstoneOre{

    @Override
    public String getName() {
        return "&4&lRedstone";
    }

    @Override
    public Material getItemDrop() {
        return Material.REDSTONE;
    }

    @Override
    public Material getMaterial() {
        return Material.REDSTONE_BLOCK;
    }
}
