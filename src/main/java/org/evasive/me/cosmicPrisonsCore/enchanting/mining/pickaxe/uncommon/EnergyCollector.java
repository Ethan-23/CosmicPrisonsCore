package org.evasive.me.cosmicPrisonsCore.enchanting.mining.pickaxe.uncommon;

import org.evasive.me.cosmicPrisonsCore.enchanting.WormholePickaxeEnchantBuilder;
import org.evasive.me.cosmicPrisonsCore.enchanting.mining.PickaxeEnchants;
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
