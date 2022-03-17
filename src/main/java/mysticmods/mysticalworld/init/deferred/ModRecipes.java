package mysticmods.mysticalworld.init.deferred;

import mysticmods.mysticalworld.MysticalWorld;
import mysticmods.mysticalworld.recipe.BlazeRocketRecipe;
import mysticmods.mysticalworld.recipe.EmptyRecipe;
import mysticmods.mysticalworld.recipe.ShapedDamageRecipe;
import mysticmods.mysticalworld.recipe.ShapelessDamageRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleRecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import noobanidus.libs.noobutil.recipe.UniqueShapelessRecipe;

public class ModRecipes {
  private static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZER = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, MysticalWorld.MODID);

  public static void register(IEventBus bus) {
    RECIPE_SERIALIZER.register(bus);
  }

  public static final RegistryObject<ShapedDamageRecipe.Serializer> SHAPED_DAMAGE_SERIALIZER = RECIPE_SERIALIZER.register("shaped_damage_recipe", ShapedDamageRecipe.Serializer::new);

  public static final RegistryObject<ShapelessDamageRecipe.Serializer> SHAPELESS_DAMAGE_SERIALIZER = RECIPE_SERIALIZER.register("shapeless_damage_recipe", ShapelessDamageRecipe.Serializer::new);

  public static final RegistryObject<SimpleRecipeSerializer<BlazeRocketRecipe>> BLAZE_SERIALIZER = RECIPE_SERIALIZER.register("blaze_rocket_recipe", () -> new SimpleRecipeSerializer<>(BlazeRocketRecipe::new));

  public static final RegistryObject<SimpleRecipeSerializer<EmptyRecipe>> EMPTY_SERIALIZER = RECIPE_SERIALIZER.register("empty_recipe", () -> new SimpleRecipeSerializer<>(EmptyRecipe::new));

  public static final RegistryObject<UniqueShapelessRecipe.Serializer> UNIQUE_SHAPELESS_RECIPE = RECIPE_SERIALIZER.register("unique_shapeless", UniqueShapelessRecipe.Serializer::new);
}
