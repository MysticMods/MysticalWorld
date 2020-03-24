package epicsquid.mysticalworld.init;

import com.tterrag.registrate.util.RegistryEntry;
import com.tterrag.registrate.util.nullness.NonNullFunction;
import epicsquid.mysticallib.item.*;
import epicsquid.mysticallib.material.MaterialType;
import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.items.*;
import epicsquid.mysticalworld.items.amethyst.AmethystArmorItem;
import epicsquid.mysticalworld.items.copper.CopperArmorItem;
import epicsquid.mysticalworld.items.lead.LeadArmorItem;
import epicsquid.mysticalworld.items.quicksilver.*;
import epicsquid.mysticalworld.items.silver.*;
import epicsquid.mysticalworld.items.tin.TinArmorItem;
import net.minecraft.block.Block;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.RegistryObject;

import static epicsquid.mysticalworld.MysticalWorld.REGISTRATE;
import static epicsquid.mysticalworld.init.ModMaterials.*;
import static epicsquid.mysticallib.material.MaterialType.Type;

// TODO: Convert to Registrate

@SuppressWarnings("unused")
public class ModItems {
  @FunctionalInterface
  public interface ToolBuilder<V extends Item> {
    V apply(IItemTier tier, int attackDamageIn, float attackSpeedIn, Item.Properties builder);
  }

  @FunctionalInterface
  public interface HoeBuilder<V extends Item> {
    V apply(IItemTier tier, float attackSpeedIn, Item.Properties builder);
  }

  @FunctionalInterface
  public interface ArmorBuilder<V extends Item> {
    V apply(IArmorMaterial materialIn, EquipmentSlotType slot, Item.Properties builder);
  }

  public static <T extends Item> NonNullFunction<Item.Properties, T> tool(ToolBuilder<T> builder, MaterialType.Type matType, MaterialType material) {
    return (b) -> builder.apply(material.getItemMaterial(), material.getDamage(matType), material.getSpeed(matType), b);
  }

  public static NonNullFunction<Item.Properties, SwordItem> sword(ToolBuilder<SwordItem> builder, MaterialType material) {
    return tool(builder, Type.SWORD, material);
  }

  public static NonNullFunction<Item.Properties, SpearItem> spear(ToolBuilder<SpearItem> builder, MaterialType material) {
    return tool(builder, Type.SPEAR, material);
  }

  public static NonNullFunction<Item.Properties, PickaxeItem> pickaxe(ToolBuilder<PickaxeItem> builder, MaterialType material) {
    return tool(builder, Type.PICKAXE, material);
  }

  public static NonNullFunction<Item.Properties, AxeItem> axe(ToolBuilder<AxeItem> builder, MaterialType material) {
    return tool(builder, Type.AXE, material);
  }

  public static NonNullFunction<Item.Properties, ShovelItem> shovel(ToolBuilder<ShovelItem> builder, MaterialType material) {
    return tool(builder, Type.SHOVEL, material);
  }

  public static NonNullFunction<Item.Properties, KnifeItem> knife(ToolBuilder<KnifeItem> builder, MaterialType material) {
    return tool(builder, Type.KNIFE, material);
  }

  public static NonNullFunction<Item.Properties, HoeItem> hoe(HoeBuilder<HoeItem> builder, MaterialType material) {
    return (b) -> builder.apply(material.getItemMaterial(), material.getSpeed(Type.HOE), b);
  }

  public static NonNullFunction<Item.Properties, ArmorItem> armor(ArmorBuilder<ArmorItem> builder, MaterialType material, EquipmentSlotType slot) {
    return (b) -> builder.apply(material.getArmorMaterial(), slot, b);
  }

  public static NonNullFunction<Item.Properties, DyeItem> dyeItem (DyeColor color) {
    return (b) -> new DyeItem(color, b);
  }

  public static <T extends Block> NonNullFunction<Item.Properties, BlockNamedItem> blockNamedItem (RegistryEntry<T> block) {
    return (b) -> new BlockNamedItem(block.get(), b);
  }

