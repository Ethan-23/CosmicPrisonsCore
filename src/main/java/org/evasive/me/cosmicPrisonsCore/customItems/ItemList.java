package org.evasive.me.cosmicPrisonsCore.customItems;

import org.evasive.me.cosmicPrisonsCore.customItems.energy.CosmicEnergy;
import org.evasive.me.cosmicPrisonsCore.customItems.pickaxes.*;
import org.evasive.me.cosmicPrisonsCore.customItems.shards.SimpleShard;

public enum ItemList {
    WOODEN_PICKAXE(new WoodenPickaxe()),
    STONE_PICKAXE(new StonePickaxe()),
    GOLDEN_PICKAXE(new GoldenPickaxe()),
    IRON_PICKAXE(new IronPickaxe()),
    DIAMOND_PICKAXE(new DiamondPickaxe()),
    COSMIC_ENERGY(new CosmicEnergy()),
    SIMPLE_SHARD(new SimpleShard());

    private final ItemBuilder itemBuilder;

    ItemList(ItemBuilder itemBuilder){this.itemBuilder = itemBuilder;}

    public ItemBuilder getItemBuilder(){
        return itemBuilder;
    }
}
