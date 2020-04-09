package epicsquid.mysticalworld.entity.model.heads;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.model.GenericHeadModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.util.ResourceLocation;

public class LayeredHeadModel extends GenericHeadModel {
  private final ResourceLocation secondLayer;
  private final RendererModel biggerHead;

  public LayeredHeadModel(ResourceLocation secondLayer, int offsetX, int offsetY, int width, int height) {
    super(offsetX, offsetY, width, height);
    this.secondLayer = secondLayer;
    this.textureWidth = width;
    this.textureHeight = height;
    this.biggerHead = new RendererModel(this, offsetX, offsetY);
    this.biggerHead.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.25F);
    this.biggerHead.setRotationPoint(0.0F, 0.0F, 0.0F);
  }

  @Override
  public void func_217104_a(float progress, float a, float b, float rotation, float c, float scale) {
    super.func_217104_a(progress, a, b, rotation, c, scale);
    this.biggerHead.rotateAngleY = (float) (rotation * (Math.PI / 180));
    this.biggerHead.rotateAngleX = (float) (c * (Math.PI / 180));
    Minecraft.getInstance().getRenderManager().textureManager.bindTexture(secondLayer);
    this.biggerHead.render(scale);
  }
}
