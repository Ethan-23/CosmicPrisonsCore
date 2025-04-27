package org.evasive.me.cosmicPrisonsCore.mining.ores;

import org.bukkit.Material;

public class CoalOre implements OreCreator {
    @Override
    public String getName() {
        return "Coal Ore";
    }

    @Override
    public Material getMaterial() {
        return Material.COAL_ORE;
    }

    @Override
    public Material getRefinedMaterial() {
        return Material.COAL_BLOCK;
    }

    @Override
    public float getHardness() {
        return 0.6f;
    }

    @Override
    public Material getItemDrop() {
        return Material.COAL_ORE;
    }

    @Override
    public int getExperience() {
        return 2;
    }

    @Override
    public int getRespawnTime() {
        return 15;
    }
}
