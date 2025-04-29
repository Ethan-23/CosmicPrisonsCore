package org.evasive.me.cosmicPrisonsCore.rarity;

public enum Rarity {
    SIMPLE(new Simple()),
    UNCOMMON(new Uncommon()),
    ELITE(new Elite()),
    ULTIMATE(new Ultimate()),
    LEGENDARY(new Legendary()),
    GODLY(new Godly());

    private final RarityBuilder rarityBuilder;

    // Constructor for BlockType enum
    Rarity(RarityBuilder rarityBuilder) {
        this.rarityBuilder = rarityBuilder;
    }

    public RarityBuilder getRarityBuilder() {
        return rarityBuilder;
    }

}

