package org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.pickaxe.functions.data;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SuperBreakerMap {

    Map<UUID, SuperBreakerInfo> superBreakerMap = new HashMap<UUID, SuperBreakerInfo>();

    public void addSuperBreaker(Player player, int blocks, int time, boolean doubleXP, int taskId){
        SuperBreakerInfo superBreakerInfo = new SuperBreakerInfo(blocks, time, doubleXP, taskId);
        superBreakerMap.put(player.getUniqueId(), superBreakerInfo);
    }

    public void removeSuperBreaker(Player player){
        Bukkit.getScheduler().cancelTask(getSuperBreaker(player).getTimerID());
        superBreakerMap.remove(player.getUniqueId());
    }

    public SuperBreakerInfo getSuperBreaker(Player player){
        return superBreakerMap.get(player.getUniqueId());
    }

    public boolean hasSuperBreaker(Player player) {
        return superBreakerMap.containsKey(player.getUniqueId());
    }

    public boolean hasDoubleXP(Player player){
        if (!superBreakerMap.containsKey(player.getUniqueId()))
            return false;
        return getSuperBreaker(player).isDoubleXP();
    }

    public void subtractTime(Player player) {
        SuperBreakerInfo superBreakerInfo = getSuperBreaker(player);
        superBreakerInfo.setTime(superBreakerInfo.getTime() - 1);
        superBreakerMap.put(player.getUniqueId(), superBreakerInfo);
    }

    public void subtractBlock(Player player){
        SuperBreakerInfo superBreakerInfo = getSuperBreaker(player);
        superBreakerInfo.setBlocks(superBreakerInfo.getBlocks() - 1);
        superBreakerMap.put(player.getUniqueId(), superBreakerInfo);
    }
}
