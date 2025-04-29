package org.evasive.me.cosmicPrisonsCore.customItems.satchels;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.evasive.me.cosmicPrisonsCore.customItems.ItemMaker;
import org.evasive.me.cosmicPrisonsCore.mining.ores.OreType;

public class RedstoneOreSatchel implements SatchelBuilder{
    @Override
    public OreType getOreType() {
        return OreType.REDSTONE_ORE;
    }

    @Override
    public Component getName() {
        return null;
    }

    @Override
    public String getID() {
        return "REDSTONE_ORE_SATCHEL";
    }

    @Override
    public Material getMaterial() {
        return Material.REDSTONE_ORE;
    }

    @Override
    public ItemStack getItem() {
        return ItemMaker.redstoneOreSatchel;
    }
}
