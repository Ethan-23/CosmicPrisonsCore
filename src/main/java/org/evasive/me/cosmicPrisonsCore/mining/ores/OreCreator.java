package org.evasive.me.cosmicPrisonsCore.mining.ores;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public interface OreCreator {
    String getName();
    Material getMaterial();
    Material getRefinedMaterial();
    float getHardness();
    Material getItemDrop();
    int getExperience();
    int getRespawnTime();
    int mineableLevel();
}
