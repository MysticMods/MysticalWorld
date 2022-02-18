package mysticmods.mysticalworld.client.render;

import mysticmods.mysticalworld.MysticalWorld;
import mysticmods.mysticalworld.client.model.ClamModel;
import mysticmods.mysticalworld.client.model.ModelHolder;
import mysticmods.mysticalworld.entity.ClamEntity;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nonnull;

public class ClamRenderer extends MobRenderer<ClamEntity, ClamModel> {
  public ClamRenderer(EntityRenderDispatcher pManager, ClamModel pModel, float pScale) {
    super(pManager, pModel, pScale);
  }

  @Override
  public ResourceLocation getTextureLocation(ClamEntity pEntity) {
    return new ResourceLocation(MysticalWorld.MODID, "textures/entity/clam.png");
  }

  public static class Factory implements IRenderFactory<ClamEntity> {
    @Override
    @Nonnull
    public ClamRenderer createRenderFor(@Nonnull EntityRenderDispatcher manager) {
      return new ClamRenderer(manager, ModelHolder.clamModel, 0.5f);
    }
  }
}
