package org.evasive.me.cosmicPrisonsCore.player.events;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.evasive.me.cosmicPrisonsCore.CosmicPrisonsCore;
import org.evasive.me.cosmicPrisonsCore.player.scoreboard.ScoreboardSetup;

import java.util.UUID;

public class JoinEvent implements Listener {

    @EventHandler
    public void playerJoinEvent(PlayerJoinEvent e){
        Player player = e.getPlayer();
        UUID uuid = player.getUniqueId();

        if(player.getAttribute(Attribute.BLOCK_BREAK_SPEED) == null) {
            player.registerAttribute(Attribute.BLOCK_BREAK_SPEED);
        }
        player.getAttribute(Attribute.BLOCK_BREAK_SPEED).setBaseValue(0);



        if(!CosmicPrisonsCore.getPlayerLevelManager().hasPlayer(uuid))
            CosmicPrisonsCore.playerLevelManager.addPlayer(uuid);


        Bukkit.getScheduler().runTaskLater(CosmicPrisonsCore.core, () -> {
            new ScoreboardSetup().setupMainScoreboard(player);
        }, 20L);

    }

}
