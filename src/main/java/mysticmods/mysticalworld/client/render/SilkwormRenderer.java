package mysticmods.mysticalworld.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import mysticmods.mysticalworld.MysticalWorld;
import mysticmods.mysticalworld.entity.SilkwormEntity;
import mysticmods.mysticalworld.client.model.ModelHolder;
import mysticmods.mysticalworld.client.model.SilkwormModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nonnull;

public class SilkwormRenderer extends MobRenderer<SilkwormEntity, SilkwormModel> {
  private static final ResourceLocation SILKWORM_TEXTURE = new ResourceLocation(MysticalWorld.MODID, "textures/entity/silkworm.png");

  public SilkwormRenderer(EntityRendererManager rendermanagerIn, SilkwormModel model, float shadow) {
    super(rendermanagerIn, model, shadow);
  }

  @Override
  protected void scale(SilkwormEntity entity, MatrixStack matrix, float partialTickTime) {
    float scale = entity.getSize() / 120.0f;
    if (entity.isBaby()) {
      scale = -0.2f;
    }
    matrix.scale(0.4f + scale, 0.6f + scale, 1f + scale);
  }

  @Override
  @Nonnull
  public ResourceLocation getTextureLocation(@Nonnull SilkwormEntity entity) {
    return SILKWORM_TEXTURE;
  }

  public static class Factory implements IRenderFactory<SilkwormEntity> {
    @Override
    @Nonnull
    public SilkwormRenderer createRenderFor(@Nonnull EntityRendererManager manager) {
      return new SilkwormRenderer(manager, ModelHolder.silkwormModel, 0.15f);
    }
  }
}
