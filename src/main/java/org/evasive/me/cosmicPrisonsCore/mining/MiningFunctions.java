package org.evasive.me.cosmicPrisonsCore.mining;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.evasive.me.cosmicPrisonsCore.CosmicPrisonsCore;
import org.evasive.me.cosmicPrisonsCore.customItems.ItemList;
import org.evasive.me.cosmicPrisonsCore.customItems.ItemMaker;
import org.evasive.me.cosmicPrisonsCore.customItems.pickaxes.PickaxeBuilder;
import org.evasive.me.cosmicPrisonsCore.customItems.satchels.SatchelBuilder;
import org.evasive.me.cosmicPrisonsCore.enchanting.EnchantFunctions;
import org.evasive.me.cosmicPrisonsCore.enchanting.mining.PickaxeEnchants;
import org.evasive.me.cosmicPrisonsCore.keys.ItemKeyFunctions;
import org.evasive.me.cosmicPrisonsCore.mining.data.BlockRespawnData;
import org.evasive.me.cosmicPrisonsCore.mining.levels.Equations;
import org.evasive.me.cosmicPrisonsCore.mining.ores.OreCreator;
import org.evasive.me.cosmicPrisonsCore.mining.ores.OreType;
import org.evasive.me.cosmicPrisonsCore.mining.records.BlockPos;
import org.evasive.me.cosmicPrisonsCore.player.PlayerData;
import org.evasive.me.cosmicPrisonsCore.utils.ComponentUtils;

import java.util.List;
import java.util.Random;

import static org.evasive.me.cosmicPrisonsCore.keys.ItemKeyFunctions.*;
import static org.evasive.me.cosmicPrisonsCore.keys.ItemKeys.*;

public class MiningFunctions {

    public void BreakBlock(Player player, Material material, BlockPos blockPos, ItemStack pickaxe){
        OreCreator ore;
        boolean transfuse = false;
        if(new EnchantFunctions().checkTransfuseChance(pickaxe.getItemMeta()) && material != Material.EMERALD_BLOCK && material != Material.EMERALD_ORE){
            player.playSound(player.getLocation(), Sound.BLOCK_BREWING_STAND_BREW, 1f, 1f);
            ore = OreType.values()[OreType.valueOf(material.name()).ordinal() + 2].getOreCreator();
            transfuse = true;
        }else{
            ore = OreType.valueOf(material.name()).getOreCreator();
        }

        int blockDrops = blockDrop(player, ore, pickaxe);
        int experience = addExperience(player, ore.getExperience(), pickaxe);
        int energy = addEnergy(player, material, pickaxe);
        actionBar(player, experience, blockDrops, ore.getItemDrop(), transfuse);
        handleRespawn(OreType.valueOf(material.name()).getOreCreator(), blockPos, pickaxe);
        Bukkit.getWorld(blockPos.worldName()).playSound(player, Sound.BLOCK_STONE_BREAK, 1, 1);
    }

    public int addExperience(Player player, Integer base, ItemStack pickaxe){
        CosmicPrisonsCore.getPlayerLevelManager().addExperince(player.getUniqueId(), base);
        checkForLevelUp(player);
        return base;
    }

    public int addEnergy(Player player, Material material, ItemStack pickaxe){
        ItemStack heldItem = player.getInventory().getItemInMainHand();
        //HAVE TO CHECK FOR PICKAXE WHEN ADDING SATCHELS
        ItemKeyFunctions pickaxeFunctions = new ItemKeyFunctions();
        ItemMeta itemMeta = heldItem.getItemMeta();
        if(!hasKey(itemMeta, itemIDKey))
            return 0;

        Random random = new Random();
        int energyGain = (int) (100 / calculateEfficiency(player, heldItem, material));

        if(new EnchantFunctions().hasEnchant(pickaxe.getItemMeta(), PickaxeEnchants.ENERGY_COLLECTOR)){

            int level = new EnchantFunctions().getEnchantLevel(pickaxe.getItemMeta(), PickaxeEnchants.ENERGY_COLLECTOR);
            energyGain = (int) (energyGain * (1 + (0.2f * level)));

        }

        if((getEnergy(itemMeta) + energyGain) > getEnergyCap(itemMeta)){
            setEnergy(itemMeta, getEnergyCap(itemMeta));
        }else{
            pickaxeFunctions.addEnergy(itemMeta, energyGain);
        }
        heldItem.setItemMeta(itemMeta);;
        PickaxeBuilder pickaxeBuilder = (PickaxeBuilder) ItemList.valueOf(getID(itemMeta)).getItemBuilder();
        player.getInventory().setItemInMainHand(pickaxeBuilder.buildItem(itemMeta));
        return energyGain;
    }

