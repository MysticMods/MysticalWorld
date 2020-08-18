package epicsquid.mysticalworld.init;

import epicsquid.mysticalworld.MysticalWorld;
import net.minecraft.block.Blocks;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemTier;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvents;
import noobanidus.libs.noobutil.item.WeaponType;
import noobanidus.libs.noobutil.material.MaterialType;

// TODO: No Registrate conversion

public class ModMaterials {
  public static final String AMETHYST_NAME = "amethyst";
  public static final String COPPER_NAME = "copper";
  public static final String LEAD_NAME = "lead";
  public static final String QUICKSILVER_NAME = "quicksilver";
  public static final String SILVER_NAME = "silver";
  public static final String TIN_NAME = "tin";
  public static final String CACTUS_NAME = "cactus";
  public static final String WOODEN_NAME = "wood";
  public static final String STONE_NAME = "stone";
  public static final String IRON_NAME = "iron";
  public static final String GOLD_NAME = "gold";
  public static final String DIAMOND_NAME = "diamond";
  public static final String CARAPACE_NAME = "carapace";
  public static final String ANTLER_NAME = "antler";

  public static MaterialType ANTLER = new MaterialType(ANTLER_NAME).itemMaterial(350, 4.0f, 1.0f, 2, 18).item(() -> ModItems.ANTLERS).armorMaterial(7, new int[]{3, 0, 0, 0}, SoundEvents.ITEM_ARMOR_EQUIP_TURTLE, 1.0f);

  public static MaterialType CARAPACE = new MaterialType(CARAPACE_NAME).itemMaterial(399, 4.0f, 1.0f, 2, 18).item(() -> ModItems.CARAPACE).armorMaterial(7, new int[]{3, 0, 0, 0}, SoundEvents.ITEM_ARMOR_EQUIP_TURTLE, 1.0f);

  public static MaterialType AMETHYST = new MaterialType(AMETHYST_NAME).itemMaterial(960, 8.0f, 3.0f, 3, 14).item(() -> ModItems.AMETHYST_GEM).block(() -> ModBlocks.AMETHYST_BLOCK).ore(() -> ModBlocks.AMETHYST_ORE).armorMaterial(33, new int[]{3, 6, 8, 3}, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0f).setMinXP(1).setMaxXP(4).setModId(MysticalWorld.MODID).putDamageSpeed(
      WeaponType.AXE, 6.0f, -3.1f,
      WeaponType.KNIFE, 0.5f, -1.5f);

  public static MaterialType COPPER = new MaterialType(COPPER_NAME).itemMaterial(200, 4.0f, 2.0f, 2, 7).item(() -> ModItems.COPPER_INGOT).nugget(() -> ModItems.COPPER_NUGGET).dust(() -> ModItems.COPPER_DUST).block(() -> ModBlocks.COPPER_BLOCK).ore(() -> ModBlocks.COPPER_ORE).armorMaterial(15, new int[]{2, 5, 6, 2}, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0f).setModId(MysticalWorld.MODID).putDamageSpeed(
      WeaponType.AXE, 5.0f, -3.1f,
      WeaponType.KNIFE, 0f, -1.5f);

  public static MaterialType LEAD = new MaterialType(LEAD_NAME).itemMaterial(650, 4.0f, 2.5f, 3, 5).item(() -> ModItems.LEAD_INGOT).nugget(() -> ModItems.LEAD_NUGGET).dust(() -> ModItems.LEAD_DUST).block(() -> ModBlocks.LEAD_BLOCK).ore(() -> ModBlocks.LEAD_ORE).armorMaterial(22, new int[]{2, 5, 6, 2}, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 1.0f).setModId(MysticalWorld.MODID).putDamageSpeed(
      WeaponType.AXE, 7.0f, -3.1f,
      WeaponType.KNIFE, 0f, -2.1f);

