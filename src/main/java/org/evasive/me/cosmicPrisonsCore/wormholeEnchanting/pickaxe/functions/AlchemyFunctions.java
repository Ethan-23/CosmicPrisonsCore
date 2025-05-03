package org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.pickaxe.functions;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.PickaxeEnchantFunctions;
import org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.pickaxe.PickaxeEnchants;
import org.evasive.me.cosmicPrisonsCore.mining.ores.OreCreator;

import java.util.Random;

public class AlchemyFunctions {

    PickaxeEnchantFunctions enchantFunctions = new PickaxeEnchantFunctions();
    private final Random RANDOM = new Random();

    public boolean checkForAlchemy(ItemStack pickaxe, OreCreator ore, int amount, Player player) {
        if(enchantFunctions.getEnchantLevel(pickaxe.getItemMeta(), PickaxeEnchants.ALCHEMY) == 0)
            return false;
        if(!checkAlchemyChance(pickaxe.getItemMeta()))
            return false;
        activateAlchemy(player);
        return true;
    }

    public boolean checkAlchemyChance(ItemMeta meta) {
        int level = enchantFunctions.getEnchantLevel(meta, PickaxeEnchants.TRANSFUSE);
        return RANDOM.nextFloat() < 0.033f * level;
    }

    public void activateAlchemy(Player player){
        player.playSound(player.getLocation(), Sound.BLOCK_PISTON_CONTRACT, 1f, 1f);
        //Check ore and give money note
        //If already has money note combine
        //ALCHEMY 3 CHANCE TO DOUBLE SELL
    }

}
