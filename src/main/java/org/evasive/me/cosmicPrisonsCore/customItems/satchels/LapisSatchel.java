package org.evasive.me.cosmicPrisonsCore.customItems.satchels;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.evasive.me.cosmicPrisonsCore.customItems.ItemMaker;
import org.evasive.me.cosmicPrisonsCore.mining.ores.OreType;

public class LapisSatchel implements SatchelBuilder{
    @Override
    public OreType getOreType() {
        return OreType.LAPIS_BLOCK;
    }

    @Override
    public Component getName() {
        return null;
    }

    @Override
    public String getID() {
        return "LAPIS_SATCHEL";
    }

    @Override
    public Material getMaterial() {
        return Material.LAPIS_LAZULI;
    }

    @Override
    public ItemStack getItem() {
        return ItemMaker.lapisSatchel;
    }
}
