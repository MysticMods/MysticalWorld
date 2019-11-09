package epicsquid.mysticalworld.entity.render.layers;

import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.entity.LavaCatEntity;
import epicsquid.mysticalworld.entity.model.LavaCatModel;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LayerEyes extends LayerRenderer<LavaCatEntity, LavaCatModel> {
  private static final ResourceLocation LAVA_EYES_TEXTURE = new ResourceLocation(MysticalWorld.MODID, "textures/entity/magma_cat_eyes.png");
  private static final ResourceLocation OBSIDIAN_EYES_TEXTURE = new ResourceLocation(MysticalWorld.MODID, "textures/entity/magma_cat_obsidian_eyes.png");

  private IEntityRenderer<LavaCatEntity, LavaCatModel> renderer;

  public LayerEyes(IEntityRenderer<LavaCatEntity, LavaCatModel> entityRendererIn) {
    super(entityRendererIn);
    this.renderer = entityRendererIn;
  }

  @Override
  public void render(LavaCatEntity entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
    if (entitylivingbaseIn.getIsLava()) {
      this.renderer.bindTexture(LAVA_EYES_TEXTURE);
    } else {
      this.renderer.bindTexture(OBSIDIAN_EYES_TEXTURE);
    }
    this.renderer.getEntityModel().render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
  }

  @Override
  public boolean shouldCombineTextures() {
    return false;
  }
}

