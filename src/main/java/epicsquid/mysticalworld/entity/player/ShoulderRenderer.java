package epicsquid.mysticalworld.entity.player;

import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.capability.PlayerShoulderCapability;
import epicsquid.mysticalworld.capability.PlayerShoulderCapabilityProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

// Code below was used with permission by Tschipp, modified from the carry renderer in Animania
// https://github.com/capnkirok/animaniamod/blob/1.12/src/main/java/com/animania/common/events/CarryRenderer.java

@SideOnly(Side.CLIENT)
@SuppressWarnings("unused")
@Mod.EventBusSubscriber(modid = MysticalWorld.MODID, value = Side.CLIENT)
public class ShoulderRenderer {
  @SideOnly(Side.CLIENT)
  private static double[] playerPosition(EntityPlayer player, float ticks) {
    double d0 = player.lastTickPosX + (player.posX - player.lastTickPosX) * ticks;
    double d1 = player.lastTickPosY + (player.posY - player.lastTickPosY) * ticks;
    double d2 = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * ticks;
    return new double[]{d0, d1, d2};
  }

  @SideOnly(Side.CLIENT)
  @Nullable
  private static Entity resolveAnimal(EntityPlayer player, World world, PlayerShoulderCapability cap, float ticks) {
    Entity animal = EntityList.createEntityByIDFromName(cap.getRegistryName(), world);
    if (animal != null) {
      animal.readFromNBT(cap.getAnimalSerialized());
      double[] d = playerPosition(player, ticks);
      animal.setPositionAndRotation(d[0], d[1], d[2], 0, 0);
      animal.prevRotationYaw = 0;
      animal.setRotationYawHead(0);
    }
    return animal;
  }

  @SubscribeEvent
  @SideOnly(Side.CLIENT)
  public static void onRenderHand(RenderHandEvent event) {
    Minecraft mc = Minecraft.getMinecraft();
    EntityPlayer player = mc.player;
    if (player == null) {
      return;
    }
    World world = mc.world;
    float ticks = event.getPartialTicks();

    PlayerShoulderCapability cap = player.getCapability(PlayerShoulderCapabilityProvider.PLAYER_SHOULDER_CAPABILITY, null);
    if (cap != null && cap.isShouldered()) {
      Entity animal = resolveAnimal(player, world, cap, ticks);
      if (animal != null) {
        GlStateManager.pushMatrix();
        GlStateManager.scale(50, 50, 50);
        GlStateManager.rotate(180, 0, 1, 0);
        GlStateManager.translate(-0.2, -0.1, 0.13);
        GlStateManager.enableAlpha();
        GlStateManager.scale(0.3, 0.3, 0.3);
        GlStateManager.translate(0, 0.1, 0);

        if (mc.gameSettings.thirdPersonView == 0) {
          RenderHelper.enableStandardItemLighting();
          RenderManager renderManager = mc.getRenderManager();
          renderManager.setRenderShadow(false);
          renderManager.renderEntityStatic(animal, 0, false);
          renderManager.setRenderShadow(true);
        }

        GlStateManager.disableAlpha();
        GlStateManager.scale(1, 1, 1);
        GlStateManager.popMatrix();
      }
    }
  }

  @SubscribeEvent
  @SideOnly(Side.CLIENT)
  public static void onRenderPlayerPost(RenderPlayerEvent.Post event) {
    Minecraft mc = Minecraft.getMinecraft();
    EntityPlayer player = mc.player;
    if (player == null) {
      return;
    }
    EntityPlayer ePlayer = event.getEntityPlayer();
    World world = mc.world;
    float ticks = event.getPartialRenderTick();

    PlayerShoulderCapability cap = player.getCapability(PlayerShoulderCapabilityProvider.PLAYER_SHOULDER_CAPABILITY, null);
    if (cap != null && cap.isShouldered()) {
      Entity animal = resolveAnimal(player, world, cap, ticks);
      if (animal != null) {
        double[] d = playerPosition(ePlayer, ticks);
        double[] c = playerPosition(player, ticks);

        double x = d[0] - c[0];
        double y = d[1] - c[1];
        double z = d[2] - c[2];

        animal.setPosition(c[0], c[1], c[2]);

        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y, z);
        GlStateManager.scale(1, 1, 1);
        GlStateManager.rotate(-(ePlayer.prevRenderYawOffset + (ePlayer.renderYawOffset - player.prevRenderYawOffset) * ticks), 0, 1f, 0);
        GlStateManager.translate(-0.32, 1.37, 0);

        if (player.isSneaking()) {
          GlStateManager.translate(0, -0.3, 0);
        }

        RenderManager renderManager = mc.getRenderManager();
        renderManager.setRenderShadow(false);
        renderManager.renderEntityStatic(animal, 0, false);
        renderManager.setRenderShadow(true);

        GlStateManager.scale(1, 1, 1);
        GlStateManager.popMatrix();
      }
    }
  }
}
