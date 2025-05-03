package org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.pickaxe.functions;

import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.evasive.me.cosmicPrisonsCore.CosmicPrisonsCore;
import org.evasive.me.cosmicPrisonsCore.utils.EnchantUtil;
import org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.PickaxeEnchantFunctions;
import org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.pickaxe.PickaxeEnchants;

public class SuperBreakerFunctions {

    PickaxeEnchantFunctions enchantFunctions = new PickaxeEnchantFunctions();

    public void checkForSuperBreaker(Player player, ItemMeta meta){
        if(CosmicPrisonsCore.superBreakerMap.hasSuperBreaker(player)){
            handleSuperBreaker(player);
            return;
        }
        int level = enchantFunctions.getEnchantLevel(meta, PickaxeEnchants.SUPER_BREAKER);
        if(level == 0)
            return;
        if(EnchantUtil.calculateChance(0.05f))//Does not increase per level
            activateSuperBreaker(player, level);
    }

    public void activateSuperBreaker(Player player, int level){
        boolean doubleXP = false;
        if(level == 5)
            doubleXP = EnchantUtil.calculateChance(0.10f);
        int timerID = startSuperBreakerTimer(player);
        CosmicPrisonsCore.superBreakerMap.addSuperBreaker(player, 3+level, 10 + level, doubleXP, timerID);
        if(doubleXP)
            player.spawnParticle(Particle.WITCH, player.getLocation().add(0f, 1.5f, 0f), 10, 0.2, 0.2, 0.2, 0.05);
        else
            player.spawnParticle(Particle.LAVA, player.getLocation().add(0f, 1.5f, 0f), 10, 0.2, 0.2, 0.2, 0.05);
        player.playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 1f, 1f);
    }

    private int startSuperBreakerTimer(Player player) {

        return new BukkitRunnable() {
            public void run() {
                if (!CosmicPrisonsCore.superBreakerMap.hasSuperBreaker(player)) {
                    this.cancel();
                    return;
                }
                //Bukkit.getConsoleSender().sendMessage("RUNNING");
                if (CosmicPrisonsCore.superBreakerMap.getSuperBreaker(player).getTime() <= 0) {
                    CosmicPrisonsCore.superBreakerMap.removeSuperBreaker(player);
                    return;
                }

                // Your repeating logic here
                CosmicPrisonsCore.superBreakerMap.subtractTime(player);
            }
        }.runTaskTimer(CosmicPrisonsCore.getCore(), 0L, 20L).getTaskId(); // Delay = 0 ticks, Repeat every 20 ticks (1 second)
    }

    private void handleSuperBreaker(Player player) {
        if(!CosmicPrisonsCore.superBreakerMap.hasSuperBreaker(player))
            return;
        int remainingBlocks = CosmicPrisonsCore.superBreakerMap.getSuperBreaker(player).getBlocks() - 1;
        CosmicPrisonsCore.superBreakerMap.subtractBlock(player);
        if(remainingBlocks != 0)
            return;
        CosmicPrisonsCore.superBreakerMap.removeSuperBreaker(player);
    }

    public int calculateSuperbreakerMulti(Player player) {
        if(!CosmicPrisonsCore.superBreakerMap.hasSuperBreaker(player))
            return 1;
        return CosmicPrisonsCore.superBreakerMap.getSuperBreaker(player).isDoubleXP() ? 2:1;
    }
}
