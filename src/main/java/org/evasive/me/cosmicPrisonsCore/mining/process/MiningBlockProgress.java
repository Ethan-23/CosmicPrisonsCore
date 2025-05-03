package org.evasive.me.cosmicPrisonsCore.mining.process;

import com.github.retrooper.packetevents.PacketEvents;
import com.github.retrooper.packetevents.util.Vector3i;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerBlockBreakAnimation;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.evasive.me.cosmicPrisonsCore.CosmicPrisonsCore;
import org.evasive.me.cosmicPrisonsCore.mining.ores.OreType;
import org.evasive.me.cosmicPrisonsCore.mining.records.BlockPos;
import org.evasive.me.cosmicPrisonsCore.wormholeEnchanting.pickaxe.functions.FractureFunctions;

public class MiningBlockProgress {

    public void addBlockProgress(Player player, Block block) {
        float progress = getMiningProgress(player, block);
        createNewAnimation(player, progress, block);
        CosmicPrisonsCore.miningMap.increaseBlockBreak(BlockPos.fromBlock(block), player.getUniqueId(), progress);
        checkForBlockBreak(player, block);
    }

    public void checkForFracture(Player player, ItemStack itemStack, Block block){
        FractureFunctions fractureFunctions = new FractureFunctions();
        if(!fractureFunctions.checkFractureChance(itemStack.getItemMeta()))
            return;
        for(Block fractureBlock : fractureFunctions.getFractureBlocks(block)){
            new MiningPacketEvents().handleFractureProgress(player, fractureBlock);
        }
    }

    public void addBlockFractureProgress(Player player, Block block){
        float progress = getFractureProgress(player, block);
        createNewAnimation(player, progress, block);
        CosmicPrisonsCore.miningMap.increaseBlockFractureBreak(BlockPos.fromBlock(block), player.getUniqueId(), progress);
        checkForBlockBreak(player, block);
    }

    private void checkForBlockBreak(Player player, Block block) {
        if(CosmicPrisonsCore.miningMap.getTotalProgress(BlockPos.fromBlock(block), player.getUniqueId()) >= 100){
            new MiningBlockBreak().breakBlock(player, block);
        }
    }

    private void createNewAnimation(Player player, float progress, Block block) {
        if(!isBlockStillValid(BlockPos.fromBlock(block)))
            return;
        float currentProgress = CosmicPrisonsCore.miningMap.getTotalProgress(BlockPos.fromBlock(block), player.getUniqueId());
        if((int)(currentProgress + progress) / 10 > (int)currentProgress / 10 || currentProgress == 0)
            sendAnimationPacket(player, block, (byte) ((currentProgress + progress) / (100 / 10)));
    }

    private float getMiningProgress(Player player, Block block) {
        MiningSpeedCalculations miningSpeedCalculations = new MiningSpeedCalculations();
        return canPlayerMineOre(player, block) ? miningSpeedCalculations.calculateMiningSpeed(player, player.getInventory().getItemInMainHand(), block.getType()) : miningSpeedCalculations.getBaseSpeed(player.getInventory().getItemInMainHand());
    }

    private float getFractureProgress(Player player, Block block){
        MiningSpeedCalculations miningSpeedCalculations = new MiningSpeedCalculations();
        return canPlayerMineOre(player, block) ? miningSpeedCalculations.calculateFractureSpeed(player.getInventory().getItemInMainHand()) : 0f;
    }

    public boolean canPlayerMineOre(Player player, Block block){
        return OreType.valueOf(block.getType().name()).getOreCreator().mineableLevel() <= CosmicPrisonsCore.playerLevelManager.getPlayerData(player.getUniqueId()).getLevel();
    }



    public void sendAnimationPacket(Player player, Block block, byte blockProgress){
        if(blockProgress>9) blockProgress = 9;
        WrapperPlayServerBlockBreakAnimation progressAnimation = new WrapperPlayServerBlockBreakAnimation(
                CosmicPrisonsCore.miningMap.getPlayerBlockAnimationId(BlockPos.fromBlock(block), player.getUniqueId()),
                new Vector3i(block.getX(), block.getY(), block.getZ()),
                blockProgress
        );
        PacketEvents.getAPI().getPlayerManager().sendPacket(player, progressAnimation);
    }

    public boolean isBlockStillValid(BlockPos blockPos){
        return CosmicPrisonsCore.miningMap.containsBlockLocation(blockPos);
    }

}
