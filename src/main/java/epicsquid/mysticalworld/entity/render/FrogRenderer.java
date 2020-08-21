package epicsquid.mysticalworld.entity.render;

import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.entity.FrogEntity;
import epicsquid.mysticalworld.entity.model.FrogModel;
import epicsquid.mysticalworld.entity.model.ModelHolder;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nonnull;

public class FrogRenderer extends MobRenderer<FrogEntity, FrogModel> {

  private FrogRenderer(@Nonnull EntityRendererManager renderManager, @Nonnull FrogModel m, float f) {
    super(renderManager, m, f);
  }

  public static class Factory implements IRenderFactory<FrogEntity> {

    @Override
    @Nonnull
    public FrogRenderer createRenderFor(@Nonnull EntityRendererManager manager) {
      return new FrogRenderer(manager, ModelHolder.frogModel, 0.125f);
    }
  }

  @Override
  @Nonnull
  public ResourceLocation getEntityTexture(@Nonnull FrogEntity entity) {
    return new ResourceLocation(MysticalWorld.MODID + ":textures/entity/" + (entity.getEntityId() % 2 == 0 ? "frog" : "toad") + ".png");
  }
}
