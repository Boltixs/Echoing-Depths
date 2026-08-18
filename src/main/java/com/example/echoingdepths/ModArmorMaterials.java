package com.example.echoingdepths;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

public enum ModArmorMaterials implements ArmorMaterial {
    RESONITE("resonite", 36, new int[]{3, 6, 8, 3}, 18,
            SoundEvents.ARMOR_EQUIP_DIAMOND, 2.5F, 0.05F),
    VOIDGLASS("voidglass", 44, new int[]{4, 7, 9, 4}, 22,
            SoundEvents.ARMOR_EQUIP_NETHERITE, 3.5F, 0.12F);

    private static final int BOOTS = 0;
    private static final int LEGGINGS = 1;
    private static final int CHESTPLATE = 2;
    private static final int HELMET = 3;
    private static final int[] HEALTH_PER_TYPE = {13, 15, 16, 11};

    private final String name;
    private final int durabilityMultiplier;
    private final int[] protection;
    private final int enchantability;
    private final SoundEvent equipSound;
    private final float toughness;
    private final float knockbackResistance;

    ModArmorMaterials(String name, int durabilityMultiplier, int[] protection,
                      int enchantability, SoundEvent equipSound,
                      float toughness, float knockbackResistance) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.protection = protection;
        this.enchantability = enchantability;
        this.equipSound = equipSound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
    }

    private static int index(ArmorItem.Type type) {
        return switch (type) {
            case BOOTS -> BOOTS;
            case LEGGINGS -> LEGGINGS;
            case CHESTPLATE -> CHESTPLATE;
            case HELMET -> HELMET;
        };
    }

    @Override
    public int getDurabilityForType(ArmorItem.Type type) {
        return HEALTH_PER_TYPE[index(type)] * durabilityMultiplier;
    }

    @Override
    public int getDefenseForType(ArmorItem.Type type) {
        return protection[index(type)];
    }

    @Override
    public int getEnchantmentValue() {
        return enchantability;
    }

    @Override
    public SoundEvent getEquipSound() {
        return equipSound;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return switch (this) {
            case RESONITE -> Ingredient.of(EchoingDepths.RESONITE_INGOT.get());
            case VOIDGLASS -> Ingredient.of(EchoingDepths.VOIDGLASS_INGOT.get());
        };
    }

    @Override
    public String getName() {
        return EchoingDepths.MOD_ID + ":" + name;
    }

    @Override
    public float getToughness() {
        return toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return knockbackResistance;
    }
}
