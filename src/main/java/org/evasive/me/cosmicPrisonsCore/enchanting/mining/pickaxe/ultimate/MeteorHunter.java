package org.evasive.me.cosmicPrisonsCore.enchanting.mining.pickaxe.ultimate;

import org.evasive.me.cosmicPrisonsCore.enchanting.WormholePickaxeEnchantBuilder;
import org.evasive.me.cosmicPrisonsCore.enchanting.mining.PickaxeEnchants;
import org.evasive.me.cosmicPrisonsCore.rarity.Rarity;

public class MeteorHunter implements WormholePickaxeEnchantBuilder {
    @Override
    public String getName() {
        return "Meteor Hunter";
    }

    @Override
    public String getDescription() {
        return "Chance of receiving double Contrabands and faster mining speed from Meteors";
    }

    @Override
    public PickaxeEnchants getEnchant() {
        return PickaxeEnchants.METEOR_HUNTER;
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
