package epicsquid.mysticalworld.init;

import com.tterrag.registrate.util.entry.RegistryEntry;
import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.recipe.*;
import epicsquid.mysticalworld.recipe.ingredients.SeedIngredient;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.SpecialRecipeSerializer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.event.RegistryEvent;

import static epicsquid.mysticalworld.MysticalWorld.REGISTRATE;

// TODO: Done

public class ModRecipes {
/*
  public static RegistryEntry<SpecialRecipeSerializer<KnifeRecipe>> KNIFE_SERIALIZER = REGISTRATE.recipeSerializer("knife_recipe", () -> new SpecialRecipeSerializer<>(KnifeRecipe::new)).register();

  public static RegistryEntry<SpecialRecipeSerializer<FlintAndSteelRecipe>> FLINT_SERIALIZER = REGISTRATE.recipeSerializer("flint_and_steel_recipe", () -> new SpecialRecipeSerializer<>(FlintAndSteelRecipe::new)).register();

  public static RegistryEntry<SpecialRecipeSerializer<SpindleRecipe>> SPINDLE_SERIALIZER = REGISTRATE.recipeSerializer("spindle_recipe", () -> new SpecialRecipeSerializer<>(SpindleRecipe::new)).register();

  public static RegistryEntry<SpecialRecipeSerializer<KnifeHornRecipe>> HORN_SERIALIZER = REGISTRATE.recipeSerializer("knife_horn_recipe", () -> new SpecialRecipeSerializer<>(KnifeHornRecipe::new)).register();
*/

  public static RegistryEntry<ShapedDamageRecipe.Serializer> SHAPED_DAMAGE_SERIALIZER = REGISTRATE.recipeSerializer("shaped_damage_recipe", ShapedDamageRecipe.Serializer::new).register();

  public static RegistryEntry<ShapelessDamageRecipe.Serializer> SHAPELESS_DAMAGE_SERIALIZER = REGISTRATE.recipeSerializer("shapeless_damage_recipe", ShapelessDamageRecipe.Serializer::new).register();

  public static RegistryEntry<SpecialRecipeSerializer<BlazeRocketRecipe>> BLAZE_SERIALIZER = REGISTRATE.recipeSerializer("blaze_rocket_recipe", () -> new SpecialRecipeSerializer<>(BlazeRocketRecipe::new)).register();

  public static RegistryEntry<SpecialRecipeSerializer<EmptyRecipe>> EMPTY_SERIALIZER = REGISTRATE.recipeSerializer("empty_recipe", () -> new SpecialRecipeSerializer<>(EmptyRecipe::new)).register();

/*  public static RegistryEntry<AssortedSeedsRecipe.Serializer> SEED_SERIALIZER = REGISTRATE.recipeSerializer(AssortedSeedsRecipe.SEEDS_RECIPE.getPath(), AssortedSeedsRecipe.Serializer::new).register();*/

  public static void load() {

  }
}
