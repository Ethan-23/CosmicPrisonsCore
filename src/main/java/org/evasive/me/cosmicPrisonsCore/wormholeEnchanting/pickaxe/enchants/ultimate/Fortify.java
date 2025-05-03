package org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.pickaxe.enchants.ultimate;

import org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.pickaxe.WormholePickaxeEnchantBuilder;
import org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.pickaxe.PickaxeEnchants;
import org.evasive.me.cosmicPrisonsCore.rarity.Rarity;

public class Fortify implements WormholePickaxeEnchantBuilder {
    @Override
    public String getName() {
        return "Fortify";
    }

    @Override
    public String getDescription() {
        return "Gain a health boost after mining for 30 seconds";
    }

    @Override
    public PickaxeEnchants getEnchant() {
        return PickaxeEnchants.FORTIFY;
    }

    @Override
    public Rarity getRarity() {
        return Rarity.ULTIMATE;
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }
}
