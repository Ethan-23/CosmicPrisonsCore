package org.evasive.me.cosmicPrisonsCore.enchanting;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.evasive.me.cosmicPrisonsCore.enchanting.mining.PickaxeEnchants;
import org.evasive.me.cosmicPrisonsCore.utils.EnchantUtil;

import java.util.Map;
import java.util.Random;

import static org.evasive.me.cosmicPrisonsCore.keys.ItemKeyFunctions.getEnchantMap;

public class EnchantFunctions {

    private final Random RANDOM = new Random();

    public void handleBreakingEnchants(Player player, ItemMeta meta) {

        if(hasEnchant(meta, PickaxeEnchants.FEED) && RANDOM.nextFloat() < 0.05 && player.getFoodLevel() < 20){

            player.playSound(player.getLocation(), Sound.ENTITY_GENERIC_EAT, 1f, 1f);
            player.spawnParticle(Particle.ITEM, player.getLocation().add(0f, 1.5f, 0f), 10, 0.2, 0.2, 0.2, 0.05, new ItemStack(Material.COOKED_BEEF));
            player.setFoodLevel(20);
            player.setSaturation(getEnchantLevel(meta, PickaxeEnchants.FEED) * 15);
        }

    }

    public boolean hasEnchant(ItemMeta meta, PickaxeEnchants pickaxeEnchants){
        Map<PickaxeEnchants, Integer> enchantMap = new EnchantUtil().stringToEnumMap(getEnchantMap(meta), PickaxeEnchants.class);
        return enchantMap.containsKey(pickaxeEnchants);
    }

    public int getEnchantLevel(ItemMeta meta, PickaxeEnchants pickaxeEnchants){
        Map<PickaxeEnchants, Integer> enchantMap = new EnchantUtil().stringToEnumMap(getEnchantMap(meta), PickaxeEnchants.class);
        return enchantMap.get(pickaxeEnchants);
    }

    public boolean checkFractureChance(ItemMeta meta) {
        if(!hasEnchant(meta, PickaxeEnchants.FRACTURE))
            return false;
        int level = getEnchantLevel(meta, PickaxeEnchants.FRACTURE);
        int number = RANDOM.nextInt(15) + 1;
        return level >= number;
    }

    public boolean checkTransfuseChance(ItemMeta meta) {
        if(!hasEnchant(meta, PickaxeEnchants.TRANSFUSE))
            return false;
        int level = getEnchantLevel(meta, PickaxeEnchants.TRANSFUSE);
        return RANDOM.nextFloat() < 0.033f * level;
    }
}
