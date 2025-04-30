package org.evasive.me.cosmicPrisonsCore.enchanting;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.evasive.me.cosmicPrisonsCore.enchanting.mining.PickaxeEnchants;
import org.evasive.me.cosmicPrisonsCore.mining.ores.OreCreator;
import org.evasive.me.cosmicPrisonsCore.mining.ores.OreType;
import org.evasive.me.cosmicPrisonsCore.utils.EnchantUtil;

import java.util.Map;
import java.util.Random;

import static org.evasive.me.cosmicPrisonsCore.keys.ItemKeyFunctions.getEnchantMap;

public class EnchantFunctions {

    private final Random RANDOM = new Random();

    public boolean hasEnchant(ItemMeta meta, PickaxeEnchants pickaxeEnchants){
        Map<PickaxeEnchants, Integer> enchantMap = new EnchantUtil().stringToEnumMap(getEnchantMap(meta), PickaxeEnchants.class);
        return enchantMap.containsKey(pickaxeEnchants);
    }

    public int getEnchantLevel(ItemMeta meta, PickaxeEnchants pickaxeEnchants){
        Map<PickaxeEnchants, Integer> enchantMap = new EnchantUtil().stringToEnumMap(getEnchantMap(meta), PickaxeEnchants.class);
        return enchantMap.get(pickaxeEnchants);
    }

    public void handleBreakingEnchants(Player player, ItemMeta meta) {

        checkFeedChance(player, meta);

    }

    public void checkFeedChance(Player player, ItemMeta meta){
        if(hasEnchant(meta, PickaxeEnchants.FEED) && RANDOM.nextFloat() < 0.05 && player.getFoodLevel() < 20){

            player.playSound(player.getLocation(), Sound.ENTITY_GENERIC_EAT, 1f, 1f);
            player.spawnParticle(Particle.ITEM, player.getLocation().add(0f, 1.5f, 0f), 10, 0.2, 0.2, 0.2, 0.05, new ItemStack(Material.COOKED_BEEF));
            player.setFoodLevel(20);
            player.setSaturation(getEnchantLevel(meta, PickaxeEnchants.FEED) * 15);
        }
    }

    public boolean checkFractureChance(ItemMeta meta) {
        if(!hasEnchant(meta, PickaxeEnchants.FRACTURE))
            return false;
        int level = getEnchantLevel(meta, PickaxeEnchants.FRACTURE);
        int number = RANDOM.nextInt(15) + 1;
        return level >= number;
    }

    public boolean checkTransfuseChance(ItemMeta meta) {
        if(!hasEnchant(meta, PickaxeEnchants.TRANSFUSE))
            return false;
        int level = getEnchantLevel(meta, PickaxeEnchants.TRANSFUSE);
        return RANDOM.nextFloat() < 0.033f * level;
    }

    public OreCreator checkForTransfuse(Player player, ItemStack pickaxe, Material material) {
        if(!hasEnchant(pickaxe.getItemMeta(), PickaxeEnchants.TRANSFUSE))
            return OreType.valueOf(material.name()).getOreCreator();
        if (new EnchantFunctions().checkTransfuseChance(pickaxe.getItemMeta()) && OreType.valueOf(material.name()).ordinal() < 12) {
            player.playSound(player.getLocation(), Sound.BLOCK_BREWING_STAND_BREW, 1f, 1f);
            return OreType.values()[OreType.valueOf(material.name()).ordinal() + 2].getOreCreator();
        }
        return OreType.valueOf(material.name()).getOreCreator();
    }

    public int checkForEnergyCollector(int energyGain, ItemStack pickaxe) {

        if(!hasEnchant(pickaxe.getItemMeta(), PickaxeEnchants.ENERGY_COLLECTOR))
            return energyGain;
        int level = new EnchantFunctions().getEnchantLevel(pickaxe.getItemMeta(), PickaxeEnchants.ENERGY_COLLECTOR);
        return (int) (energyGain * (1 + (0.2f * level)));
    }

    public float checkForEfficiency(float progress, ItemStack pickaxe) {
        if(!hasEnchant(pickaxe.getItemMeta(), PickaxeEnchants.EFFICIENCY))
            return progress;
        return progress * (1 + (0.20f * getEnchantLevel(pickaxe.getItemMeta(), PickaxeEnchants.EFFICIENCY)));
    }

    public boolean checkAlchemyChance(ItemMeta meta) {
        if(!hasEnchant(meta, PickaxeEnchants.TRANSFUSE))
            return false;
        int level = getEnchantLevel(meta, PickaxeEnchants.TRANSFUSE);
        return RANDOM.nextFloat() < 0.033f * level;
    }

    public boolean checkForAlchemy(ItemStack pickaxe, OreCreator ore, int amount, Player player) {
        //ALCHEMY 3 CHANCE TO DOUBLE SELL
        if(!hasEnchant(pickaxe.getItemMeta(), PickaxeEnchants.ALCHEMY))
            return false;
        if(!checkAlchemyChance(pickaxe.getItemMeta()))
            return false;
        player.playSound(player.getLocation(), Sound.BLOCK_PISTON_CONTRACT, 1f, 1f);
        //Check ore and give money note
        //If already has money note combine
        return true;
    }
}
