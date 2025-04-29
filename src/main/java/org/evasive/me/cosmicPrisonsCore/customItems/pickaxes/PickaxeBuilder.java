package org.evasive.me.cosmicPrisonsCore.customItems.pickaxes;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.evasive.me.cosmicPrisonsCore.customItems.ItemBuilder;
import org.evasive.me.cosmicPrisonsCore.customItems.energy.EnergyInterface;
import org.evasive.me.cosmicPrisonsCore.enchanting.WormholePickaxeEnchantBuilder;
import org.evasive.me.cosmicPrisonsCore.enchanting.mining.PickaxeEnchants;
import org.evasive.me.cosmicPrisonsCore.keys.ItemKeyFunctions;
import org.evasive.me.cosmicPrisonsCore.rarity.Rarity;
import org.evasive.me.cosmicPrisonsCore.utils.ComponentUtils;
import org.evasive.me.cosmicPrisonsCore.utils.EnchantUtil;
import org.evasive.me.cosmicPrisonsCore.utils.EnergyBar;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.evasive.me.cosmicPrisonsCore.keys.ItemKeyFunctions.*;
import static org.evasive.me.cosmicPrisonsCore.keys.ItemKeys.*;

public interface PickaxeBuilder extends ItemBuilder, EnergyInterface {

    ItemKeyFunctions pickaxeFunctions = new ItemKeyFunctions();

    float getBaseSpeed();
    int getRequiredMiningLevel();
    List<Material> getTrackingMaterialOne();
    List<Material> getTrackingMaterialTwo();

    default ItemStack buildItem(){
        return buildItem(null);
    }

    default List<Component> getLore(ItemMeta itemMeta){
        int currentEnergy = getEnergy(itemMeta);
        List<Component> lore = new ArrayList<>();

        //ENCHANTS FIRST
        int enchantCount = 0;
        if(!Objects.equals(getEnchantMap(itemMeta), "")){
            lore.add(Component.text(""));
            String stringEnchantMap = getEnchantMap(itemMeta);
            Map<PickaxeEnchants, Integer> enchantMap = new EnchantUtil().stringToEnumMap(stringEnchantMap, PickaxeEnchants.class);
            for (PickaxeEnchants value : PickaxeEnchants.values()) {
                WormholePickaxeEnchantBuilder enchant = value.wormholePickaxeEnchantBuilder;
                if(!enchantMap.containsKey(value))
                    continue;
                if(enchant.getRarity().ordinal() >= Rarity.ELITE.ordinal() && !itemMeta.hasEnchant(Enchantment.LOYALTY)){
                    itemMeta.addEnchant(Enchantment.LOYALTY, 1, true);
                    itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                }
                enchantCount+= enchantMap.get(value);
                String bold = "";
                if(enchantMap.get(value) == enchant.getMaxLevel())
                    bold = "&l";
                lore.add(ComponentUtils.legacy(enchant.getRarity().getRarityBuilder().getTextColor()+ bold + enchant.getName() + " &b" + bold + new EnchantUtil().intToRoman(enchantMap.get(value))));
                // Do something with value
            }
        }

        //WHITESCROLL
        if(hasKey(itemMeta, whiteScrolled) && isWhitescrolled(itemMeta)){
            lore.add(Component.text(""));
            //lore.add(ComponentUtils.makeText("WHITECSCROLLED", NamedTextColor.WHITE, true));
        }

        //ENCHANTS \n FAILS
        if(getLevel(itemMeta) > 1){
            lore.add(Component.text(""));
            if(enchantCount > 1){
                lore.add(ComponentUtils.legacy("&a&l&m= &r&f&l " + enchantCount + " &a&lEnchants &l&m ="));
            }else if(enchantCount == 1){
                lore.add(ComponentUtils.legacy("&a&l&m= &r&f&l " + enchantCount + " &a&lEnchant &l&m ="));
            }

            int fails = (getLevel(itemMeta) - enchantCount - 1);
            if(fails > 1){
                lore.add(ComponentUtils.legacy("&c&l&m= &r&f&l " + fails + " &c&lFailures &l&m ="));
            }else if (fails == 1){
                lore.add(ComponentUtils.legacy("&c&l&m= &r&f&l " + fails + " &c&lFailure &l&m ="));
            }

        }

        //ENERGY
        lore.add(Component.text(""));
        lore.add(ComponentUtils.legacy("&b&lCosmic Energy"));
        int energyCap = getEnergyCap(itemMeta);
//        StringBuilder greenBar = new StringBuilder();
//        StringBuilder redBar = new StringBuilder();
//
//
//        double energyPercent = ((double)currentEnergy / energyCap) * 100;
//
//        for(int i = 0; i <= 29; i++){
//            if(((double)i/30) * 100 < energyPercent){
//                greenBar.append("|");
//            }else{
//                redBar.append("|");
//            }
//        }
//        lore.add(ComponentUtils.legacy("&a&l"+greenBar + "&c&l"+redBar + " &f&l" + (int)energyPercent + "%"));
        lore.add(new EnergyBar().getEnergyBar(currentEnergy, energyCap));
        lore.add(ComponentUtils.legacy("&7(&f" + String.format("%,d", currentEnergy) + " &7/ " + String.format("%,d", energyCap) + ")"));


        //CHARGE ORB
        lore.add(Component.text(""));
        if(itemMeta.getPersistentDataContainer().has(chargedOrbList)){
            lore.add(Component.text("+12% ENERGY GAIN"));
            lore.add(Component.text(" from 3 Charge Orbs")); // FROM LEN OF LIST CHARGE ORBS
        }
        lore.add(ComponentUtils.legacy("&f"+ getChargeOrbSlots(itemMeta) + " &bCharge Orb &7slots unlocked"));
        lore.add(ComponentUtils.legacy(" &7(max &f"+ getChargeOrbMaxSlots(itemMeta)+"&7)"));
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
            data.set(enchantMap, PersistentDataType.STRING, "");
            data.set(prestigeKey, PersistentDataType.INTEGER, 0);
            data.set(chargedOrbSlots, PersistentDataType.INTEGER, 3);
            data.set(maxChargedOrbSlots, PersistentDataType.INTEGER, 10);
        }

        meta.lore(getLore(meta));
        meta.displayName(getName(meta));

        item.setItemMeta(meta);

        return item;
    }

    @Override
    default ItemStack rebuild(ItemMeta meta, String name) {
        return buildItem(meta); // Rebuild pickaxe with existing meta
    }
}
