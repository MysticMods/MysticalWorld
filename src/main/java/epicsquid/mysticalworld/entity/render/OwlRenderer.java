package epicsquid.mysticalworld.entity.render;

import com.mojang.blaze3d.platform.GlStateManager;
import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.entity.OwlEntity;
import epicsquid.mysticalworld.entity.model.ModelHolder;
import epicsquid.mysticalworld.entity.model.OwlModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nonnull;

public class OwlRenderer extends MobRenderer<OwlEntity, OwlModel> {

  public OwlRenderer(EntityRendererManager p_i50961_1_, OwlModel p_i50961_2_, float p_i50961_3_) {
    super(p_i50961_1_, p_i50961_2_, p_i50961_3_);
  }

  public static class Factory implements IRenderFactory<OwlEntity> {

    @Override
    @Nonnull
    public OwlRenderer createRenderFor(@Nonnull EntityRendererManager manager) {
      return new OwlRenderer(manager, ModelHolder.owlModel, 0.25f);
    }
  }

  @Override
  public void renderModel(@Nonnull OwlEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
    GlStateManager.pushMatrix();
    if ((entity).getGrowingAge() < 0) {
      GlStateManager.scaled(0.5, 0.5, 0.5);
      GlStateManager.translated(0, 1.5, 0);
    }
    GlStateManager.translated(0, -0.0625, 0);
    super.renderModel(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
    GlStateManager.popMatrix();
  }

  @Override
  @Nonnull
  protected ResourceLocation getEntityTexture(@Nonnull OwlEntity entity) {
    return new ResourceLocation(MysticalWorld.MODID + ":textures/entity/owl.png");
  }
}
