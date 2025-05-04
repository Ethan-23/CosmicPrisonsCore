package org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.pickaxe.functions;

import org.bukkit.inventory.ItemStack;
import org.evasive.me.cosmicPrisonsCore.utils.EnchantUtil;
import org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.PickaxeEnchantFunctions;
import org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.pickaxe.PickaxeEnchants;

public class OreMagnetFunctions {

    public boolean procOreMagnet(ItemStack pickaxe) {
        int level = new PickaxeEnchantFunctions().getEnchantLevel(pickaxe.getItemMeta(), PickaxeEnchants.ORE_MAGNET);
        return (EnchantUtil.calculateChance(0.05f, level));
    }

}
