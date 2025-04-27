package org.evasive.me.cosmicPrisonsCore.customItems.rarity;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Material;

public interface RarityBuilder {
    String getTextColor();
    Material getTierMaterial();
}
