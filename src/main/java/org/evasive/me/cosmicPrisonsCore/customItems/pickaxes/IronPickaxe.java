package org.evasive.me.cosmicPrisonsCore.customItems.pickaxes;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.evasive.me.cosmicPrisonsCore.customItems.ItemMaker;
import org.evasive.me.cosmicPrisonsCore.utils.ComponentUtils;

import java.util.List;

public class IronPickaxe implements PickaxeBuilder{
    @Override
    public int getBaseEnergyCap() {
        return 8400;
    }

    @Override
    public float getBaseSpeed() {
        return 4f;
    }

    @Override
    public int getRequiredMiningLevel() {
        return 70;
    }

    @Override
    public int getEnergyLevelIncrease() {
        return 13200;
    }

    @Override
    public List<Material> getTrackingMaterialOne() {
        return List.of(Material.GOLD_ORE, Material.GOLD_BLOCK);
    }

    @Override
    public List<Material> getTrackingMaterialTwo() {
        return List.of();
    }

    @Override
    public Component getName() {
        return ComponentUtils.legacy("Iron Pickaxe");
    }

    @Override
    public String getID() {
        return "IRON_PICKAXE";
    }

    @Override
    public Material getMaterial() {
        return Material.IRON_PICKAXE;
    }

    @Override
    public boolean isGlowing() {
        return false;
    }

    @Override
    public ItemStack getItem() {
        return ItemMaker.ironPickaxe;
    }
}
