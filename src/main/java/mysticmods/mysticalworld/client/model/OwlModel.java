package mysticmods.mysticalworld.client.model;

import com.google.common.collect.ImmutableSet;
import mysticmods.mysticalworld.entity.OwlEntity;
import net.minecraft.client.model.AgeableListModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;

public class OwlModel extends AgeableListModel<OwlEntity> {
  private final ModelPart body;
  private final ModelPart footR;
  private final ModelPart footL;
  private final ModelPart wingR1;
  private final ModelPart wingR2;
  private final ModelPart wingL1;
  private final ModelPart wingL2;
  private final ModelPart tail;
  private final ModelPart head;
  private final ModelPart tuftR;
  private final ModelPart tuftL;
  private State state = State.STANDING;

  public OwlModel() {
    super(true, 5.0f, 2.0f);
    texWidth = 64;
    texHeight = 64;

    body = new ModelPart(this);
    body.setPos(0.0F, 24.0F, 0.0F);
    body.texOffs(0, 0).addBox(-4.0F, -13.0F, -4.0F, 8.0F, 9.0F, 8.0F, 0.0F, false);

    footR = new ModelPart(this);
    footR.setPos(-1.5F, -4.0F, -0.5F);
    body.addChild(footR);
    footR.texOffs(0, 43).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 2.0F, 3.0F, 0.0F, false);
    footR.texOffs(26, 48).addBox(-1.0F, 2.0F, 0.5F, 2.0F, 2.0F, 0.0F, 0.0F, false);
    footR.texOffs(0, 48).addBox(-1.0F, 4.0F, -1.5F, 2.0F, 0.0F, 2.0F, 0.0F, false);

