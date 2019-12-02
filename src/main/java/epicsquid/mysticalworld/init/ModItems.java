package epicsquid.mysticalworld.init;

import epicsquid.mysticallib.item.EffectItem;
import epicsquid.mysticallib.item.FastFoodItem;
import epicsquid.mysticallib.item.KnifeItem;
import epicsquid.mysticallib.item.SpearItem;
import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.items.*;
import epicsquid.mysticalworld.items.amethyst.AmethystArmorItem;
import epicsquid.mysticalworld.items.copper.CopperArmorItem;
import epicsquid.mysticalworld.items.lead.LeadArmorItem;
import epicsquid.mysticalworld.items.quicksilver.*;
import epicsquid.mysticalworld.items.silver.*;
import epicsquid.mysticalworld.items.tin.TinArmorItem;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.RegistryObject;

@SuppressWarnings("unused")
public class ModItems {
  public static RegistryObject<DyeItem> CARAPACE = MysticalWorld.REGISTRY.registerItem("carapace", MysticalWorld.REGISTRY.dyeItem(DyeColor.BLUE, ModRegistries.SIG));
  public static RegistryObject<Item> PELT = MysticalWorld.REGISTRY.registerItem("pelt", MysticalWorld.REGISTRY.item(Item::new, ModRegistries.SIG));
  public static RegistryObject<Item> ANTLERS = MysticalWorld.REGISTRY.registerItem("antlers", MysticalWorld.REGISTRY.item(Item::new, ModRegistries.SIG));
  public static RegistryObject<DyeItem> INK_BOTTLE = MysticalWorld.REGISTRY.registerItem("ink_bottle", MysticalWorld.REGISTRY.dyeItem(DyeColor.BLACK, () -> new Item.Properties().group(MysticalWorld.ITEM_GROUP).containerItem(Items.GLASS_BOTTLE)));
  public static RegistryObject<UnripePearlItem> UNRIPE_ENDER_PEARL = MysticalWorld.REGISTRY.registerItem("unripe_ender_pearl", MysticalWorld.REGISTRY.item(UnripePearlItem::new, ModRegistries.SIG));
  public static RegistryObject<NautilusHorn> NAUTILUS_HORN = MysticalWorld.REGISTRY.registerItem("nautilus_horn", MysticalWorld.REGISTRY.item((prop) -> new NautilusHorn(prop, 200), () -> new Item.Properties().group(MysticalWorld.ITEM_GROUP).maxDamage(32).rarity(Rarity.RARE)));
  public static RegistryObject<NautilusHorn> GLISTERING_HORN = MysticalWorld.REGISTRY.registerItem("glistering_horn", MysticalWorld.REGISTRY.item((prop) -> new NautilusHorn(prop, 500), () -> new Item.Properties().group(MysticalWorld.ITEM_GROUP).maxDamage(64).rarity(Rarity.EPIC)));

  public static RegistryObject<SilkwormEgg> SILKWORM_EGG = MysticalWorld.REGISTRY.registerItem("silkworm_egg", MysticalWorld.REGISTRY.item(SilkwormEgg::new, ModRegistries.SIG));
  public static RegistryObject<Item> SILK_COCOON = MysticalWorld.REGISTRY.registerItem("silk_cocoon", MysticalWorld.REGISTRY.item(Item::new, ModRegistries.SIG));
  public static RegistryObject<Item> SILK_THREAD = MysticalWorld.REGISTRY.registerItem("silk_thread", MysticalWorld.REGISTRY.item(Item::new, ModRegistries.SIG));
  public static RegistryObject<Item> SPINDLE = MysticalWorld.REGISTRY.registerItem("spindle", MysticalWorld.REGISTRY.item(Item::new, () -> new Item.Properties().group(MysticalWorld.ITEM_GROUP).maxDamage(64)));

  public static RegistryObject<Item> VENISON = MysticalWorld.REGISTRY.registerItem("venison", MysticalWorld.REGISTRY.item(Item::new, ModRegistries.foodProp(ModFoods.VENISON)));
  public static RegistryObject<Item> COOKED_VENISON = MysticalWorld.REGISTRY.registerItem("cooked_venison", MysticalWorld.REGISTRY.item(Item::new, ModRegistries.foodProp(ModFoods.COOKED_VENISON)));

  public static RegistryObject<BlockNamedItem> AUBERGINE_SEEDS = MysticalWorld.REGISTRY.registerItem("aubergine_seeds", MysticalWorld.REGISTRY.blockNamedItem(() -> ModBlocks.AUBERGINE_CROP, ModRegistries.SIG));

