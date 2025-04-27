package org.evasive.me.cosmicPrisonsCore.customItems.energy;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.evasive.me.cosmicPrisonsCore.CosmicPrisonsCore;
import org.jetbrains.annotations.NotNull;

public class EnergyCommands implements CommandExecutor {

    public EnergyCommands(){
        CosmicPrisonsCore.getCore().getCommand("adminenergy").setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        return false;
    }
}
