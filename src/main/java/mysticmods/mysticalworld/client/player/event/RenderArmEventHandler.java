package mysticmods.mysticalworld.client.player.event;

import mysticmods.mysticalworld.MysticalWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MysticalWorld.MODID, value = Dist.CLIENT)
public class RenderArmEventHandler {
/*  public static final BeetleArmorModel chestModel = new BeetleArmorModel(CHEST);

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
  }*/
}
