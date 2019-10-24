package epicsquid.mysticalworld.init;

import epicsquid.mysticallib.item.KnifeItem;
import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.items.*;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraftforge.fml.RegistryObject;

@SuppressWarnings("unused")
public class ModItems {
  public static RegistryObject<DyeItem> CARAPACE = ModRegistries.registerItem("carapace", ModRegistries.dyeItem(DyeColor.BLUE, ModRegistries.SIG));
  public static RegistryObject<Item> PELT = ModRegistries.registerItem("pelt", ModRegistries.item(Item::new, ModRegistries.SIG));
  public static RegistryObject<Item> ANTLERS = ModRegistries.registerItem("antlers", ModRegistries.item(Item::new, ModRegistries.SIG));
  public static RegistryObject<DyeItem> INK_BOTTLE = ModRegistries.registerItem("ink_bottle", ModRegistries.dyeItem(DyeColor.BLACK, ModRegistries.SIG));

  public static RegistryObject<Item> VENISON = ModRegistries.registerItem("venison", ModRegistries.item(Item::new, ModRegistries.foodProp(ModFoods.VENISON)));
  public static RegistryObject<Item> COOKED_VENISON = ModRegistries.registerItem("cooked_venison", ModRegistries.item(Item::new, ModRegistries.foodProp(ModFoods.COOKED_VENISON)));

  public static RegistryObject<BlockNamedItem> AUBERGINE_SEEDS = ModRegistries.registerItem("aubergine_seeds", ModRegistries.blockNamedItem(() -> ModBlocks.AUBERGINE_CROP, ModRegistries.SIG));

  public static RegistryObject<Item> AUBERGINE = ModRegistries.registerItem("aubergine", ModRegistries.item(Item::new, ModRegistries.foodProp(ModFoods.AUBERGINE)));
  public static RegistryObject<Item> COOKED_AUBERGINE = ModRegistries.registerItem("cooked_aubergine", ModRegistries.item(Item::new, ModRegistries.foodProp(ModFoods.COOKED_AUBERGINE)));
  public static RegistryObject<Item> STUFFED_AUBERGINE = ModRegistries.registerItem("stuffed_aubergine", ModRegistries.item(Item::new, ModRegistries.foodProp(ModFoods.STUFFED_AUBERGINE)));

  public static RegistryObject<Item> RAW_SQUID = ModRegistries.registerItem("raw_squid", ModRegistries.item(Item::new, ModRegistries.foodProp(ModFoods.RAW_SQUID)));
  public static RegistryObject<Item> COOKED_SQUID = ModRegistries.registerItem("cooked_squid", ModRegistries.item(Item::new, ModRegistries.foodProp(ModFoods.COOKED_SQUID)));
  public static RegistryObject<EffectItem> EPIC_SQUID = ModRegistries.registerItem("epic_squid", ModRegistries.item(EffectItem::new, () -> new Item.Properties().group(MysticalWorld.ITEM_GROUP).food(ModFoods.EPIC_SQUID).rarity(Rarity.EPIC)));

  // Ingots/gems
  public static RegistryObject<Item> AMETHYST_GEM = ModRegistries.registerItem(ModMaterials.AMETHYST.getName(), ModRegistries.item(Item::new, ModRegistries.SMG));
  public static RegistryObject<Item> COPPER_INGOT = ModRegistries.registerItem(ModMaterials.COPPER.ingotName(), ModRegistries.item(Item::new, ModRegistries.SMG));
  public static RegistryObject<Item> LEAD_INGOT = ModRegistries.registerItem(ModMaterials.LEAD.ingotName(), ModRegistries.item(Item::new, ModRegistries.SMG));
  public static RegistryObject<Item> QUICKSILVER_INGOT = ModRegistries.registerItem(ModMaterials.QUICKSILVER.ingotName(), ModRegistries.item(Item::new, ModRegistries.SMG));
  public static RegistryObject<Item> SILVER_INGOT = ModRegistries.registerItem(ModMaterials.SILVER.ingotName(), ModRegistries.item(Item::new, ModRegistries.SMG));
  public static RegistryObject<Item> TIN_INGOT = ModRegistries.registerItem(ModMaterials.TIN.ingotName(), ModRegistries.item(Item::new, ModRegistries.SMG));

