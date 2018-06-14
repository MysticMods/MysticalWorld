package epicsquid.mysticalworld.entity.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelFox - Elucent
 * Created using Tabula 5.1.0
 */
public class ModelFox extends ModelBase {
  public ModelRenderer body1;
  public ModelRenderer frontL;
  public ModelRenderer frontR;
  public ModelRenderer body2;
  public ModelRenderer neck;
  public ModelRenderer backL;
  public ModelRenderer backR;
  public ModelRenderer tail1;
  public ModelRenderer tail2;
  public ModelRenderer tail3;
  public ModelRenderer tail4;
  public ModelRenderer head;
  public ModelRenderer snout;
  public ModelRenderer earR;
  public ModelRenderer earL;

  public ModelFox() {
    this.textureWidth = 32;
    this.textureHeight = 32;
    this.backL = new ModelRenderer(this, 0, 16);
    this.backL.mirror = true;
    this.backL.setRotationPoint(1.0F, 4.0F, 1.5F);
    this.backL.addBox(-1.0F, 0.0F, -1.0F, 2, 6, 2, 0.0F);
    this.tail4 = new ModelRenderer(this, 8, 18);
    this.tail4.setRotationPoint(0.0F, 3.5F, 0.0F);
    this.tail4.addBox(-1.0F, 0.0F, -1.0F, 2, 2, 2, 0.0F);
    this.setRotateAngle(tail4, 0.39269908169872414F, 0.0F, 0.0F);
    this.body1 = new ModelRenderer(this, 12, 8);
    this.body1.setRotationPoint(0.0F, 15.5F, -2.0F);
    this.body1.addBox(-2.5F, 0.0F, 0.0F, 5, 4, 5, 0.0F);
    this.backR = new ModelRenderer(this, 0, 16);
    this.backR.setRotationPoint(-1.0F, 4.0F, 1.5F);
    this.backR.addBox(-1.0F, 0.0F, -1.0F, 2, 6, 2, 0.0F);
    this.neck = new ModelRenderer(this, 16, 0);
    this.neck.setRotationPoint(0.0F, 1.5F, 2.0F);
    this.neck.addBox(-2.0F, -2.0F, -4.0F, 4, 4, 4, 0.0F);
    this.setRotateAngle(neck, -0.2617993877991494F, 0.0F, 0.0F);
    this.earR = new ModelRenderer(this, 0, 12);
    this.earR.setRotationPoint(-1.5F, -1.0F, -1.0F);
    this.earR.addBox(-1.0F, -3.0F, -0.5F, 2, 3, 1, 0.0F);
    this.setRotateAngle(earR, 0.0F, 0.2617993877991494F, -0.5235987755982988F);
    this.tail3 = new ModelRenderer(this, 0, 24);
    this.tail3.setRotationPoint(0.0F, 3.0F, 0.0F);
    this.tail3.addBox(-2.0F, 0.0F, -2.0F, 4, 4, 4, 0.0F);
    this.snout = new ModelRenderer(this, 0, 0);
    this.snout.setRotationPoint(0.0F, 0.0F, -3.0F);
    this.snout.addBox(-1.5F, 0.0F, -3.0F, 3, 2, 3, 0.0F);
    this.setRotateAngle(snout, 0.17453292519943295F, 0.0F, 0.0F);
    this.head = new ModelRenderer(this, 0, 5);
    this.head.setRotationPoint(0.0F, 0.0F, -4.0F);
    this.head.addBox(-2.5F, -2.0F, -3.0F, 5, 4, 3, 0.0F);
    this.setRotateAngle(head, 0.2617993877991494F, 0.0F, 0.0F);
    this.tail1 = new ModelRenderer(this, 12, 0);
    this.tail1.setRotationPoint(0.0F, 1.0F, 2.5F);
    this.tail1.addBox(-1.0F, 0.0F, -1.0F, 2, 2, 2, 0.0F);
    this.setRotateAngle(tail1, 1.1780972450961724F, 0.0F, 0.0F);
    this.earL = new ModelRenderer(this, 0, 12);
    this.earL.mirror = true;
    this.earL.setRotationPoint(1.5F, -1.0F, -1.0F);
    this.earL.addBox(-1.0F, -3.0F, -0.5F, 2, 3, 1, 0.0F);
    this.setRotateAngle(earL, 0.0F, -0.2617993877991494F, 0.5235987755982988F);
    this.frontL = new ModelRenderer(this, 0, 16);
    this.frontL.mirror = true;
    this.frontL.setRotationPoint(1.0F, 4.0F, 1.0F);
    this.frontL.addBox(-1.0F, 0.0F, -1.0F, 2, 6, 2, 0.0F);
    this.body2 = new ModelRenderer(this, 16, 17);
    this.body2.setRotationPoint(0.0F, 0.0F, 5.0F);
    this.body2.addBox(-2.0F, 0.0F, 0.0F, 4, 4, 3, 0.0F);
    this.frontR = new ModelRenderer(this, 0, 16);
    this.frontR.setRotationPoint(-1.0F, 4.0F, 1.0F);
    this.frontR.addBox(-1.0F, 0.0F, -1.0F, 2, 6, 2, 0.0F);
    this.tail2 = new ModelRenderer(this, 16, 24);
    this.tail2.setRotationPoint(0.0F, 1.5F, 0.0F);
    this.tail2.addBox(-1.5F, 0.0F, -1.5F, 3, 3, 3, 0.0F);
    this.setRotateAngle(tail2, -0.39269908169872414F, 0.0F, 0.0F);
    this.body2.addChild(this.backL);
    this.tail3.addChild(this.tail4);
    this.body2.addChild(this.backR);
    this.body1.addChild(this.neck);
    this.head.addChild(this.earR);
    this.tail2.addChild(this.tail3);
    this.head.addChild(this.snout);
    this.neck.addChild(this.head);
    this.body2.addChild(this.tail1);
    this.head.addChild(this.earL);
    this.body1.addChild(this.frontL);
    this.body1.addChild(this.body2);
    this.body1.addChild(this.frontR);
    this.tail1.addChild(this.tail2);
  }

