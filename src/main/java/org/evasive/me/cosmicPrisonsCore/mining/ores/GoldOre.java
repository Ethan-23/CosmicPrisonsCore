package org.evasive.me.cosmicPrisonsCore.mining.ores;

import org.bukkit.Material;

public class GoldOre implements OreCreator{
    @Override
    public String getName() {
        return "Gold Ore";
    }

    @Override
    public Material getMaterial() {
        return Material.GOLD_ORE;
    }

    @Override
    public Material getRefinedMaterial() {
        return Material.GOLD_BLOCK;
    }

    @Override
    public float getHardness() {
        return 7f;
    }

    @Override
    public Material getItemDrop() {
        return Material.GOLD_ORE;
    }

    @Override
    public int getExperience() {
        return 162;
    }

    @Override
    public int getRespawnTime() {
        return 120;
    }
}
