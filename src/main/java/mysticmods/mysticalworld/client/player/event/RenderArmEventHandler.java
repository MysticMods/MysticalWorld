package mysticmods.mysticalworld.client.player.event;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import mysticmods.mysticalworld.MysticalWorld;
import mysticmods.mysticalworld.client.model.ModelHolder;
import mysticmods.mysticalworld.client.model.armor.BeetleArmorModel;
import mysticmods.mysticalworld.items.BeetleArmorItem;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.HandSide;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderArmEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import mysticmods.mysticalworld.init.ModItems;

import java.util.Objects;

public class RenderArmEventHandler {
  public static double x = 0.5;
  public static double y = 0.5;
  public static double z = 0.5;

  @SubscribeEvent
  public static void renderArm (RenderArmEvent event) {
    AbstractClientPlayerEntity player = event.getPlayer();
    ItemStack chestStack = player.getItemBySlot(EquipmentSlotType.CHEST);
    if (chestStack.getItem() == ModItems.BEETLE_CHESTPLATE.get()) {
      BeetleArmorModel chestModel = ModelHolder.beetleChestplate;
      boolean rightHand = event.getArm() == HandSide.RIGHT;
      if (rightHand) {
        chestModel.rightArmPose = BipedModel.ArmPose.EMPTY;
      } else {
        chestModel.leftArmPose = BipedModel.ArmPose.EMPTY;
      }
      chestModel.attackTime = 0f;
      chestModel.crouching = false;
      chestModel.swimAmount = 0;
      chestModel.setupAnim(player, 0f, 0f, 0f, 0f, 0f);
      IVertexBuilder ivertexbuilder = ItemRenderer.getArmorFoilBuffer(event.getMultiBufferSource(), RenderType.armorCutoutNoCull(new ResourceLocation(Objects.requireNonNull(chestStack.getItem().getArmorTexture(chestStack, player, EquipmentSlotType.CHEST, null)))), false, chestStack.hasFoil());
      MatrixStack matrixStack = event.getPoseStack();
      matrixStack.pushPose();
      matrixStack.translate(-0.3, 0.15, 0);
      chestModel.armL.zRot = -0.1f;
      chestModel.armR.zRot = 0.1f;
      chestModel.armR.render(event.getPoseStack(), ivertexbuilder, event.getPackedLight(), OverlayTexture.NO_OVERLAY);
      chestModel.armL.render(event.getPoseStack(), ivertexbuilder, event.getPackedLight(), OverlayTexture.NO_OVERLAY);
      matrixStack.popPose();
      chestModel.armL.zRot = 0;
      chestModel.armR.zRot = 0;
      //event.setCanceled(true);
    }
  }
}
