package org.evasive.me.cosmicPrisonsCore.customItems.energy;

import net.kyori.adventure.text.Component;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.evasive.me.cosmicPrisonsCore.customItems.ItemBuilder;
import org.evasive.me.cosmicPrisonsCore.utils.ComponentUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.evasive.me.cosmicPrisonsCore.keys.ItemKeys.*;

public interface CosmicEnergyBuilder extends ItemBuilder {

    default Component getName(int amount){
        return ComponentUtils.legacy("&f&l" + String.format("%,d", amount) + " &b&lCosmic Energy");
    }

    default List<Component> getLore(int amount, String name){
        List<Component> lore = new ArrayList<>();
        lore.add(ComponentUtils.legacy(""));
        lore.add(ComponentUtils.legacy("&6Contains " + "&f&l" + String.format("%,d", amount) + " &b&lCosmic Energy"));
        lore.add(ComponentUtils.legacy(" &6that is used for enchanting"));
        lore.add(ComponentUtils.legacy(""));
        lore.add(ComponentUtils.legacy("&7Hint: Drag and drop onto a pickaxe"));
        lore.add(ComponentUtils.legacy(" &7sword, or piece of armor to add"));
        lore.add(ComponentUtils.legacy(" &7to its energy!"));
        lore.add(ComponentUtils.legacy(""));
        lore.add(ComponentUtils.legacy("&6Extracted by " + "&f" + name));
        return lore;
    }

    default ItemStack buildItem(int amount, String name){
        ItemStack item = ItemBuilder.super.buildItem();
        ItemMeta meta = item.getItemMeta();
        meta.displayName(getName(amount));
        meta.lore(getLore(amount, name));
        meta.getPersistentDataContainer().set(itemIDKey, PersistentDataType.STRING, getID());
        meta.getPersistentDataContainer().set(energyKey, PersistentDataType.INTEGER, amount);
        meta.getPersistentDataContainer().set(uniqueKey, PersistentDataType.STRING, UUID.randomUUID().toString());
        item.setItemMeta(meta);
        return item;
    }

    default ItemStack rebuild(ItemMeta meta, String name) {
        int amount = meta.getPersistentDataContainer().getOrDefault(energyKey, PersistentDataType.INTEGER, 0);

        return buildItem(amount, name);
    }
}
