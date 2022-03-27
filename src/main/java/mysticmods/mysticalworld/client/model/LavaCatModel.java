package mysticmods.mysticalworld.client.model;

import com.google.common.collect.ImmutableList;
import mysticmods.mysticalworld.entity.LavaCatEntity;
import net.minecraft.client.model.AgeableListModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;

import javax.annotation.Nonnull;

public class LavaCatModel<T extends LavaCatEntity> extends AgeableListModel<T> {
  protected final ModelPart leftHindLeg;
  protected final ModelPart rightHindLeg;
  protected final ModelPart leftFrontLeg;
  protected final ModelPart rightFrontLeg;
  protected final ModelPart tail1;
  protected final ModelPart tail2;
  protected final ModelPart head;
  protected final ModelPart body;
  private int state = 1;

  public LavaCatModel(ModelPart pRoot) {
    super(true, 5.0f, 2.0f);
    this.head = pRoot.getChild("head");
    this.body = pRoot.getChild("body");
    this.tail1 = pRoot.getChild("tail1");
    this.tail2 = pRoot.getChild("tail2");
    this.leftHindLeg = pRoot.getChild("left_hind_leg");
    this.rightHindLeg = pRoot.getChild("right_hind_leg");
    this.leftFrontLeg = pRoot.getChild("left_front_leg");
    this.rightFrontLeg = pRoot.getChild("right_front_leg");
  }

