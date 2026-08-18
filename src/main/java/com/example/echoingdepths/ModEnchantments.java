package com.example.echoingdepths;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public final class ModEnchantments {
    private ModEnchantments() {}

    public static class EchoStrike extends Enchantment {
        public EchoStrike() { super(Rarity.RARE, EnchantmentCategory.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND}); }
        @Override public int getMinCost(int level) { return 10 + (level - 1) * 12; }
        @Override public int getMaxCost(int level) { return getMinCost(level) + 25; }
        @Override public int getMaxLevel() { return 3; }
    }

    public static class DeepStep extends Enchantment {
        public DeepStep() { super(Rarity.RARE, EnchantmentCategory.ARMOR_FEET, new EquipmentSlot[]{EquipmentSlot.FEET}); }
        @Override public int getMinCost(int level) { return 12 + (level - 1) * 15; }
        @Override public int getMaxCost(int level) { return getMinCost(level) + 30; }
        @Override public int getMaxLevel() { return 2; }
    }

    public static class ResonantGuard extends Enchantment {
        public ResonantGuard() { super(Rarity.VERY_RARE, EnchantmentCategory.ARMOR, EquipmentSlot.values()); }
        @Override public int getMinCost(int level) { return 18 + (level - 1) * 18; }
        @Override public int getMaxCost(int level) { return getMinCost(level) + 35; }
        @Override public int getMaxLevel() { return 3; }
    }

    public static class VoidWalker extends Enchantment {
        public VoidWalker() { super(Rarity.VERY_RARE, EnchantmentCategory.ARMOR_FEET, new EquipmentSlot[]{EquipmentSlot.FEET}); }
        @Override public int getMinCost(int level) { return 20 + (level - 1) * 20; }
        @Override public int getMaxCost(int level) { return getMinCost(level) + 40; }
        @Override public int getMaxLevel() { return 2; }
    }
}
