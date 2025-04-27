package org.evasive.me.cosmicPrisonsCore.mining.ores;

import org.bukkit.Material;

public class EmeraldOre implements OreCreator{
    @Override
    public String getName() {
        return "Emerald Ore";
    }

    @Override
    public Material getMaterial() {
        return Material.EMERALD_ORE;
    }

    @Override
    public Material getRefinedMaterial() {
        return Material.EMERALD;
    }

    @Override
    public float getHardness() {
        return 11f;
    }

    @Override
    public Material getItemDrop() {
        return Material.EMERALD_ORE;
    }

    @Override
    public int getExperience() {
        return 1458;
    }

    @Override
    public int getRespawnTime() {
        return 120;
    }
}