  public static MaterialType QUICKSILVER = new MaterialType(QUICKSILVER_NAME).itemMaterial(75, 6.0f, 2.5f, 3, 22).item(() -> ModItems.QUICKSILVER_INGOT).nugget(() -> ModItems.QUICKSILVER_NUGGET).dust(() -> ModItems.QUICKSILVER_DUST).block(() -> ModBlocks.QUICKSILVER_BLOCK).ore(() -> ModBlocks.QUICKSILVER_ORE).armorMaterial(7, new int[]{1, 3, 5, 2}, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 0.0f).setModId(MysticalWorld.MODID).putDamageSpeed(
      WeaponType.AXE, 6.0f, -2.6f,
      WeaponType.KNIFE, 0.5f, -1.0f);

  public static MaterialType SILVER = new MaterialType(SILVER_NAME).itemMaterial(175, 6.0f, 2.5f, 2, 22).item(() -> ModItems.SILVER_INGOT).nugget(() -> ModItems.SILVER_NUGGET).dust(() -> ModItems.SILVER_DUST).block(() -> ModBlocks.SILVER_BLOCK).ore(() -> ModBlocks.SILVER_ORE).setModId(MysticalWorld.MODID).armorMaterial(7, new int[]{1, 3, 5, 2}, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 0.0f).putDamageSpeed(
      WeaponType.AXE, 6.0f, -3.1f,
      WeaponType.KNIFE, 0.5f, -1.0f);

  public static MaterialType TIN = new MaterialType(TIN_NAME).itemMaterial(165, 6.5f, 2.0f, 2, 7).item(() -> ModItems.TIN_INGOT).nugget(() -> ModItems.TIN_NUGGET).dust(() -> ModItems.TIN_DUST).block(() -> ModBlocks.TIN_BLOCK).ore(() -> ModBlocks.TIN_ORE).armorMaterial(10, new int[]{1, 4, 5, 2}, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0f).setModId(MysticalWorld.MODID).putDamageSpeed(
      WeaponType.AXE, 6.0f, -2.7f,
      WeaponType.KNIFE, 0f, -1.2f);

  public static MaterialType CACTUS = new MaterialType(CACTUS_NAME).itemMaterial(76, 4.0f, 1.5f, 1, 3, () -> Ingredient.fromItems(Blocks.CACTUS)).setArmorMaterial(ArmorMaterial.LEATHER /* Not actually used as there is no cactus armor */).setModId(MysticalWorld.MODID).putDamageSpeed(
      WeaponType.SWORD, 1.5f, -2.4f,
      WeaponType.SHOVEL, 0.5f, -3.0f,
      WeaponType.PICKAXE, 1.0f, -2.8f,
      WeaponType.AXE, 3.0f, -3.1f,
      WeaponType.KNIFE, 0f, -1.0f,
      WeaponType.HOE, 1.0f, -3.0f);

  public static MaterialType WOODEN = new MaterialType(WOODEN_NAME).setItemTier(ItemTier.WOOD).setArmorMaterial(ArmorMaterial.LEATHER /* Unused */).setModId(MysticalWorld.MODID).putDamageSpeed(
      WeaponType.KNIFE, 0f, -2.0f);

  public static MaterialType STONE = new MaterialType(STONE_NAME).setItemTier(ItemTier.STONE).setArmorMaterial(ArmorMaterial.LEATHER /* Unused */).setModId(MysticalWorld.MODID).putDamageSpeed(
      WeaponType.KNIFE, 0f, -1.0f);

  public static MaterialType IRON = new MaterialType(IRON_NAME).setItemTier(ItemTier.IRON).setArmorMaterial(ArmorMaterial.IRON /* Unused though */).setModId(MysticalWorld.MODID).putDamageSpeed(
      WeaponType.KNIFE, 0f, -1.5f);

  public static MaterialType GOLD = new MaterialType(GOLD_NAME).setItemTier(ItemTier.GOLD).setArmorMaterial(ArmorMaterial.GOLD /* Unused though */).setModId(MysticalWorld.MODID).putDamageSpeed(
      WeaponType.KNIFE, 0f, -1.0f);

  public static MaterialType DIAMOND = new MaterialType(DIAMOND_NAME).setItemTier(ItemTier.DIAMOND).setArmorMaterial(ArmorMaterial.DIAMOND /* Unused */).setModId(MysticalWorld.MODID).putDamageSpeed(
      WeaponType.KNIFE, 0.5f, -1.2f);
}
