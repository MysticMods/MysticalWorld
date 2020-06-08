package epicsquid.mysticalworld.entity.render;

import epicsquid.mysticalworld.entity.EntityDeer;
import epicsquid.mysticalworld.entity.model.ModelHolder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import org.lwjgl.opengl.GL11;

import javax.annotation.Nonnull;

public class RenderDeer extends RenderLiving<EntityDeer> {

  private RenderDeer(@Nonnull RenderManager renderManager, @Nonnull ModelBase modelBase, float shadowSize) {
    super(renderManager, modelBase, shadowSize);
  }

  @Override
  @Nonnull
  protected ResourceLocation getEntityTexture(@Nonnull EntityDeer entity) {
    if (entity.getEntityId() % 2 == 0) {
      GL11.glGetInteger(GL11.GL_BLEND);
      return new ResourceLocation("mysticalworld:textures/entity/rudolph.png");
    }
    return new ResourceLocation("mysticalworld:textures/entity/deer.png");
  }

  @Override
  public void doRender(EntityDeer entity, double x, double y, double z, float entityYaw, float partialTicks) {
    if (entity.getDataManager().get(EntityDeer.spirit)) {
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
    } else {
      super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }
  }

  public static class Factory implements IRenderFactory<EntityDeer> {

    @Override
    public Render<EntityDeer> createRenderFor(RenderManager manager) {
      return new RenderDeer(manager, ModelHolder.models.get("deer"), 0.35f);
    }
  }
}
