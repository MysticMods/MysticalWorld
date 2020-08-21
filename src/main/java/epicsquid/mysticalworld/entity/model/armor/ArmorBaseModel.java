package epicsquid.mysticalworld.entity.model.armor;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ArmorStandEntity;
import net.minecraft.inventory.EquipmentSlotType;

public class ArmorBaseModel extends BipedModel<LivingEntity> {


  public EquipmentSlotType slot;

  public ArmorBaseModel(EquipmentSlotType slot, float modelSize, float yOffset, int width, int height) {
    super(modelSize, yOffset, width, height);
    this.textureHeight = 64;
    this.textureWidth = 64;
    this.slot = slot;
    this.isChild = false;
    //chest.offsetY -= 0.125;
  }

  @Override
  public void setRotationAngles(LivingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    if (!(entity instanceof ArmorStandEntity)) {
      super.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
      return;
    }

    ArmorStandEntity entityIn = (ArmorStandEntity) entity;
    this.bipedHead.rotateAngleX = ((float) Math.PI / 180F) * entityIn.getHeadRotation().getX();
    this.bipedHead.rotateAngleY = ((float) Math.PI / 180F) * entityIn.getHeadRotation().getY();
    this.bipedHead.rotateAngleZ = ((float) Math.PI / 180F) * entityIn.getHeadRotation().getZ();
    this.bipedHead.setRotationPoint(0.0F, 1.0F, 0.0F);
    this.bipedBody.rotateAngleX = ((float) Math.PI / 180F) * entityIn.getBodyRotation().getX();
    this.bipedBody.rotateAngleY = ((float) Math.PI / 180F) * entityIn.getBodyRotation().getY();
    this.bipedBody.rotateAngleZ = ((float) Math.PI / 180F) * entityIn.getBodyRotation().getZ();
    this.bipedLeftArm.rotateAngleX = ((float) Math.PI / 180F) * entityIn.getLeftArmRotation().getX();
    this.bipedLeftArm.rotateAngleY = ((float) Math.PI / 180F) * entityIn.getLeftArmRotation().getY();
    this.bipedLeftArm.rotateAngleZ = ((float) Math.PI / 180F) * entityIn.getLeftArmRotation().getZ();
    this.bipedRightArm.rotateAngleX = ((float) Math.PI / 180F) * entityIn.getRightArmRotation().getX();
    this.bipedRightArm.rotateAngleY = ((float) Math.PI / 180F) * entityIn.getRightArmRotation().getY();
    this.bipedRightArm.rotateAngleZ = ((float) Math.PI / 180F) * entityIn.getRightArmRotation().getZ();
    this.bipedLeftLeg.rotateAngleX = ((float) Math.PI / 180F) * entityIn.getLeftLegRotation().getX();
    this.bipedLeftLeg.rotateAngleY = ((float) Math.PI / 180F) * entityIn.getLeftLegRotation().getY();
    this.bipedLeftLeg.rotateAngleZ = ((float) Math.PI / 180F) * entityIn.getLeftLegRotation().getZ();
    this.bipedLeftLeg.setRotationPoint(1.9F, 11.0F, 0.0F);
    this.bipedRightLeg.rotateAngleX = ((float) Math.PI / 180F) * entityIn.getRightLegRotation().getX();
    this.bipedRightLeg.rotateAngleY = ((float) Math.PI / 180F) * entityIn.getRightLegRotation().getY();
    this.bipedRightLeg.rotateAngleZ = ((float) Math.PI / 180F) * entityIn.getRightLegRotation().getZ();
    this.bipedRightLeg.setRotationPoint(-1.9F, 11.0F, 0.0F);
    this.bipedHeadwear.copyModelAngles(this.bipedHead);
  }

/*
  @Override
  public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float netHeadPitch, float scale) {
    prepareForRender(entity);
    this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, netHeadPitch, scale, entity);
    this.setHeadRotation();
    this.setChestRotation(entity);
    this.setLegsRotation();
    this.setBootRotation();
    GlStateManager.pushMatrix();
    head.showModel = slot == EntityEquipmentSlot.HEAD;
    chest.showModel = slot == EntityEquipmentSlot.CHEST;
    armR.showModel = slot == EntityEquipmentSlot.CHEST;
    armL.showModel = slot == EntityEquipmentSlot.CHEST;
    legR.showModel = slot == EntityEquipmentSlot.LEGS;
    legL.showModel = slot == EntityEquipmentSlot.LEGS;
    bootR.showModel = slot == EntityEquipmentSlot.FEET;
    bootL.showModel = slot == EntityEquipmentSlot.FEET;
    if (this.isChild) {
      float f = 2.0F;
      GlStateManager.scale(1.5F / f, 1.5F / f, 1.5F / f);
      GlStateManager.translate(0.0F, 16.0F * scale, 0.0F);
      this.renderHead(scale * armorScale);
      GlStateManager.popMatrix();
      GlStateManager.pushMatrix();
      GlStateManager.scale(1.0F / f, 1.0F / f, 1.0F / f);
      GlStateManager.translate(0.0F, 24.0F * scale, 0.0F);
      this.renderPartsMinusHead(scale * armorScale);
      GlStateManager.translate(0.0F, 24.0F * scale, 0.0F);
      this.renderLegs(scale * armorScale);
    } else {
      if (entity.isSneaking()) {
        GlStateManager.translate(0.0F, 0.2F, 0.0F);
      }
      this.renderParts(scale * armorScale);
      this.renderArms(scale * armorScale);
      GlStateManager.translate(0.0F, 24.0F * scale, 0.0F);
      this.renderLegs(scale * armorScale);
    }
    GlStateManager.popMatrix();
  }

  public void renderHead(float scale) {
    head.render(scale);
  }

  public void renderParts(float scale) {
    head.render(scale);
    chest.render(scale);
  }

  public void renderArms(float scale) {
    armR.render(scale);
    armL.render(scale);
  }

  public void renderPartsMinusHead(float scale) {
    chest.render(scale);
  }

  public void renderLegs(float scale) {
    legR.render(scale);
    legL.render(scale);
    bootR.render(scale);
    bootL.render(scale);
  }

  public void setHeadRotation() {
    head.rotationPointX = bipedHead.rotationPointX;
    head.rotationPointY = bipedHead.rotationPointY;
    head.rotationPointZ = bipedHead.rotationPointZ;
    setRotation(head, bipedHead.rotateAngleX, bipedHead.rotateAngleY, bipedHead.rotateAngleZ);
  }

  public void setChestRotation(Entity e) {
    *//* if (e instanceof EntityPlayer){ ((EntityPlayer)e).get } *//*
    chest.rotationPointX = bipedBody.rotationPointX;
    chest.rotationPointY = bipedBody.rotationPointY - 1;
    chest.rotationPointZ = bipedBody.rotationPointZ;
    armR.rotationPointX = bipedRightArm.rotationPointX + 5;
    armR.rotationPointY = bipedRightArm.rotationPointY - 1;
    armR.rotationPointZ = bipedRightArm.rotationPointZ;
    armL.rotationPointX = bipedLeftArm.rotationPointX - 5;
    armL.rotationPointY = bipedLeftArm.rotationPointY - 1;
    armL.rotationPointZ = bipedLeftArm.rotationPointZ;
    setRotation(chest, bipedBody.rotateAngleX, bipedBody.rotateAngleY, bipedBody.rotateAngleZ);
    setRotation(armR, bipedRightArm.rotateAngleX, bipedRightArm.rotateAngleY, bipedRightArm.rotateAngleZ);
    setRotation(armL, bipedLeftArm.rotateAngleX, bipedLeftArm.rotateAngleY, bipedLeftArm.rotateAngleZ);
  }

  public void setLegsRotation() {
    legR.rotationPointX = bipedRightLeg.rotationPointX + 2;
    legR.rotationPointY = bipedRightLeg.rotationPointY - 22;
    legR.rotationPointZ = bipedRightLeg.rotationPointZ;
    legL.rotationPointX = bipedLeftLeg.rotationPointX - 2;
    legL.rotationPointY = bipedLeftLeg.rotationPointY - 22;
    legL.rotationPointZ = bipedLeftLeg.rotationPointZ;
    setRotation(legR, bipedRightLeg.rotateAngleX, bipedRightLeg.rotateAngleY, bipedRightLeg.rotateAngleZ);
    setRotation(legL, bipedLeftLeg.rotateAngleX, bipedLeftLeg.rotateAngleY, bipedLeftLeg.rotateAngleZ);
  }

  public void setBootRotation() {
    bootR.rotationPointX = bipedRightLeg.rotationPointX + 2;
    bootR.rotationPointY = bipedRightLeg.rotationPointY - 22;
    bootR.rotationPointZ = bipedRightLeg.rotationPointZ;
    bootL.rotationPointX = bipedLeftLeg.rotationPointX - 2;
    bootL.rotationPointY = bipedLeftLeg.rotationPointY - 22;
    bootL.rotationPointZ = bipedLeftLeg.rotationPointZ;
    setRotation(bootR, bipedRightLeg.rotateAngleX, bipedRightLeg.rotateAngleY, bipedRightLeg.rotateAngleZ);
    setRotation(bootL, bipedLeftLeg.rotateAngleX, bipedLeftLeg.rotateAngleY, bipedLeftLeg.rotateAngleZ);
  }

  */

