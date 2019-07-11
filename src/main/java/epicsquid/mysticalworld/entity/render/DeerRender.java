package epicsquid.mysticalworld.entity.render;

import javax.annotation.Nonnull;

import epicsquid.mysticalworld.entity.model.DeerModel;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

import epicsquid.mysticalworld.entity.DeerEntity;
import epicsquid.mysticalworld.entity.model.ModelHolder;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class DeerRender extends LivingRenderer<DeerEntity> {

  private DeerRender(@Nonnull EntityRendererManager renderManager, @Nonnull DeerModel modelBase, float shadowSize) {
    super(renderManager, modelBase, shadowSize);
  }

  @Override
  @Nonnull
  protected ResourceLocation getEntityTexture(@Nonnull Entity entity) {
    if (entity.getEntityId() % 2 == 0) {
      GL11.glGetInteger(GL11.GL_BLEND);
      return new ResourceLocation("mysticalworld:textures/entity/rudolph.png");
    }
    return new ResourceLocation("mysticalworld:textures/entity/deer.png");
  }

  public static class Factory implements IRenderFactory<DeerEntity> {

    @Override
    public EntityRenderer<DeerEntity> createRenderFor(EntityRendererManager manager) {
      return new DeerRender(manager, ModelHolder.models.get("deer"), 0.35f);
    }
  }
}
