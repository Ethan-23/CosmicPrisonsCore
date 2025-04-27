package org.evasive.me.cosmicPrisonsCore.customItems.energy;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.evasive.me.cosmicPrisonsCore.customItems.ItemBuilder;
import org.evasive.me.cosmicPrisonsCore.customItems.ItemMaker;
import org.evasive.me.cosmicPrisonsCore.customItems.pickaxes.PickaxeBuilder;
import org.evasive.me.cosmicPrisonsCore.utils.ComponentUtils;

import java.util.List;

public class CosmicEnergy implements CosmicEnergyBuilder {
    @Override
    public Component getName() {
        return ComponentUtils.legacy("&aCosmic Energy");
    }

    @Override
    public String getID() {
        return "COSMIC_ENERGY";
    }

    @Override
    public ItemStack buildItem() {
        return CosmicEnergyBuilder.super.buildItem();
    }

    @Override
    public List<Component> getLore() {
        return List.of();
    }

    @Override
    public Material getMaterial() {
        return Material.LIGHT_BLUE_DYE;
    }

    @Override
    public boolean isGlowing() {
        return false;
    }

    @Override
    public ItemStack getItem() {
        return ItemMaker.cosmicEnergy;
    }
}
