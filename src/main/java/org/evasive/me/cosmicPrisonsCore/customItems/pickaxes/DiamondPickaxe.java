package org.evasive.me.cosmicPrisonsCore.customItems.pickaxes;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.evasive.me.cosmicPrisonsCore.customItems.ItemMaker;
import org.evasive.me.cosmicPrisonsCore.utils.ComponentUtils;

import java.util.List;

public class DiamondPickaxe implements PickaxeBuilder{
    @Override
    public int getBaseEnergyCap() {
        return 9600;
    }

    @Override
    public float getBaseSpeed() {
        return 5f;
    }

    @Override
    public int getRequiredMiningLevel() {
        return 90;
    }

    @Override
    public int getEnergyLevelIncrease() {
        return 14400;
    }

    @Override
    public List<Material> getTrackingMaterialOne() {
        return List.of(Material.DIAMOND_ORE, Material.DIAMOND_BLOCK);
    }

    @Override
    public List<Material> getTrackingMaterialTwo() {
        return List.of(Material.EMERALD_ORE, Material.EMERALD_BLOCK);
    }

    @Override
    public Component getName() {
        return ComponentUtils.legacy("Diamond Pickaxe");
    }

    @Override
    public String getID() {
        return "DIAMOND_PICKAXE";
    }

    @Override
    public Material getMaterial() {
        return Material.DIAMOND_PICKAXE;
    }

    @Override
    public boolean isGlowing() {
        return false;
    }

    @Override
    public ItemStack getItem() {
        return ItemMaker.diamondPickaxe;
    }
}
