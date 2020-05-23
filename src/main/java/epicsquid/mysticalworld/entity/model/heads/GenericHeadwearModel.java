package epicsquid.mysticalworld.entity.model.heads;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.GenericHeadModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class GenericHeadwearModel extends GenericHeadModel {
  public ModelRenderer bipedHeadwear;

  public GenericHeadwearModel(int offsetX, int offsetY, int textureWidth, int textureHeight) {
    super(offsetX, offsetY, textureWidth, textureHeight);
    this.bipedHeadwear = new ModelRenderer(this, 32, 0);
    this.bipedHeadwear.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.5F);
    this.bipedHeadwear.setRotationPoint(0.0F, 0.0F, 0.0F);
  }

  @Override
  public void func_225603_a_(float p_225603_1_, float p_225603_2_, float p_225603_3_) {
    super.func_225603_a_(p_225603_1_, p_225603_2_, p_225603_3_);
    this.bipedHeadwear.rotateAngleX = this.field_217105_a.rotateAngleX;
    this.bipedHeadwear.rotateAngleY = this.field_217105_a.rotateAngleY;
    this.bipedHeadwear.rotateAngleZ = this.field_217105_a.rotateAngleZ;
    this.bipedHeadwear.rotationPointX = this.field_217105_a.rotationPointX;
    this.bipedHeadwear.rotationPointY = this.field_217105_a.rotationPointY;
    this.bipedHeadwear.rotationPointZ = this.field_217105_a.rotationPointZ;
  }

  @Override
  public void render(MatrixStack stack, IVertexBuilder vertex, int v, int v2, float v3, float v4, float v5, float v6) {
    super.render(stack, vertex, v, v2, v3, v4, v5, v6);
    this.bipedHeadwear.render(stack, vertex, v, v2, v3, v4, v5, v6);
  }
}
