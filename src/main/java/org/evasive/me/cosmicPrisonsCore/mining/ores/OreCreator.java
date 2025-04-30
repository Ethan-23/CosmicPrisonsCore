package org.evasive.me.cosmicPrisonsCore.mining.ores;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public interface OreCreator {
    String getName();
    Material getMaterial();
    Material getRefinedMaterial();
    Material getRespawnMaterial();
    List<ItemStack> getShards();
    float getHardness();
    Material getItemDrop();
    int getExperience();
    int getRespawnTime();
    int mineableLevel();
}
