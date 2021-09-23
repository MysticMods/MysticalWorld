package mysticmods.mysticalworld.recipe;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.AbstractCookingRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistryEntry;

@SuppressWarnings("ALL")
public abstract class AbstractCookingRecipeSerializer<T extends AbstractCookingRecipe> extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<T> {
  protected final int defaultCookTime;
  protected final IFactory<T> serializer;

  public AbstractCookingRecipeSerializer(IFactory<T> serializer, int defaultCookTime) {
    this.defaultCookTime = defaultCookTime;
    this.serializer = serializer;
  }

  @Override
  public T fromJson(ResourceLocation recipeId, JsonObject json) {
    String s = JSONUtils.getAsString(json, "group", "");
    JsonElement jsonelement = (JSONUtils.isArrayNode(json, "ingredient") ? JSONUtils.getAsJsonArray(json, "ingredient") : JSONUtils.getAsJsonObject(json, "ingredient"));
    Ingredient ingredient = Ingredient.fromJson(jsonelement);
    //Forge: Check if primitive string to keep vanilla or a object which can contain a count field.
    if (!json.has("result"))
      throw new com.google.gson.JsonSyntaxException("Missing result, expected to find a string or object");
    ItemStack itemstack;
    if (json.get("result").isJsonObject())
      itemstack = ShapedRecipe.itemFromJson(JSONUtils.getAsJsonObject(json, "result"));
    else {
      String s1 = JSONUtils.getAsString(json, "result");
      ResourceLocation resourcelocation = new ResourceLocation(s1);
      Item item = ForgeRegistries.ITEMS.getValue(resourcelocation);
      if (item == null) {
        throw new IllegalStateException("Item: " + s1 + " does not exist");
      }
      itemstack = new ItemStack(item);
    }
    float f = JSONUtils.getAsFloat(json, "experience", 0.0F);
    int i = JSONUtils.getAsInt(json, "cookingtime", this.defaultCookTime);
    return this.serializer.create(recipeId, s, ingredient, itemstack, f, i);
  }

  @Override
  public T fromNetwork(ResourceLocation recipeId, PacketBuffer buffer) {
    String s = buffer.readUtf(32767);
    Ingredient ingredient = Ingredient.fromNetwork(buffer);
    ItemStack itemstack = buffer.readItem();
    float f = buffer.readFloat();
    int i = buffer.readVarInt();
    return this.serializer.create(recipeId, s, ingredient, itemstack, f, i);
  }

  @Override
  public void toNetwork(PacketBuffer buffer, T recipe) {
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
