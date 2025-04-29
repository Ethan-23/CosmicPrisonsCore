package org.evasive.me.cosmicPrisonsCore.enchanting.mining.pickaxe.simple;

import org.evasive.me.cosmicPrisonsCore.rarity.Rarity;
import org.evasive.me.cosmicPrisonsCore.enchanting.WormholePickaxeEnchantBuilder;
import org.evasive.me.cosmicPrisonsCore.enchanting.mining.PickaxeEnchants;

public class Efficiency implements WormholePickaxeEnchantBuilder {
    @Override
    public String getName() {
        return "Efficiency";
    }

    @Override
    public String getDescription() {
        return "Increases mining speed";
    }

    @Override
    public PickaxeEnchants getEnchant() {
        return PickaxeEnchants.EFFICIENCY;
    }

    @Override
    public Rarity getRarity() {
        return Rarity.SIMPLE;
    }

    @Override
    public int getMaxLevel() {
        return 6;
    }

    @Override
    public int getWormholeMaxLevel() {
        return 5;
    }
}
