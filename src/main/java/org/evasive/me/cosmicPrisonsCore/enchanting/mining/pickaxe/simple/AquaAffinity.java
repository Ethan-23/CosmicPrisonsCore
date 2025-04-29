package org.evasive.me.cosmicPrisonsCore.enchanting.mining.pickaxe.simple;

import org.evasive.me.cosmicPrisonsCore.rarity.Rarity;
import org.evasive.me.cosmicPrisonsCore.enchanting.WormholePickaxeEnchantBuilder;
import org.evasive.me.cosmicPrisonsCore.enchanting.mining.PickaxeEnchants;

public class AquaAffinity implements WormholePickaxeEnchantBuilder {
    @Override
    public String getName() {
        return "Aqua Affinity";
    }

    @Override
    public String getDescription() {
        return "Increases underwater mining rate";
    }

    @Override
    public PickaxeEnchants getEnchant() {
        return PickaxeEnchants.AQUA_AFFINITY;
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
