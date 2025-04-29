package org.evasive.me.cosmicPrisonsCore.enchanting.mining.pickaxe.godly;

import org.evasive.me.cosmicPrisonsCore.enchanting.WormholePickaxeEnchantBuilder;
import org.evasive.me.cosmicPrisonsCore.enchanting.mining.PickaxeEnchants;
import org.evasive.me.cosmicPrisonsCore.rarity.Rarity;

public class EnergyBattery implements WormholePickaxeEnchantBuilder {
    @Override
    public String getName() {
        return "Energy Battery";
    }

    @Override
    public String getDescription() {
        return "Increases your pickaxe's energy capacity.";
    }

    @Override
    public PickaxeEnchants getEnchant() {
        return PickaxeEnchants.ENERGY_BATTERY;
    }

    @Override
    public Rarity getRarity() {
        return Rarity.GODLY;
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }
}
