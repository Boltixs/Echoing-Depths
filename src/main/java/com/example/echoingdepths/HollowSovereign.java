package com.example.echoingdepths;

import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.BossEvent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

public class HollowSovereign extends Zombie {
    private final ServerBossEvent bossEvent = new ServerBossEvent(getDisplayName(), BossEvent.BossBarColor.PURPLE, BossEvent.BossBarOverlay.NOTCHED_20);

    public HollowSovereign(EntityType<? extends Zombie> type, Level level) {
        super(type, level);
        this.xpReward = 500;
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (!level().isClientSide) {
            float ratio = getHealth() / getMaxHealth();
            bossEvent.setProgress(ratio);
            LivingEntity target = getTarget();

            if (ratio <= 0.75F && ratio > 0.5F && tickCount % 100 == 0) {
                addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 100, 1));
            }
            if (ratio <= 0.5F && ratio > 0.25F && tickCount % 80 == 0) {
                for (LivingEntity e : level().getEntitiesOfClass(LivingEntity.class, new AABB(blockPosition()).inflate(7.0D), e -> e != this && !(e instanceof Zombie))) {
                    e.hurt(damageSources().magic(), 10.0F);
                    e.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 100, 1));
                    e.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 1));
                }
                addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 100, 2));
            }
            if (ratio <= 0.25F && tickCount % 60 == 0) {
                addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 100, 2));
                addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 100, 3));
                addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 100, 2));
            }
            if (target != null && tickCount % 40 == 0 && distanceToSqr(target) < 400.0D) {
                target.hurt(damageSources().magic(), ratio <= 0.25F ? 12.0F : ratio <= 0.5F ? 9.0F : 6.0F);
                target.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 100, 0));
            }
        }
    }

    @Override public void startSeenByPlayer(ServerPlayer player) { super.startSeenByPlayer(player); bossEvent.addPlayer(player); }
    @Override public void stopSeenByPlayer(ServerPlayer player) { super.stopSeenByPlayer(player); bossEvent.removePlayer(player); }
}
