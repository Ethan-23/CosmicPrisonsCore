package org.evasive.me.cosmicPrisonsCore.player;

import org.bukkit.entity.Player;

import java.util.UUID;

public class PlayerData {

    private UUID uuid;
    private int prestige;
    private int level;
    private int experience;
    private int blocksBroken;

    public PlayerData(UUID uuid){
        this.uuid = uuid;
        this.prestige = 0;
        this.level = 1;
        this.experience = 0;
        this.blocksBroken = 0;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public int getPrestige() {
        return prestige;
    }

    public void setPrestige(int prestige) {
        this.prestige = prestige;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getBlocksBroken() {
        return blocksBroken;
    }

    public void setBlocksBroken(int blocksBroken) {
        this.blocksBroken = blocksBroken;
    }
}
