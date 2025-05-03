package org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.pickaxe.enchants.simple;

import org.evasive.me.cosmicPrisonsCore.rarity.Rarity;
import org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.pickaxe.WormholePickaxeEnchantBuilder;
import org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.pickaxe.PickaxeEnchants;

public class OreMagnet implements WormholePickaxeEnchantBuilder {
    @Override
    public String getName() {
        return "Ore Magnet";
    }

    @Override
    public String getDescription() {
        return "Receive more ores while mining";
    }

    @Override
    public PickaxeEnchants getEnchant() {
        return PickaxeEnchants.ORE_MAGNET;
    }

    @Override
    public Rarity getRarity() {
        return Rarity.SIMPLE;
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }
}
