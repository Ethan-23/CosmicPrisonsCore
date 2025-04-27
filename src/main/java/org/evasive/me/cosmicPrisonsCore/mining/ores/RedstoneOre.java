package org.evasive.me.cosmicPrisonsCore.mining.ores;

import org.bukkit.Material;

public class RedstoneOre implements OreCreator{
    @Override
    public String getName() {
        return "Redstone Ore";
    }

    @Override
    public Material getMaterial() {
        return Material.REDSTONE_ORE;
    }

    @Override
    public Material getRefinedMaterial() {
        return Material.REDSTONE_BLOCK;
    }

    @Override
    public float getHardness() {
        return 5f;
    }

    @Override
    public Material getItemDrop() {
        return Material.REDSTONE_ORE;
    }

    @Override
    public int getExperience() {
        return 54;
    }

    @Override
    public int getRespawnTime() {
        return 60;
    }
}
