package org.evasive.me.cosmicPrisonsCore.customItems.satchels;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.evasive.me.cosmicPrisonsCore.customItems.ItemMaker;
import org.evasive.me.cosmicPrisonsCore.mining.ores.OreType;

public class RedstoneSatchel implements SatchelBuilder{
    @Override
    public OreType getOreType() {
        return OreType.REDSTONE_BLOCK;
    }

    @Override
    public Component getName() {
        return null;
    }

    @Override
    public String getID() {
        return "REDSTONE_SATCHEL";
    }

    @Override
    public Material getMaterial() {
        return Material.REDSTONE;
    }

    @Override
    public ItemStack getItem() {
        return ItemMaker.redstoneSatchel;
    }
}
