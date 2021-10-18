package mysticmods.mysticalworld.entity.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import mysticmods.mysticalworld.MysticalWorld;
import mysticmods.mysticalworld.entity.DuckEntity;
import mysticmods.mysticalworld.entity.model.DuckModel;
import mysticmods.mysticalworld.entity.model.ModelHolder;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nonnull;

public class DuckRenderer extends MobRenderer<DuckEntity, DuckModel> {
  private DuckRenderer(@Nonnull EntityRendererManager renderManager, @Nonnull DuckModel m, float f) {
    super(renderManager, m, f);
  }

  public static class Factory implements IRenderFactory<DuckEntity> {

    @Override
    @Nonnull
    public DuckRenderer createRenderFor(@Nonnull EntityRendererManager manager) {
      return new DuckRenderer(manager, ModelHolder.duckModel, 0.05f);
    }
  }

  @Override
  protected void scale(DuckEntity entity, MatrixStack matrix, float partialTickTime) {
    // TODO: matrix.scale(0.45f, 0.45f, 0.45f);
  }

  @Override
  @Nonnull
  public ResourceLocation getTextureLocation(@Nonnull DuckEntity entity) {
    return new ResourceLocation(MysticalWorld.MODID + ":textures/entity/duck.png");
  }

  @Override
  protected float getBob(DuckEntity pLivingBase, float pPartialTicks) {
    if (pLivingBase.isInWater()) {
      return 0f;
    }
    float f = MathHelper.lerp(pPartialTicks, pLivingBase.oFlap, pLivingBase.flap);
    float f1 = MathHelper.lerp(pPartialTicks, pLivingBase.oFlapSpeed, pLivingBase.flapSpeed);
    return (MathHelper.sin(f) + 1.0F) * f1;
  }
}
