package epicsquid.mysticalworld.entity.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.entity.OwlEntity;
import epicsquid.mysticalworld.entity.SilkwormEntity;
import epicsquid.mysticalworld.entity.model.ModelHolder;
import epicsquid.mysticalworld.entity.model.SilkwormModel;
import net.minecraft.client.renderer.IRenderTypeBuffer;
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

  @Override
  public void render(SilkwormEntity entity, float yaw, float partialTicks, MatrixStack matrix, IRenderTypeBuffer buffer, int light)  {
    matrix.push();
    float scale = entity.getSize() / 120.0f;
    if (entity.isChild()) {
      scale = -0.2f;
    }
    matrix.scale(0.4f+scale, 0.6f+scale, 1f+scale);
    super.render(entity, yaw, partialTicks, matrix, buffer, light);
    matrix.pop();
  }

  @Override
  @Nonnull
  public ResourceLocation getEntityTexture(@Nonnull SilkwormEntity entity) {
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
