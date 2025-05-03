//package org.evasive.me.cosmicPrisonsCore.mining.animation;
//
//import org.bukkit.Bukkit;
//import org.bukkit.Material;
//import org.bukkit.Sound;
//import org.bukkit.entity.Player;
//import org.bukkit.inventory.Inventory;
//import org.bukkit.inventory.ItemStack;
//import org.bukkit.inventory.meta.ItemMeta;
//import org.evasive.me.cosmicPrisonsCore.CosmicPrisonsCore;
//import org.evasive.me.cosmicPrisonsCore.customItems.ItemList;
//import org.evasive.me.cosmicPrisonsCore.customItems.pickaxes.PickaxeBuilder;
//import org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.PickaxeEnchantFunctions;
//import org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.pickaxe.PickaxeEnchants;
//import org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.pickaxe.functions.*;
//import org.evasive.me.cosmicPrisonsCore.keys.ItemKeyFunctions;
//import org.evasive.me.cosmicPrisonsCore.mining.respawn.BlockRespawnData;
//import org.evasive.me.cosmicPrisonsCore.mining.levels.Equations;
//import org.evasive.me.cosmicPrisonsCore.mining.ores.OreCreator;
//import org.evasive.me.cosmicPrisonsCore.mining.ores.OreType;
//import org.evasive.me.cosmicPrisonsCore.mining.records.BlockPos;
//import org.evasive.me.cosmicPrisonsCore.player.PlayerData;
//import org.evasive.me.cosmicPrisonsCore.utils.ComponentUtils;
//
//import java.util.List;
//import java.util.Random;
//
//import static org.evasive.me.cosmicPrisonsCore.keys.ItemKeyFunctions.*;
//import static org.evasive.me.cosmicPrisonsCore.keys.ItemKeys.*;
//
//public class MiningFunctions {
//
////    public void BreakBlock(Player player, Material material, BlockPos blockPos, ItemStack pickaxe){
////        OreCreator ore = new TransfuseFunctions().checkForTransfuse(player, pickaxe, material);
////        if(material == ore.getRefinedMaterial())
////            refinedRespawnCancel(blockPos, ore);
////        int dropAmount = determineBlockDrop(pickaxe);
////        boolean alchemy = new AlchemyFunctions().checkForAlchemy(pickaxe, ore, dropAmount, player);
////        boolean inventoryFull = false;
////        if(!alchemy)
////            inventoryFull = inventoryRoomCheck(player, player.getInventory(), ore, dropAmount);
////        new PickaxeEnchantFunctions().handleBreakingEnchants(player, pickaxe.getItemMeta());
////
////        //EXPERIENCE & ENERGY FOR ACTION BAR TOGGLE
////        int experience = addExperience(player, ore.getExperience(), pickaxe);
////        int energy = addEnergy(player, material, pickaxe);
////        checkForShardDrop(player, ore, pickaxe);
////        actionBar(player, dropAmount, ore.getItemDrop(), material != ore.getMaterial(), alchemy, inventoryFull);
////
////        handleRespawn(OreType.valueOf(OreType.valueOf(material.name()).getOreCreator().getRespawnMaterial().name()).getOreCreator(), blockPos);
////        player.playSound(player.getLocation(), Sound.BLOCK_STONE_BREAK, 1, 1);
////    }
//
//
//    private void checkForShardDrop(Player player, OreCreator ore, ItemStack pickaxe) {
//        PickaxeEnchantFunctions pickaxeEnchantFunctions = new PickaxeEnchantFunctions();
//        double num = Math.random();
//        int level = new ShardDiscovererFunctions().checkForShardDiscoverer(pickaxe);
//        if(num > (0.01f * ((level * .2f) + 1f)))
//            return;
//        Random random = new Random();
//        double chance = random.nextDouble();
//        ItemStack selectedItem = (chance < 0.75) ? ore.getShards().get(0) : ore.getShards().get(1);
//
//        double doubleShards = Math.random();
//        if(level == 5 && doubleShards < 0.05f)
//            selectedItem.setAmount(2);
//
//        Inventory inventory = player.getInventory();
//        player.playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_HURT, 1f, 1f);
//        if (inventory.firstEmpty() != -1) {
//            inventory.addItem(selectedItem);
//        } else {
//            player.getWorld().dropItemNaturally(player.getLocation(), selectedItem);
//            //LATER WORRY ABOUT DROP TAGS
//        }
//    }
//
//    public void refinedRespawnCancel(BlockPos blockPos, OreCreator ore){
//        long startTime = System.currentTimeMillis();
//        BlockRespawnData blockRespawnData = new BlockRespawnData(ore.getRespawnMaterial(), ore.getRefinedMaterial(), blockPos);
//        for (long currentTime = startTime; currentTime < startTime + 10000; currentTime+=1000) {
//            if(!CosmicPrisonsCore.blockRespawnMap.hasThisTime(currentTime))
//                continue;
//            if(!CosmicPrisonsCore.blockRespawnMap.getRespawnSet(currentTime).contains(blockRespawnData))
//                continue;
//            CosmicPrisonsCore.blockRespawnMap.getRespawnSet(currentTime).remove(blockRespawnData);
//            break;
//        }
//
//    }
//
//    public int addExperience(Player player, Integer base, ItemStack pickaxe){
//        //PICKAXE USED FOR WARP MINER
//        int superBreakerMulti = CosmicPrisonsCore.superBreakerMap.hasDoubleXP(player) ? 2 : 1;
//        CosmicPrisonsCore.getPlayerLevelManager().addExperince(player.getUniqueId(), base * superBreakerMulti);
//        checkForLevelUp(player);
//        return base;
//    }
//
//    public int addEnergy(Player player, Material material, ItemStack pickaxe){
//        //HAVE TO CHECK FOR PICKAXE WHEN ADDING SATCHELS
//        ItemMeta itemMeta = pickaxe.getItemMeta();
//        if(!hasKey(itemMeta, itemIDKey))
//            return 0;
//
//        int energyGain = (int) (100 / calculateEfficiency(player, pickaxe, material));
//
//        energyGain = new EnergyCollectorFunctions().checkForEnergyCollector(energyGain, pickaxe);
//
//        if((getEnergy(itemMeta) + energyGain) > getEnergyCap(itemMeta)){
//            setEnergy(itemMeta, getEnergyCap(itemMeta));
//        }else{
//            ItemKeyFunctions.addEnergy(itemMeta, energyGain);
//        }
//        pickaxe.setItemMeta(itemMeta);
//        PickaxeBuilder pickaxeBuilder = (PickaxeBuilder) ItemList.valueOf(getID(itemMeta)).getItemBuilder();
//        player.getInventory().setItemInMainHand(pickaxeBuilder.buildItem(itemMeta));
//        return energyGain;
//    }
//
//    public void checkForLevelUp(Player player){
//        PlayerData playerData = CosmicPrisonsCore.playerLevelManager.getPlayerData(player.getUniqueId());
//        int experienceLevel = new Equations().getLevelExperience(playerData.getLevel() + 1);
//        if(experienceLevel > playerData.getExperience())
//            return;
//        while (experienceLevel <= playerData.getExperience()){
//            playerData.setLevel(playerData.getLevel() + 1);
//            player.playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_HURT, 1.0f, 0.8f);
//            if(CosmicPrisonsCore.playerLevelManager.getPlayerData(player.getUniqueId()).getLevel() % 5 == 0)
//
//                Bukkit.getServer().sendMessage(ComponentUtils.legacy(String.format("%s has reached level %d", player.getName(), playerData.getLevel())));
//            experienceLevel = new Equations().getLevelExperience(playerData.getLevel() + 1);
//        }
//
//
//    }
//
//    public int determineBlockDrop(ItemStack pickaxe){
//        int oreMagLevel = new PickaxeEnchantFunctions().getEnchantLevel(pickaxe.getItemMeta(), PickaxeEnchants.ORE_MAGNET);
//        int amount;
//        double num = Math.random();
//        if(num < 0.5){
//            amount = 2;
//        }else if(oreMagLevel > 0 && num < (1.0f - (oreMagLevel * 0.05f))){
//            amount = 3;
//        }else if (oreMagLevel > 0){
//            amount = 4;
//        }else{
//            amount = 3;
//        }
//        return amount;
//    }
//
//    public boolean inventoryRoomCheck(Player player, Inventory inventory, OreCreator ore, int amount){
//        int empty = 0;
//        for (int slot = 0; slot < inventory.getStorageContents().length; slot++) {
//            if(amount == 0)
//                continue;
//            ItemStack item = inventory.getStorageContents()[slot];
//            if(item == null || item.getType() == Material.AIR || item.getType().equals(ore.getMaterial()) && !item.hasItemMeta() && item.getAmount() < 64){
//                empty++;
//                continue;
//            }
//            String satchelName = String.format("%s%s",ore.getItemDrop().name().replace("_INGOT", ""), "_SATCHEL");
//            //BROKEN???
//            if(!item.hasItemMeta() || !hasKey(item.getItemMeta(), itemIDKey) || !(satchelName).equals(getID(item.getItemMeta())))
//                continue;
//            ItemMeta meta = item.getItemMeta();
//            if(getOreStorage(meta) >= getOreCap(meta))
//                continue;
//            int cap = getOreCap(meta) - getOreStorage(meta);
//            if(cap < amount){
//                addOreStorage(meta, cap);
//                amount -= cap;
//            }else{
//                addOreStorage(meta, amount);
//                amount = 0;
//            }
//            ItemList.valueOf(satchelName).getItemBuilder().rebuild(meta, null);
//            item.setItemMeta(meta);
//        }
//        if(empty == 0 && amount != 0)
//            return true;
//        if(amount > 1)
//            player.getInventory().addItem(new ItemStack(ore.getItemDrop(), amount));
//        return false;
//    }
//
//    public void actionBar(Player player, int blockAmount, Material dropMaterial, boolean transfuse, boolean alchemy, boolean inventoryFull){
//        List<String> nameParts = List.of(dropMaterial.name().split("_"));
//        StringBuilder oreName = new StringBuilder();
//        for (String part : nameParts){
//            oreName.append(part.substring(0, 1).toUpperCase()).append(part.substring(1).toLowerCase());
//            if(!part.equals(nameParts.getLast()))
//                oreName.append(" ");
//        }
//        if(alchemy){
//            //INSERT MONEY AMOUNT HERE
//
//            player.sendActionBar(ComponentUtils.legacy(String.format("Sold &a%d &f%s &ffor &a$0", blockAmount, oreName)));
//        }
//        else if(inventoryFull){
//            player.sendActionBar(ComponentUtils.legacy(String.format("&f%s &c(%d lost - full inventory)", oreName, blockAmount)));
//        }else{
//            player.sendActionBar(ComponentUtils.legacy(String.format("%s%s &a+%d", transfuse ? "&5" : "&f", oreName, blockAmount)));
//        }
//
//    }
//
//    public float calculateEfficiency(Player player, ItemStack pickaxe, Material material) {
//        OreCreator ore = OreType.valueOf(material.name()).getOreCreator();
//        PickaxeBuilder pickaxeBuilder = (PickaxeBuilder) ItemList.valueOf(getID(pickaxe.getItemMeta())).getItemBuilder();
//        //Add some sort of check that makes the efficiency 1 if not 5 levels before required level & pickaxe < 20
//        boolean superBreaker = CosmicPrisonsCore.superBreakerMap.hasSuperBreaker(player);
//        return new EfficiencyFunctions().checkForEfficiency(pickaxeBuilder.getBaseSpeed(), pickaxe) * (superBreaker ? 2 : 1) / ore.getHardness();
//    }
//
//    public float calculateFractureEfficiency(Player player, ItemStack pickaxe, Material material){
//        OreCreator ore = OreType.valueOf(material.name()).getOreCreator();
//        PickaxeBuilder pickaxeBuilder = (PickaxeBuilder) ItemList.valueOf(getID(pickaxe.getItemMeta())).getItemBuilder();
//        //Add some sort of check that makes the efficiency 1 if not 5 levels before required level & pickaxe < 20
//        return new FractureFunctions().checkForFractureSpeed(pickaxeBuilder.getBaseSpeed(), pickaxe) / ore.getHardness();
//    }
//
//    public float getBaseSpeed(ItemStack itemInMainHand, Material material){
//        float progress = 0;
//        OreCreator ore = OreType.valueOf(material.name()).getOreCreator();
//        PickaxeBuilder pickaxeBuilder = (PickaxeBuilder) ItemList.valueOf(getID(itemInMainHand.getItemMeta())).getItemBuilder();
//        progress += pickaxeBuilder.getBaseSpeed();
//        return progress / ore.getHardness();
//    }
//
//    public void handleRespawn(OreCreator ore,BlockPos blockPos){
//        Material nextSpawn = ore.getRespawnMaterial();
//        if (Math.random() > 0.99f && Bukkit.getWorld(blockPos.worldName()).getBlockAt(blockPos.x(), blockPos.y(), blockPos.z()).getType() == Material.STONE) {
//            nextSpawn = ore.getRefinedMaterial();
//        }
//        CosmicPrisonsCore.blockRespawnMap.addBlockRespawn(System.currentTimeMillis() + (1000L * ore.getRespawnTime()), new BlockRespawnData(nextSpawn, ore.getRefinedMaterial(), blockPos));
//    }
//
//}
