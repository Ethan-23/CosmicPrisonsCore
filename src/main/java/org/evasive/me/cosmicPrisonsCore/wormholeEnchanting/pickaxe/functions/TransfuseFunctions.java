package org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.pickaxe.functions;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.evasive.me.cosmicPrisonsCore.utils.EnchantUtil;
import org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.PickaxeEnchantFunctions;
import org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.pickaxe.PickaxeEnchants;
import org.evasive.me.cosmicPrisonsCore.mining.ores.OreCreator;
import org.evasive.me.cosmicPrisonsCore.mining.ores.OreType;

public class TransfuseFunctions {

    PickaxeEnchantFunctions pickaxeEnchantFunctions = new PickaxeEnchantFunctions();

    public boolean handleTransfuse(Player player, ItemStack itemStack, OreCreator oreCreator) {
        int level = pickaxeEnchantFunctions.getEnchantLevel(itemStack.getItemMeta(), PickaxeEnchants.TRANSFUSE);
        if(level == 0)
            return false;
        OreType oreType = OreType.valueOf(oreCreator.getMaterial().name());
        if (!EnchantUtil.calculateChance(0.02f, level) || oreType.ordinal() > 11)
            return false;
        player.playSound(player.getLocation(), Sound.BLOCK_BREWING_STAND_BREW, 1f, 1f);
        return true;
    }

    public OreCreator transfuseOres(OreCreator oreCreator){
        return OreType.values()[OreType.valueOf(oreCreator.getMaterial().name()).ordinal() + 2].getOreCreator();
    }
}