  // Nuggets
  public static RegistryObject<Item> COPPER_NUGGET = ModRegistries.registerItem(ModMaterials.COPPER.nuggetName(), ModRegistries.item(Item::new, ModRegistries.SMG));
  public static RegistryObject<Item> LEAD_NUGGET = ModRegistries.registerItem(ModMaterials.LEAD.nuggetName(), ModRegistries.item(Item::new, ModRegistries.SMG));
  public static RegistryObject<Item> QUICKSILVER_NUGGET = ModRegistries.registerItem(ModMaterials.QUICKSILVER.nuggetName(), ModRegistries.item(Item::new, ModRegistries.SMG));
  public static RegistryObject<Item> SILVER_NUGGET = ModRegistries.registerItem(ModMaterials.SILVER.nuggetName(), ModRegistries.item(Item::new, ModRegistries.SMG));
  public static RegistryObject<Item> TIN_NUGGET = ModRegistries.registerItem(ModMaterials.TIN.nuggetName(), ModRegistries.item(Item::new, ModRegistries.SMG));

  // Dusts
  public static RegistryObject<Item> COPPER_DUST = ModRegistries.registerItem(ModMaterials.COPPER.dustName(), ModRegistries.item(Item::new, ModRegistries.SMG));
  public static RegistryObject<Item> LEAD_DUST = ModRegistries.registerItem(ModMaterials.LEAD.dustName(), ModRegistries.item(Item::new, ModRegistries.SMG));
  public static RegistryObject<Item> QUICKSILVER_DUST = ModRegistries.registerItem(ModMaterials.QUICKSILVER.dustName(), ModRegistries.item(Item::new, ModRegistries.SMG));
  public static RegistryObject<Item> SILVER_DUST = ModRegistries.registerItem(ModMaterials.SILVER.dustName(), ModRegistries.item(Item::new, ModRegistries.SMG));
  public static RegistryObject<Item> TIN_DUST = ModRegistries.registerItem(ModMaterials.TIN.dustName(), ModRegistries.item(Item::new, ModRegistries.SMG));
  public static RegistryObject<Item> GOLD_DUST = ModRegistries.registerItem(ModMaterials.GOLD.dustName(), ModRegistries.item(Item::new, ModRegistries.SMG));
  public static RegistryObject<Item> IRON_DUST = ModRegistries.registerItem(ModMaterials.IRON.dustName(), ModRegistries.item(Item::new, ModRegistries.SMG));

  public static RegistryObject<AxeItem> AMETHYST_AXE = ModRegistries.registerItem(ModMaterials.AMETHYST.getName() + "_axe", ModRegistries.axe(AxeItem::new, ModMaterials.AMETHYST, ModRegistries.SMG));
  public static RegistryObject<HoeItem> AMETHYST_HOE = ModRegistries.registerItem(ModMaterials.AMETHYST.getName() + "_hoe", ModRegistries.hoe(HoeItem::new, ModMaterials.AMETHYST, ModRegistries.SMG));
  public static RegistryObject<KnifeItem> AMETHYST_KNIFE = ModRegistries.registerItem(ModMaterials.AMETHYST.getName() + "_knife", ModRegistries.knife(KnifeItem::new, ModMaterials.AMETHYST, ModRegistries.SMG));
  public static RegistryObject<PickaxeItem> AMETHYST_PICKAXE = ModRegistries.registerItem(ModMaterials.AMETHYST.getName() + "_pickaxe", ModRegistries.pickaxe(PickaxeItem::new, ModMaterials.AMETHYST, ModRegistries.SMG));
  public static RegistryObject<ShovelItem> AMETHYST_SHOVEL = ModRegistries.registerItem(ModMaterials.AMETHYST.getName() + "_shovel", ModRegistries.shovel(ShovelItem::new, ModMaterials.AMETHYST, ModRegistries.SMG));
  public static RegistryObject<SwordItem> AMETHYST_SWORD = ModRegistries.registerItem(ModMaterials.AMETHYST.getName() + "_sword", ModRegistries.sword(SwordItem::new, ModMaterials.AMETHYST, ModRegistries.SMG));

