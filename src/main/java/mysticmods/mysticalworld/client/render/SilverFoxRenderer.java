package mysticmods.mysticalworld.client.render;

import mysticmods.mysticalworld.MysticalWorld;
import mysticmods.mysticalworld.client.model.ModelHolder;
import mysticmods.mysticalworld.client.model.SilverFoxModel;
import mysticmods.mysticalworld.entity.SilverFoxEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nonnull;

public class SilverFoxRenderer extends MobRenderer<SilverFoxEntity, SilverFoxModel> {

  public SilverFoxRenderer(@Nonnull EntityRendererProvider.Context context) {
    super(context, new SilverFoxModel(context.bakeLayer(ModelHolder.SILVER_FOX)), 0.25f);
  }

  @Override
  @Nonnull
  public ResourceLocation getTextureLocation(@Nonnull SilverFoxEntity entity) {
    return new ResourceLocation(MysticalWorld.MODID + ":textures/entity/fox.png");
  }
}
