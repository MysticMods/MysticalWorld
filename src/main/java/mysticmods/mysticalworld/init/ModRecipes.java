package mysticmods.mysticalworld.init;

import com.tterrag.registrate.providers.ProviderType;
import com.tterrag.registrate.providers.RegistrateRecipeProvider;
import com.tterrag.registrate.util.entry.RegistryEntry;
import mysticmods.mysticalworld.recipe.BlazeRocketRecipe;
import mysticmods.mysticalworld.recipe.EmptyRecipe;
import mysticmods.mysticalworld.recipe.ShapedDamageRecipe;
import mysticmods.mysticalworld.recipe.ShapelessDamageRecipe;
import mysticmods.mysticalworld.MysticalWorld;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.item.crafting.SpecialRecipeSerializer;
import net.minecraft.util.ResourceLocation;

// TODO: Done

public class ModRecipes {
  public static RegistryEntry<ShapedDamageRecipe.Serializer> SHAPED_DAMAGE_SERIALIZER = MysticalWorld.REGISTRATE.recipeSerializer("shaped_damage_recipe", ShapedDamageRecipe.Serializer::new).register();

  public static RegistryEntry<ShapelessDamageRecipe.Serializer> SHAPELESS_DAMAGE_SERIALIZER = MysticalWorld.REGISTRATE.recipeSerializer("shapeless_damage_recipe", ShapelessDamageRecipe.Serializer::new).register();

  public static RegistryEntry<SpecialRecipeSerializer<BlazeRocketRecipe>> BLAZE_SERIALIZER = MysticalWorld.REGISTRATE.recipeSerializer("blaze_rocket_recipe", () -> new SpecialRecipeSerializer<>(BlazeRocketRecipe::new)).register();

  public static RegistryEntry<SpecialRecipeSerializer<EmptyRecipe>> EMPTY_SERIALIZER = MysticalWorld.REGISTRATE.recipeSerializer("empty_recipe", () -> new SpecialRecipeSerializer<>(EmptyRecipe::new)).register();

  public static void load() {

  }
}
