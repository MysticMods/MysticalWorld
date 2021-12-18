package mysticmods.mysticalworld.client.model;

import com.google.common.collect.ImmutableSet;
import mysticmods.mysticalworld.entity.FrogEntity;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;

import javax.annotation.Nonnull;

/**
 * FrogModel - Elucent
 * Created using Tabula 5.1.0
 */
public class FrogModel extends AgeableModel<FrogEntity> {

  private final ModelRenderer body;
  private final ModelRenderer backR;
  private final ModelRenderer backL;
  private final ModelRenderer frontR;
  private final ModelRenderer frontL;
  private final ModelRenderer head;

  public FrogModel() {
    super(true, 5.0f, 2.0f);
    this.texWidth = 16;
    this.texHeight = 16;
    this.head = new ModelRenderer(this, 4, 3);
    this.head.setPos(0.0F, 0.0F, -3.0F);
    this.head.addBox(-1.5F, -1.0F, -3.0F, 3, 2, 3, 0.0F);
    this.setRotateAngle(head, 0.39269908169872414F, 0.0F, 0.0F);
    this.backL = new ModelRenderer(this, 0, 0);
    this.backL.setPos(1.0F, 0.5F, 1.5F);
    this.backL.addBox(-1.0F, 0.0F, -0.5F, 2, 4, 1, 0.0F);
    this.setRotateAngle(backL, -0.6981317007977318F, -0.5235987755982988F, -0.5235987755982988F);
    this.frontL = new ModelRenderer(this, 0, 5);
    this.frontL.mirror = true;
    this.frontL.setPos(1.0F, -0.25F, -2.5F);
    this.frontL.addBox(-0.5F, 0.0F, -0.5F, 1, 4, 1, 0.0F);
    this.setRotateAngle(frontL, 0.2617993877991494F, 0.0F, -0.2617993877991494F);
    this.body = new ModelRenderer(this, 0, 9);
    this.body.setPos(0.0F, 22.0F, 0.0F);
    this.body.addBox(-1.5F, -1.0F, -3.0F, 3, 2, 5, 0.0F);
    this.setRotateAngle(body, -0.5235987755982988F, 0.0F, 0.0F);
    this.backR = new ModelRenderer(this, 0, 0);
    this.backR.mirror = true;
    this.backR.setPos(-1.0F, 0.5F, 1.5F);
    this.backR.addBox(-1.0F, 0.0F, -0.5F, 2, 4, 1, 0.0F);
    this.setRotateAngle(backR, -0.6981317007977318F, 0.5235987755982988F, 0.5235987755982988F);
    this.frontR = new ModelRenderer(this, 0, 5);
    this.frontR.mirror = true;
    this.frontR.setPos(-1.0F, -0.25F, -2.5F);
    this.frontR.addBox(-0.5F, 0.0F, -0.5F, 1, 4, 1, 0.0F);
    this.setRotateAngle(frontR, 0.2617993877991494F, 0.0F, 0.2617993877991494F);
    this.body.addChild(this.head);
    this.body.addChild(this.backL);
    this.body.addChild(this.frontL);
    this.body.addChild(this.backR);
    this.body.addChild(this.frontR);
  }

  @Nonnull
  @Override
  protected Iterable<ModelRenderer> headParts() {
    return ImmutableSet.of();
  }

  @Nonnull
  @Override
  protected Iterable<ModelRenderer> bodyParts() {
    return ImmutableSet.of(body);
  }

  @Override
  public void setupAnim(@Nonnull FrogEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    this.backL.xRot = -0.6981317007977318F + 1.5f * entity.getOffGround(ageInTicks - (int) ageInTicks);
    this.backR.xRot = -0.6981317007977318F + 1.5f * entity.getOffGround(ageInTicks - (int) ageInTicks);
    this.head.xRot = headPitch * 0.017453292F;
    this.head.yRot = netHeadYaw * 0.017453292F;
  }

  /**
   * This is a helper function from Tabula to set the rotation of model parts
   */
  private void setRotateAngle(@Nonnull ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.xRot = x;
    modelRenderer.yRot = y;
    modelRenderer.zRot = z;
  }
}
