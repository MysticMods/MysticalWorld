package epicsquid.mysticalworld.entity.render;

import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.entity.SilverFoxEntity;
import epicsquid.mysticalworld.entity.model.FoxModel;
import epicsquid.mysticalworld.entity.model.ModelHolder;
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
  @Nonnull
  public ResourceLocation getEntityTexture(@Nonnull SilverFoxEntity entity) {
    return new ResourceLocation(MysticalWorld.MODID + ":textures/entity/fox.png");
  }
}
