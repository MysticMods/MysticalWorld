package epicsquid.mysticalworld.entity.render;

import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.entity.EntityBeetle;
import epicsquid.mysticalworld.entity.EntityClam;
import epicsquid.mysticalworld.entity.model.ModelHolder;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nonnull;

public class RenderClam extends RenderLiving<EntityClam> {

  private RenderClam(@Nonnull RenderManager renderManager, @Nonnull ModelBase m, float f) {
    super(renderManager, m, f);
  }

  public static class Factory implements IRenderFactory {
    @Override
    @Nonnull
    public RenderClam createRenderFor(@Nonnull RenderManager manager) {
      return new RenderClam(manager, ModelHolder.models.get("clam"), 0.05f);
    }
  }

  @Override
  public void renderModel(@Nonnull EntityClam entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
    GlStateManager.pushMatrix();
    super.renderModel(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
    GlStateManager.popMatrix();
  }

  @Override
  @Nonnull
  protected ResourceLocation getEntityTexture(@Nonnull EntityClam entity) {
    return new ResourceLocation(MysticalWorld.MODID + ":textures/entity/clam.png");
  }
}
