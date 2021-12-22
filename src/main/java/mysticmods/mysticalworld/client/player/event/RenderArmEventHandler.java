package mysticmods.mysticalworld.client.player.event;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import mysticmods.mysticalworld.MysticalWorld;
import mysticmods.mysticalworld.client.model.armor.BeetleArmorModel;
import mysticmods.mysticalworld.init.ModItems;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.HandSide;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderArmEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Objects;

import static net.minecraft.inventory.EquipmentSlotType.CHEST;

@Mod.EventBusSubscriber(modid = MysticalWorld.MODID, value = Dist.CLIENT)
public class RenderArmEventHandler {
  public static final BeetleArmorModel chestModel = new BeetleArmorModel(CHEST);

  static {
    chestModel.attackTime = 0f;
    chestModel.crouching = false;
    chestModel.swimAmount = 0;
  }

  @SubscribeEvent
  public static void renderArm(RenderArmEvent event) {
    AbstractClientPlayerEntity player = event.getPlayer();
    ItemStack chestStack = player.getItemBySlot(CHEST);
    if (chestStack.getItem() == ModItems.BEETLE_CHESTPLATE.get()) {
      RenderHand renderHand;

      ItemStack inHand = player.getMainHandItem();
      if (inHand.getItem() == Items.FILLED_MAP && player.getOffhandItem().isEmpty()) {
        renderHand = RenderHand.BOTH;
      } else if (event.getArm() == HandSide.RIGHT) {
        renderHand = RenderHand.RIGHT;
      } else {
        renderHand = RenderHand.LEFT;
      }

      IVertexBuilder ivertexbuilder = ItemRenderer.getArmorFoilBuffer(event.getMultiBufferSource(), RenderType.armorCutoutNoCull(new ResourceLocation(Objects.requireNonNull(chestStack.getItem().getArmorTexture(chestStack, player, CHEST, null)))), false, chestStack.hasFoil());
      if (event.getArm() == HandSide.RIGHT) {
        chestModel.rightArmPose = BipedModel.ArmPose.EMPTY;
      } else {
        chestModel.leftArmPose = BipedModel.ArmPose.EMPTY;
      }
      chestModel.setupAnim(player, 0f, 0f, 0f, 0f, 0f);
      if (renderHand.shouldRender(HandSide.RIGHT)) {
        chestModel.rightArm.render(event.getPoseStack(), ivertexbuilder, event.getPackedLight(), OverlayTexture.NO_OVERLAY);
      }
      if (renderHand.shouldRender(HandSide.LEFT)) {
        chestModel.leftArm.render(event.getPoseStack(), ivertexbuilder, event.getPackedLight(), OverlayTexture.NO_OVERLAY);
      }
    }
  }

  public enum RenderHand {
    LEFT,
    RIGHT,
    BOTH;

    public boolean shouldRender (HandSide hand) {
      switch (this) {
        default:
        case BOTH:
          return true;
        case LEFT:
          return hand == HandSide.LEFT;
        case RIGHT:
          return hand == HandSide.RIGHT;
      }
    }
  }
}
