package org.evasive.me.cosmicPrisonsCore.customItems.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.evasive.me.cosmicPrisonsCore.CosmicPrisonsCore;
import org.evasive.me.cosmicPrisonsCore.customItems.ItemList;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public class ItemCommands implements CommandExecutor{

    public ItemCommands(){
        CosmicPrisonsCore.getCore().getCommand("admingive").setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        List<String> itemList = Arrays.stream(ItemList.values()).map(Enum::name).toList();
        if(strings.length == 0){
            commandSender.sendMessage("" + itemList);
            return true;
        }
        if(!(commandSender instanceof Player player))
            return true;

        if(strings.length == 1){
            String itemName = strings[0].toUpperCase();
            if(itemList.contains(itemName)) {
                player.getInventory().addItem(ItemList.valueOf(itemName).getItemBuilder().getItem());
                return true;
            }

            player.sendMessage(strings[0] + " Not Found");
            return true;

        }else if(strings.length == 2 && Bukkit.getPlayer(strings[0]) != null){
            Player givenPlayer = Bukkit.getPlayer(strings[0]);
            String itemName = strings[1].toUpperCase();
            //DONT GIVE ERROR IF NOT IN ITEMLIST
            givenPlayer.getInventory().addItem(ItemList.valueOf(itemName).getItemBuilder().getItem());
            return true;
        }
        return true;
    }

}
