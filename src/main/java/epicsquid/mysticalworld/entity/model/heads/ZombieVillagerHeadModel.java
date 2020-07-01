package epicsquid.mysticalworld.entity.model.heads;

import net.minecraft.client.renderer.entity.model.GenericHeadModel;
import net.minecraft.client.renderer.entity.model.RendererModel;

public class ZombieVillagerHeadModel extends GenericHeadModel {
  private RendererModel bipedHead;
  private RendererModel bipedHeadwear;
  private RendererModel hatStuff;

  public ZombieVillagerHeadModel() {
    super(0, 0, 64, 64);
    this.bipedHead = new RendererModel(this, 0, 0);
    this.bipedHead.setTextureOffset(0, 0).addBox(-4.0F, -10.0F, -4.0F, 8, 10, 8, 0);
    this.bipedHead.setTextureOffset(24, 0).addBox(-1.0F, -3.0F, -6.0F, 2, 4, 2, 0);
    this.bipedHeadwear = new RendererModel(this, 32, 0);
    this.bipedHeadwear.addBox(-4.0F, -10.0F, -4.0F, 8, 10, 8, 0.5F);
    this.hatStuff = new RendererModel(this);
    this.hatStuff.setTextureOffset(30, 47).addBox(-8.0F, -8.0F, -6.0F, 16, 16, 1, 0);
    this.hatStuff.rotateAngleX = -1.5707964F;
    this.bipedHeadwear.addChild(this.hatStuff);
  }

  @Override
  public void func_217104_a(float progress, float a, float b, float rotation, float c, float scale) {
    this.bipedHead.rotateAngleY = (float) (rotation * (Math.PI / 180));
    this.bipedHead.rotateAngleX = (float) (c * (Math.PI / 180));
    this.bipedHeadwear.rotateAngleY = (float) (rotation * (Math.PI / 180));
    this.bipedHeadwear.rotateAngleX = (float) (c * (Math.PI / 180));
    this.bipedHead.render(scale);
    this.bipedHeadwear.render(scale);
  }
}
