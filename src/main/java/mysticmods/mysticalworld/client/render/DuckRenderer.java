package mysticmods.mysticalworld.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import mysticmods.mysticalworld.MysticalWorld;
import mysticmods.mysticalworld.entity.DuckEntity;
import mysticmods.mysticalworld.client.model.DuckModel;
import mysticmods.mysticalworld.client.model.ModelHolder;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nonnull;

public class DuckRenderer extends MobRenderer<DuckEntity, DuckModel> {
  private DuckRenderer(@Nonnull EntityRenderDispatcher renderManager, @Nonnull DuckModel m, float f) {
    super(renderManager, m, f);
  }

  public static class Factory implements IRenderFactory<DuckEntity> {

    @Override
    @Nonnull
    public DuckRenderer createRenderFor(@Nonnull EntityRenderDispatcher manager) {
      return new DuckRenderer(manager, ModelHolder.duckModel, 0.05f);
    }
  }

  @Override
  protected void scale(DuckEntity entity, PoseStack matrix, float partialTickTime) {
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
    float f = Mth.lerp(pPartialTicks, pLivingBase.oFlap, pLivingBase.flap);
    float f1 = Mth.lerp(pPartialTicks, pLivingBase.oFlapSpeed, pLivingBase.flapSpeed);
    return (Mth.sin(f) + 1.0F) * f1;
  }
}
