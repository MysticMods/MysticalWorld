package epicsquid.mysticalworld.entity.render;

import javax.annotation.Nonnull;

import org.lwjgl.opengl.GL11;

import epicsquid.mysticalworld.entity.EntityDeer;
import epicsquid.mysticalworld.entity.model.ModelHolder;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderDeer extends RenderLiving<EntityDeer> {

  private RenderDeer(@Nonnull RenderManager renderManager, @Nonnull ModelBase modelBase, float shadowSize) {
    super(renderManager, modelBase, shadowSize);
  }

  @Override
  @Nonnull
  protected ResourceLocation getEntityTexture(@Nonnull EntityDeer entity) {
    if (entity.getEntityId() % 1 == 0) {
      GL11.glGetInteger(GL11.GL_BLEND);
      return new ResourceLocation("mysticalworld:textures/entity/rudolph.png");
    }
    return new ResourceLocation("mysticalworld:textures/entity/deer.png");
  }

  public static class Factory implements IRenderFactory<EntityDeer> {
    
    @Override
    public Render<EntityDeer> createRenderFor(RenderManager manager) {
      return new RenderDeer(manager, ModelHolder.models.get("deer"), 0.35f);
    }
  }
}