  /**
   * borrowed from: https://github.com/williewillus/Botania/blob/MC19/src/main/java/vazkii/botania/client/model/armor/ModelArmorManasteel.java
   *//*
  public void prepareForRender(Entity entity) {
    EntityLivingBase living = (EntityLivingBase) entity;
    isSneak = living != null && living.isSneaking();
    isChild = living != null && living.isChild();
    isRiding = living.isRiding();
    this.swingProgress = living.getSwingProgress(0);
    if (living != null) {
      ModelBiped.ArmPose mainPose = ModelBiped.ArmPose.EMPTY;
      ModelBiped.ArmPose offPose = ModelBiped.ArmPose.EMPTY;
      if (living.getHeldItemMainhand() != null) {
        mainPose = ModelBiped.ArmPose.ITEM;
        if (living.getItemInUseCount() > 0) {
          EnumAction enumaction = living.getHeldItemMainhand().getItemUseAction();
          if (enumaction == EnumAction.BLOCK) {
            mainPose = ModelBiped.ArmPose.BLOCK;
          } else if (enumaction == EnumAction.BOW) {
            mainPose = ModelBiped.ArmPose.BOW_AND_ARROW;
          }
        }
      }
      if (living.getHeldItemOffhand() != null) {
        offPose = ModelBiped.ArmPose.ITEM;
        if (living.getItemInUseCount() > 0) {
          EnumAction enumaction1 = living.getHeldItemOffhand().getItemUseAction();
          if (enumaction1 == EnumAction.BLOCK) {
            offPose = ModelBiped.ArmPose.BLOCK;
          }
        }
      }
      if (living.getPrimaryHand() == EnumHandSide.RIGHT) {
        rightArmPose = mainPose;
        leftArmPose = offPose;
      } else {
        rightArmPose = offPose;
        leftArmPose = mainPose;
      }
    }
  }*/
  public static void setRotation(ModelRenderer model, float x, float y, float z) {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
}