  public static RegistryEntry<GuideItem> ENCYCLOPEDIA = REGISTRATE.item("encyclopedia", GuideItem::new).register();

  public static RegistryEntry<DyeItem> CARAPACE = REGISTRATE.item("carapace", dyeItem(DyeColor.BLUE)).register();

  public static RegistryEntry<Item> PELT = REGISTRATE.item("pelt", Item::new).register();

  public static RegistryEntry<Item> ANTLERS = REGISTRATE.item("antlers", Item::new).register();

  public static RegistryEntry<DyeItem> INK_BOTTLE = REGISTRATE.item("ink_bottle", dyeItem(DyeColor.BLACK))
      .properties(o -> o.containerItem(Items.GLASS_BOTTLE))
      .register();

  public static RegistryEntry<UnripePearlItem> YOUNG_PEARL = REGISTRATE.item("young_pearl", UnripePearlItem::new).register();

  public static RegistryEntry<NautilusHornBase.NautilusHorn> NAUTILUS_HORN = REGISTRATE.item("nautilus_horn", NautilusHornBase.NautilusHorn::new)
      .properties(o -> o.maxDamage(32).rarity(Rarity.RARE))
      .register();

  public static RegistryEntry<NautilusHornBase.GlisteringHorn> GLISTERING_HORN = REGISTRATE.item("glistering_horn", NautilusHornBase.GlisteringHorn::new)
      .properties(o -> o.maxDamage(64).rarity(Rarity.EPIC))
      .register();

  public static RegistryEntry<SilkwormEgg> SILKWORM_EGG = REGISTRATE.item("silkworm_egg", SilkwormEgg::new).register();

  public static RegistryEntry<Item> SILK_COCOON = REGISTRATE.item("silk_cocoon", Item::new).register();

  public static RegistryEntry<Item> SILK_THREAD = REGISTRATE.item("silk_thread", Item::new).register();

  public static RegistryEntry<Item> SPINDLE = REGISTRATE.item("spindle", Item::new)
      .properties(o -> o.maxDamage(64))
      .register();

  public static RegistryEntry<Item> VENISON = REGISTRATE.item("venison", Item::new)
      .properties(o -> o.food(ModFoods.VENISON))
      .register();

  public static RegistryEntry<Item> COOKED_VENISON = REGISTRATE.item("cooked_venison", Item::new)
      .properties(o -> o.food(ModFoods.COOKED_VENISON))
      .register();

  public static RegistryEntry<BlockNamedItem> AUBERGINE_SEEDS = REGISTRATE.item("aubergine_seeds", blockNamedItem(ModBlocks.AUBERGINE_CROP)).register();

  public static RegistryEntry<Item> COOKED_BEETROOT = REGISTRATE.item("cooked_beetroot", Item::new)
      .properties(o -> o.food(ModFoods.COOKED_BEETROOT))
      .register();

  public static RegistryEntry<FastFoodItem> SLICED_CARROT = REGISTRATE.item("sliced_carrot", FastFoodItem::new)
      .properties(o -> o.food(ModFoods.SLICED_CARROT))
      .register();

  public static RegistryEntry<Item> COOKED_CARROT = REGISTRATE.item("cooked_carrot", Item::new)
      .properties(o -> o.food(ModFoods.COOKED_CARROT))
      .register();

  public static RegistryEntry<Item> AUBERGINE = REGISTRATE.item("aubergine", Item::new)
      .properties(o -> o.food(ModFoods.AUBERGINE))
      .register();

  public static RegistryEntry<Item> COOKED_AUBERGINE = REGISTRATE.item("cooked_aubergine", Item::new)
      .properties(o -> o.food(ModFoods.COOKED_AUBERGINE))
      .register();

  public static RegistryEntry<Item> STUFFED_AUBERGINE = REGISTRATE.item("stuffed_aubergine", Item::new)
      .properties(o -> o.food(ModFoods.STUFFED_AUBERGINE))
      .register();

  public static RegistryEntry<Item> RAW_SQUID = REGISTRATE.item("raw_squid", Item::new)
      .properties(o -> o.food(ModFoods.RAW_SQUID))
      .register();

