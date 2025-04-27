package org.evasive.me.cosmicPrisonsCore.mining.records;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;

public record BlockPos(int x, int y, int z, String worldName) {

    public static BlockPos fromLocation(Location location){
        return new BlockPos((int) location.getX(), (int) location.getY(), (int) location.getZ(), location.getWorld().getName());
    }

    public static BlockPos fromBlock(Block block){
        return new BlockPos((int) block.getX(), (int) block.getY(), (int) block.getZ(), block.getWorld().getName());
    }

    public static Location getLocation(BlockPos blockPos){
        return new Location(Bukkit.getWorld(blockPos.worldName), blockPos.x(), blockPos.y(), blockPos.z());
    }

}
