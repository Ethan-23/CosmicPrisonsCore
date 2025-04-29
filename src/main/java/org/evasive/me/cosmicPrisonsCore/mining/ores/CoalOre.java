package org.evasive.me.cosmicPrisonsCore.mining.ores;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.evasive.me.cosmicPrisonsCore.utils.ComponentUtils;

public class CoalOre implements OreCreator {
    @Override
    public String getName() {
        return "&8&lCoal Ore";
    }

    @Override
    public Material getMaterial() {
        return Material.COAL_ORE;
    }

    @Override
    public Material getRefinedMaterial() {
        return Material.COAL_BLOCK;
    }

    @Override
    public float getHardness() {
        return 0.6f;
    }

    @Override
    public Material getItemDrop() {
        return Material.COAL_ORE;
    }

    @Override
    public int getExperience() {
        return 2;
    }

    @Override
    public int getRespawnTime() {
        return 15;
    }

    @Override
    public int mineableLevel() {
        return 0;
    }
}
