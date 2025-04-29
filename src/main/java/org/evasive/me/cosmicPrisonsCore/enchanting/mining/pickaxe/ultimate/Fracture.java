package org.evasive.me.cosmicPrisonsCore.enchanting.mining.pickaxe.ultimate;

import org.evasive.me.cosmicPrisonsCore.enchanting.WormholePickaxeEnchantBuilder;
import org.evasive.me.cosmicPrisonsCore.enchanting.mining.PickaxeEnchants;
import org.evasive.me.cosmicPrisonsCore.rarity.Rarity;

public class Fracture implements WormholePickaxeEnchantBuilder {
    @Override
    public String getName() {
        return "Fracture";
    }

    @Override
    public String getDescription() {
        return "Chance to damage ores around your pickaxe as you mine. (Does not work on generators.)";
    }

    @Override
    public PickaxeEnchants getEnchant() {
        return PickaxeEnchants.FRACTURE;
    }

    @Override
    public Rarity getRarity() {
        return Rarity.ULTIMATE;
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }
}
