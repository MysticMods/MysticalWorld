package epicsquid.mysticalworld.entity.render.layers;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GLX;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.entity.LavaCatEntity;
import epicsquid.mysticalworld.entity.model.LavaCatModel;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.RenderState;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.EnergyLayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexFormat;
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
      this.getEntityModel().copyModelAttributesTo(mainModel);
      IVertexBuilder ivertexbuilder = p_225628_2_.getBuffer(AdditionalRenderTypes.getFullbrightLayer(MAGMA_TEXTURE, 0, -f * 0.003f));
      mainModel.setRotationAngles(p_225628_4_, p_225628_5_, p_225628_6_, p_225628_8_, p_225628_9_, p_225628_10_);

      mainModel.render(p_225628_1_, ivertexbuilder, LightTexture.packLight(15, 15), OverlayTexture.NO_OVERLAY, 1f, 1f, 1f, 1f);
    } else {
      mainModel.setLivingAnimations(p_225628_4_, p_225628_5_, p_225628_6_, p_225628_7_);
      this.getEntityModel().copyModelAttributesTo(mainModel);
      IVertexBuilder ivertexbuilder = p_225628_2_.getBuffer(RenderType.getEntitySolid(OBSIDIAN_TEXTURE));
      mainModel.setRotationAngles(p_225628_4_, p_225628_5_, p_225628_6_, p_225628_8_, p_225628_9_, p_225628_10_);
      mainModel.render(p_225628_1_, ivertexbuilder, p_225628_3_, OverlayTexture.NO_OVERLAY, 0.5F, 0.5F, 0.5F, 1.0F);
    }
  }

  public static class AdditionalRenderTypes extends RenderType {
    public AdditionalRenderTypes(String p_i225992_1_, VertexFormat p_i225992_2_, int p_i225992_3_, int p_i225992_4_, boolean p_i225992_5_, boolean p_i225992_6_, Runnable p_i225992_7_, Runnable p_i225992_8_) {
      super(p_i225992_1_, p_i225992_2_, p_i225992_3_, p_i225992_4_, p_i225992_5_, p_i225992_6_, p_i225992_7_, p_i225992_8_);
    }

    public static RenderType getFullbrightLayer(ResourceLocation p_228636_0_, float p_228636_1_, float p_228636_2_) {
      return makeType("energy_swirl", DefaultVertexFormats.ENTITY, 7, 256, false, true, RenderType.State.getBuilder().texture(new TextureState(p_228636_0_, false, false)).texturing(new OffsetTexturingState(p_228636_1_, p_228636_2_)).fog(NO_FOG).diffuseLighting(DIFFUSE_LIGHTING_DISABLED).alpha(DEFAULT_ALPHA).cull(CULL_DISABLED).lightmap(LIGHTMAP_ENABLED).transparency(RenderState.NO_TRANSPARENCY).overlay(OVERLAY_DISABLED).build(false));
    }
  }

  private float getEnergySwirlX(float p_225634_1_) {
    return p_225634_1_ * 0.004F;
  }
}
