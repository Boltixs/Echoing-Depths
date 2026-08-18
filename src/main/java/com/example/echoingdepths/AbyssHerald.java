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

public class AbyssHerald extends Zombie {
    private final ServerBossEvent bossEvent = new ServerBossEvent(getDisplayName(), BossEvent.BossBarColor.BLUE, BossEvent.BossBarOverlay.NOTCHED_10);

    public AbyssHerald(EntityType<? extends Zombie> type, Level level) {
        super(type, level);
        this.xpReward = 180;
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (!level().isClientSide) {
            bossEvent.setProgress(getHealth() / getMaxHealth());
            LivingEntity target = getTarget();
            if (target != null && tickCount % 60 == 0 && distanceToSqr(target) < 400.0D) {
                target.hurt(damageSources().magic(), getHealth() < getMaxHealth() * 0.4F ? 9.0F : 6.0F);
                target.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 80, 0));
                target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 60, 1));
            }
            if (getHealth() < getMaxHealth() * 0.4F && tickCount % 120 == 0) {
                addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 100, 1));
                addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 100, 1));
            }
        }
    }

    @Override public void startSeenByPlayer(ServerPlayer player) { super.startSeenByPlayer(player); bossEvent.addPlayer(player); }
    @Override public void stopSeenByPlayer(ServerPlayer player) { super.stopSeenByPlayer(player); bossEvent.removePlayer(player); }
}
