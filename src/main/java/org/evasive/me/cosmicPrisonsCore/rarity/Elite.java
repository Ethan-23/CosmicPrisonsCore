package org.evasive.me.cosmicPrisonsCore.rarity;

import org.bukkit.Material;

public class Elite implements RarityBuilder{
    @Override
    public String getTextColor() {
        return "&9";
    }

    @Override
    public Material getTierMaterial() {
        return Material.CYAN_DYE;
    }
}
