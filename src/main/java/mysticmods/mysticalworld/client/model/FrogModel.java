package mysticmods.mysticalworld.client.model;

import com.google.common.collect.ImmutableSet;
import mysticmods.mysticalworld.entity.FrogEntity;
import net.minecraft.client.model.AgeableListModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

import javax.annotation.Nonnull;

/**
 * FrogModel - Elucent
 * Created using Tabula 5.1.0
 */
public class FrogModel extends AgeableListModel<FrogEntity> {

  private final ModelPart body;
  private final ModelPart backR;
  private final ModelPart backL;
  private final ModelPart frontR;
  private final ModelPart frontL;
  private final ModelPart head;

  public FrogModel(ModelPart pRoot) {
    super(true, 5.0f, 2.0f);
    this.body = pRoot.getChild("body");
    this.head = body.getChild("head");
    this.backL = body.getChild("back_left_leg");
    this.backR = body.getChild("back_right_leg");
    this.frontL = body.getChild("front_left_leg");
    this.frontR = body.getChild("front_right_leg");
  }

  public static LayerDefinition createBodyLayer() {
    MeshDefinition meshdefinition = new MeshDefinition();
    PartDefinition partdefinition = meshdefinition.getRoot();

    PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().create()
                    .texOffs(0, 9).addBox(-1.5F, -1.0F, -3.0F, 3.0F, 2.0F, 5.0F),
            PartPose.offsetAndRotation(0.0F, 22.0F, 0.0F, -0.5236F, 0.0F, 0.0F));

    PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create()
                    .texOffs(4, 3).addBox(-1.5F, -1.0F, -3.0F, 3.0F, 2.0F, 3.0F),
            PartPose.offsetAndRotation(0.0F, 0.0F, -3.0F, 0.3927F, 0.0F, 0.0F));

    PartDefinition backL = body.addOrReplaceChild("back_left_leg", CubeListBuilder.create()
                    .texOffs(0, 0).addBox(-1.0F, 0.0F, -0.5F, 2.0F, 4.0F, 1.0F),
            PartPose.offsetAndRotation(1.0F, 0.5F, 1.5F, -0.6981F, -0.5236F, -0.5236F));

    PartDefinition backR = body.addOrReplaceChild("back_right_leg", CubeListBuilder.create()
                    .texOffs(0, 0).mirror().addBox(-1.0F, 0.0F, -0.5F, 2.0F, 4.0F, 1.0F).mirror(false),
            PartPose.offsetAndRotation(-1.0F, 0.5F, 1.5F, -0.6981F, 0.5236F, 0.5236F));

    PartDefinition frontL = body.addOrReplaceChild("front_left_leg", CubeListBuilder.create()
                    .texOffs(0, 5).mirror().addBox(-0.5F, 0.0F, -0.5F, 1.0F, 4.0F, 1.0F).mirror(false),
            PartPose.offsetAndRotation(1.0F, -0.25F, -2.5F, 0.2618F, 0.0F, -0.2618F));

    PartDefinition frontR = body.addOrReplaceChild("front_right_leg", CubeListBuilder.create()
                    .texOffs(0, 5).mirror().addBox(-0.5F, 0.0F, -0.5F, 1.0F, 4.0F, 1.0F).mirror(false),
            PartPose.offsetAndRotation(-1.0F, -0.25F, -2.5F, 0.2618F, 0.0F, 0.2618F));

    return LayerDefinition.create(meshdefinition, 16, 16);
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
  public void setupAnim(@Nonnull FrogEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    this.backL.xRot = -0.6981317007977318F + 1.5f * entity.getOffGround(ageInTicks - (int) ageInTicks);
    this.backR.xRot = -0.6981317007977318F + 1.5f * entity.getOffGround(ageInTicks - (int) ageInTicks);
    this.head.xRot = headPitch * 0.017453292F;
    this.head.yRot = netHeadYaw * 0.017453292F;
  }
}