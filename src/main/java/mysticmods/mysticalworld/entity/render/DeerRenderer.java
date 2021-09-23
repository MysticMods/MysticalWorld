package mysticmods.mysticalworld.entity.render;

import mysticmods.mysticalworld.entity.DeerEntity;
import mysticmods.mysticalworld.entity.model.DeerModel;
import mysticmods.mysticalworld.entity.model.ModelHolder;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nonnull;

public class DeerRenderer extends MobRenderer<DeerEntity, DeerModel> {

  private DeerRenderer(@Nonnull EntityRendererManager renderManager, @Nonnull DeerModel modelBase, float shadowSize) {
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
    public EntityRenderer<DeerEntity> createRenderFor(EntityRendererManager manager) {
      return new DeerRenderer(manager, ModelHolder.deerModel, 0.35f);
    }
  }
}
