package epicsquid.mysticalworld.entity.model.heads;

import net.minecraft.client.renderer.entity.model.GenericHeadModel;
import net.minecraft.client.renderer.entity.model.RendererModel;

public class VillagerHeadModel extends GenericHeadModel {
  protected RendererModel villagerNose;
  protected RendererModel villagerHead;
  protected RendererModel unknown;
  protected RendererModel field_217152_f;

  public VillagerHeadModel(int textureWidth, int textureHeight, float scale) {
    this.textureWidth = textureWidth;
    this.textureHeight = textureHeight;
    this.villagerHead = (new RendererModel(this)).setTextureSize(textureWidth, textureHeight);
    this.villagerHead.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.villagerHead.setTextureOffset(0, 0).addBox(-4.0F, -10.0F, -4.0F, 8, 10, 8, scale);
    this.unknown = (new RendererModel(this)).setTextureSize(textureWidth, textureWidth);
    this.unknown.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.unknown.setTextureOffset(32, 0).addBox(-4.0F, -10.0F, -4.0F, 8, 10, 8, scale + 0.5F);
    this.villagerHead.addChild(this.unknown);
    this.field_217152_f = (new RendererModel(this)).setTextureSize(textureWidth, textureWidth);
    this.field_217152_f.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.field_217152_f.setTextureOffset(30, 47).addBox(-8.0F, -8.0F, -6.0F, 16, 16, 1, scale);
    this.field_217152_f.rotateAngleX = (-(float) Math.PI / 2F);
    this.unknown.addChild(this.field_217152_f);
    this.villagerNose = (new RendererModel(this)).setTextureSize(textureWidth, textureWidth);
    this.villagerNose.setRotationPoint(0.0F, -2.0F, 0.0F);
    this.villagerNose.setTextureOffset(24, 0).addBox(-1.0F, -1.0F, -6.0F, 2, 4, 2, scale);
    this.villagerHead.addChild(this.villagerNose);
  }

  @Override
  public void func_217104_a(float progress, float a, float b, float rotation, float c, float scale) {
    this.villagerHead.rotateAngleY = (float) (rotation * (Math.PI / 180));
    this.villagerHead.rotateAngleX = (float) (c * (Math.PI / 180));
    this.villagerHead.render(scale);
  }
}
