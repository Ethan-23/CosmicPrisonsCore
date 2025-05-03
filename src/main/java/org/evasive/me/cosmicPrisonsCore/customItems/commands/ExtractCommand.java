package org.evasive.me.cosmicPrisonsCore.customItems.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.evasive.me.cosmicPrisonsCore.CosmicPrisonsCore;
import org.evasive.me.cosmicPrisonsCore.customItems.ItemBuilder;
import org.evasive.me.cosmicPrisonsCore.customItems.ItemList;
import org.evasive.me.cosmicPrisonsCore.keys.ItemKeyFunctions;
import org.evasive.me.cosmicPrisonsCore.utils.ComponentUtils;
import org.jetbrains.annotations.NotNull;

import static org.evasive.me.cosmicPrisonsCore.keys.ItemKeys.energyKey;

public class ExtractCommand implements CommandExecutor {

    public ExtractCommand(){
        CosmicPrisonsCore.getCore().getCommand("extract").setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        if(!(commandSender instanceof Player player))
            return true;
        if(strings.length == 0){
            player.sendMessage("Enter and amount of energy you want to extract");
            return true;
        } else if (strings.length == 1){

            int amount;
            if(strings[0].equals("all"))
                strings[0] = "2147483647";
            try{
                amount = Integer.parseInt(strings[0]);
            }catch (NumberFormatException e){
                player.sendMessage("Enter a valid amount of energy to extract /extract (number)");
                return true;
            }

            if(amount <= 0){
                player.sendMessage("You cannot extract less than 1 energy");
                return true;
            }

            ItemStack mainItem = player.getInventory().getItemInMainHand();
            if(!mainItem.hasItemMeta()){
                player.sendMessage("Item must have energy to extract");
                return true;
            }

            if(!ItemKeyFunctions.hasKey(mainItem, energyKey)){
                player.sendMessage("Item must have energy to extract");
                return true;
            }


            if(ItemKeyFunctions.getEnergy(mainItem.getItemMeta()) == 0){
                player.sendMessage("No energy to extract");
                return true;
            }


            ItemMeta meta = mainItem.getItemMeta();

            if(ItemKeyFunctions.getEnergy(meta) < amount){
                amount = ItemKeyFunctions.getEnergy(meta);
            }

            ItemKeyFunctions.removeEnergy(meta, amount);
            mainItem.setItemMeta(meta);

            ItemStack newItem = CosmicPrisonsCore.itemMaker.createEnergyStack(amount, player.getName());

            player.sendMessage(ComponentUtils.legacy("Successfully extracted &l" + String.format("%,d", amount) + " &r&bCosmic Energy"));

            if(ItemKeyFunctions.getID(meta).equals("COSMIC_ENERGY") && ItemKeyFunctions.getEnergy(meta) == 0){
                player.getInventory().setItemInMainHand(newItem);
                return true;
            }

            ItemBuilder builder = ItemList.valueOf(ItemKeyFunctions.getID(meta)).getItemBuilder();
            player.getInventory().setItemInMainHand(builder.rebuild(meta, player.getName()));
            player.getInventory().addItem(newItem);
        }



        return true;
    }
}
