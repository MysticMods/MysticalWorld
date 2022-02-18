package mysticmods.mysticalworld.recipe;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.GsonHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistryEntry;

@SuppressWarnings("ALL")
public abstract class AbstractCookingRecipeSerializer<T extends AbstractCookingRecipe> extends ForgeRegistryEntry<RecipeSerializer<?>> implements RecipeSerializer<T> {
  protected final int defaultCookTime;
  protected final IFactory<T> serializer;

  public AbstractCookingRecipeSerializer(IFactory<T> serializer, int defaultCookTime) {
    this.defaultCookTime = defaultCookTime;
    this.serializer = serializer;
  }

  @Override
  public T fromJson(ResourceLocation recipeId, JsonObject json) {
    String s = GsonHelper.getAsString(json, "group", "");
    JsonElement jsonelement = (GsonHelper.isArrayNode(json, "ingredient") ? GsonHelper.getAsJsonArray(json, "ingredient") : GsonHelper.getAsJsonObject(json, "ingredient"));
    Ingredient ingredient = Ingredient.fromJson(jsonelement);
    //Forge: Check if primitive string to keep vanilla or a object which can contain a count field.
    if (!json.has("result"))
      throw new com.google.gson.JsonSyntaxException("Missing result, expected to find a string or object");
    ItemStack itemstack;
    if (json.get("result").isJsonObject())
      itemstack = ShapedRecipe.itemFromJson(GsonHelper.getAsJsonObject(json, "result"));
    else {
      String s1 = GsonHelper.getAsString(json, "result");
      ResourceLocation resourcelocation = new ResourceLocation(s1);
      Item item = ForgeRegistries.ITEMS.getValue(resourcelocation);
      if (item == null) {
        throw new IllegalStateException("Item: " + s1 + " does not exist");
      }
      itemstack = new ItemStack(item);
    }
    float f = GsonHelper.getAsFloat(json, "experience", 0.0F);
    int i = GsonHelper.getAsInt(json, "cookingtime", this.defaultCookTime);
    return this.serializer.create(recipeId, s, ingredient, itemstack, f, i);
  }

  @Override
  public T fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
    String s = buffer.readUtf(32767);
    Ingredient ingredient = Ingredient.fromNetwork(buffer);
    ItemStack itemstack = buffer.readItem();
    float f = buffer.readFloat();
    int i = buffer.readVarInt();
    return this.serializer.create(recipeId, s, ingredient, itemstack, f, i);
  }

  @Override
  public void toNetwork(FriendlyByteBuf buffer, T recipe) {
    buffer.writeUtf(recipe.getGroup());
    recipe.getIngredients().forEach(o -> o.toNetwork(buffer));
    buffer.writeItem(recipe.getResultItem());
    buffer.writeFloat(recipe.getExperience());
    buffer.writeVarInt(recipe.getCookingTime());
  }

  public interface IFactory<T extends AbstractCookingRecipe> {
    T create(ResourceLocation rl, String group, Ingredient input, ItemStack result, float xp, int cookTime);
  }
}
