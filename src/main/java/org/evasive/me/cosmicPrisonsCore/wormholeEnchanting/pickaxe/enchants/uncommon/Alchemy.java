package org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.pickaxe.enchants.uncommon;

import org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.pickaxe.WormholePickaxeEnchantBuilder;
import org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.pickaxe.PickaxeEnchants;
import org.evasive.me.cosmicPrisonsCore.rarity.Rarity;

public class Alchemy implements WormholePickaxeEnchantBuilder {
    @Override
    public String getName() {
        return "Alchemy";
    }

    @Override
    public String getDescription() {
        return "Chance to turn mined ores into money automatically.";
    }

    @Override
    public PickaxeEnchants getEnchant() {
        return PickaxeEnchants.ALCHEMY;
    }

    @Override
    public Rarity getRarity() {
        return Rarity.UNCOMMON;
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }
}
