package org.evasive.me.cosmicPrisonsCore.enchanting.mining;

import org.evasive.me.cosmicPrisonsCore.enchanting.WormholePickaxeEnchantBuilder;
import org.evasive.me.cosmicPrisonsCore.enchanting.WormholeSatchelEnchantBuilder;
import org.evasive.me.cosmicPrisonsCore.enchanting.mining.satchel.godly.Snatch;
import org.evasive.me.cosmicPrisonsCore.enchanting.mining.satchel.legendary.DoubleDrop;
import org.evasive.me.cosmicPrisonsCore.enchanting.mining.satchel.ultimate.EnergyMirror;
import org.evasive.me.cosmicPrisonsCore.enchanting.mining.satchel.uncommon.AutoSell;

public enum SatchelEnchants {
    AUTO_SELL(new AutoSell()),
    ENERGY_MIRROR(new EnergyMirror()),
    DOUBLE_DROP(new DoubleDrop()),
    SNATCH(new Snatch());

    public final WormholeSatchelEnchantBuilder wormholeSatchelEnchantBuilder;

    SatchelEnchants(WormholeSatchelEnchantBuilder wormholeSatchelEnchantBuilder){this.wormholeSatchelEnchantBuilder = wormholeSatchelEnchantBuilder;}

    public WormholeSatchelEnchantBuilder wormholeSatchelEnchantBuilder() {return wormholeSatchelEnchantBuilder;}
}