  public static RegistryObject<Item> COOKED_BEETROOT = MysticalWorld.REGISTRY.registerItem("cooked_beetroot", MysticalWorld.REGISTRY.item(Item::new, ModRegistries.foodProp(ModFoods.COOKED_BEETROOT)));
  public static RegistryObject<Item> SLICED_CARROT = MysticalWorld.REGISTRY.registerItem("sliced_carrot", MysticalWorld.REGISTRY.item(FastFoodItem::new, ModRegistries.foodProp(ModFoods.SLICED_CARROT)));
  public static RegistryObject<Item> COOKED_CARROT = MysticalWorld.REGISTRY.registerItem("cooked_carrot", MysticalWorld.REGISTRY.item(Item::new, ModRegistries.foodProp(ModFoods.COOKED_CARROT)));

  public static RegistryObject<Item> AUBERGINE = MysticalWorld.REGISTRY.registerItem("aubergine", MysticalWorld.REGISTRY.item(Item::new, ModRegistries.foodProp(ModFoods.AUBERGINE)));
  public static RegistryObject<Item> COOKED_AUBERGINE = MysticalWorld.REGISTRY.registerItem("cooked_aubergine", MysticalWorld.REGISTRY.item(Item::new, ModRegistries.foodProp(ModFoods.COOKED_AUBERGINE)));
  public static RegistryObject<Item> STUFFED_AUBERGINE = MysticalWorld.REGISTRY.registerItem("stuffed_aubergine", MysticalWorld.REGISTRY.item(Item::new, ModRegistries.foodProp(ModFoods.STUFFED_AUBERGINE)));

  public static RegistryObject<Item> RAW_SQUID = MysticalWorld.REGISTRY.registerItem("raw_squid", MysticalWorld.REGISTRY.item(Item::new, ModRegistries.foodProp(ModFoods.RAW_SQUID)));
  public static RegistryObject<Item> COOKED_SQUID = MysticalWorld.REGISTRY.registerItem("cooked_squid", MysticalWorld.REGISTRY.item(Item::new, ModRegistries.foodProp(ModFoods.COOKED_SQUID)));
  public static RegistryObject<EffectItem> EPIC_SQUID = MysticalWorld.REGISTRY.registerItem("epic_squid", MysticalWorld.REGISTRY.item(EffectItem::new, () -> new Item.Properties().group(MysticalWorld.ITEM_GROUP).food(ModFoods.EPIC_SQUID).rarity(Rarity.EPIC)));

  public static RegistryObject<Item> ROTTEN_APPLE = MysticalWorld.REGISTRY.registerItem("rotten_apple", MysticalWorld.REGISTRY.item(Item::new, () -> new Item.Properties().group(MysticalWorld.ITEM_GROUP).rarity(Rarity.RARE)));

  // Ingots/gems
  public static RegistryObject<Item> AMETHYST_GEM = MysticalWorld.REGISTRY.registerItem(ModMaterials.AMETHYST.getInternalName(), MysticalWorld.REGISTRY.item(Item::new, ModRegistries.SMG));
  public static RegistryObject<Item> COPPER_INGOT = MysticalWorld.REGISTRY.registerItem(ModMaterials.COPPER.ingotName(), MysticalWorld.REGISTRY.item(Item::new, ModRegistries.SMG));
  public static RegistryObject<Item> LEAD_INGOT = MysticalWorld.REGISTRY.registerItem(ModMaterials.LEAD.ingotName(), MysticalWorld.REGISTRY.item(Item::new, ModRegistries.SMG));
  public static RegistryObject<Item> QUICKSILVER_INGOT = MysticalWorld.REGISTRY.registerItem(ModMaterials.QUICKSILVER.ingotName(), MysticalWorld.REGISTRY.item(Item::new, ModRegistries.SMG));
  public static RegistryObject<Item> SILVER_INGOT = MysticalWorld.REGISTRY.registerItem(ModMaterials.SILVER.ingotName(), MysticalWorld.REGISTRY.item(Item::new, ModRegistries.SMG));
  public static RegistryObject<Item> TIN_INGOT = MysticalWorld.REGISTRY.registerItem(ModMaterials.TIN.ingotName(), MysticalWorld.REGISTRY.item(Item::new, ModRegistries.SMG));

  // Nuggets
  public static RegistryObject<Item> COPPER_NUGGET = MysticalWorld.REGISTRY.registerItem(ModMaterials.COPPER.nuggetName(), MysticalWorld.REGISTRY.item(Item::new, ModRegistries.SMG));
  public static RegistryObject<Item> LEAD_NUGGET = MysticalWorld.REGISTRY.registerItem(ModMaterials.LEAD.nuggetName(), MysticalWorld.REGISTRY.item(Item::new, ModRegistries.SMG));
  public static RegistryObject<Item> QUICKSILVER_NUGGET = MysticalWorld.REGISTRY.registerItem(ModMaterials.QUICKSILVER.nuggetName(), MysticalWorld.REGISTRY.item(Item::new, ModRegistries.SMG));
  public static RegistryObject<Item> SILVER_NUGGET = MysticalWorld.REGISTRY.registerItem(ModMaterials.SILVER.nuggetName(), MysticalWorld.REGISTRY.item(Item::new, ModRegistries.SMG));
  public static RegistryObject<Item> TIN_NUGGET = MysticalWorld.REGISTRY.registerItem(ModMaterials.TIN.nuggetName(), MysticalWorld.REGISTRY.item(Item::new, ModRegistries.SMG));

