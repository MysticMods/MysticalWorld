package epicsquid.mysticalworld.integration.jei;

import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.init.ModBlocks;
import epicsquid.mysticalworld.init.ModItems;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaRecipeCategoryUid;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.IVanillaCategoryExtensionRegistration;
import mezz.jei.api.runtime.IJeiRuntime;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Collections;

@JeiPlugin
public class MWJEIPlugin implements IModPlugin {
  private static final ResourceLocation UID = new ResourceLocation(MysticalWorld.MODID, MysticalWorld.MODID);

  private GallAppleCropCategory gallAppleCropCategory;

  private WaspAttractantCategory waspAttractantCategory;

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
    waspAttractantCategory = new WaspAttractantCategory(guiHelper);
    registration.addRecipeCategories(waspAttractantCategory);
  }

  @Override
  public void registerRecipes(IRecipeRegistration registration) {
    registration.addRecipes(Collections.singletonList(GallAppleCropCategory.INSTANCE), GallAppleCropCategory.UID);
    registration.addRecipes(Collections.singletonList(WaspAttractantCategory.INSTANCE), WaspAttractantCategory.UID);
  }

  @Override
  public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
    registration.addRecipeCatalyst(new ItemStack(ModItems.WASP_ATTRACTANT.get()), WaspAttractantCategory.UID);
  }
}