  public static RegistryObject<AxeItem> CACTUS_AXE = ModRegistries.registerItem(ModMaterials.CACTUS.getName() + "_axe", ModRegistries.axe(AxeItem::new, ModMaterials.CACTUS, ModRegistries.SMG));
  public static RegistryObject<HoeItem> CACTUS_HOE = ModRegistries.registerItem(ModMaterials.CACTUS.getName() + "_hoe", ModRegistries.hoe(HoeItem::new, ModMaterials.CACTUS, ModRegistries.SMG));
  public static RegistryObject<KnifeItem> CACTUS_KNIFE = ModRegistries.registerItem(ModMaterials.CACTUS.getName() + "_knife", ModRegistries.knife(KnifeItem::new, ModMaterials.CACTUS, ModRegistries.SMG));
  public static RegistryObject<PickaxeItem> CACTUS_PICKAXE = ModRegistries.registerItem(ModMaterials.CACTUS.getName() + "_pickaxe", ModRegistries.pickaxe(PickaxeItem::new, ModMaterials.CACTUS, ModRegistries.SMG));
  public static RegistryObject<ShovelItem> CACTUS_SHOVEL = ModRegistries.registerItem(ModMaterials.CACTUS.getName() + "_shovel", ModRegistries.shovel(ShovelItem::new, ModMaterials.CACTUS, ModRegistries.SMG));
  public static RegistryObject<SwordItem> CACTUS_SWORD = ModRegistries.registerItem(ModMaterials.CACTUS.getName() + "_sword", ModRegistries.sword(SwordItem::new, ModMaterials.CACTUS, ModRegistries.SMG));

  public static RegistryObject<AxeItem> COPPER_AXE = ModRegistries.registerItem(ModMaterials.COPPER.getName() + "_axe", ModRegistries.axe(AxeItem::new, ModMaterials.COPPER, ModRegistries.SMG));
  public static RegistryObject<HoeItem> COPPER_HOE = ModRegistries.registerItem(ModMaterials.COPPER.getName() + "_hoe", ModRegistries.hoe(HoeItem::new, ModMaterials.COPPER, ModRegistries.SMG));
  public static RegistryObject<KnifeItem> COPPER_KNIFE = ModRegistries.registerItem(ModMaterials.COPPER.getName() + "_knife", ModRegistries.knife(KnifeItem::new, ModMaterials.COPPER, ModRegistries.SMG));
  public static RegistryObject<PickaxeItem> COPPER_PICKAXE = ModRegistries.registerItem(ModMaterials.COPPER.getName() + "_pickaxe", ModRegistries.pickaxe(PickaxeItem::new, ModMaterials.COPPER, ModRegistries.SMG));
  public static RegistryObject<ShovelItem> COPPER_SHOVEL = ModRegistries.registerItem(ModMaterials.COPPER.getName() + "_shovel", ModRegistries.shovel(ShovelItem::new, ModMaterials.COPPER, ModRegistries.SMG));
  public static RegistryObject<SwordItem> COPPER_SWORD = ModRegistries.registerItem(ModMaterials.COPPER.getName() + "_sword", ModRegistries.sword(SwordItem::new, ModMaterials.COPPER, ModRegistries.SMG));

  public static RegistryObject<AxeItem> LEAD_AXE = ModRegistries.registerItem(ModMaterials.LEAD.getName() + "_axe", ModRegistries.axe(AxeItem::new, ModMaterials.LEAD, ModRegistries.SMG));
  public static RegistryObject<HoeItem> LEAD_HOE = ModRegistries.registerItem(ModMaterials.LEAD.getName() + "_hoe", ModRegistries.hoe(HoeItem::new, ModMaterials.LEAD, ModRegistries.SMG));
  public static RegistryObject<KnifeItem> LEAD_KNIFE = ModRegistries.registerItem(ModMaterials.LEAD.getName() + "_knife", ModRegistries.knife(KnifeItem::new, ModMaterials.LEAD, ModRegistries.SMG));
  public static RegistryObject<PickaxeItem> LEAD_PICKAXE = ModRegistries.registerItem(ModMaterials.LEAD.getName() + "_pickaxe", ModRegistries.pickaxe(PickaxeItem::new, ModMaterials.LEAD, ModRegistries.SMG));
  public static RegistryObject<ShovelItem> LEAD_SHOVEL = ModRegistries.registerItem(ModMaterials.LEAD.getName() + "_shovel", ModRegistries.shovel(ShovelItem::new, ModMaterials.LEAD, ModRegistries.SMG));
  public static RegistryObject<SwordItem> LEAD_SWORD = ModRegistries.registerItem(ModMaterials.LEAD.getName() + "_sword", ModRegistries.sword(SwordItem::new, ModMaterials.LEAD, ModRegistries.SMG));

