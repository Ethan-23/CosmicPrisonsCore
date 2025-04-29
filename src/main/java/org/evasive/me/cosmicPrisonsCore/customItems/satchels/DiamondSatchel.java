package org.evasive.me.cosmicPrisonsCore.customItems.satchels;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.evasive.me.cosmicPrisonsCore.customItems.ItemMaker;
import org.evasive.me.cosmicPrisonsCore.mining.ores.OreType;

public class DiamondSatchel implements SatchelBuilder{
    @Override
    public OreType getOreType() {
        return OreType.DIAMOND_BLOCK;
    }

    @Override
    public Component getName() {
        return null;
    }

    @Override
    public String getID() {
        return "DIAMOND_SATCHEL";
    }

    @Override
    public Material getMaterial() {
        return Material.DIAMOND;
    }

    @Override
    public ItemStack getItem() {
        return ItemMaker.diamondSatchel;
    }
}
