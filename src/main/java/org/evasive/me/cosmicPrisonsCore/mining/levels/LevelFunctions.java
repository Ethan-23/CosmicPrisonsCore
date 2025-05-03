package org.evasive.me.cosmicPrisonsCore.mining.levels;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.evasive.me.cosmicPrisonsCore.CosmicPrisonsCore;
import org.evasive.me.cosmicPrisonsCore.player.PlayerData;
import org.evasive.me.cosmicPrisonsCore.utils.ComponentUtils;

public class LevelFunctions {

    public void checkForLevelUp(Player player) {
        PlayerData playerData = CosmicPrisonsCore.playerLevelManager.getPlayerData(player.getUniqueId());

        int experienceLevel = new Equations().getLevelExperience(playerData.getLevel() + 1);

        if(!canLevelUp(experienceLevel, playerData)) return;

        while (experienceLevel <= playerData.getExperience()) {
            playerData.setLevel(playerData.getLevel() + 1);
            playLevelUpSound(player);
            sendLevelUpMessage(player, playerData.getLevel());
            experienceLevel = new Equations().getLevelExperience(playerData.getLevel() + 1);
        }
    }

    public boolean canLevelUp(int experienceLevel, PlayerData playerData){
        //Add level cap & prestige logic here later
        return (experienceLevel < playerData.getExperience());
    }

    public void playLevelUpSound(Player player){
        player.playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_HURT, 1.0f, 0.8f);
    }

    public void sendLevelUpMessage(Player player, int level){
        if (CosmicPrisonsCore.playerLevelManager.getPlayerData(player.getUniqueId()).getLevel() % 5 == 0)
            Bukkit.getServer().sendMessage(ComponentUtils.legacy(String.format("%s has reached level %d", player.getName(), level)));
    }
}
