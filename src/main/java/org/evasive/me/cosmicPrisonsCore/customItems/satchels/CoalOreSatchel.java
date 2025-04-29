package org.evasive.me.cosmicPrisonsCore.customItems.satchels;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.evasive.me.cosmicPrisonsCore.customItems.ItemMaker;
import org.evasive.me.cosmicPrisonsCore.mining.ores.OreType;
import org.evasive.me.cosmicPrisonsCore.utils.ComponentUtils;

public class CoalOreSatchel implements SatchelBuilder{

    @Override
    public OreType getOreType() {
        return OreType.COAL_ORE;
    }

    @Override
    public Component getName() {
        return null;
    }

    @Override
    public String getID() {
        return "COAL_ORE_SATCHEL";
    }

    @Override
    public Material getMaterial() {
        return Material.COAL_ORE;
    }

    @Override
    public ItemStack getItem() {
        return ItemMaker.coalOreSatchel;
    }
}
