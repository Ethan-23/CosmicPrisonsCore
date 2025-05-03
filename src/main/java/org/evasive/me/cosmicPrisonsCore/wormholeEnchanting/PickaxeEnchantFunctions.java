package org.evasive.me.cosmicPrisonsCore.wormholeEnchanting;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.evasive.me.cosmicPrisonsCore.customItems.ItemList;
import org.evasive.me.cosmicPrisonsCore.customItems.pickaxes.PickaxeBuilder;
import org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.pickaxe.functions.FeedFunctions;
import org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.pickaxe.functions.SuperBreakerFunctions;
import org.evasive.me.cosmicPrisonsCore.utils.EnchantUtil;
import org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.pickaxe.PickaxeEnchants;

import java.util.HashMap;
import java.util.Map;

import static org.evasive.me.cosmicPrisonsCore.keys.ItemKeyFunctions.*;

public class PickaxeEnchantFunctions {



    public int getEnchantLevel(ItemMeta meta, PickaxeEnchants pickaxeEnchants){
        Map<PickaxeEnchants, Integer> enchantMap = new EnchantUtil().stringToEnumMap(getEnchantMap(meta), PickaxeEnchants.class);
        if(!enchantMap.containsKey(pickaxeEnchants))
            return 0;
        return enchantMap.get(pickaxeEnchants);
    }

    public void handleBreakingEnchants(Player player, ItemMeta meta) {

        new FeedFunctions().checkFeedChance(player, meta);
        new SuperBreakerFunctions().checkForSuperBreaker(player, meta);

    }

    public ItemStack addEnchant(ItemStack itemStack, PickaxeEnchants pickaxeEnchants, int level){
        ItemMeta meta = itemStack.getItemMeta();
        String tempStringMap = getEnchantMap(meta);
        Map<PickaxeEnchants, Integer> enchantMap;
        if(tempStringMap.isEmpty()){
            enchantMap = new HashMap<>();
        }else{
            enchantMap = new EnchantUtil().stringToEnumMap(tempStringMap, PickaxeEnchants.class);
        }

        enchantMap.put(pickaxeEnchants, level);
        String stringEnchantMap = new EnchantUtil().EnchantMapToString(enchantMap);
        setEnchantMap(meta, stringEnchantMap);
        setLevel(meta, getLevel(meta) + level);
        PickaxeBuilder pickaxeBuilder = (PickaxeBuilder) ItemList.valueOf(getID(meta)).getItemBuilder();
        return pickaxeBuilder.buildItem(meta);
    }
}
