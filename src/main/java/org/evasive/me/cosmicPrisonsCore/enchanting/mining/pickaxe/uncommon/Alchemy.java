package org.evasive.me.cosmicPrisonsCore.enchanting.mining.pickaxe.uncommon;

import org.evasive.me.cosmicPrisonsCore.enchanting.WormholePickaxeEnchantBuilder;
import org.evasive.me.cosmicPrisonsCore.enchanting.mining.PickaxeEnchants;
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
