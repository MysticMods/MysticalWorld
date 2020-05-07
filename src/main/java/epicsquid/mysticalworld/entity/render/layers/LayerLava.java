package epicsquid.mysticalworld.entity.render.layers;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GLX;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.entity.LavaCatEntity;
import epicsquid.mysticalworld.entity.model.LavaCatModel;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.EnergyLayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
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
    this.mainModel = this.renderer.getEntityModel();
  }

  public void render(MatrixStack p_225628_1_, IRenderTypeBuffer p_225628_2_, int p_225628_3_, LavaCatEntity p_225628_4_, float p_225628_5_, float p_225628_6_, float p_225628_7_, float p_225628_8_, float p_225628_9_, float p_225628_10_) {
    if (p_225628_4_.getIsLava()) {
      float f = (float) p_225628_4_.ticksExisted + p_225628_7_;
      mainModel.setLivingAnimations(p_225628_4_, p_225628_5_, p_225628_6_, p_225628_7_);
      this.getEntityModel().setModelAttributes(mainModel);
      IVertexBuilder ivertexbuilder = p_225628_2_.getBuffer(RenderType.getEnergySwirl(MAGMA_TEXTURE, this.getEnergySwirlX(f), f * 0.01F));
      mainModel.setAngles(p_225628_4_, p_225628_5_, p_225628_6_, p_225628_8_, p_225628_9_, p_225628_10_);
      mainModel.render(p_225628_1_, ivertexbuilder, p_225628_3_, OverlayTexture.DEFAULT_UV, 0.5F, 0.5F, 0.5F, 1.0F);
    } else {
      mainModel.setLivingAnimations(p_225628_4_, p_225628_5_, p_225628_6_, p_225628_7_);
      this.getEntityModel().setModelAttributes(mainModel);
      IVertexBuilder ivertexbuilder = p_225628_2_.getBuffer(RenderType.getEntitySolid(OBSIDIAN_TEXTURE));
      mainModel.setAngles(p_225628_4_, p_225628_5_, p_225628_6_, p_225628_8_, p_225628_9_, p_225628_10_);
      mainModel.render(p_225628_1_, ivertexbuilder, p_225628_3_, OverlayTexture.DEFAULT_UV, 0.5F, 0.5F, 0.5F, 1.0F);
    }
  }

  private float getEnergySwirlX(float p_225634_1_) {
    return p_225634_1_ * 0.01F;
  }
}
