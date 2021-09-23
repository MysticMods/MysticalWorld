package mysticmods.mysticalworld.entity.model;

import com.google.common.collect.ImmutableSet;
import mysticmods.mysticalworld.entity.SilverFoxEntity;
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

  public FoxModel() {
    super(true, 5.0f, 2.0f);
    this.texWidth = 32;
    this.texHeight = 32;
    this.backL = new ModelRenderer(this, 0, 16);
    this.backL.mirror = true;
    this.backL.setPos(1.0F, 4.0F, 1.5F);
    this.backL.addBox(-1.0F, 0.0F, -1.0F, 2, 6, 2, 0.0F);
    this.tail4 = new ModelRenderer(this, 8, 18);
    this.tail4.setPos(0.0F, 3.5F, 0.0F);
    this.tail4.addBox(-1.0F, 0.0F, -1.0F, 2, 2, 2, 0.0F);
    this.setRotateAngle(tail4, 0.39269908169872414F, 0.0F, 0.0F);
    this.body1 = new ModelRenderer(this, 12, 8);
    this.body1.setPos(0.0F, 15.5F, -2.0F);
    this.body1.addBox(-2.5F, 0.0F, 0.0F, 5, 4, 5, 0.0F);
    this.backR = new ModelRenderer(this, 0, 16);
    this.backR.setPos(-1.0F, 4.0F, 1.5F);
    this.backR.addBox(-1.0F, 0.0F, -1.0F, 2, 6, 2, 0.0F);
    this.neck = new ModelRenderer(this, 16, 0);
    this.neck.setPos(0.0F, 1.5F, 2.0F);
    this.neck.addBox(-2.0F, -2.0F, -4.0F, 4, 4, 4, 0.0F);
    this.setRotateAngle(neck, -0.2617993877991494F, 0.0F, 0.0F);
    this.earR = new ModelRenderer(this, 0, 12);
    this.earR.setPos(-1.5F, -1.0F, -1.0F);
    this.earR.addBox(-1.0F, -3.0F, -0.5F, 2, 3, 1, 0.0F);
    this.setRotateAngle(earR, 0.0F, 0.2617993877991494F, -0.5235987755982988F);
    this.tail3 = new ModelRenderer(this, 0, 24);
    this.tail3.setPos(0.0F, 3.0F, 0.0F);
    this.tail3.addBox(-2.0F, 0.0F, -2.0F, 4, 4, 4, 0.0F);
    this.snout = new ModelRenderer(this, 0, 0);
    this.snout.setPos(0.0F, 0.0F, -3.0F);
    this.snout.addBox(-1.5F, 0.0F, -3.0F, 3, 2, 3, 0.0F);
    this.setRotateAngle(snout, 0.17453292519943295F, 0.0F, 0.0F);
    this.head = new ModelRenderer(this, 0, 5);
    this.head.setPos(0.0F, 0.0F, -4.0F);
    this.head.addBox(-2.5F, -2.0F, -3.0F, 5, 4, 3, 0.0F);
    this.setRotateAngle(head, 0.2617993877991494F, 0.0F, 0.0F);
    this.tail1 = new ModelRenderer(this, 12, 0);
    this.tail1.setPos(0.0F, 1.0F, 2.5F);
    this.tail1.addBox(-1.0F, 0.0F, -1.0F, 2, 2, 2, 0.0F);
    this.setRotateAngle(tail1, 1.1780972450961724F, 0.0F, 0.0F);
    this.earL = new ModelRenderer(this, 0, 12);
    this.earL.mirror = true;
    this.earL.setPos(1.5F, -1.0F, -1.0F);
    this.earL.addBox(-1.0F, -3.0F, -0.5F, 2, 3, 1, 0.0F);
    this.setRotateAngle(earL, 0.0F, -0.2617993877991494F, 0.5235987755982988F);
    this.frontL = new ModelRenderer(this, 0, 16);
    this.frontL.mirror = true;
    this.frontL.setPos(1.0F, 4.0F, 1.0F);
    this.frontL.addBox(-1.0F, 0.0F, -1.0F, 2, 6, 2, 0.0F);
    this.body2 = new ModelRenderer(this, 16, 17);
    this.body2.setPos(0.0F, 0.0F, 5.0F);
    this.body2.addBox(-2.0F, 0.0F, 0.0F, 4, 4, 3, 0.0F);
    this.frontR = new ModelRenderer(this, 0, 16);
    this.frontR.setPos(-1.0F, 4.0F, 1.0F);
    this.frontR.addBox(-1.0F, 0.0F, -1.0F, 2, 6, 2, 0.0F);
    this.tail2 = new ModelRenderer(this, 16, 24);
    this.tail2.setPos(0.0F, 1.5F, 0.0F);
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
  protected Iterable<ModelRenderer> headParts() {
    return ImmutableSet.of();
  }

  @Nonnull
  @Override
  protected Iterable<ModelRenderer> bodyParts() {
    return ImmutableSet.of(body1);
  }

  private float getBobble(float deg, float ageInTicks) {
    return (float) Math.sin(ageInTicks * 0.03125f * (Math.PI * 2.0f) + Math.toRadians(deg));
  }

  @Override
  public void setupAnim(@Nonnull SilverFoxEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    float sin = (float) Math.sin(ageInTicks * 0.125f * (Math.PI * 2.0f));
    float cos = (float) Math.cos(ageInTicks * 0.0625f * (Math.PI * 2.0f));
    if (!entityIn.isInSittingPose()) {
      this.earL.zRot = 0.5235987755982988F + getBobble(60, ageInTicks) * 0.0981747703F;
      this.earR.zRot = -0.5235987755982988F - getBobble(130, ageInTicks) * 0.0981747703F;
      this.backL.xRot = limbSwingAmount * sin;
      this.frontR.xRot = limbSwingAmount * sin;
      this.backR.xRot = -limbSwingAmount * sin;
      this.frontL.xRot = -limbSwingAmount * sin;
      this.head.xRot = headPitch * 0.017453292F;
      this.head.yRot = netHeadYaw * 0.017453292F;
      this.tail1.xRot = 1.1780972450961724F + limbSwingAmount;
      this.tail1.zRot = limbSwingAmount * 0.375f * cos + 0.0872664626F * getBobble(45, ageInTicks);
      this.tail2.zRot = limbSwingAmount * 0.375f * cos + 0.0872664626F * getBobble(90, ageInTicks);
      this.tail3.zRot = limbSwingAmount * 0.375f * cos + 0.0872664626F * getBobble(135, ageInTicks);
      this.tail4.zRot = limbSwingAmount * 0.375f * cos + 0.0872664626F * getBobble(180, ageInTicks);
      this.setRotateAngle(body1, 0F, 0.0F, 0.0F);
      this.setRotateAngle(body2, 0F, 0.0F, 0.0F);
    } else {
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
    }
  }

  @Override
  public void prepareMobModel(@Nonnull SilverFoxEntity entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTickTime) {

    this.backL.setPos(1.0F, 4.0F, 1.5F);
    this.backR.setPos(-1.0F, 4.0F, 1.5F);
    this.body1.setPos(0.0F, 15.5F, -2.0F);
    this.body2.setPos(0.0F, 0.0F, 5.0F);
    this.earL.setPos(1.5F, -1.0F, -1.0F);
    this.earR.setPos(-1.5F, -1.0F, -1.0F);
    this.frontL.setPos(1.0F, 4.0F, 1.0F);
    this.frontR.setPos(-1.0F, 4.0F, 1.0F);
    this.head.setPos(0.0F, 0.0F, -4.0F);
    this.neck.setPos(0.0F, 1.5F, 2.0F);
    this.snout.setPos(0.0F, 0.0F, -3.0F);
    this.tail1.setPos(0.0F, 1.0F, 2.5F);
    this.tail2.setPos(0.0F, 1.5F, 0.0F);
    this.tail3.setPos(0.0F, 3.0F, 0.0F);
    this.tail4.setPos(0.0F, 3.5F, 0.0F);

    super.prepareMobModel(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTickTime);
  }

  /**
   * This is a helper function from Tabula to set the rotation of model parts
   */
  private void setRotateAngle(@Nonnull ModelRenderer ModelRenderer, float x, float y, float z) {
    ModelRenderer.xRot = x;
    ModelRenderer.yRot = y;
    ModelRenderer.zRot = z;
  }
}
