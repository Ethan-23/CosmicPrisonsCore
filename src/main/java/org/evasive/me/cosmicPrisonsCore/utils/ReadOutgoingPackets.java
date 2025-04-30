package org.evasive.me.cosmicPrisonsCore.utils;

import com.github.retrooper.packetevents.event.PacketListenerAbstract;
import com.github.retrooper.packetevents.event.PacketSendEvent;
import org.evasive.me.cosmicPrisonsCore.CosmicPrisonsCore;

public class ReadOutgoingPackets extends PacketListenerAbstract {



    @Override
    public void onPacketSend(PacketSendEvent event) {
        if(event.getPacketId() == 47 || event.getPacketId() == 48 || event.getPacketId() == 77 || event.getPacketId() == 93 || event.getPacketId() == 95 || event.getPacketId() == 44 || event.getPacketId() == 50 || event.getPacketId() == 32 || event.getPacketId() == 31 || event.getPacketId() == 104 || event.getPacketId() == 107 || event.getPacketId() == 0 || event.getPacketId() == 1)
            return;
        //Bukkit.getConsoleSender().sendMessage("PACKETID: " + event.getPacketId());
        if (event.getPacketId() == 6){
            //Bukkit.getConsoleSender().sendMessage("PACKET NAME: " + event.getPacketType().getName());
            CosmicPrisonsCore.packetBroadcaster.increasePacketCount(1);
        }

    }

}
