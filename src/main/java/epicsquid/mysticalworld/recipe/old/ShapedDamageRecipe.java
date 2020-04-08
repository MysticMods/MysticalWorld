/*package epicsquid.mysticalworld.recipe.old;

import com.google.common.collect.Sets;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import epicsquid.mysticalworld.recipe.IDamageRecipe;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.registries.ForgeRegistryEntry;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class ShapedDamageRecipe extends ShapedRecipe implements IDamageRecipe {
  public static int MAX_WIDTH = 3;
  public static int MAX_HEIGHT = 3;

  private ItemStack damageItem;
  private Ingredient damageIngredient;
  private int damageAmount;
  private final ResourceLocation id;

  public ShapedDamageRecipe(ResourceLocation idIn, String groupIn, int recipeWidthIn, int recipeHeightIn, NonNullList<Ingredient> recipeItemsIn, ItemStack damageItem, int damageAmount, ItemStack recipeOutputIn) {
    super(idIn, groupIn, recipeWidthIn, recipeHeightIn, recipeItemsIn, recipeOutputIn);
    this.id = idIn;
    this.damageAmount = damageAmount;
    this.damageItem = damageItem;
    this.damageIngredient = createDamageIngredient(damageItem);
  }

  @Override
  public NonNullList<ItemStack> getRemainingItems(CraftingInventory inv) {
    return getRemainingItems(inv, damageIngredient, damageAmount);
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

  public static String[] shrink(String... toShrink) {
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

  public static int firstNonSpace(String str) {
    int i;
    for (i = 0; i < str.length() && str.charAt(i) == ' '; ++i) {
      ;
    }

    return i;
  }

  public static int lastNonSpace(String str) {
    int i;
    for (i = str.length() - 1; i >= 0 && str.charAt(i) == ' '; --i) {
      ;
    }

    return i;
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

  @SuppressWarnings({"NullableProblems", "ConstantConditions"})
  public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<ShapedDamageRecipe> {
    @Override
    public ShapedDamageRecipe read(ResourceLocation recipeId, JsonObject json) {
      ItemStack damageItem = ItemStack.EMPTY;
      int damageAmount = -1;
      String s = JSONUtils.getString(json, "group", "");

      Map<String, Ingredient> map = new HashMap<>();
      for (Map.Entry<String, JsonElement> entry : JSONUtils.getJsonObject(json, "key").entrySet()) {
        if (entry.getKey().length() != 1) {
          throw new JsonSyntaxException("Invalid key entry: '" + entry.getKey() + "' is an invalid symbol (must be 1 character only).");
        }

        if (" ".equals(entry.getKey())) {
          throw new JsonSyntaxException("Invalid key entry: ' ' is a reserved symbol.");
        }

        JsonElement element = entry.getValue();

        Ingredient ingredient;

        if (element != null && !element.isJsonNull()) {
          ingredient = net.minecraftforge.common.crafting.CraftingHelper.getIngredient(element);
          //noinspection ConstantConditions
          if (ingredient == null) {
            if (element.isJsonObject()) {
              Ingredient.IItemList list = Ingredient.deserializeItemList(element.getAsJsonObject());
              if (list instanceof Ingredient.SingleItemList) {
                ItemStack stack = list.getStacks().iterator().next();
                if (stack.hasTag()) {
                  CompoundNBT tag = stack.getTag();
                  if (tag.contains(IDamageRecipe.TAG, Constants.NBT.TAG_INT)) {
                    if (!damageItem.isEmpty()) {
                      throw new JsonSyntaxException("Invalid recipe '" + recipeId.toString() + "', contains multiple damage items.");
                    }
                    damageAmount = tag.getInt(IDamageRecipe.TAG);
                    tag.remove(IDamageRecipe.TAG);
                    damageItem = stack;
                    ingredient = Ingredient.fromStacks(stack);
                  }
                }
              } else {
                ingredient = Ingredient.fromItemListStream(Stream.of(list));
              }
            } else if (element.isJsonArray()) {
              JsonArray jsonarray = element.getAsJsonArray();
              if (jsonarray.size() == 0) {
                throw new JsonSyntaxException("Item array cannot be empty, at least one item must be defined");
              } else {
                ingredient = Ingredient.fromItemListStream(StreamSupport.stream(jsonarray.spliterator(), false).map((p_209355_0_) -> Ingredient.deserializeItemList(JSONUtils.getJsonObject(p_209355_0_, "item"))));
              }
            } else {
              throw new JsonSyntaxException("Expected item to be object or array of objects");
            }
          }
        } else {
          throw new JsonSyntaxException("Item cannot be null");
        }

        if (ingredient != null) {
          map.put(entry.getKey(), ingredient);
        } else {
          throw new JsonSyntaxException("Somehow our ingredient is null!");
        }
      }

      map.put(" ", Ingredient.EMPTY);
      String[] astring = shrink(patternFromJson(JSONUtils.getJsonArray(json, "pattern")));
      int i = astring[0].length();
      int j = astring.length;
      NonNullList<Ingredient> nonnulllist = deserializeIngredients(astring, map, i, j);
      ItemStack itemstack = ShapedRecipe.deserializeItem(JSONUtils.getJsonObject(json, "result"));
      return new ShapedDamageRecipe(recipeId, s, i, j, nonnulllist, damageItem, damageAmount, itemstack);
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
      ItemStack damageItem = buffer.readItemStack();
      int amount = buffer.readInt();

      return new ShapedDamageRecipe(recipeId, s, i, j, nonnulllist, damageItem, amount, itemstack);
    }

    @Override
    public void write(PacketBuffer buffer, ShapedDamageRecipe recipe) {
      buffer.writeVarInt(recipe.getRecipeWidth());
      buffer.writeVarInt(recipe.getRecipeHeight());
      buffer.writeString(recipe.getGroup());

      for (Ingredient ingredient : recipe.getIngredients()) {
        ingredient.write(buffer);
      }

      buffer.writeItemStack(recipe.getRecipeOutput());
      buffer.writeItemStack(recipe.damageItem);
      buffer.writeInt(recipe.damageAmount);
    }
  }
}*/
