package epicsquid.mysticalworld.entity.render;

import javax.annotation.Nonnull;

import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.entity.EntityBeetle;
import epicsquid.mysticalworld.entity.model.ModelHolder;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderBeetle extends RenderLiving<EntityBeetle> {

  private RenderBeetle(@Nonnull RenderManager renderManager, @Nonnull ModelBase m, float f) {
    super(renderManager, m, f);
  }

  public static class Factory implements IRenderFactory {

    @Override
    @Nonnull
    public RenderBeetle createRenderFor(@Nonnull RenderManager manager) {
      return new RenderBeetle(manager, ModelHolder.models.get("beetle"), 0.05f);
    }
  }

  @Override
  public void renderModel(@Nonnull EntityBeetle entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch,
      float scaleFactor) {
    GlStateManager.pushMatrix();
    if ((entity).getGrowingAge() < 0) {
      GlStateManager.scale(0.2, 0.2, 0.2);
      GlStateManager.translate(0, 6, 0);
    } else {
      GlStateManager.scale(0.45, 0.45, 0.45);
      GlStateManager.translate(0, 2.05, 0);
    }
    super.renderModel(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
    GlStateManager.popMatrix();
  }

  @Override
  @Nonnull
  protected ResourceLocation getEntityTexture(@Nonnull EntityBeetle entity) {
    return new ResourceLocation(MysticalWorld.MODID + ":textures/entity/beetle_blue.png");
  }
}
