package org.evasive.me.cosmicPrisonsCore.customItems.energy.events;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.evasive.me.cosmicPrisonsCore.customItems.ItemBuilder;
import org.evasive.me.cosmicPrisonsCore.customItems.ItemList;
import org.evasive.me.cosmicPrisonsCore.customItems.energy.CosmicEnergy;
import org.evasive.me.cosmicPrisonsCore.customItems.pickaxes.PickaxeBuilder;
import org.evasive.me.cosmicPrisonsCore.customItems.satchels.SatchelBuilder;
import org.evasive.me.cosmicPrisonsCore.keys.ItemKeyFunctions;

import static org.evasive.me.cosmicPrisonsCore.keys.ItemKeys.energyKey;

public class EnergyCombine implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();

        if(event.getClickedInventory() == null)
            return;

        if(event.getCurrentItem() == null)
            return;

        ItemStack clickedItem = event.getCurrentItem();
        ItemStack cursorItem = event.getCursor();

        if(!cursorItem.hasItemMeta())
            return;

        if(!clickedItem.hasItemMeta())
            return;

        ItemMeta cursorMeta = cursorItem.getItemMeta();
        if(!ItemKeyFunctions.hasKey(cursorMeta, energyKey))
            return;

        ItemMeta clickedMeta = clickedItem.getItemMeta();
        if(!ItemKeyFunctions.hasKey(clickedMeta, energyKey))
            return;

        if(!ItemKeyFunctions.getID(cursorMeta).equals("COSMIC_ENERGY"))
            return;

        event.setCancelled(true);

        player.setItemOnCursor(null);

        ItemKeyFunctions.addEnergy(clickedMeta, ItemKeyFunctions.getEnergy(cursorMeta));

        ItemStack completeItem = null;

        if(ItemKeyFunctions.getID(clickedMeta).equals("COSMIC_ENERGY")){
            //Energy -> Energy
            completeItem = new CosmicEnergy().buildItem(ItemKeyFunctions.getEnergy(clickedMeta), player.getName());
        } else {
            //Energy -> Pickaxe
//            try{
//                PickaxeBuilder pickaxeBuilder = (PickaxeBuilder) ItemList.valueOf(pickaxeFunctions.getID(clickedMeta)).getItemBuilder();
//                completeItem = pickaxeBuilder.buildItem(clickedMeta);
//            }catch (IllegalArgumentException e){
//                //
//            }
            ItemBuilder itemBuilder = ItemList.valueOf(ItemKeyFunctions.getID(clickedMeta)).getItemBuilder();

            if (itemBuilder instanceof PickaxeBuilder pickaxeBuilder) {
                completeItem = pickaxeBuilder.buildItem(clickedMeta);
            } else if (itemBuilder instanceof SatchelBuilder satchelBuilder) {
                completeItem = satchelBuilder.buildItem(clickedMeta);
            } else {
                // Handle unknown item types if needed
            }

        }

        if(completeItem != null){
            player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 0.75f);
            player.getInventory().setItem(event.getSlot(), completeItem);
        }


    }
}
