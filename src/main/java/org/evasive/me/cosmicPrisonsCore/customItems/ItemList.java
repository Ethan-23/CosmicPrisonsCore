package org.evasive.me.cosmicPrisonsCore.customItems;

import org.evasive.me.cosmicPrisonsCore.customItems.energy.CosmicEnergy;
import org.evasive.me.cosmicPrisonsCore.customItems.pickaxes.*;
import org.evasive.me.cosmicPrisonsCore.customItems.satchels.*;
import org.evasive.me.cosmicPrisonsCore.customItems.shards.SimpleShard;

public enum ItemList {
    WOODEN_PICKAXE(new WoodenPickaxe()),
    STONE_PICKAXE(new StonePickaxe()),
    GOLDEN_PICKAXE(new GoldenPickaxe()),
    IRON_PICKAXE(new IronPickaxe()),
    DIAMOND_PICKAXE(new DiamondPickaxe()),
    COSMIC_ENERGY(new CosmicEnergy()),
    SIMPLE_SHARD(new SimpleShard()),
    COAL_ORE_SATCHEL(new CoalOreSatchel()),
    COAL_SATCHEL(new CoalSatchel()),
    IRON_ORE_SATCHEL(new IronOreSatchel()),
    IRON_SATCHEL(new IronSatchel()),
    LAPIS_ORE_SATCHEL(new LapisOreSatchel()),
    LAPIS_SATCHEL(new LapisSatchel()),
    REDSTONE_ORE_SATCHEL(new RedstoneOreSatchel()),
    REDSTONE_SATCHEL(new RedstoneSatchel()),
    GOLD_ORE_SATCHEL(new GoldOreSatchel()),
    GOLD_SATCHEL(new GoldSatchel()),
    DIAMOND_ORE_SATCHEL(new DiamondOreSatchel()),
    DIAMOND_SATCHEL(new DiamondSatchel()),
    EMERALD_ORE_SATCHEL(new EmeraldOreSatchel()),
    EMERALD_SATCHEL(new EmeraldSatchel());

    private final ItemBuilder itemBuilder;

    ItemList(ItemBuilder itemBuilder){this.itemBuilder = itemBuilder;}

    public ItemBuilder getItemBuilder(){
        return itemBuilder;
    }
}
