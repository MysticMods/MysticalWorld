package mysticmods.mysticalworld.client.render;

import mysticmods.mysticalworld.client.model.ModelHolder;
import mysticmods.mysticalworld.client.model.SproutModel;
import mysticmods.mysticalworld.entity.SproutEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class SproutRenderer extends MobRenderer<SproutEntity, SproutModel> {
  public SproutRenderer(EntityRendererProvider.Context context) {
    super(context, new SproutModel(context.bakeLayer(ModelHolder.SPROUT)), 0.15f);
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
}
