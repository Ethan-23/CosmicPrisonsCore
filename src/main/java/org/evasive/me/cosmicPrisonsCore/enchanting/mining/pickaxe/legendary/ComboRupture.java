package org.evasive.me.cosmicPrisonsCore.enchanting.mining.pickaxe.legendary;

import org.evasive.me.cosmicPrisonsCore.enchanting.WormholePickaxeEnchantBuilder;
import org.evasive.me.cosmicPrisonsCore.enchanting.mining.PickaxeEnchants;
import org.evasive.me.cosmicPrisonsCore.rarity.Rarity;

public class ComboRupture implements WormholePickaxeEnchantBuilder {
    @Override
    public String getName() {
        return "Combo Rupture";
    }

    @Override
    public String getDescription() {
        return "Chance to shatter all combo blocks within a 5 block radius.";
    }

    @Override
    public PickaxeEnchants getEnchant() {
        return PickaxeEnchants.COMBO_RUPTURE;
    }

    @Override
    public Rarity getRarity() {
        return Rarity.LEGENDARY;
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }
}
