package epicsquid.mysticalworld.recipe;

import epicsquid.mysticallib.util.Util;
import epicsquid.mysticalworld.MWTags;
import epicsquid.mysticalworld.init.ModItems;
import epicsquid.mysticalworld.init.ModRecipes;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.SpecialRecipe;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class KnifeRecipe extends SpecialRecipe {
  private static Ingredient CARROT_INGREDIENT = null;
  private static Ingredient KNIFE_INGREDIENT = null;

  public KnifeRecipe(ResourceLocation idIn) {
    super(idIn);
  }

  @Override
  public boolean matches(CraftingInventory inv, World worldIn) {
    boolean found_knife = false;
    boolean found_carrot = false;

    if (CARROT_INGREDIENT == null) {
      CARROT_INGREDIENT = Ingredient.fromItems(Items.CARROT);
    }
    if (KNIFE_INGREDIENT == null) {
      KNIFE_INGREDIENT = DamagedIngredient.fromTag(MWTags.Items.KNIVES);
    }

    int count = 0;

    for (int i = 0; i < inv.getSizeInventory(); i++) {
      ItemStack current = inv.getStackInSlot(i);
      if (current.isEmpty()) {
        continue;
      }

      if (count == 2) {
        return false;
      }

      count++;

      if (CARROT_INGREDIENT.test(current)) {
        if (!found_carrot) {
          found_carrot = true;
        } else {
          return false;
        }
      }

      if (KNIFE_INGREDIENT.test(current)) {
        if (!found_knife) {
          found_knife = true;
        } else {
          return false;
        }
      }
    }

    return found_carrot && found_knife;
  }

  @Override
  public NonNullList<ItemStack> getRemainingItems(CraftingInventory inv) {
    NonNullList<ItemStack> result = NonNullList.withSize(inv.getSizeInventory(), ItemStack.EMPTY);

    for (int i = 0; i < result.size(); i++) {
      ItemStack current = inv.getStackInSlot(i);
      if (current.isEmpty()) {
        continue;
      }

      if (KNIFE_INGREDIENT.test(current)) {
        ItemStack newStack = Util.damageItem(1, current, inv);
        result.set(i, newStack.copy());
        break;
      }
    }

    return result;
  }

  @Override
  public ItemStack getCraftingResult(CraftingInventory inv) {
    return new ItemStack(ModItems.SLICED_CARROT.get(), 4);
  }

  @Override
  public boolean canFit(int width, int height) {
    return width * height >= 2;
  }

  @Override
  public IRecipeSerializer<?> getSerializer() {
    return ModRecipes.KNIFE_SERIALIZER.get();
  }
}
