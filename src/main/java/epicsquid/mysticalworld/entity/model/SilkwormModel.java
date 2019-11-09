package epicsquid.mysticalworld.entity.model;

import epicsquid.mysticalworld.entity.SilkwormEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.util.math.MathHelper;

public class SilkwormModel extends EntityModel<SilkwormEntity> {
  private static final int[][] BODY_SIZES = new int[][]{{4, 3, 2}, {6, 4, 5}, {3, 3, 1}, {1, 2, 1}};
  private static final int[][] BODY_TEXS = new int[][]{{0, 0}, {0, 5}, {0, 14}, {0, 18}};
  private static final int BODY_COUNT = BODY_SIZES.length;
  private final RendererModel[] bodyParts;

  public SilkwormModel() {
    this.bodyParts = new RendererModel[BODY_COUNT];
    float f = -3.5F;

    for (int i = 0; i < this.bodyParts.length; ++i) {
      this.bodyParts[i] = new RendererModel(this, BODY_TEXS[i][0], BODY_TEXS[i][1]);
      this.bodyParts[i].addBox((float) BODY_SIZES[i][0] * -0.5F, 0.0F, (float) BODY_SIZES[i][2] * -0.5F, BODY_SIZES[i][0], BODY_SIZES[i][1], BODY_SIZES[i][2]);
      this.bodyParts[i].setRotationPoint(0.0F, (float) (24 - BODY_SIZES[i][1]), f);

      if (i < this.bodyParts.length - 1) {
        f += (float) (BODY_SIZES[i][2] + BODY_SIZES[i + 1][2]) * 0.5F;
      }
    }
  }

  @Override
  public void render(SilkwormEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
    this.setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);

    for (RendererModel modelrenderer : this.bodyParts) {
      modelrenderer.render(scale);
    }
  }

  @Override
  public void setRotationAngles(SilkwormEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
    for (int i = 0; i < this.bodyParts.length; ++i) {
      this.bodyParts[i].rotateAngleY = MathHelper.cos(ageInTicks * 0.25F + (float) i * 0.15F * (float) Math.PI) * (float) Math.PI * 0.01F * (float) (1 + Math.abs(i - 2));
      this.bodyParts[i].rotationPointX = MathHelper.sin(ageInTicks * 0.25F + (float) i * 0.15F * (float) Math.PI) * (float) Math.PI * 0.1F * (float) Math.abs(i - 2);
    }
  }
}
