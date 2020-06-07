package epicsquid.mysticalworld.entity.model.armor;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelDeerHat extends ModelBase {
  public ModelRenderer field_178723_h;
  public ModelRenderer field_178721_j;
  public ModelRenderer field_78116_c;
  public ModelRenderer field_78115_e;
  public ModelRenderer field_178724_i;
  public ModelRenderer field_178722_k;
  public ModelRenderer hat;
  public ModelRenderer horn1;
  public ModelRenderer horn1_1;
  public ModelRenderer strap;
  public ModelRenderer horn3;
  public ModelRenderer horn6;
  public ModelRenderer horn8;
  public ModelRenderer horn3_1;
  public ModelRenderer horn6_1;
  public ModelRenderer horn8_1;

  public ModelDeerHat() {
    this.textureWidth = 128;
    this.textureHeight = 64;
    this.horn1_1 = new ModelRenderer(this, 66, 32);
    this.horn1_1.setRotationPoint(-2.0F, -9.0F, 0.0F);
    this.horn1_1.addBox(-0.5F, -5.0F, -0.5F, 1, 5, 1, 0.0F);
    this.setRotateAngle(horn1_1, 0.0F, 0.0F, -0.08726646259971647F);
    this.horn8_1 = new ModelRenderer(this, 70, 32);
    this.horn8_1.setRotationPoint(0.0F, -2.0F, 0.0F);
    this.horn8_1.addBox(-0.5F, -3.0F, -0.5F, 1, 3, 1, 0.0F);
    this.setRotateAngle(horn8_1, 0.0F, -0.17453292519943295F, -0.7853981633974483F);
    this.field_78116_c = new ModelRenderer(this, 0, 0);
    this.field_78116_c.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.field_78116_c.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
    this.horn1 = new ModelRenderer(this, 66, 32);
    this.horn1.setRotationPoint(2.0F, -9.0F, 0.0F);
    this.horn1.addBox(-0.5F, -5.0F, -0.5F, 1, 5, 1, 0.0F);
    this.setRotateAngle(horn1, 0.0F, 0.0F, 0.08726646259971647F);
    this.horn8 = new ModelRenderer(this, 70, 32);
    this.horn8.setRotationPoint(0.0F, -2.0F, 0.0F);
    this.horn8.addBox(-0.5F, -3.0F, -0.5F, 1, 3, 1, 0.0F);
    this.setRotateAngle(horn8, 0.0F, 0.17453292519943295F, 0.7853981633974483F);
    this.horn6_1 = new ModelRenderer(this, 66, 32);
    this.horn6_1.setRotationPoint(0.3F, -3.2F, -0.2F);
    this.horn6_1.addBox(-0.5F, -5.0F, -0.5F, 1, 5, 1, 0.0F);
    this.setRotateAngle(horn6_1, 0.0F, 0.08726646259971647F, 1.0471975511965976F);
    this.horn3 = new ModelRenderer(this, 66, 32);
    this.horn3.setRotationPoint(0.15F, -2.5F, 0.0F);
    this.horn3.addBox(-0.5F, -5.0F, -0.5F, 1, 5, 1, 0.0F);
    this.setRotateAngle(horn3, 0.0F, 0.08726646259971647F, 1.0471975511965976F);
    this.field_178724_i = new ModelRenderer(this, 32, 48);
    this.field_178724_i.setRotationPoint(5.0F, 2.0F, 0.0F);
    this.field_178724_i.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
    this.setRotateAngle(field_178724_i, 0.0F, 0.0F, -0.10000000149011613F);
    this.strap = new ModelRenderer(this, 64, 10);
    this.strap.setRotationPoint(0.0F, -8.1F, -0.5F);
    this.strap.addBox(-4.5F, 0.0F, -0.5F, 9, 9, 1, 0.0F);
    this.setRotateAngle(strap, -0.25132741228718347F, 0.0F, 0.0F);
    this.horn3_1 = new ModelRenderer(this, 66, 32);
    this.horn3_1.setRotationPoint(-0.15F, -2.5F, 0.0F);
    this.horn3_1.addBox(-0.5F, -5.0F, -0.5F, 1, 5, 1, 0.0F);
    this.setRotateAngle(horn3_1, 0.0F, -0.08726646259971647F, -1.0471975511965976F);
    this.field_78115_e = new ModelRenderer(this, 16, 16);
    this.field_78115_e.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.field_78115_e.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F);
    this.field_178723_h = new ModelRenderer(this, 40, 16);
    this.field_178723_h.setRotationPoint(-5.0F, 2.0F, 0.0F);
    this.field_178723_h.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
    this.setRotateAngle(field_178723_h, 0.0F, 0.0F, 0.10000000149011613F);
    this.hat = new ModelRenderer(this, 64, 0);
    this.hat.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.hat.addBox(-3.0F, -10.0F, -3.0F, 6, 3, 6, 0.0F);
    this.field_178721_j = new ModelRenderer(this, 0, 16);
    this.field_178721_j.setRotationPoint(-1.899999976158142F, 12.0F, 0.10000000149011612F);
    this.field_178721_j.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
    this.field_178722_k = new ModelRenderer(this, 16, 48);
    this.field_178722_k.setRotationPoint(1.899999976158142F, 12.0F, 0.10000000149011612F);
    this.field_178722_k.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
    this.horn6 = new ModelRenderer(this, 66, 32);
    this.horn6.setRotationPoint(-0.3F, -3.2F, -0.2F);
    this.horn6.addBox(-0.5F, -5.0F, -0.5F, 1, 5, 1, 0.0F);
    this.setRotateAngle(horn6, 0.0F, -0.08726646259971647F, -1.0471975511965976F);
    this.hat.addChild(this.horn1_1);
    this.horn6_1.addChild(this.horn8_1);
    this.hat.addChild(this.horn1);
    this.horn6.addChild(this.horn8);
    this.horn3_1.addChild(this.horn6_1);
    this.horn1.addChild(this.horn3);
    this.hat.addChild(this.strap);
    this.horn1_1.addChild(this.horn3_1);
    this.horn3.addChild(this.horn6);
  }

  @Override
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    this.field_78116_c.render(f5);
    this.field_178724_i.render(f5);
    this.field_78115_e.render(f5);
    this.field_178723_h.render(f5);
    this.hat.render(f5);
    this.field_178721_j.render(f5);
    this.field_178722_k.render(f5);
    this.field_78116_c.render(f5);
    this.field_178724_i.render(f5);
    this.field_78115_e.render(f5);
    this.field_178723_h.render(f5);
    this.hat.render(f5);
    this.field_178721_j.render(f5);
    this.field_178722_k.render(f5);
  }

  public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.rotateAngleX = x;
    modelRenderer.rotateAngleY = y;
    modelRenderer.rotateAngleZ = z;
  }
}
