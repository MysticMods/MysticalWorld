package epicsquid.mysticalworld.init;

import com.tterrag.registrate.util.RegistryEntry;
import epicsquid.mysticalworld.recipe.*;
import net.minecraft.item.crafting.SpecialRecipeSerializer;

import static epicsquid.mysticalworld.MysticalWorld.REGISTRATE;

public class ModRecipes {
  public static RegistryEntry<SpecialRecipeSerializer<KnifeRecipe>> KNIFE_SERIALIZER = REGISTRATE.recipeSerializer("knife_recipe", () -> new SpecialRecipeSerializer<>(KnifeRecipe::new)).register();

  public static RegistryEntry<SpecialRecipeSerializer<FlintAndSteelRecipe>> FLINT_SERIALIZER = REGISTRATE.recipeSerializer("flint_and_steel_recipe", () -> new SpecialRecipeSerializer<>(FlintAndSteelRecipe::new)).register();

  public static RegistryEntry<SpecialRecipeSerializer<SpindleRecipe>> SPINDLE_SERIALIZER = REGISTRATE.recipeSerializer("knife_recipe", () -> new SpecialRecipeSerializer<>(SpindleRecipe::new)).register();

  public static RegistryEntry<SpecialRecipeSerializer<KnifeHornRecipe>> HORN_SERIALIZER = REGISTRATE.recipeSerializer("knife_horn_recipe", () -> new SpecialRecipeSerializer<>(KnifeHornRecipe::new)).register();

  public static RegistryEntry<SpecialRecipeSerializer<BlazeRocketRecipe>> BLAZE_SERIALIZER = REGISTRATE.recipeSerializer("blaze_rocket_recipe", () -> new SpecialRecipeSerializer<>(BlazeRocketRecipe::new)).register();

  public static RegistryEntry<SpecialRecipeSerializer<EmptyRecipe>> EMPTY_SERIALIZER = REGISTRATE.recipeSerializer("empty_recipe", () -> new SpecialRecipeSerializer<>(EmptyRecipe::new)).register();

  public static void load() {

  }
}
