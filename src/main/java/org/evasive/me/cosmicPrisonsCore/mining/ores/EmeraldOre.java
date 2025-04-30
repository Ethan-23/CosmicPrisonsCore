package org.evasive.me.cosmicPrisonsCore.mining.ores;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.evasive.me.cosmicPrisonsCore.customItems.ItemList;

import java.util.List;

public class EmeraldOre implements OreCreator{
    @Override
    public String getName() {
        return "&a&lEmerald Ore";
    }

    @Override
    public Material getMaterial() {
        return Material.EMERALD_ORE;
    }

    @Override
    public Material getRefinedMaterial() {
        return Material.EMERALD;
    }

    @Override
    public Material getRespawnMaterial() {
        return Material.EMERALD_ORE;
    }

    @Override
    public List<ItemStack> getShards() {
        return List.of(ItemList.SIMPLE_SHARD.getItemBuilder().getItem(), ItemList.SIMPLE_SHARD.getItemBuilder().getItem());
    }

    @Override
    public float getHardness() {
        return 11f;
    }

    @Override
    public Material getItemDrop() {
        return Material.EMERALD_ORE;
    }

    @Override
    public int getExperience() {
        return 1458;
    }

    @Override
    public int getRespawnTime() {
        return 120;
    }

    @Override
    public int mineableLevel() {
        return 100;
    }
}
