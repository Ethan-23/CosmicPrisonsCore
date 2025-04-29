package org.evasive.me.cosmicPrisonsCore.enchanting.mining.pickaxe.elite;

import org.evasive.me.cosmicPrisonsCore.enchanting.WormholePickaxeEnchantBuilder;
import org.evasive.me.cosmicPrisonsCore.enchanting.mining.PickaxeEnchants;
import org.evasive.me.cosmicPrisonsCore.rarity.Rarity;

public class Replenish implements WormholePickaxeEnchantBuilder {
    @Override
    public String getName() {
        return "Replenish";
    }

    @Override
    public String getDescription() {
        return "Chance to replenish ores that you mine. (Does not work on generators.)";
    }

    @Override
    public PickaxeEnchants getEnchant() {
        return PickaxeEnchants.REPLENISH;
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
