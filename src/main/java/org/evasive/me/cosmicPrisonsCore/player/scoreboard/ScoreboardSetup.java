package org.evasive.me.cosmicPrisonsCore.player.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scoreboard.*;
import org.evasive.me.cosmicPrisonsCore.CosmicPrisonsCore;
import org.evasive.me.cosmicPrisonsCore.keys.ItemKeyFunctions;
import org.evasive.me.cosmicPrisonsCore.mining.levels.Equations;
import org.evasive.me.cosmicPrisonsCore.player.PlayerData;
import org.evasive.me.cosmicPrisonsCore.utils.ComponentUtils;

import java.time.LocalDateTime;

import static org.evasive.me.cosmicPrisonsCore.keys.ItemKeyFunctions.*;

public class ScoreboardSetup {

    public void setupMiningScoreboard(Player player) {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard miningBoard = manager.getNewScoreboard(); // Creating a new scoreboard for mining

        Objective miningObjective = miningBoard.registerNewObjective("miningStats", Criteria.create("miningStats"),
                ComponentUtils.legacy(" &b&lCosmic&6&lPrisons &r&7" + LocalDateTime.now().getMonthValue() + "/" + LocalDateTime.now().getDayOfMonth()));

        miningObjective.setDisplaySlot(DisplaySlot.SIDEBAR); // Set the display slot for the mining scoreboard

        updateMiningScores(player, miningBoard, miningObjective); // Update the mining stats
        player.setScoreboard(miningBoard); // Assign the mining scoreboard
    }

    public void setupMainScoreboard(Player player) {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard mainBoard = manager.getNewScoreboard(); // Creating a new scoreboard for main stats

        Objective mainObjective = mainBoard.registerNewObjective("mainStats", Criteria.create("mainStats"),
                ComponentUtils.legacy(" &b&lCosmic&6&lPrisons &r&7" + LocalDateTime.now().getMonthValue() + "/" + LocalDateTime.now().getDayOfMonth()));

        mainObjective.setDisplaySlot(DisplaySlot.SIDEBAR); // Set the display slot for the main scoreboard

        updateMainScores(player, mainBoard, mainObjective); // Update the main stats
        player.setScoreboard(mainBoard); // Assign the main scoreboard
    }

    public void updateMainScores(Player player, Scoreboard board, Objective objective) {
        // Clear existing main scores
        for (String entry : board.getEntries()) {
            board.resetScores(entry);
        }

        PlayerData playerData = CosmicPrisonsCore.playerLevelManager.getPlayerData(player.getUniqueId());
        // Here you can add other main stats (e.g., player currency, login time, etc.)
        addScore(board, objective, "§r", 9);
        addScore(board, objective, "§b§lAccount", 8);
        addScore(board, objective, "  " + player.getName(), 7);
        addScore(board, objective, "§r ", 6);
        addScore(board, objective, "§c§lCriminal Record", 5);
        addScore(board, objective, "  §eNeutral", 4);
        addScore(board, objective, "§r  ", 3);
        addScore(board, objective, "§a§lBalance", 2);
        addScore(board, objective, "  $0", 1);
        // Other main stats can go here
    }

    public void updateMiningScores(Player player, Scoreboard board, Objective objective) {

        // Clear existing scores
        for (String entry : board.getEntries()) {
            board.resetScores(entry);
        }


        PlayerData playerData = CosmicPrisonsCore.playerLevelManager.getPlayerData(player.getUniqueId());
        // Add your custom stats here
        int experienceNeeded = new Equations().getLevelExperience(playerData.getLevel() + 1);
        int currentLevelExpereience = new Equations().getLevelExperience(playerData.getLevel());
        int currentXP = playerData.getExperience();
        int xpThisLevel = experienceNeeded - currentLevelExpereience; // XP needed between current and next level
        int xpIntoLevel = currentXP - currentLevelExpereience;
        int experienceLeft = experienceNeeded - currentXP;

        int percent = (int)((xpIntoLevel * 100f) / xpThisLevel);

        ItemMeta meta = player.getInventory().getItemInMainHand().getItemMeta();

        int currentEnergy = getEnergy(meta);
        int energyCap = getEnergyCap(meta);

        double energyPercent = ((double)currentEnergy / energyCap) * 100;

        addScore(board, objective, "§b§lAccount " + "§f"+player.getName(), 9);
        addScore(board, objective, "§r", 8);
        addScore(board, objective, "§6§lLevel", 7);
        addScore(board, objective, "  §f§l" + playerData.getLevel() + " §7("+playerData.getExperience()+" XP)", 6);
        addScore(board, objective, "§e§lProgress", 5);
        addScore(board, objective, "  §7"+ experienceLeft +" ("+ "§a"+percent+"%" + "§7) to " + "§f"+Math.addExact(playerData.getLevel(), 1), 4);
        addScore(board, objective, "§b§lCosmic Energy", 3);
        addScore(board, objective, "  §c"+(int)energyPercent+"% " + "§7(level " + "§r§l"+ getLevel(meta) + "§7)", 2);
        addScore(board, objective, "  §7(" + "§r"+String.format("%,d", getEnergy(meta))+" " +"/ "+String.format("%,d", getEnergyCap(meta))+")", 1);
//        addScore(board, objective, ComponentUtils.makeText("Account ", NamedTextColor.AQUA, true) + player.getName(), 9);
//        addScore(board, objective, "§r", 8);
//        addScore(board, objective, "" + ComponentUtils.makeText("Level", NamedTextColor.GOLD, true), 7);
//        addScore(board, objective, "" + ComponentUtils.makeText(" "+ playerData.getLevel(), NamedTextColor.WHITE, true).append(ComponentUtils.makeText(" (" + playerData.getExperience() + ")", NamedTextColor.GRAY, false)), 6);
//        addScore(board, objective, "" + ComponentUtils.makeText("Progress", NamedTextColor.YELLOW, true), 5);
//        addScore(board, objective, "" + ComponentUtils.makeText(" 0 (", NamedTextColor.GRAY, false).append(ComponentUtils.makeText("" + "0%",NamedTextColor.GREEN, false)).append(ComponentUtils.makeText(") to ", NamedTextColor.GRAY, false)).append(ComponentUtils.makeText("" + Math.addExact(playerData.getLevel(), 1), NamedTextColor.WHITE, false)), 4);
//        addScore(board, objective, "" + ComponentUtils.makeText("Cosmic Energy", NamedTextColor.AQUA, true), 3);
//        addScore(board, objective, " 0% (level 1)", 2);
//        addScore(board, objective, " (0 / 4800)", 1);
    }

    private void addScore(Scoreboard board, Objective obj, String text, int score) {
        obj.getScore(text).setScore(score);
    }

    public void repeatingScoreboardUpdate() {
        Bukkit.getScheduler().runTaskTimer(CosmicPrisonsCore.core, () -> {
            for (Player player : Bukkit.getOnlinePlayers()) {
                // Check if the player is holding a pickaxe;
                ItemStack item = player.getInventory().getItemInMainHand();
                if (item.hasItemMeta() && hasPickaxe(item.getItemMeta())) {
                    // Show the mining scoreboard if the player is holding a pickaxe
                    setupMiningScoreboard(player);
                } else {
                    // Show the main scoreboard if the player is not holding a pickaxe
                    setupMainScoreboard(player);
                }
            }
        }, 0L, 20L); // Update every second (20 ticks)
    }
}
