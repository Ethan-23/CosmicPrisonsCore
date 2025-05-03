package org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.pickaxe;

import org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.pickaxe.enchants.elite.OreSurge;
import org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.pickaxe.enchants.elite.Powerball;
import org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.pickaxe.enchants.elite.Replenish;
import org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.pickaxe.enchants.elite.SuperBreaker;
import org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.pickaxe.enchants.godly.EnergyBattery;
import org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.pickaxe.enchants.godly.OreMiner;
import org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.pickaxe.enchants.godly.WarpMiner;
import org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.pickaxe.enchants.legendary.ComboRupture;
import org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.pickaxe.enchants.legendary.Lucky;
import org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.pickaxe.enchants.legendary.Momentum;
import org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.pickaxe.enchants.legendary.Shatter;
import org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.pickaxe.enchants.simple.*;
import org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.pickaxe.enchants.ultimate.Fortify;
import org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.pickaxe.enchants.ultimate.Fracture;
import org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.pickaxe.enchants.ultimate.Magnetism;
import org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.pickaxe.enchants.ultimate.MeteorHunter;
import org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.pickaxe.enchants.uncommon.Alchemy;
import org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.pickaxe.enchants.uncommon.EnergyCollector;
import org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.pickaxe.enchants.uncommon.Transfuse;

public enum PickaxeEnchants {
    WARP_MINER(new WarpMiner()),
    ENERGY_BATTERY(new EnergyBattery()),
    ORE_MINER(new OreMiner()),
    COMBO_RUPTURE(new ComboRupture()),
    LUCKY(new Lucky()),
    SHATTER(new Shatter()),
    MOMENTUM(new Momentum()),
    FORTIFY(new Fortify()),
    FRACTURE(new Fracture()),
    MAGNETISM(new Magnetism()),
    METEOR_HUNTER(new MeteorHunter()),
    ORE_SURGE(new OreSurge()),
    POWERBALL(new Powerball()),
    REPLENISH(new Replenish()),
    SUPER_BREAKER(new SuperBreaker()),
    ALCHEMY(new Alchemy()),
    ENERGY_COLLECTOR(new EnergyCollector()),
    TRANSFUSE(new Transfuse()),
    AQUA_AFFINITY(new AquaAffinity()),
    EFFICIENCY(new Efficiency()),
    FEED(new Feed()),
    ORE_MAGNET(new OreMagnet()),
    SHARD_DISCOVERER(new ShardDiscoverer()),
    SIXTH_SENSE(new SixthSense());

    public final WormholePickaxeEnchantBuilder wormholePickaxeEnchantBuilder;

    PickaxeEnchants(WormholePickaxeEnchantBuilder wormholePickaxeEnchantBuilder){this.wormholePickaxeEnchantBuilder = wormholePickaxeEnchantBuilder;}

    public WormholePickaxeEnchantBuilder wormholePickaxeEnchantBuilder() {return wormholePickaxeEnchantBuilder;}

}
