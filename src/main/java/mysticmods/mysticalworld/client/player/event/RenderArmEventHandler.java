package mysticmods.mysticalworld.client.player.event;

import com.mojang.blaze3d.vertex.VertexConsumer;
import mysticmods.mysticalworld.MysticalWorld;
import mysticmods.mysticalworld.client.model.armor.BeetleArmorModel;
import mysticmods.mysticalworld.init.ModItems;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderArmEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Objects;

import static net.minecraft.inventory.EquipmentSlotType.CHEST;

@Mod.EventBnet.minecraft.world.entity.EquipmentSlot value = Dist.CLIENT)
public class RenderArmEventHandler {
  public static final BeetleArmorModel chestModel = new BeetleArmorModel(CHEST);

  static {
    chestModel.attackTime = 0f;
    chestModel.crouching = false;
    chestModel.swimAmount = 0;
  }

  @SubscribeEvent
  public static void renderArm(RenderArmEvent event) {
    AbstractClientPlayer player = event.getPlayer();
    ItemStack chestStack = player.getItemBySlot(CHEST);
    if (chestStack.getItem() == ModItems.BEETLE_CHESTPLATE.get()) {
      RenderHand renderHand;

      ItemStack inHand = player.getMainHandItem();
      if (inHand.getItem() == Items.FILLED_MAP && player.getOffhandItem().isEmpty()) {
        renderHand = RenderHand.BOTH;
      } else if (event.getArm() == HumanoidArm.RIGHT) {
        renderHand = RenderHand.RIGHT;
      } else {
        renderHand = RenderHand.LEFT;
      }

      VertexConsumer ivertexbuilder = ItemRenderer.getArmorFoilBuffer(event.getMultiBufferSource(), RenderType.armorCutoutNoCull(new ResourceLocation(Objects.requireNonNull(chestStack.getItem().getArmorTexture(chestStack, player, CHEST, null)))), false, chestStack.hasFoil());
      if (event.getArm() == HumanoidArm.RIGHT) {
        chestModel.rightArmPose = HumanoidModel.ArmPose.EMPTY;
      } else {
        chestModel.leftArmPose = HumanoidModel.ArmPose.EMPTY;
      }
      chestModel.setupAnim(player, 0f, 0f, 0f, 0f, 0f);
      if (renderHand.shouldRender(HumanoidArm.RIGHT)) {
        chestModel.rightArm.render(event.getPoseStack(), ivertexbuilder, event.getPackedLight(), OverlayTexture.NO_OVERLAY);
      }
      if (renderHand.shouldRender(HumanoidArm.LEFT)) {
        chestModel.leftArm.render(event.getPoseStack(), ivertexbuilder, event.getPackedLight(), OverlayTexture.NO_OVERLAY);
      }
    }
  }

  public enum RenderHand {
    LEFT,
    RIGHT,
    BOTH;

    public boolean shouldRender (HumanoidArm hand) {
      switch (this) {
        default:
        case BOTH:
          return true;
        case LEFT:
          return hand == HumanoidArm.LEFT;
        case RIGHT:
          return hand == HumanoidArm.RIGHT;
      }
    }
  }
}
