package org.evasive.me.cosmicPrisonsCore.rarity;

import org.bukkit.Material;

public class Ultimate implements RarityBuilder{
    @Override
    public String getTextColor() {
        return "&e";
    }

    @Override
    public Material getTierMaterial() {
        return Material.YELLOW_DYE;
    }
}
