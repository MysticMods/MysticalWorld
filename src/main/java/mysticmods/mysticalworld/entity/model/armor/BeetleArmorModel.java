package mysticmods.mysticalworld.entity.model.armor;// Made with Blockbench 4.0.1
// Exported for Minecraft version 1.15 - 1.16 with Mojang mappings
// Paste this class into your mod and generate all required imports

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.inventory.EquipmentSlotType;
import noobanidus.libs.noobutil.client.model.ArmorBaseModel;

public class BeetleArmorModel extends ArmorBaseModel {
  private final ModelRenderer helmet;
  private final ModelRenderer top_horn_piece_r1;
  private final ModelRenderer right_strap_r1;
  private final ModelRenderer chest_armor;
  private final ModelRenderer right_top_strap_r1;
  private final ModelRenderer right_arm_armor;
  private final ModelRenderer right_top_horn_r1;
  private final ModelRenderer left_arm_armor;
  private final ModelRenderer left_top_horn_r1;
  private final ModelRenderer right_leg_armor;
  private final ModelRenderer bootR;
  private final ModelRenderer right_plate_r1;
  private final ModelRenderer left_leg_armor;
  private final ModelRenderer bootL;
  private final ModelRenderer left_plate_r1;

  public BeetleArmorModel(EquipmentSlotType slot) {
    super(slot, 1f, 0f, 64, 64);

    helmet = new ModelRenderer(this);
    helmet.setPos(0.0F, -2.0F, 1.0F);
    head.addChild(helmet);
    helmet.texOffs(0, 0).addBox(-4.5F, -6.5F, -6.0F, 9.0F, 9.0F, 2.0F, 0.0F, false);
    helmet.texOffs(6, 11).addBox(3.25F, -2.0F, -4.0F, 1.0F, 2.0F, 7.0F, 0.0F, false);
    helmet.texOffs(0, 30).addBox(-4.25F, -2.0F, 2.25F, 8.0F, 2.0F, 1.0F, 0.0F, false);
    helmet.texOffs(0, 11).addBox(-2.5F, 2.5F, -6.0F, 5.0F, 4.0F, 1.0F, 0.0F, false);

    top_horn_piece_r1 = new ModelRenderer(this);
    top_horn_piece_r1.setPos(0.0F, -9.25F, -2.75F);
    helmet.addChild(top_horn_piece_r1);
    setRotationAngle(top_horn_piece_r1, -0.3927F, 0.0F, 0.0F);
    top_horn_piece_r1.texOffs(0, 25).addBox(-1.0F, -2.75F, 0.25F, 2.0F, 2.0F, 3.0F, 0.0F, false);
    top_horn_piece_r1.texOffs(0, 16).addBox(-1.0F, -2.75F, -1.75F, 2.0F, 7.0F, 2.0F, 0.0F, false);

    right_strap_r1 = new ModelRenderer(this);
    right_strap_r1.setPos(-3.75F, -1.0F, -0.5F);
    helmet.addChild(right_strap_r1);
    setRotationAngle(right_strap_r1, 0.0F, 0.0F, -3.1416F);
    right_strap_r1.texOffs(6, 11).addBox(-0.5F, -1.0F, -3.5F, 1.0F, 2.0F, 7.0F, 0.0F, false);

    chest_armor = new ModelRenderer(this);
    chest_armor.setPos(-2.0F, 5.0F, -2.0F);
    body.addChild(chest_armor);
    chest_armor.texOffs(0, 33).addBox(-2.5F, -4.75F, -1.5F, 9.0F, 6.0F, 1.0F, 0.0F, false);
    chest_armor.texOffs(0, 40).addBox(-1.0F, 1.25F, -1.0F, 6.0F, 5.0F, 1.0F, 0.0F, false);
    chest_armor.texOffs(0, 53).addBox(-2.5F, -4.25F, 4.0F, 9.0F, 10.0F, 1.0F, 0.0F, false);
    chest_armor.texOffs(8, 20).addBox(3.75F, -5.25F, -0.25F, 2.0F, 1.0F, 4.0F, 0.0F, false);
    chest_armor.texOffs(0, 46).addBox(-2.25F, 3.0F, -0.25F, 8.0F, 2.0F, 4.0F, 0.0F, false);
    chest_armor.texOffs(0, 46).addBox(-2.25F, -3.0F, -0.25F, 8.0F, 2.0F, 4.0F, 0.0F, false);

    right_top_strap_r1 = new ModelRenderer(this);
    right_top_strap_r1.setPos(-1.75F, -4.25F, 2.0F);
    chest_armor.addChild(right_top_strap_r1);
    setRotationAngle(right_top_strap_r1, 0.0F, 3.1416F, 0.0F);
    right_top_strap_r1.texOffs(8, 20).addBox(-2.0F, -1.0F, -2.25F, 2.0F, 1.0F, 4.0F, 0.0F, false);

    right_arm_armor = new ModelRenderer(this);
    right_arm_armor.setPos(-1.0F, 0.5F, 0.0F);
    rightArm.addChild(right_arm_armor);
    right_arm_armor.texOffs(15, 11).addBox(1.0F, -2.25F, -1.25F, 1.0F, 1.0F, 2.0F, 0.0F, true);
    right_arm_armor.texOffs(22, 0).addBox(-2.5F, -2.75F, -2.5F, 3.0F, 6.0F, 5.0F, 0.0F, true);
    right_arm_armor.texOffs(12, 22).addBox(-2.25F, 3.75F, -1.0F, 1.0F, 1.0F, 2.0F, 0.0F, true);
    right_arm_armor.texOffs(38, 0).addBox(-2.5F, 5.5F, -2.5F, 2.0F, 4.0F, 5.0F, 0.0F, true);
    right_arm_armor.texOffs(14, 40).addBox(-2.5F, 9.5F, -2.5F, 3.0F, 1.0F, 5.0F, 0.0F, true);
    right_arm_armor.texOffs(18, 18).addBox(-1.5F, 6.5F, -2.25F, 3.0F, 1.0F, 4.0F, 0.0F, true);
    right_arm_armor.texOffs(18, 18).addBox(-1.5F, 8.0F, -2.25F, 3.0F, 1.0F, 4.0F, 0.0F, true);

    right_top_horn_r1 = new ModelRenderer(this);
    right_top_horn_r1.setPos(-2.5F, -2.5F, 1.5F);
    right_arm_armor.addChild(right_top_horn_r1);
    setRotationAngle(right_top_horn_r1, 0.0F, 0.0F, -0.3927F);
    right_top_horn_r1.texOffs(10, 27).addBox(0.0F, -2.0F, -2.0F, 2.0F, 1.0F, 2.0F, 0.0F, true);
    right_top_horn_r1.texOffs(22, 11).addBox(-1.0F, -2.0F, -2.0F, 1.0F, 5.0F, 2.0F, 0.0F, true);

    left_arm_armor = new ModelRenderer(this);
    left_arm_armor.setPos(1.0F, 0.5F, 0.0F);
    leftArm.addChild(left_arm_armor);
    left_arm_armor.texOffs(15, 11).addBox(-2.25F, -2.25F, -1.25F, 1.0F, 1.0F, 2.0F, 0.0F, false);
    left_arm_armor.texOffs(22, 0).addBox(-1.0F, -2.75F, -2.5F, 3.0F, 6.0F, 5.0F, 0.0F, false);
    left_arm_armor.texOffs(12, 22).addBox(1.25F, 3.75F, -1.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
    left_arm_armor.texOffs(38, 0).addBox(0.5F, 5.5F, -2.5F, 2.0F, 4.0F, 5.0F, 0.0F, false);
    left_arm_armor.texOffs(14, 40).addBox(-0.5F, 9.5F, -2.5F, 3.0F, 1.0F, 5.0F, 0.0F, false);
    left_arm_armor.texOffs(18, 18).addBox(-2.25F, 6.5F, -2.25F, 3.0F, 1.0F, 4.0F, 0.0F, false);
    left_arm_armor.texOffs(18, 18).addBox(-2.25F, 8.0F, -2.25F, 3.0F, 1.0F, 4.0F, 0.0F, false);

    left_top_horn_r1 = new ModelRenderer(this);
    left_top_horn_r1.setPos(2.5F, -2.5F, 1.5F);
    left_arm_armor.addChild(left_top_horn_r1);
    setRotationAngle(left_top_horn_r1, 0.0F, 0.0F, 0.3927F);
    left_top_horn_r1.texOffs(10, 27).addBox(-2.0F, -2.0F, -2.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
    left_top_horn_r1.texOffs(22, 11).addBox(0.0F, -2.0F, -2.0F, 1.0F, 5.0F, 2.0F, 0.0F, false);

    right_leg_armor = new ModelRenderer(this);
    right_leg_armor.setPos(-0.1F, 0.5F, 0.0F);
    rightLeg.addChild(right_leg_armor);
    right_leg_armor.texOffs(28, 11).addBox(-2.5F, 0.25F, -2.5F, 3.0F, 4.0F, 5.0F, 0.0F, true);
    right_leg_armor.texOffs(39, 11).addBox(-1.75F, 5.75F, -2.5F, 3.0F, 4.0F, 1.0F, 0.0F, true);
    right_leg_armor.texOffs(16, 23).addBox(1.0F, 1.5F, -2.25F, 1.0F, 2.0F, 4.0F, 0.0F, true);
    right_leg_armor.texOffs(19, 29).addBox(-2.25F, 6.25F, -2.25F, 4.0F, 1.0F, 4.0F, 0.0F, true);
    right_leg_armor.texOffs(19, 29).addBox(-2.25F, 7.75F, -2.25F, 4.0F, 1.0F, 4.0F, 0.0F, true);

    bootR = new ModelRenderer(this);
    bootR.setPos(-0.1F, 0.5F, 0.0F);
    rightLeg.addChild(bootR);
    bootR.texOffs(20, 46).addBox(-2.25F, 9.1F, 0.25F, 4.0F, 1.0F, 2.0F, 0.0F, true);
    bootR.texOffs(20, 57).addBox(-2.25F, 10.1F, -2.75F, 4.0F, 2.0F, 5.0F, 0.0F, true);

    right_plate_r1 = new ModelRenderer(this);
    right_plate_r1.setPos(-0.125F, 11.0143F, -2.6575F);
    bootR.addChild(right_plate_r1);
    setRotationAngle(right_plate_r1, -1.9635F, 0.0F, 0.0F);
    right_plate_r1.texOffs(20, 34).addBox(-1.875F, -0.5F, -1.0F, 3.0F, 1.0F, 2.0F, 0.0F, true);

    left_leg_armor = new ModelRenderer(this);
    left_leg_armor.setPos(0.1F, 0.5F, 0.0F);
    leftLeg.addChild(left_leg_armor);
    left_leg_armor.texOffs(28, 11).addBox(-1.0F, 0.25F, -2.5F, 3.0F, 4.0F, 5.0F, 0.0F, false);
    left_leg_armor.texOffs(39, 11).addBox(-1.75F, 5.75F, -2.5F, 3.0F, 4.0F, 1.0F, 0.0F, false);
    left_leg_armor.texOffs(16, 23).addBox(-2.25F, 1.5F, -2.25F, 1.0F, 2.0F, 4.0F, 0.0F, false);
    left_leg_armor.texOffs(19, 29).addBox(-2.25F, 6.25F, -2.25F, 4.0F, 1.0F, 4.0F, 0.0F, false);
    left_leg_armor.texOffs(19, 29).addBox(-2.25F, 7.75F, -2.25F, 4.0F, 1.0F, 4.0F, 0.0F, false);

    bootL = new ModelRenderer(this);
    bootL.setPos(0.1F, 0.5F, 0.0F);
    leftLeg.addChild(bootL);
    bootL.texOffs(20, 46).addBox(-2.0F, 9.1F, 0.25F, 4.0F, 1.0F, 2.0F, 0.0F, false);
    bootL.texOffs(20, 57).addBox(-2.0F, 10.1F, -2.75F, 4.0F, 2.0F, 5.0F, 0.0F, false);

    left_plate_r1 = new ModelRenderer(this);
    left_plate_r1.setPos(-2.0F, 12.75F, -5.0F);
    bootL.addChild(left_plate_r1);
    setRotationAngle(left_plate_r1, -1.9635F, 0.0F, 0.0F);
    left_plate_r1.texOffs(20, 34).addBox(0.25F, -2.0F, -3.5F, 3.0F, 1.0F, 2.0F, 0.0F, false);
  }
  @Override
  public void renderToBuffer(MatrixStack pMatrixStack, IVertexBuilder pBuffer, int pPackedLight, int pPackedOverlay, float pRed, float pGreen, float pBlue, float pAlpha) {
    head.visible = slot == EquipmentSlotType.HEAD;
    body.visible = slot == EquipmentSlotType.CHEST;
    rightArm.visible = slot == EquipmentSlotType.CHEST;
    leftArm.visible = slot == EquipmentSlotType.CHEST;
    rightLeg.visible = slot == EquipmentSlotType.LEGS;
    leftLeg.visible = slot == EquipmentSlotType.LEGS;
    bootL.visible = slot == EquipmentSlotType.FEET;
    bootR.visible = slot == EquipmentSlotType.FEET;
    hat.visible = false;

    super.renderToBuffer(pMatrixStack, pBuffer, pPackedLight, pPackedOverlay, pRed, pGreen, pBlue, pAlpha);
  }

  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.xRot = x;
    modelRenderer.yRot = y;
    modelRenderer.zRot = z;
  }
}