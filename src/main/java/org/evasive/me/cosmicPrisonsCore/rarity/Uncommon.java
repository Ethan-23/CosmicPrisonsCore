package org.evasive.me.cosmicPrisonsCore.rarity;

import org.bukkit.Material;

public class Uncommon implements RarityBuilder{
    @Override
    public String getTextColor() {
        return "&a";
    }

    @Override
    public Material getTierMaterial() {
        return Material.LIME_DYE;
    }
}
