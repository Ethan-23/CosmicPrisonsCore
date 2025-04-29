package org.evasive.me.cosmicPrisonsCore.customItems.satchels;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.evasive.me.cosmicPrisonsCore.customItems.ItemMaker;
import org.evasive.me.cosmicPrisonsCore.mining.ores.OreType;

public class GoldOreSatchel implements SatchelBuilder{
    @Override
    public OreType getOreType() {
        return OreType.GOLD_ORE;
    }

    @Override
    public Component getName() {
        return null;
    }

    @Override
    public String getID() {
        return "GOLD_ORE_SATCHEL";
    }

    @Override
    public Material getMaterial() {
        return Material.GOLD_ORE;
    }

    @Override
    public ItemStack getItem() {
        return ItemMaker.goldOreSatchel;
    }
}
