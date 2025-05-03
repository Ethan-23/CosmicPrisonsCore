package org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.pickaxe.functions;

import org.bukkit.inventory.ItemStack;
import org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.PickaxeEnchantFunctions;
import org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.pickaxe.PickaxeEnchants;

public class EnergyCollectorFunctions {

    PickaxeEnchantFunctions pickaxeEnchantFunctions = new PickaxeEnchantFunctions();

    public int checkForEnergyCollector(int energyGain, ItemStack pickaxe) {
        int level = pickaxeEnchantFunctions.getEnchantLevel(pickaxe.getItemMeta(), PickaxeEnchants.ENERGY_COLLECTOR);
        return (int) (energyGain * (1 + (0.2f * level)));
    }

}