  public static RegistryEntry<Item> COOKED_SQUID = REGISTRATE.item("cooked_squid", Item::new)
      .properties(o -> o.food(ModFoods.COOKED_SQUID))
      .register();

  public static RegistryEntry<EffectItem> EPIC_SQUID = REGISTRATE.item("epic_squid", EffectItem::new)
      .properties(o -> o.food(ModFoods.EPIC_SQUID).rarity(Rarity.EPIC))
      .register();

  // Drinkies

  public static RegistryEntry<DrinkItem> APPLE_CORDIAL = REGISTRATE.item("apple_cordial", DrinkItem::new)
      .properties(o -> o.food(ModFoods.APPLE_CORDIAL))
      .register();

  public static RegistryEntry<DrinkItem> CACTUS_SYRUP = REGISTRATE.item("cactus_syrup", DrinkItem::new)
      .properties(o -> o.food(ModFoods.CACTUS_SYRUP))
      .register();

  public static RegistryEntry<DrinkItem> DANDELION_CORDIAL = REGISTRATE.item("dandelion_cordial", DrinkItem::new)
      .properties(o -> o.food(ModFoods.DANDELION_CORDIAL))
      .register();

  public static RegistryEntry<DrinkItem> LILAC_CORDIAL = REGISTRATE.item("lilac_cordial", DrinkItem::new)
      .properties(o -> o.food(ModFoods.LILAC_CORDIAL))
      .register();

  public static RegistryEntry<DrinkItem> PEONY_CORDIAL = REGISTRATE.item("peony_cordial", DrinkItem::new)
      .properties(o -> o.food(ModFoods.PEONY_CORDIAL))
      .register();

  public static RegistryEntry<DrinkItem> ROSE_CORDIAL = REGISTRATE.item("rose_cordial", DrinkItem::new)
      .properties(o -> o.food(ModFoods.ROSE_CORDIAL))
      .register();

  public static RegistryEntry<DrinkItem> VINEGAR = REGISTRATE.item("vinegar", DrinkItem::new)
      .properties(o -> o.food(ModFoods.VINEGAR))
      .register();

  public static RegistryEntry<DrinkItem> VEGETABLE_JUICE = REGISTRATE.item("vegetable_juice", DrinkItem::new)
      .properties(o -> o.food(ModFoods.VEGETABLE_JUICE))
      .register();

  // Salads
  public static RegistryEntry<SoupItem> AUBERGINE_SALAD = REGISTRATE.item("aubergine_salad", SoupItem::new)
      .properties(o -> o.food(ModFoods.AUBERGINE_SALAD))
      .register();

  public static RegistryEntry<SoupItem> BEETROOT_SALAD = REGISTRATE.item("beetroot_salad", SoupItem::new)
      .properties(o -> o.food(ModFoods.BEETROOT_SALAD))
      .register();

  public static RegistryEntry<SoupItem> CACTUS_DANDELION_SALAD = REGISTRATE.item("cactus_dandelion_salad", SoupItem::new)
      .properties(o -> o.food(ModFoods.CACTUS_DANDELION_SALAD))
      .register();

  public static RegistryEntry<SoupItem> DANDELION_CORNFLOWER_SALAD = REGISTRATE.item("dandelion_cornflower_salad", SoupItem::new)
      .properties(o -> o.food(ModFoods.DANDELION_CORNFLOWER_SALAD))
      .register();

  public static RegistryEntry<SoupItem> STEWED_EGGPLANT = REGISTRATE.item("stewed_eggplant", SoupItem::new)
      .properties(o -> o.food(ModFoods.STEWED_EGGPLANT))
      .register();

  public static RegistryEntry<Item> ROTTEN_APPLE = REGISTRATE.item("rotten_apple", Item::new)
      .properties(o -> o.rarity(Rarity.RARE))
      .register();

