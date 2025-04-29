package org.evasive.me.cosmicPrisonsCore.enchanting.mining.pickaxe.simple;

import org.evasive.me.cosmicPrisonsCore.rarity.Rarity;
import org.evasive.me.cosmicPrisonsCore.enchanting.WormholePickaxeEnchantBuilder;
import org.evasive.me.cosmicPrisonsCore.enchanting.mining.PickaxeEnchants;

public class ShardDiscoverer implements WormholePickaxeEnchantBuilder {
    @Override
    public String getName() {
        return "Shard Discoverer";
    }

    @Override
    public String getDescription() {
        return "Higher chance to discover shards while mining";
    }

    @Override
    public PickaxeEnchants getEnchant() {
        return PickaxeEnchants.SHARD_DISCOVERER;
    }

    @Override
    public Rarity getRarity() {
        return Rarity.SIMPLE;
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }
}
