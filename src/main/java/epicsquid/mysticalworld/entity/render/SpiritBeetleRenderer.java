package epicsquid.mysticalworld.entity.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.entity.SpiritBeetleEntity;
import epicsquid.mysticalworld.entity.model.ModelHolder;
import epicsquid.mysticalworld.entity.model.SpiritBeetleModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nonnull;

public class SpiritBeetleRenderer extends MobRenderer<SpiritBeetleEntity, SpiritBeetleModel> {

  private SpiritBeetleRenderer(@Nonnull EntityRendererManager renderManager, @Nonnull SpiritBeetleModel m, float f) {
    super(renderManager, m, f);
  }

  public static class Factory implements IRenderFactory<SpiritBeetleEntity> {

    @Override
    @Nonnull
    public SpiritBeetleRenderer createRenderFor(@Nonnull EntityRendererManager manager) {
      return new SpiritBeetleRenderer(manager, ModelHolder.spiritBeetleModel, 0.05f);
    }
  }

  @Override
  protected void preRenderCallback(SpiritBeetleEntity entity, MatrixStack matrix, float partialTickTime) {
    matrix.scale(0.45f, 0.45f, 0.45f);
  }

  private static final ResourceLocation TEXTURE = new ResourceLocation(MysticalWorld.MODID + ":textures/entity/beetle_blue.png");

  @Override
  @Nonnull
  public ResourceLocation getEntityTexture(@Nonnull SpiritBeetleEntity entity) {
    return TEXTURE;
  }
}
