package com.example.echoingdepths;

import com.google.common.collect.ImmutableSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod(EchoingDepths.MOD_ID)
public class EchoingDepths {
    public static final String MOD_ID = "echoing_depths";

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);
    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, MOD_ID);
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MOD_ID);
    public static final DeferredRegister<PoiType> POI_TYPES = DeferredRegister.create(ForgeRegistries.POI_TYPES, MOD_ID);
    public static final DeferredRegister<VillagerProfession> PROFESSIONS = DeferredRegister.create(ForgeRegistries.VILLAGER_PROFESSIONS, MOD_ID);
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, MOD_ID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MOD_ID);

    public static final ResourceLocation ECHO_RUINS_LOOT = new ResourceLocation(MOD_ID, "chests/echo_ruins");

    public static final RegistryObject<Item> RESONANT_SHARD = ITEMS.register("resonant_shard", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ECHO_COMPASS = ITEMS.register("echo_compass", () -> new EchoCompassItem(new Item.Properties().stacksTo(1).durability(96)));
    public static final RegistryObject<Item> RESONANCE_HORN = ITEMS.register("resonance_horn", () -> new ResonanceHornItem(new Item.Properties().stacksTo(1).durability(24)));
    public static final RegistryObject<Item> RAW_RESONITE = ITEMS.register("raw_resonite", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RESONITE_INGOT = ITEMS.register("resonite_ingot", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RESONANCE_CORE = ITEMS.register("resonance_core", () -> new Item(new Item.Properties().rarity(Rarity.RARE).fireResistant()));
    public static final RegistryObject<Item> ECHO_SIGIL = ITEMS.register("echo_sigil", () -> new EchoSigilItem(new Item.Properties().rarity(Rarity.EPIC).stacksTo(16)));
    public static final RegistryObject<Item> CRYSTAL_FANG = ITEMS.register("crystal_fang", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> COLOSSUS_HEART = ITEMS.register("colossus_heart", () -> new Item(new Item.Properties().rarity(Rarity.RARE).fireResistant()));
    public static final RegistryObject<Item> ABYSSAL_EYE = ITEMS.register("abyssal_eye", () -> new Item(new Item.Properties().rarity(Rarity.EPIC).fireResistant()));
    public static final RegistryObject<Item> TYRANT_CROWN = ITEMS.register("tyrant_crown", () -> new Item(new Item.Properties().rarity(Rarity.EPIC).fireResistant()));
    public static final RegistryObject<Item> RAW_VOIDGLASS = ITEMS.register("raw_voidglass", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> VOIDGLASS_INGOT = ITEMS.register("voidglass_ingot", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> VOIDGLASS_SHARD = ITEMS.register("voidglass_shard", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SOVEREIGN_CORE = ITEMS.register("sovereign_core", () -> new Item(new Item.Properties().rarity(Rarity.EPIC).fireResistant()));

    public static final RegistryObject<Block> RESONITE_ORE = registerBlock("resonite_ore", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).strength(4.0F, 4.5F).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final RegistryObject<Block> DEEPSLATE_RESONITE_ORE = registerBlock("deepslate_resonite_ore", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.DEEPSLATE).strength(5.5F, 5.0F).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE)));
    public static final RegistryObject<Block> RESONANCE_TABLE = registerBlock("resonance_table", () -> new Block(BlockBehaviour.Properties.copy(Blocks.LECTERN).strength(2.5F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> VOIDGLASS_ORE = registerBlock("voidglass_ore", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).strength(6.0F, 8.0F).requiresCorrectToolForDrops().sound(SoundType.AMETHYST)));
    public static final RegistryObject<Block> DEEPSLATE_VOIDGLASS_ORE = registerBlock("deepslate_voidglass_ore", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.DEEPSLATE).strength(7.5F, 8.5F).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE)));
    public static final RegistryObject<Block> VOIDGLASS_BEACON = registerBlock("voidglass_beacon", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).strength(5.0F, 40.0F).lightLevel(s -> 13).noOcclusion().sound(SoundType.GLASS)));

    public static final RegistryObject<Item> RESONITE_SWORD = ITEMS.register("resonite_sword", () -> new SwordItem(ModTiers.RESONITE, 3, -2.4F, new Item.Properties()));
    public static final RegistryObject<Item> RESONITE_PICKAXE = ITEMS.register("resonite_pickaxe", () -> new PickaxeItem(ModTiers.RESONITE, 1, -2.8F, new Item.Properties()));
    public static final RegistryObject<Item> RESONITE_AXE = ITEMS.register("resonite_axe", () -> new AxeItem(ModTiers.RESONITE, 5.0F, -3.0F, new Item.Properties()));
    public static final RegistryObject<Item> RESONITE_SHOVEL = ITEMS.register("resonite_shovel", () -> new ShovelItem(ModTiers.RESONITE, 1.5F, -3.0F, new Item.Properties()));
    public static final RegistryObject<Item> RESONITE_HOE = ITEMS.register("resonite_hoe", () -> new HoeItem(ModTiers.RESONITE, -4, 0.0F, new Item.Properties()));
    public static final RegistryObject<Item> RESONITE_HELMET = ITEMS.register("resonite_helmet", () -> new ArmorItem(ModArmorMaterials.RESONITE, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> RESONITE_CHESTPLATE = ITEMS.register("resonite_chestplate", () -> new ArmorItem(ModArmorMaterials.RESONITE, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> RESONITE_LEGGINGS = ITEMS.register("resonite_leggings", () -> new ArmorItem(ModArmorMaterials.RESONITE, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> RESONITE_BOOTS = ITEMS.register("resonite_boots", () -> new ArmorItem(ModArmorMaterials.RESONITE, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static final RegistryObject<Item> VOIDGLASS_SWORD = ITEMS.register("voidglass_sword", () -> new SwordItem(ModTiers.VOIDGLASS, 4, -2.2F, new Item.Properties()));
    public static final RegistryObject<Item> VOIDGLASS_PICKAXE = ITEMS.register("voidglass_pickaxe", () -> new PickaxeItem(ModTiers.VOIDGLASS, 1, -2.6F, new Item.Properties()));
    public static final RegistryObject<Item> VOIDGLASS_AXE = ITEMS.register("voidglass_axe", () -> new AxeItem(ModTiers.VOIDGLASS, 6.0F, -2.9F, new Item.Properties()));
    public static final RegistryObject<Item> VOIDGLASS_SHOVEL = ITEMS.register("voidglass_shovel", () -> new ShovelItem(ModTiers.VOIDGLASS, 2.0F, -2.9F, new Item.Properties()));
    public static final RegistryObject<Item> VOIDGLASS_HOE = ITEMS.register("voidglass_hoe", () -> new HoeItem(ModTiers.VOIDGLASS, -3, 0.5F, new Item.Properties()));
    public static final RegistryObject<Item> VOIDGLASS_HELMET = ITEMS.register("voidglass_helmet", () -> new ArmorItem(ModArmorMaterials.VOIDGLASS, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> VOIDGLASS_CHESTPLATE = ITEMS.register("voidglass_chestplate", () -> new ArmorItem(ModArmorMaterials.VOIDGLASS, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> VOIDGLASS_LEGGINGS = ITEMS.register("voidglass_leggings", () -> new ArmorItem(ModArmorMaterials.VOIDGLASS, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> VOIDGLASS_BOOTS = ITEMS.register("voidglass_boots", () -> new ArmorItem(ModArmorMaterials.VOIDGLASS, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static final RegistryObject<Enchantment> ECHO_STRIKE = ENCHANTMENTS.register("echo_strike", ModEnchantments.EchoStrike::new);
    public static final RegistryObject<Enchantment> DEEP_STEP = ENCHANTMENTS.register("deep_step", ModEnchantments.DeepStep::new);
    public static final RegistryObject<Enchantment> RESONANT_GUARD = ENCHANTMENTS.register("resonant_guard", ModEnchantments.ResonantGuard::new);
    public static final RegistryObject<Enchantment> VOID_WALKER = ENCHANTMENTS.register("void_walker", ModEnchantments.VoidWalker::new);

    public static final RegistryObject<EntityType<EchoStalker>> ECHO_STALKER = ENTITIES.register("echo_stalker", () -> EntityType.Builder.of(EchoStalker::new, MobCategory.MONSTER).sized(0.6F, 1.95F).clientTrackingRange(8).build(new ResourceLocation(MOD_ID, "echo_stalker").toString()));
    public static final RegistryObject<EntityType<CrystalCrawler>> CRYSTAL_CRAWLER = ENTITIES.register("crystal_crawler", () -> EntityType.Builder.of(CrystalCrawler::new, MobCategory.MONSTER).sized(1.4F, 0.9F).clientTrackingRange(8).build(new ResourceLocation(MOD_ID, "crystal_crawler").toString()));
    public static final RegistryObject<EntityType<EchoGuardian>> ECHO_GUARDIAN = ENTITIES.register("echo_guardian", () -> EntityType.Builder.of(EchoGuardian::new, MobCategory.MONSTER).sized(0.8F, 2.4F).clientTrackingRange(10).build(new ResourceLocation(MOD_ID, "echo_guardian").toString()));
    public static final RegistryObject<EntityType<ResonantColossus>> RESONANT_COLOSSUS = ENTITIES.register("resonant_colossus", () -> EntityType.Builder.of(ResonantColossus::new, MobCategory.MONSTER).sized(1.15F, 2.7F).clientTrackingRange(12).build(new ResourceLocation(MOD_ID, "resonant_colossus").toString()));
    public static final RegistryObject<EntityType<AbyssHerald>> ABYSS_HERALD = ENTITIES.register("abyss_herald", () -> EntityType.Builder.of(AbyssHerald::new, MobCategory.MONSTER).sized(0.8F, 2.35F).clientTrackingRange(12).build(new ResourceLocation(MOD_ID, "abyss_herald").toString()));
    public static final RegistryObject<EntityType<ResonanceTyrant>> RESONANCE_TYRANT = ENTITIES.register("resonance_tyrant", () -> EntityType.Builder.of(ResonanceTyrant::new, MobCategory.MONSTER).sized(1.0F, 2.8F).clientTrackingRange(14).build(new ResourceLocation(MOD_ID, "resonance_tyrant").toString()));
    public static final RegistryObject<EntityType<VoidglassWraith>> VOIDGLASS_WRAITH = ENTITIES.register("voidglass_wraith", () -> EntityType.Builder.of(VoidglassWraith::new, MobCategory.MONSTER).sized(0.7F, 2.1F).clientTrackingRange(10).build(new ResourceLocation(MOD_ID, "voidglass_wraith").toString()));
    public static final RegistryObject<EntityType<HollowSovereign>> HOLLOW_SOVEREIGN = ENTITIES.register("hollow_sovereign", () -> EntityType.Builder.of(HollowSovereign::new, MobCategory.MONSTER).sized(1.05F, 2.9F).clientTrackingRange(16).build(new ResourceLocation(MOD_ID, "hollow_sovereign").toString()));

    public static final RegistryObject<Item> ECHO_STALKER_EGG = ITEMS.register("echo_stalker_spawn_egg", () -> new ForgeSpawnEggItem(ECHO_STALKER, 0x182337, 0x7C4DFF, new Item.Properties()));
    public static final RegistryObject<Item> CRYSTAL_CRAWLER_EGG = ITEMS.register("crystal_crawler_spawn_egg", () -> new ForgeSpawnEggItem(CRYSTAL_CRAWLER, 0x261F33, 0xB76CFF, new Item.Properties()));
    public static final RegistryObject<Item> ECHO_GUARDIAN_EGG = ITEMS.register("echo_guardian_spawn_egg", () -> new ForgeSpawnEggItem(ECHO_GUARDIAN, 0x11151F, 0xD39CFF, new Item.Properties()));
    public static final RegistryObject<Item> RESONANT_COLOSSUS_EGG = ITEMS.register("resonant_colossus_spawn_egg", () -> new ForgeSpawnEggItem(RESONANT_COLOSSUS, 0x443719, 0xE7C55E, new Item.Properties()));
    public static final RegistryObject<Item> ABYSS_HERALD_EGG = ITEMS.register("abyss_herald_spawn_egg", () -> new ForgeSpawnEggItem(ABYSS_HERALD, 0x0C1528, 0x4F78E8, new Item.Properties()));
    public static final RegistryObject<Item> RESONANCE_TYRANT_EGG = ITEMS.register("resonance_tyrant_spawn_egg", () -> new ForgeSpawnEggItem(RESONANCE_TYRANT, 0x2A0710, 0xE73755, new Item.Properties()));
    public static final RegistryObject<Item> VOIDGLASS_WRAITH_EGG = ITEMS.register("voidglass_wraith_spawn_egg", () -> new ForgeSpawnEggItem(VOIDGLASS_WRAITH, 0x120A1E, 0x9A5CFF, new Item.Properties()));
    public static final RegistryObject<Item> HOLLOW_SOVEREIGN_EGG = ITEMS.register("hollow_sovereign_spawn_egg", () -> new ForgeSpawnEggItem(HOLLOW_SOVEREIGN, 0x05040A, 0xC79CFF, new Item.Properties()));

    public static final RegistryObject<Item> COLOSSUS_SIGIL = ITEMS.register("colossus_sigil", () -> new BossSigilItem(new Item.Properties().rarity(Rarity.RARE).stacksTo(16), RESONANT_COLOSSUS, 32));
    public static final RegistryObject<Item> ABYSS_SIGIL = ITEMS.register("abyss_sigil", () -> new BossSigilItem(new Item.Properties().rarity(Rarity.EPIC).stacksTo(16), ABYSS_HERALD, 16));
    public static final RegistryObject<Item> TYRANT_SIGIL = ITEMS.register("tyrant_sigil", () -> new BossSigilItem(new Item.Properties().rarity(Rarity.EPIC).stacksTo(8), RESONANCE_TYRANT, 0));
    public static final RegistryObject<Item> SOVEREIGN_SIGIL = ITEMS.register("sovereign_sigil", () -> new BossSigilItem(new Item.Properties().rarity(Rarity.EPIC).stacksTo(4), HOLLOW_SOVEREIGN, 0));

    public static final RegistryObject<PoiType> RESONANCE_POI = POI_TYPES.register("resonance_archivist", () -> new PoiType(ImmutableSet.copyOf(RESONANCE_TABLE.get().getStateDefinition().getPossibleStates()), 1, 1));
    public static final RegistryObject<VillagerProfession> RESONANCE_ARCHIVIST = PROFESSIONS.register("resonance_archivist", () -> new VillagerProfession(
            "resonance_archivist",
            holder -> holder.is(RESONANCE_POI.getKey()), holder -> holder.is(RESONANCE_POI.getKey()),
            ImmutableSet.of(), ImmutableSet.of(), SoundEvents.VILLAGER_WORK_LIBRARIAN));

    public static final RegistryObject<Feature<NoneFeatureConfiguration>> ECHO_RUINS = FEATURES.register("echo_ruins", () -> new EchoRuinsFeature(NoneFeatureConfiguration.CODEC));

    public static final RegistryObject<CreativeModeTab> ECHOING_DEPTHS_TAB = CREATIVE_MODE_TABS.register("echoing_depths_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("creativetab.echoing_depths"))
            .icon(() -> new ItemStack(RESONITE_INGOT.get()))
            .displayItems((parameters, output) -> {
                output.accept(ECHO_COMPASS.get()); output.accept(RESONANCE_HORN.get());
                output.accept(RAW_RESONITE.get()); output.accept(RESONITE_INGOT.get()); output.accept(RESONANCE_CORE.get()); output.accept(RESONANT_SHARD.get());
                output.accept(RESONITE_SWORD.get()); output.accept(RESONITE_PICKAXE.get()); output.accept(RESONITE_AXE.get()); output.accept(RESONITE_SHOVEL.get()); output.accept(RESONITE_HOE.get());
                output.accept(RESONITE_HELMET.get()); output.accept(RESONITE_CHESTPLATE.get()); output.accept(RESONITE_LEGGINGS.get()); output.accept(RESONITE_BOOTS.get());
                output.accept(RAW_VOIDGLASS.get()); output.accept(VOIDGLASS_INGOT.get()); output.accept(VOIDGLASS_SHARD.get());
                output.accept(VOIDGLASS_SWORD.get()); output.accept(VOIDGLASS_PICKAXE.get()); output.accept(VOIDGLASS_AXE.get()); output.accept(VOIDGLASS_SHOVEL.get()); output.accept(VOIDGLASS_HOE.get());
                output.accept(VOIDGLASS_HELMET.get()); output.accept(VOIDGLASS_CHESTPLATE.get()); output.accept(VOIDGLASS_LEGGINGS.get()); output.accept(VOIDGLASS_BOOTS.get());
                output.accept(ECHO_SIGIL.get()); output.accept(CRYSTAL_FANG.get()); output.accept(COLOSSUS_HEART.get()); output.accept(ABYSSAL_EYE.get()); output.accept(TYRANT_CROWN.get()); output.accept(SOVEREIGN_CORE.get());
                output.accept(COLOSSUS_SIGIL.get()); output.accept(ABYSS_SIGIL.get()); output.accept(TYRANT_SIGIL.get()); output.accept(SOVEREIGN_SIGIL.get());
                output.accept(RESONANCE_TABLE.get()); output.accept(VOIDGLASS_BEACON.get());
                output.accept(RESONITE_ORE.get()); output.accept(DEEPSLATE_RESONITE_ORE.get()); output.accept(VOIDGLASS_ORE.get()); output.accept(DEEPSLATE_VOIDGLASS_ORE.get());
                output.accept(ECHO_STALKER_EGG.get()); output.accept(CRYSTAL_CRAWLER_EGG.get()); output.accept(ECHO_GUARDIAN_EGG.get()); output.accept(RESONANT_COLOSSUS_EGG.get());
                output.accept(ABYSS_HERALD_EGG.get()); output.accept(RESONANCE_TYRANT_EGG.get()); output.accept(VOIDGLASS_WRAITH_EGG.get()); output.accept(HOLLOW_SOVEREIGN_EGG.get());
            }).build());

    public EchoingDepths() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ITEMS.register(bus); BLOCKS.register(bus); ENCHANTMENTS.register(bus); ENTITIES.register(bus);
        POI_TYPES.register(bus); PROFESSIONS.register(bus); FEATURES.register(bus); CREATIVE_MODE_TABS.register(bus);
    }

    private static RegistryObject<Block> registerBlock(String name, java.util.function.Supplier<Block> supplier) {
        RegistryObject<Block> block = BLOCKS.register(name, supplier);
        ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
        return block;
    }

}
