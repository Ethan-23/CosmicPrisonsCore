package org.evasive.me.cosmicPrisonsCore.mining.ores;

import org.bukkit.Material;

public class EmeraldBlock extends EmeraldOre{

    @Override
    public String getName() {
        return "&a&lEmerald";
    }

    @Override
    public Material getItemDrop() {
        return Material.EMERALD;
    }
}