  // Dusts
  public static RegistryObject<Item> COPPER_DUST = MysticalWorld.REGISTRY.registerItem(ModMaterials.COPPER.dustName(), MysticalWorld.REGISTRY.item(Item::new, ModRegistries.SMG));
  public static RegistryObject<Item> LEAD_DUST = MysticalWorld.REGISTRY.registerItem(ModMaterials.LEAD.dustName(), MysticalWorld.REGISTRY.item(Item::new, ModRegistries.SMG));
  public static RegistryObject<Item> QUICKSILVER_DUST = MysticalWorld.REGISTRY.registerItem(ModMaterials.QUICKSILVER.dustName(), MysticalWorld.REGISTRY.item(Item::new, ModRegistries.SMG));
  public static RegistryObject<Item> SILVER_DUST = MysticalWorld.REGISTRY.registerItem(ModMaterials.SILVER.dustName(), MysticalWorld.REGISTRY.item(Item::new, ModRegistries.SMG));
  public static RegistryObject<Item> TIN_DUST = MysticalWorld.REGISTRY.registerItem(ModMaterials.TIN.dustName(), MysticalWorld.REGISTRY.item(Item::new, ModRegistries.SMG));
  public static RegistryObject<Item> GOLD_DUST = MysticalWorld.REGISTRY.registerItem(ModMaterials.GOLD.dustName(), MysticalWorld.REGISTRY.item(Item::new, ModRegistries.SMG));
  public static RegistryObject<Item> IRON_DUST = MysticalWorld.REGISTRY.registerItem(ModMaterials.IRON.dustName(), MysticalWorld.REGISTRY.item(Item::new, ModRegistries.SMG));

  public static RegistryObject<AxeItem> AMETHYST_AXE = MysticalWorld.REGISTRY.registerItem(ModMaterials.AMETHYST.getInternalName() + "_axe", MysticalWorld.REGISTRY.axe(AxeItem::new, ModMaterials.AMETHYST, ModRegistries.SMG));
  public static RegistryObject<HoeItem> AMETHYST_HOE = MysticalWorld.REGISTRY.registerItem(ModMaterials.AMETHYST.getInternalName() + "_hoe", MysticalWorld.REGISTRY.hoe(HoeItem::new, ModMaterials.AMETHYST, ModRegistries.SMG));
  public static RegistryObject<KnifeItem> AMETHYST_KNIFE = MysticalWorld.REGISTRY.registerItem(ModMaterials.AMETHYST.getInternalName() + "_knife", MysticalWorld.REGISTRY.knife(KnifeItem::new, ModMaterials.AMETHYST, ModRegistries.SMG));
  public static RegistryObject<PickaxeItem> AMETHYST_PICKAXE = MysticalWorld.REGISTRY.registerItem(ModMaterials.AMETHYST.getInternalName() + "_pickaxe", MysticalWorld.REGISTRY.pickaxe(PickaxeItem::new, ModMaterials.AMETHYST, ModRegistries.SMG));
  public static RegistryObject<ShovelItem> AMETHYST_SHOVEL = MysticalWorld.REGISTRY.registerItem(ModMaterials.AMETHYST.getInternalName() + "_shovel", MysticalWorld.REGISTRY.shovel(ShovelItem::new, ModMaterials.AMETHYST, ModRegistries.SMG));
  public static RegistryObject<SwordItem> AMETHYST_SWORD = MysticalWorld.REGISTRY.registerItem(ModMaterials.AMETHYST.getInternalName() + "_sword", MysticalWorld.REGISTRY.sword(SwordItem::new, ModMaterials.AMETHYST, ModRegistries.SMG));
  public static RegistryObject<SpearItem> AMETHYST_SPEAR = MysticalWorld.REGISTRY.registerItem(ModMaterials.AMETHYST.getInternalName() + " _spear", MysticalWorld.REGISTRY.spear(ModifiedSpearItem::new, ModMaterials.AMETHYST, ModRegistries.SMG));

