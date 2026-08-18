package com.example.echoingdepths;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class EchoSigilItem extends Item {
    public EchoSigilItem(Properties properties) { super(properties); }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (!level.isClientSide && level instanceof ServerLevel serverLevel) {
            if (player.getY() > 45) return InteractionResultHolder.fail(stack);
            EchoGuardian boss = EchoingDepths.ECHO_GUARDIAN.get().create(serverLevel);
            if (boss != null) {
                boss.moveTo(player.getX() + 4.0D, player.getY(), player.getZ(), player.getYRot(), 0.0F);
                boss.finalizeSpawn(serverLevel, serverLevel.getCurrentDifficultyAt(boss.blockPosition()), MobSpawnType.TRIGGERED, null, null);
                serverLevel.addFreshEntity(boss);
                if (!player.getAbilities().instabuild) stack.shrink(1);
                player.getCooldowns().addCooldown(this, 200);
                return InteractionResultHolder.sidedSuccess(stack, false);
            }
        }
        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide);
    }
}