  public static MeshDefinition createBodyMesh(CubeDeformation pCubeDeformation) {
    MeshDefinition meshdefinition = new MeshDefinition();
    PartDefinition partdefinition = meshdefinition.getRoot();
    partdefinition.addOrReplaceChild("head", CubeListBuilder.create().addBox("main", -2.5F, -2.0F, -3.0F, 5.0F, 4.0F, 5.0F, pCubeDeformation).addBox("nose", -1.5F, 0.0F, -4.0F, 3, 2, 2, pCubeDeformation, 0, 24).addBox("ear1", -2.0F, -3.0F, 0.0F, 1, 1, 2, pCubeDeformation, 0, 10).addBox("ear2", 1.0F, -3.0F, 0.0F, 1, 1, 2, pCubeDeformation, 6, 10), PartPose.offset(0.0F, 15.0F, -9.0F));
    partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(20, 0).addBox(-2.0F, 3.0F, -8.0F, 4.0F, 16.0F, 6.0F, pCubeDeformation), PartPose.offsetAndRotation(0.0F, 12.0F, -10.0F, ((float)Math.PI / 2F), 0.0F, 0.0F));
    partdefinition.addOrReplaceChild("tail1", CubeListBuilder.create().texOffs(0, 15).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 8.0F, 1.0F, pCubeDeformation), PartPose.offsetAndRotation(0.0F, 15.0F, 8.0F, 0.9F, 0.0F, 0.0F));
    partdefinition.addOrReplaceChild("tail2", CubeListBuilder.create().texOffs(4, 15).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 8.0F, 1.0F, pCubeDeformation), PartPose.offset(0.0F, 20.0F, 14.0F));
    CubeListBuilder cubelistbuilder = CubeListBuilder.create().texOffs(8, 13).addBox(-1.0F, 0.0F, 1.0F, 2.0F, 6.0F, 2.0F, pCubeDeformation);
    partdefinition.addOrReplaceChild("left_hind_leg", cubelistbuilder, PartPose.offset(1.1F, 18.0F, 5.0F));
    partdefinition.addOrReplaceChild("right_hind_leg", cubelistbuilder, PartPose.offset(-1.1F, 18.0F, 5.0F));
    CubeListBuilder cubelistbuilder1 = CubeListBuilder.create().texOffs(40, 0).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 10.0F, 2.0F, pCubeDeformation);
    partdefinition.addOrReplaceChild("left_front_leg", cubelistbuilder1, PartPose.offset(1.2F, 14.1F, -5.0F));
    partdefinition.addOrReplaceChild("right_front_leg", cubelistbuilder1, PartPose.offset(-1.2F, 14.1F, -5.0F));
    return meshdefinition;
  }

  public static LayerDefinition createBodyLayer() {
    return LayerDefinition.create(LavaCatModel.createBodyMesh(CubeDeformation.NONE), 64, 32);
  }

  @Override
  protected Iterable<ModelPart> headParts() {
    return ImmutableList.of();
  }

  @Override
  protected Iterable<ModelPart> bodyParts() {
    return ImmutableList.of(this.body, this.leftHindLeg, this.rightHindLeg, this.leftFrontLeg, this.rightFrontLeg, this.tail1, this.tail2, this.head);
  }

  /**
   * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
   * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how
   * "far" arms and legs can swing at most.
   */
  @Override
  public void setupAnim(@Nonnull T entityIn, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
    this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
    this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
    if (this.state != 3) {
      this.body.xRot = ((float)Math.PI / 2F);
      if (this.state == 2) {
        this.leftHindLeg.xRot = Mth.cos(pLimbSwing * 0.6662F) * pLimbSwingAmount;
        this.rightHindLeg.xRot = Mth.cos(pLimbSwing * 0.6662F + 0.3F) * pLimbSwingAmount;
        this.leftFrontLeg.xRot = Mth.cos(pLimbSwing * 0.6662F + (float)Math.PI + 0.3F) * pLimbSwingAmount;
        this.rightFrontLeg.xRot = Mth.cos(pLimbSwing * 0.6662F + (float)Math.PI) * pLimbSwingAmount;
        this.tail2.xRot = 1.7278761F + ((float)Math.PI / 10F) * Mth.cos(pLimbSwing) * pLimbSwingAmount;
      } else {
        this.leftHindLeg.xRot = Mth.cos(pLimbSwing * 0.6662F) * pLimbSwingAmount;
        this.rightHindLeg.xRot = Mth.cos(pLimbSwing * 0.6662F + (float)Math.PI) * pLimbSwingAmount;
        this.leftFrontLeg.xRot = Mth.cos(pLimbSwing * 0.6662F + (float)Math.PI) * pLimbSwingAmount;
        this.rightFrontLeg.xRot = Mth.cos(pLimbSwing * 0.6662F) * pLimbSwingAmount;
        if (this.state == 1) {
          this.tail2.xRot = 1.7278761F + ((float)Math.PI / 4F) * Mth.cos(pLimbSwing) * pLimbSwingAmount;
        } else {
          this.tail2.xRot = 1.7278761F + 0.47123894F * Mth.cos(pLimbSwing) * pLimbSwingAmount;
        }
      }
    }
  }

  /**
   * Used for easily adding entity-dependent animations. The second and third float params here are the same second
   * and third as in the setRotationAngles method.
   */
  @Override
  public void prepareMobModel(T entityocelot, float limbSwing, float limbSwingAmount, float partialTickTime) {
    this.body.y = 12.0F;
    this.body.z = -10.0F;
    this.head.y = 15.0F;
    this.head.z = -9.0F;
    this.tail1.y = 15.0F;
    this.tail1.z = 8.0F;
    this.tail2.y = 20.0F;
    this.tail2.z = 14.0F;
    this.leftFrontLeg.y = 14.1F;
    this.leftFrontLeg.z = -5.0F;
    this.rightFrontLeg.y = 14.1F;
    this.rightFrontLeg.z = -5.0F;
    this.leftHindLeg.y = 18.0F;
    this.leftHindLeg.z = 5.0F;
    this.rightHindLeg.y = 18.0F;
    this.rightHindLeg.z = 5.0F;
    this.tail1.xRot = 0.9F;
    this.body.xRot = 0;
    this.tail2.xRot = 0;
    this.leftFrontLeg.xRot = 0;
    this.rightFrontLeg.xRot = 0;
    this.leftHindLeg.xRot = 0;
    this.rightHindLeg.xRot = 0;

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
      this.body.xRot = ((float) Math.PI / 4F);
      this.body.y += -4.0F;
      this.body.z += 5.0F;
      this.head.y += -3.3F;
      ++this.head.z;
      this.tail1.y += 8.0F;
      this.tail1.z += -2.0F;
      this.tail2.y += 2.0F;
      this.tail2.z += -0.8F;
      this.tail1.xRot = 1.7278761F;
      this.tail2.xRot = 2.670354F;
      this.leftFrontLeg.xRot = -0.15707964F;
      this.leftFrontLeg.y = 15.8F;
      this.leftFrontLeg.z = -7.0F;
      this.rightFrontLeg.xRot = -0.15707964F;
      this.rightFrontLeg.y = 15.8F;
      this.rightFrontLeg.z = -7.0F;
      this.leftHindLeg.xRot = -((float) Math.PI / 2F);
      this.leftHindLeg.y = 21.0F;
      this.leftHindLeg.z = 1.0F;
      this.rightHindLeg.xRot = -((float) Math.PI / 2F);
      this.rightHindLeg.y = 21.0F;
      this.rightHindLeg.z = 1.0F;
      this.state = 3;
    } else {
      this.state = 1;
    }
  }
}