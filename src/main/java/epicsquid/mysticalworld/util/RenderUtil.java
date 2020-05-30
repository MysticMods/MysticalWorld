package epicsquid.mysticalworld.util;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderUtil {
  public static void renderBlock(BlockState block, float posX, float posY, float posZ, float rotation, float scale) {
    GlStateManager.enableRescaleNormal();
    GlStateManager.pushMatrix();
    GlStateManager.rotated(-30, 0, 1, 0);
    RenderHelper.enableStandardItemLighting();
    GlStateManager.popMatrix();
    GlStateManager.pushMatrix();
    GlStateManager.translated(posX, posY, 50 + posZ);
    GlStateManager.rotated(20, 1, 0, 0);
    GlStateManager.scaled(scale * 50, -(scale * 50), -(scale * 50));
    GlStateManager.translated(0.5f, 0.5f, 0.5f);
    GlStateManager.rotated(rotation, 0, 1, 0);
    GlStateManager.translated(-0.5f, -0.5f, -0.5f);
    Minecraft mc = Minecraft.getInstance();
    mc.getTextureManager().bindTexture(AtlasTexture.LOCATION_BLOCKS_TEXTURE);
    mc.getBlockRendererDispatcher().renderBlockBrightness(block, 1);
    GlStateManager.popMatrix();
    RenderHelper.disableStandardItemLighting();
    GlStateManager.disableRescaleNormal();
  }
}
