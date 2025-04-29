package org.evasive.me.cosmicPrisonsCore.customItems.satchels;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.evasive.me.cosmicPrisonsCore.customItems.ItemMaker;
import org.evasive.me.cosmicPrisonsCore.mining.ores.OreType;

public class IronOreSatchel implements SatchelBuilder{
    @Override
    public OreType getOreType() {
        return OreType.IRON_ORE;
    }

    @Override
    public Component getName() {
        return null;
    }

    @Override
    public String getID() {
        return "IRON_ORE_SATCHEL";
    }

    @Override
    public Material getMaterial() {
        return Material.IRON_ORE;
    }

    @Override
    public ItemStack getItem() {
        return ItemMaker.ironOreSatchel;
    }
}
