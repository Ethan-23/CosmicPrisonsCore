package org.evasive.me.cosmicPrisonsCore.rarity;

import org.bukkit.Material;

public class Simple implements RarityBuilder{
    @Override
    public String getTextColor() {
        return "&f";
    }

    @Override
    public Material getTierMaterial() {
        return Material.GRAY_DYE;
    }
}
