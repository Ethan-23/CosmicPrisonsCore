package org.evasive.me.cosmicPrisonsCore.mining;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.evasive.me.cosmicPrisonsCore.CosmicPrisonsCore;
import org.evasive.me.cosmicPrisonsCore.customItems.ItemList;
import org.evasive.me.cosmicPrisonsCore.customItems.pickaxes.PickaxeBuilder;
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

import static org.evasive.me.cosmicPrisonsCore.keys.ItemKeys.*;

public class MiningFunctions {

    public void BreakBlock(Player player, Material material, BlockPos blockPos){
        OreCreator ore = OreType.valueOf(material.name()).getOreCreator();
        int blockDrops = blockDrop(player, ore.getItemDrop());
        int experience = addExperience(player, ore.getExperience());
        int energy = addEnergy(player, material);
        actionBar(player, experience, blockDrops, ore.getItemDrop());
        handleRespawn(ore, blockPos);
        Bukkit.getWorld(blockPos.worldName()).playSound(player, Sound.BLOCK_STONE_BREAK, 1, 1);
    }

    public int addExperience(Player player, Integer base){
        CosmicPrisonsCore.getPlayerLevelManager().addExperince(player.getUniqueId(), base);
        checkForLevelUp(player);
        return base;
    }

    public int addEnergy(Player player, Material material){
        ItemStack heldItem = player.getInventory().getItemInMainHand();
        //HAVE TO CHECK FOR PICKAXE WHEN ADDING SATCHELS
        ItemKeyFunctions pickaxeFunctions = new ItemKeyFunctions();
        ItemMeta itemMeta = heldItem.getItemMeta();
        if(!pickaxeFunctions.hasKey(itemMeta, itemIDKey))
            return 0;

        Random random = new Random();
        int energyGain = (int)((random.nextInt(11) + 60)/ calculateEfficiency(player, heldItem, material));
        if((pickaxeFunctions.getEnergy(itemMeta) + energyGain) > pickaxeFunctions.getEnergyCap(itemMeta)){
            pickaxeFunctions.setEnergy(itemMeta, pickaxeFunctions.getEnergyCap(itemMeta));
        }else{
            pickaxeFunctions.addEnergy(itemMeta, energyGain);
        }
        heldItem.setItemMeta(itemMeta);;
        PickaxeBuilder pickaxeBuilder = (PickaxeBuilder) ItemList.valueOf(pickaxeFunctions.getID(itemMeta)).getItemBuilder();
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

    public int blockDrop(Player player, Material material){
        int amount;
        if(Math.random() < 0.5){
            amount = 2;
        }else{
            amount = 3;
        }
        player.getInventory().addItem(new ItemStack(material, amount));
        return amount;
    }

    public void actionBar(Player player, int experience, int blockAmount, Material dropMaterial){
        List<String> nameParts = List.of(dropMaterial.name().split("_"));
        StringBuilder oreName = new StringBuilder();
        for (String part : nameParts){
            oreName.append(part.substring(0, 1).toUpperCase()).append(part.substring(1).toLowerCase());
            if(!part.equals(nameParts.getLast()))
                oreName.append(" ");
        }
        player.sendActionBar(ComponentUtils.legacy("&f"+oreName+" &a+"+blockAmount));
    }

    public float calculateEfficiency(Player player, ItemStack itemInMainHand, Material material) {
        float efficiency = 0;
        ItemKeyFunctions pickaxeFunctions = new ItemKeyFunctions();
        OreCreator ore = OreType.valueOf(material.name()).getOreCreator();
        PickaxeBuilder pickaxeBuilder = (PickaxeBuilder) ItemList.valueOf(pickaxeFunctions.getID(itemInMainHand.getItemMeta())).getItemBuilder();
        efficiency += pickaxeBuilder.getBaseSpeed();
        //Add some sort of check that makes the efficiency 1 if not 5 levels before required level & pickaxe < 20
        return efficiency / ore.getHardness();
    }

    public void handleRespawn(OreCreator ore,BlockPos blockPos){
        Material nextSpawn = ore.getMaterial();
        if (Math.random() > 0.99f) {
            nextSpawn = ore.getRefinedMaterial();
        }
        CosmicPrisonsCore.blockRespawnMap.addBlockRespawn(System.currentTimeMillis() + (1000L * ore.getRespawnTime()), new BlockRespawnData(nextSpawn, ore.getRefinedMaterial(), blockPos));
    }

}
