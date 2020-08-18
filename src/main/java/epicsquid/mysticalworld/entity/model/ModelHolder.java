package epicsquid.mysticalworld.entity.model;

import epicsquid.mysticalworld.entity.EnderminiEntity;
import epicsquid.mysticalworld.entity.model.armor.AntlerHatModel;
import epicsquid.mysticalworld.entity.model.armor.BeetleMaskModel;
import net.minecraft.resources.IResourceManager;
import net.minecraftforge.resource.IResourceType;
import net.minecraftforge.resource.ISelectiveResourceReloadListener;

import javax.annotation.Nonnull;
import java.util.function.Predicate;

public class ModelHolder implements ISelectiveResourceReloadListener {
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
  public static SpiritBeetleModel spiritBeetleModel;
  public static SpiritDeerModel spiritDeerModel;

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
    spiritBeetleModel = new SpiritBeetleModel();
    spiritDeerModel = new SpiritDeerModel();
    beetleMaskModel = new BeetleMaskModel();
    antlerHatModel = new AntlerHatModel();
  }

  @Override
  public void onResourceManagerReload(@Nonnull IResourceManager resourceManager) {
  }

  @Override
  public void onResourceManagerReload(IResourceManager resourceManager, Predicate<IResourceType> resourcePredicate) {
    // TODO make this work selectively
    init();
  }
}
