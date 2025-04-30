package org.evasive.me.cosmicPrisonsCore.mining.ores;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.evasive.me.cosmicPrisonsCore.customItems.ItemList;

import java.util.List;

public class IronOre implements OreCreator {
    @Override
    public String getName() {
        return "&7&lIron Ore";
    }

    @Override
    public Material getMaterial() {
        return Material.IRON_ORE;
    }

    @Override
    public Material getRefinedMaterial() {
        return Material.IRON_BLOCK;
    }

    @Override
    public Material getRespawnMaterial() {
        return Material.IRON_ORE;
    }

    @Override
    public List<ItemStack> getShards() {
        return List.of(ItemList.SIMPLE_SHARD.getItemBuilder().getItem(), ItemList.SIMPLE_SHARD.getItemBuilder().getItem());
    }

    @Override
    public float getHardness() {
        return 1f;
    }

    @Override
    public Material getItemDrop() {
        return Material.IRON_ORE;
    }

    @Override
    public int getExperience() {
        return 6;
    }

    @Override
    public int getRespawnTime() {
        return 30;
    }

    @Override
    public int mineableLevel() {
        return 10;
    }
}
