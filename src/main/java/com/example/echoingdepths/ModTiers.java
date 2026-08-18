package com.example.echoingdepths;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;

public final class ModTiers {
    private ModTiers() {}

    public static final ForgeTier RESONITE = new ForgeTier(
            3,          // mining level: diamond tier
            1850,       // durability
            9.0F,       // mining speed
            3.5F,       // attack damage bonus
            18,         // enchantability
            BlockTags.NEEDS_DIAMOND_TOOL,
            () -> Ingredient.of(EchoingDepths.RESONITE_INGOT.get())
    );

    public static final ForgeTier VOIDGLASS = new ForgeTier(
            4,          // mining level: above diamond tier
            2400,       // durability
            10.5F,      // mining speed
            4.5F,       // attack damage bonus
            22,         // enchantability
            BlockTags.NEEDS_DIAMOND_TOOL,
            () -> Ingredient.of(EchoingDepths.VOIDGLASS_INGOT.get())
    );
}
