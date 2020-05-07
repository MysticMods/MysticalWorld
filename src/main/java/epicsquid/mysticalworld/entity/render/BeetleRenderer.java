package epicsquid.mysticalworld.entity.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.entity.BeetleEntity;
import epicsquid.mysticalworld.entity.model.BeetleModel;
import epicsquid.mysticalworld.entity.model.ModelHolder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
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
  public void render(BeetleEntity entity, float yaw, float partialTicks, MatrixStack matrix, IRenderTypeBuffer buffer, int light)  {
    matrix.push();
    if (entity.getGrowingAge() < 0) {
      matrix.scale(0.3f, 0.3f, 0.3f);
      matrix.translate(0, 3.5, 0);
    } else {
      matrix.scale(0.45f, 0.45f, 0.45f);
      matrix.translate(0, 1.85, 0);
    }
    super.render(entity, yaw, partialTicks, matrix, buffer, light);
    matrix.pop();
  }

  @Override
  @Nonnull
  public ResourceLocation getEntityTexture(@Nonnull BeetleEntity entity) {
    return new ResourceLocation(MysticalWorld.MODID + ":textures/entity/beetle_blue.png");
  }
}