  public static RegistryObject<AxeItem> QUICKSILVER_AXE = ModRegistries.registerItem(ModMaterials.QUICKSILVER.getName() + "_axe", ModRegistries.axe(QuicksilverAxeItem::new, ModMaterials.QUICKSILVER, ModRegistries.SMG));
  public static RegistryObject<HoeItem> QUICKSILVER_HOE = ModRegistries.registerItem(ModMaterials.QUICKSILVER.getName() + "_hoe", ModRegistries.hoe(QuicksilverHoeItem::new, ModMaterials.QUICKSILVER, ModRegistries.SMG));
  public static RegistryObject<KnifeItem> QUICKSILVER_KNIFE = ModRegistries.registerItem(ModMaterials.QUICKSILVER.getName() + "_knife", ModRegistries.knife(QuicksilverKnifeItem::new, ModMaterials.QUICKSILVER, ModRegistries.SMG));
  public static RegistryObject<PickaxeItem> QUICKSILVER_PICKAXE = ModRegistries.registerItem(ModMaterials.QUICKSILVER.getName() + "_pickaxe", ModRegistries.pickaxe(QuicksilverPickaxeItem::new, ModMaterials.QUICKSILVER, ModRegistries.SMG));
  public static RegistryObject<ShovelItem> QUICKSILVER_SHOVEL = ModRegistries.registerItem(ModMaterials.QUICKSILVER.getName() + "_shovel", ModRegistries.shovel(QuicksilverShovelItem::new, ModMaterials.QUICKSILVER, ModRegistries.SMG));
  public static RegistryObject<SwordItem> QUICKSILVER_SWORD = ModRegistries.registerItem(ModMaterials.QUICKSILVER.getName() + "_sword", ModRegistries.sword(QuicksilverSwordItem::new, ModMaterials.QUICKSILVER, ModRegistries.SMG));

  public static RegistryObject<AxeItem> SILVER_AXE = ModRegistries.registerItem(ModMaterials.SILVER.getName() + "_axe", ModRegistries.axe(AxeItem::new, ModMaterials.SILVER, ModRegistries.SMG));
  public static RegistryObject<HoeItem> SILVER_HOE = ModRegistries.registerItem(ModMaterials.SILVER.getName() + "_hoe", ModRegistries.hoe(HoeItem::new, ModMaterials.SILVER, ModRegistries.SMG));
  public static RegistryObject<KnifeItem> SILVER_KNIFE = ModRegistries.registerItem(ModMaterials.SILVER.getName() + "_knife", ModRegistries.knife(KnifeItem::new, ModMaterials.SILVER, ModRegistries.SMG));
  public static RegistryObject<PickaxeItem> SILVER_PICKAXE = ModRegistries.registerItem(ModMaterials.SILVER.getName() + "_pickaxe", ModRegistries.pickaxe(PickaxeItem::new, ModMaterials.SILVER, ModRegistries.SMG));
  public static RegistryObject<ShovelItem> SILVER_SHOVEL = ModRegistries.registerItem(ModMaterials.SILVER.getName() + "_shovel", ModRegistries.shovel(ShovelItem::new, ModMaterials.SILVER, ModRegistries.SMG));
  public static RegistryObject<SwordItem> SILVER_SWORD = ModRegistries.registerItem(ModMaterials.SILVER.getName() + "_sword", ModRegistries.sword(SwordItem::new, ModMaterials.SILVER, ModRegistries.SMG));

  public static RegistryObject<AxeItem> TIN_AXE = ModRegistries.registerItem(ModMaterials.TIN.getName() + "_axe", ModRegistries.axe(AxeItem::new, ModMaterials.TIN, ModRegistries.SMG));
  public static RegistryObject<HoeItem> TIN_HOE = ModRegistries.registerItem(ModMaterials.TIN.getName() + "_hoe", ModRegistries.hoe(HoeItem::new, ModMaterials.TIN, ModRegistries.SMG));
  public static RegistryObject<KnifeItem> TIN_KNIFE = ModRegistries.registerItem(ModMaterials.TIN.getName() + "_knife", ModRegistries.knife(KnifeItem::new, ModMaterials.TIN, ModRegistries.SMG));
  public static RegistryObject<PickaxeItem> TIN_PICKAXE = ModRegistries.registerItem(ModMaterials.TIN.getName() + "_pickaxe", ModRegistries.pickaxe(PickaxeItem::new, ModMaterials.TIN, ModRegistries.SMG));
  public static RegistryObject<ShovelItem> TIN_SHOVEL = ModRegistries.registerItem(ModMaterials.TIN.getName() + "_shovel", ModRegistries.shovel(ShovelItem::new, ModMaterials.TIN, ModRegistries.SMG));
  public static RegistryObject<SwordItem> TIN_SWORD = ModRegistries.registerItem(ModMaterials.TIN.getName() + "_sword", ModRegistries.sword(SwordItem::new, ModMaterials.TIN, ModRegistries.SMG));

