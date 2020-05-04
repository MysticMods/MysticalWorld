package epicsquid.mysticalworld.entity.model.heads;

import net.minecraft.client.renderer.entity.model.GenericHeadModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class VillagerHeadModel extends GenericHeadModel {
  protected ModelRenderer villagerNose;
  protected ModelRenderer villagerHead;
  protected ModelRenderer unknown;
  protected ModelRenderer field_217152_f;

  public VillagerHeadModel(int textureWidth, int textureHeight, float scale) {
    this.textureWidth = textureWidth;
    this.textureHeight = textureHeight;
    this.villagerHead = (new ModelRenderer(this)).setTextureSize(textureWidth, textureHeight);
    this.villagerHead.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.villagerHead.setTextureOffset(0, 0).addCuboid(-4.0F, -10.0F, -4.0F, 8, 10, 8, scale);
    this.unknown = (new ModelRenderer(this)).setTextureSize(textureWidth, textureWidth);
    this.unknown.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.unknown.setTextureOffset(32, 0).addCuboid(-4.0F, -10.0F, -4.0F, 8, 10, 8, scale + 0.5F);
    this.villagerHead.addChild(this.unknown);
    this.field_217152_f = (new ModelRenderer(this)).setTextureSize(textureWidth, textureWidth);
    this.field_217152_f.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.field_217152_f.setTextureOffset(30, 47).addCuboid(-8.0F, -8.0F, -6.0F, 16, 16, 1, scale);
    this.field_217152_f.rotateAngleX = (-(float) Math.PI / 2F);
    this.unknown.addChild(this.field_217152_f);
    this.villagerNose = (new ModelRenderer(this)).setTextureSize(textureWidth, textureWidth);
    this.villagerNose.setRotationPoint(0.0F, -2.0F, 0.0F);
    this.villagerNose.setTextureOffset(24, 0).addCuboid(-1.0F, -1.0F, -6.0F, 2, 4, 2, scale);
    this.villagerHead.addChild(this.villagerNose);
  }

  @Override
  public void render(float a, float yaw, float pitch) {
    super.render(a, yaw, pitch);
    this.villagerHead.rotateAngleY = (float) (yaw * (Math.PI / 180));
    this.villagerHead.rotateAngleX = (float) (pitch * (Math.PI / 180));
  }
}
