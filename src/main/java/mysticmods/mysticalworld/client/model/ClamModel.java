package mysticmods.mysticalworld.client.model;

import com.google.common.collect.ImmutableList;
import mysticmods.mysticalworld.entity.ClamEntity;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;

import javax.annotation.Nonnull;
import java.util.Collections;

public class ClamModel extends AgeableModel<ClamEntity> {

  public ModelRenderer Bottom;
  public ModelRenderer Top;
  public ModelRenderer Bottom_side2;
  public ModelRenderer shape3_1;
  public ModelRenderer shape2;
  public ModelRenderer shape1;
  public ModelRenderer Bottom_center;
  public ModelRenderer Bottom_side1;
  public ModelRenderer shape2_1;
  public ModelRenderer shape3;
  public ModelRenderer Bottom_side4;
  public ModelRenderer shape1_1;
  public ModelRenderer part22;
  public ModelRenderer shape3_2;
  public ModelRenderer shape1_3;
  public ModelRenderer shape2_2;
  public ModelRenderer shape3_3;
  public ModelRenderer shape2_3;
  public ModelRenderer shape1_2;
  public ModelRenderer Bottom_side3;


  public ClamModel() {
    this.texWidth= 64;
    this.texHeight = 32;
    this.Bottom_side4 = new ModelRenderer(this, 14, 21);
    this.Bottom_side4.setPos(0.0F, -0.5F, 0.0F);
    this.Bottom_side4.addBox(4.5F, -0.5F, -3.0F, 1.0F, 1.0F, 6.0F, 0.0F, 0.0F, 0.0F);
    this.shape1 = new ModelRenderer(this, 2, 20);
    this.shape1.setPos(-5.4F, -3.3F, -0.6F);
    this.shape1.addBox(0.0F, 0.0F, -4.0F, 1.0F, 3.0F, 9.0F, 0.0F, 0.0F, 0.0F);
    this.setRotateAngle(shape1, 0.05585053689605638F, -0.09075712609713217F, 0.001745329278001762F);
    this.shape1_1 = new ModelRenderer(this, 2, 20);
    this.shape1_1.setPos(5.4F, -3.3F, -0.6F);
    this.shape1_1.addBox(-1.0F, 0.0F, -4.0F, 1.0F, 3.0F, 9.0F, 0.0F, 0.0F, 0.0F);
    this.setRotateAngle(shape1_1, 0.05585053689605638F, 0.09075712609713217F, 0.0F);
    this.shape2_2 = new ModelRenderer(this, 1, 19);
    this.shape2_2.setPos(-3.0F, -3.3F, -0.6F);
    this.shape2_2.addBox(-0.5F, 0.0F, -4.0F, 1.0F, 3.0F, 10.0F, 0.0F, 0.0F, 0.0F);
    this.setRotateAngle(shape2_2, 0.05585053689605638F, -0.05235987755982988F, 0.0F);
    this.Bottom = new ModelRenderer(this, 25, 16);
    this.Bottom.setPos(0.0F, 20.25F, 0.0F);
    this.Bottom.addBox(-5.5F, -3.0F, -4.0F, 11.0F, 2.0F, 8.0F, 0.0F, 0.0F, 0.0F);
    this.setRotateAngle(Bottom, 3.0960395494600967F, 0.0F, 0.0F);
    this.shape3_1 = new ModelRenderer(this, 0, 18);
    this.shape3_1.setPos(1.0F, -3.3F, -0.6F);
    this.shape3_1.addBox(-0.5F, 0.0F, -4.0F, 1.0F, 3.0F, 11.0F, 0.0F, 0.0F, 0.0F);
    this.setRotateAngle(shape3_1, 0.05585053689605638F, 0.04363323129985824F, 0.0F);
    this.shape1_3 = new ModelRenderer(this, 2, 20);
    this.shape1_3.setPos(5.4F, -3.3F, -0.6F);
    this.shape1_3.addBox(-1.0F, 0.0F, -4.0F, 1.0F, 3.0F, 9.0F, 0.0F, 0.0F, 0.0F);
    this.setRotateAngle(shape1_3, 0.05585053689605638F, 0.09075712609713217F, 0.0F);
    this.Top = new ModelRenderer(this, 25, 15);
    this.Top.setPos(0.0F, -0.2F, 0.0F);
    this.Top.addBox(-5.5F, -3.0F, -4.0F, 11.0F, 3.0F, 8.0F, 0.0F, 0.0F, 0.0F);
    this.setRotateAngle(Top, -3.141592653589793F, 3.141592653589793F, 0.0F);
    this.shape2 = new ModelRenderer(this, 1, 19);
    this.shape2.setPos(-3.0F, -3.3F, -0.6F);
    this.shape2.addBox(-0.5F, 0.0F, -4.0F, 1.0F, 3.0F, 10.0F, 0.0F, 0.0F, 0.0F);
    this.setRotateAngle(shape2, 0.05585053689605638F, -0.05235987755982988F, 0.0F);
    this.Bottom_side3 = new ModelRenderer(this, 14, 21);
    this.Bottom_side3.setPos(0.0F, -0.5F, 0.0F);
    this.Bottom_side3.addBox(-5.5F, 0.0F, -3.0F, 1.0F, 1.0F, 6.0F, 0.0F, 0.0F, 0.0F);
    this.Bottom_center = new ModelRenderer(this, 0, 10);
    this.Bottom_center.setPos(0.0F, -0.4F, 0.0F);
    this.Bottom_center.addBox(-4.5F, -0.9F, -3.0F, 9.0F, 1.0F, 6.0F, 0.0F, 0.0F, 0.0F);
    this.setRotateAngle(Bottom_center, 0.03141592778425469F, 0.0F, 0.0F);
    this.shape1_2 = new ModelRenderer(this, 2, 20);
    this.shape1_2.setPos(-5.4F, -3.3F, -0.6F);
    this.shape1_2.addBox(0.0F, 0.0F, -4.0F, 1.0F, 3.0F, 9.0F, 0.0F, 0.0F, 0.0F);
    this.setRotateAngle(shape1_2, 0.05585053689605638F, -0.09075712609713217F, 0.001745329278001762F);
    this.Bottom_side2 = new ModelRenderer(this, 0, 4);
    this.Bottom_side2.setPos(0.0F, -0.5F, 7.0F);
    this.Bottom_side2.addBox(-5.5F, -0.5F, -4.0F, 11.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F);
    this.shape2_3 = new ModelRenderer(this, 1, 19);
    this.shape2_3.setPos(3.0F, -3.3F, -0.6F);
    this.shape2_3.addBox(-0.5F, 0.0F, -4.0F, 1.0F, 3.0F, 10.0F, 0.0F, 0.0F, 0.0F);
    this.setRotateAngle(shape2_3, 0.05585053689605638F, 0.03490658503988659F, 0.0F);
    this.shape2_1 = new ModelRenderer(this, 1, 19);
    this.shape2_1.setPos(3.0F, -3.3F, -0.6F);
    this.shape2_1.addBox(-0.5F, 0.0F, -4.0F, 1.0F, 3.0F, 10.0F, 0.0F, 0.0F, 0.0F);
    this.setRotateAngle(shape2_1, 0.05585053689605638F, 0.03490658503988659F, 0.0F);
    this.shape3 = new ModelRenderer(this, 0, 18);
    this.shape3.setPos(-1.0F, -3.3F, -0.6F);
    this.shape3.addBox(-0.5F, 0.0F, -4.0F, 1.0F, 3.0F, 11.0F, 0.0F, 0.0F, 0.0F);
    this.setRotateAngle(shape3, 0.05585053689605638F, -0.017453292519943295F, 0.0F);
    this.shape3_2 = new ModelRenderer(this, 0, 18);
    this.shape3_2.setPos(-1.0F, -3.3F, -0.6F);
    this.shape3_2.addBox(-0.5F, 0.0F, -4.0F, 1.0F, 3.0F, 11.0F, 0.0F, 0.0F, 0.0F);
    this.setRotateAngle(shape3_2, 0.05585053689605638F, -0.017453292519943295F, 0.0F);
    this.part22 = new ModelRenderer(this, 0, 0);
    this.part22.setPos(0.0F, 0.0F, 0.0F);
    this.part22.addBox(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F);
    this.Bottom_side1 = new ModelRenderer(this, 0, 7);
    this.Bottom_side1.setPos(0.0F, -0.5F, 0.0F);
    this.Bottom_side1.addBox(-5.5F, -0.5F, -4.0F, 11.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F);
    this.shape3_3 = new ModelRenderer(this, 0, 18);
    this.shape3_3.setPos(1.0F, -3.3F, -0.6F);
    this.shape3_3.addBox(-0.5F, 0.0F, -4.0F, 1.0F, 3.0F, 11.0F, 0.0F, 0.0F, 0.0F);
    this.setRotateAngle(shape3_3, 0.05585053689605638F, 0.04363323129985824F, 0.0F);
    this.Bottom.addChild(this.Bottom_side4);
    this.Bottom.addChild(this.shape1);
    this.Bottom.addChild(this.shape1_1);
    this.Top.addChild(this.shape2_2);
    this.Bottom.addChild(this.shape3_1);
    this.Top.addChild(this.shape1_3);
    this.Bottom.addChild(this.Top);
    this.Bottom.addChild(this.shape2);
    this.Bottom_side4.addChild(this.Bottom_side3);
    this.Bottom.addChild(this.Bottom_center);
    this.Top.addChild(this.shape1_2);
    this.Bottom.addChild(this.Bottom_side2);
    this.Top.addChild(this.shape2_3);
    this.Bottom.addChild(this.shape2_1);
    this.Bottom.addChild(this.shape3);
    this.Top.addChild(this.shape3_2);
    this.Bottom.addChild(this.part22);
    this.Bottom.addChild(this.Bottom_side1);
    this.Top.addChild(this.shape3_3);

  }

  @Override
  protected Iterable<ModelRenderer> headParts() {
    return Collections.emptyList();
  }

  @Override
  protected Iterable<ModelRenderer> bodyParts() {
    return ImmutableList.of(Bottom);
  }

  @Override
  public void setupAnim(ClamEntity pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
    this.Bottom.yRot = pEntity.yRot;
  }

  private void setRotateAngle(@Nonnull ModelRenderer model, float x, float y, float z) {
    model.xRot = x;
    model.yRot = y;
    model.zRot = z;
  }
}
