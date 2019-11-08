package epicsquid.mysticalworld.entity.render;

import com.mojang.blaze3d.platform.GLX;
import com.mojang.blaze3d.platform.GlStateManager;
import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.entity.EnderminiEntity;
import epicsquid.mysticalworld.entity.model.ModelHolder;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.EndermanEyesLayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EndermanModel;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import java.util.Random;

public class EnderminiRenderer extends MobRenderer<EnderminiEntity, EndermanModel<EnderminiEntity>> {
  private static final ResourceLocation ENDERMINI_TEXTURES = new ResourceLocation(MysticalWorld.MODID, "textures/entity/endermini.png");
  private final Random rnd = new Random();

  public EnderminiRenderer(EntityRendererManager renderManagerIn, EndermanModel model, float size) {
    super(renderManagerIn, model, size);
    this.addLayer(new EndermanEyesLayer<>(this));
    this.addLayer(new HeldBlockLayer(this));
  }

  @Override
  protected void renderModel(EnderminiEntity entitylivingbaseIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
    GlStateManager.pushMatrix();
    GlStateManager.scaled(0.4, 0.4, 0.4);
    GlStateManager.translated(0, 2.2, 0);
    super.renderModel(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
    GlStateManager.popMatrix();
  }

  @Override
  public void doRender(EnderminiEntity entity, double x, double y, double z, float entityYaw, float partialTicks) {
    BlockState blockstate = entity.getHeldBlockState();
    EndermanModel<EnderminiEntity> endermanmodel = this.getEntityModel();
    endermanmodel.isCarrying = blockstate != null;
    endermanmodel.isAttacking = entity.isScreaming();
    if (entity.isScreaming()) {
      double d0 = 0.02D;
      x += this.rnd.nextGaussian() * 0.02D;
      z += this.rnd.nextGaussian() * 0.02D;
    }

    super.doRender(entity, x, y, z, entityYaw, partialTicks);
  }

  @Override
  protected ResourceLocation getEntityTexture(EnderminiEntity entity) {
    return ENDERMINI_TEXTURES;
  }

  @Override
  public EndermanModel<EnderminiEntity> getEntityModel() {
    return ModelHolder.enderminiModel;
  }

  public static class HeldBlockLayer extends LayerRenderer<EnderminiEntity, EndermanModel<EnderminiEntity>> {
    public HeldBlockLayer(IEntityRenderer<EnderminiEntity, EndermanModel<EnderminiEntity>> p_i50949_1_) {
      super(p_i50949_1_);
    }

    @Override
    public void render(EnderminiEntity entityIn, float p_212842_2_, float p_212842_3_, float p_212842_4_, float p_212842_5_, float p_212842_6_, float p_212842_7_, float p_212842_8_) {
      BlockState blockstate = entityIn.getHeldBlockState();
      if (blockstate != null) {
        GlStateManager.enableRescaleNormal();
        GlStateManager.pushMatrix();
        GlStateManager.translatef(0.0F, 0.6875F, -0.75F);
        GlStateManager.rotatef(20.0F, 1.0F, 0.0F, 0.0F);
        GlStateManager.rotatef(45.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.translatef(0.25F, 0.1875F, 0.25F);
        float f = 0.5F;
        GlStateManager.scalef(-0.5F, -0.5F, 0.5F);
        int i = entityIn.getBrightnessForRender();
        int j = i % 65536;
        int k = i / 65536;
        GLX.glMultiTexCoord2f(GLX.GL_TEXTURE1, (float) j, (float) k);
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.bindTexture(AtlasTexture.LOCATION_BLOCKS_TEXTURE);
        Minecraft.getInstance().getBlockRendererDispatcher().renderBlockBrightness(blockstate, 1.0F);
        GlStateManager.popMatrix();
        GlStateManager.disableRescaleNormal();
      }
    }

    @Override
    public boolean shouldCombineTextures() {
      return false;
    }
  }

  public static class Factory implements IRenderFactory<EnderminiEntity> {
    @Override
    public EntityRenderer<? super EnderminiEntity> createRenderFor(EntityRendererManager manager) {
      return new EnderminiRenderer(manager, ModelHolder.enderminiModel, 0.35f);
    }
  }
}
