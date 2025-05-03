package org.evasive.me.cosmicPrisonsCore.mining.process;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.evasive.me.cosmicPrisonsCore.CosmicPrisonsCore;
import org.evasive.me.cosmicPrisonsCore.mining.ores.OreCreator;
import org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.pickaxe.functions.EfficiencyFunctions;
import org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.pickaxe.functions.FractureFunctions;

import static org.evasive.me.cosmicPrisonsCore.customItems.ItemFunctions.getPickaxeBuilderFromId;
import static org.evasive.me.cosmicPrisonsCore.keys.ItemKeyFunctions.getID;
import static org.evasive.me.cosmicPrisonsCore.mining.ores.functions.OreFunctions.getOreCreatorFromMaterial;

public class MiningSpeedCalculations {

    public float calculateMiningSpeed(Player player, ItemStack itemStack, Material material){
        OreCreator oreCreator = getOreCreatorFromMaterial(material);
        float base = getBaseSpeed(itemStack);
        float efficiency = getEfficiencySpeed(itemStack);

        return base * efficiency * (CosmicPrisonsCore.superBreakerMap.hasSuperBreaker(player) ? 2:1) / oreCreator.getHardness();
    }

    public float getBaseSpeed(ItemStack itemStack) {
        return getPickaxeBuilderFromId(getID(itemStack.getItemMeta())).getBaseSpeed();
    }

    private float getEfficiencySpeed(ItemStack itemStack){
        return new EfficiencyFunctions().getEfficiencyCalculation(itemStack);
    }

    public float calculateFractureSpeed(ItemStack itemStack) {
        return new FractureFunctions().getFractureSpeed(itemStack);
    }
}
