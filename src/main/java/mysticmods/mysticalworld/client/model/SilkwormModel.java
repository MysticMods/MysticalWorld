package mysticmods.mysticalworld.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import mysticmods.mysticalworld.entity.SilkwormEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;

import java.util.Arrays;

public class SilkwormModel extends EntityModel<SilkwormEntity> {
  private static final int[][] BODY_SIZES = new int[][]{{4, 3, 2}, {6, 4, 5}, {3, 3, 1}, {1, 2, 1}};
  private static final int[][] BODY_TEXS = new int[][]{{0, 0}, {0, 5}, {0, 14}, {0, 18}};
  private static final int BODY_COUNT = BODY_SIZES.length;
  private final ModelPart[] bodyParts;

  public SilkwormModel(ModelPart pRoot) {
    this.bodyParts = new ModelPart[BODY_COUNT];

    Arrays.setAll(this.bodyParts, (index) -> {
      return pRoot.getChild(getSegmentName(index));
    });
  }

  private static String getSegmentName(int pIndex) {
		return "segment" + pIndex;
	}

  public static LayerDefinition createBodyLayer() {
	MeshDefinition meshdefinition = new MeshDefinition();
	PartDefinition partdefinition = meshdefinition.getRoot();
	float[] afloat = new float[BODY_COUNT];
	float f = -3.5F;

	for(int i = 0; i < BODY_COUNT; ++i) {
	  partdefinition.addOrReplaceChild(getSegmentName(i), CubeListBuilder.create()
			  .texOffs(BODY_TEXS[i][0], BODY_TEXS[i][1])
			  .addBox((float) BODY_SIZES[i][0] * -0.5F, 0.0F, (float) BODY_SIZES[i][2] * -0.5F, BODY_SIZES[i][0], BODY_SIZES[i][1], BODY_SIZES[i][2]),
			  PartPose.offset(0.0F, (float) (24 - BODY_SIZES[i][1]), f));
	  afloat[i] = f;
      if (i < BODY_COUNT - 1) {
      	f += (float) (BODY_SIZES[i][2] + BODY_SIZES[i + 1][2]) * 0.5F;
      }
	}

	return LayerDefinition.create(meshdefinition, 64, 32);
  }

  @Override
  public void setupAnim(SilkwormEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    for (int i = 0; i < this.bodyParts.length; ++i) {
      this.bodyParts[i].yRot = Mth.cos(ageInTicks * 0.25F + (float) i * 0.15F * (float) Math.PI) * (float) Math.PI * 0.01F * (float) (1 + Math.abs(i - 2));
      this.bodyParts[i].x = Mth.sin(ageInTicks * 0.25F + (float) i * 0.15F * (float) Math.PI) * (float) Math.PI * 0.1F * (float) Math.abs(i - 2);
    }
  }

  @Override
  public void renderToBuffer(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
    for (ModelPart p_228227_8_ : this.bodyParts) {
      p_228227_8_.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    }
  }
}