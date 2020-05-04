package epicsquid.mysticalworld.entity.model.heads;

import net.minecraft.client.renderer.entity.model.GenericHeadModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class IllagerHeadModel extends GenericHeadModel {
  protected final ModelRenderer head;
  private final ModelRenderer hat;
  private final ModelRenderer nose;

  public IllagerHeadModel(float scaleFactor, int textureWidthIn, int textureHeightIn) {
    this.head = (new ModelRenderer(this)).setTextureSize(textureWidthIn, textureHeightIn);
    this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.head.setTextureOffset(0, 0).addCuboid(-4.0F, -10.0F, -4.0F, 8, 10, 8, scaleFactor);
    this.hat = (new ModelRenderer(this, 32, 0)).setTextureSize(textureWidthIn, textureHeightIn);
    this.hat.addCuboid(-4.0F, -10.0F, -4.0F, 8, 12, 8, scaleFactor + 0.45F);
    this.head.addChild(this.hat);
    this.hat.showModel = false;
    this.nose = (new ModelRenderer(this)).setTextureSize(textureWidthIn, textureHeightIn);
    this.nose.setRotationPoint(0.0F, -2.0F, 0.0F);
    this.nose.setTextureOffset(24, 0).addCuboid(-1.0F, -1.0F, -6.0F, 2, 4, 2, scaleFactor);
    this.head.addChild(this.nose);
  }

  @Override
  public void render(float a, float yaw, float pitch) {
    super.render(a, yaw, pitch);
    this.head.rotateAngleY = (float) (yaw * (Math.PI / 180));
    this.head.rotateAngleX = (float) (pitch * (Math.PI / 180));
  }
}
