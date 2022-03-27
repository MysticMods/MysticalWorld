package mysticmods.mysticalworld.init;

import com.tterrag.registrate.util.entry.RegistryEntry;
import mysticmods.mysticalworld.MysticalWorld;
import mysticmods.mysticalworld.recipe.BlazeRocketRecipe;
import mysticmods.mysticalworld.recipe.EmptyRecipe;
import mysticmods.mysticalworld.recipe.ShapedDamageRecipe;
import mysticmods.mysticalworld.recipe.ShapelessDamageRecipe;
import net.minecraft.world.item.crafting.SimpleRecipeSerializer;
import noobanidus.libs.noobutil.recipe.UniqueShapelessRecipe;

public class ModRecipes {
  public static RegistryEntry<ShapedDamageRecipe.Serializer> SHAPED_DAMAGE_SERIALIZER = MysticalWorld.REGISTRATE.recipeSerializer("shaped_damage_recipe", ShapedDamageRecipe.Serializer::new).register();

  public static RegistryEntry<ShapelessDamageRecipe.Serializer> SHAPELESS_DAMAGE_SERIALIZER = MysticalWorld.REGISTRATE.recipeSerializer("shapeless_damage_recipe", ShapelessDamageRecipe.Serializer::new).register();

  public static RegistryEntry<SimpleRecipeSerializer<BlazeRocketRecipe>> BLAZE_SERIALIZER = MysticalWorld.REGISTRATE.recipeSerializer("blaze_rocket_recipe", () -> new SimpleRecipeSerializer<>(BlazeRocketRecipe::new)).register();

  public static RegistryEntry<SimpleRecipeSerializer<EmptyRecipe>> EMPTY_SERIALIZER = MysticalWorld.REGISTRATE.recipeSerializer("empty_recipe", () -> new SimpleRecipeSerializer<>(EmptyRecipe::new)).register();

  public static RegistryEntry<UniqueShapelessRecipe.Serializer> UNIQUE_SHAPELESS_RECIPE = MysticalWorld.REGISTRATE.recipeSerializer("unique_shapeless", UniqueShapelessRecipe.Serializer::new).register();

  public static void load() {

  }
}
