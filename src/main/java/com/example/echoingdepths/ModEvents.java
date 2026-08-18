package com.example.echoingdepths;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = EchoingDepths.MOD_ID)
public final class ModEvents {
    private ModEvents() {}

    @SubscribeEvent
    public static void onVillagerTrades(VillagerTradesEvent event) {
        if (event.getType() != EchoingDepths.RESONANCE_ARCHIVIST.get()) return;
        Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
        trades.get(1).add(bookTrade(EchoingDepths.DEEP_STEP.get(), 1, 10, 8));
        trades.get(2).add(bookTrade(EchoingDepths.ECHO_STRIKE.get(), 1, 14, 8));
        trades.get(3).add(bookTrade(EchoingDepths.DEEP_STEP.get(), 2, 22, 5));
        trades.get(4).add(bookTrade(EchoingDepths.ECHO_STRIKE.get(), 3, 30, 4));
        trades.get(5).add(bookTrade(EchoingDepths.RESONANT_GUARD.get(), 3, 42, 2));
        trades.get(5).add((trader, random) -> new MerchantOffer(
                new ItemStack(EchoingDepths.RESONANCE_CORE.get(), 1),
                new ItemStack(Items.EMERALD, 18), 4, 30, 0.05F));
    }

    private static VillagerTrades.ItemListing bookTrade(net.minecraft.world.item.enchantment.Enchantment enchantment,
                                                         int level, int emeralds, int maxUses) {
        return (trader, random) -> new MerchantOffer(
                new ItemStack(Items.EMERALD, emeralds),
                EnchantedBookItem.createForEnchantment(new EnchantmentInstance(enchantment, level)),
                maxUses, 12 + level * 4, 0.2F);
    }

    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
        if (event.getSource().getEntity() instanceof LivingEntity attacker) {
            int lvl = EnchantmentHelper.getItemEnchantmentLevel(EchoingDepths.ECHO_STRIKE.get(), attacker.getMainHandItem());
            if (lvl > 0) event.setAmount(event.getAmount() + 1.5F * lvl);
        }
        LivingEntity victim = event.getEntity();
        int guard = EnchantmentHelper.getEnchantmentLevel(EchoingDepths.RESONANT_GUARD.get(), victim);
        if (guard > 0) event.setAmount(event.getAmount() * Math.max(0.55F, 1.0F - 0.07F * guard));
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END || event.player.level().isClientSide || event.player.tickCount % 20 != 0) return;
        Player p = event.player;
        if (p.getY() < 48) {
            int deep = EnchantmentHelper.getItemEnchantmentLevel(EchoingDepths.DEEP_STEP.get(), p.getItemBySlot(net.minecraft.world.entity.EquipmentSlot.FEET));
            if (deep > 0) p.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 40, deep - 1, true, false));
            boolean full = p.getInventory().armor.stream().allMatch(stack -> stack.is(EchoingDepths.RESONITE_HELMET.get()) || stack.is(EchoingDepths.RESONITE_CHESTPLATE.get()) || stack.is(EchoingDepths.RESONITE_LEGGINGS.get()) || stack.is(EchoingDepths.RESONITE_BOOTS.get()));
            if (full && p.getInventory().armor.stream().filter(s -> !s.isEmpty()).count() == 4) {
                p.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 240, 0, true, false));
            }
        }
    }
}
