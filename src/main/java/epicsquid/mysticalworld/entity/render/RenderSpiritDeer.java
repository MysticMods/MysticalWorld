package epicsquid.mysticalworld.entity.render;

import epicsquid.mysticalworld.entity.EntitySpiritDeer;
import epicsquid.mysticalworld.entity.model.ModelHolder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nonnull;

public class RenderSpiritDeer extends RenderLiving<EntitySpiritDeer> {

  private RenderSpiritDeer(@Nonnull RenderManager renderManager, @Nonnull ModelBase modelBase, float shadowSize) {
    super(renderManager, modelBase, shadowSize);
  }

  @Override
  @Nonnull
  protected ResourceLocation getEntityTexture(@Nonnull EntitySpiritDeer entity) {
    return new ResourceLocation("mysticalworld:textures/entity/spirit_deer.png");
  }

  @Override
  public void doRender(EntitySpiritDeer entity, double x, double y, double z, float entityYaw, float partialTicks) {
    boolean flag = entity.isInvisible();
    GlStateManager.depthMask(!flag);
    GlStateManager.matrixMode(5890);
    GlStateManager.loadIdentity();
    GlStateManager.matrixMode(5888);
    GlStateManager.enableBlend();
    GlStateManager.color(0.5F, 0.5F, 0.5F, 1.0F);
    GlStateManager.disableLighting();
    GlStateManager.blendFunc(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE);
    Minecraft.getMinecraft().entityRenderer.setupFogColor(true);
    super.doRender(entity, x, y, z, entityYaw, partialTicks);
    Minecraft.getMinecraft().entityRenderer.setupFogColor(false);
    GlStateManager.matrixMode(5890);
    GlStateManager.loadIdentity();
    GlStateManager.matrixMode(5888);
    GlStateManager.enableLighting();
    GlStateManager.disableBlend();
    GlStateManager.depthMask(flag);
  }

  public static class Factory implements IRenderFactory<EntitySpiritDeer> {

    @Override
    public Render<EntitySpiritDeer> createRenderFor(RenderManager manager) {
      return new RenderSpiritDeer(manager, ModelHolder.models.get("deer"), 0);
    }
  }
}
