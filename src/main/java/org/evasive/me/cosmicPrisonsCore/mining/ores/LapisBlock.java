package org.evasive.me.cosmicPrisonsCore.mining.ores;

import org.bukkit.Material;

public class LapisBlock extends LapisOre{

    @Override
    public String getName() {
        return "&2&lLapis";
    }

    @Override
    public Material getItemDrop() {
        return Material.LAPIS_LAZULI;
    }

    @Override
    public Material getMaterial() {
        return Material.LAPIS_BLOCK;
    }
}
