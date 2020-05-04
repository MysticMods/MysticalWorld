/*package epicsquid.mysticalworld.entity.model.heads;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.entity.model.GenericHeadModel;
import net.minecraft.client.renderer.entity.model.ModelRenderer;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.ResourceLocation;

@SuppressWarnings("Duplicates")
public class EndermanHeadModel extends GenericHeadModel {
  private static final ResourceLocation RES_ENDERMAN_EYES = new ResourceLocation("minecraft", "textures/entity/enderman/enderman_eyes.png");

  private ModelRenderer bipedHead;
  private ModelRenderer bipedHeadwear;

  public EndermanHeadModel() {
    super(0, 0, 64, 32);
    this.bipedHead = new ModelRenderer(this, 0, 0);
    this.bipedHead.addCuboid(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0);
    this.bipedHead.setRotationPoint(0.0F, 0.0f, 0.0F);
    this.bipedHeadwear = new ModelRenderer(this, 0, 16);
    this.bipedHeadwear.addCuboid(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.5F);
    this.bipedHeadwear.setRotationPoint(0.0F, 0.0F, 0.0F);
  }

  @Override
  public void func_217104_a(float progress, float a, float b, float rotation, float c, float scale) {
    this.bipedHead.rotateAngleY = (float) (rotation * (Math.PI / 180));
    this.bipedHead.rotateAngleX = (float) (c * (Math.PI / 180));
    this.bipedHeadwear.rotateAngleY = (float) (rotation * (Math.PI / 180));
    this.bipedHeadwear.rotateAngleX = (float) (c * (Math.PI / 180));
    this.bipedHead.render(scale);
    //this.bipedHeadwear.render(scale);
    Minecraft.getInstance().getRenderManager().textureManager.bindTexture(RES_ENDERMAN_EYES);
    GlStateManager.enableBlend();
    GlStateManager.disableAlphaTest();
    GlStateManager.blendFunc(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE);
    GlStateManager.disableLighting();
    GlStateManager.depthMask(true);
    GlStateManager.enableLighting();
    GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
    GameRenderer gamerenderer = Minecraft.getInstance().gameRenderer;
    gamerenderer.setupFogColor(true);
    this.bipedHead.render(scale);
    gamerenderer.setupFogColor(false);
    GlStateManager.depthMask(true);
    GlStateManager.disableBlend();
    GlStateManager.enableAlphaTest();
  }
}*/
