package mysticmods.mysticalworld.client.render;

import mysticmods.mysticalworld.MysticalWorld;
import mysticmods.mysticalworld.client.model.ClamModel;
import mysticmods.mysticalworld.client.model.ModelHolder;
import mysticmods.mysticalworld.entity.ClamEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class ClamRenderer extends MobRenderer<ClamEntity, ClamModel> {
  public ClamRenderer(EntityRendererProvider.Context context) {
    super(context, new ClamModel(context.bakeLayer(ModelHolder.CLAM)), 0.5F);
  }

  @Override
  public ResourceLocation getTextureLocation(ClamEntity pEntity) {
    return new ResourceLocation(MysticalWorld.MODID, "textures/entity/clam.png");
  }
}