package org.evasive.me.cosmicPrisonsCore.customItems;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.evasive.me.cosmicPrisonsCore.utils.ComponentUtils;

import java.util.List;

import static org.evasive.me.cosmicPrisonsCore.keys.ItemKeys.itemIDKey;

public interface ItemBuilder {
    Component getName();
    default Component getName(int level){
        if(level == 1)
            return getName();
        return getName().append(ComponentUtils.legacy("&a&l" + level));
    }
    String getID();

    default void setItemID(ItemMeta meta){
        meta.getPersistentDataContainer().set(itemIDKey, PersistentDataType.STRING, getID());
    }

    default List<Component> getLore() {
        return List.of();
    }

    Material getMaterial();
    boolean isGlowing();
    ItemStack getItem();

    default ItemStack buildItem(){
        ItemStack item = new ItemStack(getMaterial());
        ItemMeta meta = item.getItemMeta();
        meta.displayName(getName());
        setItemID(meta);
        meta.lore(getLore());
        if(isGlowing()){
            meta.addEnchant(Enchantment.LOYALTY, 1, true);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }else{
            meta.removeEnchant(Enchantment.LOYALTY);
        }
        item.setItemMeta(meta);
        return item;
    }

    default ItemStack rebuild(ItemMeta meta, String name) {
        return buildItem(); // Default fallback, override if needed
    }
}
