package mysticmods.mysticalworld.client.render;

import mysticmods.mysticalworld.MysticalWorld;
import mysticmods.mysticalworld.client.model.FoxModel;
import mysticmods.mysticalworld.client.model.ModelHolder;
import mysticmods.mysticalworld.entity.SilverFoxEntity;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nonnull;

public class FoxRenderer extends MobRenderer<SilverFoxEntity, FoxModel> {

  private FoxRenderer(@Nonnull EntityRenderDispatcher renderManager, @Nonnull FoxModel m, float f) {
    super(renderManager, m, f);
  }

  public static class Factory implements IRenderFactory<SilverFoxEntity> {

    @Override
    @Nonnull
    public FoxRenderer createRenderFor(@Nonnull EntityRenderDispatcher manager) {
      return new FoxRenderer(manager, ModelHolder.foxModel, 0.25f);
    }
  }

  @Override
  @Nonnull
  public ResourceLocation getTextureLocation(@Nonnull SilverFoxEntity entity) {
    return new ResourceLocation(MysticalWorld.MODID + ":textures/entity/fox.png");
  }
}
