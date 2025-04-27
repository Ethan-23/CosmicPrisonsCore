package org.evasive.me.cosmicPrisonsCore.random;

import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;

public class DisableCraftingEvents implements Listener {
    @EventHandler
    public void inventoryClickEvent(InventoryClickEvent e){
        if(e.getSlotType().equals(InventoryType.SlotType.RESULT))
            e.setCancelled(true);
    }
}
