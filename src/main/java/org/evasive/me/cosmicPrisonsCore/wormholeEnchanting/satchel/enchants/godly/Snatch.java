package org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.satchel.enchants.godly;

import org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.satchel.WormholeSatchelEnchantBuilder;
import org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.satchel.SatchelEnchants;
import org.evasive.me.cosmicPrisonsCore.rarity.Rarity;

public class Snatch implements WormholeSatchelEnchantBuilder{
    @Override
    public SatchelEnchants getEnchant() {
        return SatchelEnchants.SNATCH;
    }

    @Override
    public String getName() {
        return "Snatch";
    }

    @Override
    public String getDescription() {
        return "Chance to gain ores on your satchel from surrounding players.";
    }

    @Override
    public Rarity getRarity() {
        return Rarity.GODLY;
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }
}
