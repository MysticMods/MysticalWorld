package epicsquid.mysticalworld.entity.model;

import com.google.common.collect.ImmutableSet;
import epicsquid.mysticalworld.entity.SpiritBeetleEntity;
import epicsquid.mysticalworld.entity.render.layers.AdditionalRenderTypes;
import net.minecraft.client.renderer.model.ModelRenderer;

import javax.annotation.Nonnull;

public class SpiritBeetleModel extends GlowingModel<SpiritBeetleEntity> {
  private ModelRenderer body;
  private ModelRenderer wingL;
  private ModelRenderer wingR;
  private ModelRenderer head;
  private ModelRenderer legL1;
  private ModelRenderer legL2;
  private ModelRenderer legL3;
  private ModelRenderer legR1;
  private ModelRenderer legR2;
  private ModelRenderer legR3;
  private ModelRenderer antennaR1;
  private ModelRenderer antennaL1;
  private ModelRenderer antennaR2;
  private ModelRenderer antennaR2_1;

  public SpiritBeetleModel() {
    super(AdditionalRenderTypes::getSpiritEntity, true, 5.0f, 2.0f, 2.0F, 2.0F, 24.0F);
    this.textureWidth = 32;
    this.textureHeight = 32;
    this.legL2 = new ModelRenderer(this, 18, 6);
    this.legL2.setRotationPoint(2.0F, 1.0F, 3.5F);
    this.legL2.addBox(-0.5F, 0.0F, -0.5F, 1, 7, 1, 0.0F);
    this.setRotateAngle(legL2, 0.0F, 0.0F, -0.2617993877991494F);
    this.antennaL1 = new ModelRenderer(this, 24, 6);
    this.antennaL1.setRotationPoint(1.0F, 0.0F, -0.5F);
    this.antennaL1.addBox(-0.5F, -5.0F, -0.5F, 1, 5, 1, 0.0F);
    this.setRotateAngle(antennaL1, 0.1308996938995747F, 0.0F, 0.2617993877991494F);
    this.wingL = new ModelRenderer(this, 0, 0);
    this.wingL.setRotationPoint(1.5F, -0.5F, 1.0F);
    this.wingL.addBox(-2.5F, 0.0F, -1.0F, 5, 8, 3, 0.0F);
    this.setRotateAngle(wingL, 1.7453292519943295F, 0.17453292519943295F, 0.2617993877991494F);
    this.wingR = new ModelRenderer(this, 0, 0);
    this.wingR.mirror = true;
    this.wingR.setRotationPoint(-1.5F, -0.5F, 1.0F);
    this.wingR.addBox(-2.5F, 0.0F, -1.0F, 5, 8, 3, 0.0F);
    this.setRotateAngle(wingR, 1.7453292519943295F, -0.17453292519943295F, -0.2617993877991494F);
    this.legR1 = new ModelRenderer(this, 18, 6);
    this.legR1.setRotationPoint(-1.5F, 1.0F, 1.0F);
    this.legR1.addBox(-0.5F, 0.0F, -0.5F, 1, 7, 1, 0.0F);
    this.setRotateAngle(legR1, -0.2617993877991494F, 0.0F, 0.2617993877991494F);
    this.body = new ModelRenderer(this, 0, 11);
    this.body.setRotationPoint(0.0F, 16.0F, -4.0F);
    this.body.addBox(-2.5F, -2.0F, 0.0F, 5, 4, 8, 0.0F);
    this.antennaR1 = new ModelRenderer(this, 24, 6);
    this.antennaR1.setRotationPoint(-1.0F, 0.0F, -0.5F);
    this.antennaR1.addBox(-0.5F, -5.0F, -0.5F, 1, 5, 1, 0.0F);
    this.setRotateAngle(antennaR1, 0.1308996938995747F, 0.0F, -0.2617993877991494F);
    this.legR2 = new ModelRenderer(this, 18, 6);
    this.legR2.setRotationPoint(-2.0F, 1.0F, 3.5F);
    this.legR2.addBox(-0.5F, 0.0F, -0.5F, 1, 7, 1, 0.0F);
    this.setRotateAngle(legR2, 0.0F, 0.0F, 0.2617993877991494F);
    this.head = new ModelRenderer(this, 16, 0);
    this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.head.addBox(-2.0F, -1.25F, -3.0F, 4, 3, 3, 0.0F);
    this.setRotateAngle(head, 0.17453292519943295F, 0.0F, 0.0F);
    this.antennaR2 = new ModelRenderer(this, 24, 6);
    this.antennaR2.setRotationPoint(0.0F, -5.0F, 0.0F);
    this.antennaR2.addBox(-0.5F, -5.0F, -0.5F, 1, 5, 1, 0.0F);
    this.setRotateAngle(antennaR2, 0.39269908169872414F, 0.0F, 0.0F);
    this.legL1 = new ModelRenderer(this, 18, 6);
    this.legL1.setRotationPoint(1.5F, 1.0F, 1.0F);
    this.legL1.addBox(-0.5F, 0.0F, -0.5F, 1, 7, 1, 0.0F);
    this.setRotateAngle(legL1, -0.2617993877991494F, 0.0F, -0.2617993877991494F);
    this.legL3 = new ModelRenderer(this, 18, 6);
    this.legL3.setRotationPoint(1.5F, 1.0F, 6.0F);
    this.legL3.addBox(-0.5F, 0.0F, -0.5F, 1, 7, 1, 0.0F);
    this.setRotateAngle(legL3, 0.2617993877991494F, 0.0F, -0.2617993877991494F);
    this.legR3 = new ModelRenderer(this, 18, 6);
    this.legR3.setRotationPoint(-1.5F, 1.0F, 6.0F);
    this.legR3.addBox(-0.5F, 0.0F, -0.5F, 1, 7, 1, 0.0F);
    this.setRotateAngle(legR3, 0.2617993877991494F, 0.0F, 0.2617993877991494F);
    this.antennaR2_1 = new ModelRenderer(this, 24, 6);
    this.antennaR2_1.setRotationPoint(0.0F, -5.0F, 0.0F);
    this.antennaR2_1.addBox(-0.5F, -5.0F, -0.5F, 1, 5, 1, 0.0F);
    this.setRotateAngle(antennaR2_1, 0.39269908169872414F, 0.0F, 0.0F);
    this.body.addChild(this.legL2);
    this.head.addChild(this.antennaL1);
    this.body.addChild(this.wingL);
    this.body.addChild(this.wingR);
    this.body.addChild(this.legR1);
    this.head.addChild(this.antennaR1);
    this.body.addChild(this.legR2);
    this.body.addChild(this.head);
    this.antennaR1.addChild(this.antennaR2);
    this.body.addChild(this.legL1);
    this.body.addChild(this.legL3);
    this.body.addChild(this.legR3);
    this.antennaL1.addChild(this.antennaR2_1);
  }

