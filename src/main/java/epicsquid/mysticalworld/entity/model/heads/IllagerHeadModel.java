package epicsquid.mysticalworld.entity.model.heads;

import net.minecraft.client.renderer.entity.model.GenericHeadModel;
import net.minecraft.client.renderer.entity.model.RendererModel;

public class IllagerHeadModel extends GenericHeadModel {
  protected final RendererModel head;
  private final RendererModel hat;
  private final RendererModel nose;

  public IllagerHeadModel(float scaleFactor, int textureWidthIn, int textureHeightIn) {
    this.head = (new RendererModel(this)).setTextureSize(textureWidthIn, textureHeightIn);
    this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.head.setTextureOffset(0, 0).addBox(-4.0F, -10.0F, -4.0F, 8, 10, 8, scaleFactor);
    this.hat = (new RendererModel(this, 32, 0)).setTextureSize(textureWidthIn, textureHeightIn);
    this.hat.addBox(-4.0F, -10.0F, -4.0F, 8, 12, 8, scaleFactor + 0.45F);
    this.head.addChild(this.hat);
    this.hat.showModel = false;
    this.nose = (new RendererModel(this)).setTextureSize(textureWidthIn, textureHeightIn);
    this.nose.setRotationPoint(0.0F, -2.0F, 0.0F);
    this.nose.setTextureOffset(24, 0).addBox(-1.0F, -1.0F, -6.0F, 2, 4, 2, scaleFactor);
    this.head.addChild(this.nose);
  }

  @Override
  public void func_217104_a(float progress, float a, float b, float rotation, float c, float scale) {
    this.head.rotateAngleY = (float) (rotation * (Math.PI / 180));
    this.head.rotateAngleX = (float) (c * (Math.PI / 180));
    this.head.render(scale);
  }
}
