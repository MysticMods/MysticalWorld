package mysticmods.mysticalworld.client.model;

import mysticmods.mysticalworld.entity.EnderminiEntity;
import mysticmods.mysticalworld.client.model.armor.AntlerHatModel;
import mysticmods.mysticalworld.client.model.armor.BeetleMaskModel;

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
  }
}
