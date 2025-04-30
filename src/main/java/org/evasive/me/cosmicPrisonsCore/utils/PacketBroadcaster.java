package org.evasive.me.cosmicPrisonsCore.utils;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import org.evasive.me.cosmicPrisonsCore.CosmicPrisonsCore;

public class PacketBroadcaster {

    private int packetCount = 0;

    public void increasePacketCount(int amount){
        packetCount += amount;
    }

    public void globalPacketBroadcast(){
        BukkitRunnable task = new BukkitRunnable() {
            @Override
            public void run() {
                // Your code here that runs every 1 second
                Bukkit.getConsoleSender().sendMessage("PACKETS SENT THIS SECOND: " + packetCount);
                packetCount = 0;
            }
        };
        task.runTaskTimer(CosmicPrisonsCore.getCore(), 0L, 20L);  // `plugin` is the instance of your plugin

    }

}
