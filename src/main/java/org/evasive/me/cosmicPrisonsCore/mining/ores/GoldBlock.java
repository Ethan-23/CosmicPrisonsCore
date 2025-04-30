package org.evasive.me.cosmicPrisonsCore.mining.ores;

import org.bukkit.Material;

public class GoldBlock extends GoldOre{

    @Override
    public String getName() {
        return "&e&lGold";
    }

    @Override
    public Material getItemDrop() {
        return Material.GOLD_INGOT;
    }

    @Override
    public Material getMaterial() {
        return Material.GOLD_BLOCK;
    }
}
