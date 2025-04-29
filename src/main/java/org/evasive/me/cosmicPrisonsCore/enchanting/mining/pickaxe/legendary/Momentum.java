package org.evasive.me.cosmicPrisonsCore.enchanting.mining.pickaxe.legendary;

import org.evasive.me.cosmicPrisonsCore.enchanting.WormholePickaxeEnchantBuilder;
import org.evasive.me.cosmicPrisonsCore.enchanting.mining.PickaxeEnchants;
import org.evasive.me.cosmicPrisonsCore.rarity.Rarity;

public class Momentum implements WormholePickaxeEnchantBuilder {
    @Override
    public String getName() {
        return "Momentum";
    }

    @Override
    public String getDescription() {
        return "The longer you mine, the faster you mine.";
    }

    @Override
    public PickaxeEnchants getEnchant() {
        return PickaxeEnchants.MOMENTUM;
    }

    @Override
    public Rarity getRarity() {
        return Rarity.LEGENDARY;
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    public int getWormholeMaxLevel() {
        return 6;
    }
}