  // Ingots/gems
  public static RegistryEntry<Item> AMETHYST_GEM = REGISTRATE.item(AMETHYST.getInternalName(), Item::new).register();
  public static RegistryEntry<Item> COPPER_INGOT = REGISTRATE.item(COPPER.getInternalName(), Item::new).register();
  public static RegistryEntry<Item> LEAD_INGOT = REGISTRATE.item(LEAD.getInternalName(), Item::new).register();
  public static RegistryEntry<Item> QUICKSILVER_INGOT = REGISTRATE.item(QUICKSILVER.getInternalName(), Item::new).register();
  public static RegistryEntry<Item> SILVER_INGOT = REGISTRATE.item(SILVER.getInternalName(), Item::new).register();
  public static RegistryEntry<Item> TIN_INGOT = REGISTRATE.item(TIN.getInternalName(), Item::new).register();

  // Nuggets
  public static RegistryEntry<Item> COPPER_NUGGET = REGISTRATE.item(COPPER.nuggetName(), Item::new).register();
  public static RegistryEntry<Item> LEAD_NUGGET = REGISTRATE.item(LEAD.nuggetName(), Item::new).register();
  public static RegistryEntry<Item> QUICKSILVER_NUGGET = REGISTRATE.item(QUICKSILVER.nuggetName(), Item::new).register();
  public static RegistryEntry<Item> SILVER_NUGGET = REGISTRATE.item(SILVER.nuggetName(), Item::new).register();
  public static RegistryEntry<Item> TIN_NUGGET = REGISTRATE.item(TIN.nuggetName(), Item::new).register();

  // Dusts
  public static RegistryEntry<Item> COPPER_DUST = REGISTRATE.item(COPPER.dustName(), Item::new).register();
  public static RegistryEntry<Item> LEAD_DUST = REGISTRATE.item(LEAD.dustName(), Item::new).register();
  public static RegistryEntry<Item> QUICKSILVER_DUST = REGISTRATE.item(QUICKSILVER.dustName(), Item::new).register();
  public static RegistryEntry<Item> SILVER_DUST = REGISTRATE.item(SILVER.dustName(), Item::new).register();
  public static RegistryEntry<Item> TIN_DUST = REGISTRATE.item(TIN.dustName(), Item::new).register();
  public static RegistryEntry<Item> GOLD_DUST = REGISTRATE.item(GOLD.dustName(), Item::new).register();
  public static RegistryEntry<Item> IRON_DUST = REGISTRATE.item(IRON.dustName(), Item::new).register();

  // Amethyst Tools
  public static RegistryEntry<AxeItem> AMETHYST_AXE = REGISTRATE.item(AMETHYST.getInternalName() + "_axe", axe(AxeItem::new, AMETHYST)).register();
  public static RegistryEntry<HoeItem> AMETHYST_HOE = REGISTRATE.item(AMETHYST.getInternalName() + "_hoe", hoe(HoeItem::new, AMETHYST)).register();
  public static RegistryEntry<KnifeItem> AMETHYST_KNIFE = REGISTRATE.item(AMETHYST.getInternalName() + "_knife", knife(KnifeItem::new, AMETHYST)).register();
  public static RegistryEntry<PickaxeItem> AMETHYST_PICKAXE = REGISTRATE.item(AMETHYST.getInternalName() + "_pickaxe", pickaxe(PickaxeItem::new, AMETHYST)).register();
  public static RegistryEntry<ShovelItem> AMETHYST_SHOVEL = REGISTRATE.item(AMETHYST.getInternalName() + "_shovel", shovel(ShovelItem::new, AMETHYST)).register();
  public static RegistryEntry<SwordItem> AMETHYST_SWORD = REGISTRATE.item(AMETHYST.getInternalName() + "_sword", sword(SwordItem::new, AMETHYST)).register();
  public static RegistryEntry<SpearItem> AMETHYST_SPEAR = REGISTRATE.item(AMETHYST.getInternalName() + "_spear", spear(SpearItem::new, AMETHYST)).register();

