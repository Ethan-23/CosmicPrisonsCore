package org.evasive.me.cosmicPrisonsCore.enchanting.mining.satchel.legendary;

import org.evasive.me.cosmicPrisonsCore.enchanting.WormholeSatchelEnchantBuilder;
import org.evasive.me.cosmicPrisonsCore.enchanting.mining.SatchelEnchants;
import org.evasive.me.cosmicPrisonsCore.rarity.Rarity;

public class DoubleDrop implements WormholeSatchelEnchantBuilder {
    @Override
    public SatchelEnchants getEnchant() {
        return SatchelEnchants.DOUBLE_DROP;
    }

    @Override
    public String getName() {
        return "Double Drop";
    }

    @Override
    public String getDescription() {
        return "Chance to receive double drops in your satchel.";
    }

    @Override
    public Rarity getRarity() {
        return Rarity.LEGENDARY;
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }
}
