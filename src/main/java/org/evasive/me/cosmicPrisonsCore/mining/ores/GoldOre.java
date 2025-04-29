package org.evasive.me.cosmicPrisonsCore.mining.ores;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.evasive.me.cosmicPrisonsCore.utils.ComponentUtils;

public class GoldOre implements OreCreator{
    @Override
    public String getName() {
        return "&e&lGold Ore";
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

    @Override
    public int mineableLevel() {
        return 70;
    }
}
