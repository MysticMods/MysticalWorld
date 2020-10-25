package epicsquid.mysticalworld.recipe;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import epicsquid.mysticallib.util.Util;
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
import net.minecraftforge.oredict.ShapedOreRecipe;

import javax.annotation.Nonnull;
import java.util.Map;
import java.util.Set;

// This code taken from Pyrotech by CodeTaylor, with permission
// It is license compatible under the terms of the Apache License
// https://github.com/codetaylor/pyrotech/blob/master/src/main/java/com/codetaylor/mc/pyrotech/modules/core/recipe/ChoppingBlockRecipe.java
public class ShapedCharredWoodRecipe extends ShapedOreRecipe {
  public ShapedCharredWoodRecipe(ResourceLocation group, @Nonnull ItemStack result, CraftingHelper.ShapedPrimer primer) {
    super(group, result, primer);
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
      //if (!group.isEmpty() && group.indexOf(':') == -1)
      //    group = context.getModId() + ":" + group;

      Map<Character, Ingredient> ingMap = Maps.newHashMap();
      for (Map.Entry<String, JsonElement> entry : JsonUtils.getJsonObject(json, "key").entrySet()) {
        if (entry.getKey().length() != 1)
          throw new JsonSyntaxException("Invalid key entry: '" + entry.getKey() + "' is an invalid symbol (must be 1 character only).");
        if (" ".equals(entry.getKey()))
          throw new JsonSyntaxException("Invalid key entry: ' ' is a reserved symbol.");

        ingMap.put(entry.getKey().toCharArray()[0], CraftingHelper.getIngredient(entry.getValue(), context));
      }

      ingMap.put(' ', Ingredient.EMPTY);

      JsonArray patternJ = JsonUtils.getJsonArray(json, "pattern");

      if (patternJ.size() == 0)
        throw new JsonSyntaxException("Invalid pattern: empty pattern not allowed");

      String[] pattern = new String[patternJ.size()];
      for (int x = 0; x < pattern.length; ++x) {
        String line = JsonUtils.getString(patternJ.get(x), "pattern[" + x + "]");
        if (x > 0 && pattern[0].length() != line.length())
          throw new JsonSyntaxException("Invalid pattern: each row must  be the same width");
        pattern[x] = line;
      }

      CraftingHelper.ShapedPrimer primer = new CraftingHelper.ShapedPrimer();
      primer.width = pattern[0].length();
      primer.height = pattern.length;
      primer.mirrored = JsonUtils.getBoolean(json, "mirrored", true);
      primer.input = NonNullList.withSize(primer.width * primer.height, Ingredient.EMPTY);

      Set<Character> keys = Sets.newHashSet(ingMap.keySet());
      keys.remove(' ');

      int x = 0;
      for (String line : pattern) {
        for (char chr : line.toCharArray()) {
          Ingredient ing = ingMap.get(chr);
          if (ing == null)
            throw new JsonSyntaxException("Pattern references symbol '" + chr + "' but it's not defined in the key");
          primer.input.set(x++, ing);
          keys.remove(chr);
        }
      }

      if (!keys.isEmpty())
        throw new JsonSyntaxException("Key defines symbols that aren't used in pattern: " + keys);

      ItemStack result = CraftingHelper.getItemStack(JsonUtils.getJsonObject(json, "result"), context);
      return new ShapedCharredWoodRecipe(group.isEmpty() ? null : new ResourceLocation(group), result, primer);
    }
  }
}
