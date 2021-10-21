package mysticmods.mysticalworld.entity.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import mysticmods.mysticalworld.MysticalWorld;
import mysticmods.mysticalworld.entity.LavaCatEntity;
import mysticmods.mysticalworld.entity.model.LavaCatModel;
import mysticmods.mysticalworld.entity.model.ModelHolder;
import mysticmods.mysticalworld.entity.render.layers.LayerEyes;
import mysticmods.mysticalworld.entity.render.layers.LayerLava;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nonnull;

@OnlyIn(Dist.CLIENT)
public class LavaCatRenderer extends MobRenderer<LavaCatEntity, LavaCatModel> {
  private static final ResourceLocation OBSIDIAN_KITTY = new ResourceLocation(MysticalWorld.MODID, "textures/entity/magma_cat_eyes.png");

  public LavaCatRenderer(EntityRendererManager rendermanagerIn, LavaCatModel model, float shadowsizeIn) {
    super(rendermanagerIn, model, shadowsizeIn);
    this.addLayer(new LayerLava(this));
    this.addLayer(new LayerEyes(this));
  }

  @Nonnull
  @Override
  public ResourceLocation getTextureLocation(@Nonnull LavaCatEntity entity) {
    return OBSIDIAN_KITTY;
  }

  @Override
  protected void scale(@Nonnull LavaCatEntity cat, MatrixStack matrix, float tick) {
    super.scale(cat, matrix, tick);
    matrix.scale(0.9F, 0.9F, 0.9F);
  }

  public static class Factory implements IRenderFactory<LavaCatEntity> {

    @Override
    @Nonnull
    public LavaCatRenderer createRenderFor(@Nonnull EntityRendererManager manager) {
      return new LavaCatRenderer(manager, ModelHolder.lavaCatModel, 0.5f);
    }
  }

}

