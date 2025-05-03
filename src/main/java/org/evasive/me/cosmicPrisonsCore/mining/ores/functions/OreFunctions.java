package org.evasive.me.cosmicPrisonsCore.mining.ores.functions;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.evasive.me.cosmicPrisonsCore.mining.ores.OreCreator;
import org.evasive.me.cosmicPrisonsCore.mining.ores.OreType;

public class OreFunctions {

    public static OreCreator getOreCreatorFromBlock(Block block){
        return OreType.valueOf(block.getType().name()).getOreCreator();
    }

    public static OreCreator getOreCreatorFromMaterial(Material material){
        return OreType.valueOf(material.name()).getOreCreator();
    }

}
