package org.evasive.me.cosmicPrisonsCore.mining.ores;

import org.bukkit.Material;

public class DiamondOre implements OreCreator{
    @Override
    public String getName() {
        return "Diamond Ore";
    }

    @Override
    public Material getMaterial() {
        return Material.DIAMOND_ORE;
    }

    @Override
    public Material getRefinedMaterial() {
        return Material.DIAMOND_BLOCK;
    }

    @Override
    public float getHardness() {
        return 8f;
    }

    @Override
    public Material getItemDrop() {
        return Material.DIAMOND_ORE;
    }

    @Override
    public int getExperience() {
        return 486;
    }

    @Override
    public int getRespawnTime() {
        return 120;
    }
}
