package epicsquid.mysticalworld.entity.model.heads;

import net.minecraft.client.renderer.entity.model.GenericHeadModel;
import net.minecraft.client.renderer.entity.model.RendererModel;

public class GenericHeadwearModel extends GenericHeadModel {
  public RendererModel bipedHeadwear;

  public GenericHeadwearModel(int offsetX, int offsetY, int textureWidth, int textureHeight) {
    super(offsetX, offsetY, textureWidth, textureHeight);
    this.bipedHeadwear = new RendererModel(this, 32, 0);
    this.bipedHeadwear.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.5F);
    this.bipedHeadwear.setRotationPoint(0.0F, 0.0F, 0.0F);
  }

  @Override
  public void func_217104_a(float progress, float a, float b, float rotation, float c, float scale) {
    super.func_217104_a(progress, a, b, rotation, c, scale);
    this.bipedHeadwear.rotateAngleX = this.field_217105_a.rotateAngleX;
    this.bipedHeadwear.rotateAngleY = this.field_217105_a.rotateAngleY;
    this.bipedHeadwear.rotateAngleZ = this.field_217105_a.rotateAngleZ;
    this.bipedHeadwear.rotationPointX = this.field_217105_a.rotationPointX;
    this.bipedHeadwear.rotationPointY = this.field_217105_a.rotationPointY;
    this.bipedHeadwear.rotationPointZ = this.field_217105_a.rotationPointZ;
    this.bipedHeadwear.render(scale);
  }
}
