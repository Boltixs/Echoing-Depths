package com.example.echoingdepths;

import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.BossEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

public class ResonantColossus extends Zombie {
    private final ServerBossEvent bossEvent = new ServerBossEvent(getDisplayName(), BossEvent.BossBarColor.YELLOW, BossEvent.BossBarOverlay.PROGRESS);

    public ResonantColossus(EntityType<? extends Zombie> type, Level level) {
        super(type, level);
        this.xpReward = 140;
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (!level().isClientSide) {
            bossEvent.setProgress(getHealth() / getMaxHealth());
            if (tickCount % 100 == 0) {
                for (LivingEntity target : level().getEntitiesOfClass(LivingEntity.class, new AABB(blockPosition()).inflate(5.0D), e -> e != this && !(e instanceof ResonantColossus))) {
                    target.hurt(damageSources().mobAttack(this), getHealth() < getMaxHealth() * 0.5F ? 10.0F : 7.0F);
                    target.knockback(1.4D, getX() - target.getX(), getZ() - target.getZ());
                }
            }
        }
    }

    @Override public void startSeenByPlayer(ServerPlayer player) { super.startSeenByPlayer(player); bossEvent.addPlayer(player); }
    @Override public void stopSeenByPlayer(ServerPlayer player) { super.stopSeenByPlayer(player); bossEvent.removePlayer(player); }
}
