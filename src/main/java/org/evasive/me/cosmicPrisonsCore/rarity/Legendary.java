package org.evasive.me.cosmicPrisonsCore.rarity;

import org.bukkit.Material;

public class Legendary implements RarityBuilder{
    @Override
    public String getTextColor() {
        return "&6";
    }

    @Override
    public Material getTierMaterial() {
        return Material.ORANGE_DYE;
    }
}
