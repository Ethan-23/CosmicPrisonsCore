package org.evasive.me.cosmicPrisonsCore.enchanting.mining.pickaxe.ultimate;

import org.evasive.me.cosmicPrisonsCore.enchanting.WormholePickaxeEnchantBuilder;
import org.evasive.me.cosmicPrisonsCore.enchanting.mining.PickaxeEnchants;
import org.evasive.me.cosmicPrisonsCore.rarity.Rarity;

public class Magnetism implements WormholePickaxeEnchantBuilder {
    @Override
    public String getName() {
        return "Magnetism";
    }

    @Override
    public String getDescription() {
        return "Draws richer ores closer towards the ore your mining";
    }

    @Override
    public PickaxeEnchants getEnchant() {
        return PickaxeEnchants.MAGNETISM;
    }

    @Override
    public Rarity getRarity() {
        return Rarity.ULTIMATE;
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }
}
