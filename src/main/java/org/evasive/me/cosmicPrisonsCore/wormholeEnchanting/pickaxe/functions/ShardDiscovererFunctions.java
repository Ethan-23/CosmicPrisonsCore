package org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.pickaxe.functions;

import org.bukkit.inventory.ItemStack;
import org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.PickaxeEnchantFunctions;
import org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.pickaxe.PickaxeEnchants;

public class ShardDiscovererFunctions {

    PickaxeEnchantFunctions pickaxeEnchantFunctions = new PickaxeEnchantFunctions();

    public int checkForShardDiscoverer(ItemStack pickaxe){
        return pickaxeEnchantFunctions.getEnchantLevel(pickaxe.getItemMeta(), PickaxeEnchants.SHARD_DISCOVERER);
    }

}
