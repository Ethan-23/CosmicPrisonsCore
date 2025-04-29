package org.evasive.me.cosmicPrisonsCore.enchanting.mining.pickaxe.godly;

import org.evasive.me.cosmicPrisonsCore.enchanting.WormholePickaxeEnchantBuilder;
import org.evasive.me.cosmicPrisonsCore.enchanting.mining.PickaxeEnchants;
import org.evasive.me.cosmicPrisonsCore.rarity.Rarity;

public class WarpMiner implements WormholePickaxeEnchantBuilder {

    @Override
    public String getName() {
        return "Warp Miner";
    }

    @Override
    public String getDescription() {
        return "Increases xp gain in the overworld and the Executive Mine";
    }

    @Override
    public PickaxeEnchants getEnchant() {
        return PickaxeEnchants.WARP_MINER;
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
