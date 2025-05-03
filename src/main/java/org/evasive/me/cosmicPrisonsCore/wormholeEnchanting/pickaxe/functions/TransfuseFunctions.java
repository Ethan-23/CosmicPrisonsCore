package org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.pickaxe.functions;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.PickaxeEnchantFunctions;
import org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.pickaxe.PickaxeEnchants;
import org.evasive.me.cosmicPrisonsCore.mining.ores.OreCreator;
import org.evasive.me.cosmicPrisonsCore.mining.ores.OreType;

import java.util.Random;

public class TransfuseFunctions {

    PickaxeEnchantFunctions pickaxeEnchantFunctions = new PickaxeEnchantFunctions();
    private final Random RANDOM = new Random();

    public OreCreator checkForTransfuse(Player player, ItemStack pickaxe, Material material) {
        int level = pickaxeEnchantFunctions.getEnchantLevel(pickaxe.getItemMeta(), PickaxeEnchants.TRANSFUSE);
        if(level == 0)
            return OreType.valueOf(material.name()).getOreCreator();
        if (RANDOM.nextFloat() < 0.033f * level && OreType.valueOf(material.name()).ordinal() < 12) {
            player.playSound(player.getLocation(), Sound.BLOCK_BREWING_STAND_BREW, 1f, 1f);
            return OreType.values()[OreType.valueOf(material.name()).ordinal() + 2].getOreCreator();
        }
        return OreType.valueOf(material.name()).getOreCreator();
    }

}
