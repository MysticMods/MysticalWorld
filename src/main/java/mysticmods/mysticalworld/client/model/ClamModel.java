package mysticmods.mysticalworld.client.model;

import com.google.common.collect.ImmutableList;
import mysticmods.mysticalworld.entity.ClamEntity;
import net.minecraft.client.model.AgeableListModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

import java.util.Collections;

public class ClamModel extends AgeableListModel<ClamEntity> {
  public ModelPart Bottom;
  public ModelPart Top;
  public ModelPart part22;

  public ClamModel(ModelPart pRoot) {
    this.Bottom = pRoot.getChild("bottom");
    this.Top = Bottom.getChild("top");
    this.part22 = Bottom.getChild("part22");
  }

  public static LayerDefinition createBodyLayer() {
    MeshDefinition meshdefinition = new MeshDefinition();
    PartDefinition partdefinition = meshdefinition.getRoot();

    PartDefinition Bottom = partdefinition.addOrReplaceChild("bottom", CubeListBuilder.create()
            .texOffs(25, 16).addBox(-5.5F, -3.0F, -4.0F, 11.0F, 2.0F, 8.0F),
        PartPose.offsetAndRotation(0.0F, 20.25F, 0.0F, 3.096F, 0.0F, 0.0F));

    PartDefinition Bottom_side4 = Bottom.addOrReplaceChild("bottom_side4", CubeListBuilder.create()
            .texOffs(14, 21).addBox(4.5F, -0.5F, -3.0F, 1.0F, 1.0F, 6.0F),
        PartPose.offset(0.0F, -0.5F, 0.0F));

    PartDefinition Bottom_side3 = Bottom_side4.addOrReplaceChild("bottom_side3", CubeListBuilder.create()
            .texOffs(14, 21).addBox(-5.5F, 0.0F, -3.0F, 1.0F, 1.0F, 6.0F),
        PartPose.offset(0.0F, -0.5F, 0.0F));

    PartDefinition shape1 = Bottom.addOrReplaceChild("shape1", CubeListBuilder.create()
            .texOffs(2, 20).addBox(0.0F, 0.0F, -4.0F, 1.0F, 3.0F, 9.0F),
        PartPose.offsetAndRotation(-5.4F, -3.3F, -0.6F, 0.0559F, -0.0908F, 0.0017F));

    PartDefinition shape1_1 = Bottom.addOrReplaceChild("shape1_1", CubeListBuilder.create()
            .texOffs(2, 20).addBox(-1.0F, 0.0F, -4.0F, 1.0F, 3.0F, 9.0F),
        PartPose.offsetAndRotation(5.4F, -3.3F, -0.6F, 0.0559F, 0.0908F, 0.0F));

    PartDefinition shape3_1 = Bottom.addOrReplaceChild("shape3_1", CubeListBuilder.create()
            .texOffs(0, 18).addBox(-0.5F, 0.0F, -4.0F, 1.0F, 3.0F, 11.0F),
        PartPose.offsetAndRotation(1.0F, -3.3F, -0.6F, 0.0559F, 0.0436F, 0.0F));

    PartDefinition shape2 = Bottom.addOrReplaceChild("shape2", CubeListBuilder.create()
            .texOffs(1, 19).addBox(-0.5F, 0.0F, -4.0F, 1.0F, 3.0F, 10.0F),
        PartPose.offsetAndRotation(-3.0F, -3.3F, -0.6F, 0.0559F, -0.0524F, 0.0F));

    PartDefinition Bottom_center = Bottom.addOrReplaceChild("bottom_center", CubeListBuilder.create()
            .texOffs(0, 10).addBox(-4.5F, -0.9F, -3.0F, 9.0F, 1.0F, 6.0F),
        PartPose.offsetAndRotation(0.0F, -0.4F, 0.0F, 0.0314F, 0.0F, 0.0F));

    PartDefinition Bottom_side2 = Bottom.addOrReplaceChild("bottom_side2", CubeListBuilder.create()
            .texOffs(0, 4).addBox(-5.5F, -0.5F, -4.0F, 11.0F, 1.0F, 1.0F),
        PartPose.offset(0.0F, -0.5F, 7.0F));

    PartDefinition shape2_1 = Bottom.addOrReplaceChild("shape2_1", CubeListBuilder.create()
            .texOffs(1, 19).addBox(-0.5F, 0.0F, -4.0F, 1.0F, 3.0F, 10.0F),
        PartPose.offsetAndRotation(3.0F, -3.3F, -0.6F, 0.0559F, 0.0349F, 0.0F));

    PartDefinition shape3 = Bottom.addOrReplaceChild("shape3", CubeListBuilder.create()
            .texOffs(0, 18).addBox(-0.5F, 0.0F, -4.0F, 1.0F, 3.0F, 11.0F),
        PartPose.offsetAndRotation(-1.0F, -3.3F, -0.6F, 0.0559F, -0.0175F, 0.0F));

    PartDefinition part22 = Bottom.addOrReplaceChild("part22", CubeListBuilder.create()
            .texOffs(0, 0).addBox(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F),
        PartPose.offset(0.0F, 0.0F, 0.0F));

    PartDefinition Bottom_side1 = Bottom.addOrReplaceChild("bottom_side1", CubeListBuilder.create()
            .texOffs(0, 7).addBox(-5.5F, -0.5F, -4.0F, 11.0F, 1.0F, 1.0F),
        PartPose.offset(0.0F, -0.5F, 0.0F));

    PartDefinition Top = Bottom.addOrReplaceChild("top", CubeListBuilder.create()
            .texOffs(25, 15).addBox(-5.5F, -2.45F, 0.0F, 11.0F, 3.0F, 8.0F),
        PartPose.offsetAndRotation(0.0F, 0.25F, -4.0F, 0.0F, 0.0F, 3.1416F));

    PartDefinition shape2_2 = Top.addOrReplaceChild("shape2_2", CubeListBuilder.create()
            .texOffs(1, 19).addBox(-0.4954F, 19.9985F, -5.0299F, 1.0F, 3.0F, 10.0F),
        PartPose.offsetAndRotation(-3.0F, -23.0F, 3.4F, 0.0559F, -0.0524F, 0.0F));

    PartDefinition shape1_3 = Top.addOrReplaceChild("shape1_3", CubeListBuilder.create()
            .texOffs(2, 20).addBox(-1.008F, 19.9985F, -5.0301F, 1.0F, 3.0F, 9.0F),
        PartPose.offsetAndRotation(5.4F, -23.0F, 3.4F, 0.0559F, 0.0908F, 0.0F));

    PartDefinition shape1_2 = Top.addOrReplaceChild("shape1_2", CubeListBuilder.create()
            .texOffs(2, 20).addBox(0.0F, 0.0F, -4.0F, 1.0F, 3.0F, 9.0F),
        PartPose.offsetAndRotation(-5.4F, -2.75F, 3.4F, 0.0559F, -0.0908F, 0.0017F));

    PartDefinition shape2_3 = Top.addOrReplaceChild("shape2_3", CubeListBuilder.create()
            .texOffs(1, 19).addBox(-0.5F, 0.0F, -4.0F, 1.0F, 3.0F, 10.0F),
        PartPose.offsetAndRotation(3.0F, -2.75F, 3.4F, 0.0559F, 0.0349F, 0.0F));

    PartDefinition shape3_2 = Top.addOrReplaceChild("shape3_2", CubeListBuilder.create()
            .texOffs(0, 18).addBox(-0.5F, 0.0F, -4.0F, 1.0F, 3.0F, 11.0F),
        PartPose.offsetAndRotation(-1.0F, -2.75F, 3.4F, 0.0559F, -0.0175F, 0.0F));

    PartDefinition shape3_3 = Top.addOrReplaceChild("shape3_3", CubeListBuilder.create()
            .texOffs(0, 18).addBox(-0.5F, 0.0F, -4.0F, 1.0F, 3.0F, 11.0F),
        PartPose.offsetAndRotation(1.0F, -2.75F, 3.4F, 0.0559F, 0.0436F, 0.0F));

    return LayerDefinition.create(meshdefinition, 64, 32);
  }

  @Override
  protected Iterable<ModelPart> headParts() {
    return Collections.emptyList();
  }

  @Override
  protected Iterable<ModelPart> bodyParts() {
    return ImmutableList.of(Bottom);
  }

  @Override
  public void setupAnim(ClamEntity pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
    this.Bottom.yRot = pEntity.getYRot();
  }
}
