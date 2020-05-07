package epicsquid.mysticalworld.entity.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.entity.SilverFoxEntity;
import epicsquid.mysticalworld.entity.model.FoxModel;
import epicsquid.mysticalworld.entity.model.ModelHolder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nonnull;

public class FoxRenderer extends MobRenderer<SilverFoxEntity, FoxModel> {

  private FoxRenderer(@Nonnull EntityRendererManager renderManager, @Nonnull FoxModel m, float f) {
    super(renderManager, m, f);
  }

  public static class Factory implements IRenderFactory<SilverFoxEntity> {

    @Override
    @Nonnull
    public FoxRenderer createRenderFor(@Nonnull EntityRendererManager manager) {
      return new FoxRenderer(manager, ModelHolder.foxModel, 0.25f);
    }
  }

  @Override
  public void render(SilverFoxEntity entity, float yaw, float partialTicks, MatrixStack matrix, IRenderTypeBuffer buffer, int light) {
    matrix.push();
    if (entity.getGrowingAge() < 0) {
      matrix.scale(0.5f, 0.5f, 0.5f);
      matrix.translate(0, 1.5, 0);
    }
    matrix.translate(0, -0.0625, 0);
    super.render(entity, yaw, partialTicks, matrix, buffer, light);
    matrix.pop();
  }

  @Override
  @Nonnull
  public ResourceLocation getEntityTexture(@Nonnull SilverFoxEntity entity) {
    return new ResourceLocation(MysticalWorld.MODID + ":textures/entity/fox.png");
  }
}
