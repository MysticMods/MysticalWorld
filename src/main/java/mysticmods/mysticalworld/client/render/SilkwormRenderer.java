package mysticmods.mysticalworld.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import mysticmods.mysticalworld.MysticalWorld;
import mysticmods.mysticalworld.client.model.ModelHolder;
import mysticmods.mysticalworld.client.model.SilkwormModel;
import mysticmods.mysticalworld.entity.SilkwormEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nonnull;

public class SilkwormRenderer extends MobRenderer<SilkwormEntity, SilkwormModel> {
  private static final ResourceLocation SILKWORM_TEXTURE = new ResourceLocation(MysticalWorld.MODID, "textures/entity/silkworm.png");

  public SilkwormRenderer(EntityRendererProvider.Context context) {
    super(context, new SilkwormModel(context.bakeLayer(ModelHolder.SILKWORM)), 0.15f);
  }

  @Override
  protected void scale(SilkwormEntity entity, PoseStack matrix, float partialTickTime) {
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
}
