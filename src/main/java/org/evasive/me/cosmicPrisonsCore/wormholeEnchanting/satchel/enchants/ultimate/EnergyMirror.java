package org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.satchel.enchants.ultimate;

import org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.satchel.WormholeSatchelEnchantBuilder;
import org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.satchel.SatchelEnchants;
import org.evasive.me.cosmicPrisonsCore.rarity.Rarity;

public class EnergyMirror implements WormholeSatchelEnchantBuilder {
    @Override
    public SatchelEnchants getEnchant() {
        return SatchelEnchants.ENERGY_MIRROR;
    }

    @Override
    public String getName() {
        return "Energy Mirror";
    }

    @Override
    public String getDescription() {
        return "Chance to gain energy on your satchel while mining.";
    }

    @Override
    public Rarity getRarity() {
        return Rarity.ULTIMATE;
    }

    @Override
    public int getMaxLevel() {
        return 6;
    }
}
