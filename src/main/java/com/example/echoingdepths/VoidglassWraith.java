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
import net.minecraft.world.phys.Vec3;

public class VoidglassWraith extends Zombie {
    private final ServerBossEvent bossEvent = new ServerBossEvent(getDisplayName(), BossEvent.BossBarColor.PURPLE, BossEvent.BossBarOverlay.NOTCHED_10);

    public VoidglassWraith(EntityType<? extends Zombie> type, Level level) {
        super(type, level);
        this.xpReward = 260;
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (!level().isClientSide) {
            bossEvent.setProgress(getHealth() / getMaxHealth());
            LivingEntity target = getTarget();

            if (tickCount % 100 == 0) {
                addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 60, 0));
            }

            if (target != null && tickCount % 50 == 0 && distanceToSqr(target) < 324.0D) {
                target.hurt(damageSources().magic(), getHealth() < getMaxHealth() * 0.5F ? 8.0F : 5.5F);
                target.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 40, 0));
            }

            if (getHealth() < getMaxHealth() * 0.3F && tickCount % 90 == 0 && target != null) {
                Vec3 behind = target.position().subtract(target.getLookAngle().scale(3.0D));
                teleportTo(behind.x, behind.y, behind.z);
                addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 60, 1));
            }
        }
    }

    @Override public void startSeenByPlayer(ServerPlayer player) { super.startSeenByPlayer(player); bossEvent.addPlayer(player); }
    @Override public void stopSeenByPlayer(ServerPlayer player) { super.stopSeenByPlayer(player); bossEvent.removePlayer(player); }
}
