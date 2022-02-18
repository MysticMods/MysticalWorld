package mysticmods.mysticalworld.client.render.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import mysticmods.mysticalworld.MysticalWorld;
import mysticmods.mysticalworld.client.model.LavaCatModel;
import mysticmods.mysticalworld.entity.LavaCatEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;

@OnlyIn(Dist.CLIENT)
public class LayerEyes extends EyesLayer<LavaCatEntity, LavaCatModel> {
  private static final RenderType LAVA_EYES_TEXTURE = RenderType.entityCutout(new ResourceLocation(MysticalWorld.MODID, "textures/entity/magma_cat_eyes.png"));
  private static final RenderType OBSIDIAN_EYES_TEXTURE = RenderType.entityCutout(new ResourceLocation(MysticalWorld.MODID, "textures/entity/magma_cat_obsidian_eyes.png"));

  public LayerEyes(RenderLayerParent<LavaCatEntity, LavaCatModel> entityRendererIn) {
    super(entityRendererIn);
  }

  @Override
  public void render(@Nonnull PoseStack pMatrixStack, @Nonnull MultiBufferSource pBuffer, int pPackedLight, LavaCatEntity pLivingEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTicks, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
    VertexConsumer ivertexbuilder;
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

