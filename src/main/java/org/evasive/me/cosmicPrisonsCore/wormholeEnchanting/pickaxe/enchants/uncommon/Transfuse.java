package org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.pickaxe.enchants.uncommon;

import org.evasive.me.cosmicPrisonsCore.rarity.Rarity;
import org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.pickaxe.WormholePickaxeEnchantBuilder;
import org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.pickaxe.PickaxeEnchants;

public class Transfuse implements WormholePickaxeEnchantBuilder {
    @Override
    public String getName() {
        return "Transfuse";
    }

    @Override
    public String getDescription() {
        return "Chance to turn mined materials into an upper tier.";
    }

    @Override
    public PickaxeEnchants getEnchant() {
        return PickaxeEnchants.TRANSFUSE;
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
