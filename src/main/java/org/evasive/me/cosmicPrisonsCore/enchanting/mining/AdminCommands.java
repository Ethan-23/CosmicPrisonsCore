package org.evasive.me.cosmicPrisonsCore.enchanting.mining;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.evasive.me.cosmicPrisonsCore.CosmicPrisonsCore;
import org.evasive.me.cosmicPrisonsCore.enchanting.ItemEnchantFunctions;
import org.evasive.me.cosmicPrisonsCore.utils.EnchantUtil;
import org.jetbrains.annotations.NotNull;

import static org.evasive.me.cosmicPrisonsCore.keys.ItemKeyFunctions.hasKey;
import static org.evasive.me.cosmicPrisonsCore.keys.ItemKeys.enchantMap;

public class AdminCommands implements CommandExecutor {

    public AdminCommands(){
        CosmicPrisonsCore.getCore().getCommand("cosmicenchant").setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {

        if(!(commandSender instanceof Player player)){
            commandSender.sendMessage("This command must be used by a player");
            return true;
        }

        if(strings.length == 1 && strings[0].equals("all")){
            for (PickaxeEnchants pickaxeEnchants : PickaxeEnchants.values()){
                player.getInventory().setItemInMainHand(new ItemEnchantFunctions().addEnchant(player.getInventory().getItemInMainHand(), pickaxeEnchants.wormholePickaxeEnchantBuilder().getEnchant(), pickaxeEnchants.wormholePickaxeEnchantBuilder().getMaxLevel()));
            }
        }

        if(strings.length == 2){
            try {
                PickaxeEnchants.valueOf(strings[0]);
            }catch (IllegalArgumentException e){
                player.sendMessage("Invalid Enchant Name");
                return true;
            }

            try{
                Integer.parseInt(strings[1]);
            }catch (NumberFormatException e){
                player.sendMessage("Not a valid number");
                return true;
            }

            ItemStack itemStack = player.getInventory().getItemInMainHand();
            if(!itemStack.hasItemMeta()){
                player.sendMessage("Invalid Item");
                return true;
            }

            ItemMeta meta = itemStack.getItemMeta();
            if(!hasKey(meta, enchantMap)){
                player.sendMessage("Invalid Item");
                return true;
            }
            player.getInventory().setItemInMainHand(new ItemEnchantFunctions().addEnchant(player.getInventory().getItemInMainHand(), PickaxeEnchants.valueOf(strings[0]), Integer.parseInt(strings[1])));
        }

        return true;
    }
}
