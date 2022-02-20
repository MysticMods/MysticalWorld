package mysticmods.mysticalworld.client.render.layers;

/*
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

*/