  // Cactus
  public static RegistryEntry<AxeItem> CACTUS_AXE = REGISTRATE.item(CACTUS.getInternalName() + "_axe", axe(AxeItem::new, CACTUS)).register();
  public static RegistryEntry<HoeItem> CACTUS_HOE = REGISTRATE.item(CACTUS.getInternalName() + "_hoe", hoe(HoeItem::new, CACTUS)).register();
  public static RegistryEntry<KnifeItem> CACTUS_KNIFE = REGISTRATE.item(CACTUS.getInternalName() + "_knife", knife(KnifeItem::new, CACTUS)).register();
  public static RegistryEntry<PickaxeItem> CACTUS_PICKAXE = REGISTRATE.item(CACTUS.getInternalName() + "_pickaxe", pickaxe(PickaxeItem::new, CACTUS)).register();
  public static RegistryEntry<ShovelItem> CACTUS_SHOVEL = REGISTRATE.item(CACTUS.getInternalName() + "_shovel", shovel(ShovelItem::new, CACTUS)).register();
  public static RegistryEntry<SwordItem> CACTUS_SWORD = REGISTRATE.item(CACTUS.getInternalName() + "_sword", sword(SwordItem::new, CACTUS)).register();
  public static RegistryEntry<SpearItem> CACTUS_SPEAR = REGISTRATE.item(CACTUS.getInternalName() + "_spear", spear(SpearItem::new, CACTUS)).register();

  // COPPER
  public static RegistryEntry<AxeItem> COPPER_AXE = REGISTRATE.item(COPPER.getInternalName() + "_axe", axe(AxeItem::new, COPPER)).register();
  public static RegistryEntry<HoeItem> COPPER_HOE = REGISTRATE.item(COPPER.getInternalName() + "_hoe", hoe(HoeItem::new, COPPER)).register();
  public static RegistryEntry<KnifeItem> COPPER_KNIFE = REGISTRATE.item(COPPER.getInternalName() + "_knife", knife(KnifeItem::new, COPPER)).register();
  public static RegistryEntry<PickaxeItem> COPPER_PICKAXE = REGISTRATE.item(COPPER.getInternalName() + "_pickaxe", pickaxe(PickaxeItem::new, COPPER)).register();
  public static RegistryEntry<ShovelItem> COPPER_SHOVEL = REGISTRATE.item(COPPER.getInternalName() + "_shovel", shovel(ShovelItem::new, COPPER)).register();
  public static RegistryEntry<SwordItem> COPPER_SWORD = REGISTRATE.item(COPPER.getInternalName() + "_sword", sword(SwordItem::new, COPPER)).register();
  public static RegistryEntry<SpearItem> COPPER_SPEAR = REGISTRATE.item(COPPER.getInternalName() + "_spear", spear(SpearItem::new, COPPER)).register();

  // LEAD
  public static RegistryEntry<AxeItem> LEAD_AXE = REGISTRATE.item(LEAD.getInternalName() + "_axe", axe(AxeItem::new, LEAD)).register();
  public static RegistryEntry<HoeItem> LEAD_HOE = REGISTRATE.item(LEAD.getInternalName() + "_hoe", hoe(HoeItem::new, LEAD)).register();
  public static RegistryEntry<KnifeItem> LEAD_KNIFE = REGISTRATE.item(LEAD.getInternalName() + "_knife", knife(KnifeItem::new, LEAD)).register();
  public static RegistryEntry<PickaxeItem> LEAD_PICKAXE = REGISTRATE.item(LEAD.getInternalName() + "_pickaxe", pickaxe(PickaxeItem::new, LEAD)).register();
  public static RegistryEntry<ShovelItem> LEAD_SHOVEL = REGISTRATE.item(LEAD.getInternalName() + "_shovel", shovel(ShovelItem::new, LEAD)).register();
  public static RegistryEntry<SwordItem> LEAD_SWORD = REGISTRATE.item(LEAD.getInternalName() + "_sword", sword(SwordItem::new, LEAD)).register();
  public static RegistryEntry<SpearItem> LEAD_SPEAR = REGISTRATE.item(LEAD.getInternalName() + "_spear", spear(SpearItem::new, LEAD)).register();

