package epicsquid.mysticalworld.integration.jei;

import epicsquid.mysticalworld.MysticalWorld;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.util.ResourceLocation;

import java.util.Collections;

@JeiPlugin
public class MWJEIPlugin implements IModPlugin {
  private static final ResourceLocation UID = new ResourceLocation(MysticalWorld.MODID, MysticalWorld.MODID);

  private GallAppleCropCategory gallAppleCropCategory;

  @Override
  public ResourceLocation getPluginUid() {
    return UID;
  }

  @Override
  public void registerCategories(IRecipeCategoryRegistration registration) {
    IJeiHelpers helpers = registration.getJeiHelpers();
    IGuiHelper guiHelper = helpers.getGuiHelper();
    gallAppleCropCategory = new GallAppleCropCategory(guiHelper);
    registration.addRecipeCategories(gallAppleCropCategory);
  }

  @Override
  public void registerRecipes(IRecipeRegistration registration) {
    registration.addRecipes(Collections.singletonList(GallAppleCropCategory.INSTANCE), GallAppleCropCategory.UID);
  }
}
