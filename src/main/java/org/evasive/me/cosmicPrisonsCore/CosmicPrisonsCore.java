package org.evasive.me.cosmicPrisonsCore;

import io.github.retrooper.packetevents.factory.spigot.SpigotPacketEventsBuilder;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.evasive.me.cosmicPrisonsCore.customItems.ItemMaker;
import org.evasive.me.cosmicPrisonsCore.customItems.commands.ItemCommands;
import org.evasive.me.cosmicPrisonsCore.customItems.energy.events.EnergyCombine;
import org.evasive.me.cosmicPrisonsCore.customItems.commands.ExtractCommand;
import org.evasive.me.cosmicPrisonsCore.customItems.satchels.events.CancelPlace;
import org.evasive.me.cosmicPrisonsCore.mining.process.MiningPacketEvents;
import org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.commands.AdminCommands;
import org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.pickaxe.functions.data.SuperBreakerMap;
import org.evasive.me.cosmicPrisonsCore.mining.respawn.BlockRespawner;
import org.evasive.me.cosmicPrisonsCore.mining.respawn.BlockRespawnMap;
import org.evasive.me.cosmicPrisonsCore.mining.process.MiningAnimationIds;
import org.evasive.me.cosmicPrisonsCore.mining.process.MiningMap;

import org.evasive.me.cosmicPrisonsCore.mining.process.MiningSelectedBlockMap;
import org.evasive.me.cosmicPrisonsCore.player.PlayerManager;
import org.evasive.me.cosmicPrisonsCore.player.commands.PlayerStatsCommands;
import org.evasive.me.cosmicPrisonsCore.player.events.JoinEvent;
import org.evasive.me.cosmicPrisonsCore.scoreboard.ScoreboardSetup;
import org.evasive.me.cosmicPrisonsCore.serverEvents.DisableCraftingEvents;
import org.evasive.me.cosmicPrisonsCore.utils.PacketBroadcaster;
import org.evasive.me.cosmicPrisonsCore.utils.ReadOutgoingPackets;

public final class CosmicPrisonsCore extends JavaPlugin {

    public static MiningMap miningMap;
    public static MiningSelectedBlockMap selectedBlockMap;
    public static BlockRespawnMap blockRespawnMap;
    public static PlayerManager playerLevelManager;
    public static CosmicPrisonsCore core;
    public static MiningAnimationIds animationIds;
    public static ItemMaker itemMaker;
    public static PacketBroadcaster packetBroadcaster;
    public static SuperBreakerMap superBreakerMap;

    @Override
    public void onLoad(){
        com.github.retrooper.packetevents.PacketEvents.setAPI(SpigotPacketEventsBuilder.build(this));
        com.github.retrooper.packetevents.PacketEvents.getAPI().load();

//        com.github.retrooper.packetevents.PacketEvents.getAPI().getEventManager().registerListener(new MiningAnimation());
        com.github.retrooper.packetevents.PacketEvents.getAPI().getEventManager().registerListener(new MiningPacketEvents());
        com.github.retrooper.packetevents.PacketEvents.getAPI().getEventManager().registerListener(new ReadOutgoingPackets());
    }

    @Override
    public void onEnable() {
        com.github.retrooper.packetevents.PacketEvents.getAPI().init();
        // Plugin startup logic

        core = this;
        miningMap = new MiningMap();
        selectedBlockMap = new MiningSelectedBlockMap();
        blockRespawnMap = new BlockRespawnMap();
        animationIds = new MiningAnimationIds();
        playerLevelManager = new PlayerManager();
        itemMaker = new ItemMaker();
        packetBroadcaster = new PacketBroadcaster();
        superBreakerMap = new SuperBreakerMap();
        itemMaker.init();
        Bukkit.getConsoleSender().sendMessage(Component.text("Cosmic Prisons Loaded", TextColor.fromHexString("#0affdf")));
        new ScoreboardSetup().repeatingScoreboardUpdate();
        new BlockRespawner().respawnChecker();

        loadEvents();
        loadCommands();
        //packetBroadcaster.globalPacketBroadcast();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        blockRespawnMap.forceRespawn();
        Bukkit.getConsoleSender().sendMessage(Component.text("Cosmic Prisons UnLoaded", TextColor.fromHexString("#ff0b00")));
    }

    public static CosmicPrisonsCore getCore() {
        return core;
    }

    public void loadCommands(){
        new PlayerStatsCommands();
        new ItemCommands();
        new ExtractCommand();
        new AdminCommands();
    }

    public void loadEvents(){
        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new DisableCraftingEvents(), this);
        pluginManager.registerEvents(new JoinEvent(), this);
        pluginManager.registerEvents(new EnergyCombine(), this);
        pluginManager.registerEvents(new CancelPlace(), this);
    }

    public static PlayerManager getPlayerLevelManager(){
        return playerLevelManager;
    }
}
