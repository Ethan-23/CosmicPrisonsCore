package org.evasive.me.cosmicPrisonsCore.mining.ores;

import org.bukkit.Material;

public class DiamondBlock extends DiamondOre{

    @Override
    public String getName() {
        return "&b&lDiamond";
    }

    @Override
    public Material getItemDrop() {
        return Material.DIAMOND;
    }
}
