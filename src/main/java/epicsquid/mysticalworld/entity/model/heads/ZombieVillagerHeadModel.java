package epicsquid.mysticalworld.entity.model.heads;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.GenericHeadModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class ZombieVillagerHeadModel extends GenericHeadModel {
  private ModelRenderer bipedHead;
  private ModelRenderer bipedHeadwear;
  private ModelRenderer hatStuff;

  public ZombieVillagerHeadModel() {
    super(0, 0, 64, 64);
    this.bipedHead = new ModelRenderer(this, 0, 0);
    this.bipedHead.setTextureOffset(0, 0).addBox(-4.0F, -10.0F, -4.0F, 8, 10, 8, 0);
    this.bipedHead.setTextureOffset(24, 0).addBox(-1.0F, -3.0F, -6.0F, 2, 4, 2, 0);
    this.bipedHeadwear = new ModelRenderer(this, 32, 0);
    this.bipedHeadwear.addBox(-4.0F, -10.0F, -4.0F, 8, 10, 8, 0.5F);
    this.hatStuff = new ModelRenderer(this);
    this.hatStuff.setTextureOffset(30, 47).addBox(-8.0F, -8.0F, -6.0F, 16, 16, 1, 0);
    this.hatStuff.rotateAngleX = -1.5707964F;
    this.bipedHeadwear.addChild(this.hatStuff);
  }

  @Override
  public void func_225603_a_(float a, float rotation, float c) {
    super.func_225603_a_(a, rotation, c);
    this.bipedHead.rotateAngleY = (float) (rotation * (Math.PI / 180));
    this.bipedHead.rotateAngleX = (float) (c * (Math.PI / 180));
    this.bipedHeadwear.rotateAngleY = (float) (rotation * (Math.PI / 180));
    this.bipedHeadwear.rotateAngleX = (float) (c * (Math.PI / 180));
  }

  @Override
  public void render(MatrixStack stack, IVertexBuilder vertex, int v, int v2, float v3, float v4, float v5, float v6) {
    super.render(stack, vertex, v, v2, v3, v4, v5, v6);
    this.bipedHeadwear.render(stack, vertex, v, v2, v3, v4, v5, v6);
  }
}
