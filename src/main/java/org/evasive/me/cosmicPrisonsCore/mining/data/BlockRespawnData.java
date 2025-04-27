package org.evasive.me.cosmicPrisonsCore.mining.data;

import org.bukkit.Material;
import org.evasive.me.cosmicPrisonsCore.mining.records.BlockPos;

public class BlockRespawnData {
    private Material material;
    private Material refinedMaterial;
    private BlockPos blockPos;

    public BlockRespawnData(Material material, Material refinedMaterial, BlockPos blockPos) {
        this.material = material;
        this.refinedMaterial = refinedMaterial;
        this.blockPos = blockPos;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Material getRefinedMaterial() {
        return refinedMaterial;
    }

    public void setRefinedMaterial(Material refinedMaterial) {
        this.refinedMaterial = refinedMaterial;
    }

    public BlockPos getBlockPos() {
        return blockPos;
    }

    public void setBlockPos(BlockPos blockPos) {
        this.blockPos = blockPos;
    }
}
