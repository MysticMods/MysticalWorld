package epicsquid.mysticalworld.entity.render.layers;

import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.entity.EntityLavaCat;
import epicsquid.mysticalworld.entity.model.ModelLavaCat;
import epicsquid.mysticalworld.entity.render.RenderLavaCat;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class LayerLava implements LayerRenderer<EntityLavaCat> {
  private static final ResourceLocation MAGMA_TEXTURE = new ResourceLocation(MysticalWorld.MODID, "textures/entity/magma_cat_magma.png");
  private static final ResourceLocation OBSIDIAN_TEXTURE = new ResourceLocation(MysticalWorld.MODID, "textures/entity/magma_cat_obsidian.png");

  private RenderLavaCat renderer;
  private ModelLavaCat mainModel;

  public LayerLava(RenderLavaCat renderer) {
    this.renderer = renderer;
  }

  @Override
  public void doRenderLayer(EntityLavaCat entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
    if (entitylivingbaseIn.getIsLava()) {
      GlStateManager.pushMatrix();
      GlStateManager.enableBlend();
      GlStateManager.enableAlpha();
      GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
      boolean flag = !entitylivingbaseIn.isInvisible();
      GlStateManager.depthMask(flag);
      this.renderer.bindTexture(MAGMA_TEXTURE);
      GlStateManager.matrixMode(5890);
      GlStateManager.loadIdentity();
      GlStateManager.scale(1.5, 1.5, 1.5);
      final float f = entitylivingbaseIn.ticksExisted + partialTicks;
      GlStateManager.translate(0.0f, -f * 0.003f, 0.0f);
      GlStateManager.matrixMode(5888);
      GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
      OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0F, 240.0F);
      GlStateManager.enableLighting();
      mainModel = (ModelLavaCat) this.renderer.getMainModel();
      mainModel.setModelAttributes(this.renderer.getMainModel());
      mainModel.setLivingAnimations(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks);
      mainModel.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entitylivingbaseIn);
      mainModel.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
      GlStateManager.matrixMode(5890);
      GlStateManager.loadIdentity();
      GlStateManager.matrixMode(5888);
      GlStateManager.depthMask(flag);
      GlStateManager.disableBlend();
      GlStateManager.popMatrix();
    } else {
      this.renderer.bindTexture(OBSIDIAN_TEXTURE);
      mainModel = (ModelLavaCat) this.renderer.getMainModel();
      mainModel.setModelAttributes(this.renderer.getMainModel());
      mainModel.setLivingAnimations(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks);
      mainModel.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entitylivingbaseIn);
      mainModel.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
    }
  }

  @Override
  public boolean shouldCombineTextures() {
    return false;
  }
}
