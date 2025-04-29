package org.evasive.me.cosmicPrisonsCore.enchanting.mining.pickaxe.simple;

import org.evasive.me.cosmicPrisonsCore.rarity.Rarity;
import org.evasive.me.cosmicPrisonsCore.enchanting.WormholePickaxeEnchantBuilder;
import org.evasive.me.cosmicPrisonsCore.enchanting.mining.PickaxeEnchants;

public class SixthSense implements WormholePickaxeEnchantBuilder {
    @Override
    public String getName() {
        return "Sixth Sense";
    }

    @Override
    public String getDescription() {
        return "Senses nearby enemies while mining meteorites";
    }

    @Override
    public PickaxeEnchants getEnchant() {
        return PickaxeEnchants.SIXTH_SENSE;
    }

    @Override
    public Rarity getRarity() {
        return Rarity.SIMPLE;
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }
}
