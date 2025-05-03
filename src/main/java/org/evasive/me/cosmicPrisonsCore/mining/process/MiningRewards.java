package org.evasive.me.cosmicPrisonsCore.mining.process;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.evasive.me.cosmicPrisonsCore.CosmicPrisonsCore;
import org.evasive.me.cosmicPrisonsCore.customItems.energy.EnergyItemModification;
import org.evasive.me.cosmicPrisonsCore.customItems.satchels.functions.SatchelFunctions;
import org.evasive.me.cosmicPrisonsCore.mining.levels.LevelFunctions;
import org.evasive.me.cosmicPrisonsCore.mining.ores.OreCreator;
import org.evasive.me.cosmicPrisonsCore.mining.ores.OreType;
import org.evasive.me.cosmicPrisonsCore.utils.ComponentUtils;
import org.evasive.me.cosmicPrisonsCore.utils.EnumNameConversions;
import org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.pickaxe.functions.*;

import java.util.AbstractList;

public class MiningRewards {

    public void handleBlockRewards(Player player, ItemStack pickaxe, OreCreator oreCreator) {
        getOreDrops(player, oreCreator);
        getEnergyDrop(player, oreCreator, pickaxe);
        getExperienceDrop(player, oreCreator);
        getGoodies(player, oreCreator);
    }

    private void getGoodies(Player player, OreCreator oreCreator) {
        //Shards and other special drops
        double chance = Math.random();
        if(chance > 0.01f * new ShardDiscovererFunctions().getShardDiscovererMulti(player.getInventory().getItemInMainHand()))
            return;
        double rarity = Math.random();
        ItemStack selectedItem = (rarity < 0.75f) ? oreCreator.getShards().get(0) : oreCreator.getShards().get(1);

        player.playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_HURT, 1f, 1f);
        if(player.getInventory().firstEmpty() == -1){
            player.getWorld().dropItemNaturally(player.getLocation(), selectedItem);
        }else{
            player.getInventory().addItem(selectedItem);
        }
    }

    private void getExperienceDrop(Player player, OreCreator oreCreator) {
        //Add check to see if player is mining an ore early and if so give the lower tier xp not the current ore
        //Subtract xp (from base) based on fracture progress like original cosmic prisons
        int base = oreCreator.getExperience();
        int superBreakerMulti = new SuperBreakerFunctions().calculateSuperbreakerMulti(player);
        CosmicPrisonsCore.playerLevelManager.addExperince(player.getUniqueId(), base * superBreakerMulti);
        new LevelFunctions().checkForLevelUp(player);
    }

    private void getEnergyDrop(Player player, OreCreator oreCreator, ItemStack itemStack) {
        int energyGain = CalculateEnergyGain(player, itemStack, oreCreator.getMaterial());
        float energyCollectorMulti = new EnergyCollectorFunctions().getEnergyCollectorMulti(itemStack);

        int totalEnergyGain = (int) (energyCollectorMulti * energyGain);

        new EnergyItemModification().addEnergy(player, itemStack.getItemMeta(), totalEnergyGain);
    }

    private int CalculateEnergyGain(Player player, ItemStack itemStack, Material material) {
        float miningSpeed = new MiningSpeedCalculations().calculateMiningSpeed(player, itemStack, material);
        return (int) (100 / miningSpeed);
    }

    public void getOreDrops(Player player, OreCreator oreCreator){
        boolean transfuse = false;
        double chance = Math.random();
        int amount = chance > 0.50 ? 2 : 3;
        if(new TransfuseFunctions().handleTransfuse(player, player.getInventory().getItemInMainHand(), oreCreator)){
            transfuse = true;
            oreCreator = new TransfuseFunctions().transfuseOres(oreCreator);
        }
        if(new AlchemyFunctions().handleAlchemy(player, player.getInventory().getItemInMainHand(), oreCreator, amount))
            return;
        oreDropAlert(player, oreCreator.getItemDrop(), amount, transfuse);
        addItemsToInventory(player, oreCreator, amount);
    }

    public void addItemsToInventory(Player player, OreCreator oreCreator, int amount){
        amount = new SatchelFunctions().addItemsToSatchel(player, player.getInventory(), oreCreator, amount);
        if(!checkForSlots(player.getInventory(), oreCreator))
            return;
        if(amount > 0) player.getInventory().addItem(new ItemStack(oreCreator.getItemDrop(), amount));
    }

    private boolean checkForSlots(PlayerInventory inventory, OreCreator oreCreator) {
        for (int slot = 0; slot < inventory.getStorageContents().length; slot++) {
            ItemStack item = inventory.getStorageContents()[slot];
            if (item == null || item.getType() == Material.AIR || item.getType().equals(oreCreator.getMaterial()) && !item.hasItemMeta() && item.getAmount() < 64) {
                return true;
            }
        }
        return false;
    }

    public void oreDropAlert(Player player, Material material, int blockAmount, boolean transfuse){
        String oreName = new EnumNameConversions().getOreName(material);
        player.sendActionBar(ComponentUtils.legacy(String.format("%s%s &a+%d", transfuse ? "&5" : "&f", oreName, blockAmount)));
    }





}
