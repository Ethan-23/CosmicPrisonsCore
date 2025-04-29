package org.evasive.me.cosmicPrisonsCore.keys;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.evasive.me.cosmicPrisonsCore.customItems.ItemBuilder;
import org.evasive.me.cosmicPrisonsCore.customItems.ItemList;
import org.evasive.me.cosmicPrisonsCore.customItems.energy.EnergyInterface;
import org.evasive.me.cosmicPrisonsCore.customItems.pickaxes.PickaxeBuilder;


import java.util.Set;

import static org.evasive.me.cosmicPrisonsCore.keys.ItemKeys.*;

public class ItemKeyFunctions {

    static Set<String> pickaxeKeys = Set.of("WOODEN_PICKAXE", "STONE_PICKAXE", "GOLDEN_PICKAXE", "IRON_PICKAXE", "DIAMOND_PICKAXE");

    public static boolean hasKey(ItemMeta itemMeta, NamespacedKey namespacedKey){
        return itemMeta.getPersistentDataContainer().has(namespacedKey);
    }

    public static boolean hasKey(ItemStack itemStack, NamespacedKey namespacedKey){
        return itemStack.getItemMeta().getPersistentDataContainer().has(namespacedKey);
    }

    public static String getID(ItemMeta itemMeta){
        return itemMeta.getPersistentDataContainer().get(itemIDKey, PersistentDataType.STRING);
    }

    public static int getLevel(ItemMeta itemMeta){
        return itemMeta.getPersistentDataContainer().get(levelKey, PersistentDataType.INTEGER);
    }

    public static void setLevel(ItemMeta itemMeta, int amount){
        itemMeta.getPersistentDataContainer().set(levelKey, PersistentDataType.INTEGER, amount);
    }

    public static int getPrestige(ItemMeta itemMeta){
        return itemMeta.getPersistentDataContainer().get(prestigeKey, PersistentDataType.INTEGER);
    }

    public static int getEnergy(ItemMeta itemMeta){
        return itemMeta.getPersistentDataContainer().get(energyKey, PersistentDataType.INTEGER);
    }

    public static int getBaseEnergyCap(ItemMeta itemMeta){
        return itemMeta.getPersistentDataContainer().get(energyCapKey, PersistentDataType.INTEGER);
    }

    public static int getChargeOrbSlots(ItemMeta itemMeta){
        return itemMeta.getPersistentDataContainer().get(chargedOrbSlots, PersistentDataType.INTEGER);
    }

    public static int getChargeOrbMaxSlots(ItemMeta itemMeta){
        return itemMeta.getPersistentDataContainer().get(maxChargedOrbSlots, PersistentDataType.INTEGER);
    }

    public static boolean isWhitescrolled(ItemMeta itemMeta){
        return itemMeta.getPersistentDataContainer().get(whiteScrolled, PersistentDataType.BOOLEAN);
    }

    public static int getEnergyLevelIncrease(ItemMeta itemMeta){
        EnergyInterface pickaxeBuilder = (EnergyInterface) ItemList.valueOf(getID(itemMeta)).getItemBuilder();
        return pickaxeBuilder.getEnergyLevelIncrease();
    }

    public static int getEnergyCap(ItemMeta itemMeta){
        return getBaseEnergyCap(itemMeta) + (getEnergyLevelIncrease(itemMeta) * (getLevel(itemMeta) - 1));
    }

    public static boolean hasPickaxe(ItemMeta itemMeta) {
        if(!hasKey(itemMeta, itemIDKey))
            return false;
        return pickaxeKeys.contains(getID(itemMeta));
    }

    public static boolean isEnergyFull(ItemMeta itemMeta) {
        return getEnergy(itemMeta) >= getEnergyCap(itemMeta);
    }

    public static void removeEnergy(ItemMeta itemMeta, int amount){
        itemMeta.getPersistentDataContainer().set(energyKey, PersistentDataType.INTEGER, getEnergy(itemMeta) - amount);
    }

    public static void addEnergy(ItemMeta itemMeta, int amount){
        itemMeta.getPersistentDataContainer().set(energyKey, PersistentDataType.INTEGER, getEnergy(itemMeta) + amount);
    }

    public static void setEnergy(ItemMeta itemMeta, int amount){
        itemMeta.getPersistentDataContainer().set(energyKey, PersistentDataType.INTEGER, amount);
    }

    public static int getLevelRequirement(ItemMeta itemMeta){
        PickaxeBuilder pickaxeBuilder = (PickaxeBuilder) ItemList.valueOf(getID(itemMeta)).getItemBuilder();
        return pickaxeBuilder.getRequiredMiningLevel();
    }


    public static int getOreStorage(ItemMeta meta) {
        return meta.getPersistentDataContainer().get(oreAmountKey, PersistentDataType.INTEGER);
    }


    public static int getOreCap(ItemMeta meta) {
        return meta.getPersistentDataContainer().get(oreCapKey, PersistentDataType.INTEGER);
    }

    public static void addOreStorage(ItemMeta meta, int amount){
        meta.getPersistentDataContainer().set(oreAmountKey, PersistentDataType.INTEGER, getOreStorage(meta) + amount);
    }

    public static String getEnchantMap(ItemMeta meta){
        return meta.getPersistentDataContainer().get(enchantMap, PersistentDataType.STRING);
    }

    public static void setEnchantMap(ItemMeta meta, String string){
        meta.getPersistentDataContainer().set(enchantMap, PersistentDataType.STRING, string);
    }
}
