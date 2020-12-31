package epicsquid.mysticalworld.recipe.damage;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import epicsquid.mysticalworld.init.ModRecipes;
import epicsquid.mysticalworld.recipe.IDamageRecipe;
import it.unimi.dsi.fastutil.ints.IntList;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ICraftingRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.RecipeItemHelper;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class ShapelessDamageRecipe implements ICraftingRecipe, IDamageRecipe {
  private final ResourceLocation id;
  private final String group;
  private final ItemStack recipeOutput;
  private final NonNullList<Ingredient> recipeItems;
  private final boolean isSimple;
  private Ingredient damageItem;
  private Ingredient damageIngredient;
  private int damageAmount;

  public ShapelessDamageRecipe(ResourceLocation idIn, String groupIn, ItemStack recipeOutputIn, NonNullList<Ingredient> recipeItemsIn, Ingredient damageItem, int damageAmount) {
    this.id = idIn;
    this.group = groupIn;
    this.recipeOutput = recipeOutputIn;
    this.recipeItems = recipeItemsIn;
    this.isSimple = recipeItemsIn.stream().allMatch(Ingredient::isSimple);
    this.damageItem = damageItem;
    this.damageAmount = damageAmount;
    this.damageIngredient = createDamageIngredient(damageItem);
  }

  @Override
  public ResourceLocation getId() {
    return this.id;
  }

  @Override
  public IRecipeSerializer<?> getSerializer() {
    return ModRecipes.SHAPELESS_DAMAGE_SERIALIZER.get();
  }

  @Override
  public NonNullList<ItemStack> getRemainingItems(CraftingInventory inv) {
    return getRemainingItems(inv, damageIngredient, damageAmount);
  }

  /**
   * Recipes with equal group are combined into one button in the recipe book
   */
  @Override
  public String getGroup() {
    return this.group;
  }

  /**
   * Get the result of this recipe, usually for display purposes (e.g. recipe book). If your recipe has more than one
   * possible result (e.g. it's dynamic and depends on its inputs), then return an empty stack.
   */
  @Override
  public ItemStack getRecipeOutput() {
    return this.recipeOutput;
  }

  @Override
  public NonNullList<Ingredient> getIngredients() {
    return this.recipeItems;
  }

  /**
   * Used to check if a recipe matches current crafting inventory
   */
  @Override
  public boolean matches(CraftingInventory inv, World worldIn) {
    RecipeItemHelper recipeitemhelper = new RecipeItemHelper();
    java.util.List<ItemStack> inputs = new java.util.ArrayList<>();
    int i = 0;

    for (int j = 0; j < inv.getSizeInventory(); ++j) {
      ItemStack itemstack = inv.getStackInSlot(j);
      if (!itemstack.isEmpty()) {
        ++i;
        if (isSimple)
          recipeitemhelper.func_221264_a(itemstack, 1);
        else inputs.add(itemstack);
      }
    }

    return i == this.recipeItems.size() && (isSimple ? recipeitemhelper.canCraft(this, (IntList) null) : net.minecraftforge.common.util.RecipeMatcher.findMatches(inputs, this.recipeItems) != null);
  }

  /**
   * Returns an Item that is the result of this recipe
   */
  @Override
  public ItemStack getCraftingResult(CraftingInventory inv) {
    return this.recipeOutput.copy();
  }

  /**
   * Used to determine if this recipe can fit in a grid of the given width/height
   */
  @Override
  public boolean canFit(int width, int height) {
    return width * height >= this.recipeItems.size();
  }

  public static class Serializer extends net.minecraftforge.registries.ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<ShapelessDamageRecipe> {
    private static final ResourceLocation NAME = new ResourceLocation("minecraft", "crafting_shapeless");

    @Override
    public ShapelessDamageRecipe read(ResourceLocation recipeId, JsonObject json) {
      String s = JSONUtils.getString(json, "group", "");
      NonNullList<Ingredient> nonnulllist = readIngredients(JSONUtils.getJsonArray(json, "ingredients"));
      if (nonnulllist.isEmpty()) {
        throw new JsonParseException("No ingredients for shapeless recipe");
      } else if (nonnulllist.size() > ShapedDamageRecipe.MAX_WIDTH * ShapedDamageRecipe.MAX_HEIGHT) {
        throw new JsonParseException("Too many ingredients for shapeless recipe the max is " + (ShapedDamageRecipe.MAX_WIDTH * ShapedDamageRecipe.MAX_HEIGHT));
      } else {
        ItemStack itemstack = ShapedDamageRecipe.deserializeItem(JSONUtils.getJsonObject(json, "result"));
      Ingredient damageItem = Ingredient.deserialize(json.get(IDamageRecipe.TAG));
      int damageAmount = JSONUtils.getInt(json, IDamageRecipe.DAMAGE, -1);
      if (damageAmount == -1) {
        throw new JsonSyntaxException("Invalid damage_amount for ShapelessDamageRecipe.");
      }
        return new ShapelessDamageRecipe(recipeId, s, itemstack, nonnulllist, damageItem, damageAmount);
      }
    }

    private static NonNullList<Ingredient> readIngredients(JsonArray ingredientArray) {
      NonNullList<Ingredient> nonnulllist = NonNullList.create();

      for (int i = 0; i < ingredientArray.size(); ++i) {
        Ingredient ingredient = Ingredient.deserialize(ingredientArray.get(i));
        if (!ingredient.hasNoMatchingItems()) {
          nonnulllist.add(ingredient);
        }
      }

      return nonnulllist;
    }

    @Override
    public ShapelessDamageRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
      String s = buffer.readString(32767);
      int i = buffer.readVarInt();
      NonNullList<Ingredient> nonnulllist = NonNullList.withSize(i, Ingredient.EMPTY);

      for (int j = 0; j < nonnulllist.size(); ++j) {
        nonnulllist.set(j, Ingredient.read(buffer));
      }

      ItemStack itemstack = buffer.readItemStack();
      Ingredient damageItem = Ingredient.read(buffer);
      int damageAmount = buffer.readInt();
      return new ShapelessDamageRecipe(recipeId, s, itemstack, nonnulllist, damageItem, damageAmount);
    }

    @Override
    public void write(PacketBuffer buffer, ShapelessDamageRecipe recipe) {
      buffer.writeString(recipe.group);
      buffer.writeVarInt(recipe.recipeItems.size());

      for (Ingredient ingredient : recipe.recipeItems) {
        ingredient.write(buffer);
      }

      buffer.writeItemStack(recipe.recipeOutput);
      recipe.damageItem.write(buffer);
      buffer.writeInt(recipe.damageAmount);
    }
  }
}
