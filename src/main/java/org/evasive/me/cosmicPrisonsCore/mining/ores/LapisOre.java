package org.evasive.me.cosmicPrisonsCore.mining.ores;

import org.bukkit.Material;

public class LapisOre implements OreCreator{
    @Override
    public String getName() {
        return "Lapis Ore";
    }

    @Override
    public Material getMaterial() {
        return Material.LAPIS_ORE;
    }

    @Override
    public Material getRefinedMaterial() {
        return Material.LAPIS_BLOCK;
    }

    @Override
    public float getHardness() {
        return 3f;
    }

    @Override
    public Material getItemDrop() {
        return Material.LAPIS_ORE;
    }

    @Override
    public int getExperience() {
        return 18;
    }

    @Override
    public int getRespawnTime() {
        return 45;
    }
}
