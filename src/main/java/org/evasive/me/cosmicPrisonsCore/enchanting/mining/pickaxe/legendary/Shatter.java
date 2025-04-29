package org.evasive.me.cosmicPrisonsCore.enchanting.mining.pickaxe.legendary;

import org.evasive.me.cosmicPrisonsCore.enchanting.WormholePickaxeEnchantBuilder;
import org.evasive.me.cosmicPrisonsCore.enchanting.mining.PickaxeEnchants;
import org.evasive.me.cosmicPrisonsCore.rarity.Rarity;

public class Shatter implements WormholePickaxeEnchantBuilder {
    @Override
    public String getName() {
        return "Shatter";
    }

    @Override
    public String getDescription() {
        return "Chance to cause an explosion which mines up nearby ores.";
    }

    @Override
    public PickaxeEnchants getEnchant() {
        return PickaxeEnchants.SHATTER;
    }

    @Override
    public Rarity getRarity() {
        return Rarity.LEGENDARY;
    }

    @Override
    public int getMaxLevel() {
        return 6;
    }
}
