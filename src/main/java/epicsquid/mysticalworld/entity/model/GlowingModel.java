package epicsquid.mysticalworld.entity.model;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import java.util.function.Function;

public abstract class GlowingModel<T extends Entity> extends AgeableModel<T> {
  public GlowingModel(boolean p_i225943_1_, float p_i225943_2_, float p_i225943_3_) {
    super(p_i225943_1_, p_i225943_2_, p_i225943_3_);
  }

  public GlowingModel(boolean p_i225944_1_, float p_i225944_2_, float p_i225944_3_, float p_i225944_4_, float p_i225944_5_, float p_i225944_6_) {
    super(p_i225944_1_, p_i225944_2_, p_i225944_3_, p_i225944_4_, p_i225944_5_, p_i225944_6_);
  }

  public GlowingModel(Function<ResourceLocation, RenderType> p_i225942_1_, boolean p_i225942_2_, float p_i225942_3_, float p_i225942_4_, float p_i225942_5_, float p_i225942_6_, float p_i225942_7_) {
    super(p_i225942_1_, p_i225942_2_, p_i225942_3_, p_i225942_4_, p_i225942_5_, p_i225942_6_, p_i225942_7_);
  }

  public GlowingModel() {
  }
}
