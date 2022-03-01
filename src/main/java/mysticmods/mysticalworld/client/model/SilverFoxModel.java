package mysticmods.mysticalworld.client.model;

import com.google.common.collect.ImmutableSet;
import mysticmods.mysticalworld.entity.SilverFoxEntity;
import net.minecraft.client.model.AgeableListModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

import javax.annotation.Nonnull;

/**
 * FoxModel - Elucent
 * Created using Tabula 5.1.0
 */
public class SilverFoxModel extends AgeableListModel<SilverFoxEntity> {
  private final ModelPart body1;
  private final ModelPart frontL;
  private final ModelPart frontR;
  private final ModelPart body2;
  private final ModelPart neck;
  private final ModelPart backL;
  private final ModelPart backR;
  private final ModelPart tail1;
  private final ModelPart tail2;
  private final ModelPart tail3;
  private final ModelPart tail4;
  private final ModelPart head;
  private final ModelPart snout;
  private final ModelPart earR;
  private final ModelPart earL;

  public SilverFoxModel(ModelPart pRoot) {
    super(true, 5.0f, 2.0f);

    this.body1 = pRoot.getChild("body1");
    this.neck = body1.getChild("neck");
    this.head = body1.getChild("head");
    this.earR = head.getChild("ear_right");
    this.earL = head.getChild("ear_left");
    this.snout = head.getChild("snout");
    this.frontL = body1.getChild("front_left_leg");
    this.frontR = body1.getChild("front_right_leg");
    this.body2 = body1.getChild("body2");
    this.backL = body2.getChild("back_left_leg");
    this.backR = body2.getChild("back_right_leg");
    this.tail1 = body2.getChild("tail1");
    this.tail2 = tail1.getChild("tail2");
    this.tail3 = tail2.getChild("tail3");
    this.tail4 = tail3.getChild("tail4");
  }

