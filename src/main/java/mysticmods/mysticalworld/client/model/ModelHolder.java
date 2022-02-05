package mysticmods.mysticalworld.client.model;

import mysticmods.mysticalworld.client.model.armor.AntlerHatModel;
import mysticmods.mysticalworld.client.model.armor.BeetleArmorModel;
import mysticmods.mysticalworld.entity.EnderminiEntity;
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
  public static ClamModel clamModel;

  public static BeetleArmorModel beetleHelmet;
  public static BeetleArmorModel beetleChestplate;
  public static BeetleArmorModel beetleLeggings;
  public static BeetleArmorModel beetleBoots;


  public static AntlerHatModel antlerHatModel;

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
    antlerHatModel = new AntlerHatModel();
    duckModel = new DuckModel();
    clamModel = new ClamModel();

    beetleHelmet = new BeetleArmorModel(EquipmentSlotType.HEAD);
    beetleChestplate = new BeetleArmorModel(EquipmentSlotType.CHEST);
    beetleLeggings = new BeetleArmorModel(EquipmentSlotType.LEGS);
    beetleBoots = new BeetleArmorModel(EquipmentSlotType.FEET);
  }

  public static BeetleArmorModel modelForSlot (EquipmentSlotType slot) {
    switch (slot) {
      case HEAD:
        return beetleHelmet;
      case CHEST:
        return beetleChestplate;
      case LEGS:
        return beetleLeggings;
      default:
      case FEET:
        return beetleBoots;
    }
  }
}
