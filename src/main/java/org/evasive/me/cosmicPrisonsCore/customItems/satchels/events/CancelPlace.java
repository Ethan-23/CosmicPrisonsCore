package org.evasive.me.cosmicPrisonsCore.customItems.satchels.events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.evasive.me.cosmicPrisonsCore.customItems.ItemMaker;

import static org.evasive.me.cosmicPrisonsCore.keys.ItemKeyFunctions.getID;
import static org.evasive.me.cosmicPrisonsCore.keys.ItemKeyFunctions.hasKey;
import static org.evasive.me.cosmicPrisonsCore.keys.ItemKeys.itemIDKey;

public class CancelPlace implements Listener {

    @EventHandler
    public void cancelPlace(BlockPlaceEvent e){

        ItemStack itemStack = e.getItemInHand();

        if(!itemStack.hasItemMeta())
            return;
        ItemMeta meta = itemStack.getItemMeta();
        if(!hasKey(meta, itemIDKey))
            return;
        if(getID(meta).substring(Math.max(getID(meta).length() - 7, 0)).equals("SATCHEL")){
            e.setCancelled(true);
        }

    }

}
