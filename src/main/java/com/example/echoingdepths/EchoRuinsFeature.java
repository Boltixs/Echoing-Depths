package com.example.echoingdepths;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class EchoRuinsFeature extends Feature<NoneFeatureConfiguration> {
    public EchoRuinsFeature(Codec<NoneFeatureConfiguration> codec) { super(codec); }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> ctx) {
        WorldGenLevel level = ctx.level();
        BlockPos origin = new BlockPos(ctx.origin().getX(), -18 - ctx.random().nextInt(30), ctx.origin().getZ());
        if (!level.getBlockState(origin).isSolid()) return false;

        for (int x = -4; x <= 4; x++) {
            for (int z = -4; z <= 4; z++) {
                for (int y = 0; y <= 4; y++) {
                    BlockPos p = origin.offset(x, y, z);
                    boolean wall = Math.abs(x) == 4 || Math.abs(z) == 4 || y == 0;
                    if (wall) {
                        level.setBlock(p, ctx.random().nextFloat() < 0.18F ? Blocks.CRYING_OBSIDIAN.defaultBlockState() : Blocks.DEEPSLATE_BRICKS.defaultBlockState(), 2);
                    } else {
                        level.setBlock(p, Blocks.AIR.defaultBlockState(), 2);
                    }
                }
            }
        }
        level.setBlock(origin.above(), Blocks.AMETHYST_BLOCK.defaultBlockState(), 2);
        BlockPos chestPos = origin.offset(0, 1, 2);
        level.setBlock(chestPos, Blocks.CHEST.defaultBlockState(), 2);
        if (level.getBlockEntity(chestPos) instanceof ChestBlockEntity chest) {
            chest.setLootTable(EchoingDepths.ECHO_RUINS_LOOT, ctx.random().nextLong());
        }
        return true;
    }
}
