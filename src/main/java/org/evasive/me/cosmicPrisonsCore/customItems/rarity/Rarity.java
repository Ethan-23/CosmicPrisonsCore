package org.evasive.me.cosmicPrisonsCore.customItems.rarity;

public enum Rarity {
    SIMPLE(new Simple());
//    UNCOMMON,
//    ELITE,
//    ULTIMATE,
//    LEGENDARY,
//    GODLY

    private final RarityBuilder rarityBuilder;

    // Constructor for BlockType enum
    Rarity(RarityBuilder rarityBuilder) {
        this.rarityBuilder = rarityBuilder;
    }

    public RarityBuilder getRarityBuilder() {
        return rarityBuilder;
    }

}

