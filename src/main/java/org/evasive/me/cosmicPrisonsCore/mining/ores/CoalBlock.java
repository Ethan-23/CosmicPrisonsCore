package org.evasive.me.cosmicPrisonsCore.mining.ores;

import org.bukkit.Material;

public class CoalBlock extends CoalOre {

    @Override
    public String getName() {
        return "&8&lCoal";
    }

    @Override
    public Material getItemDrop() {
        return Material.COAL;
    }

    @Override
    public int getRespawnTime(){
        return 10;
    }

    @Override
    public Material getMaterial() {
        return Material.COAL_BLOCK;
    }

}
