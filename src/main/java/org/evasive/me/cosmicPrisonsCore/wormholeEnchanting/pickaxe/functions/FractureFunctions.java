package org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.pickaxe.functions;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.evasive.me.cosmicPrisonsCore.mining.ores.OreType;
import org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.PickaxeEnchantFunctions;
import org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.pickaxe.PickaxeEnchants;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FractureFunctions {

    PickaxeEnchantFunctions enchantFunctions = new PickaxeEnchantFunctions();
    private final Random RANDOM = new Random();

    public boolean checkFractureChance(ItemMeta meta) {
        int level = enchantFunctions.getEnchantLevel(meta, PickaxeEnchants.FRACTURE);
        if(level == 0)
            return false;
        int number = RANDOM.nextInt(15) + 1;
        return level >= number;
    }

    public float getFractureSpeed(ItemStack pickaxe) {
        return (1 + enchantFunctions.getEnchantLevel(pickaxe.getItemMeta(), PickaxeEnchants.FRACTURE) * 0.2f);
    }

    public List<Block> getFractureBlocks(Block block){
        List<Block> blockList = new ArrayList<>();
        int centerX = block.getX();
        int centerY = block.getY();
        int centerZ = block.getZ();
        World world = block.getWorld();

        for (int x = centerX - 1; x <= centerX + 1; x++) {
            for (int y = centerY - 1; y <= centerY + 1; y++) {
                for (int z = centerZ - 1; z <= centerZ + 1; z++) {
                    if (x == centerX && y == centerY && z == centerZ)
                        continue;
                    Block nearbyBlock = world.getBlockAt(x, y, z);
                    if(nearbyBlock.getType() != block.getType() && nearbyBlock.getType() != OreType.valueOf(block.getType().name()).getOreCreator().getRefinedMaterial() && nearbyBlock.getType() != OreType.valueOf(block.getType().name()).getOreCreator().getRespawnMaterial())
                        continue;
                    BlockFace[] faces = {BlockFace.UP, BlockFace.DOWN, BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST};

                    boolean exposedToAir = false;
                    for (BlockFace face : faces) {
                        if (nearbyBlock.getRelative(face).getType() == Material.AIR) {
                            exposedToAir = true;
                            break;
                        }
                    }


                    if (!exposedToAir)
                        continue;

                    blockList.add(nearbyBlock);
                }
            }
        }
        return blockList;
    }
}
