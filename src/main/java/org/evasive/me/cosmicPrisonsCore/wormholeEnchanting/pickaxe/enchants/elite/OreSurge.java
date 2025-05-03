package org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.pickaxe.enchants.elite;

import org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.pickaxe.WormholePickaxeEnchantBuilder;
import org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.pickaxe.PickaxeEnchants;
import org.evasive.me.cosmicPrisonsCore.rarity.Rarity;

public class OreSurge implements WormholePickaxeEnchantBuilder {
    @Override
    public String getName() {
        return "Ore Surge";
    }

    @Override
    public String getDescription() {
        return "Chance to shoot bolts of energy at nearby ore, enriching them.";
    }

    @Override
    public PickaxeEnchants getEnchant() {
        return PickaxeEnchants.ORE_SURGE;
    }

    @Override
    public Rarity getRarity() {
        return Rarity.ELITE;
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }
}
