package mysticmods.mysticalworld.client.render.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import mysticmods.mysticalworld.MysticalWorld;
import mysticmods.mysticalworld.client.model.LavaCatModel;
import mysticmods.mysticalworld.client.model.ModelHolder;
import mysticmods.mysticalworld.entity.LavaCatEntity;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class LayerLava extends RenderLayer<LavaCatEntity, LavaCatModel<LavaCatEntity>> {
  private static final ResourceLocation MAGMA_TEXTURE = new ResourceLocation(MysticalWorld.MODID, "textures/entity/magma_cat_magma.png");
  private static final ResourceLocation OBSIDIAN_TEXTURE = new ResourceLocation(MysticalWorld.MODID, "textures/entity/magma_cat_obsidian.png");

  private final LavaCatModel<LavaCatEntity> mainModel;

  public LayerLava(RenderLayerParent<LavaCatEntity, LavaCatModel<LavaCatEntity>> entityRendererIn, EntityModelSet modelSet) {
    super(entityRendererIn);
    this.mainModel = new LavaCatModel<>(modelSet.bakeLayer(ModelHolder.LAVA_CAT));
  }

  @Override
  public void render(PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight, LavaCatEntity pLivingEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTicks, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
    if (pLivingEntity.getIsLava()) {
      float f = (float) pLivingEntity.tickCount + pPartialTicks;
      mainModel.prepareMobModel(pLivingEntity, pLimbSwing, pLimbSwingAmount, pPartialTicks);
      this.getParentModel().copyPropertiesTo(mainModel);
      VertexConsumer vertexConsumer = pBuffer.getBuffer(AdditionalRenderTypes.getFullbrightLayer(MAGMA_TEXTURE, 0, -f * 0.003f));
      mainModel.setupAnim(pLivingEntity, pLimbSwing, pLimbSwingAmount, pAgeInTicks, pNetHeadYaw, pHeadPitch);
      mainModel.renderToBuffer(pMatrixStack, vertexConsumer, 15728640, OverlayTexture.NO_OVERLAY, 1f, 1f, 1f, 1f);
    } else {
      mainModel.prepareMobModel(pLivingEntity, pLimbSwing, pLimbSwingAmount, pPartialTicks);
      this.getParentModel().copyPropertiesTo(mainModel);
      VertexConsumer vertexConsumer = pBuffer.getBuffer(RenderType.entitySolid(OBSIDIAN_TEXTURE));
      mainModel.setupAnim(pLivingEntity, pLimbSwing, pLimbSwingAmount, pAgeInTicks, pNetHeadYaw, pHeadPitch);
      mainModel.renderToBuffer(pMatrixStack, vertexConsumer, pPackedLight, OverlayTexture.NO_OVERLAY, 0.5F, 0.5F, 0.5F, 1.0F);
    }
  }

  private float xOffset(float p_225634_1_) {
    return p_225634_1_ * 0.004F;
  }
}