  public static RegistryObject<AxeItem> CACTUS_AXE = MysticalWorld.REGISTRY.registerItem(ModMaterials.CACTUS.getInternalName() + "_axe", MysticalWorld.REGISTRY.axe(AxeItem::new, ModMaterials.CACTUS, ModRegistries.SMG));
  public static RegistryObject<HoeItem> CACTUS_HOE = MysticalWorld.REGISTRY.registerItem(ModMaterials.CACTUS.getInternalName() + "_hoe", MysticalWorld.REGISTRY.hoe(HoeItem::new, ModMaterials.CACTUS, ModRegistries.SMG));
  public static RegistryObject<KnifeItem> CACTUS_KNIFE = MysticalWorld.REGISTRY.registerItem(ModMaterials.CACTUS.getInternalName() + "_knife", MysticalWorld.REGISTRY.knife(KnifeItem::new, ModMaterials.CACTUS, ModRegistries.SMG));
  public static RegistryObject<PickaxeItem> CACTUS_PICKAXE = MysticalWorld.REGISTRY.registerItem(ModMaterials.CACTUS.getInternalName() + "_pickaxe", MysticalWorld.REGISTRY.pickaxe(PickaxeItem::new, ModMaterials.CACTUS, ModRegistries.SMG));
  public static RegistryObject<ShovelItem> CACTUS_SHOVEL = MysticalWorld.REGISTRY.registerItem(ModMaterials.CACTUS.getInternalName() + "_shovel", MysticalWorld.REGISTRY.shovel(ShovelItem::new, ModMaterials.CACTUS, ModRegistries.SMG));
  public static RegistryObject<SwordItem> CACTUS_SWORD = MysticalWorld.REGISTRY.registerItem(ModMaterials.CACTUS.getInternalName() + "_sword", MysticalWorld.REGISTRY.sword(SwordItem::new, ModMaterials.CACTUS, ModRegistries.SMG));
  public static RegistryObject<SpearItem> CACTUS_SPEAR = MysticalWorld.REGISTRY.registerItem(ModMaterials.CACTUS.getInternalName() + "_spear", MysticalWorld.REGISTRY.spear(ModifiedSpearItem::new, ModMaterials.CACTUS, ModRegistries.SMG));

  public static RegistryObject<AxeItem> COPPER_AXE = MysticalWorld.REGISTRY.registerItem(ModMaterials.COPPER.getInternalName() + "_axe", MysticalWorld.REGISTRY.axe(AxeItem::new, ModMaterials.COPPER, ModRegistries.SMG));
  public static RegistryObject<HoeItem> COPPER_HOE = MysticalWorld.REGISTRY.registerItem(ModMaterials.COPPER.getInternalName() + "_hoe", MysticalWorld.REGISTRY.hoe(HoeItem::new, ModMaterials.COPPER, ModRegistries.SMG));
  public static RegistryObject<KnifeItem> COPPER_KNIFE = MysticalWorld.REGISTRY.registerItem(ModMaterials.COPPER.getInternalName() + "_knife", MysticalWorld.REGISTRY.knife(KnifeItem::new, ModMaterials.COPPER, ModRegistries.SMG));
  public static RegistryObject<PickaxeItem> COPPER_PICKAXE = MysticalWorld.REGISTRY.registerItem(ModMaterials.COPPER.getInternalName() + "_pickaxe", MysticalWorld.REGISTRY.pickaxe(PickaxeItem::new, ModMaterials.COPPER, ModRegistries.SMG));
  public static RegistryObject<ShovelItem> COPPER_SHOVEL = MysticalWorld.REGISTRY.registerItem(ModMaterials.COPPER.getInternalName() + "_shovel", MysticalWorld.REGISTRY.shovel(ShovelItem::new, ModMaterials.COPPER, ModRegistries.SMG));
  public static RegistryObject<SwordItem> COPPER_SWORD = MysticalWorld.REGISTRY.registerItem(ModMaterials.COPPER.getInternalName() + "_sword", MysticalWorld.REGISTRY.sword(SwordItem::new, ModMaterials.COPPER, ModRegistries.SMG));
  public static RegistryObject<SpearItem> COPPER_SPEAR = MysticalWorld.REGISTRY.registerItem(ModMaterials.COPPER.getInternalName() + "_spear", MysticalWorld.REGISTRY.spear(ModifiedSpearItem::new, ModMaterials.COPPER, ModRegistries.SMG));

  public static RegistryObject<AxeItem> LEAD_AXE = MysticalWorld.REGISTRY.registerItem(ModMaterials.LEAD.getInternalName() + "_axe", MysticalWorld.REGISTRY.axe(AxeItem::new, ModMaterials.LEAD, ModRegistries.SMG));
  public static RegistryObject<HoeItem> LEAD_HOE = MysticalWorld.REGISTRY.registerItem(ModMaterials.LEAD.getInternalName() + "_hoe", MysticalWorld.REGISTRY.hoe(HoeItem::new, ModMaterials.LEAD, ModRegistries.SMG));
  public static RegistryObject<KnifeItem> LEAD_KNIFE = MysticalWorld.REGISTRY.registerItem(ModMaterials.LEAD.getInternalName() + "_knife", MysticalWorld.REGISTRY.knife(KnifeItem::new, ModMaterials.LEAD, ModRegistries.SMG));
  public static RegistryObject<PickaxeItem> LEAD_PICKAXE = MysticalWorld.REGISTRY.registerItem(ModMaterials.LEAD.getInternalName() + "_pickaxe", MysticalWorld.REGISTRY.pickaxe(PickaxeItem::new, ModMaterials.LEAD, ModRegistries.SMG));
  public static RegistryObject<ShovelItem> LEAD_SHOVEL = MysticalWorld.REGISTRY.registerItem(ModMaterials.LEAD.getInternalName() + "_shovel", MysticalWorld.REGISTRY.shovel(ShovelItem::new, ModMaterials.LEAD, ModRegistries.SMG));
  public static RegistryObject<SwordItem> LEAD_SWORD = MysticalWorld.REGISTRY.registerItem(ModMaterials.LEAD.getInternalName() + "_sword", MysticalWorld.REGISTRY.sword(SwordItem::new, ModMaterials.LEAD, ModRegistries.SMG));
  public static RegistryObject<SpearItem> LEAD_SPEAR = MysticalWorld.REGISTRY.registerItem(ModMaterials.LEAD.getInternalName() + "_spear", MysticalWorld.REGISTRY.spear(ModifiedSpearItem::new, ModMaterials.LEAD, ModRegistries.SMG));

