package com.example.echoingdepths;

import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.BossEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.Level;

public class EchoGuardian extends Zombie {
    private final ServerBossEvent bossEvent = new ServerBossEvent(
            getDisplayName(), BossEvent.BossBarColor.PURPLE, BossEvent.BossBarOverlay.PROGRESS);

    public EchoGuardian(EntityType<? extends Zombie> type, Level level) {
        super(type, level);
        this.xpReward = 80;
    }

    @Override public void aiStep() {
        super.aiStep();
        if (!level().isClientSide) {
            bossEvent.setProgress(getHealth() / getMaxHealth());
            if (tickCount % 80 == 0 && getTarget() != null && distanceToSqr(getTarget()) < 144.0D) {
                getTarget().hurt(damageSources().magic(), 4.0F);
            }
        }
    }

    @Override public void startSeenByPlayer(ServerPlayer player) {
        super.startSeenByPlayer(player);
        bossEvent.addPlayer(player);
    }

    @Override public void stopSeenByPlayer(ServerPlayer player) {
        super.stopSeenByPlayer(player);
        bossEvent.removePlayer(player);
    }
}
