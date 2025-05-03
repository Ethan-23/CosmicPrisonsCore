package org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.pickaxe.enchants.ultimate;

import org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.pickaxe.WormholePickaxeEnchantBuilder;
import org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.pickaxe.PickaxeEnchants;
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
