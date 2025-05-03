package org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.pickaxe.functions;

import org.bukkit.inventory.ItemStack;
import org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.PickaxeEnchantFunctions;
import org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.pickaxe.PickaxeEnchants;

public class EfficiencyFunctions {

    PickaxeEnchantFunctions enchantFunctions = new PickaxeEnchantFunctions();

    public float getEfficiencyCalculation(ItemStack pickaxe) {
        return (1 + (0.20f * enchantFunctions.getEnchantLevel(pickaxe.getItemMeta(), PickaxeEnchants.EFFICIENCY)));
    }

}
