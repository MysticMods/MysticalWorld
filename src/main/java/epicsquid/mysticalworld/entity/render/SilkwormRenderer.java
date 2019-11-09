package epicsquid.mysticalworld.entity.render;

import com.mojang.blaze3d.platform.GlStateManager;
import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.entity.SilkwormEntity;
import epicsquid.mysticalworld.entity.model.ModelHolder;
import epicsquid.mysticalworld.entity.model.SilkwormModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class SilkwormRenderer extends MobRenderer<SilkwormEntity, SilkwormModel> {
  private static final ResourceLocation SILKWORM_TEXTURE = new ResourceLocation(MysticalWorld.MODID, "textures/entity/silkworm.png");

  public SilkwormRenderer(EntityRendererManager rendermanagerIn, SilkwormModel model, float shadow) {
    super(rendermanagerIn, model, shadow);
  }

  @Nullable
  @Override
  protected ResourceLocation getEntityTexture(SilkwormEntity entity) {
    return SILKWORM_TEXTURE;
  }

  @Override
  protected void preRenderCallback(SilkwormEntity entitylivingbaseIn, float partialTickTime) {
    super.preRenderCallback(entitylivingbaseIn, partialTickTime);

    float scale = entitylivingbaseIn.getSize() / 120.0f;
    if (entitylivingbaseIn.isChild()) {
      scale = -0.2f;
    }
    GlStateManager.scalef(0.4F + scale, 0.6F + scale, 1F + scale);
  }

  public static class Factory implements IRenderFactory<SilkwormEntity> {
    @Override
    @Nonnull
    public SilkwormRenderer createRenderFor(@Nonnull EntityRendererManager manager) {
      return new SilkwormRenderer(manager, ModelHolder.silkwormModel, 0.15f);
    }
  }
}
