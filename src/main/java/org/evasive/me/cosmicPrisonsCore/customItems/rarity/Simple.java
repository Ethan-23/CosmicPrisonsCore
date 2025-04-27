package org.evasive.me.cosmicPrisonsCore.customItems.rarity;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Material;
import org.evasive.me.cosmicPrisonsCore.utils.ComponentUtils;

public class Simple implements RarityBuilder{
    @Override
    public String getTextColor() {
        return "&8";
    }

    @Override
    public Material getTierMaterial() {
        return Material.GRAY_DYE;
    }
}
