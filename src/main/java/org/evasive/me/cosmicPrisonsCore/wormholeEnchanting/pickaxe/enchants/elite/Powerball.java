package org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.pickaxe.enchants.elite;

import org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.pickaxe.WormholePickaxeEnchantBuilder;
import org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.pickaxe.PickaxeEnchants;
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
