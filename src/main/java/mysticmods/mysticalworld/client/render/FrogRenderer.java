package mysticmods.mysticalworld.client.render;

import mysticmods.mysticalworld.MysticalWorld;
import mysticmods.mysticalworld.client.model.FrogModel;
import mysticmods.mysticalworld.client.model.ModelHolder;
import mysticmods.mysticalworld.entity.FrogEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nonnull;

public class FrogRenderer extends MobRenderer<FrogEntity, FrogModel> {

  public FrogRenderer(@Nonnull EntityRendererProvider.Context context) {
    super(context, new FrogModel(context.bakeLayer(ModelHolder.FROG)), 0.125F);
  }

  @Override
  @Nonnull
  public ResourceLocation getTextureLocation(@Nonnull FrogEntity entity) {
    return new ResourceLocation(MysticalWorld.MODID + ":textures/entity/" + (entity.getId() % 2 == 0 ? "frog" : "toad") + ".png");
  }
}
