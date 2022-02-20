package mysticmods.mysticalworld.client.render;

/*public class DuckRenderer extends MobRenderer<DuckEntity, DuckModel> {
  private DuckRenderer(@Nonnull EntityRenderDispatcher renderManager, @Nonnull DuckModel m, float f) {
    super(renderManager, m, f);
  }

  public static class Factory implements IRenderFactory<DuckEntity> {

    @Override
    @Nonnull
    public DuckRenderer createRenderFor(@Nonnull EntityRenderDispatcher manager) {
      return new DuckRenderer(manager, ModelHolder.duckModel, 0.05f);
    }
  }

  @Override
  protected void scale(DuckEntity entity, PoseStack matrix, float partialTickTime) {
    // TODO: matrix.scale(0.45f, 0.45f, 0.45f);
  }

  @Override
  @Nonnull
  public ResourceLocation getTextureLocation(@Nonnull DuckEntity entity) {
    return new ResourceLocation(MysticalWorld.MODID + ":textures/entity/duck.png");
  }

  @Override
  protected float getBob(DuckEntity pLivingBase, float pPartialTicks) {
    if (pLivingBase.isInWater()) {
      return 0f;
    }
    float f = Mth.lerp(pPartialTicks, pLivingBase.oFlap, pLivingBase.flap);
    float f1 = Mth.lerp(pPartialTicks, pLivingBase.oFlapSpeed, pLivingBase.flapSpeed);
    return (Mth.sin(f) + 1.0F) * f1;
  }
}*/
