package org.evasive.me.cosmicPrisonsCore.keys;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.evasive.me.cosmicPrisonsCore.customItems.ItemList;
import org.evasive.me.cosmicPrisonsCore.customItems.pickaxes.PickaxeBuilder;


import java.util.Set;

import static org.evasive.me.cosmicPrisonsCore.keys.ItemKeys.*;

public class ItemKeyFunctions {

    Set<String> pickaxeKeys = Set.of("WOODEN_PICKAXE", "STONE_PICKAXE", "GOLDEN_PICKAXE", "IRON_PICKAXE", "DIAMOND_PICKAXE");

    public boolean hasKey(ItemMeta itemMeta, NamespacedKey namespacedKey){
        return itemMeta.getPersistentDataContainer().has(namespacedKey);
    }

    public boolean hasKey(ItemStack itemStack, NamespacedKey namespacedKey){
        return itemStack.getItemMeta().getPersistentDataContainer().has(namespacedKey);
    }

    public String getID(ItemMeta itemMeta){
        return itemMeta.getPersistentDataContainer().get(itemIDKey, PersistentDataType.STRING);
    }

    public int getLevel(ItemMeta itemMeta){
        return itemMeta.getPersistentDataContainer().get(levelKey, PersistentDataType.INTEGER);
    }

    public int getPrestige(ItemMeta itemMeta){
        return itemMeta.getPersistentDataContainer().get(prestigeKey, PersistentDataType.INTEGER);
    }

    public int getEnergy(ItemMeta itemMeta){
        return itemMeta.getPersistentDataContainer().get(energyKey, PersistentDataType.INTEGER);
    }

    public int getBaseEnergyCap(ItemMeta itemMeta){
        return itemMeta.getPersistentDataContainer().get(energyCapKey, PersistentDataType.INTEGER);
    }

    public int getChargeOrbSlots(ItemMeta itemMeta){
        return itemMeta.getPersistentDataContainer().get(chargedOrbSlots, PersistentDataType.INTEGER);
    }

    public int getChargeOrbMaxSlots(ItemMeta itemMeta){
        return itemMeta.getPersistentDataContainer().get(maxChargedOrbSlots, PersistentDataType.INTEGER);
    }

    public boolean isWhitescrolled(ItemMeta itemMeta){
        return itemMeta.getPersistentDataContainer().get(whiteScrolled, PersistentDataType.BOOLEAN);
    }

    public int getEnergyLevelIncrease(ItemMeta itemMeta){
        PickaxeBuilder pickaxeBuilder = (PickaxeBuilder) ItemList.valueOf(getID(itemMeta)).getItemBuilder();
        return pickaxeBuilder.getEnergyLevelIncrease();
    }

    public int getEnergyCap(ItemMeta itemMeta){
        return getBaseEnergyCap(itemMeta) + (getEnergyLevelIncrease(itemMeta) * (getLevel(itemMeta) - 1));
    }

    public boolean hasPickaxe(ItemMeta itemMeta) {
        if(!hasKey(itemMeta, itemIDKey))
            return false;
        return pickaxeKeys.contains(getID(itemMeta));
    }

    public boolean isEnergyFull(ItemMeta itemMeta) {
        return getEnergy(itemMeta) >= getEnergyCap(itemMeta);
    }

    public void removeEnergy(ItemMeta itemMeta, int amount){
        itemMeta.getPersistentDataContainer().set(energyKey, PersistentDataType.INTEGER, getEnergy(itemMeta) - amount);
    }

    public void addEnergy(ItemMeta itemMeta, int amount){
        itemMeta.getPersistentDataContainer().set(energyKey, PersistentDataType.INTEGER, getEnergy(itemMeta) + amount);
    }

    public void setEnergy(ItemMeta itemMeta, int amount){
        itemMeta.getPersistentDataContainer().set(energyKey, PersistentDataType.INTEGER, amount);
    }

    public int getLevelRequirement(ItemMeta itemMeta){
        PickaxeBuilder pickaxeBuilder = (PickaxeBuilder) ItemList.valueOf(getID(itemMeta)).getItemBuilder();
        return pickaxeBuilder.getRequiredMiningLevel();
    }
}
