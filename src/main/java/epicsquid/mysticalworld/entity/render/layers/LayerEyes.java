package epicsquid.mysticalworld.entity.render.layers;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.entity.LavaCatEntity;
import epicsquid.mysticalworld.entity.model.LavaCatModel;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.AbstractEyesLayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LayerEyes extends AbstractEyesLayer<LavaCatEntity, LavaCatModel> {
  private static final RenderType LAVA_EYES_TEXTURE = RenderType.getEyes(new ResourceLocation(MysticalWorld.MODID, "textures/entity/magma_cat_eyes.png"));
  private static final RenderType OBSIDIAN_EYES_TEXTURE = RenderType.getEyes(new ResourceLocation(MysticalWorld.MODID, "textures/entity/magma_cat_obsidian_eyes.png"));

  public LayerEyes(IEntityRenderer<LavaCatEntity, LavaCatModel> entityRendererIn) {
    super(entityRendererIn);
  }

  @Override
  public RenderType getEyesTexture() {
    return null;
  }

  @Override
  public void render(MatrixStack p_225628_1_, IRenderTypeBuffer p_225628_2_, int p_225628_3_, LavaCatEntity p_225628_4_, float p_225628_5_, float p_225628_6_, float p_225628_7_, float p_225628_8_, float p_225628_9_, float p_225628_10_) {
    IVertexBuilder ivertexbuilder;
    if (p_225628_4_.getIsLava()) {
      ivertexbuilder = p_225628_2_.getBuffer(LAVA_EYES_TEXTURE);
    } else {
      ivertexbuilder = p_225628_2_.getBuffer(OBSIDIAN_EYES_TEXTURE);
    }
    this.getEntityModel().render(p_225628_1_, ivertexbuilder, 15728640, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);
  }
}