  // QUICKSILVER
  public static RegistryEntry<AxeItem> QUICKSILVER_AXE = REGISTRATE.item(QUICKSILVER.getInternalName() + "_axe", axe(AxeItem::new, QUICKSILVER)).register();
  public static RegistryEntry<HoeItem> QUICKSILVER_HOE = REGISTRATE.item(QUICKSILVER.getInternalName() + "_hoe", hoe(HoeItem::new, QUICKSILVER)).register();
  public static RegistryEntry<KnifeItem> QUICKSILVER_KNIFE = REGISTRATE.item(QUICKSILVER.getInternalName() + "_knife", knife(KnifeItem::new, QUICKSILVER)).register();
  public static RegistryEntry<PickaxeItem> QUICKSILVER_PICKAXE = REGISTRATE.item(QUICKSILVER.getInternalName() + "_pickaxe", pickaxe(PickaxeItem::new, QUICKSILVER)).register();
  public static RegistryEntry<ShovelItem> QUICKSILVER_SHOVEL = REGISTRATE.item(QUICKSILVER.getInternalName() + "_shovel", shovel(ShovelItem::new, QUICKSILVER)).register();
  public static RegistryEntry<SwordItem> QUICKSILVER_SWORD = REGISTRATE.item(QUICKSILVER.getInternalName() + "_sword", sword(SwordItem::new, QUICKSILVER)).register();
  public static RegistryEntry<SpearItem> QUICKSILVER_SPEAR = REGISTRATE.item(QUICKSILVER.getInternalName() + "_spear", spear(SpearItem::new, QUICKSILVER)).register();

  // SILVER
  public static RegistryEntry<AxeItem> SILVER_AXE = REGISTRATE.item(SILVER.getInternalName() + "_axe", axe(AxeItem::new, SILVER)).register();
  public static RegistryEntry<HoeItem> SILVER_HOE = REGISTRATE.item(SILVER.getInternalName() + "_hoe", hoe(HoeItem::new, SILVER)).register();
  public static RegistryEntry<KnifeItem> SILVER_KNIFE = REGISTRATE.item(SILVER.getInternalName() + "_knife", knife(KnifeItem::new, SILVER)).register();
  public static RegistryEntry<PickaxeItem> SILVER_PICKAXE = REGISTRATE.item(SILVER.getInternalName() + "_pickaxe", pickaxe(PickaxeItem::new, SILVER)).register();
  public static RegistryEntry<ShovelItem> SILVER_SHOVEL = REGISTRATE.item(SILVER.getInternalName() + "_shovel", shovel(ShovelItem::new, SILVER)).register();
  public static RegistryEntry<SwordItem> SILVER_SWORD = REGISTRATE.item(SILVER.getInternalName() + "_sword", sword(SwordItem::new, SILVER)).register();
  public static RegistryEntry<SpearItem> SILVER_SPEAR = REGISTRATE.item(SILVER.getInternalName() + "_spear", spear(SpearItem::new, SILVER)).register();

  // TIN
  public static RegistryEntry<AxeItem> TIN_AXE = REGISTRATE.item(TIN.getInternalName() + "_axe", axe(AxeItem::new, TIN)).register();
  public static RegistryEntry<HoeItem> TIN_HOE = REGISTRATE.item(TIN.getInternalName() + "_hoe", hoe(HoeItem::new, TIN)).register();
  public static RegistryEntry<KnifeItem> TIN_KNIFE = REGISTRATE.item(TIN.getInternalName() + "_knife", knife(KnifeItem::new, TIN)).register();
  public static RegistryEntry<PickaxeItem> TIN_PICKAXE = REGISTRATE.item(TIN.getInternalName() + "_pickaxe", pickaxe(PickaxeItem::new, TIN)).register();
  public static RegistryEntry<ShovelItem> TIN_SHOVEL = REGISTRATE.item(TIN.getInternalName() + "_shovel", shovel(ShovelItem::new, TIN)).register();
  public static RegistryEntry<SwordItem> TIN_SWORD = REGISTRATE.item(TIN.getInternalName() + "_sword", sword(SwordItem::new, TIN)).register();
  public static RegistryEntry<SpearItem> TIN_SPEAR = REGISTRATE.item(TIN.getInternalName() + "_spear", spear(SpearItem::new, TIN)).register();

