package org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.pickaxe.functions;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.evasive.me.cosmicPrisonsCore.utils.ComponentUtils;
import org.evasive.me.cosmicPrisonsCore.utils.EnchantUtil;
import org.evasive.me.cosmicPrisonsCore.utils.EnumNameConversions;
import org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.PickaxeEnchantFunctions;
import org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.pickaxe.PickaxeEnchants;
import org.evasive.me.cosmicPrisonsCore.mining.ores.OreCreator;

public class AlchemyFunctions {

    PickaxeEnchantFunctions enchantFunctions = new PickaxeEnchantFunctions();

    public boolean handleAlchemy(Player player, ItemStack pickaxe, OreCreator oreCreator, int blockAmount) {
        int level = enchantFunctions.getEnchantLevel(pickaxe.getItemMeta(), PickaxeEnchants.ALCHEMY);
        if(level == 0)
            return false;
        if(!EnchantUtil.calculateChance(0.02f, level))
            return false;
        activateAlchemy(player, level, oreCreator, blockAmount);
        return true;
    }


    public void activateAlchemy(Player player, int level, OreCreator oreCreator, int blockAmount){
        player.playSound(player.getLocation(), Sound.BLOCK_PISTON_CONTRACT, 1f, 1f);
        float amount = 0;
        boolean doubleSell = false;
        if(EnchantUtil.calculateChance(0.10f, 1)){
            amount *= 2;
            doubleSell = true;
        }
        alchemyAlert(player, oreCreator.getItemDrop(), blockAmount, amount, doubleSell);
        //Check ore and give money note
        //If already has money note combine
        //ALCHEMY 3 CHANCE TO DOUBLE SELL
    }

    public void alchemyAlert(Player player, Material material, int blockAmount, float amount,  boolean doubleSell){
        String oreName = new EnumNameConversions().getOreName(material);
        player.sendActionBar(ComponentUtils.legacy(String.format("Sold &a%d &f%s &ffor %s$%.2f", blockAmount, oreName, doubleSell? "&b":"&a", amount)));
    }
}
