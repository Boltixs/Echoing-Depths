package com.example.echoingdepths;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

public class ResonanceHornItem extends Item {
    public ResonanceHornItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (!level.isClientSide) {
            for (LivingEntity entity : level.getEntitiesOfClass(LivingEntity.class, new AABB(player.blockPosition()).inflate(12.0D),
                    e -> e instanceof net.minecraft.world.entity.monster.Monster && e != player)) {
                entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 1));
                entity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 100, 0));
            }

            for (Player nearby : level.getEntitiesOfClass(Player.class, new AABB(player.blockPosition()).inflate(12.0D))) {
                nearby.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 100, 0));
            }

            level.playSound(null, player.blockPosition(), SoundEvents.NOTE_BLOCK_DIDGERIDOO,
                    SoundSource.PLAYERS, 1.5F, 0.7F);

            player.getCooldowns().addCooldown(this, 20 * 30);
            if (!player.getAbilities().instabuild && player instanceof ServerPlayer serverPlayer) {
                stack.hurt(1, level.random, serverPlayer);
            }
        }

        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
    }
}
