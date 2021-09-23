package mysticmods.mysticalworld.entity.render;

import mysticmods.mysticalworld.MysticalWorld;
import mysticmods.mysticalworld.entity.SilverFoxEntity;
import mysticmods.mysticalworld.entity.model.FoxModel;
import mysticmods.mysticalworld.entity.model.ModelHolder;
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
  public ResourceLocation getTextureLocation(@Nonnull SilverFoxEntity entity) {
    return new ResourceLocation(MysticalWorld.MODID + ":textures/entity/fox.png");
  }
}
