package epicsquid.mysticalworld.entity.render;

import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.entity.HellSproutEntity;
import epicsquid.mysticalworld.entity.model.HellSproutModel;
import epicsquid.mysticalworld.entity.model.ModelHolder;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class HellSproutRenderer extends MobRenderer<HellSproutEntity, HellSproutModel> {
  private HellSproutRenderer(EntityRendererManager renderManager, HellSproutModel modelBase, float shadowSize) {
    super(renderManager, modelBase, shadowSize);
  }

  public static ResourceLocation TEXTURE = new ResourceLocation(MysticalWorld.MODID, "textures/entity/sprout_hell.png");

  @Override
  public ResourceLocation getEntityTexture(HellSproutEntity entity) {
    return TEXTURE;
  }

  public static class Factory implements IRenderFactory<HellSproutEntity> {
    @Override
    public EntityRenderer<HellSproutEntity> createRenderFor(EntityRendererManager manager) {
      return new HellSproutRenderer(manager, ModelHolder.hellSproutModel, 0.15f);
    }
  }
}
