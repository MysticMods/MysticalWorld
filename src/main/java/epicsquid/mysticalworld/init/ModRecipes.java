package epicsquid.mysticalworld.init;

import epicsquid.mysticalworld.MysticalWorld;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;

public class ModRecipes {
  private static <T extends IRecipe<?>> IRecipeType<T> register (String key) {
    return Registry.register(Registry.RECIPE_TYPE, new ResourceLocation(MysticalWorld.MODID, key), new IRecipeType<T>() {
      @Override
      public String toString() {
        return key;
      }
    });
  }
}
