package org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.satchel.enchants.uncommon;

import org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.satchel.WormholeSatchelEnchantBuilder;
import org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.satchel.SatchelEnchants;
import org.evasive.me.cosmicPrisonsCore.rarity.Rarity;

public class AutoSell implements WormholeSatchelEnchantBuilder {
    @Override
    public String getName() {
        return "Auto Sell";
    }

    @Override
    public String getDescription() {
        return "Chance to auto-sell ores that would have gone into your satchel";
    }

    @Override
    public SatchelEnchants getEnchant() {
        return SatchelEnchants.AUTO_SELL;
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
