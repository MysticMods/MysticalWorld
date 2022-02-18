package mysticmods.mysticalworld.client.model.armor;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.EquipmentSlot;
import noobanidus.libs.noobutil.client.model.ArmorBaseModel;

public class BeetleMaskModel extends ArmorBaseModel {
  public ModelPart mask;
  public ModelPart horn;
  public ModelPart maskBottom;
  public ModelPart strap;
  public ModelPart horn2;

  public BeetleMaskModel() {
    super(EquipmentSlot.HEAD, 0.0f, 1.0f, 64, 96);
    this.texWidth = 64;
    this.texHeight = 96;
    this.horn = new ModelPart(this, 1, 76);
    this.horn.setPos(0.0F, -8.5F, -4.5F);
    this.horn.addBox(-1.0F, -7.0F, 0.0F, 2, 7, 2, 0.0F);
    this.setRotateAngle(horn, -0.3141592653589793F, 0.0F, 0.0F);
    this.strap = new ModelPart(this, 15, 82);
    this.strap.setPos(0.0F, -4.0F, -2.5F);
    this.strap.addBox(-4.5F, -1.0F, 0.0F, 9, 2, 7, 0.0F);
    this.maskBottom = new ModelPart(this, 22, 64);
    this.maskBottom.setPos(0.0F, 0.5F, -3.5F);
    this.maskBottom.addBox(-2.5F, 0.0F, -1.0F, 5, 4, 2, 0.0F);
    this.mask = new ModelPart(this, 0, 64);
    this.mask.setPos(0.0F, 0.0F, 0.0F);
    this.mask.addBox(-4.5F, -8.5F, -4.5F, 9, 9, 2, 0.0F);
    this.horn2 = new ModelPart(this, 0, 85);
    this.horn2.setPos(0.0F, -6.0F, 2.0F);
    this.horn2.addBox(-1.0F, -1.0F, 0.0F, 2, 2, 3, 0.0F);
    this.mask.addChild(this.horn);
    this.mask.addChild(this.strap);
    this.mask.addChild(this.maskBottom);
    this.horn.addChild(this.horn2);
  }

  @Override
  public void renderToBuffer(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
    head = mask;
    super.renderToBuffer(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
  }

  public void setRotateAngle(ModelPart modelRenderer, float x, float y, float z) {
    modelRenderer.xRot = x;
    modelRenderer.yRot = y;
    modelRenderer.zRot = z;
  }
}

