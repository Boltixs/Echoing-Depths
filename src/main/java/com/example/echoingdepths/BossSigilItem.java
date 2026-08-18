package com.example.echoingdepths;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.function.Supplier;

public class BossSigilItem extends Item {
    private final Supplier<? extends EntityType<? extends Zombie>> bossType;
    private final int maxY;

    public BossSigilItem(Properties properties, Supplier<? extends EntityType<? extends Zombie>> bossType, int maxY) {
        super(properties);
        this.bossType = bossType;
        this.maxY = maxY;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (!level.isClientSide && level instanceof ServerLevel serverLevel) {
            if (player.getY() > maxY) return InteractionResultHolder.fail(stack);
            Zombie boss = bossType.get().create(serverLevel);
            if (boss != null) {
                boss.moveTo(player.getX() + 5.0D, player.getY(), player.getZ(), player.getYRot(), 0.0F);
                boss.finalizeSpawn(serverLevel, serverLevel.getCurrentDifficultyAt(boss.blockPosition()), MobSpawnType.TRIGGERED, null, null);
                serverLevel.addFreshEntity(boss);
                if (!player.getAbilities().instabuild) stack.shrink(1);
                player.getCooldowns().addCooldown(this, 300);
                return InteractionResultHolder.sidedSuccess(stack, false);
            }
        }
        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide);
    }
}
