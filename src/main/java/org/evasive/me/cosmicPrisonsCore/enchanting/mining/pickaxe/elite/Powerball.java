package org.evasive.me.cosmicPrisonsCore.enchanting.mining.pickaxe.elite;

import org.evasive.me.cosmicPrisonsCore.enchanting.WormholePickaxeEnchantBuilder;
import org.evasive.me.cosmicPrisonsCore.enchanting.mining.PickaxeEnchants;
import org.evasive.me.cosmicPrisonsCore.rarity.Rarity;

public class Powerball implements WormholePickaxeEnchantBuilder {
    @Override
    public String getName() {
        return "Powerball";
    }

    @Override
    public String getDescription() {
        return "Hold right click to launch a powerful ball that harvests ores.";
    }

    @Override
    public PickaxeEnchants getEnchant() {
        return PickaxeEnchants.POWERBALL;
    }

    @Override
    public Rarity getRarity() {
        return Rarity.ELITE;
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }
}
