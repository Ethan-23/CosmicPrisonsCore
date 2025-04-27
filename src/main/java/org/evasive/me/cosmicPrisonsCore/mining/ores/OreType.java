package org.evasive.me.cosmicPrisonsCore.mining.ores;

public enum OreType {
    COAL_ORE(new CoalOre()),
    COAL_BLOCK(new CoalBlock()),
    IRON_ORE(new IronOre()),
    IRON_BLOCK(new IronBlock()),
    LAPIS_ORE(new LapisOre()),
    LAPIS_BLOCK(new LapisBlock()),
    REDSTONE_ORE(new RedstoneOre()),
    REDSTONE_BLOCK(new RedstoneBlock()),
    GOLD_ORE(new GoldOre()),
    GOLD_BLOCK(new GoldBlock()),
    DIAMOND_ORE(new DiamondOre()),
    DIAMOND_BLOCK(new DiamondBlock()),
    EMERALD_ORE(new EmeraldOre()),
    EMERALD_BLOCK(new EmeraldBlock());
    private OreCreator oreCreator;

    OreType(OreCreator oreCreator){
        this.oreCreator = oreCreator;
    }

    public OreCreator getOreCreator(){
        return oreCreator;
    }

}
