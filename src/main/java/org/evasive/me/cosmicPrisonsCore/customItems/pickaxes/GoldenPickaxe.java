package org.evasive.me.cosmicPrisonsCore.customItems.pickaxes;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.evasive.me.cosmicPrisonsCore.customItems.ItemMaker;
import org.evasive.me.cosmicPrisonsCore.utils.ComponentUtils;

import java.util.List;

public class GoldenPickaxe implements PickaxeBuilder{
    @Override
    public int getBaseEnergyCap() {
        return 7200;
    }

    @Override
    public float getBaseSpeed() {
        return 3f;
    }

    @Override
    public int getRequiredMiningLevel() {
        return 50;
    }

    @Override
    public int getEnergyLevelIncrease() {
        return 12000;
    }

    @Override
    public List<Material> getTrackingMaterialOne() {
        return List.of(Material.REDSTONE_ORE, Material.REDSTONE_BLOCK);
    }

    @Override
    public List<Material> getTrackingMaterialTwo() {
        return List.of();
    }

    @Override
    public Component getName() {
        return ComponentUtils.legacy("Golden Pickaxe");
    }

    @Override
    public String getID() {
        return "GOLDEN_PICKAXE";
    }

    @Override
    public Material getMaterial() {
        return Material.GOLDEN_PICKAXE;
    }

    @Override
    public boolean isGlowing() {
        return false;
    }

    @Override
    public ItemStack getItem() {
        return ItemMaker.goldenPickaxe;
    }
}