  // VANILLA
  // Knives
  public static RegistryEntry<KnifeItem> STONE_KNIFE = REGISTRATE.item(STONE.getInternalName() + "_knife", knife(KnifeItem::new, STONE)).register();
  public static RegistryEntry<KnifeItem> WOODEN_KNIFE = REGISTRATE.item(WOODEN.getInternalName() + "_knife", knife(KnifeItem::new, WOODEN)).register();
  public static RegistryEntry<KnifeItem> DIAMOND_KNIFE = REGISTRATE.item(DIAMOND.getInternalName() + "_knife", knife(KnifeItem::new, DIAMOND)).register();
  public static RegistryEntry<KnifeItem> GOLD_KNIFE = REGISTRATE.item(GOLD.getInternalName() + "_knife", knife(KnifeItem::new, GOLD)).register();
  public static RegistryEntry<KnifeItem> IRON_KNIFE = REGISTRATE.item(IRON.getInternalName() + "_knife", knife(KnifeItem::new, IRON)).register();

  // Spears
  public static RegistryEntry<SpearItem> STONE_SPEAR = REGISTRATE.item(STONE.getInternalName() + "_spear", spear(SpearItem::new, STONE)).register();
  public static RegistryEntry<SpearItem> WOODEN_SPEAR = REGISTRATE.item(WOODEN.getInternalName() + "_spear", spear(SpearItem::new, WOODEN)).register();
  public static RegistryEntry<SpearItem> DIAMOND_SPEAR = REGISTRATE.item(DIAMOND.getInternalName() + "_spear", spear(SpearItem::new, DIAMOND)).register();
  public static RegistryEntry<SpearItem> GOLD_SPEAR = REGISTRATE.item(GOLD.getInternalName() + "_spear", spear(SpearItem::new, GOLD)).register();
  public static RegistryEntry<SpearItem> IRON_SPEAR = REGISTRATE.item(IRON.getInternalName() + "_spear", spear(SpearItem::new, IRON)).register();

  // Armors
  public static RegistryEntry<ArmorItem> AMETHYST_HELMET = REGISTRATE.item(AMETHYST.getInternalName() + "_helmet", armor(ArmorItem::new, AMETHYST, EquipmentSlotType.HEAD)).register();
  public static RegistryEntry<ArmorItem> AMETHYST_CHESTPLATE = REGISTRATE.item(AMETHYST.getInternalName() + "_chestplate", armor(ArmorItem::new, AMETHYST, EquipmentSlotType.CHEST)).register();
  public static RegistryEntry<ArmorItem> AMETHYST_LEGGINGS = REGISTRATE.item(AMETHYST.getInternalName() + "_leggings", armor(ArmorItem::new, AMETHYST, EquipmentSlotType.LEGS)).register();
  public static RegistryEntry<ArmorItem> AMETHYST_BOOTS = REGISTRATE.item(AMETHYST.getInternalName() + "_boots", armor(ArmorItem::new, AMETHYST, EquipmentSlotType.FEET)).register();

  // COPPER
  public static RegistryEntry<ArmorItem> COPPER_HELMET = REGISTRATE.item(COPPER.getInternalName() + "_helmet", armor(ArmorItem::new, COPPER, EquipmentSlotType.HEAD)).register();
  public static RegistryEntry<ArmorItem> COPPER_CHESTPLATE = REGISTRATE.item(COPPER.getInternalName() + "_chestplate", armor(ArmorItem::new, COPPER, EquipmentSlotType.CHEST)).register();
  public static RegistryEntry<ArmorItem> COPPER_LEGGINGS = REGISTRATE.item(COPPER.getInternalName() + "_leggings", armor(ArmorItem::new, COPPER, EquipmentSlotType.LEGS)).register();
  public static RegistryEntry<ArmorItem> COPPER_BOOTS = REGISTRATE.item(COPPER.getInternalName() + "_boots", armor(ArmorItem::new, COPPER, EquipmentSlotType.FEET)).register();

