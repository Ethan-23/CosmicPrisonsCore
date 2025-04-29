package org.evasive.me.cosmicPrisonsCore.customItems.shards;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.evasive.me.cosmicPrisonsCore.customItems.ItemMaker;
import org.evasive.me.cosmicPrisonsCore.rarity.Rarity;

public class SimpleShard implements ShardBuilder{

    @Override
    public String getID() {
        return "SIMPLE_SHARD";
    }

    @Override
    public Material getMaterial() {
        return Material.PRISMARINE_SHARD;
    }

    @Override
    public boolean isGlowing() {
        return false;
    }

    @Override
    public ItemStack getItem() {
        return ItemMaker.simpleShard;
    }

    @Override
    public Rarity getRatiry() {
        return Rarity.SIMPLE;
    }
}
