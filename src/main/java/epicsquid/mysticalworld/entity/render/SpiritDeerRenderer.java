package epicsquid.mysticalworld.entity.render;

import epicsquid.mysticalworld.entity.SpiritDeerEntity;
import epicsquid.mysticalworld.entity.model.DeerModel;
import epicsquid.mysticalworld.entity.model.ModelHolder;
import epicsquid.mysticalworld.entity.model.SpiritDeerModel;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nonnull;

public class SpiritDeerRenderer extends MobRenderer<SpiritDeerEntity, SpiritDeerModel> {

  private SpiritDeerRenderer(@Nonnull EntityRendererManager renderManager, @Nonnull SpiritDeerModel modelBase, float shadowSize) {
    super(renderManager, modelBase, shadowSize);
  }

  @Override
  @Nonnull
  public ResourceLocation getEntityTexture(@Nonnull SpiritDeerEntity entity) {
    return new ResourceLocation("mysticalworld:textures/entity/spirit_deer.png");
  }

  public static class Factory implements IRenderFactory<SpiritDeerEntity> {

    @Override
    public EntityRenderer<SpiritDeerEntity> createRenderFor(EntityRendererManager manager) {
      return new SpiritDeerRenderer(manager, ModelHolder.spiritDeerModel, 0.35f);
    }
  }
}
