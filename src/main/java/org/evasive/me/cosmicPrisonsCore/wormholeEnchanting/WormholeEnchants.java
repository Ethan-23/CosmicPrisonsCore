package org.evasive.me.cosmicPrisonsCore.wormholeEnchanting;

import org.evasive.me.cosmicPrisonsCore.rarity.Rarity;

public interface WormholeEnchants {
    String getName();
    String getDescription();
    Rarity getRarity();
    int getMaxLevel();
    default int getWormholeMaxLevel(){
        return getMaxLevel();
    }
}
