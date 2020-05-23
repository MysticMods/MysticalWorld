package epicsquid.mysticalworld.entity.model.heads;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.model.GenericHeadModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.ResourceLocation;

public class LayeredHeadModel extends GenericHeadModel {
  private final ResourceLocation secondLayer;
  private final ModelRenderer biggerHead;

  public LayeredHeadModel(ResourceLocation secondLayer, int offsetX, int offsetY, int width, int height) {
    super(offsetX, offsetY, width, height);
    this.secondLayer = secondLayer;
    this.textureWidth = width;
    this.textureHeight = height;
    this.biggerHead = new ModelRenderer(this, offsetX, offsetY);
    this.biggerHead.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.25F);
    this.biggerHead.setRotationPoint(0.0F, 0.0F, 0.0F);
  }

  @Override
  public void func_225603_a_(float a, float yaw, float pitch) {
    super.func_225603_a_(a, yaw, pitch);
    this.biggerHead.rotateAngleY = (float) (yaw * (Math.PI / 180));
    this.biggerHead.rotateAngleX = (float) (pitch * (Math.PI / 180));
  }

  @Override
  public void render(MatrixStack stack, IVertexBuilder vertex, int v, int v2, float v3, float v4, float v5, float v6) {
    super.render(stack, vertex, v, v2, v3, v4, v5, v6);
    Minecraft.getInstance().getRenderManager().textureManager.bindTexture(secondLayer);
    this.biggerHead.render(stack, vertex, v, v2, v3, v4, v5, v6);
  }
}
