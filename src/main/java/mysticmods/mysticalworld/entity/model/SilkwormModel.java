package mysticmods.mysticalworld.entity.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import mysticmods.mysticalworld.entity.SilkwormEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class SilkwormModel extends EntityModel<SilkwormEntity> {
  private static final int[][] BODY_SIZES = new int[][]{{4, 3, 2}, {6, 4, 5}, {3, 3, 1}, {1, 2, 1}};
  private static final int[][] BODY_TEXS = new int[][]{{0, 0}, {0, 5}, {0, 14}, {0, 18}};
  private static final int BODY_COUNT = BODY_SIZES.length;
  private final ModelRenderer[] bodyParts;

  public SilkwormModel() {
    this.bodyParts = new ModelRenderer[BODY_COUNT];
    float f = -3.5F;

    for (int i = 0; i < this.bodyParts.length; ++i) {
      this.bodyParts[i] = new ModelRenderer(this, BODY_TEXS[i][0], BODY_TEXS[i][1]);
      this.bodyParts[i].addBox((float) BODY_SIZES[i][0] * -0.5F, 0.0F, (float) BODY_SIZES[i][2] * -0.5F, BODY_SIZES[i][0], BODY_SIZES[i][1], BODY_SIZES[i][2]);
      this.bodyParts[i].setRotationPoint(0.0F, (float) (24 - BODY_SIZES[i][1]), f);

      if (i < this.bodyParts.length - 1) {
        f += (float) (BODY_SIZES[i][2] + BODY_SIZES[i + 1][2]) * 0.5F;
      }
    }
  }

  @Override
  public void setRotationAngles(SilkwormEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    for (int i = 0; i < this.bodyParts.length; ++i) {
      this.bodyParts[i].rotateAngleY = MathHelper.cos(ageInTicks * 0.25F + (float) i * 0.15F * (float) Math.PI) * (float) Math.PI * 0.01F * (float) (1 + Math.abs(i - 2));
      this.bodyParts[i].rotationPointX = MathHelper.sin(ageInTicks * 0.25F + (float) i * 0.15F * (float) Math.PI) * (float) Math.PI * 0.1F * (float) Math.abs(i - 2);
    }
  }

  @Override
  public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
    for (ModelRenderer p_228227_8_ : this.bodyParts) {
      p_228227_8_.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    }
  }
}
