package org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.pickaxe.enchants.elite;

import org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.pickaxe.WormholePickaxeEnchantBuilder;
import org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.pickaxe.PickaxeEnchants;
import org.evasive.me.cosmicPrisonsCore.rarity.Rarity;

public class SuperBreaker implements WormholePickaxeEnchantBuilder {
    @Override
    public String getName() {
        return "Super Breaker";
    }

    @Override
    public String getDescription() {
        return "Chance to gain an insane mining speed boost for a short period of time.";
    }

    @Override
    public PickaxeEnchants getEnchant() {
        return PickaxeEnchants.SUPER_BREAKER;
    }

    @Override
    public Rarity getRarity() {
        return Rarity.ELITE;
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }
}
