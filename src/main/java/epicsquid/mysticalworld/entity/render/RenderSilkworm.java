package epicsquid.mysticalworld.entity.render;

import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.entity.EntitySilkworm;
import epicsquid.mysticalworld.entity.model.ModelHolder;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class RenderSilkworm extends RenderLiving<EntitySilkworm> {
  private static final ResourceLocation SILKWORM_TEXTURE = new ResourceLocation(MysticalWorld.MODID, "textures/entity/silkworm.png");

  public RenderSilkworm(RenderManager rendermanagerIn, ModelBase model, float shadow) {
    super(rendermanagerIn, model, shadow);
  }

  @Nullable
  @Override
  protected ResourceLocation getEntityTexture(EntitySilkworm entity) {
    return SILKWORM_TEXTURE;
  }

  @Override
  protected void preRenderCallback(EntitySilkworm entitylivingbaseIn, float partialTickTime) {
    super.preRenderCallback(entitylivingbaseIn, partialTickTime);

    float scale = entitylivingbaseIn.getSize() / 120.0f;
    if (entitylivingbaseIn.isChild()) {
      scale = -0.2f;
    }
    GlStateManager.scale(0.4F + scale, 0.6F + scale, 0.8F + scale);
  }

  public static class Factory implements IRenderFactory {
    @Override
    @Nonnull
    public RenderSilkworm createRenderFor(@Nonnull RenderManager manager) {
      return new RenderSilkworm(manager, ModelHolder.models.get("silkworm"), 0.15f);
    }
  }
}
