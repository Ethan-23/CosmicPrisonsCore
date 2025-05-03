package org.evasive.me.cosmicPrisonsCore.customItems.satchels;

import net.kyori.adventure.text.Component;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.evasive.me.cosmicPrisonsCore.customItems.ItemBuilder;
import org.evasive.me.cosmicPrisonsCore.customItems.energy.EnergyInterface;
import org.evasive.me.cosmicPrisonsCore.keys.ItemKeyFunctions;
import org.evasive.me.cosmicPrisonsCore.mining.ores.OreType;
import org.evasive.me.cosmicPrisonsCore.utils.ComponentUtils;
import org.evasive.me.cosmicPrisonsCore.utils.EnergyBar;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.evasive.me.cosmicPrisonsCore.keys.ItemKeyFunctions.getLevel;
import static org.evasive.me.cosmicPrisonsCore.keys.ItemKeys.*;

public interface SatchelBuilder extends ItemBuilder, EnergyInterface {

    OreType getOreType();

    default int getBaseEnergyCap() {
        return 7200;
    }

    default int getEnergyLevelIncrease() {
        return 12000;
    }

    default ItemStack buildItem(){
        return buildItem(null);
    }

    @Override
    default Component getName(ItemMeta meta) {
        String nameS = getOreType().getOreCreator().getName() + " Satchel&r&7 (&a" + String.format("%,d", ItemKeyFunctions.getOreStorage(meta)) + " &7/ &f" + String.format("%,d", Math.multiplyExact(getLevel(meta), ItemKeyFunctions.getOreCap(meta))) +"&7)";
        if(getLevel(meta) > 1)
            nameS += " &a&l" + getLevel(meta);
        return ComponentUtils.legacy(nameS);
    }

    default List<Component> getLore(ItemMeta itemMeta){
        int currentEnergy = ItemKeyFunctions.getEnergy(itemMeta);
        List<Component> lore = new ArrayList<>();

        lore.add(ComponentUtils.legacy("&a&l" + String.format("%,d", ItemKeyFunctions.getOreStorage(itemMeta)) + " &r&7/ &f&l" + String.format("%,d", ItemKeyFunctions.getOreCap(itemMeta))));

        //ENCHANTS FIRST
        if(ItemKeyFunctions.hasKey(itemMeta, enchantMap)){
            lore.add(Component.text(""));
            //ADD ENCHANT LORE HERE
        }

        //WHITESCROLL
        if(ItemKeyFunctions.hasKey(itemMeta, whiteScrolled) && ItemKeyFunctions.isWhitescrolled(itemMeta)){
            lore.add(Component.text(""));
            //lore.add(ComponentUtils.makeText("WHITECSCROLLED", NamedTextColor.WHITE, true));
        }

        //ENERGY
        lore.add(Component.text(""));
        lore.add(ComponentUtils.legacy("&b&lCosmic Energy"));
        int energyCap = ItemKeyFunctions.getEnergyCap(itemMeta);
        lore.add(new EnergyBar().getEnergyBar(currentEnergy, energyCap));
        lore.add(ComponentUtils.legacy("&7(&f" + String.format("%,d", currentEnergy) + " &7/ " + String.format("%,d", energyCap) + ")"));

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
            data.set(levelKey, PersistentDataType.INTEGER, 1);
            data.set(energyKey, PersistentDataType.INTEGER, 0);
            data.set(energyCapKey, PersistentDataType.INTEGER, getBaseEnergyCap() * level);
            data.set(oreCapKey, PersistentDataType.INTEGER, 2304);
            data.set(oreAmountKey, PersistentDataType.INTEGER, 0);
            // NEED TO MAKE THE GIVE FUNCTION ADD THE RANDOM SINCE THIS IS BEING BUILT ON LOAD THEY ALL HAVE THE SAME KEY
            data.set(uniqueKey, PersistentDataType.STRING, UUID.randomUUID().toString());
        }
        meta.displayName(getName(meta));
        meta.lore(getLore(meta));

        item.setItemMeta(meta);

        return item;
    }

    @Override
    default ItemStack rebuild(ItemMeta meta, String name) {
        return buildItem(meta); // Rebuild pickaxe with existing meta
    }

    @Override
    default boolean isGlowing() {
        return true;
    }

}
