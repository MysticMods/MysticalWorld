package mysticmods.mysticalworld.recipe;

import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.ShapelessRecipe;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.GsonHelper;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class ShapelessDamageRecipe extends ShapelessRecipe implements IDamageRecipe {
  private final Ingredient damageItem;
  private final Ingredient damageIngredient;
  private final int damageAmount;

  public ShapelessDamageRecipe(ResourceLocation idIn, String groupIn, ItemStack recipeOutputIn, NonNullList<Ingredient> recipeItemsIn, Ingredient damageItem, int damageAmount) {
    super(idIn, groupIn, recipeOutputIn, recipeItemsIn);
    this.damageItem = damageItem;
    this.damageAmount = damageAmount;
    this.damageIngredient = createDamageIngredient(damageItem);
  }

  @Override
  public NonNullList<ItemStack> getRemainingItems(CraftingContainer inv) {
    return getRemainingItems(inv, damageIngredient, damageAmount);
  }

  public static ShapelessDamageRecipe create(ShapelessRecipe recipe, Ingredient damageItem, int damageAmount) {
    return new ShapelessDamageRecipe(recipe.getId(), recipe.getGroup(), recipe.getResultItem(), recipe.getIngredients(), damageItem, damageAmount);
  }

  public static class Serializer extends ForgeRegistryEntry<RecipeSerializer<?>> implements RecipeSerializer<ShapelessDamageRecipe> {

    @Override
    public ShapelessDamageRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
      ShapelessRecipe result = RecipeSerializer.SHAPELESS_RECIPE.fromJson(recipeId, json);
      Ingredient damageItem = Ingredient.fromJson(json.get(TAG));
      int damageAmount = GsonHelper.getAsInt(json, DAMAGE, -1);
      if (damageAmount == -1) {
        throw new JsonSyntaxException("Invalid damage_amount for ShapelessDamageRecipe.");
      }
      return ShapelessDamageRecipe.create(result, damageItem, damageAmount);
    }

    @Override
    public ShapelessDamageRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
      ShapelessRecipe result = RecipeSerializer.SHAPELESS_RECIPE.fromNetwork(recipeId, buffer);
      Ingredient damageItem = Ingredient.fromNetwork(buffer);
      int damageAmount = buffer.readInt();
      return ShapelessDamageRecipe.create(result, damageItem, damageAmount);
    }

    @Override
    public void toNetwork(FriendlyByteBuf buffer, ShapelessDamageRecipe recipe) {
      RecipeSerializer.SHAPELESS_RECIPE.toNetwork(buffer, recipe);
      recipe.damageItem.toNetwork(buffer);
      buffer.writeInt(recipe.damageAmount);
    }
  }
}
