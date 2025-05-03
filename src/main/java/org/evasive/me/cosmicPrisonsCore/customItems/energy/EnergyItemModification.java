package org.evasive.me.cosmicPrisonsCore.customItems.energy;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.evasive.me.cosmicPrisonsCore.customItems.ItemBuilder;
import org.evasive.me.cosmicPrisonsCore.customItems.ItemList;
import org.evasive.me.cosmicPrisonsCore.keys.ItemKeyFunctions;

import static org.evasive.me.cosmicPrisonsCore.keys.ItemKeyFunctions.*;

public class EnergyItemModification {

    public void addEnergy(Player player, ItemMeta itemMeta, int energyGain){
        energyGain = Math.min(energyGain, getMissingEnergy(itemMeta));
        ItemKeyFunctions.addEnergy(itemMeta, energyGain);
        rebuildItem(player, player.getInventory().getItemInMainHand(), itemMeta);
    }

    public void removeEnergy(Player player, ItemMeta itemMeta, int energyAmount){
        energyAmount = Math.min(getEnergy(itemMeta), energyAmount);
        ItemKeyFunctions.removeEnergy(itemMeta, energyAmount);
        rebuildItem(player, player.getInventory().getItemInMainHand(), itemMeta);
    }

    public void rebuildItem(Player player, ItemStack itemStack, ItemMeta itemMeta){
        ItemBuilder builder = ItemList.valueOf(getID(itemMeta)).getItemBuilder();
        itemStack.setItemMeta(builder.rebuild(itemMeta, null).getItemMeta());
        player.getInventory().setItemInMainHand(itemStack);
    }

    public int getCurrentEnergy(ItemMeta itemMeta){
        return getEnergy(itemMeta);
    }

    public int getMissingEnergy(ItemMeta itemMeta){
        return getEnergyCap(itemMeta) - getCurrentEnergy(itemMeta);
    }

}
