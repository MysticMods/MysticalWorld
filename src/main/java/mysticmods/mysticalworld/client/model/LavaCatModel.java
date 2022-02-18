package mysticmods.mysticalworld.client.model;


import com.google.common.collect.ImmutableList;
import mysticmods.mysticalworld.entity.LavaCatEntity;
import net.minecraft.client.model.AgeableListModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;

@OnlyIn(Dist.CLIENT)
public class LavaCatModel extends AgeableListModel<LavaCatEntity> {
  public final ModelPart ocelotBackLeftLeg;
  public final ModelPart ocelotBackRightLeg;
  public final ModelPart ocelotFrontLeftLeg;
  public final ModelPart ocelotFrontRightLeg;
  public final ModelPart ocelotTail;
  public final ModelPart ocelotTail2;
  public final ModelPart ocelotHead;
  public final ModelPart ocelotBody;
  private int state = 1;

  public LavaCatModel() {
    super(true, 5.0f, 2.0f);
    this.ocelotHead = new ModelPart(this);
    this.ocelotHead.addBox("main", -2.5F, -2.0F, -3.0F, 5, 4, 5, 0, 0, 0);
    this.ocelotHead.addBox("nose", -1.5F, 0.0F, -4.0F, 3, 2, 2, 0, 0, 24);
    this.ocelotHead.addBox("ear1", -2.0F, -3.0F, 0.0F, 1, 1, 2, 0, 0, 10);
    this.ocelotHead.addBox("ear2", 1.0F, -3.0F, 0.0F, 1, 1, 2, 0, 6, 10);
    this.ocelotHead.setPos(0.0F, 15.0F, -9.0F);
    this.ocelotBody = new ModelPart(this, 20, 0);
    this.ocelotBody.addBox(-2.0F, 3.0F, -8.0F, 4, 16, 6, 0);
    this.ocelotBody.setPos(0.0F, 12.0F, -10.0F);
    this.ocelotTail = new ModelPart(this, 0, 15);
    this.ocelotTail.addBox(-0.5F, 0.0F, 0.0F, 1, 8, 1, 0);
    this.ocelotTail.xRot = 0.9F;
    this.ocelotTail.setPos(0.0F, 15.0F, 8.0F);
    this.ocelotTail2 = new ModelPart(this, 4, 15);
    this.ocelotTail2.addBox(-0.5F, 0.0F, 0.0F, 1, 8, 1, 0);
    this.ocelotTail2.setPos(0.0F, 20.0F, 14.0F);
    this.ocelotBackLeftLeg = new ModelPart(this, 8, 13);
    this.ocelotBackLeftLeg.addBox(-1.0F, 0.0F, 1.0F, 2, 6, 2, 0);
    this.ocelotBackLeftLeg.setPos(1.1F, 18.0F, 5.0F);
    this.ocelotBackRightLeg = new ModelPart(this, 8, 13);
    this.ocelotBackRightLeg.addBox(-1.0F, 0.0F, 1.0F, 2, 6, 2, 0);
    this.ocelotBackRightLeg.setPos(-1.1F, 18.0F, 5.0F);
    this.ocelotFrontLeftLeg = new ModelPart(this, 40, 0);
    this.ocelotFrontLeftLeg.addBox(-1.0F, 0.0F, 0.0F, 2, 10, 2, 0);
    this.ocelotFrontLeftLeg.setPos(1.2F, 14.1F, -5.0F);
    this.ocelotFrontRightLeg = new ModelPart(this, 40, 0);
    this.ocelotFrontRightLeg.addBox(-1.0F, 0.0F, 0.0F, 2, 10, 2, 0);
    this.ocelotFrontRightLeg.setPos(-1.2F, 14.1F, -5.0F);
  }

  @Override
  protected Iterable<ModelPart> headParts() {
    return ImmutableList.of();
  }

  @Override
  protected Iterable<ModelPart> bodyParts() {
    return ImmutableList.of(this.ocelotBody, this.ocelotBackLeftLeg, this.ocelotBackRightLeg, this.ocelotFrontLeftLeg, this.ocelotFrontRightLeg, this.ocelotTail, this.ocelotTail2, this.ocelotHead);
  }


