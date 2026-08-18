package com.example.echoingdepths;

import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public final class ModBusEvents {
    private ModBusEvents() {}

    @Mod.EventBusSubscriber(modid = EchoingDepths.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static final class Common {
        @SubscribeEvent
        public static void commonSetup(FMLCommonSetupEvent event) {
            event.enqueueWork(() -> {
                SpawnPlacements.register(EchoingDepths.ECHO_STALKER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                        (type, level, reason, pos, random) -> pos.getY() < 42 && Monster.checkMonsterSpawnRules(type, level, reason, pos, random));
                SpawnPlacements.register(EchoingDepths.CRYSTAL_CRAWLER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                        (type, level, reason, pos, random) -> pos.getY() < 30 && Monster.checkMonsterSpawnRules(type, level, reason, pos, random));
            });
        }

        @SubscribeEvent
        public static void attributes(EntityAttributeCreationEvent event) {
            event.put(EchoingDepths.ECHO_STALKER.get(), Zombie.createAttributes()
                    .add(Attributes.MAX_HEALTH, 32.0D)
                    .add(Attributes.ATTACK_DAMAGE, 5.5D)
                    .add(Attributes.MOVEMENT_SPEED, 0.25D).build());
            event.put(EchoingDepths.CRYSTAL_CRAWLER.get(), Spider.createAttributes()
                    .add(Attributes.MAX_HEALTH, 20.0D)
                    .add(Attributes.ATTACK_DAMAGE, 4.0D)
                    .add(Attributes.MOVEMENT_SPEED, 0.34D).build());
            event.put(EchoingDepths.ECHO_GUARDIAN.get(), Zombie.createAttributes()
                    .add(Attributes.MAX_HEALTH, 180.0D)
                    .add(Attributes.ATTACK_DAMAGE, 12.0D)
                    .add(Attributes.ARMOR, 10.0D)
                    .add(Attributes.KNOCKBACK_RESISTANCE, 0.65D)
                    .add(Attributes.MOVEMENT_SPEED, 0.26D).build());
            event.put(EchoingDepths.RESONANT_COLOSSUS.get(), Zombie.createAttributes()
                    .add(Attributes.MAX_HEALTH, 320.0D).add(Attributes.ATTACK_DAMAGE, 16.0D)
                    .add(Attributes.ARMOR, 14.0D).add(Attributes.KNOCKBACK_RESISTANCE, 0.9D)
                    .add(Attributes.MOVEMENT_SPEED, 0.20D).build());
            event.put(EchoingDepths.ABYSS_HERALD.get(), Zombie.createAttributes()
                    .add(Attributes.MAX_HEALTH, 260.0D).add(Attributes.ATTACK_DAMAGE, 10.0D)
                    .add(Attributes.ARMOR, 8.0D).add(Attributes.KNOCKBACK_RESISTANCE, 0.55D)
                    .add(Attributes.MOVEMENT_SPEED, 0.28D).build());
            event.put(EchoingDepths.RESONANCE_TYRANT.get(), Zombie.createAttributes()
                    .add(Attributes.MAX_HEALTH, 500.0D).add(Attributes.ATTACK_DAMAGE, 18.0D)
                    .add(Attributes.ARMOR, 18.0D).add(Attributes.KNOCKBACK_RESISTANCE, 1.0D)
                    .add(Attributes.MOVEMENT_SPEED, 0.25D).build());
            event.put(EchoingDepths.VOIDGLASS_WRAITH.get(), Zombie.createAttributes()
                    .add(Attributes.MAX_HEALTH, 400.0D).add(Attributes.ATTACK_DAMAGE, 14.0D)
                    .add(Attributes.ARMOR, 12.0D).add(Attributes.KNOCKBACK_RESISTANCE, 0.7D)
                    .add(Attributes.MOVEMENT_SPEED, 0.30D).build());
            event.put(EchoingDepths.HOLLOW_SOVEREIGN.get(), Zombie.createAttributes()
                    .add(Attributes.MAX_HEALTH, 750.0D).add(Attributes.ATTACK_DAMAGE, 22.0D)
                    .add(Attributes.ARMOR, 22.0D).add(Attributes.KNOCKBACK_RESISTANCE, 1.0D)
                    .add(Attributes.MOVEMENT_SPEED, 0.27D).build());
        }
    }

    @Mod.EventBusSubscriber(modid = EchoingDepths.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static final class Client {
        @SubscribeEvent
        public static void renderers(EntityRenderersEvent.RegisterRenderers event) {
            event.registerEntityRenderer(EchoingDepths.ECHO_STALKER.get(), ctx -> new EchoZombieRenderer(ctx, "echo_stalker"));
            event.registerEntityRenderer(EchoingDepths.CRYSTAL_CRAWLER.get(), CrystalCrawlerRenderer::new);
            event.registerEntityRenderer(EchoingDepths.ECHO_GUARDIAN.get(), ctx -> new EchoZombieRenderer(ctx, "echo_guardian"));
            event.registerEntityRenderer(EchoingDepths.RESONANT_COLOSSUS.get(), ctx -> new EchoZombieRenderer(ctx, "resonant_colossus"));
            event.registerEntityRenderer(EchoingDepths.ABYSS_HERALD.get(), ctx -> new EchoZombieRenderer(ctx, "abyss_herald"));
            event.registerEntityRenderer(EchoingDepths.RESONANCE_TYRANT.get(), ctx -> new EchoZombieRenderer(ctx, "resonance_tyrant"));
            event.registerEntityRenderer(EchoingDepths.VOIDGLASS_WRAITH.get(), ctx -> new EchoZombieRenderer(ctx, "voidglass_wraith"));
            event.registerEntityRenderer(EchoingDepths.HOLLOW_SOVEREIGN.get(), ctx -> new EchoZombieRenderer(ctx, "hollow_sovereign"));
        }
    }
}
