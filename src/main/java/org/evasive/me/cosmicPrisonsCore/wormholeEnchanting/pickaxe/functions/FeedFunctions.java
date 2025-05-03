package org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.pickaxe.functions;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.evasive.me.cosmicPrisonsCore.utils.EnchantUtil;
import org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.PickaxeEnchantFunctions;
import org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.pickaxe.PickaxeEnchants;

public class FeedFunctions {

    PickaxeEnchantFunctions enchantFunctions = new PickaxeEnchantFunctions();

    public void checkFeedChance(Player player, ItemMeta meta){
        int level = enchantFunctions.getEnchantLevel(meta, PickaxeEnchants.FEED);
        if(level > 0 && EnchantUtil.calculateChance(0.05f) && player.getFoodLevel() < 20){
            player.playSound(player.getLocation(), Sound.ENTITY_GENERIC_EAT, 1f, 1f);
            player.spawnParticle(Particle.ITEM, player.getLocation().add(0f, 1.5f, 0f), 10, 0.2, 0.2, 0.2, 0.05, new ItemStack(Material.COOKED_BEEF));
            player.setFoodLevel(20);
            player.setSaturation(level * 15);
        }
    }

}