  /**
   * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
   * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how
   * "far" arms and legs can swing at most.
   */
  @Override
  public void setupAnim(@Nonnull LavaCatEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    this.ocelotHead.xRot = headPitch * 0.017453292F;
    this.ocelotHead.yRot = netHeadYaw * 0.017453292F;

    if (this.state != 3) {
      this.ocelotBody.xRot = ((float) Math.PI / 2F);

      if (this.state == 2) {
        this.ocelotBackLeftLeg.xRot = Mth.cos(limbSwing * 0.6662F) * limbSwingAmount;
        this.ocelotBackRightLeg.xRot = Mth.cos(limbSwing * 0.6662F + 0.3F) * limbSwingAmount;
        this.ocelotFrontLeftLeg.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI + 0.3F) * limbSwingAmount;
        this.ocelotFrontRightLeg.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * limbSwingAmount;
        this.ocelotTail2.xRot = 1.7278761F + ((float) Math.PI / 10F) * Mth.cos(limbSwing) * limbSwingAmount;
      } else {
        this.ocelotBackLeftLeg.xRot = Mth.cos(limbSwing * 0.6662F) * limbSwingAmount;
        this.ocelotBackRightLeg.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * limbSwingAmount;
        this.ocelotFrontLeftLeg.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * limbSwingAmount;
        this.ocelotFrontRightLeg.xRot = Mth.cos(limbSwing * 0.6662F) * limbSwingAmount;

        if (this.state == 1) {
          this.ocelotTail2.xRot = 1.7278761F + ((float) Math.PI / 4F) * Mth.cos(limbSwing) * limbSwingAmount;
        } else {
          this.ocelotTail2.xRot = 1.7278761F + 0.47123894F * Mth.cos(limbSwing) * limbSwingAmount;
        }
      }
    }
  }

  /**
   * Used for easily adding entity-dependent animations. The second and third float params here are the same second
   * and third as in the setRotationAngles method.
   */
  @Override
  public void prepareMobModel(LavaCatEntity entityocelot, float limbSwing, float limbSwingAmount, float partialTickTime) {
    this.ocelotBody.y = 12.0F;
    this.ocelotBody.z = -10.0F;
    this.ocelotHead.y = 15.0F;
    this.ocelotHead.z = -9.0F;
    this.ocelotTail.y = 15.0F;
    this.ocelotTail.z = 8.0F;
    this.ocelotTail2.y = 20.0F;
    this.ocelotTail2.z = 14.0F;
    this.ocelotFrontLeftLeg.y = 13.8F;
    this.ocelotFrontLeftLeg.z = -5.0F;
    this.ocelotFrontRightLeg.y = 13.8F;
    this.ocelotFrontRightLeg.z = -5.0F;
    this.ocelotBackLeftLeg.y = 18.0F;
    this.ocelotBackLeftLeg.z = 5.0F;
    this.ocelotBackRightLeg.y = 18.0F;
    this.ocelotBackRightLeg.z = 5.0F;
    this.ocelotTail.xRot = 0.9F;
    this.ocelotBody.xRot = 0;
    this.ocelotTail2.xRot = 0;
    this.ocelotFrontLeftLeg.xRot = 0;
    this.ocelotFrontRightLeg.xRot = 0;
    this.ocelotBackLeftLeg.xRot = 0;
    this.ocelotBackRightLeg.xRot = 0;

/*    if (entityocelot.isSneaking()) {
      ++this.ocelotBody.rotationPointY;
      this.ocelotHead.rotationPointY += 2.0F;
      ++this.ocelotTail.rotationPointY;
      this.ocelotTail2.rotationPointY += -4.0F;
      this.ocelotTail2.rotationPointZ += 2.0F;
      this.ocelotTail.rotateAngleX = ((float) Math.PI / 2F);
      this.ocelotTail2.rotateAngleX = ((float) Math.PI / 2F);
      this.state = 0;
    } else if (entityocelot.isSprinting()) {
      this.ocelotTail2.rotationPointY = this.ocelotTail.rotationPointY;
      this.ocelotTail2.rotationPointZ += 2.0F;
      this.ocelotTail.rotateAngleX = ((float) Math.PI / 2F);
      this.ocelotTail2.rotateAngleX = ((float) Math.PI / 2F);
      this.state = 2;*/
    if (entityocelot.isInSittingPose()) {
      this.ocelotBody.xRot = ((float) Math.PI / 4F);
      this.ocelotBody.y += -4.0F;
      this.ocelotBody.z += 5.0F;
      this.ocelotHead.y += -3.3F;
      ++this.ocelotHead.z;
      this.ocelotTail.y += 8.0F;
      this.ocelotTail.z += -2.0F;
      this.ocelotTail2.y += 2.0F;
      this.ocelotTail2.z += -0.8F;
      this.ocelotTail.xRot = 1.7278761F;
      this.ocelotTail2.xRot = 2.670354F;
      this.ocelotFrontLeftLeg.xRot = -0.15707964F;
      this.ocelotFrontLeftLeg.y = 15.8F;
      this.ocelotFrontLeftLeg.z = -7.0F;
      this.ocelotFrontRightLeg.xRot = -0.15707964F;
      this.ocelotFrontRightLeg.y = 15.8F;
      this.ocelotFrontRightLeg.z = -7.0F;
      this.ocelotBackLeftLeg.xRot = -((float) Math.PI / 2F);
      this.ocelotBackLeftLeg.y = 21.0F;
      this.ocelotBackLeftLeg.z = 1.0F;
      this.ocelotBackRightLeg.xRot = -((float) Math.PI / 2F);
      this.ocelotBackRightLeg.y = 21.0F;
      this.ocelotBackRightLeg.z = 1.0F;
      this.state = 3;
    } else {
      this.state = 1;
    }
  }
}