    footL = new ModelPart(this);
    footL.setPos(1.5F, -4.0F, -0.5F);
    body.addChild(footL);
    footL.texOffs(12, 43).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 2.0F, 3.0F, 0.0F, false);
    footL.texOffs(22, 48).addBox(-1.0F, 2.0F, 0.5F, 2.0F, 2.0F, 0.0F, 0.0F, false);
    footL.texOffs(8, 48).addBox(-1.0F, 4.0F, -1.5F, 2.0F, 0.0F, 2.0F, 0.0F, false);

    wingR1 = new ModelPart(this);
    wingR1.setPos(-4.0F, -13.0F, -1.0F);
    body.addChild(wingR1);
    wingR1.texOffs(16, 17).addBox(-1.0F, 0.0F, -2.0F, 1.0F, 7.0F, 7.0F, 0.0F, false);

    wingR2 = new ModelPart(this);
    wingR2.setPos(-1.0F, 7.0F, -2.0F);
    wingR1.addChild(wingR2);
    setRotationAngle(wingR2, 0.1745F, 0.0349F, -0.1745F);
    wingR2.texOffs(14, 31).addBox(0.0F, 0.0F, 0.0F, 1.0F, 6.0F, 6.0F, 0.0F, false);

    wingL1 = new ModelPart(this);
    wingL1.setPos(4.0F, -13.0F, -1.0F);
    body.addChild(wingL1);
    wingL1.texOffs(0, 17).addBox(0.0F, 0.0F, -2.0F, 1.0F, 7.0F, 7.0F, 0.0F, false);

    wingL2 = new ModelPart(this);
    wingL2.setPos(1.0F, 7.0F, -2.0F);
    wingL1.addChild(wingL2);
    setRotationAngle(wingL2, 0.1745F, -0.0349F, 0.1745F);
    wingL2.texOffs(0, 31).addBox(-1.0F, 0.0F, 0.0F, 1.0F, 6.0F, 6.0F, 0.0F, false);

    tail = new ModelPart(this);
    tail.setPos(0.0F, -5.0F, 4.0F);
    body.addChild(tail);
    setRotationAngle(tail, 0.5236F, 0.0F, 0.0F);
    tail.texOffs(28, 31).addBox(-3.0F, 0.0F, -2.0F, 6.0F, 4.0F, 2.0F, 0.0F, false);

    head = new ModelPart(this);
    head.setPos(0.0F, -13.0F, 0.0F);
    body.addChild(head);
    head.texOffs(32, 0).addBox(-3.5F, -6.0F, -3.5F, 7.0F, 6.0F, 7.0F, 0.0F, false);
    head.texOffs(16, 48).addBox(-1.0F, -3.0F, -4.5F, 2.0F, 2.0F, 1.0F, 0.0F, false);

    tuftR = new ModelPart(this);
    tuftR.setPos(-2.0F, -6.0F, -3.0F);
    head.addChild(tuftR);
    setRotationAngle(tuftR, 0.4363F, -0.3491F, 0.0F);
    tuftR.texOffs(24, 43).addBox(-2.0F, 0.0F, 0.0F, 2.0F, 0.0F, 4.0F, 0.0F, false);

    tuftL = new ModelPart(this);
    tuftL.setPos(2.0F, -6.0F, -3.0F);
    head.addChild(tuftL);
    setRotationAngle(tuftL, 0.4363F, 0.3491F, 0.0F);
    tuftL.texOffs(36, 43).addBox(0.0F, 0.0F, 0.0F, 2.0F, 0.0F, 4.0F, 0.0F, false);
  }

  @Nonnull
  @Override
  protected Iterable<ModelPart> headParts() {
    return ImmutableSet.of();
  }

  @Nonnull
  @Override
  protected Iterable<ModelPart> bodyParts() {
    return ImmutableSet.of(body);
  }

  @Override
  public void setupAnim(OwlEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    this.head.xRot = headPitch * 0.017453292F;
    this.head.yRot = netHeadYaw * 0.017453292F;
    this.head.zRot = 0.0F;

    boolean hootfire = false;
    if (entityIn.hasCustomName() && entityIn.getCustomName() != null && entityIn.getCustomName().getString().equals("Hootfire")) {
      hootfire = true;
    }

    if (this.state == State.FLYING && !hootfire) {
      setRotationAngle(this.body, 0.45f, 0, 0);
      setRotationAngle(this.footR, 0.25f, 0, 0);
      setRotationAngle(this.footL, 0.25f, 0, 0);
      setRotationAngle(this.tail, 0.15f, 0, 0);
      setRotationAngle(this.head, -0.3f, 0, 0);
      float wingR_rotation = -(0.65f * (float) Math.sin(ageInTicks) - 2.5f * 0.65f);
      float wingL_rotation = 0.65f * (float) Math.sin(ageInTicks) - 2.5f * 0.65f;
      setRotationAngle(this.wingR1, 0.45f, 0, wingR_rotation);
      setRotationAngle(this.wingL1, 0.45f, 0, wingL_rotation);
      setRotationAngle(this.wingR2, 0.45f, 0, 0.05f * wingR_rotation);
      setRotationAngle(this.wingL2, 0.45f, 0, 0.05f * wingL_rotation);
    } else {
      setRotationAngle(this.body, 0, 0, 0);
      setRotationAngle(this.footR, 0, 0, 0);
      setRotationAngle(this.footL, 0, 0, 0);
      setRotationAngle(tail, 0.5236F, 0.0F, 0.0F);
      setRotationAngle(this.head, 0, 0, 0);
      setRotationAngle(wingR1, 0, 0, 0);
      setRotationAngle(wingR2, 0.1745F, 0.0349F, -0.1745F);
      setRotationAngle(wingL1, 0, 0, 0);
      setRotationAngle(wingL2, 0.1745F, -0.0349F, 0.1745F);
    }
  }

  @Override
  public void prepareMobModel(OwlEntity owl, float limbSwing, float limbSwingAmount, float partialTickTime) {
    if (owl.isFlying()) {
      this.state = State.FLYING;
    } else {
      this.state = State.STANDING;
    }
  }

  public void setRotationAngle(ModelPart modelRenderer, float x, float y, float z) {
    modelRenderer.xRot = x;
    modelRenderer.yRot = y;
    modelRenderer.zRot = z;
  }

  @OnlyIn(Dist.CLIENT)
  public enum State {
    FLYING,
    STANDING,
    SITTING
  }
}