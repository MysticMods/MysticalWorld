package mysticmods.mysticalworld.entity.render.layers;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import mysticmods.mysticalworld.MysticalWorld;
import mysticmods.mysticalworld.entity.LavaCatEntity;
import mysticmods.mysticalworld.entity.model.LavaCatModel;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.AbstractEyesLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;

@OnlyIn(Dist.CLIENT)
public class LayerEyes extends AbstractEyesLayer<LavaCatEntity, LavaCatModel> {
  private static final RenderType LAVA_EYES_TEXTURE = RenderType.entityCutout(new ResourceLocation(MysticalWorld.MODID, "textures/entity/magma_cat_eyes.png"));
  private static final RenderType OBSIDIAN_EYES_TEXTURE = RenderType.entityCutout(new ResourceLocation(MysticalWorld.MODID, "textures/entity/magma_cat_obsidian_eyes.png"));

  public LayerEyes(IEntityRenderer<LavaCatEntity, LavaCatModel> entityRendererIn) {
    super(entityRendererIn);
  }

  @Override
  public void render(@Nonnull MatrixStack pMatrixStack, @Nonnull IRenderTypeBuffer pBuffer, int pPackedLight, LavaCatEntity pLivingEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTicks, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
    IVertexBuilder ivertexbuilder;
    if (pLivingEntity.getIsLava()) {
      ivertexbuilder = pBuffer.getBuffer(LAVA_EYES_TEXTURE);
    } else {
      ivertexbuilder = pBuffer.getBuffer(OBSIDIAN_EYES_TEXTURE);
    }
    this.getParentModel().renderToBuffer(pMatrixStack, ivertexbuilder, 15728640, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
  }

  @Override
  public RenderType renderType() {
    return null;
  }
}

