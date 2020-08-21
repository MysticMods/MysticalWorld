package epicsquid.mysticalworld.entity.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.entity.LavaCatEntity;
import epicsquid.mysticalworld.entity.model.LavaCatModel;
import epicsquid.mysticalworld.entity.model.ModelHolder;
import epicsquid.mysticalworld.entity.render.layers.LayerEyes;
import epicsquid.mysticalworld.entity.render.layers.LayerLava;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nonnull;

@OnlyIn(Dist.CLIENT)
public class LavaCatRenderer extends MobRenderer<LavaCatEntity, LavaCatModel> {
  private static ResourceLocation OBSIDIAN_KITTY = new ResourceLocation(MysticalWorld.MODID, "textures/entity/magma_cat_eyes.png");

  public LavaCatRenderer(EntityRendererManager rendermanagerIn, LavaCatModel model, float shadowsizeIn) {
    super(rendermanagerIn, model, shadowsizeIn);
    this.addLayer(new LayerLava(this));
    this.addLayer(new LayerEyes(this));
  }

  @Nonnull
  @Override
  public ResourceLocation getEntityTexture(@Nonnull LavaCatEntity entity) {
    return OBSIDIAN_KITTY;
  }

  @Override
  protected void preRenderCallback(@Nonnull LavaCatEntity p_225620_1_, MatrixStack matrix, float p_225620_3_) {
    matrix.scale(1.2f, 1.2f, 1.2f);
  }

  public static class Factory implements IRenderFactory<LavaCatEntity> {

    @Override
    @Nonnull
    public LavaCatRenderer createRenderFor(@Nonnull EntityRendererManager manager) {
      return new LavaCatRenderer(manager, ModelHolder.lavaCatModel, 0.5f);
    }
  }

}

