package org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.satchel;

import org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.satchel.enchants.godly.Snatch;
import org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.satchel.enchants.legendary.DoubleDrop;
import org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.satchel.enchants.ultimate.EnergyMirror;
import org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.satchel.enchants.uncommon.AutoSell;

public enum SatchelEnchants {
    AUTO_SELL(new AutoSell()),
    ENERGY_MIRROR(new EnergyMirror()),
    DOUBLE_DROP(new DoubleDrop()),
    SNATCH(new Snatch());

    public final WormholeSatchelEnchantBuilder wormholeSatchelEnchantBuilder;

    SatchelEnchants(WormholeSatchelEnchantBuilder wormholeSatchelEnchantBuilder){this.wormholeSatchelEnchantBuilder = wormholeSatchelEnchantBuilder;}

    public WormholeSatchelEnchantBuilder wormholeSatchelEnchantBuilder() {return wormholeSatchelEnchantBuilder;}
}