  public static RegistryObject<KnifeItem> STONE_KNIFE = ModRegistries.registerItem(ModMaterials.STONE.getName() + "_knife", ModRegistries.knife(KnifeItem::new, ModMaterials.STONE, ModRegistries.SMG));
  public static RegistryObject<KnifeItem> WOODEN_KNIFE = ModRegistries.registerItem(ModMaterials.WOODEN.getName() + "_knife", ModRegistries.knife(KnifeItem::new, ModMaterials.WOODEN, ModRegistries.SMG));
  public static RegistryObject<KnifeItem> DIAMOND_KNIFE = ModRegistries.registerItem(ModMaterials.DIAMOND.getName() + "_knife", ModRegistries.knife(KnifeItem::new, ModMaterials.DIAMOND, ModRegistries.SMG));
  public static RegistryObject<KnifeItem> GOLD_KNIFE = ModRegistries.registerItem(ModMaterials.GOLD.getName() + "_knife", ModRegistries.knife(KnifeItem::new, ModMaterials.GOLD, ModRegistries.SMG));
  public static RegistryObject<KnifeItem> IRON_KNIFE = ModRegistries.registerItem(ModMaterials.IRON.getName() + "_knife", ModRegistries.knife(KnifeItem::new, ModMaterials.IRON, ModRegistries.SMG));


  // Armors
  public static RegistryObject<ArmorItem> AMETHYST_HELMET = ModRegistries.registerItem(ModMaterials.AMETHYST.getName() + "_helmet", ModRegistries.armor(ArmorItem::new, ModMaterials.AMETHYST, EquipmentSlotType.HEAD, ModRegistries.SMG));
  public static RegistryObject<ArmorItem> AMETHYST_CHESTPLATE = ModRegistries.registerItem(ModMaterials.AMETHYST.getName() + "_chestplate", ModRegistries.armor(ArmorItem::new, ModMaterials.AMETHYST, EquipmentSlotType.CHEST, ModRegistries.SMG));
  public static RegistryObject<ArmorItem> AMETHYST_LEGGINGS = ModRegistries.registerItem(ModMaterials.AMETHYST.getName() + "_leggings", ModRegistries.armor(ArmorItem::new, ModMaterials.AMETHYST, EquipmentSlotType.LEGS, ModRegistries.SMG));
  public static RegistryObject<ArmorItem> AMETHYST_BOOTS = ModRegistries.registerItem(ModMaterials.AMETHYST.getName() + "_boots", ModRegistries.armor(ArmorItem::new, ModMaterials.AMETHYST, EquipmentSlotType.FEET, ModRegistries.SMG));

  public static RegistryObject<ArmorItem> COPPER_HELMET = ModRegistries.registerItem(ModMaterials.COPPER.getName() + "_helmet", ModRegistries.armor(ArmorItem::new, ModMaterials.COPPER, EquipmentSlotType.HEAD, ModRegistries.SMG));
  public static RegistryObject<ArmorItem> COPPER_CHESTPLATE = ModRegistries.registerItem(ModMaterials.COPPER.getName() + "_chestplate", ModRegistries.armor(ArmorItem::new, ModMaterials.COPPER, EquipmentSlotType.CHEST, ModRegistries.SMG));
  public static RegistryObject<ArmorItem> COPPER_LEGGINGS = ModRegistries.registerItem(ModMaterials.COPPER.getName() + "_leggings", ModRegistries.armor(ArmorItem::new, ModMaterials.COPPER, EquipmentSlotType.LEGS, ModRegistries.SMG));
  public static RegistryObject<ArmorItem> COPPER_BOOTS = ModRegistries.registerItem(ModMaterials.COPPER.getName() + "_boots", ModRegistries.armor(ArmorItem::new, ModMaterials.COPPER, EquipmentSlotType.FEET, ModRegistries.SMG));

