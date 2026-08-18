package com.example.echoingdepths;

import net.minecraft.client.model.SpiderModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class CrystalCrawlerRenderer extends MobRenderer<CrystalCrawler, SpiderModel<CrystalCrawler>> {
    private static final ResourceLocation TEX = new ResourceLocation(EchoingDepths.MOD_ID, "textures/entity/crystal_crawler.png");

    public CrystalCrawlerRenderer(EntityRendererProvider.Context context) {
        super(context, new SpiderModel<>(context.bakeLayer(ModelLayers.SPIDER)), 0.8F);
    }

    @Override
    public ResourceLocation getTextureLocation(CrystalCrawler entity) {
        return TEX;
    }
}
