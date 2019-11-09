package epicsquid.mysticalworld.entity.render.layers;

import com.mojang.blaze3d.platform.GLX;
import com.mojang.blaze3d.platform.GlStateManager;
import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.entity.LavaCatEntity;
import epicsquid.mysticalworld.entity.model.LavaCatModel;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LayerLava extends LayerRenderer<LavaCatEntity, LavaCatModel> {
  private static final ResourceLocation MAGMA_TEXTURE = new ResourceLocation(MysticalWorld.MODID, "textures/entity/magma_cat_magma.png");
  private static final ResourceLocation OBSIDIAN_TEXTURE = new ResourceLocation(MysticalWorld.MODID, "textures/entity/magma_cat_obsidian.png");

  private IEntityRenderer<LavaCatEntity, LavaCatModel> renderer;
  private LavaCatModel mainModel;

  public LayerLava(IEntityRenderer<LavaCatEntity, LavaCatModel> entityRendererIn) {
    super(entityRendererIn);
    this.renderer = entityRendererIn;
  }

  @Override
  public void render(LavaCatEntity entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
    if (entitylivingbaseIn.getIsLava()) {
      GlStateManager.pushMatrix();
      GlStateManager.enableBlend();
      GlStateManager.enableAlphaTest();
      GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
      boolean flag = !entitylivingbaseIn.isInvisible();
      GlStateManager.depthMask(flag);
      this.renderer.bindTexture(MAGMA_TEXTURE);
      GlStateManager.matrixMode(5890);
      GlStateManager.loadIdentity();
      GlStateManager.scaled(1.5, 1.5, 1.5);
      final float f = entitylivingbaseIn.ticksExisted + partialTicks;
      GlStateManager.translated(0.0f, -f * 0.003f, 0.0f);
      GlStateManager.matrixMode(5888);
      GlStateManager.color4f(1.0f, 1.0f, 1.0f, 1.0f);
      GLX.glMultiTexCoord2f(GLX.GL_TEXTURE1, 240.0f, 240.f);
      GlStateManager.enableLighting();
      mainModel = this.renderer.getEntityModel();
      mainModel.setModelAttributes(this.renderer.getEntityModel());
      mainModel.setLivingAnimations(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks);
      mainModel.setRotationAngles(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
      mainModel.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
      GlStateManager.matrixMode(5890);
      GlStateManager.loadIdentity();
      GlStateManager.matrixMode(5888);
      GlStateManager.depthMask(flag);
      GlStateManager.disableBlend();
      GlStateManager.popMatrix();
    } else {
      this.renderer.bindTexture(OBSIDIAN_TEXTURE);
      mainModel = this.renderer.getEntityModel();
      mainModel.setModelAttributes(this.renderer.getEntityModel());
      mainModel.setLivingAnimations(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks);
      mainModel.setRotationAngles(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
      mainModel.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
    }
  }

  @Override
  public boolean shouldCombineTextures() {
    return false;
  }
}
