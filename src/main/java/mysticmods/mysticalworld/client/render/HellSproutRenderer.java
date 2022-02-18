package mysticmods.mysticalworld.client.render;

import mysticmods.mysticalworld.MysticalWorld;
import mysticmods.mysticalworld.client.model.HellSproutModel;
import mysticmods.mysticalworld.client.model.ModelHolder;
import mysticmods.mysticalworld.entity.HellSproutEntity;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class HellSproutRenderer extends MobRenderer<HellSproutEntity, HellSproutModel> {
  private HellSproutRenderer(EntityRenderDispatcher renderManager, HellSproutModel modelBase, float shadowSize) {
    super(renderManager, modelBase, shadowSize);
  }

  public static ResourceLocation TEXTURE = new ResourceLocation(MysticalWorld.MODID, "textures/entity/sprout_hell.png");

  @Override
  public ResourceLocation getTextureLocation(HellSproutEntity entity) {
    return TEXTURE;
  }

  public static class Factory implements IRenderFactory<HellSproutEntity> {
    @Override
    public EntityRenderer<HellSproutEntity> createRenderFor(EntityRenderDispatcher manager) {
      return new HellSproutRenderer(manager, ModelHolder.hellSproutModel, 0.15f);
    }
  }
}
