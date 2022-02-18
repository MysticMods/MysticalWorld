package mysticmods.mysticalworld.recipe;

import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.GsonHelper;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistryEntry;

@SuppressWarnings("NullableProblems")
public class ShapedDamageRecipe extends ShapedRecipe implements IDamageRecipe {
  private final Ingredient damageItem;
  private final Ingredient damageIngredient;
  private final int damageAmount;

  private ShapedDamageRecipe(ResourceLocation idIn, String groupIn, int recipeWidthIn, int recipeHeightIn, NonNullList<Ingredient> recipeItemsIn, ItemStack recipeOutputIn, Ingredient damageItem, int damageAmount) {
    super(idIn, groupIn, recipeWidthIn, recipeHeightIn, recipeItemsIn, recipeOutputIn);
    this.damageItem = damageItem;
    this.damageAmount = damageAmount;
    this.damageIngredient = createDamageIngredient(damageItem);
  }

  @Override
  public NonNullList<ItemStack> getRemainingItems(CraftingContainer inv) {
    return getRemainingItems(inv, damageIngredient, damageAmount);
  }

  public static ShapedDamageRecipe create(ShapedRecipe recipe, Ingredient damageItem, int damageAmount) {
    return new ShapedDamageRecipe(recipe.getId(), recipe.getGroup(), recipe.getRecipeWidth(), recipe.getRecipeHeight(), recipe.getIngredients(), recipe.getResultItem(), damageItem, damageAmount);
  }

  public static class Serializer extends ForgeRegistryEntry<RecipeSerializer<?>> implements RecipeSerializer<ShapedDamageRecipe> {

    @Override
    public ShapedDamageRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
      ShapedRecipe result = RecipeSerializer.SHAPED_RECIPE.fromJson(recipeId, json);
      Ingredient damageItem = Ingredient.fromJson(json.get(IDamageRecipe.TAG));
      int damageAmount = GsonHelper.getAsInt(json, IDamageRecipe.DAMAGE, -1);
      if (damageAmount == -1) {
        throw new JsonSyntaxException("Invalid damage_amount for ShapedDamageRecipe.");
      }
      return ShapedDamageRecipe.create(result, damageItem, damageAmount);
    }

    @Override
    public ShapedDamageRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
      ShapedRecipe result = RecipeSerializer.SHAPED_RECIPE.fromNetwork(recipeId, buffer);
      Ingredient damageItem = Ingredient.fromNetwork(buffer);
      int damageAmount = buffer.readInt();
      return ShapedDamageRecipe.create(result, damageItem, damageAmount);
    }

    @Override
    public void toNetwork(FriendlyByteBuf buffer, ShapedDamageRecipe recipe) {
      RecipeSerializer.SHAPED_RECIPE.toNetwork(buffer, recipe);
      recipe.damageItem.toNetwork(buffer);
      buffer.writeInt(recipe.damageAmount);
    }
  }
}
