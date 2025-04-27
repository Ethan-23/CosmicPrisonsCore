package org.evasive.me.cosmicPrisonsCore.customItems.pickaxes;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.evasive.me.cosmicPrisonsCore.customItems.ItemMaker;
import org.evasive.me.cosmicPrisonsCore.utils.ComponentUtils;

import java.util.List;

public class StonePickaxe implements PickaxeBuilder{
    @Override
    public Component getName() {
        return ComponentUtils.legacy("Stone Pickaxe");
    }

    @Override
    public String getID() {
        return "STONE_PICKAXE";
    }

    @Override
    public Material getMaterial() {
        return Material.STONE_PICKAXE;
    }

    @Override
    public boolean isGlowing() {
        return false;
    }

    @Override
    public ItemStack getItem() {
        return ItemMaker.stonePickaxe;
    }

    @Override
    public ItemStack buildItem() {
        return PickaxeBuilder.super.buildItem();
    }

    @Override
    public int getBaseEnergyCap() {
        return 6000;
    }

    @Override
    public float getBaseSpeed() {
        return 5f;
    }

    @Override
    public int getRequiredMiningLevel() {
        return 30;
    }

    @Override
    public int getEnergyLevelIncrease() {
        return 10800;
    }

    @Override
    public List<Material> getTrackingMaterialOne() {
        return List.of(Material.LAPIS_ORE, Material.LAPIS_BLOCK);
    }

    @Override
    public List<Material> getTrackingMaterialTwo() {
        return null;
    }
}
