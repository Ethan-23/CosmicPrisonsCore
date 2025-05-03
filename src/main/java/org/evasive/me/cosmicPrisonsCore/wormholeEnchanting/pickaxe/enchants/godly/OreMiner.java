package org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.pickaxe.enchants.godly;

import org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.pickaxe.WormholePickaxeEnchantBuilder;
import org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.pickaxe.PickaxeEnchants;
import org.evasive.me.cosmicPrisonsCore.rarity.Rarity;

public class OreMiner implements WormholePickaxeEnchantBuilder {
    @Override
    public String getName() {
        return "Ore Miner";
    }

    @Override
    public String getDescription() {
        return "Gain boosted ores while mining in the Overworld and Executive Mines";
    }

    @Override
    public PickaxeEnchants getEnchant() {
        return PickaxeEnchants.ORE_MINER;
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
