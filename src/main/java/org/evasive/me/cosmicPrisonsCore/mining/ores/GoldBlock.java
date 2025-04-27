package org.evasive.me.cosmicPrisonsCore.mining.ores;

import org.bukkit.Material;

public class GoldBlock extends GoldOre{
    @Override
    public Material getItemDrop() {
        return Material.GOLD_INGOT;
    }
}
