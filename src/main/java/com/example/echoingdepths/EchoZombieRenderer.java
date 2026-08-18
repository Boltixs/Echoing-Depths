package com.example.echoingdepths;

import net.minecraft.client.model.ZombieModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Zombie;

public class EchoZombieRenderer<T extends Zombie> extends MobRenderer<T, ZombieModel<T>> {
    private final ResourceLocation texture;

    public EchoZombieRenderer(EntityRendererProvider.Context context, String textureName) {
        super(context, new ZombieModel<>(context.bakeLayer(ModelLayers.ZOMBIE)), 0.5F);
        this.texture = new ResourceLocation(EchoingDepths.MOD_ID, "textures/entity/" + textureName + ".png");
    }

    @Override
    public ResourceLocation getTextureLocation(T entity) {
        return texture;
    }
}
