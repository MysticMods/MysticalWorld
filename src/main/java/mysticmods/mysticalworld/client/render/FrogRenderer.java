package mysticmods.mysticalworld.client.render;

import mysticmods.mysticalworld.MysticalWorld;
import mysticmods.mysticalworld.client.model.FrogModel;
import mysticmods.mysticalworld.client.model.ModelHolder;
import mysticmods.mysticalworld.entity.FrogEntity;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nonnull;

public class FrogRenderer extends MobRenderer<FrogEntity, FrogModel> {

  private FrogRenderer(@Nonnull EntityRenderDispatcher renderManager, @Nonnull FrogModel m, float f) {
    super(renderManager, m, f);
  }

  public static class Factory implements IRenderFactory<FrogEntity> {

    @Override
    @Nonnull
    public FrogRenderer createRenderFor(@Nonnull EntityRenderDispatcher manager) {
      return new FrogRenderer(manager, ModelHolder.frogModel, 0.125f);
    }
  }

  @Override
  @Nonnull
  public ResourceLocation getTextureLocation(@Nonnull FrogEntity entity) {
    return new ResourceLocation(MysticalWorld.MODID + ":textures/entity/" + (entity.getId() % 2 == 0 ? "frog" : "toad") + ".png");
  }
}