  public static RegistryObject<ArmorItem> LEAD_HELMET = ModRegistries.registerItem(ModMaterials.LEAD.getName() + "_helmet", ModRegistries.armor(ArmorItem::new, ModMaterials.LEAD, EquipmentSlotType.HEAD, ModRegistries.SMG));
  public static RegistryObject<ArmorItem> LEAD_CHESTPLATE = ModRegistries.registerItem(ModMaterials.LEAD.getName() + "_chestplate", ModRegistries.armor(ArmorItem::new, ModMaterials.LEAD, EquipmentSlotType.CHEST, ModRegistries.SMG));
  public static RegistryObject<ArmorItem> LEAD_LEGGINGS = ModRegistries.registerItem(ModMaterials.LEAD.getName() + "_leggings", ModRegistries.armor(ArmorItem::new, ModMaterials.LEAD, EquipmentSlotType.LEGS, ModRegistries.SMG));
  public static RegistryObject<ArmorItem> LEAD_BOOTS = ModRegistries.registerItem(ModMaterials.LEAD.getName() + "_boots", ModRegistries.armor(ArmorItem::new, ModMaterials.LEAD, EquipmentSlotType.FEET, ModRegistries.SMG));

  public static RegistryObject<ArmorItem> QUICKSILVER_HELMET = ModRegistries.registerItem(ModMaterials.QUICKSILVER.getName() + "_helmet", ModRegistries.armor(QuicksilverArmorItem::new, ModMaterials.QUICKSILVER, EquipmentSlotType.HEAD, ModRegistries.SMG));
  public static RegistryObject<ArmorItem> QUICKSILVER_CHESTPLATE = ModRegistries.registerItem(ModMaterials.QUICKSILVER.getName() + "_chestplate", ModRegistries.armor(QuicksilverArmorItem::new, ModMaterials.QUICKSILVER, EquipmentSlotType.CHEST, ModRegistries.SMG));
  public static RegistryObject<ArmorItem> QUICKSILVER_LEGGINGS = ModRegistries.registerItem(ModMaterials.QUICKSILVER.getName() + "_leggings", ModRegistries.armor(QuicksilverArmorItem::new, ModMaterials.QUICKSILVER, EquipmentSlotType.LEGS, ModRegistries.SMG));
  public static RegistryObject<ArmorItem> QUICKSILVER_BOOTS = ModRegistries.registerItem(ModMaterials.QUICKSILVER.getName() + "_boots", ModRegistries.armor(QuicksilverArmorItem::new, ModMaterials.QUICKSILVER, EquipmentSlotType.FEET, ModRegistries.SMG));

  public static RegistryObject<ArmorItem> SILVER_HELMET = ModRegistries.registerItem(ModMaterials.SILVER.getName() + "_helmet", ModRegistries.armor(ArmorItem::new, ModMaterials.SILVER, EquipmentSlotType.HEAD, ModRegistries.SMG));
  public static RegistryObject<ArmorItem> SILVER_CHESTPLATE = ModRegistries.registerItem(ModMaterials.SILVER.getName() + "_chestplate", ModRegistries.armor(ArmorItem::new, ModMaterials.SILVER, EquipmentSlotType.CHEST, ModRegistries.SMG));
  public static RegistryObject<ArmorItem> SILVER_LEGGINGS = ModRegistries.registerItem(ModMaterials.SILVER.getName() + "_leggings", ModRegistries.armor(ArmorItem::new, ModMaterials.SILVER, EquipmentSlotType.LEGS, ModRegistries.SMG));
  public static RegistryObject<ArmorItem> SILVER_BOOTS = ModRegistries.registerItem(ModMaterials.SILVER.getName() + "_boots", ModRegistries.armor(ArmorItem::new, ModMaterials.SILVER, EquipmentSlotType.FEET, ModRegistries.SMG));

  public static RegistryObject<ArmorItem> TIN_HELMET = ModRegistries.registerItem(ModMaterials.TIN.getName() + "_helmet", ModRegistries.armor(ArmorItem::new, ModMaterials.TIN, EquipmentSlotType.HEAD, ModRegistries.SMG));
  public static RegistryObject<ArmorItem> TIN_CHESTPLATE = ModRegistries.registerItem(ModMaterials.TIN.getName() + "_chestplate", ModRegistries.armor(ArmorItem::new, ModMaterials.TIN, EquipmentSlotType.CHEST, ModRegistries.SMG));
  public static RegistryObject<ArmorItem> TIN_LEGGINGS = ModRegistries.registerItem(ModMaterials.TIN.getName() + "_leggings", ModRegistries.armor(ArmorItem::new, ModMaterials.TIN, EquipmentSlotType.LEGS, ModRegistries.SMG));
  public static RegistryObject<ArmorItem> TIN_BOOTS = ModRegistries.registerItem(ModMaterials.TIN.getName() + "_boots", ModRegistries.armor(ArmorItem::new, ModMaterials.TIN, EquipmentSlotType.FEET, ModRegistries.SMG));

  public static void init() {}
}
