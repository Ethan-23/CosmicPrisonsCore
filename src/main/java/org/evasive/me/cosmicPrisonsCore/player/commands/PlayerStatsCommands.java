package org.evasive.me.cosmicPrisonsCore.player.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.evasive.me.cosmicPrisonsCore.CosmicPrisonsCore;
import org.evasive.me.cosmicPrisonsCore.player.PlayerData;
import org.jetbrains.annotations.NotNull;

public class PlayerStatsCommands implements CommandExecutor {

    public PlayerStatsCommands(){
        CosmicPrisonsCore.getCore().getCommand("stats").setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        if(!(commandSender instanceof Player player))
            return true;

        if(strings.length == 0){
            PlayerData playerData = CosmicPrisonsCore.getPlayerLevelManager().getPlayerData(player.getUniqueId());
            player.sendMessage("Stats\nPrestige: " + playerData.getPrestige() + "\nLevel: " + playerData.getLevel() +"\nExperience: " + playerData.getExperience());
        }

        return true;
    }
}