    public void checkForLevelUp(Player player){
        PlayerData playerData = CosmicPrisonsCore.playerLevelManager.getPlayerData(player.getUniqueId());
        int experienceLevel = new Equations().getLevelExperience(playerData.getLevel() + 1);
        if(experienceLevel > playerData.getExperience())
            return;
        while (experienceLevel <= playerData.getExperience()){
            playerData.setLevel(playerData.getLevel() + 1);
            player.playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_HURT, 1.0f, 0.8f);
            if(CosmicPrisonsCore.playerLevelManager.getPlayerData(player.getUniqueId()).getLevel() % 5 == 0)
                Bukkit.getServer().sendMessage(ComponentUtils.legacy(player.getName()+" has reached level " + playerData.getLevel()));
            experienceLevel = new Equations().getLevelExperience(playerData.getLevel() + 1);
        }


    }

    public int blockDrop(Player player, OreCreator ore, ItemStack pickaxe){
        int amount;
        boolean oreMag = new EnchantFunctions().hasEnchant(pickaxe.getItemMeta(), PickaxeEnchants.ORE_MAGNET);


        double num = Math.random();
        if(num < 0.5){
            amount = 2;
        }else if(num < (1.0f - (new EnchantFunctions().getEnchantLevel(pickaxe.getItemMeta(), PickaxeEnchants.ORE_MAGNET) * 0.05f)) && oreMag){
            amount = 3;
        }else if (oreMag){
            amount = 4;
        }else{
            amount = 3;
        }

        int tempAmount = amount;
        PlayerInventory inventory = player.getInventory();
        for (int slot = 0; slot < inventory.getSize(); slot++) {
            ItemStack item = inventory.getItem(slot);
            if (item == null)
                continue;
            if(item.getType() == Material.AIR)
                continue;
            if(!item.hasItemMeta())
                continue;
            ItemMeta meta = item.getItemMeta();
            if(!hasKey(meta, itemIDKey))
                continue;
            if(!(ore.getItemDrop().name() + "_SATCHEL").equals(getID(meta)))
                continue;
            if(getOreStorage(meta) >= getOreCap(meta))
                continue;
            int cap = getOreCap(meta) - getOreStorage(meta);
            if(cap < tempAmount){
                addOreStorage(meta, cap);
                ItemList.valueOf(ore.getItemDrop().name() + "_SATCHEL").getItemBuilder().rebuild(meta, null);
                item.setItemMeta(meta);
                player.getInventory().addItem(new ItemStack(ore.getMaterial(), tempAmount - cap));
                tempAmount -= cap;
            }else{
                addOreStorage(meta, tempAmount);
                ItemList.valueOf(ore.getItemDrop().name() + "_SATCHEL").getItemBuilder().rebuild(meta, null);
                item.setItemMeta(meta);
                return amount;
            }
        }

        player.getInventory().addItem(new ItemStack(ore.getMaterial(), tempAmount));
        return amount;
    }

    public void actionBar(Player player, int experience, int blockAmount, Material dropMaterial, boolean transfuse){
        List<String> nameParts = List.of(dropMaterial.name().split("_"));
        StringBuilder oreName = new StringBuilder();
        for (String part : nameParts){
            oreName.append(part.substring(0, 1).toUpperCase()).append(part.substring(1).toLowerCase());
            if(!part.equals(nameParts.getLast()))
                oreName.append(" ");
        }
        String color = "&f";
        if(transfuse)
            color = "&5";
        player.sendActionBar(ComponentUtils.legacy(color+oreName+" &a+"+blockAmount));
    }

    public float calculateEfficiency(Player player, ItemStack itemInMainHand, Material material) {
        float progress = 0;
        OreCreator ore = OreType.valueOf(material.name()).getOreCreator();
        PickaxeBuilder pickaxeBuilder = (PickaxeBuilder) ItemList.valueOf(getID(itemInMainHand.getItemMeta())).getItemBuilder();
        progress += pickaxeBuilder.getBaseSpeed();
        if(new EnchantFunctions().hasEnchant(itemInMainHand.getItemMeta(), PickaxeEnchants.EFFICIENCY))
            progress *= (1 + (0.20f * new EnchantFunctions().getEnchantLevel(itemInMainHand.getItemMeta(), PickaxeEnchants.EFFICIENCY)));
        //Add some sort of check that makes the efficiency 1 if not 5 levels before required level & pickaxe < 20
        return progress / ore.getHardness();
    }

    public float getBaseSpeed(ItemStack itemInMainHand, Material material){
        float progress = 0;
        OreCreator ore = OreType.valueOf(material.name()).getOreCreator();
        PickaxeBuilder pickaxeBuilder = (PickaxeBuilder) ItemList.valueOf(getID(itemInMainHand.getItemMeta())).getItemBuilder();
        progress += pickaxeBuilder.getBaseSpeed();
        return progress / ore.getHardness();
    }

    public void handleRespawn(OreCreator ore,BlockPos blockPos, ItemStack pickaxe){
        Material nextSpawn = ore.getMaterial();
        if (Math.random() > 0.99f) {
            nextSpawn = ore.getRefinedMaterial();
        }
        CosmicPrisonsCore.blockRespawnMap.addBlockRespawn(System.currentTimeMillis() + (1000L * ore.getRespawnTime()), new BlockRespawnData(nextSpawn, ore.getRefinedMaterial(), blockPos));
    }

}
