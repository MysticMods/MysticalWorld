package epicsquid.mysticalworld.entity.render.layers;

import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.entity.EntityLavaCat;
import epicsquid.mysticalworld.entity.render.RenderLavaCat;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class LayerEyes implements LayerRenderer<EntityLavaCat> {
  private static final ResourceLocation LAVA_EYES_TEXTURE = new ResourceLocation(MysticalWorld.MODID, "textures/entity/magma_cat_eyes.png");
  private static final ResourceLocation OBSIDIAN_EYES_TEXTURE = new ResourceLocation(MysticalWorld.MODID, "textures/entity/magma_cat_obsidian_eyes.png");

  private RenderLavaCat renderer;

  public LayerEyes(RenderLavaCat renderer) {
    this.renderer = renderer;
  }

  @Override
  public void doRenderLayer(EntityLavaCat entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
    if (entitylivingbaseIn.getIsLava()) {
      this.renderer.bindTexture(LAVA_EYES_TEXTURE);
    } else {
      this.renderer.bindTexture(OBSIDIAN_EYES_TEXTURE);
    }
    this.renderer.getMainModel().render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
  }

  @Override
  public boolean shouldCombineTextures() {
    return false;
  }
}

