package org.evasive.me.cosmicPrisonsCore.customItems.satchels;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.evasive.me.cosmicPrisonsCore.customItems.ItemMaker;
import org.evasive.me.cosmicPrisonsCore.mining.ores.OreType;

public class EmeraldOreSatchel implements SatchelBuilder{
    @Override
    public OreType getOreType() {
        return OreType.EMERALD_ORE;
    }

    @Override
    public Component getName() {
        return null;
    }

    @Override
    public String getID() {
        return "EMERALD_ORE_SATCHEL";
    }

    @Override
    public Material getMaterial() {
        return Material.EMERALD_ORE;
    }

    @Override
    public ItemStack getItem() {
        return ItemMaker.emeraldOreSatchel;
    }
}
