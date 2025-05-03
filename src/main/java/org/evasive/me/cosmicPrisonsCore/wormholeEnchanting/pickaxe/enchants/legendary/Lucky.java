package org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.pickaxe.enchants.legendary;

import org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.pickaxe.WormholePickaxeEnchantBuilder;
import org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.pickaxe.PickaxeEnchants;
import org.evasive.me.cosmicPrisonsCore.rarity.Rarity;

public class Lucky implements WormholePickaxeEnchantBuilder {
    @Override
    public String getName() {
        return "Lucky";
    }

    @Override
    public String getDescription() {
        return "Increases the chance of other enchants on the same item.";
    }

    @Override
    public PickaxeEnchants getEnchant() {
        return PickaxeEnchants.LUCKY;
    }

    @Override
    public Rarity getRarity() {
        return Rarity.LEGENDARY;
    }

    @Override
    public int getMaxLevel() {
        return 4;
    }

    @Override
    public int getWormholeMaxLevel() {
        return 3;
    }
}
