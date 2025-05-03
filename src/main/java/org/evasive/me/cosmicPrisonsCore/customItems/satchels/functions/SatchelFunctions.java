package org.evasive.me.cosmicPrisonsCore.customItems.satchels.functions;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.evasive.me.cosmicPrisonsCore.customItems.ItemBuilder;
import org.evasive.me.cosmicPrisonsCore.customItems.ItemList;
import org.evasive.me.cosmicPrisonsCore.mining.ores.OreCreator;

import static org.evasive.me.cosmicPrisonsCore.keys.ItemKeyFunctions.*;
import static org.evasive.me.cosmicPrisonsCore.keys.ItemKeyFunctions.getID;
import static org.evasive.me.cosmicPrisonsCore.keys.ItemKeys.itemIDKey;

public class SatchelFunctions {

    public int addBlocks(Player player, ItemStack itemStack, ItemMeta itemMeta, int amount, int slot){
        int add = Math.min(getOreCap(itemMeta) - getOreStorage(itemMeta), amount);
        addOreStorage(itemMeta, add);
        rebuildItem(player, itemStack, itemMeta, slot);
        return amount - add;
    }

    public void clearBlocks(){

    }

    public boolean isFull(ItemMeta meta){
        return getOreCap(meta) <= getOreStorage(meta);
    }

    public void levelUp(){

    }

    public boolean isSatchel(ItemStack itemStack, OreCreator oreCreator){
        if(itemStack == null || itemStack.getType() == Material.AIR)
            return false;
        String satchelName = String.format("%s%s",oreCreator.getItemDrop().name().replace("_INGOT", ""), "_SATCHEL");
        return itemStack.hasItemMeta() && hasKey(itemStack.getItemMeta(), itemIDKey) && (satchelName).equals(getID(itemStack.getItemMeta()));
    }

    public void rebuildItem(Player player, ItemStack itemStack, ItemMeta itemMeta, int slot){
        ItemBuilder builder = ItemList.valueOf(getID(itemMeta)).getItemBuilder();
        itemStack.setItemMeta(builder.rebuild(itemMeta, null).getItemMeta());
        player.getInventory().setItem(slot, itemStack);
    }

    public int addItemsToSatchel(Player player, Inventory inventory, OreCreator oreCreator, int amount){

        for (int slot = 0; slot < inventory.getStorageContents().length; slot++) {
            if (amount == 0)
                break;
            ItemStack item = inventory.getStorageContents()[slot];
            if (!isSatchel(item, oreCreator))
                continue;
            ItemMeta meta = item.getItemMeta();
            if (isFull(meta))
                continue;
            amount = addBlocks(player, item, meta, amount, slot);
        }
        return amount;
    }

}