  public static RegistryObject<AxeItem> QUICKSILVER_AXE = MysticalWorld.REGISTRY.registerItem(ModMaterials.QUICKSILVER.getInternalName() + "_axe", MysticalWorld.REGISTRY.axe(QuicksilverAxeItem::new, ModMaterials.QUICKSILVER, ModRegistries.SMG));
  public static RegistryObject<HoeItem> QUICKSILVER_HOE = MysticalWorld.REGISTRY.registerItem(ModMaterials.QUICKSILVER.getInternalName() + "_hoe", MysticalWorld.REGISTRY.hoe(QuicksilverHoeItem::new, ModMaterials.QUICKSILVER, ModRegistries.SMG));
  public static RegistryObject<KnifeItem> QUICKSILVER_KNIFE = MysticalWorld.REGISTRY.registerItem(ModMaterials.QUICKSILVER.getInternalName() + "_knife", MysticalWorld.REGISTRY.knife(QuicksilverKnifeItem::new, ModMaterials.QUICKSILVER, ModRegistries.SMG));
  public static RegistryObject<PickaxeItem> QUICKSILVER_PICKAXE = MysticalWorld.REGISTRY.registerItem(ModMaterials.QUICKSILVER.getInternalName() + "_pickaxe", MysticalWorld.REGISTRY.pickaxe(QuicksilverPickaxeItem::new, ModMaterials.QUICKSILVER, ModRegistries.SMG));
  public static RegistryObject<ShovelItem> QUICKSILVER_SHOVEL = MysticalWorld.REGISTRY.registerItem(ModMaterials.QUICKSILVER.getInternalName() + "_shovel", MysticalWorld.REGISTRY.shovel(QuicksilverShovelItem::new, ModMaterials.QUICKSILVER, ModRegistries.SMG));
  public static RegistryObject<SwordItem> QUICKSILVER_SWORD = MysticalWorld.REGISTRY.registerItem(ModMaterials.QUICKSILVER.getInternalName() + "_sword", MysticalWorld.REGISTRY.sword(QuicksilverSwordItem::new, ModMaterials.QUICKSILVER, ModRegistries.SMG));
  public static RegistryObject<SpearItem> QUICKSILVER_SPEAR = MysticalWorld.REGISTRY.registerItem(ModMaterials.QUICKSILVER.getInternalName() + "_spear", MysticalWorld.REGISTRY.spear(QuicksilverSpearItem::new, ModMaterials.QUICKSILVER, ModRegistries.SMG));

  public static RegistryObject<AxeItem> SILVER_AXE = MysticalWorld.REGISTRY.registerItem(ModMaterials.SILVER.getInternalName() + "_axe", MysticalWorld.REGISTRY.axe(SilverAxeItem::new, ModMaterials.SILVER, ModRegistries.SMG));
  public static RegistryObject<HoeItem> SILVER_HOE = MysticalWorld.REGISTRY.registerItem(ModMaterials.SILVER.getInternalName() + "_hoe", MysticalWorld.REGISTRY.hoe(SilverHoeItem::new, ModMaterials.SILVER, ModRegistries.SMG));
  public static RegistryObject<KnifeItem> SILVER_KNIFE = MysticalWorld.REGISTRY.registerItem(ModMaterials.SILVER.getInternalName() + "_knife", MysticalWorld.REGISTRY.knife(SilverKnifeItem::new, ModMaterials.SILVER, ModRegistries.SMG));
  public static RegistryObject<PickaxeItem> SILVER_PICKAXE = MysticalWorld.REGISTRY.registerItem(ModMaterials.SILVER.getInternalName() + "_pickaxe", MysticalWorld.REGISTRY.pickaxe(SilverPickaxeItem::new, ModMaterials.SILVER, ModRegistries.SMG));
  public static RegistryObject<ShovelItem> SILVER_SHOVEL = MysticalWorld.REGISTRY.registerItem(ModMaterials.SILVER.getInternalName() + "_shovel", MysticalWorld.REGISTRY.shovel(SilverShovelItem::new, ModMaterials.SILVER, ModRegistries.SMG));
  public static RegistryObject<SwordItem> SILVER_SWORD = MysticalWorld.REGISTRY.registerItem(ModMaterials.SILVER.getInternalName() + "_sword", MysticalWorld.REGISTRY.sword(SilverSwordItem::new, ModMaterials.SILVER, ModRegistries.SMG));
  public static RegistryObject<SpearItem> SILVER_SPEAR = MysticalWorld.REGISTRY.registerItem(ModMaterials.SILVER.getInternalName() + "_spear", MysticalWorld.REGISTRY.spear(SilverSpearItem::new, ModMaterials.SILVER, ModRegistries.SMG));

