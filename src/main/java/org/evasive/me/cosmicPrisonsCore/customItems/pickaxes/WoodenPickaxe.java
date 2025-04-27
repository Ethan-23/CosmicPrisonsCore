package org.evasive.me.cosmicPrisonsCore.customItems.pickaxes;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.evasive.me.cosmicPrisonsCore.customItems.ItemList;
import org.evasive.me.cosmicPrisonsCore.customItems.ItemMaker;
import org.evasive.me.cosmicPrisonsCore.utils.ComponentUtils;

import java.util.List;

public class WoodenPickaxe implements PickaxeBuilder{
    @Override
    public Component getName() {
        return ComponentUtils.legacy("&fWooden Pickaxe");
    }

    @Override
    public String getID() {
        return "WOODEN_PICKAXE";
    }

    @Override
    public Material getMaterial() {
        return Material.WOODEN_PICKAXE;
    }

    @Override
    public boolean isGlowing() {
        return false;
    }

    @Override
    public ItemStack getItem() {
        return ItemMaker.woodenPickaxe;
    }

    @Override
    public ItemStack buildItem() {
        return PickaxeBuilder.super.buildItem();
    }

    @Override
    public int getBaseEnergyCap() {
        return 4800;
    }

    @Override
    public float getBaseSpeed() {
        return 1f;
    }

    @Override
    public int getRequiredMiningLevel() {
        return 1;
    }

    @Override
    public int getEnergyLevelIncrease() {
        return 9800;
    }

    @Override
    public List<Material> getTrackingMaterialOne() {
        return List.of(Material.IRON_ORE, Material.IRON_BLOCK);
    }

    @Override
    public List<Material> getTrackingMaterialTwo() {
        return null;
    }
}
