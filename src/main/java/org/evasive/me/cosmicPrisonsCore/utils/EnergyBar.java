package org.evasive.me.cosmicPrisonsCore.utils;

import net.kyori.adventure.text.Component;

public class EnergyBar {

    public Component getEnergyBar(int currentEnergy, int energyCap){
        StringBuilder greenBar = new StringBuilder();
        StringBuilder redBar = new StringBuilder();


        double energyPercent = ((double)currentEnergy / energyCap) * 100;

        if(energyPercent > 100)
            energyPercent = 100;

        for(int i = 0; i <= 29; i++){
            if(((double)i/30) * 100 < energyPercent){
                greenBar.append("|");
            }else{
                redBar.append("|");
            }
        }
        return ComponentUtils.legacy("&a&l"+greenBar + "&c&l"+redBar + " &f&l" + (int)energyPercent + "%");
    }

}
