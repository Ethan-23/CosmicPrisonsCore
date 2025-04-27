package org.evasive.me.cosmicPrisonsCore.customItems.pickaxes;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.evasive.me.cosmicPrisonsCore.customItems.ItemBuilder;
import org.evasive.me.cosmicPrisonsCore.keys.ItemKeyFunctions;
import org.evasive.me.cosmicPrisonsCore.utils.ComponentUtils;

import java.util.ArrayList;
import java.util.List;

import static org.evasive.me.cosmicPrisonsCore.keys.ItemKeys.*;

public interface PickaxeBuilder extends ItemBuilder {

    ItemKeyFunctions pickaxeFunctions = new ItemKeyFunctions();

    int getBaseEnergyCap();
    float getBaseSpeed();
    int getRequiredMiningLevel();
    int getEnergyLevelIncrease();
    List<Material> getTrackingMaterialOne();
    List<Material> getTrackingMaterialTwo();

    default ItemStack buildItem(){
        return buildItem(null);
    }

    default List<Component> getLore(int level, ItemMeta itemMeta){
        int currentEnergy = pickaxeFunctions.getEnergy(itemMeta);
        List<Component> lore = new ArrayList<>();

        //ENCHANTS FIRST
        if(pickaxeFunctions.hasKey(itemMeta, enchantMap)){
            lore.add(Component.text(""));
            lore.add(Component.text("Enchants YAY"));
        }

        //WHITESCROLL
        if(pickaxeFunctions.hasKey(itemMeta, whiteScrolled) && pickaxeFunctions.isWhitescrolled(itemMeta)){
            lore.add(Component.text(""));
            //lore.add(ComponentUtils.makeText("WHITECSCROLLED", NamedTextColor.WHITE, true));
        }

        //ENCHANTS \n FAILS
        if(pickaxeFunctions.getLevel(itemMeta) > 1){
            lore.add(Component.text(""));
            lore.add(Component.text("ENCHANTS"));
            lore.add(Component.text("FAILURE"));
        }

        //ENERGY
        lore.add(Component.text(""));
        lore.add(ComponentUtils.legacy("&b&lCosmic Energy"));
        StringBuilder greenBar = new StringBuilder();
        StringBuilder redBar = new StringBuilder();
        int energyCap = pickaxeFunctions.getEnergyCap(itemMeta);

        double energyPercent = ((double)currentEnergy / energyCap) * 100;

        for(int i = 0; i <= 29; i++){
            if(((double)i/30) * 100 < energyPercent){
                greenBar.append("|");
            }else{
                redBar.append("|");
            }
        }
        lore.add(ComponentUtils.legacy("&a&l"+greenBar + "&c&l"+redBar + " &f&l" + (int)energyPercent + "%"));
        lore.add(ComponentUtils.legacy("&7(&f" + String.format("%,d", currentEnergy) + " &7/ " + String.format("%,d", energyCap) + ")"));


        //CHARGE ORB
        lore.add(Component.text(""));
        if(itemMeta.getPersistentDataContainer().has(chargedOrbList)){
            lore.add(Component.text("+12% ENERGY GAIN"));
            lore.add(Component.text(" from 3 Charge Orbs")); // FROM LEN OF LIST CHARGE ORBS
        }
        lore.add(ComponentUtils.legacy("&f"+pickaxeFunctions.getChargeOrbSlots(itemMeta) + " &bCharge Orb &7slots unlocked"));
        lore.add(ComponentUtils.legacy(" &7(max &f"+pickaxeFunctions.getChargeOrbMaxSlots(itemMeta)+"&7)"));
        //BLOCKS MINED DID THIS EVEN EXIST AFTER MAP 5???????
        //lore.add(Component.text(""));

        //REQUIRED MINING LEVEL
        lore.add(Component.text(""));
        lore.add(ComponentUtils.legacy("&eRequired Mining Level &f" + getRequiredMiningLevel()));
        return lore;
    }

    default ItemStack buildItem(ItemMeta newMeta){
        ItemStack item = ItemBuilder.super.buildItem();
        ItemMeta meta = item.getItemMeta();
        if(newMeta != null)
            meta = newMeta;
        int level;


        PersistentDataContainer data = meta.getPersistentDataContainer();

        if(!data.has(energyKey)){
            level = 1;
            data.set(baseSpeedKey, PersistentDataType.FLOAT, getBaseSpeed());
            data.set(levelKey, PersistentDataType.INTEGER, 1);
            data.set(energyKey, PersistentDataType.INTEGER, 0);
            data.set(energyCapKey, PersistentDataType.INTEGER, getBaseEnergyCap() * level);
            data.set(prestigeKey, PersistentDataType.INTEGER, 0);
            data.set(chargedOrbSlots, PersistentDataType.INTEGER, 3);
            data.set(maxChargedOrbSlots, PersistentDataType.INTEGER, 10);
        }
        meta.displayName(getName(pickaxeFunctions.getLevel(meta)));
        meta.lore(getLore(pickaxeFunctions.getLevel(meta), meta));

        item.setItemMeta(meta);

        return item;
    }

    @Override
    default ItemStack rebuild(ItemMeta meta, String name) {
        return buildItem(meta); // Rebuild pickaxe with existing meta
    }
}
