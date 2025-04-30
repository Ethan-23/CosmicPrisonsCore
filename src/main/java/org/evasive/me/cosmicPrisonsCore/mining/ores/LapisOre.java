package org.evasive.me.cosmicPrisonsCore.mining.ores;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.evasive.me.cosmicPrisonsCore.customItems.ItemList;

import java.util.List;

public class LapisOre implements OreCreator{
    @Override
    public String getName() {
        return "&9&lLapis Ore";
    }

    @Override
    public Material getMaterial() {
        return Material.LAPIS_ORE;
    }

    @Override
    public Material getRefinedMaterial() {
        return Material.LAPIS_BLOCK;
    }

    @Override
    public Material getRespawnMaterial() {
        return Material.LAPIS_ORE;
    }

    @Override
    public List<ItemStack> getShards() {
        return List.of(ItemList.SIMPLE_SHARD.getItemBuilder().getItem(), ItemList.SIMPLE_SHARD.getItemBuilder().getItem());
    }

    @Override
    public float getHardness() {
        return 3f;
    }

    @Override
    public Material getItemDrop() {
        return Material.LAPIS_ORE;
    }

    @Override
    public int getExperience() {
        return 18;
    }

    @Override
    public int getRespawnTime() {
        return 45;
    }

    @Override
    public int mineableLevel() {
        return 30;
    }
}
