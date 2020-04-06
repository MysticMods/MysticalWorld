package epicsquid.mysticalworld.entity.model;

import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.entity.BeetleEntity;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

/**
 * BeetleModel - Elucent
 * Created using Tabula 5.1.0
 */
public class BeetleModel extends ShoulderRidingModel<BeetleEntity> {

  private RendererModel body;
  private RendererModel wingL;
  private RendererModel wingR;
  private RendererModel head;
  private RendererModel legL1;
  private RendererModel legL2;
  private RendererModel legL3;
  private RendererModel legR1;
  private RendererModel legR2;
  private RendererModel legR3;
  private RendererModel antennaR1;
  private RendererModel antennaL1;
  private RendererModel antennaR2;
  private RendererModel antennaR2_1;

  public BeetleModel() {
    this.textureWidth = 32;
    this.textureHeight = 32;
    this.legL2 = new RendererModel(this, 18, 6);
    this.legL2.setRotationPoint(2.0F, 1.0F, 3.5F);
    this.legL2.addBox(-0.5F, 0.0F, -0.5F, 1, 7, 1, 0.0F);
    this.setRotateAngle(legL2, 0.0F, 0.0F, -0.2617993877991494F);
    this.antennaL1 = new RendererModel(this, 24, 6);
    this.antennaL1.setRotationPoint(1.0F, 0.0F, -0.5F);
    this.antennaL1.addBox(-0.5F, -5.0F, -0.5F, 1, 5, 1, 0.0F);
    this.setRotateAngle(antennaL1, 0.1308996938995747F, 0.0F, 0.2617993877991494F);
    this.wingL = new RendererModel(this, 0, 0);
    this.wingL.setRotationPoint(1.5F, -0.5F, 1.0F);
    this.wingL.addBox(-2.5F, 0.0F, -1.0F, 5, 8, 3, 0.0F);
    this.setRotateAngle(wingL, 1.7453292519943295F, 0.17453292519943295F, 0.2617993877991494F);
    this.wingR = new RendererModel(this, 0, 0);
    this.wingR.mirror = true;
    this.wingR.setRotationPoint(-1.5F, -0.5F, 1.0F);
    this.wingR.addBox(-2.5F, 0.0F, -1.0F, 5, 8, 3, 0.0F);
    this.setRotateAngle(wingR, 1.7453292519943295F, -0.17453292519943295F, -0.2617993877991494F);
    this.legR1 = new RendererModel(this, 18, 6);
    this.legR1.setRotationPoint(-1.5F, 1.0F, 1.0F);
    this.legR1.addBox(-0.5F, 0.0F, -0.5F, 1, 7, 1, 0.0F);
    this.setRotateAngle(legR1, -0.2617993877991494F, 0.0F, 0.2617993877991494F);
    this.body = new RendererModel(this, 0, 11);
    this.body.setRotationPoint(0.0F, 16.0F, -4.0F);
    this.body.addBox(-2.5F, -2.0F, 0.0F, 5, 4, 8, 0.0F);
    this.antennaR1 = new RendererModel(this, 24, 6);
    this.antennaR1.setRotationPoint(-1.0F, 0.0F, -0.5F);
    this.antennaR1.addBox(-0.5F, -5.0F, -0.5F, 1, 5, 1, 0.0F);
    this.setRotateAngle(antennaR1, 0.1308996938995747F, 0.0F, -0.2617993877991494F);
    this.legR2 = new RendererModel(this, 18, 6);
    this.legR2.setRotationPoint(-2.0F, 1.0F, 3.5F);
    this.legR2.addBox(-0.5F, 0.0F, -0.5F, 1, 7, 1, 0.0F);
    this.setRotateAngle(legR2, 0.0F, 0.0F, 0.2617993877991494F);
    this.head = new RendererModel(this, 16, 0);
    this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.head.addBox(-2.0F, -1.25F, -3.0F, 4, 3, 3, 0.0F);
    this.setRotateAngle(head, 0.17453292519943295F, 0.0F, 0.0F);
    this.antennaR2 = new RendererModel(this, 24, 6);
    this.antennaR2.setRotationPoint(0.0F, -5.0F, 0.0F);
    this.antennaR2.addBox(-0.5F, -5.0F, -0.5F, 1, 5, 1, 0.0F);
    this.setRotateAngle(antennaR2, 0.39269908169872414F, 0.0F, 0.0F);
    this.legL1 = new RendererModel(this, 18, 6);
    this.legL1.setRotationPoint(1.5F, 1.0F, 1.0F);
    this.legL1.addBox(-0.5F, 0.0F, -0.5F, 1, 7, 1, 0.0F);
    this.setRotateAngle(legL1, -0.2617993877991494F, 0.0F, -0.2617993877991494F);
    this.legL3 = new RendererModel(this, 18, 6);
    this.legL3.setRotationPoint(1.5F, 1.0F, 6.0F);
    this.legL3.addBox(-0.5F, 0.0F, -0.5F, 1, 7, 1, 0.0F);
    this.setRotateAngle(legL3, 0.2617993877991494F, 0.0F, -0.2617993877991494F);
    this.legR3 = new RendererModel(this, 18, 6);
    this.legR3.setRotationPoint(-1.5F, 1.0F, 6.0F);
    this.legR3.addBox(-0.5F, 0.0F, -0.5F, 1, 7, 1, 0.0F);
    this.setRotateAngle(legR3, 0.2617993877991494F, 0.0F, 0.2617993877991494F);
    this.antennaR2_1 = new RendererModel(this, 24, 6);
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

  private float getSwing(float deg, float ageInTicks) {
    return (float) Math.sin(ageInTicks * 0.125f * (Math.PI * 2.0f) + Math.toRadians(deg));
  }

  private float getBobble(float deg, float ageInTicks) {
    return (float) Math.sin(ageInTicks * 0.03125f * (Math.PI * 2.0f) + Math.toRadians(deg));
  }

  @Override
  public void renderOnShoulder(float limbSwing, float limbSwingAmount, float netHeadYaw, float headPitch, float scaleFactor, int ticksExisted) {
    this.setRotationAngles(ModelState.SHOULDER, ticksExisted, limbSwing, limbSwingAmount, 0.0F, netHeadYaw, headPitch);
    this.render(scaleFactor);
  }

  @Override
  public void setRotationAngles(ModelState state, int ticksExisted, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    this.head.rotateAngleX = headPitch * 0.017453292F;
    this.head.rotateAngleY = netHeadYaw * 0.017453292F;
    this.antennaR1.rotateAngleX = 0.1308996938995747F + getBobble(30, ageInTicks) * 0.2617993877991494F;
    this.antennaL1.rotateAngleX = 0.1308996938995747F + getBobble(100, ageInTicks) * 0.2617993877991494F;
    this.wingL.rotateAngleY = 0.17453292519943295F + 0.0872664626F * getBobble(45, ageInTicks);
    this.wingR.rotateAngleY = -0.17453292519943295F - 0.0872664626F * getBobble(160, ageInTicks);
    if (state != ModelState.SHOULDER) {
      this.legL1.rotateAngleZ = limbSwingAmount * getSwing(0, ageInTicks) - 0.2617993877991494F;
      this.legL2.rotateAngleZ = limbSwingAmount * getSwing(120, ageInTicks) - 0.2617993877991494F;
      this.legL3.rotateAngleZ = limbSwingAmount * getSwing(240, ageInTicks) - 0.2617993877991494F;
      this.legR1.rotateAngleZ = limbSwingAmount * getSwing(180, ageInTicks) + 0.2617993877991494F;
      this.legR2.rotateAngleZ = limbSwingAmount * getSwing(300, ageInTicks) + 0.2617993877991494F;
      this.legR3.rotateAngleZ = limbSwingAmount * getSwing(60, ageInTicks) + 0.2617993877991494F;
    }
  }

  @Override
  public void render(float scale) {
    this.body.render(scale);
  }

  @Override
  public ResourceLocation getTexture() {
    return new ResourceLocation(MysticalWorld.MODID + ":textures/entity/beetle_blue.png");
  }

  @Override
  public void render(@Nonnull BeetleEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
    setRotationAngles(ModelState.NORMAL, entity.ticksExisted, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
    if (entity.isSitting()) {
      this.body.setRotationPoint(0.0F, 20.0F, -4.0F);
      this.setRotateAngle(legR1, -0.4619008920774175F, -0.12228424816241118F, 1.2226123587776043F);
      this.setRotateAngle(legR2, 0.0F, 0.0F, 1.1609087739532686F);
      this.setRotateAngle(legR3, 0.2617993950843811F, 0.0F, 1.1727415173224531F);
      this.setRotateAngle(legL1, -0.5508348907409892F, 0.12228424816241122F, -1.213797560945557F);
      this.setRotateAngle(legL2, 0.0F, 0.0F, -1.1873531674494129F);
      this.setRotateAngle(legL3, 0.2617993950843811F, 0.0F, -1.231427156609652F);
    } else {
      this.body.setRotationPoint(0.0F, 16.0F, -4.0F);
      this.setRotateAngle(legR1, -0.2617993877991494F, 0.0F, 0.2617993877991494F);
      this.setRotateAngle(legR2, 0.0F, 0.0F, 0.2617993877991494F);
      this.setRotateAngle(legR3, 0.2617993877991494F, 0.0F, 0.2617993877991494F);
      this.setRotateAngle(legL1, -0.2617993877991494F, 0.0F, -0.2617993877991494F);
      this.setRotateAngle(legL2, 0.0F, 0.0F, -0.2617993877991494F);
      this.setRotateAngle(legL3, 0.2617993877991494F, 0.0F, -0.2617993877991494F);
    }
    render(scaleFactor);
  }

  /**
   * This is a helper function from Tabula to set the rotation of model parts
   */
  private void setRotateAngle(@Nonnull RendererModel modelRenderer, float x, float y, float z) {
    modelRenderer.rotateAngleX = x;
    modelRenderer.rotateAngleY = y;
    modelRenderer.rotateAngleZ = z;
  }
}
