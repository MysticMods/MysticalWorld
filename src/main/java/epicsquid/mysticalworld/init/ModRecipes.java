package epicsquid.mysticalworld.init;

import com.tterrag.registrate.util.entry.RegistryEntry;
import epicsquid.mysticalworld.recipe.BlazeRocketRecipe;
import epicsquid.mysticalworld.recipe.EmptyRecipe;
import epicsquid.mysticalworld.recipe.ShapedDamageRecipe;
import epicsquid.mysticalworld.recipe.ShapelessDamageRecipe;
import net.minecraft.item.crafting.SpecialRecipeSerializer;

import static epicsquid.mysticalworld.MysticalWorld.REGISTRATE;

// TODO: Done

public class ModRecipes {
  public static RegistryEntry<ShapedDamageRecipe.Serializer> SHAPED_DAMAGE_SERIALIZER = REGISTRATE.recipeSerializer("shaped_damage_recipe", ShapedDamageRecipe.Serializer::new).register();

  public static RegistryEntry<ShapelessDamageRecipe.Serializer> SHAPELESS_DAMAGE_SERIALIZER = REGISTRATE.recipeSerializer("shapeless_damage_recipe", ShapelessDamageRecipe.Serializer::new).register();

  public static RegistryEntry<SpecialRecipeSerializer<BlazeRocketRecipe>> BLAZE_SERIALIZER = REGISTRATE.recipeSerializer("blaze_rocket_recipe", () -> new SpecialRecipeSerializer<>(BlazeRocketRecipe::new)).register();

  public static RegistryEntry<SpecialRecipeSerializer<EmptyRecipe>> EMPTY_SERIALIZER = REGISTRATE.recipeSerializer("empty_recipe", () -> new SpecialRecipeSerializer<>(EmptyRecipe::new)).register();

  public static void load() {

  }
}
