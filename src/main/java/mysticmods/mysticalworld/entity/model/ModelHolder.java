package mysticmods.mysticalworld.entity.model;

import mysticmods.mysticalworld.entity.EnderminiEntity;
import mysticmods.mysticalworld.entity.model.armor.AntlerHatModel;
import mysticmods.mysticalworld.entity.model.armor.BeetleArmorModel;
import mysticmods.mysticalworld.entity.model.armor.BeetleMaskModel;
import net.minecraft.inventory.EquipmentSlotType;

public class ModelHolder {
  public static FoxModel foxModel;
  public static HellSproutModel hellSproutModel;
  public static FrogModel frogModel;
  public static BeetleModel beetleModel;
  public static DeerModel deerModel;
  public static SproutModel sproutModel;
  public static EnderminiModel<EnderminiEntity> enderminiModel;
  public static LavaCatModel lavaCatModel;
  public static OwlModel owlModel;
  public static SilkwormModel silkwormModel;
  public static DuckModel duckModel;

  public static BeetleMaskModel beetleMaskModel;
  public static AntlerHatModel antlerHatModel;

  public static BeetleArmorModel beetle_helm;
  public static BeetleArmorModel beetle_chestplate;
  public static BeetleArmorModel beetle_leggings;
  public static BeetleArmorModel beetle_boots;

  public static void init() {
    foxModel = new FoxModel();
    frogModel = new FrogModel();
    beetleModel = new BeetleModel();
    deerModel = new DeerModel();
    sproutModel = new SproutModel();
    hellSproutModel = new HellSproutModel();
    enderminiModel = new EnderminiModel<>();
    lavaCatModel = new LavaCatModel();
    owlModel = new OwlModel();
    silkwormModel = new SilkwormModel();
    beetleMaskModel = new BeetleMaskModel();
    antlerHatModel = new AntlerHatModel();
    duckModel = new DuckModel();
    beetle_helm = new BeetleArmorModel(EquipmentSlotType.HEAD);
    beetle_boots = new BeetleArmorModel(EquipmentSlotType.FEET);
    beetle_chestplate = new BeetleArmorModel(EquipmentSlotType.CHEST);
    beetle_leggings = new BeetleArmorModel(EquipmentSlotType.LEGS);
  }
}
