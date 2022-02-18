package mysticmods.mysticalworld.client.render;

import mysticmods.mysticalworld.entity.DeerEntity;
import mysticmods.mysticalworld.client.model.DeerModel;
import mysticmods.mysticalworld.client.model.ModelHolder;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nonnull;

public class DeerRenderer extends MobRenderer<DeerEntity, DeerModel> {

  private DeerRenderer(@Nonnull EntityRenderDispatcher renderManager, @Nonnull DeerModel modelBase, float shadowSize) {
    super(renderManager, modelBase, shadowSize);
  }

  @Override
  @Nonnull
  public ResourceLocation getTextureLocation(@Nonnull DeerEntity entity) {
    if (entity.getId() % 20 == 0) {
      return new ResourceLocation("mysticalworld:textures/entity/rudolph.png");
    }
    return new ResourceLocation("mysticalworld:textures/entity/deer.png");
  }

  public static class Factory implements IRenderFactory<DeerEntity> {

    @Override
    public EntityRenderer<DeerEntity> createRenderFor(EntityRenderDispatcher manager) {
      return new DeerRenderer(manager, ModelHolder.deerModel, 0.35f);
    }
  }
}
