package org.evasive.me.cosmicPrisonsCore.enchanting;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.evasive.me.cosmicPrisonsCore.customItems.ItemList;
import org.evasive.me.cosmicPrisonsCore.customItems.pickaxes.PickaxeBuilder;
import org.evasive.me.cosmicPrisonsCore.enchanting.mining.PickaxeEnchants;
import org.evasive.me.cosmicPrisonsCore.utils.EnchantUtil;

import java.util.HashMap;
import java.util.Map;

import static org.evasive.me.cosmicPrisonsCore.keys.ItemKeyFunctions.*;
import static org.evasive.me.cosmicPrisonsCore.keys.ItemKeys.enchantMap;

public class ItemEnchantFunctions {

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
