package mysticmods.mysticalworld.client.model;

import mysticmods.mysticalworld.MysticalWorld;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class ModelHolder {
  public static final ModelLayerLocation BEETLE = new ModelLayerLocation(new ResourceLocation(MysticalWorld.MODID, "beetle"), "main");
  public static final ModelLayerLocation CLAM = new ModelLayerLocation(new ResourceLocation(MysticalWorld.MODID, "clam"), "main");
  public static final ModelLayerLocation DEER = new ModelLayerLocation(new ResourceLocation(MysticalWorld.MODID, "deer"), "main");
  public static final ModelLayerLocation DUCK = new ModelLayerLocation(new ResourceLocation(MysticalWorld.MODID, "duck"), "main");
  public static final ModelLayerLocation ENDERMINI = new ModelLayerLocation(new ResourceLocation(MysticalWorld.MODID, "endermini"), "main");
  public static final ModelLayerLocation FROG = new ModelLayerLocation(new ResourceLocation(MysticalWorld.MODID, "frog"), "main");
  public static final ModelLayerLocation HELL_SPROUT = new ModelLayerLocation(new ResourceLocation(MysticalWorld.MODID, "hell_sprout"), "main");
  public static final ModelLayerLocation LAVA_CAT = new ModelLayerLocation(new ResourceLocation(MysticalWorld.MODID, "lava_cat"), "main");
  public static final ModelLayerLocation OWL = new ModelLayerLocation(new ResourceLocation(MysticalWorld.MODID, "owl"), "main");
  public static final ModelLayerLocation SILKWORM = new ModelLayerLocation(new ResourceLocation(MysticalWorld.MODID, "silkworm"), "main");
  public static final ModelLayerLocation SILVER_FOX = new ModelLayerLocation(new ResourceLocation(MysticalWorld.MODID, "silver_fox"), "main");
  public static final ModelLayerLocation SPROUT = new ModelLayerLocation(new ResourceLocation(MysticalWorld.MODID, "sprout"), "main");
/*
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
*/

  public static void init() {
/*    foxModel = new FoxModel();
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

    beetleHelmet = new BeetleArmorModel(EquipmentSlot.HEAD);
    beetleChestplate = new BeetleArmorModel(EquipmentSlot.CHEST);
    beetleLeggings = new BeetleArmorModel(EquipmentSlot.LEGS);
    beetleBoots = new BeetleArmorModel(EquipmentSlot.FEET);*/
  }

/*  public static BeetleArmorModel modelForSlot (EquipmentSlot slot) {
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
  }*/
}