  public static RegistryObject<AxeItem> TIN_AXE = MysticalWorld.REGISTRY.registerItem(ModMaterials.TIN.getInternalName() + "_axe", MysticalWorld.REGISTRY.axe(AxeItem::new, ModMaterials.TIN, ModRegistries.SMG));
  public static RegistryObject<HoeItem> TIN_HOE = MysticalWorld.REGISTRY.registerItem(ModMaterials.TIN.getInternalName() + "_hoe", MysticalWorld.REGISTRY.hoe(HoeItem::new, ModMaterials.TIN, ModRegistries.SMG));
  public static RegistryObject<KnifeItem> TIN_KNIFE = MysticalWorld.REGISTRY.registerItem(ModMaterials.TIN.getInternalName() + "_knife", MysticalWorld.REGISTRY.knife(KnifeItem::new, ModMaterials.TIN, ModRegistries.SMG));
  public static RegistryObject<PickaxeItem> TIN_PICKAXE = MysticalWorld.REGISTRY.registerItem(ModMaterials.TIN.getInternalName() + "_pickaxe", MysticalWorld.REGISTRY.pickaxe(PickaxeItem::new, ModMaterials.TIN, ModRegistries.SMG));
  public static RegistryObject<ShovelItem> TIN_SHOVEL = MysticalWorld.REGISTRY.registerItem(ModMaterials.TIN.getInternalName() + "_shovel", MysticalWorld.REGISTRY.shovel(ShovelItem::new, ModMaterials.TIN, ModRegistries.SMG));
  public static RegistryObject<SwordItem> TIN_SWORD = MysticalWorld.REGISTRY.registerItem(ModMaterials.TIN.getInternalName() + "_sword", MysticalWorld.REGISTRY.sword(SwordItem::new, ModMaterials.TIN, ModRegistries.SMG));
  public static RegistryObject<SpearItem> TIN_SPEAR = MysticalWorld.REGISTRY.registerItem(ModMaterials.TIN.getInternalName() + "_spear", MysticalWorld.REGISTRY.spear(ModifiedSpearItem::new, ModMaterials.TIN, ModRegistries.SMG));

  public static RegistryObject<KnifeItem> STONE_KNIFE = MysticalWorld.REGISTRY.registerItem(ModMaterials.STONE.getInternalName() + "_knife", MysticalWorld.REGISTRY.knife(KnifeItem::new, ModMaterials.STONE, ModRegistries.SMG));
  public static RegistryObject<KnifeItem> WOODEN_KNIFE = MysticalWorld.REGISTRY.registerItem(ModMaterials.WOODEN.getInternalName() + "_knife", MysticalWorld.REGISTRY.knife(KnifeItem::new, ModMaterials.WOODEN, ModRegistries.SMG));
  public static RegistryObject<KnifeItem> DIAMOND_KNIFE = MysticalWorld.REGISTRY.registerItem(ModMaterials.DIAMOND.getInternalName() + "_knife", MysticalWorld.REGISTRY.knife(KnifeItem::new, ModMaterials.DIAMOND, ModRegistries.SMG));
  public static RegistryObject<KnifeItem> GOLD_KNIFE = MysticalWorld.REGISTRY.registerItem(ModMaterials.GOLD.getInternalName() + "_knife", MysticalWorld.REGISTRY.knife(KnifeItem::new, ModMaterials.GOLD, ModRegistries.SMG));
  public static RegistryObject<KnifeItem> IRON_KNIFE = MysticalWorld.REGISTRY.registerItem(ModMaterials.IRON.getInternalName() + "_knife", MysticalWorld.REGISTRY.knife(KnifeItem::new, ModMaterials.IRON, ModRegistries.SMG));

