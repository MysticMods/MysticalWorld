package epicsquid.mysticalworld.entity.player;

import com.mojang.blaze3d.platform.GlStateManager;
import epicsquid.mysticalworld.capability.PlayerShoulderCapability;
import epicsquid.mysticalworld.capability.PlayerShoulderCapabilityProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;

// Code below was used with permission by Tschipp, modified from the carry renderer in Animania
// https://github.com/capnkirok/animaniamod/blob/1.12/src/main/java/com/animania/common/events/CarryRenderer.java

@OnlyIn(Dist.CLIENT)
public class ShoulderRenderer {
  @OnlyIn(Dist.CLIENT)
  private static double[] playerPosition(PlayerEntity player, float ticks) {
    double d0 = player.lastTickPosX + (player.posX - player.lastTickPosX) * ticks;
    double d1 = player.lastTickPosY + (player.posY - player.lastTickPosY) * ticks;
    double d2 = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * ticks;
    return new double[]{d0, d1, d2};
  }

  @OnlyIn(Dist.CLIENT)
  @Nullable
  private static Entity resolveAnimal(PlayerEntity player, World world, PlayerShoulderCapability cap, float ticks) {
    EntityType<?> type = ForgeRegistries.ENTITIES.getValue(cap.getRegistryName());
    if (type != null) {
      Entity animal = type.create(world);
      if (animal != null) {
        animal.read(cap.getAnimalSerialized());
        double[] d = playerPosition(player, ticks);
        animal.setPositionAndRotation(d[0], d[1], d[2], 0, 0);
        animal.prevRotationYaw = 0;
        animal.setRotationYawHead(0);
      }
      return animal;
    }
    return null;
  }

  @OnlyIn(Dist.CLIENT)
  public static void onRenderHand(RenderHandEvent event) {
    Minecraft mc = Minecraft.getInstance();
    PlayerEntity player = mc.player;
    World world = mc.world;
    float ticks = event.getPartialTicks();

    LazyOptional<PlayerShoulderCapability> lazycap = player.getCapability(PlayerShoulderCapabilityProvider.PLAYER_SHOULDER_CAPABILITY, null);
    if (lazycap.isPresent()) {
      PlayerShoulderCapability cap = lazycap.orElseThrow(IllegalStateException::new);
      if (cap.isShouldered()) {
        Entity animal = resolveAnimal(player, world, cap, ticks);
        if (animal != null) {
          GlStateManager.pushMatrix();
          GlStateManager.scaled(50, 50, 50);
          GlStateManager.rotated(180, 0, 1, 0);
          GlStateManager.translated(-0.2, -0.1, 0.13);
          GlStateManager.enableAlphaTest();
          GlStateManager.scaled(0.3, 0.3, 0.3);
          GlStateManager.translated(0, 0.1, 0);

          if (mc.gameSettings.thirdPersonView == 0) {
            RenderHelper.enableStandardItemLighting();
            EntityRendererManager renderManager = mc.getRenderManager();
            renderManager.setRenderShadow(false);
            renderManager.renderEntityStatic(animal, 0, false);
            renderManager.setRenderShadow(true);
          }

          GlStateManager.disableAlphaTest();
          GlStateManager.scaled(1, 1, 1);
          GlStateManager.popMatrix();
        }
      }
    }
  }

  @OnlyIn(Dist.CLIENT)
  public static void onRenderPlayerPost(RenderPlayerEvent.Post event) {
    Minecraft mc = Minecraft.getInstance();
    PlayerEntity player = mc.player;
    PlayerEntity ePlayer = event.getPlayer();
    World world = mc.world;
    float ticks = event.getPartialRenderTick();

    LazyOptional<PlayerShoulderCapability> lazycap = player.getCapability(PlayerShoulderCapabilityProvider.PLAYER_SHOULDER_CAPABILITY, null);
    if (lazycap.isPresent()) {
      PlayerShoulderCapability cap = lazycap.orElseThrow(IllegalStateException::new);
      if (cap.isShouldered()) {
        Entity animal = resolveAnimal(player, world, cap, ticks);
        if (animal != null) {
          double[] d = playerPosition(ePlayer, ticks);
          double[] c = playerPosition(player, ticks);

          double x = d[0] - c[0];
          double y = d[1] - c[1];
          double z = d[2] - c[2];

          animal.setPosition(c[0], c[1], c[2]);

          GlStateManager.pushMatrix();
          GlStateManager.translated(x, y, z);
          GlStateManager.scaled(1, 1, 1);
          GlStateManager.rotatef(-(ePlayer.prevRenderYawOffset + (ePlayer.renderYawOffset - player.prevRenderYawOffset) * ticks), 0, 1f, 0);
          GlStateManager.translated(-0.32, 1.37, 0);

          if (player.isSneaking()) {
            GlStateManager.translated(0, -0.3, 0);
          }

          EntityRendererManager renderManager = mc.getRenderManager();
          renderManager.setRenderShadow(false);
          renderManager.renderEntityStatic(animal, 0, false);
          renderManager.setRenderShadow(true);

          GlStateManager.scaled(1, 1, 1);
          GlStateManager.popMatrix();
        }
      }
    }
  }
}

