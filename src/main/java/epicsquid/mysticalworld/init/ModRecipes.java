package epicsquid.mysticalworld.init;

import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.recipe.*;
import net.minecraft.item.crafting.*;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.fml.RegistryObject;

public class ModRecipes {
  public static RegistryObject<IRecipeSerializer<KnifeRecipe>> KNIFE_SERIALIZER = MysticalWorld.REGISTRY.registerRecipeSerializer("knife_recipe", () -> new SpecialRecipeSerializer<>(KnifeRecipe::new));
  public static RegistryObject<IRecipeSerializer<FlintAndSteelRecipe>> FLINT_SERIALIZER = MysticalWorld.REGISTRY.registerRecipeSerializer("flint_and_steel_recipe", () -> new SpecialRecipeSerializer<>(FlintAndSteelRecipe::new));
  public static RegistryObject<IRecipeSerializer<SpindleRecipe>> SPINDLE_SERIALIZER = MysticalWorld.REGISTRY.registerRecipeSerializer("spindle_recipe", () -> new SpecialRecipeSerializer<>(SpindleRecipe::new));
  public static RegistryObject<IRecipeSerializer<KnifeHornRecipe>> HORN_SERIALIZER = MysticalWorld.REGISTRY.registerRecipeSerializer("knife_horn_recipe", () -> new SpecialRecipeSerializer<>(KnifeHornRecipe::new));
  public static RegistryObject<IRecipeSerializer<BlazeRocketRecipe>> BLAZE_SERIALIZER = MysticalWorld.REGISTRY.registerRecipeSerializer("blaze_rocket_recipe", () -> new SpecialRecipeSerializer<>(BlazeRocketRecipe::new));

  private static <T extends IRecipe<?>> IRecipeType<T> register (String key) {
    return Registry.register(Registry.RECIPE_TYPE, new ResourceLocation(MysticalWorld.MODID, key), new IRecipeType<T>() {
      @Override
      public String toString() {
        return key;
      }
    });
  }

  public static void load () {

  }
}
