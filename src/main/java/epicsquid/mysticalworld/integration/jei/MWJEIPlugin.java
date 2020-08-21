package epicsquid.mysticalworld.integration.jei;

import epicsquid.mysticalworld.MysticalWorld;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.IVanillaCategoryExtensionRegistration;
import mezz.jei.api.runtime.IJeiRuntime;
import net.minecraft.util.ResourceLocation;

@JeiPlugin
public class MWJEIPlugin implements IModPlugin {
  private static final ResourceLocation UID = new ResourceLocation(MysticalWorld.MODID, MysticalWorld.MODID);

  @Override
  public ResourceLocation getPluginUid() {
    return UID;
  }

  @Override
  public void onRuntimeAvailable(IJeiRuntime jeiRuntime) {
    /*if (ModList.get().isLoaded("jeresources")) {
      JERIntegration.init();
    } <-- Re-enable when JER API is supported. */
  }

  @Override
  public void registerVanillaCategoryExtensions(IVanillaCategoryExtensionRegistration registration) {
  }

  @Override
  public void registerRecipes(IRecipeRegistration registration) {
  }
}
