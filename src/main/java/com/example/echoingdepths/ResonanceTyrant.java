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

public class ResonanceTyrant extends Zombie {
    private final ServerBossEvent bossEvent = new ServerBossEvent(getDisplayName(), BossEvent.BossBarColor.RED, BossEvent.BossBarOverlay.NOTCHED_12);

    public ResonanceTyrant(EntityType<? extends Zombie> type, Level level) {
        super(type, level);
        this.xpReward = 300;
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (!level().isClientSide) {
            float ratio = getHealth() / getMaxHealth();
            bossEvent.setProgress(ratio);
            LivingEntity target = getTarget();

            if (ratio <= 0.66F && ratio > 0.33F && tickCount % 100 == 0) {
                addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 80, 1));
                addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 80, 1));
            }
            if (ratio <= 0.33F && tickCount % 70 == 0) {
                for (LivingEntity e : level().getEntitiesOfClass(LivingEntity.class, new AABB(blockPosition()).inflate(6.0D), e -> e != this && !(e instanceof Zombie))) {
                    e.hurt(damageSources().magic(), 8.0F);
                    e.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 80, 1));
                }
                addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 90, 2));
            }
            if (target != null && tickCount % 45 == 0 && distanceToSqr(target) < 256.0D) {
                target.hurt(damageSources().magic(), ratio <= 0.33F ? 7.0F : 4.0F);
            }
        }
    }

    @Override public void startSeenByPlayer(ServerPlayer player) { super.startSeenByPlayer(player); bossEvent.addPlayer(player); }
    @Override public void stopSeenByPlayer(ServerPlayer player) { super.stopSeenByPlayer(player); bossEvent.removePlayer(player); }
}
