package epicsquid.mysticalworld.entity.render;

import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.entity.EntityLavaCat;
import epicsquid.mysticalworld.entity.model.ModelHolder;
import epicsquid.mysticalworld.entity.render.layers.LayerEyes;
import epicsquid.mysticalworld.entity.render.layers.LayerLava;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class RenderLavaCat extends RenderLiving<EntityLavaCat> {
  public static ResourceLocation OBSIDIAN_KITTY = new ResourceLocation(MysticalWorld.MODID, "textures/entity/magma_cat_eyes.png");

  public RenderLavaCat(RenderManager rendermanagerIn, ModelBase modelbaseIn, float shadowsizeIn) {
    super(rendermanagerIn, modelbaseIn, shadowsizeIn);
    this.addLayer(new LayerLava(this));
    this.addLayer(new LayerEyes(this));
  }

  @Nullable
  @Override
  protected ResourceLocation getEntityTexture(EntityLavaCat entity) {
    return OBSIDIAN_KITTY;
  }

  protected void preRenderCallback(EntityLavaCat entitylivingbaseIn, float partialTickTime) {
    super.preRenderCallback(entitylivingbaseIn, partialTickTime);

    if (entitylivingbaseIn.isTamed()) {
      GlStateManager.scale(0.8F, 0.8F, 0.8F);
    }
  }

  public static class Factory implements IRenderFactory {

    @Override
    @Nonnull
    public RenderLavaCat createRenderFor(@Nonnull RenderManager manager) {
      return new RenderLavaCat(manager, ModelHolder.models.get("magma_cat"), 0.5f);
    }
  }

}

