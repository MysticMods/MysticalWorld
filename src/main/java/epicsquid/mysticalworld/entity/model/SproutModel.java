package epicsquid.mysticalworld.entity.model;

import com.google.common.collect.ImmutableList;
import epicsquid.mysticalworld.entity.SproutEntity;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.Vec3d;

public class SproutModel extends AgeableModel<SproutEntity> {
  private ModelRenderer head;
  private ModelRenderer legL;
  private ModelRenderer legR;
  private ModelRenderer leafTop;
  private ModelRenderer leafBottom;

  public SproutModel() {
    super(true, 5.0f, 2.0f);
    textureWidth = 32;
    textureHeight = 32;

    head = new ModelRenderer(this, 12, 0);
    head.addBox(-2.5F, 0F, -2.5F, 5, 5, 5);
    head.setRotationPoint(0F, 11F, 0F);
    head.setTextureSize(32, 32);
    head.mirror = true;
    setRotation(head, 0F, 0F, 0F);
    legL = new ModelRenderer(this, 0, 0);
    legL.addBox(-1F, 0F, -1F, 2, 8, 2);
    legL.setRotationPoint(1.5F, 16F, 0F);
    legL.setTextureSize(32, 32);
    legL.mirror = true;
    setRotation(legL, 0F, 0F, 0F);
    legR = new ModelRenderer(this, 0, 0);
    legR.addBox(-1F, 0F, -1F, 2, 8, 2);
    legR.setRotationPoint(-1.5F, 16F, 0F);
    legR.setTextureSize(32, 32);
    legR.mirror = true;
    setRotation(legR, 0F, 0F, 0F);
    leafTop = new ModelRenderer(this, 8, 0);
    leafTop.addBox(-0.5F, 0F, -0.5F, 1, 2, 1);
    leafTop.setRotationPoint(0F, 9F, -1F);
    leafTop.setTextureSize(32, 32);
    leafTop.mirror = true;
    setRotation(leafTop, 0F, 0F, 0F);
    leafBottom = new ModelRenderer(this, 0, 10);
    leafBottom.addBox(-1.5F, -0.5F, -0.5F, 3, 4, 1);
    leafBottom.setRotationPoint(0F, 9F, -1F);
    leafBottom.setTextureSize(32, 32);
    leafBottom.mirror = true;
    setRotation(leafBottom, 1.963495F, 0.5235988F, 0F);
  }

  @Override
  protected Iterable<ModelRenderer> getHeadParts() {
    return ImmutableList.of();
  }

  @Override
  protected Iterable<ModelRenderer> getBodyParts() {
    return ImmutableList.of(head, leafTop, leafBottom, legL, legR);
  }

  @Override
  public void setRotationAngles(SproutEntity entity, float f, float f1, float age, float f3, float f4) {
    float speed = (float) Math.min(0.25f, ((new Vec3d(entity.getMotion().x, 0, entity.getMotion().z)).length() * 4.0f));
    legL.rotateAngleX = -(float) Math.toRadians(speed * 240f * (float) Math.sin(Math.toRadians(age % 360) * 24F));
    legR.rotateAngleX = (float) Math.toRadians(speed * 240f * (float) Math.sin(Math.toRadians(age % 360) * 24F));
  }

  private void setRotation(ModelRenderer model, float x, float y, float z) {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
}
