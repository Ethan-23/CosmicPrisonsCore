package org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.pickaxe.functions;

import org.bukkit.inventory.ItemStack;
import org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.PickaxeEnchantFunctions;
import org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.pickaxe.PickaxeEnchants;

import java.util.Random;

public class EnergyCollectorFunctions {

    PickaxeEnchantFunctions pickaxeEnchantFunctions = new PickaxeEnchantFunctions();

    public float getEnergyCollectorMulti(ItemStack pickaxe) {
        int level = pickaxeEnchantFunctions.getEnchantLevel(pickaxe.getItemMeta(), PickaxeEnchants.ENERGY_COLLECTOR);

        Random random = new Random();
        float doubleChance = random.nextFloat();
        if(level == 5 && doubleChance < 0.10f)
            return 2;
        return (1 + (0.1f * level));
    }

}