  // LEAD
  public static RegistryEntry<ArmorItem> LEAD_HELMET = REGISTRATE.item(LEAD.getInternalName() + "_helmet", armor(ArmorItem::new, LEAD, EquipmentSlotType.HEAD)).register();
  public static RegistryEntry<ArmorItem> LEAD_CHESTPLATE = REGISTRATE.item(LEAD.getInternalName() + "_chestplate", armor(ArmorItem::new, LEAD, EquipmentSlotType.CHEST)).register();
  public static RegistryEntry<ArmorItem> LEAD_LEGGINGS = REGISTRATE.item(LEAD.getInternalName() + "_leggings", armor(ArmorItem::new, LEAD, EquipmentSlotType.LEGS)).register();
  public static RegistryEntry<ArmorItem> LEAD_BOOTS = REGISTRATE.item(LEAD.getInternalName() + "_boots", armor(ArmorItem::new, LEAD, EquipmentSlotType.FEET)).register();


  // QUICKSILVER
  public static RegistryEntry<ArmorItem> QUICKSILVER_HELMET = REGISTRATE.item(QUICKSILVER.getInternalName() + "_helmet", armor(ArmorItem::new, QUICKSILVER, EquipmentSlotType.HEAD)).register();
  public static RegistryEntry<ArmorItem> QUICKSILVER_CHESTPLATE = REGISTRATE.item(QUICKSILVER.getInternalName() + "_chestplate", armor(ArmorItem::new, QUICKSILVER, EquipmentSlotType.CHEST)).register();
  public static RegistryEntry<ArmorItem> QUICKSILVER_LEGGINGS = REGISTRATE.item(QUICKSILVER.getInternalName() + "_leggings", armor(ArmorItem::new, QUICKSILVER, EquipmentSlotType.LEGS)).register();
  public static RegistryEntry<ArmorItem> QUICKSILVER_BOOTS = REGISTRATE.item(QUICKSILVER.getInternalName() + "_boots", armor(ArmorItem::new, QUICKSILVER, EquipmentSlotType.FEET)).register();

  // SILVER
  public static RegistryEntry<ArmorItem> SILVER_HELMET = REGISTRATE.item(SILVER.getInternalName() + "_helmet", armor(ArmorItem::new, SILVER, EquipmentSlotType.HEAD)).register();
  public static RegistryEntry<ArmorItem> SILVER_CHESTPLATE = REGISTRATE.item(SILVER.getInternalName() + "_chestplate", armor(ArmorItem::new, SILVER, EquipmentSlotType.CHEST)).register();
  public static RegistryEntry<ArmorItem> SILVER_LEGGINGS = REGISTRATE.item(SILVER.getInternalName() + "_leggings", armor(ArmorItem::new, SILVER, EquipmentSlotType.LEGS)).register();
  public static RegistryEntry<ArmorItem> SILVER_BOOTS = REGISTRATE.item(SILVER.getInternalName() + "_boots", armor(ArmorItem::new, SILVER, EquipmentSlotType.FEET)).register();

  // TIN
  public static RegistryEntry<ArmorItem> TIN_HELMET = REGISTRATE.item(TIN.getInternalName() + "_helmet", armor(ArmorItem::new, TIN, EquipmentSlotType.HEAD)).register();
  public static RegistryEntry<ArmorItem> TIN_CHESTPLATE = REGISTRATE.item(TIN.getInternalName() + "_chestplate", armor(ArmorItem::new, TIN, EquipmentSlotType.CHEST)).register();
  public static RegistryEntry<ArmorItem> TIN_LEGGINGS = REGISTRATE.item(TIN.getInternalName() + "_leggings", armor(ArmorItem::new, TIN, EquipmentSlotType.LEGS)).register();
  public static RegistryEntry<ArmorItem> TIN_BOOTS = REGISTRATE.item(TIN.getInternalName() + "_boots", armor(ArmorItem::new, TIN, EquipmentSlotType.FEET)).register();

  public static void load() {}

  public static void registerItems (RegistryEvent.Register<Item> event) {
    ModCompost.init();
  }
}