  public static RegistryObject<SpearItem> STONE_SPEAR = MysticalWorld.REGISTRY.registerItem(ModMaterials.STONE.getInternalName() + "_spear", MysticalWorld.REGISTRY.spear(ModifiedSpearItem::new, ModMaterials.STONE, ModRegistries.SMG));
  public static RegistryObject<SpearItem> WOODEN_SPEAR = MysticalWorld.REGISTRY.registerItem(ModMaterials.WOODEN.getInternalName() + "_spear", MysticalWorld.REGISTRY.spear(ModifiedSpearItem::new, ModMaterials.WOODEN, ModRegistries.SMG));
  public static RegistryObject<SpearItem> DIAMOND_SPEAR = MysticalWorld.REGISTRY.registerItem(ModMaterials.DIAMOND.getInternalName() + "_spear", MysticalWorld.REGISTRY.spear(ModifiedSpearItem::new, ModMaterials.DIAMOND, ModRegistries.SMG));
  public static RegistryObject<SpearItem> GOLD_SPEAR = MysticalWorld.REGISTRY.registerItem(ModMaterials.GOLD.getInternalName() + "_spear", MysticalWorld.REGISTRY.spear(ModifiedSpearItem::new, ModMaterials.GOLD, ModRegistries.SMG));
  public static RegistryObject<SpearItem> IRON_SPEAR = MysticalWorld.REGISTRY.registerItem(ModMaterials.IRON.getInternalName() + "_spear", MysticalWorld.REGISTRY.spear(ModifiedSpearItem::new, ModMaterials.IRON, ModRegistries.SMG));

  // Armors
  public static RegistryObject<ArmorItem> AMETHYST_HELMET = MysticalWorld.REGISTRY.registerItem(ModMaterials.AMETHYST.getInternalName() + "_helmet", MysticalWorld.REGISTRY.armor(AmethystArmorItem::new, ModMaterials.AMETHYST, EquipmentSlotType.HEAD, ModRegistries.SMG));
  public static RegistryObject<ArmorItem> AMETHYST_CHESTPLATE = MysticalWorld.REGISTRY.registerItem(ModMaterials.AMETHYST.getInternalName() + "_chestplate", MysticalWorld.REGISTRY.armor(AmethystArmorItem::new, ModMaterials.AMETHYST, EquipmentSlotType.CHEST, ModRegistries.SMG));
  public static RegistryObject<ArmorItem> AMETHYST_LEGGINGS = MysticalWorld.REGISTRY.registerItem(ModMaterials.AMETHYST.getInternalName() + "_leggings", MysticalWorld.REGISTRY.armor(AmethystArmorItem::new, ModMaterials.AMETHYST, EquipmentSlotType.LEGS, ModRegistries.SMG));
  public static RegistryObject<ArmorItem> AMETHYST_BOOTS = MysticalWorld.REGISTRY.registerItem(ModMaterials.AMETHYST.getInternalName() + "_boots", MysticalWorld.REGISTRY.armor(AmethystArmorItem::new, ModMaterials.AMETHYST, EquipmentSlotType.FEET, ModRegistries.SMG));

  public static RegistryObject<ArmorItem> COPPER_HELMET = MysticalWorld.REGISTRY.registerItem(ModMaterials.COPPER.getInternalName() + "_helmet", MysticalWorld.REGISTRY.armor(CopperArmorItem::new, ModMaterials.COPPER, EquipmentSlotType.HEAD, ModRegistries.SMG));
  public static RegistryObject<ArmorItem> COPPER_CHESTPLATE = MysticalWorld.REGISTRY.registerItem(ModMaterials.COPPER.getInternalName() + "_chestplate", MysticalWorld.REGISTRY.armor(CopperArmorItem::new, ModMaterials.COPPER, EquipmentSlotType.CHEST, ModRegistries.SMG));
  public static RegistryObject<ArmorItem> COPPER_LEGGINGS = MysticalWorld.REGISTRY.registerItem(ModMaterials.COPPER.getInternalName() + "_leggings", MysticalWorld.REGISTRY.armor(CopperArmorItem::new, ModMaterials.COPPER, EquipmentSlotType.LEGS, ModRegistries.SMG));
  public static RegistryObject<ArmorItem> COPPER_BOOTS = MysticalWorld.REGISTRY.registerItem(ModMaterials.COPPER.getInternalName() + "_boots", MysticalWorld.REGISTRY.armor(CopperArmorItem::new, ModMaterials.COPPER, EquipmentSlotType.FEET, ModRegistries.SMG));

  public static RegistryObject<ArmorItem> LEAD_HELMET = MysticalWorld.REGISTRY.registerItem(ModMaterials.LEAD.getInternalName() + "_helmet", MysticalWorld.REGISTRY.armor(LeadArmorItem::new, ModMaterials.LEAD, EquipmentSlotType.HEAD, ModRegistries.SMG));
  public static RegistryObject<ArmorItem> LEAD_CHESTPLATE = MysticalWorld.REGISTRY.registerItem(ModMaterials.LEAD.getInternalName() + "_chestplate", MysticalWorld.REGISTRY.armor(LeadArmorItem::new, ModMaterials.LEAD, EquipmentSlotType.CHEST, ModRegistries.SMG));
  public static RegistryObject<ArmorItem> LEAD_LEGGINGS = MysticalWorld.REGISTRY.registerItem(ModMaterials.LEAD.getInternalName() + "_leggings", MysticalWorld.REGISTRY.armor(LeadArmorItem::new, ModMaterials.LEAD, EquipmentSlotType.LEGS, ModRegistries.SMG));
  public static RegistryObject<ArmorItem> LEAD_BOOTS = MysticalWorld.REGISTRY.registerItem(ModMaterials.LEAD.getInternalName() + "_boots", MysticalWorld.REGISTRY.armor(LeadArmorItem::new, ModMaterials.LEAD, EquipmentSlotType.FEET, ModRegistries.SMG));

