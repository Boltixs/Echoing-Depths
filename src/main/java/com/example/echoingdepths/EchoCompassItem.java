package com.example.echoingdepths;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class EchoCompassItem extends Item {
    public EchoCompassItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (!level.isClientSide) {
            if (player.getY() <= 40) {
                player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 20 * 20, 0, false, false, true));
                player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 12 * 20, 0, false, false, true));
                player.displayClientMessage(Component.translatable("message.echoing_depths.resonance_found")
                        .withStyle(ChatFormatting.AQUA), true);
            } else {
                player.displayClientMessage(Component.translatable("message.echoing_depths.too_high")
                        .withStyle(ChatFormatting.GRAY), true);
            }

            level.playSound(null, player.blockPosition(), SoundEvents.AMETHYST_BLOCK_CHIME,
                    SoundSource.PLAYERS, 1.0F, 0.8F + level.random.nextFloat() * 0.4F);

            player.getCooldowns().addCooldown(this, 20 * 15);
            if (!player.getAbilities().instabuild && player instanceof ServerPlayer serverPlayer) {
                stack.hurt(1, level.random, serverPlayer);
            }
        }

        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
    }
}
