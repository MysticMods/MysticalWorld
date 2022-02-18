package mysticmods.mysticalworld.client.render;

import mysticmods.mysticalworld.MysticalWorld;
import mysticmods.mysticalworld.client.model.ModelHolder;
import mysticmods.mysticalworld.client.model.OwlModel;
import mysticmods.mysticalworld.entity.OwlEntity;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nonnull;

public class OwlRenderer extends MobRenderer<OwlEntity, OwlModel> {

  public OwlRenderer(EntityRenderDispatcher p_i50961_1_, OwlModel p_i50961_2_, float p_i50961_3_) {
    super(p_i50961_1_, p_i50961_2_, p_i50961_3_);
  }

  public static class Factory implements IRenderFactory<OwlEntity> {

    @Override
    @Nonnull
    public OwlRenderer createRenderFor(@Nonnull EntityRenderDispatcher manager) {
      return new OwlRenderer(manager, ModelHolder.owlModel, 0.25f);
    }
  }

  @Override
  @Nonnull
  public ResourceLocation getTextureLocation(@Nonnull OwlEntity entity) {
    return new ResourceLocation(MysticalWorld.MODID + ":textures/entity/owl.png");
  }
}