  public static RegistryObject<ArmorItem> QUICKSILVER_HELMET = MysticalWorld.REGISTRY.registerItem(ModMaterials.QUICKSILVER.getInternalName() + "_helmet", MysticalWorld.REGISTRY.armor(QuicksilverArmorItem::new, ModMaterials.QUICKSILVER, EquipmentSlotType.HEAD, ModRegistries.SMG));
  public static RegistryObject<ArmorItem> QUICKSILVER_CHESTPLATE = MysticalWorld.REGISTRY.registerItem(ModMaterials.QUICKSILVER.getInternalName() + "_chestplate", MysticalWorld.REGISTRY.armor(QuicksilverArmorItem::new, ModMaterials.QUICKSILVER, EquipmentSlotType.CHEST, ModRegistries.SMG));
  public static RegistryObject<ArmorItem> QUICKSILVER_LEGGINGS = MysticalWorld.REGISTRY.registerItem(ModMaterials.QUICKSILVER.getInternalName() + "_leggings", MysticalWorld.REGISTRY.armor(QuicksilverArmorItem::new, ModMaterials.QUICKSILVER, EquipmentSlotType.LEGS, ModRegistries.SMG));
  public static RegistryObject<ArmorItem> QUICKSILVER_BOOTS = MysticalWorld.REGISTRY.registerItem(ModMaterials.QUICKSILVER.getInternalName() + "_boots", MysticalWorld.REGISTRY.armor(QuicksilverArmorItem::new, ModMaterials.QUICKSILVER, EquipmentSlotType.FEET, ModRegistries.SMG));

  public static RegistryObject<ArmorItem> SILVER_HELMET = MysticalWorld.REGISTRY.registerItem(ModMaterials.SILVER.getInternalName() + "_helmet", MysticalWorld.REGISTRY.armor(SilverArmorItem::new, ModMaterials.SILVER, EquipmentSlotType.HEAD, ModRegistries.SMG));
  public static RegistryObject<ArmorItem> SILVER_CHESTPLATE = MysticalWorld.REGISTRY.registerItem(ModMaterials.SILVER.getInternalName() + "_chestplate", MysticalWorld.REGISTRY.armor(SilverArmorItem::new, ModMaterials.SILVER, EquipmentSlotType.CHEST, ModRegistries.SMG));
  public static RegistryObject<ArmorItem> SILVER_LEGGINGS = MysticalWorld.REGISTRY.registerItem(ModMaterials.SILVER.getInternalName() + "_leggings", MysticalWorld.REGISTRY.armor(SilverArmorItem::new, ModMaterials.SILVER, EquipmentSlotType.LEGS, ModRegistries.SMG));
  public static RegistryObject<ArmorItem> SILVER_BOOTS = MysticalWorld.REGISTRY.registerItem(ModMaterials.SILVER.getInternalName() + "_boots", MysticalWorld.REGISTRY.armor(SilverArmorItem::new, ModMaterials.SILVER, EquipmentSlotType.FEET, ModRegistries.SMG));

  public static RegistryObject<ArmorItem> TIN_HELMET = MysticalWorld.REGISTRY.registerItem(ModMaterials.TIN.getInternalName() + "_helmet", MysticalWorld.REGISTRY.armor(TinArmorItem::new, ModMaterials.TIN, EquipmentSlotType.HEAD, ModRegistries.SMG));
  public static RegistryObject<ArmorItem> TIN_CHESTPLATE = MysticalWorld.REGISTRY.registerItem(ModMaterials.TIN.getInternalName() + "_chestplate", MysticalWorld.REGISTRY.armor(TinArmorItem::new, ModMaterials.TIN, EquipmentSlotType.CHEST, ModRegistries.SMG));
  public static RegistryObject<ArmorItem> TIN_LEGGINGS = MysticalWorld.REGISTRY.registerItem(ModMaterials.TIN.getInternalName() + "_leggings", MysticalWorld.REGISTRY.armor(TinArmorItem::new, ModMaterials.TIN, EquipmentSlotType.LEGS, ModRegistries.SMG));
  public static RegistryObject<ArmorItem> TIN_BOOTS = MysticalWorld.REGISTRY.registerItem(ModMaterials.TIN.getInternalName() + "_boots", MysticalWorld.REGISTRY.armor(TinArmorItem::new, ModMaterials.TIN, EquipmentSlotType.FEET, ModRegistries.SMG));

  public static void load() {}

  public static void registerItems (RegistryEvent.Register<Item> event) {
    ModCompost.init();
  }
}
