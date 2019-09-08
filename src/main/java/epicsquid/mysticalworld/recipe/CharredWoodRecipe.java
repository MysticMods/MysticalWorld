package epicsquid.mysticalworld.recipe;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import epicsquid.mysticallib.util.Util;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.IRecipeFactory;
import net.minecraftforge.common.crafting.JsonContext;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.stream.IntStream;

// This code taken from Pyrotech by CodeTaylor, with permission
// It is license compatible under the terms of the Apache License
// https://github.com/codetaylor/pyrotech/blob/master/src/main/java/com/codetaylor/mc/pyrotech/modules/core/recipe/ChoppingBlockRecipe.java
public class CharredWoodRecipe extends ShapelessOreRecipe {
  public CharredWoodRecipe(ResourceLocation group, NonNullList<Ingredient> input, @Nonnull ItemStack result) {
    super(group, input, result);
  }

  @Override
  public NonNullList<ItemStack> getRemainingItems(InventoryCrafting inv) {
    NonNullList<ItemStack> result = NonNullList.withSize(inv.getSizeInventory(), ItemStack.EMPTY);

    for (int i = 0; i < result.size(); i++) {
      ItemStack inSlot = inv.getStackInSlot(i);

      if (IngredientFlintAndSteel.getInstance().apply(inSlot)) {
        inSlot = inSlot.copy();

        if (inSlot.attemptDamageItem(1, Util.rand, null)) {
          inSlot.shrink(1);
        }

        result.set(i, inSlot);
      } else {
        // Fluid handling?
        ItemStack inContainer = ForgeHooks.getContainerItem(inSlot);
        result.set(i, inContainer);
      }
    }

    return result;
  }

  public static class Factory implements IRecipeFactory {
    @Override
    public IRecipe parse(JsonContext context, JsonObject json) {
      String group = JsonUtils.getString(json, "group", "");

      NonNullList<Ingredient> ings = NonNullList.create();
      for (JsonElement ele : JsonUtils.getJsonArray(json, "ingredients"))
        ings.add(CraftingHelper.getIngredient(ele, context));

      if (ings.isEmpty())
        throw new JsonParseException("No ingredients for shapeless recipe");

      ItemStack itemstack = CraftingHelper.getItemStack(JsonUtils.getJsonObject(json, "result"), context);
      return new CharredWoodRecipe(group.isEmpty() ? null : new ResourceLocation(group), ings, itemstack);
    }
  }
}
