package mysticmods.mysticalworld.client.render;

import mysticmods.mysticalworld.client.model.ModelHolder;
import mysticmods.mysticalworld.client.model.SproutModel;
import mysticmods.mysticalworld.entity.SproutEntity;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class SproutRenderer extends MobRenderer<SproutEntity, SproutModel> {
  private SproutRenderer(EntityRenderDispatcher renderManager, SproutModel modelBase, float shadowSize) {
    super(renderManager, modelBase, shadowSize);
  }

  @Override
  public ResourceLocation getTextureLocation(SproutEntity entity) {
    switch (entity.getEntityData().get(SproutEntity.variant)) {
      case 1: {
        return new ResourceLocation("mysticalworld:textures/entity/sprout_tan.png");
      }
      case 2: {
        return new ResourceLocation("mysticalworld:textures/entity/sprout_red.png");
      }
      case 3: {
        return new ResourceLocation("mysticalworld:textures/entity/sprout_purple.png");
      }
      default: {
        return new ResourceLocation("mysticalworld:textures/entity/sprout_green.png");
      }
    }
  }

  public static class Factory implements IRenderFactory<SproutEntity> {
    @Override
    public EntityRenderer<SproutEntity> createRenderFor(EntityRenderDispatcher manager) {
      return new SproutRenderer(manager, ModelHolder.sproutModel, 0.15f);
    }
  }
}
