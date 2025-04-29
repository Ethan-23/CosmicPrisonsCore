package org.evasive.me.cosmicPrisonsCore.rarity;

import org.bukkit.Material;

public class Godly implements RarityBuilder{
    @Override
    public String getTextColor() {
        return "&c";
    }

    @Override
    public Material getTierMaterial() {
        return Material.RED_DYE;
    }
}
