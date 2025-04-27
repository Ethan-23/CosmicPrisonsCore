package org.evasive.me.cosmicPrisonsCore.mining.levels;

public class Equations {

    public int getLevelExperience(int level){
        int experienceLevel = 0;
        if(level <= 10){
            experienceLevel = (int) Math.round(13.5417 * (Math.pow(level, 2)) - (13.4311 * level) + 0.1167);
        }else if (level <= 30){
            experienceLevel = (int) Math.round(418.3463 * (Math.pow(level, 2)) - (11428.7014 * level) + 80717.0222);
        }else if (level <= 50){
            experienceLevel = (int) Math.round(0.1787 * (Math.pow(level, 3)) - 98.2013 * Math.pow(level, 2) + 8679.0433 * level - 217372.9114);
        }else if (level <= 70){
            experienceLevel = (int) Math.round(0.6595 * Math.pow(level, 3) - 153.9846 * Math.pow(level, 2) + 13685.2117 * level - 353306.4608);
        }else if (level <= 90){
            experienceLevel = (int) Math.round(785306.9328 * Math.E * (0.1075 * level));
        }else if (level <= 100){

        }else {
            experienceLevel = 999999999;
        }
        return experienceLevel;

    }


}
