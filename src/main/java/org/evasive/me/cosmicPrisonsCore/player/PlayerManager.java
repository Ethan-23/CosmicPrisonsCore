package org.evasive.me.cosmicPrisonsCore.player;

import org.bukkit.Bukkit;
import org.evasive.me.cosmicPrisonsCore.mining.levels.LevelFunctions;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerManager {

    private final Map<UUID, PlayerData> playerExperience = new HashMap<>();

    public PlayerData getPlayerData(UUID uuid){
        return playerExperience.get(uuid);
    }

    public boolean hasPlayer(UUID uuid){
        return playerExperience.containsKey(uuid);
    }

    public void addPlayer(UUID uuid){
        PlayerData playerData = new PlayerData(uuid);
        playerExperience.put(uuid, playerData);
    }

    public void addExperince(UUID uuid, int amount){
        PlayerData playerData = playerExperience.get(uuid);
        playerData.setExperience(playerData.getExperience() + amount);
        playerExperience.put(uuid, playerData);
    }



}
