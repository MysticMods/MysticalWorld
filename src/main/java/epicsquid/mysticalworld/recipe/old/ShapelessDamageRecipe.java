/*package epicsquid.mysticalworld.recipe.old;

import com.google.gson.*;
import epicsquid.mysticalworld.recipe.IDamageRecipe;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.item.crafting.ShapelessRecipe;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.registries.ForgeRegistryEntry;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@SuppressWarnings({"NullableProblems", "WeakerAccess"})
public class ShapelessDamageRecipe extends ShapelessRecipe implements IDamageRecipe {
  private final ItemStack damageItem;
  private final int damageAmount;
  private final Ingredient damageIngredient;

  public ShapelessDamageRecipe(ResourceLocation idIn, String groupIn, ItemStack recipeOutputIn, NonNullList<Ingredient> recipeItemsIn, ItemStack damageItem, int damageAmount) {
    super(idIn, groupIn, recipeOutputIn, recipeItemsIn);
    this.damageAmount = damageAmount;
    this.damageItem = damageItem;
    this.damageIngredient = createDamageIngredient(damageItem);
  }

  @Override
  public NonNullList<ItemStack> getRemainingItems(CraftingInventory inv) {
    return getRemainingItems(inv, damageIngredient, damageAmount);
  }

  @SuppressWarnings({"Duplicates", "NullableProblems"})
  public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<ShapelessDamageRecipe> {
    @Override
    public ShapelessDamageRecipe read(ResourceLocation recipeId, JsonObject json) {
      String s = JSONUtils.getString(json, "group", "");
      ItemStack damageItem = ItemStack.EMPTY;
      int damageAmount = -1;
      NonNullList<Ingredient> nonnulllist = NonNullList.create();
      JsonArray array = JSONUtils.getJsonArray(json, "ingredients");
      for (int i = 0; i < array.size(); ++i) {
        JsonElement element = array.get(i);
        if (element != null && !element.isJsonNull()) {
          Ingredient ret = net.minecraftforge.common.crafting.CraftingHelper.getIngredient(element);
          //noinspection ConstantConditions
          if (ret != null && !ret.hasNoMatchingItems()) {
            nonnulllist.add(ret);
          } else {
            if (element.isJsonObject()) {
              Ingredient.IItemList list = Ingredient.deserializeItemList(element.getAsJsonObject());
              if (list instanceof Ingredient.SingleItemList) {
                ItemStack stack = list.getStacks().iterator().next();
                if (stack.hasTag()) {
                  CompoundNBT tag = stack.getTag();
                  if (tag != null && tag.contains(IDamageRecipe.TAG, Constants.NBT.TAG_INT)) {
                    if (!damageItem.isEmpty()) {
                      throw new JsonSyntaxException("Invalid recipe '" + recipeId.toString() + "', contains multiple damage items.");
                    }
                    damageAmount = tag.getInt(IDamageRecipe.TAG);
                    tag.remove(IDamageRecipe.TAG);
                    damageItem = stack;
                    ret = Ingredient.fromStacks(stack);
                    if (!ret.hasNoMatchingItems()) {
                      nonnulllist.add(ret);
                    }
                  }
                }
              } else {
                ret = Ingredient.fromItemListStream(Stream.of(list));
                if (!ret.hasNoMatchingItems()) {
                  nonnulllist.add(ret);
                }
              }
            } else if (element.isJsonArray()) {
              JsonArray jsonarray = element.getAsJsonArray();
              if (jsonarray.size() == 0) {
                throw new JsonSyntaxException("Item array cannot be empty, at least one item must be defined");
              } else {
                ret = Ingredient.fromItemListStream(StreamSupport.stream(jsonarray.spliterator(), false).map((p_209355_0_) -> Ingredient.deserializeItemList(JSONUtils.getJsonObject(p_209355_0_, "item"))));
                if (!ret.hasNoMatchingItems()) {
                  nonnulllist.add(ret);
                }
              }
            } else {
              throw new JsonSyntaxException("Expected item to be object or array of objects");
            }
          }
        } else {
          throw new JsonSyntaxException("Item cannot be null");
        }
      }
      if (nonnulllist.isEmpty()) {
        throw new JsonParseException("No ingredients for shapeless recipe");
      } else if (nonnulllist.size() > ShapedDamageRecipe.MAX_WIDTH * ShapedDamageRecipe.MAX_HEIGHT) {
        throw new JsonParseException("Too many ingredients for shapeless recipe the max is " + (ShapedDamageRecipe.MAX_WIDTH * ShapedDamageRecipe.MAX_HEIGHT));
      } else {
        ItemStack itemstack = ShapedRecipe.deserializeItem(JSONUtils.getJsonObject(json, "result"));
        return new ShapelessDamageRecipe(recipeId, s, itemstack, nonnulllist, damageItem, damageAmount);
      }
    }

    private static NonNullList<Ingredient> readIngredients(JsonArray p_199568_0_) {
      NonNullList<Ingredient> nonnulllist = NonNullList.create();

      for (int i = 0; i < p_199568_0_.size(); ++i) {
        Ingredient ingredient = Ingredient.deserialize(p_199568_0_.get(i));
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
      ItemStack damageItem = buffer.readItemStack();
      int damageAmount = buffer.readInt();
      return new ShapelessDamageRecipe(recipeId, s, itemstack, nonnulllist, damageItem, damageAmount);
    }

    @Override
    public void write(PacketBuffer buffer, ShapelessDamageRecipe recipe) {
      buffer.writeString(recipe.getGroup());
      buffer.writeVarInt(recipe.getIngredients().size());

      for (Ingredient ingredient : recipe.getIngredients()) {
        ingredient.write(buffer);
      }

      buffer.writeItemStack(recipe.getRecipeOutput());
      buffer.writeItemStack(recipe.damageItem);
      buffer.writeInt(recipe.damageAmount);
    }
  }
}*/
