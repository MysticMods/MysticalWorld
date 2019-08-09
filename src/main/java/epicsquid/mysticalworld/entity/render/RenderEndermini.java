package epicsquid.mysticalworld.entity.render;

import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.entity.EntityEndermini;
import epicsquid.mysticalworld.entity.model.ModelHolder;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelEnderman;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.util.Random;

public class RenderEndermini extends RenderLiving<EntityEndermini> {
  private static final ResourceLocation ENDERMINI_TEXTURES = new ResourceLocation(MysticalWorld.MODID, "textures/entity/endermini.png");
  private final Random rnd = new Random();

  public RenderEndermini(RenderManager rendermanagerIn, ModelBase modelbaseIn, float shadowsizeIn) {
    super(rendermanagerIn, modelbaseIn, shadowsizeIn);
    this.addLayer(new LayerEnderminiEyes(this));
    this.addLayer(new LayerHeldBlockMini(this));
  }

  public ModelEnderman getMainModel() {
    return (ModelEnderman) super.getMainModel();
  }

  /**
   * Renders the desired {@code T} type Entity.
   */
  public void doRender(EntityEndermini entity, double x, double y, double z, float entityYaw, float partialTicks) {
    IBlockState iblockstate = entity.getHeldBlockState();
    ModelEnderman modelenderman = this.getMainModel();
    modelenderman.isCarrying = iblockstate != null;
    modelenderman.isAttacking = entity.isScreaming();

    if (entity.isScreaming()) {
      x += this.rnd.nextGaussian() * 0.02D;
      z += this.rnd.nextGaussian() * 0.02D;
    }

    super.doRender(entity, x, y, z, entityYaw, partialTicks);
  }

  /**
   * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
   */
  protected ResourceLocation getEntityTexture(EntityEndermini entity) {
    return ENDERMINI_TEXTURES;
  }

  @Override
  public void renderModel(@Nonnull EntityEndermini entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
    GlStateManager.pushMatrix();
    GlStateManager.scale(0.4, 0.4, 0.4);
    GlStateManager.translate(0, 2.2, 0);
    super.renderModel(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
    GlStateManager.popMatrix();
  }

  @SideOnly(Side.CLIENT)
  public static class LayerEnderminiEyes implements LayerRenderer<EntityEndermini> {
    private static final ResourceLocation RES_ENDERMAN_EYES = new ResourceLocation("textures/entity/enderman/enderman_eyes.png");
    private final RenderEndermini endermanRenderer;

    public LayerEnderminiEyes(RenderEndermini endermanRendererIn) {
      this.endermanRenderer = endermanRendererIn;
    }

    public void doRenderLayer(EntityEndermini entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
      this.endermanRenderer.bindTexture(RES_ENDERMAN_EYES);
      GlStateManager.pushMatrix();
      GlStateManager.scale(0.4, 0.4, 0.4);
      GlStateManager.translate(0, 2.2, 0);
      GlStateManager.enableBlend();
      GlStateManager.disableAlpha();
      GlStateManager.blendFunc(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE);
      GlStateManager.disableLighting();
      GlStateManager.depthMask(!entitylivingbaseIn.isInvisible());
      int i = 61680;
      int j = 61680;
      int k = 0;
      OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 61680.0F, 0.0F);
      GlStateManager.enableLighting();
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      Minecraft.getMinecraft().entityRenderer.setupFogColor(true);
      this.endermanRenderer.getMainModel().render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
      Minecraft.getMinecraft().entityRenderer.setupFogColor(false);
      this.endermanRenderer.setLightmap(entitylivingbaseIn);
      GlStateManager.depthMask(true);
      GlStateManager.disableBlend();
      GlStateManager.enableAlpha();
      GlStateManager.popMatrix();
    }

    public boolean shouldCombineTextures() {
      return false;
    }
  }

  @SideOnly(Side.CLIENT)
  public static class LayerHeldBlockMini implements LayerRenderer<EntityEndermini> {
    private final RenderEndermini endermanRenderer;

    public LayerHeldBlockMini(RenderEndermini endermanRendererIn) {
      this.endermanRenderer = endermanRendererIn;
    }

    public void doRenderLayer(EntityEndermini entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
      IBlockState iblockstate = entitylivingbaseIn.getHeldBlockState();

      if (iblockstate != null) {
        BlockRendererDispatcher blockrendererdispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
        GlStateManager.pushMatrix();
        GlStateManager.scale(0.4, 0.4, 0.4);
        GlStateManager.translate(0, 2.2, 0);
        GlStateManager.enableRescaleNormal();
        GlStateManager.pushMatrix();
        GlStateManager.translate(0.0F, 0.6875F, -0.75F);
        GlStateManager.rotate(20.0F, 1.0F, 0.0F, 0.0F);
        GlStateManager.rotate(45.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.translate(0.25F, 0.1875F, 0.25F);
        float f = 0.5F;
        GlStateManager.scale(-0.5F, -0.5F, 0.5F);
        int i = entitylivingbaseIn.getBrightnessForRender();
        int j = i % 65536;
        int k = i / 65536;
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float) j, (float) k);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.endermanRenderer.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
        blockrendererdispatcher.renderBlockBrightness(iblockstate, 1.0F);
        GlStateManager.popMatrix();
        GlStateManager.disableRescaleNormal();
        GlStateManager.popMatrix();
      }
    }

    public boolean shouldCombineTextures() {
      return false;
    }
  }

  public static class Factory implements IRenderFactory<EntityEndermini> {

    @Override
    public Render<EntityEndermini> createRenderFor(RenderManager manager) {
      return new RenderEndermini(manager, ModelHolder.models.get("endermini"), 0.35f);
    }
  }
}
