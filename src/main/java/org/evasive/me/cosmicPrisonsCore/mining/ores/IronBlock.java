package org.evasive.me.cosmicPrisonsCore.mining.ores;

import org.bukkit.Material;

public class IronBlock extends IronOre {

    @Override
    public String getName() {
        return "&7&lIron";
    }

    @Override
    public Material getItemDrop() {
        return Material.IRON_INGOT;
    }
}
