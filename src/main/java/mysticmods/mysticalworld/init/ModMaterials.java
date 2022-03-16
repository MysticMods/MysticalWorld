package mysticmods.mysticalworld.init;

import mysticmods.mysticalworld.MWTags;
import mysticmods.mysticalworld.MysticalWorld;
import mysticmods.mysticalworld.config.ConfigManager;
import mysticmods.mysticalworld.init.deferred.ModBlocks;
import mysticmods.mysticalworld.init.deferred.ModItems;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import noobanidus.libs.noobutil.ingredient.LazyIngredient;
import noobanidus.libs.noobutil.item.WeaponType;
import noobanidus.libs.noobutil.material.MaterialType;

public class ModMaterials {
  public static final String SAPPHIRE_NAME = "sapphire";
  public static final String COPPER_NAME = "copper";
  public static final String LEAD_NAME = "lead";
  public static final String ORICHALCUM_NAME = "orichalcum";
  public static final String SILVER_NAME = "silver";
  public static final String TIN_NAME = "tin";
  public static final String CACTUS_NAME = "cactus";
  public static final String WOODEN_NAME = "wood";
  public static final String STONE_NAME = "stone";
  public static final String IRON_NAME = "iron";
  public static final String GOLD_NAME = "gold";
  public static final String DIAMOND_NAME = "diamond";
  public static final String NETHERITE_NAME = "netherite";
  public static final String CARAPACE_NAME = "carapace";
  public static final String ANTLER_NAME = "antler";
  public static final String QUARTZ_NAME = "quartz";

  public static MaterialType ANTLER = new MaterialType(ANTLER_NAME)
      .item(() -> ModItems.ANTLERS)
      .itemMaterial(350, 4.0f, 1.0f, 2, 18)
      .armorMaterial(7, new int[]{3, 0, 0, 0}, SoundEvents.ARMOR_EQUIP_TURTLE, 1.0f, 0f)
      .setConfigProvider(ConfigManager::getArmorConfig);

  public static MaterialType CARAPACE = new MaterialType(CARAPACE_NAME)
      .item(() -> ModItems.CARAPACE)
      .itemMaterial(399, 4.0f, 1.0f, 2, 18, () -> MWTags.Items.CARAPACE)
      .armorMaterial(25, new int[]{2, 5, 6, 2}, SoundEvents.ARMOR_EQUIP_TURTLE, 0.0f, 0f)
      .setConfigProvider(ConfigManager::getArmorConfig);

  public static MaterialType SAPPHIRE = new MaterialType(SAPPHIRE_NAME)
      .itemMaterial(960, 8.0f, 3.0f, 3, 14, () -> MWTags.Items.SAPPHIRE_GEM)
      .item(() -> ModItems.SAPPHIRE_GEM)
      .block(() -> ModBlocks.SAPPHIRE_BLOCK)
      .ore(() -> ModBlocks.SAPPHIRE_ORE)
      .armorMaterial(33, new int[]{3, 6, 8, 3}, SoundEvents.ARMOR_EQUIP_DIAMOND, 2.0f, 0f)
      .setMinXP(1)
      .setMaxXP(4)
      .setModId(MysticalWorld.MODID)
      .putDamageSpeed(
          WeaponType.AXE, 6.0f, -3.1f,
          WeaponType.KNIFE, 0.5f, -1.5f
      )
      .setConfigProvider(ConfigManager::getArmorConfig);

  public static MaterialType COPPER = new MaterialType(COPPER_NAME)
      .itemMaterial(200, 4.0f, 2.0f, 2, 7, () -> MWTags.Items.COPPER_INGOT)
      .nugget(() -> ModItems.COPPER_NUGGET)
      .dust(() -> ModItems.COPPER_DUST)
/*      .block(() -> ModBlocks.COPPER_BLOCK)
      .ore(() -> ModBlocks.COPPER_ORE)*/
      .armorMaterial(15, new int[]{2, 5, 6, 2}, SoundEvents.ARMOR_EQUIP_IRON, 0.0f, 0f)
      .setModId(MysticalWorld.MODID)
      .putDamageSpeed(
          WeaponType.AXE, 5.0f, -3.1f,
          WeaponType.KNIFE, 0f, -1.5f
      )
      .setConfigProvider(ConfigManager::getArmorConfig);

  public static MaterialType LEAD = new MaterialType(LEAD_NAME)
      .itemMaterial(650, 4.0f, 2.5f, 3, 5, () -> MWTags.Items.LEAD_INGOT)
      .item(() -> ModItems.LEAD_INGOT)
      .nugget(() -> ModItems.LEAD_NUGGET)
      .dust(() -> ModItems.LEAD_DUST)
      .block(() -> ModBlocks.LEAD_BLOCK)
      .ore(() -> ModBlocks.LEAD_ORE)
      .armorMaterial(22, new int[]{2, 5, 6, 2}, SoundEvents.ARMOR_EQUIP_IRON, 1.0f, 0f)
      .setModId(MysticalWorld.MODID)
      .putDamageSpeed(
          WeaponType.AXE, 7.0f, -3.1f,
          WeaponType.KNIFE, 0f, -2.1f
      )
      .setConfigProvider(ConfigManager::getArmorConfig);

