package epicsquid.mysticalworld.entity.model.heads;

import net.minecraft.client.renderer.entity.model.GenericHeadModel;
import net.minecraft.client.renderer.entity.model.RendererModel;

public class WitchHeadModel extends GenericHeadModel {
  protected RendererModel villagerNose;
  protected RendererModel villagerHead;
  protected RendererModel unknown;

  public WitchHeadModel() {
    this.textureWidth = 64;
    this.textureHeight = 128;
    this.villagerHead = (new RendererModel(this)).setTextureSize(textureWidth, textureHeight);
    this.villagerHead.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.villagerHead.setTextureOffset(0, 0).addBox(-4.0F, -10.0F, -4.0F, 8, 10, 8, 0);
    this.villagerNose = (new RendererModel(this)).setTextureSize(textureWidth, textureWidth);
    this.villagerNose.setRotationPoint(0.0F, -2.0F, 0.0F);
    this.villagerNose.setTextureOffset(24, 0).addBox(-1.0F, -1.0F, -6.0F, 2, 4, 2, 0);
    this.villagerHead.addChild(this.villagerNose);
    RendererModel mole = (new RendererModel(this)).setTextureSize(64, 128);
    mole.setRotationPoint(0.0F, -2.0F, 0.0F);
    mole.setTextureOffset(0, 0).addBox(0.0F, 3.0F, -6.75F, 1, 1, 1, -0.25F);
    this.villagerNose.addChild(mole);
    this.unknown = (new RendererModel(this)).setTextureSize(64, 128);
    this.unknown.setRotationPoint(-5.0F, -10.03125F, -5.0F);
    this.unknown.setTextureOffset(0, 64).addBox(0.0F, 0.0F, 0.0F, 10, 2, 10);
    this.villagerHead.addChild(this.unknown);
    RendererModel renderermodel = (new RendererModel(this)).setTextureSize(64, 128);
    renderermodel.setRotationPoint(1.75F, -4.0F, 2.0F);
    renderermodel.setTextureOffset(0, 76).addBox(0.0F, 0.0F, 0.0F, 7, 4, 7);
    renderermodel.rotateAngleX = -0.05235988F;
    renderermodel.rotateAngleZ = 0.02617994F;
    this.unknown.addChild(renderermodel);
    RendererModel renderermodel1 = (new RendererModel(this)).setTextureSize(64, 128);
    renderermodel1.setRotationPoint(1.75F, -4.0F, 2.0F);
    renderermodel1.setTextureOffset(0, 87).addBox(0.0F, 0.0F, 0.0F, 4, 4, 4);
    renderermodel1.rotateAngleX = -0.10471976F;
    renderermodel1.rotateAngleZ = 0.05235988F;
    renderermodel.addChild(renderermodel1);
    RendererModel renderermodel2 = (new RendererModel(this)).setTextureSize(64, 128);
    renderermodel2.setRotationPoint(1.75F, -2.0F, 2.0F);
    renderermodel2.setTextureOffset(0, 95).addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.25F);
    renderermodel2.rotateAngleX = -0.20943952F;
    renderermodel2.rotateAngleZ = 0.10471976F;
    renderermodel1.addChild(renderermodel2);
  }

  @Override
  public void func_217104_a(float progress, float a, float b, float rotation, float c, float scale) {
    this.villagerHead.rotateAngleY = (float) (rotation * (Math.PI / 180));
    this.villagerHead.rotateAngleX = (float) (c * (Math.PI / 180));
    this.villagerHead.render(scale);
  }
}
