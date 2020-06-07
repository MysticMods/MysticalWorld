package epicsquid.mysticalworld.entity.model.armor;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBeetleMask extends ModelBase {
  public ModelRenderer body;
  public ModelRenderer armLeft;
  public ModelRenderer legLeft;
  public ModelRenderer head;
  public ModelRenderer armRight;
  public ModelRenderer legRight;
  public ModelRenderer mask;
  public ModelRenderer horn;
  public ModelRenderer maskBottom;
  public ModelRenderer strap;
  public ModelRenderer horn2;

  public ModelBeetleMask() {
    this.textureWidth = 64;
    this.textureHeight = 96;
    this.armRight = new ModelRenderer(this, 40, 16);
    this.armRight.setRotationPoint(-5.0F, 2.0F, 0.0F);
    this.armRight.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
    this.horn = new ModelRenderer(this, 1, 76);
    this.horn.setRotationPoint(0.0F, -8.5F, -4.5F);
    this.horn.addBox(-1.0F, -7.0F, 0.0F, 2, 7, 2, 0.0F);
    this.setRotateAngle(horn, -0.3141592653589793F, 0.0F, 0.0F);
    this.strap = new ModelRenderer(this, 15, 82);
    this.strap.setRotationPoint(0.0F, -4.0F, -2.5F);
    this.strap.addBox(-4.5F, -1.0F, 0.0F, 9, 2, 7, 0.0F);
    this.body = new ModelRenderer(this, 16, 16);
    this.body.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.body.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F);
    this.maskBottom = new ModelRenderer(this, 22, 64);
    this.maskBottom.setRotationPoint(0.0F, 0.5F, -3.5F);
    this.maskBottom.addBox(-2.5F, 0.0F, -1.0F, 5, 4, 2, 0.0F);
    this.legLeft = new ModelRenderer(this, 16, 48);
    this.legLeft.setRotationPoint(2.0F, 12.0F, 0.0F);
    this.legLeft.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
    this.mask = new ModelRenderer(this, 0, 64);
    this.mask.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.mask.addBox(-4.5F, -8.5F, -4.5F, 9, 9, 2, 0.0F);
    this.armLeft = new ModelRenderer(this, 32, 48);
    this.armLeft.setRotationPoint(5.0F, 2.0F, 0.0F);
    this.armLeft.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
    this.horn2 = new ModelRenderer(this, 0, 85);
    this.horn2.setRotationPoint(0.0F, -6.0F, 2.0F);
    this.horn2.addBox(-1.0F, -1.0F, 0.0F, 2, 2, 3, 0.0F);
    this.head = new ModelRenderer(this, 0, 0);
    this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
    this.legRight = new ModelRenderer(this, 0, 16);
    this.legRight.setRotationPoint(-2.0F, 12.0F, 0.0F);
    this.legRight.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
    this.body.addChild(this.armRight);
    this.mask.addChild(this.horn);
    this.mask.addChild(this.strap);
    this.mask.addChild(this.maskBottom);
    this.body.addChild(this.legLeft);
    this.head.addChild(this.mask);
    this.body.addChild(this.armLeft);
    this.horn.addChild(this.horn2);
    this.body.addChild(this.head);
    this.body.addChild(this.legRight);
  }

  @Override
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    this.body.render(f5);
    this.body.render(f5);
  }

  public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.rotateAngleX = x;
    modelRenderer.rotateAngleY = y;
    modelRenderer.rotateAngleZ = z;
  }
}