  public static MaterialType ORICHALCUM = new MaterialType(ORICHALCUM_NAME)
      .itemMaterial(75, 6.0f, 2.5f, 3, 22, () -> MWTags.Items.ORICHALCUM_INGOT)
      .item(() -> ModItems.ORICHALCUM_INGOT)
      .nugget(() -> ModItems.ORICHALCUM_NUGGET)
      .dust(() -> ModItems.ORICHALCUM_DUST)
      .block(() -> ModBlocks.ORICHALCUM_BLOCK)
      .armorMaterial(7, new int[]{1, 3, 5, 2}, SoundEvents.ARMOR_EQUIP_GOLD, 0.0f, 0f)
      .setModId(MysticalWorld.MODID)
      .putDamageSpeed(
          WeaponType.AXE, 6.0f, -2.6f,
          WeaponType.KNIFE, 0.5f, -1.0f
      )
      .setConfigProvider(ConfigManager::getArmorConfig);

  public static MaterialType SILVER = new MaterialType(SILVER_NAME).itemMaterial(175, 6.0f, 2.5f, 2, 22, () -> MWTags.Items.SILVER_INGOT)
      .item(() -> ModItems.SILVER_INGOT)
      .nugget(() -> ModItems.SILVER_NUGGET)
      .dust(() -> ModItems.SILVER_DUST)
      .block(() -> ModBlocks.SILVER_BLOCK)
      .ore(() -> ModBlocks.SILVER_ORE).setModId(MysticalWorld.MODID)
      .armorMaterial(7, new int[]{1, 3, 5, 2}, SoundEvents.ARMOR_EQUIP_GOLD, 0.0f, 0f)
      .putDamageSpeed(
          WeaponType.AXE, 6.0f, -3.1f,
          WeaponType.KNIFE, 0.5f, -1.0f
      )
      .setConfigProvider(ConfigManager::getArmorConfig);

  public static MaterialType TIN = new MaterialType(TIN_NAME)
      .itemMaterial(165, 6.5f, 2.0f, 2, 7, () -> MWTags.Items.TIN_INGOT)
      .item(() -> ModItems.TIN_INGOT)
      .nugget(() -> ModItems.TIN_NUGGET)
      .dust(() -> ModItems.TIN_DUST)
      .block(() -> ModBlocks.TIN_BLOCK)
      .ore(() -> ModBlocks.TIN_ORE)
      .armorMaterial(10, new int[]{1, 4, 5, 2}, SoundEvents.ARMOR_EQUIP_IRON, 0.0f, 0f)
      .setModId(MysticalWorld.MODID)
      .putDamageSpeed(
          WeaponType.AXE, 6.0f, -2.7f,
          WeaponType.KNIFE, 0f, -1.2f
      )
      .setConfigProvider(ConfigManager::getArmorConfig);

  public static MaterialType QUARTZ = new MaterialType(QUARTZ_NAME)
      .itemMaterial(200, 4.0f, 2.0f, 2, 7)
      .item(() -> () -> Items.QUARTZ)
      .ore(() -> ModBlocks.GRANITE_QUARTZ_ORE)
      .setMinXP(1)
      .setMaxXP(4)
      .setModId(MysticalWorld.MODID);

  public static MaterialType CACTUS = new MaterialType(CACTUS_NAME)
      .itemMaterial(76, 4.0f, 1.5f, 1, 3, new LazyIngredient(() -> Ingredient.of(Blocks.CACTUS)))
      .setModId(MysticalWorld.MODID)
      .putDamageSpeed(
          WeaponType.SWORD, 1.5f, -2.4f,
          WeaponType.SHOVEL, 0.5f, -3.0f,
          WeaponType.PICKAXE, 1.0f, -2.8f,
          WeaponType.AXE, 3.0f, -3.1f,
          WeaponType.KNIFE, 0f, -1.0f,
          WeaponType.HOE, 1.0f, -3.0f
      );

  public static MaterialType WOODEN = new MaterialType(WOODEN_NAME)
      .setItemTier(Tiers.WOOD)
      .setModId(MysticalWorld.MODID)
      .putDamageSpeed(
          WeaponType.KNIFE, 0f, -2.0f
      );

  public static MaterialType STONE = new MaterialType(STONE_NAME)
      .setItemTier(Tiers.STONE)
      .setModId(MysticalWorld.MODID)
      .putDamageSpeed(
          WeaponType.KNIFE, 0f, -1.0f
      );

  public static MaterialType IRON = new MaterialType(IRON_NAME)
      .setItemTier(Tiers.IRON)
      .setModId(MysticalWorld.MODID)
      .putDamageSpeed(
          WeaponType.KNIFE, 0f, -1.5f
      );

  public static MaterialType GOLD = new MaterialType(GOLD_NAME)
      .setItemTier(Tiers.GOLD)
      .setModId(MysticalWorld.MODID)
      .putDamageSpeed(
          WeaponType.KNIFE, 0f, -1.0f
      );

  public static MaterialType DIAMOND = new MaterialType(DIAMOND_NAME)
      .setItemTier(Tiers.DIAMOND)
      .setModId(MysticalWorld.MODID)
      .putDamageSpeed(
          WeaponType.KNIFE, 0.5f, -1.2f
      );

  public static MaterialType NETHERITE = new MaterialType(NETHERITE_NAME)
      .setItemTier(Tiers.NETHERITE)
      .setModId(MysticalWorld.MODID)
      .putDamageSpeed(
          WeaponType.KNIFE, 1f, -1.2f
      );
}
