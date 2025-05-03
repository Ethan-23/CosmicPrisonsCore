package org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.pickaxe.enchants.uncommon;

import org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.pickaxe.WormholePickaxeEnchantBuilder;
import org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.pickaxe.PickaxeEnchants;
import org.evasive.me.cosmicPrisonsCore.rarity.Rarity;

public class EnergyCollector implements WormholePickaxeEnchantBuilder {
    @Override
    public String getName() {
        return "Energy Collector";
    }

    @Override
    public String getDescription() {
        return "Gain additional energy while mining.";
    }

    @Override
    public PickaxeEnchants getEnchant() {
        return PickaxeEnchants.ENERGY_COLLECTOR;
    }

    @Override
    public Rarity getRarity() {
        return Rarity.UNCOMMON;
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }
}