  public float getBobble(float deg, float ageInTicks) {
    return (float) Math.sin(ageInTicks * 0.03125f * (Math.PI * 2.0f) + Math.toRadians(deg));
  }

  @Override
  public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
    float sin = (float) Math.sin(ageInTicks * 0.125f * (Math.PI * 2.0f));
    float cos = (float) Math.cos(ageInTicks * 0.0625f * (Math.PI * 2.0f));
    this.earL.rotateAngleZ = 0.5235987755982988F + getBobble(60, ageInTicks) * 0.0981747703F;
    this.earR.rotateAngleZ = -0.5235987755982988F - getBobble(130, ageInTicks) * 0.0981747703F;
    this.backL.rotateAngleX = limbSwingAmount * sin;
    this.frontR.rotateAngleX = limbSwingAmount * sin;
    this.backR.rotateAngleX = -limbSwingAmount * sin;
    this.frontL.rotateAngleX = -limbSwingAmount * sin;
    this.head.rotateAngleX = headPitch * 0.017453292F;
    this.head.rotateAngleY = netHeadYaw * 0.017453292F;
    this.tail1.rotateAngleX = 1.1780972450961724F + limbSwingAmount;
    this.tail1.rotateAngleZ = limbSwingAmount * 0.375f * cos + 0.0872664626F * getBobble(45, ageInTicks);
    this.tail2.rotateAngleZ = limbSwingAmount * 0.375f * cos + 0.0872664626F * getBobble(90, ageInTicks);
    this.tail3.rotateAngleZ = limbSwingAmount * 0.375f * cos + 0.0872664626F * getBobble(135, ageInTicks);
    this.tail4.rotateAngleZ = limbSwingAmount * 0.375f * cos + 0.0872664626F * getBobble(180, ageInTicks);
    this.body1.render(scaleFactor);
  }

  /**
   * This is a helper function from Tabula to set the rotation of model parts
   */
  public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.rotateAngleX = x;
    modelRenderer.rotateAngleY = y;
    modelRenderer.rotateAngleZ = z;
  }
}
