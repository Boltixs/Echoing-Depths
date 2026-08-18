package com.example.echoingdepths;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.Level;

public class EchoStalker extends Zombie {
    public EchoStalker(EntityType<? extends Zombie> type, Level level) { super(type, level); }
    @Override protected boolean convertsInWater() { return false; }
}
