package epicsquid.mysticalworld.recipe.damage;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.*;
import epicsquid.mysticalworld.init.ModRecipes;
import epicsquid.mysticalworld.recipe.IDamageRecipe;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ICraftingRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraftforge.common.crafting.IShapedRecipe;
import net.minecraftforge.registries.ForgeRegistryEntry;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class ShapedDamageRecipe implements ICraftingRecipe, IShapedRecipe<CraftingInventory>, IDamageRecipe {
  static int MAX_WIDTH = 3;
  static int MAX_HEIGHT = 3;

  /**
   * Expand the max width and height allowed in the deserializer.
   * This should be called by modders who add custom crafting tables that are larger than the vanilla 3x3.
   *
   * @param width  your max recipe width
   * @param height your max recipe height
   */
  public static void setCraftingSize(int width, int height) {
    if (MAX_WIDTH < width) MAX_WIDTH = width;
    if (MAX_HEIGHT < height) MAX_HEIGHT = height;
  }

  private final int recipeWidth;
  private final int recipeHeight;
  private final NonNullList<Ingredient> recipeItems;
  private final ItemStack recipeOutput;
  private final ResourceLocation id;
  private final String group;
  private Ingredient damageItem;
  private Ingredient damageIngredient;
  private int damageAmount;

  public ShapedDamageRecipe(ResourceLocation idIn, String groupIn, int recipeWidthIn, int recipeHeightIn, NonNullList<Ingredient> recipeItemsIn, ItemStack recipeOutputIn, Ingredient damageItem, int damageAmount) {
    this.id = idIn;
    this.group = groupIn;
    this.recipeWidth = recipeWidthIn;
    this.recipeHeight = recipeHeightIn;
    this.recipeItems = recipeItemsIn;
    this.recipeOutput = recipeOutputIn;
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
    return ModRecipes.SHAPED_DAMAGE_SERIALIZER.get();
  }

  @Override
  public String getGroup() {
    return this.group;
  }

  @Override
  public NonNullList<ItemStack> getRemainingItems(CraftingInventory inv) {
    return getRemainingItems(inv, damageIngredient, damageAmount);
  }

  @Override
  public ItemStack getRecipeOutput() {
    return this.recipeOutput;
  }

  @Override
  public NonNullList<Ingredient> getIngredients() {
    return this.recipeItems;
  }

  @Override
  public boolean canFit(int width, int height) {
    return width >= this.recipeWidth && height >= this.recipeHeight;
  }

  @Override
  public boolean matches(CraftingInventory inv, World worldIn) {
    for (int i = 0; i <= inv.getWidth() - this.recipeWidth; ++i) {
      for (int j = 0; j <= inv.getHeight() - this.recipeHeight; ++j) {
        if (this.checkMatch(inv, i, j, true)) {
          return true;
        }

        if (this.checkMatch(inv, i, j, false)) {
          return true;
        }
      }
    }

    return false;
  }

  private boolean checkMatch(CraftingInventory craftingInventory, int width, int height, boolean p_77573_4_) {
    for (int i = 0; i < craftingInventory.getWidth(); ++i) {
      for (int j = 0; j < craftingInventory.getHeight(); ++j) {
        int k = i - width;
        int l = j - height;
        Ingredient ingredient = Ingredient.EMPTY;
        if (k >= 0 && l >= 0 && k < this.recipeWidth && l < this.recipeHeight) {
          if (p_77573_4_) {
            ingredient = this.recipeItems.get(this.recipeWidth - k - 1 + l * this.recipeWidth);
          } else {
            ingredient = this.recipeItems.get(k + l * this.recipeWidth);
          }
        }

        if (!ingredient.test(craftingInventory.getStackInSlot(i + j * craftingInventory.getWidth()))) {
          return false;
        }
      }
    }

    return true;
  }

  @Override
  public ItemStack getCraftingResult(CraftingInventory inv) {
    return this.getRecipeOutput().copy();
  }

  public int getWidth() {
    return this.recipeWidth;
  }

  @Override
  public int getRecipeWidth() {
    return getWidth();
  }

  public int getHeight() {
    return this.recipeHeight;
  }

  @Override
  public int getRecipeHeight() {
    return getHeight();
  }

  private static NonNullList<Ingredient> deserializeIngredients(String[] pattern, Map<String, Ingredient> keys, int patternWidth, int patternHeight) {
    NonNullList<Ingredient> nonnulllist = NonNullList.withSize(patternWidth * patternHeight, Ingredient.EMPTY);
    Set<String> set = Sets.newHashSet(keys.keySet());
    set.remove(" ");

    for (int i = 0; i < pattern.length; ++i) {
      for (int j = 0; j < pattern[i].length(); ++j) {
        String s = pattern[i].substring(j, j + 1);
        Ingredient ingredient = keys.get(s);
        if (ingredient == null) {
          throw new JsonSyntaxException("Pattern references symbol '" + s + "' but it's not defined in the key");
        }

        set.remove(s);
        nonnulllist.set(j + patternWidth * i, ingredient);
      }
    }

    if (!set.isEmpty()) {
      throw new JsonSyntaxException("Key defines symbols that aren't used in pattern: " + set);
    } else {
      return nonnulllist;
    }
  }

  @VisibleForTesting
  static String[] shrink(String... toShrink) {
    int i = Integer.MAX_VALUE;
    int j = 0;
    int k = 0;
    int l = 0;

    for (int i1 = 0; i1 < toShrink.length; ++i1) {
      String s = toShrink[i1];
      i = Math.min(i, firstNonSpace(s));
      int j1 = lastNonSpace(s);
      j = Math.max(j, j1);
      if (j1 < 0) {
        if (k == i1) {
          ++k;
        }

        ++l;
      } else {
        l = 0;
      }
    }

    if (toShrink.length == l) {
      return new String[0];
    } else {
      String[] astring = new String[toShrink.length - l - k];

      for (int k1 = 0; k1 < astring.length; ++k1) {
        astring[k1] = toShrink[k1 + k].substring(i, j + 1);
      }

      return astring;
    }
  }

  private static int firstNonSpace(String str) {
    int i;
    for (i = 0; i < str.length() && str.charAt(i) == ' '; ++i) {
    }

    return i;
  }

  private static int lastNonSpace(String str) {
    int i;
    for (i = str.length() - 1; i >= 0 && str.charAt(i) == ' '; --i) {
    }

    return i;
  }

  private static String[] patternFromJson(JsonArray jsonArr) {
    String[] astring = new String[jsonArr.size()];
    if (astring.length > MAX_HEIGHT) {
      throw new JsonSyntaxException("Invalid pattern: too many rows, " + MAX_HEIGHT + " is maximum");
    } else if (astring.length == 0) {
      throw new JsonSyntaxException("Invalid pattern: empty pattern not allowed");
    } else {
      for (int i = 0; i < astring.length; ++i) {
        String s = JSONUtils.getString(jsonArr.get(i), "pattern[" + i + "]");
        if (s.length() > MAX_WIDTH) {
          throw new JsonSyntaxException("Invalid pattern: too many columns, " + MAX_WIDTH + " is maximum");
        }

        if (i > 0 && astring[0].length() != s.length()) {
          throw new JsonSyntaxException("Invalid pattern: each row must be the same width");
        }

        astring[i] = s;
      }

      return astring;
    }
  }

  /**
   * Returns a key json object as a Java HashMap.
   */
  private static Map<String, Ingredient> deserializeKey(JsonObject json) {
    Map<String, Ingredient> map = Maps.newHashMap();

    for (Entry<String, JsonElement> entry : json.entrySet()) {
      if (entry.getKey().length() != 1) {
        throw new JsonSyntaxException("Invalid key entry: '" + (String) entry.getKey() + "' is an invalid symbol (must be 1 character only).");
      }

      if (" ".equals(entry.getKey())) {
        throw new JsonSyntaxException("Invalid key entry: ' ' is a reserved symbol.");
      }

      map.put(entry.getKey(), Ingredient.deserialize(entry.getValue()));
    }

    map.put(" ", Ingredient.EMPTY);
    return map;
  }

  public static ItemStack deserializeItem(JsonObject object) {
    String s = JSONUtils.getString(object, "item");
    Item item = Registry.ITEM.getOptional(new ResourceLocation(s)).orElseThrow(() -> {
      return new JsonSyntaxException("Unknown item '" + s + "'");
    });
    if (object.has("data")) {
      throw new JsonParseException("Disallowed data tag found");
    } else {
      int i = JSONUtils.getInt(object, "count", 1);
      return net.minecraftforge.common.crafting.CraftingHelper.getItemStack(object, true);
    }
  }

  public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<ShapedDamageRecipe> {
    @Override
    public ShapedDamageRecipe read(ResourceLocation recipeId, JsonObject json) {
      String s = JSONUtils.getString(json, "group", "");
      Map<String, Ingredient> map = ShapedDamageRecipe.deserializeKey(JSONUtils.getJsonObject(json, "key"));
      String[] astring = ShapedDamageRecipe.shrink(ShapedDamageRecipe.patternFromJson(JSONUtils.getJsonArray(json, "pattern")));
      int i = astring[0].length();
      int j = astring.length;
      NonNullList<Ingredient> nonnulllist = ShapedDamageRecipe.deserializeIngredients(astring, map, i, j);
      ItemStack itemstack = ShapedDamageRecipe.deserializeItem(JSONUtils.getJsonObject(json, "result"));
      Ingredient damageItem = Ingredient.deserialize(json.get(IDamageRecipe.TAG));
      int damageAmount = JSONUtils.getInt(json, IDamageRecipe.DAMAGE, -1);
      if (damageAmount == -1) {
        throw new JsonSyntaxException("Invalid damage_amount for ShapedDamageRecipe.");
      }
      return new ShapedDamageRecipe(recipeId, s, i, j, nonnulllist, itemstack, damageItem, damageAmount);
    }

    @Override
    public ShapedDamageRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
      int i = buffer.readVarInt();
      int j = buffer.readVarInt();
      String s = buffer.readString(32767);
      NonNullList<Ingredient> nonnulllist = NonNullList.withSize(i * j, Ingredient.EMPTY);

      for (int k = 0; k < nonnulllist.size(); ++k) {
        nonnulllist.set(k, Ingredient.read(buffer));
      }

      ItemStack itemstack = buffer.readItemStack();
      Ingredient damageItem = Ingredient.read(buffer);
      int damageAmount = buffer.readInt();
      return new ShapedDamageRecipe(recipeId, s, i, j, nonnulllist, itemstack, damageItem, damageAmount);
    }

    @Override
    public void write(PacketBuffer buffer, ShapedDamageRecipe recipe) {
      buffer.writeVarInt(recipe.recipeWidth);
      buffer.writeVarInt(recipe.recipeHeight);
      buffer.writeString(recipe.group);

      for (Ingredient ingredient : recipe.recipeItems) {
        ingredient.write(buffer);
      }

      buffer.writeItemStack(recipe.recipeOutput);
      recipe.damageItem.write(buffer);
      buffer.writeInt(recipe.damageAmount);
    }
  }
}
