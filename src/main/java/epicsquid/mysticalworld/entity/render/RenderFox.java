package epicsquid.mysticalworld.entity.render;

import javax.annotation.Nonnull;

import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.entity.EntityFox;
import epicsquid.mysticalworld.entity.model.ModelHolder;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderFox extends RenderLiving<EntityFox> {

  private RenderFox(@Nonnull RenderManager renderManager, @Nonnull ModelBase m, float f) {
    super(renderManager, m, f);
  }

  public static class Factory implements IRenderFactory {

    @Override
    @Nonnull
    public RenderFox createRenderFor(@Nonnull RenderManager manager) {
      return new RenderFox(manager, ModelHolder.models.get("fox"), 0.25f);
    }
  }

  @Override
  public void renderModel(@Nonnull EntityFox entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch,
      float scaleFactor) {
    GlStateManager.pushMatrix();
    if ((entity).getGrowingAge() < 0) {
      GlStateManager.scale(0.5, 0.5, 0.5);
      GlStateManager.translate(0, 1.5, 0);
    }
    GlStateManager.translate(0, -0.0625, 0);
    super.renderModel(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
    GlStateManager.popMatrix();
  }

  @Override
  @Nonnull
  protected ResourceLocation getEntityTexture(@Nonnull EntityFox entity) {
    return new ResourceLocation(MysticalWorld.MODID + ":textures/entity/fox.png");
  }
}
