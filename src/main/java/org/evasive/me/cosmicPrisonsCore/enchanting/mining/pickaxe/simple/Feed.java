package org.evasive.me.cosmicPrisonsCore.enchanting.mining.pickaxe.simple;

import org.evasive.me.cosmicPrisonsCore.rarity.Rarity;
import org.evasive.me.cosmicPrisonsCore.enchanting.WormholePickaxeEnchantBuilder;
import org.evasive.me.cosmicPrisonsCore.enchanting.mining.PickaxeEnchants;

public class Feed implements WormholePickaxeEnchantBuilder {
    @Override
    public String getName() {
        return "Feed";
    }

    @Override
    public String getDescription() {
        return "Chance to feed yourself while mining";
    }

    @Override
    public PickaxeEnchants getEnchant() {
        return PickaxeEnchants.FEED;
    }

    @Override
    public Rarity getRarity() {
        return Rarity.SIMPLE;
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }
}
