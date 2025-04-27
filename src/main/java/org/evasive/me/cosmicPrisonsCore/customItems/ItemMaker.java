package org.evasive.me.cosmicPrisonsCore.customItems;

import org.bukkit.inventory.ItemStack;
import org.evasive.me.cosmicPrisonsCore.customItems.energy.CosmicEnergy;


public class ItemMaker {

    public static ItemStack woodenPickaxe;
    public static ItemStack stonePickaxe;
    public static ItemStack goldenPickaxe;
    public static ItemStack ironPickaxe;
    public static ItemStack diamondPickaxe;
    public static ItemStack simpleShard;
    public static ItemStack cosmicEnergy;


    public void init(){
        woodenPickaxe = ItemList.WOODEN_PICKAXE.getItemBuilder().buildItem();
        stonePickaxe = ItemList.STONE_PICKAXE.getItemBuilder().buildItem();
        goldenPickaxe = ItemList.GOLDEN_PICKAXE.getItemBuilder().buildItem();
        ironPickaxe = ItemList.IRON_PICKAXE.getItemBuilder().buildItem();
        diamondPickaxe = ItemList.DIAMOND_PICKAXE.getItemBuilder().buildItem();
        simpleShard = ItemList.SIMPLE_SHARD.getItemBuilder().buildItem();
        cosmicEnergy = ItemList.COSMIC_ENERGY.getItemBuilder().buildItem();
    }

    public ItemStack createEnergyStack(int amount, String name){
        return new CosmicEnergy().buildItem(amount, name);
    }

    public ItemStack createEnergyStack(String name){
        return new CosmicEnergy().buildItem(1, name);
    }
}
