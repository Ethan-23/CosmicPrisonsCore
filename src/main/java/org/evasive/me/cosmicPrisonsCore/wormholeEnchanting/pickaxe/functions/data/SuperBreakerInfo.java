package org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.pickaxe.functions.data;

public class SuperBreakerInfo {
    private int blocks;
    private int time;
    private boolean doubleXP;
    private int timerID;

    public SuperBreakerInfo(int blocks, int time, boolean doubleXP, int timerID) {
        this.blocks = blocks;
        this.time = time;
        this.doubleXP = doubleXP;
        this.timerID = timerID;
    }

    public int getTimerID() {
        return timerID;
    }

    public void setTimerID(int timerID) {
        this.timerID = timerID;
    }

    public int getBlocks() {
        return blocks;
    }

    public void setBlocks(int blocks) {
        this.blocks = blocks;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public boolean isDoubleXP() {
        return doubleXP;
    }

    public void setDoubleXP(boolean doubleXP) {
        this.doubleXP = doubleXP;
    }
}
