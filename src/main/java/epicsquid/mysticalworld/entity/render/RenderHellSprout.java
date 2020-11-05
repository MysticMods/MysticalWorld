package epicsquid.mysticalworld.entity.render;

import epicsquid.mysticalworld.entity.EntityHellSprout;
import epicsquid.mysticalworld.entity.EntitySprout;
import epicsquid.mysticalworld.entity.model.ModelHolder;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nonnull;

public class RenderHellSprout extends RenderLiving<EntityHellSprout> {
  public RenderHellSprout(RenderManager renderManager, ModelBase modelBase, float shadowSize) {
    super(renderManager, modelBase, shadowSize);
  }

  public static final ResourceLocation texture = new ResourceLocation("mysticalworld:textures/entity/sprout_hell.png");

  @Override
  protected ResourceLocation getEntityTexture(EntityHellSprout entity) {
    return texture;
  }

  @Override
  public void renderModel(@Nonnull EntityHellSprout entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
    OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0F, 240.0F);
    super.renderModel(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
  }

  public static class Factory implements IRenderFactory<EntityHellSprout> {
    @Override
    public Render<EntityHellSprout> createRenderFor(RenderManager manager) {
      return new RenderHellSprout(manager, ModelHolder.models.get("sprout"), 0.15f);
    }
  }
}
