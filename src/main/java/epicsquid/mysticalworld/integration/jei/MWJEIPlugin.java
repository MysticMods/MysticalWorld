package epicsquid.mysticalworld.integration.jei;

import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.recipe.FlintAndSteelRecipe;
import epicsquid.mysticalworld.recipe.KnifeRecipe;
import epicsquid.mysticalworld.recipe.SpindleRecipe;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IVanillaCategoryExtensionRegistration;
import net.minecraft.util.ResourceLocation;

@JeiPlugin
public class MWJEIPlugin implements IModPlugin {
  private static final ResourceLocation UID = new ResourceLocation(MysticalWorld.MODID, MysticalWorld.MODID);

  @Override
  public ResourceLocation getPluginUid() {
    return UID;
  }

  @Override
  public void registerVanillaCategoryExtensions(IVanillaCategoryExtensionRegistration registration) {
    registration.getCraftingCategory().addCategoryExtension(KnifeRecipe.class, SpecialRecipeWrapper<KnifeRecipe>::new);
    registration.getCraftingCategory().addCategoryExtension(SpindleRecipe.class, SpecialRecipeWrapper<SpindleRecipe>::new);
    registration.getCraftingCategory().addCategoryExtension(FlintAndSteelRecipe.class, SpecialRecipeWrapper<FlintAndSteelRecipe>::new);
  }
}