  @Nonnull
  @Override
  protected Iterable<ModelRenderer> getHeadParts() {
    return ImmutableSet.of();
  }

  @Nonnull
  @Override
  protected Iterable<ModelRenderer> getBodyParts() {
    return ImmutableSet.of(body);
  }

  private float getSwing(float deg, float ageInTicks) {
    return (float) Math.sin(ageInTicks * 0.125f * (Math.PI * 2.0f) + Math.toRadians(deg));
  }

  private float getBobble(float deg, float ageInTicks) {
    return (float) Math.sin(ageInTicks * 0.03125f * (Math.PI * 2.0f) + Math.toRadians(deg));
  }

  @Override
  public void setRotationAngles(SpiritBeetleEntity beetleEntity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    this.head.rotateAngleX = headPitch * 0.017453292F;
    this.head.rotateAngleY = netHeadYaw * 0.017453292F;
    this.antennaR1.rotateAngleX = 0.1308996938995747F + getBobble(30, ageInTicks) * 0.2617993877991494F;
    this.antennaL1.rotateAngleX = 0.1308996938995747F + getBobble(100, ageInTicks) * 0.2617993877991494F;
    this.wingL.rotateAngleY = 0.17453292519943295F + 0.0872664626F * getBobble(45, ageInTicks);
    this.wingR.rotateAngleY = -0.17453292519943295F - 0.0872664626F * getBobble(160, ageInTicks);
//    if (state != ModelState.SHOULDER) {
    //  }
    this.legL1.rotateAngleZ = limbSwingAmount * getSwing(0, ageInTicks) - 0.2617993877991494F;
    this.legL2.rotateAngleZ = limbSwingAmount * getSwing(120, ageInTicks) - 0.2617993877991494F;
    this.legL3.rotateAngleZ = limbSwingAmount * getSwing(240, ageInTicks) - 0.2617993877991494F;
    this.legR1.rotateAngleZ = limbSwingAmount * getSwing(180, ageInTicks) + 0.2617993877991494F;
    this.legR2.rotateAngleZ = limbSwingAmount * getSwing(300, ageInTicks) + 0.2617993877991494F;
    this.legR3.rotateAngleZ = limbSwingAmount * getSwing(60, ageInTicks) + 0.2617993877991494F;
  }

  /**
   * This is a helper function from Tabula to set the rotation of model parts
   */
  private void setRotateAngle(@Nonnull ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.rotateAngleX = x;
    modelRenderer.rotateAngleY = y;
    modelRenderer.rotateAngleZ = z;
  }
}