  public static LayerDefinition createBodyLayer() {
    MeshDefinition meshdefinition = new MeshDefinition();
    PartDefinition partdefinition = meshdefinition.getRoot();

    PartDefinition body1 = partdefinition.addOrReplaceChild("body1", CubeListBuilder.create()
                    .texOffs(12, 8).addBox(-2.5F, 0.0F, 0.0F, 5, 4, 5),
            PartPose.offset(0.0F, 15.5F, -2.0F));

    PartDefinition neck = body1.addOrReplaceChild("neck", CubeListBuilder.create()
                    .texOffs(16, 0).addBox(-2.0F, -2.0F, -4.0F, 4, 4, 4),
            PartPose.offsetAndRotation(0.0F, 1.5F, 2.0F, -0.2617993877991494F, 0.0F, 0.0F));

    PartDefinition head = body1.addOrReplaceChild("head", CubeListBuilder.create()
                    .texOffs(0, 5).addBox(-2.5F, -2.0F, -3.0F, 5, 4, 3),
            PartPose.offsetAndRotation(0.0F, 0.0F, -4.0F, 0.2617993877991494F, 0.0F, 0.0F));

    CubeListBuilder earCubeList = CubeListBuilder.create()
            .texOffs(0, 12).addBox(-1.0F, -3.0F, -0.5F, 2, 3, 1);
    PartDefinition earR = head.addOrReplaceChild("ear_right", earCubeList,
            PartPose.offsetAndRotation(-1.5F, -1.0F, -1.0F, 0.0F, 0.2617993877991494F, -0.5235987755982988F));
    PartDefinition earL = head.addOrReplaceChild("ear_left", earCubeList.mirror(),
            PartPose.offsetAndRotation(1.5F, -1.0F, -1.0F, 0.0F, -0.2617993877991494F, 0.5235987755982988F));

    PartDefinition snout = head.addOrReplaceChild("snout", CubeListBuilder.create()
                    .texOffs(0, 0).addBox(-1.5F, 0.0F, -3.0F, 3, 2, 3),
            PartPose.offsetAndRotation(0.0F, 0.0F, -3.0F, 0.17453292519943295F, 0.0F, 0.0F));

    CubeListBuilder legCubeList = CubeListBuilder.create()
            .texOffs(0, 16).addBox(-1.0F, 0.0F, -1.0F, 2, 6, 2);
    PartDefinition frontL = body1.addOrReplaceChild("front_left_leg", legCubeList.mirror(),
            PartPose.offset(1.0F, 4.0F, 1.0F));
    PartDefinition frontR = body1.addOrReplaceChild("front_right_leg", legCubeList,
            PartPose.offset(-1.0F, 4.0F, 1.0F));

    PartDefinition body2 = body1.addOrReplaceChild("body2", CubeListBuilder.create()
                    .texOffs(16, 17).addBox(-2.0F, 0.0F, 0.0F, 4, 4, 3),
            PartPose.offset(0.0F, 0.0F, 5.0F));

    PartDefinition backL = body2.addOrReplaceChild("back_left_leg", legCubeList.mirror(),
            PartPose.offset(1.0F, 4.0F, 1.5F));
    PartDefinition backR = body2.addOrReplaceChild("back_right_leg", legCubeList,
            PartPose.offset(-1.0F, 4.0F, 1.5F));

    PartDefinition tail1 = body2.addOrReplaceChild("tail1", CubeListBuilder.create()
                    .texOffs(12, 0).addBox(-1.0F, 0.0F, -1.0F, 2, 2, 2),
            PartPose.offsetAndRotation(0.0F, 1.0F, 2.5F, 1.1780972450961724F, 0.0F, 0.0F));

    PartDefinition tail2 = tail1.addOrReplaceChild("tail2", CubeListBuilder.create()
                    .texOffs(16, 24).addBox(-1.5F, 0.0F, -1.5F, 3, 3, 3),
            PartPose.offsetAndRotation(0.0F, 1.5F, 0.0F, -0.39269908169872414F, 0.0F, 0.0F));

    PartDefinition tail3 = tail2.addOrReplaceChild("tail3", CubeListBuilder.create()
                    .texOffs(0, 24).addBox(-2.0F, 0.0F, -2.0F, 4, 4, 4),
            PartPose.offset(0.0F, 3.0F, 0.0F));

    PartDefinition tail4 = tail3.addOrReplaceChild("tail4", CubeListBuilder.create()
                    .texOffs(8, 18).addBox(-1.0F, 0.0F, -1.0F, 2, 2, 2),
            PartPose.offsetAndRotation(0.0F, 3.5F, 0.0F, 0.39269908169872414F, 0.0F, 0.0F));

    return LayerDefinition.create(meshdefinition, 32, 32);
  }

  @Nonnull
  @Override
  protected Iterable<ModelPart> headParts() {
    return ImmutableSet.of();
  }

  @Nonnull
  @Override
  protected Iterable<ModelPart> bodyParts() {
    return ImmutableSet.of(body1);
  }

  private float getBobble(float deg, float ageInTicks) {
    return (float) Math.sin(ageInTicks * 0.03125f * (Math.PI * 2.0f) + Math.toRadians(deg));
  }

  @Override
  public void setupAnim(@Nonnull SilverFoxEntity silverFox, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    float sin = (float) Math.sin(ageInTicks * 0.125f * (Math.PI * 2.0f));
    float cos = (float) Math.cos(ageInTicks * 0.0625f * (Math.PI * 2.0f));
    if (!silverFox.isInSittingPose()) {
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
  public void prepareMobModel(@Nonnull SilverFoxEntity silverFox, float limbSwing, float limbSwingAmount, float partialTickTime) {

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

    super.prepareMobModel(silverFox, limbSwing, limbSwingAmount, partialTickTime);
  }

  /**
   * This is a helper function from Tabula to set the rotation of model parts
   */
  private void setRotateAngle(@Nonnull ModelPart ModelRenderer, float x, float y, float z) {
    ModelRenderer.xRot = x;
    ModelRenderer.yRot = y;
    ModelRenderer.zRot = z;
  }
}