package epicsquid.mysticalworld.integration.jei;

import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.recipe.FlintAndSteelRecipe;
import epicsquid.mysticalworld.recipe.KnifeRecipe;
import epicsquid.mysticalworld.recipe.SpindleRecipe;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.IVanillaCategoryExtensionRegistration;
import net.minecraft.util.ResourceLocation;

import java.util.Arrays;

@JeiPlugin
public class MWJEIPlugin implements IModPlugin {
  private static final ResourceLocation UID = new ResourceLocation(MysticalWorld.MODID, MysticalWorld.MODID);

  @Override
  public ResourceLocation getPluginUid() {
    return UID;
  }

  @Override
  public void registerCategories(IRecipeCategoryRegistration registration) {

  }

  @Override
  public void registerVanillaCategoryExtensions(IVanillaCategoryExtensionRegistration registration) {
    registration.getCraftingCategory().addCategoryExtension(KnifeRecipe.class, KnifeWrapper::new);
    registration.getCraftingCategory().addCategoryExtension(SpindleRecipe.class, SpindleWrapper::new);
    registration.getCraftingCategory().addCategoryExtension(FlintAndSteelRecipe.class, FlintAndSteelWrapper::new);
  }

  @Override
  public void registerRecipes(IRecipeRegistration registration) {
    registration.addRecipes(Arrays.asList(
        new KnifeRecipe(new ResourceLocation(MysticalWorld.MODID, "knife_temp")),
        new SpindleRecipe(new ResourceLocation(MysticalWorld.MODID, "spindle_temp")),
        new FlintAndSteelRecipe(new ResourceLocation(MysticalWorld.MODID, "flint_temp"))
        ), new ResourceLocation("minecraft", "crafting"));
  }
}
