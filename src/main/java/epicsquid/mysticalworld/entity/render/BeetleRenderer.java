package epicsquid.mysticalworld.entity.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.entity.BeetleEntity;
import epicsquid.mysticalworld.entity.model.BeetleModel;
import epicsquid.mysticalworld.entity.model.ModelHolder;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nonnull;

public class BeetleRenderer extends MobRenderer<BeetleEntity, BeetleModel> {

  private BeetleRenderer(@Nonnull EntityRendererManager renderManager, @Nonnull BeetleModel m, float f) {
    super(renderManager, m, f);
  }

  public static class Factory implements IRenderFactory<BeetleEntity> {

    @Override
    @Nonnull
    public BeetleRenderer createRenderFor(@Nonnull EntityRendererManager manager) {
      return new BeetleRenderer(manager, ModelHolder.beetleModel, 0.05f);
    }
  }

  @Override
  protected void preRenderCallback(BeetleEntity entity, MatrixStack matrix, float partialTickTime) {
    matrix.scale(0.45f, 0.45f, 0.45f);
  }

  @Override
  @Nonnull
  public ResourceLocation getEntityTexture(@Nonnull BeetleEntity entity) {
    return new ResourceLocation(MysticalWorld.MODID + ":textures/entity/beetle_blue.png");
  }
}
