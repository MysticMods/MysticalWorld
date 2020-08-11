package epicsquid.mysticalworld.recipe;

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
  public T read(ResourceLocation recipeId, JsonObject json) {
    String s = JSONUtils.getString(json, "group", "");
    JsonElement jsonelement = (JSONUtils.isJsonArray(json, "ingredient") ? JSONUtils.getJsonArray(json, "ingredient") : JSONUtils.getJsonObject(json, "ingredient"));
    Ingredient ingredient = Ingredient.deserialize(jsonelement);
    //Forge: Check if primitive string to keep vanilla or a object which can contain a count field.
    if (!json.has("result"))
      throw new com.google.gson.JsonSyntaxException("Missing result, expected to find a string or object");
    ItemStack itemstack;
    if (json.get("result").isJsonObject())
      itemstack = ShapedRecipe.deserializeItem(JSONUtils.getJsonObject(json, "result"));
    else {
      String s1 = JSONUtils.getString(json, "result");
      ResourceLocation resourcelocation = new ResourceLocation(s1);
      Item item = ForgeRegistries.ITEMS.getValue(resourcelocation);
      if (item == null) {
        throw new IllegalStateException("Item: " + s1 + " does not exist");
      }
      itemstack = new ItemStack(item);
    }
    float f = JSONUtils.getFloat(json, "experience", 0.0F);
    int i = JSONUtils.getInt(json, "cookingtime", this.defaultCookTime);
    return this.serializer.create(recipeId, s, ingredient, itemstack, f, i);
  }

  @Override
  public T read(ResourceLocation recipeId, PacketBuffer buffer) {
    String s = buffer.readString(32767);
    Ingredient ingredient = Ingredient.read(buffer);
    ItemStack itemstack = buffer.readItemStack();
    float f = buffer.readFloat();
    int i = buffer.readVarInt();
    return this.serializer.create(recipeId, s, ingredient, itemstack, f, i);
  }

  @Override
  public void write(PacketBuffer buffer, T recipe) {
    buffer.writeString(recipe.getGroup());
    recipe.getIngredients().forEach(o -> o.write(buffer));
    buffer.writeItemStack(recipe.getRecipeOutput());
    buffer.writeFloat(recipe.getExperience());
    buffer.writeVarInt(recipe.getCookTime());
  }

  public interface IFactory<T extends AbstractCookingRecipe> {
    T create(ResourceLocation rl, String group, Ingredient input, ItemStack result, float xp, int cookTime);
  }
}
