package epicsquid.mysticalworld.entity.render;

import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.entity.EntitySpiritBeetle;
import epicsquid.mysticalworld.entity.model.ModelHolder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nonnull;

public class RenderSpiritBeetle extends RenderLiving<EntitySpiritBeetle> {

  private RenderSpiritBeetle(@Nonnull RenderManager renderManager, @Nonnull ModelBase modelBase, float shadowSize) {
    super(renderManager, modelBase, shadowSize);
  }

  public static ResourceLocation TEXTURE = new ResourceLocation(MysticalWorld.MODID, "textures/entity/beetle_blue.png");

  @Override
  @Nonnull
  protected ResourceLocation getEntityTexture(@Nonnull EntitySpiritBeetle entity) {
    return TEXTURE;
  }

  @Override
  public void renderModel(@Nonnull EntitySpiritBeetle entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
    GlStateManager.pushMatrix();
    GlStateManager.scale(0.45, 0.45, 0.45);
    GlStateManager.translate(0, 1.85, 0);
    super.renderModel(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
    GlStateManager.popMatrix();
  }

  @Override
  public void doRender(EntitySpiritBeetle entity, double x, double y, double z, float entityYaw, float partialTicks) {
    boolean flag = entity.isInvisible();
    GlStateManager.depthMask(!flag);
    GlStateManager.matrixMode(5890);
    GlStateManager.loadIdentity();
    GlStateManager.matrixMode(5888);
    GlStateManager.enableBlend();
    GlStateManager.color(0.5F, 0.5F, 0.5F, 1.0F);
    GlStateManager.disableLighting();
    GlStateManager.blendFunc(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE);
    OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0F, 240.0F);
    Minecraft.getMinecraft().entityRenderer.setupFogColor(true);
    super.doRender(entity, x, y, z, entityYaw, partialTicks);
    Minecraft.getMinecraft().entityRenderer.setupFogColor(false);
    GlStateManager.matrixMode(5890);
    GlStateManager.loadIdentity();
    GlStateManager.matrixMode(5888);
    GlStateManager.enableLighting();
    GlStateManager.disableBlend();
    GlStateManager.depthMask(flag);
  }

  public static class Factory implements IRenderFactory<EntitySpiritBeetle> {

    @Override
    public Render<EntitySpiritBeetle> createRenderFor(RenderManager manager) {
      return new RenderSpiritBeetle(manager, ModelHolder.models.get("beetle"), 0);
    }
  }
}
