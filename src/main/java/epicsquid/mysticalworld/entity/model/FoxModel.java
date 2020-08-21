package epicsquid.mysticalworld.entity.model;

import com.google.common.collect.ImmutableSet;
import epicsquid.mysticalworld.entity.SilverFoxEntity;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;

import javax.annotation.Nonnull;

/**
 * FoxModel - Elucent
 * Created using Tabula 5.1.0
 */
public class FoxModel extends AgeableModel<SilverFoxEntity> {

  private ModelRenderer body1;
  private ModelRenderer frontL;
  private ModelRenderer frontR;
  private ModelRenderer body2;
  private ModelRenderer neck;
  private ModelRenderer backL;
  private ModelRenderer backR;
  private ModelRenderer tail1;
  private ModelRenderer tail2;
  private ModelRenderer tail3;
  private ModelRenderer tail4;
  private ModelRenderer head;
  private ModelRenderer snout;
  private ModelRenderer earR;
  private ModelRenderer earL;
  private int state = 0;

  public FoxModel() {
    super(true, 5.0f, 2.0f);
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

  @Nonnull
  @Override
  protected Iterable<ModelRenderer> getHeadParts() {
    return ImmutableSet.of();
  }

  @Nonnull
  @Override
  protected Iterable<ModelRenderer> getBodyParts() {
    return ImmutableSet.of(body1);
  }

  private float getBobble(float deg, float ageInTicks) {
    return (float) Math.sin(ageInTicks * 0.03125f * (Math.PI * 2.0f) + Math.toRadians(deg));
  }

  @Override
  public void setRotationAngles(@Nonnull SilverFoxEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    float sin = (float) Math.sin(ageInTicks * 0.125f * (Math.PI * 2.0f));
    float cos = (float) Math.cos(ageInTicks * 0.0625f * (Math.PI * 2.0f));
    if (state == 0) {
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
      this.setRotateAngle(body1, 0F, 0.0F, 0.0F);
      this.setRotateAngle(body2, 0F, 0.0F, 0.0F);
    } else if (state == 1) { // sitting
      this.setRotateAngle(backL, -0.8196066167365371F, -0.31869712141416456F, 0.0F);
      this.setRotateAngle(backR, -0.8196066167365371F, 0.36425021489121656F, 0.0F);
      this.setRotateAngle(body1, -0.6829473363053812F, 0.0F, 0.0F);
      this.setRotateAngle(body2, -0.045553093477052F, 0.0F, 0.0F);
      this.setRotateAngle(earL, 0.0F, -0.2617993877991494F, 0.6085963101704227F);
      this.setRotateAngle(earR, 0.0F, 0.2617993877991494F, -0.5988224663592545F);
      this.setRotateAngle(frontL, 0.31869712141416456F, 0.0F, 0.0F);
      this.setRotateAngle(frontR, 0.31869712141416456F, 0.0F, 0.0F);
      this.setRotateAngle(neck, 0.36425021489121656F, 0.0F, 0.0F);
      this.setRotateAngle(snout, 0.17453292519943295F, 0.0F, 0.0F);
      this.setRotateAngle(tail1, 2.5497515042385164F, 0.0F, 0.06178465552059926F);
      this.setRotateAngle(tail2, -0.39269908169872414F, 0.0F, 0.08726646259971647F);
      this.setRotateAngle(tail3, 0.0F, 0.0F, 0.06178465552059926F);
      this.setRotateAngle(tail4, 0.39269908169872414F, 0.0F, 0.0F);
    } else if (state == 2) { // sleeping
      this.setRotateAngle(earL, 0.5462880558742251F, 0.0F, 0.7740535232594852F);
      this.setRotateAngle(frontR, -0.6373942428283291F, 0.12636183784438945F, -1.5025539530419183F);
      this.setRotateAngle(earR, 0.40980330836826856F, 0.2617993877991494F, -0.9560913642424937F);
      this.setRotateAngle(backR, -1.5025539530419183F, -0.091106186954104F, 0.0F);
      this.setRotateAngle(backL, -1.5025539530419183F, -0.31869712141416456F, -0.045553093477052F);
      this.setRotateAngle(tail1, 0.8196066167365371F, 0.091106186954104F, -1.3658946726107624F);
      this.setRotateAngle(frontL, 0.091106186954104F, 0.0F, -1.4570008595648662F);
      this.setRotateAngle(body2, -0.091106186954104F, 0.18203784098300857F, 0.0F);
      this.setRotateAngle(tail3, -0.8651597102135892F, 0.0F, -0.18203784098300857F);
      this.setRotateAngle(neck, 0.0F, -1.0016444577195458F, 0.091106186954104F);
      this.setRotateAngle(tail4, -0.36425021489121656F, 0.0F, 0.0F);
      this.setRotateAngle(body1, 0.017453292519943295F, -0.36425021489121656F, 0.0F);
      this.setRotateAngle(tail2, -0.9208357133522083F, 0.091106186954104F, 0.091106186954104F);
      this.setRotateAngle(head, 0.045553093477052F, -0.18203784098300857F, 0.0F);
      this.setRotateAngle(snout, -0.091106186954104F, 0.0F, 0.0F);
    }
  }

  @Override
  public void setLivingAnimations(@Nonnull SilverFoxEntity entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTickTime) {
    SilverFoxEntity fox = entitylivingbaseIn;

    this.backL.setRotationPoint(1.0F, 4.0F, 1.5F);
    this.backR.setRotationPoint(-1.0F, 4.0F, 1.5F);
    this.body1.setRotationPoint(0.0F, 15.5F, -2.0F);
    this.body2.setRotationPoint(0.0F, 0.0F, 5.0F);
    this.earL.setRotationPoint(1.5F, -1.0F, -1.0F);
    this.earR.setRotationPoint(-1.5F, -1.0F, -1.0F);
    this.frontL.setRotationPoint(1.0F, 4.0F, 1.0F);
    this.frontR.setRotationPoint(-1.0F, 4.0F, 1.0F);
    this.head.setRotationPoint(0.0F, 0.0F, -4.0F);
    this.neck.setRotationPoint(0.0F, 1.5F, 2.0F);
    this.snout.setRotationPoint(0.0F, 0.0F, -3.0F);
    this.tail1.setRotationPoint(0.0F, 1.0F, 2.5F);
    this.tail2.setRotationPoint(0.0F, 1.5F, 0.0F);
    this.tail3.setRotationPoint(0.0F, 3.0F, 0.0F);
    this.tail4.setRotationPoint(0.0F, 3.5F, 0.0F);

    if (fox.isSitting()) {
      state = 1;
    } else if (fox.isSleeping()) {
      state = 2;
    } else {
      state = 0;
    }

    super.setLivingAnimations(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTickTime);
  }

  /**
   * This is a helper function from Tabula to set the rotation of model parts
   */
  private void setRotateAngle(@Nonnull ModelRenderer ModelRenderer, float x, float y, float z) {
    ModelRenderer.rotateAngleX = x;
    ModelRenderer.rotateAngleY = y;
    ModelRenderer.rotateAngleZ = z;
  }
}
