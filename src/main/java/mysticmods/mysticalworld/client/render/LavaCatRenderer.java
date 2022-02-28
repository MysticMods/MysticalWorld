package mysticmods.mysticalworld.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import mysticmods.mysticalworld.MysticalWorld;
import mysticmods.mysticalworld.client.model.LavaCatModel;
import mysticmods.mysticalworld.client.model.ModelHolder;
import mysticmods.mysticalworld.client.render.layers.LayerEyes;
import mysticmods.mysticalworld.client.render.layers.LayerLava;
import mysticmods.mysticalworld.entity.LavaCatEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nonnull;

public class LavaCatRenderer extends MobRenderer<LavaCatEntity, LavaCatModel> {
  private static final ResourceLocation OBSIDIAN_KITTY = new ResourceLocation(MysticalWorld.MODID, "textures/entity/magma_cat_eyes.png");

  public LavaCatRenderer(@Nonnull EntityRendererProvider.Context context) {
    super(context, new LavaCatModel(context.bakeLayer(ModelHolder.LAVA_CAT)), 0.5f);
    this.addLayer(new LayerLava(this));
    this.addLayer(new LayerEyes(this));
  }

  @Nonnull
  @Override
  public ResourceLocation getTextureLocation(@Nonnull LavaCatEntity entity) {
    return OBSIDIAN_KITTY;
  }

  @Override
  protected void scale(@Nonnull LavaCatEntity cat, PoseStack matrix, float tick) {
    super.scale(cat, matrix, tick);
    matrix.scale(0.9F, 0.9F, 0.9F);
  }
}
