package mysticmods.mysticalworld.entity.model.armor;// Made with Blockbench 4.0.1
// Exported for Minecraft version 1.15 - 1.16 with Mojang mappings
// Paste this class into your mod and generate all required imports

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.inventory.EquipmentSlotType;
import noobanidus.libs.noobutil.client.model.ArmorBaseModel;

public class BeetleArmorModel extends ArmorBaseModel {
  private final ModelRenderer helmAnchor;
  private final ModelRenderer bodyAnchor;
  private final ModelRenderer armLAnchor;
  private final ModelRenderer armRAnchor;
  private final ModelRenderer legLAnchor;
  private final ModelRenderer legL;
  private final ModelRenderer bootL;
  private final ModelRenderer legRAnchor;
  private final ModelRenderer legR;
  private final ModelRenderer bootR;

  public BeetleArmorModel(EquipmentSlotType slot) {
    super(slot, 0.0f, 1.0f, 64, 64);

    helmAnchor = new ModelRenderer(this);
    helmAnchor.setPos(0.0F, -2.0F, 1.0F);
    helmAnchor.texOffs(0, 0).addBox(-4.5F, -6.0F, -6.0F, 9.0F, 9.0F, 2.0F, 0.0F, false);
    helmAnchor.texOffs(6, 11).addBox(3.25F, -2.0F, -4.0F, 1.0F, 2.0F, 7.0F, 0.0F, false);
    helmAnchor.texOffs(0, 30).addBox(-4.25F, -2.0F, 2.25F, 8.0F, 2.0F, 1.0F, 0.0F, false);
    helmAnchor.texOffs(0, 11).addBox(-2.5F, 3.0F, -6.0F, 5.0F, 4.0F, 1.0F, 0.0F, false);

    ModelRenderer top_horn_piece_r1 = new ModelRenderer(this);
    top_horn_piece_r1.setPos(0.0F, -9.25F, -2.75F);
    helmAnchor.addChild(top_horn_piece_r1);
    setRotationAngle(top_horn_piece_r1, -0.3927F, 0.0F, 0.0F);
    top_horn_piece_r1.texOffs(0, 25).addBox(-1.0F, -2.75F, 0.25F, 2.0F, 2.0F, 3.0F, 0.0F, false);
    top_horn_piece_r1.texOffs(0, 16).addBox(-1.0F, -2.75F, -1.75F, 2.0F, 7.0F, 2.0F, 0.0F, false);

    ModelRenderer right_strap_r1 = new ModelRenderer(this);
    right_strap_r1.setPos(-3.75F, -1.0F, -0.5F);
    helmAnchor.addChild(right_strap_r1);
    setRotationAngle(right_strap_r1, 0.0F, 0.0F, -3.1416F);
    right_strap_r1.texOffs(6, 11).addBox(-0.5F, -1.0F, -3.5F, 1.0F, 2.0F, 7.0F, 0.0F, false);

    bodyAnchor = new ModelRenderer(this);
    //bodyAnchor.setPos(-2.0F, 5.0F, -2.0F);
    bodyAnchor.texOffs(0, 33).addBox(-2.5F, -4.0F, -1.5F, 9.0F, 6.0F, 1.0F, 0.0F, false);
    bodyAnchor.texOffs(0, 40).addBox(-1.0F, 2.0F, -1.0F, 6.0F, 5.0F, 1.0F, 0.0F, false);
    bodyAnchor.texOffs(0, 53).addBox(-2.5F, -4.0F, 4.0F, 9.0F, 10.0F, 1.0F, 0.0F, false);
    bodyAnchor.texOffs(8, 20).addBox(3.75F, -4.75F, -0.25F, 2.0F, 1.0F, 4.0F, 0.0F, false);
    bodyAnchor.texOffs(0, 46).addBox(-2.25F, 3.0F, -0.25F, 8.0F, 2.0F, 4.0F, 0.0F, false);
    bodyAnchor.texOffs(0, 46).addBox(-2.25F, -3.0F, -0.25F, 8.0F, 2.0F, 4.0F, 0.0F, false);

    ModelRenderer right_top_strap_r1 = new ModelRenderer(this);
    right_top_strap_r1.setPos(-1.75F, -4.25F, 2.0F);
    bodyAnchor.addChild(right_top_strap_r1);
    setRotationAngle(right_top_strap_r1, 0.0F, 3.1416F, 0.0F);
    right_top_strap_r1.texOffs(8, 20).addBox(-2.0F, -0.5F, -2.25F, 2.0F, 1.0F, 4.0F, 0.0F, false);

    armLAnchor = new ModelRenderer(this);
    armLAnchor.setPos(6.0F, 2.5F, 0.0F);
    armLAnchor.texOffs(15, 11).addBox(-2.25F, -2.25F, -1.25F, 1.0F, 1.0F, 2.0F, 0.0F, false);
    armLAnchor.texOffs(22, 0).addBox(-1.0F, -2.25F, -2.5F, 3.0F, 6.0F, 5.0F, 0.0F, false);
    armLAnchor.texOffs(12, 22).addBox(1.25F, 3.75F, -1.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
    armLAnchor.texOffs(38, 0).addBox(0.5F, 5.5F, -2.5F, 2.0F, 4.0F, 5.0F, 0.0F, false);
    armLAnchor.texOffs(14, 40).addBox(-0.5F, 9.5F, -2.5F, 3.0F, 1.0F, 5.0F, 0.0F, false);
    armLAnchor.texOffs(18, 18).addBox(-2.25F, 6.5F, -2.25F, 3.0F, 1.0F, 4.0F, 0.0F, false);
    armLAnchor.texOffs(18, 18).addBox(-2.25F, 8.0F, -2.25F, 3.0F, 1.0F, 4.0F, 0.0F, false);

    ModelRenderer left_top_horn_r1 = new ModelRenderer(this);
    left_top_horn_r1.setPos(2.5F, -2.5F, 1.5F);
    armLAnchor.addChild(left_top_horn_r1);
    setRotationAngle(left_top_horn_r1, 0.0F, 0.0F, 0.3927F);
    left_top_horn_r1.texOffs(10, 27).addBox(-2.0F, -2.0F, -2.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
    left_top_horn_r1.texOffs(22, 11).addBox(0.0F, -2.0F, -2.0F, 1.0F, 5.0F, 2.0F, 0.0F, false);

    armRAnchor = new ModelRenderer(this);
    armRAnchor.setPos(-6.0F, 2.5F, 0.0F);
    armRAnchor.texOffs(15, 11).addBox(1.0F, -2.25F, -1.25F, 1.0F, 1.0F, 2.0F, 0.0F, true);
    armRAnchor.texOffs(22, 0).addBox(-2.5F, -2.25F, -2.5F, 3.0F, 6.0F, 5.0F, 0.0F, true);
    armRAnchor.texOffs(12, 22).addBox(-2.25F, 3.75F, -1.0F, 1.0F, 1.0F, 2.0F, 0.0F, true);
    armRAnchor.texOffs(38, 0).addBox(-2.5F, 5.5F, -2.5F, 2.0F, 4.0F, 5.0F, 0.0F, true);
    armRAnchor.texOffs(14, 40).addBox(-2.5F, 9.5F, -2.5F, 3.0F, 1.0F, 5.0F, 0.0F, true);
    armRAnchor.texOffs(18, 18).addBox(-1.5F, 6.5F, -2.25F, 3.0F, 1.0F, 4.0F, 0.0F, true);
    armRAnchor.texOffs(18, 18).addBox(-1.5F, 8.0F, -2.25F, 3.0F, 1.0F, 4.0F, 0.0F, true);

    ModelRenderer right_top_horn_r1 = new ModelRenderer(this);
    right_top_horn_r1.setPos(-2.5F, -2.5F, 1.5F);
    armRAnchor.addChild(right_top_horn_r1);
    setRotationAngle(right_top_horn_r1, 0.0F, 0.0F, -0.3927F);
    right_top_horn_r1.texOffs(10, 27).addBox(0.0F, -2.0F, -2.0F, 2.0F, 1.0F, 2.0F, 0.0F, true);
    right_top_horn_r1.texOffs(22, 11).addBox(-1.0F, -2.0F, -2.0F, 1.0F, 5.0F, 2.0F, 0.0F, true);

    legLAnchor = new ModelRenderer(this);
    legLAnchor.setPos(0.0F, 24.0F, 0.0F);


    legL = new ModelRenderer(this);
    legL.setPos(2.0F, -11.5F, 0.0F);
    legLAnchor.addChild(legL);
    legL.texOffs(28, 11).addBox(-1.0F, 0.25F, -2.5F, 3.0F, 4.0F, 5.0F, 0.0F, false);
    legL.texOffs(39, 11).addBox(-1.75F, 5.75F, -2.5F, 3.0F, 4.0F, 1.0F, 0.0F, false);
    legL.texOffs(16, 23).addBox(-2.25F, 1.5F, -2.25F, 1.0F, 2.0F, 4.0F, 0.0F, false);
    legL.texOffs(19, 29).addBox(-2.25F, 6.25F, -2.25F, 4.0F, 1.0F, 4.0F, 0.0F, false);
    legL.texOffs(19, 29).addBox(-2.25F, 7.75F, -2.25F, 4.0F, 1.0F, 4.0F, 0.0F, false);

    bootL = new ModelRenderer(this);
    bootL.setPos(2.0F, -11.5F, 0.0F);
    legLAnchor.addChild(bootL);
    bootL.texOffs(20, 46).addBox(-2.0F, 9.1F, 0.25F, 4.0F, 1.0F, 2.0F, 0.0F, false);
    bootL.texOffs(20, 57).addBox(-2.0F, 10.1F, -2.75F, 4.0F, 2.0F, 5.0F, 0.0F, false);

    ModelRenderer left_plate_r1 = new ModelRenderer(this);
    left_plate_r1.setPos(-2.0F, 12.75F, -5.0F);
    bootL.addChild(left_plate_r1);
    setRotationAngle(left_plate_r1, -1.9635F, 0.0F, 0.0F);
    left_plate_r1.texOffs(20, 34).addBox(0.25F, -2.0F, -3.5F, 3.0F, 1.0F, 2.0F, 0.0F, false);

    legRAnchor = new ModelRenderer(this);
    legRAnchor.setPos(0.0F, 24.0F, 0.0F);

    legR = new ModelRenderer(this);
    legR.setPos(-2.0F, -11.5F, 0.0F);
    legRAnchor.addChild(legR);
    legR.texOffs(28, 11).addBox(-2.5F, 0.25F, -2.5F, 3.0F, 4.0F, 5.0F, 0.0F, true);
    legR.texOffs(39, 11).addBox(-1.75F, 5.75F, -2.5F, 3.0F, 4.0F, 1.0F, 0.0F, true);
    legR.texOffs(16, 23).addBox(1.0F, 1.5F, -2.25F, 1.0F, 2.0F, 4.0F, 0.0F, true);
    legR.texOffs(19, 29).addBox(-2.25F, 6.25F, -2.25F, 4.0F, 1.0F, 4.0F, 0.0F, true);
    legR.texOffs(19, 29).addBox(-2.25F, 7.75F, -2.25F, 4.0F, 1.0F, 4.0F, 0.0F, true);

    bootR = new ModelRenderer(this);
    bootR.setPos(-2.0F, -11.5F, 0.0F);
    legRAnchor.addChild(bootR);
    bootR.texOffs(20, 46).addBox(-2.25F, 9.1F, 0.25F, 4.0F, 1.0F, 2.0F, 0.0F, true);
    bootR.texOffs(20, 57).addBox(-2.25F, 10.1F, -2.75F, 4.0F, 2.0F, 5.0F, 0.0F, true);

    ModelRenderer right_plate_r1 = new ModelRenderer(this);
    right_plate_r1.setPos(-0.125F, 11.0143F, -2.6575F);
    bootR.addChild(right_plate_r1);
    setRotationAngle(right_plate_r1, -1.9635F, 0.0F, 0.0F);
    right_plate_r1.texOffs(20, 34).addBox(-1.875F, -0.5F, -1.0F, 3.0F, 1.0F, 2.0F, 0.0F, true);
  }

  @Override
  public void renderToBuffer(MatrixStack pMatrixStack, IVertexBuilder pBuffer, int pPackedLight, int pPackedOverlay, float pRed, float pGreen, float pBlue, float pAlpha) {
    helmAnchor.visible = slot == EquipmentSlotType.HEAD;
    bodyAnchor.visible = slot == EquipmentSlotType.CHEST;
    armRAnchor.visible = slot == EquipmentSlotType.CHEST;
    armLAnchor.visible = slot == EquipmentSlotType.CHEST;
    legRAnchor.visible = true;
    legLAnchor.visible = true;
    legR.visible = slot == EquipmentSlotType.LEGS;
    legL.visible = slot == EquipmentSlotType.LEGS;
    bootL.visible = slot == EquipmentSlotType.FEET;
    bootR.visible = slot == EquipmentSlotType.FEET;
    hat.visible = false;
    head = helmAnchor;
    body = bodyAnchor;
    rightArm= armRAnchor;
    leftArm = armLAnchor;
    rightLeg = legRAnchor;
    leftLeg = legLAnchor;

    super.renderToBuffer(pMatrixStack, pBuffer, pPackedLight, pPackedOverlay, pRed, pGreen, pBlue, pAlpha);
  }

  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.xRot = x;
    modelRenderer.yRot = y;
    modelRenderer.zRot = z;
  }
}