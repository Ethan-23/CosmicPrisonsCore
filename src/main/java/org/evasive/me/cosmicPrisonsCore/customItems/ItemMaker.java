package org.evasive.me.cosmicPrisonsCore.customItems;

import org.bukkit.entity.Item;
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
    public static ItemStack coalOreSatchel;
    public static ItemStack coalSatchel;
    public static ItemStack ironOreSatchel;
    public static ItemStack ironSatchel;
    public static ItemStack lapisOreSatchel;
    public static ItemStack lapisSatchel;
    public static ItemStack redstoneOreSatchel;
    public static ItemStack redstoneSatchel;
    public static ItemStack goldOreSatchel;
    public static ItemStack goldSatchel;
    public static ItemStack diamondOreSatchel;
    public static ItemStack diamondSatchel;
    public static ItemStack emeraldOreSatchel;
    public static ItemStack emeraldSatchel;


    public void init(){
        woodenPickaxe = ItemList.WOODEN_PICKAXE.getItemBuilder().buildItem();
        stonePickaxe = ItemList.STONE_PICKAXE.getItemBuilder().buildItem();
        goldenPickaxe = ItemList.GOLDEN_PICKAXE.getItemBuilder().buildItem();
        ironPickaxe = ItemList.IRON_PICKAXE.getItemBuilder().buildItem();
        diamondPickaxe = ItemList.DIAMOND_PICKAXE.getItemBuilder().buildItem();
        simpleShard = ItemList.SIMPLE_SHARD.getItemBuilder().buildItem();
        cosmicEnergy = ItemList.COSMIC_ENERGY.getItemBuilder().buildItem();
        coalOreSatchel = ItemList.COAL_ORE_SATCHEL.getItemBuilder().buildItem();
        coalSatchel = ItemList.COAL_SATCHEL.getItemBuilder().buildItem();
        ironOreSatchel = ItemList.IRON_ORE_SATCHEL.getItemBuilder().buildItem();
        ironSatchel = ItemList.IRON_SATCHEL.getItemBuilder().buildItem();
        lapisOreSatchel = ItemList.LAPIS_ORE_SATCHEL.getItemBuilder().buildItem();
        lapisSatchel = ItemList.LAPIS_SATCHEL.getItemBuilder().buildItem();
        redstoneOreSatchel = ItemList.REDSTONE_ORE_SATCHEL.getItemBuilder().buildItem();
        redstoneSatchel = ItemList.REDSTONE_SATCHEL.getItemBuilder().buildItem();
        goldOreSatchel = ItemList.GOLD_ORE_SATCHEL.getItemBuilder().buildItem();
        goldSatchel = ItemList.GOLD_SATCHEL.getItemBuilder().buildItem();
        diamondOreSatchel = ItemList.DIAMOND_ORE_SATCHEL.getItemBuilder().buildItem();
        diamondSatchel = ItemList.DIAMOND_SATCHEL.getItemBuilder().buildItem();
        emeraldOreSatchel = ItemList.EMERALD_ORE_SATCHEL.getItemBuilder().buildItem();
        emeraldSatchel = ItemList.EMERALD_SATCHEL.getItemBuilder().buildItem();
    }

    public ItemStack createEnergyStack(int amount, String name){
        return new CosmicEnergy().buildItem(amount, name);
    }

    public ItemStack createEnergyStack(String name){
        return new CosmicEnergy().buildItem(1, name);
    }
}
