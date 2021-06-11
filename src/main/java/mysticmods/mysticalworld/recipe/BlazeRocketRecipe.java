package mysticmods.mysticalworld.recipe;

import mysticmods.mysticalworld.init.ModRecipes;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.SpecialRecipe;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class BlazeRocketRecipe extends SpecialRecipe {
  private static final Ingredient INGREDIENT_PAPER = Ingredient.fromItems(Items.PAPER);
  private static final Ingredient INGREDIENT_BLAZE_POWDER = Ingredient.fromItems(Items.BLAZE_POWDER);
  private static final Ingredient INGREDIENT_FIREWORK_STAR = Ingredient.fromItems(Items.FIREWORK_STAR);

  public BlazeRocketRecipe(ResourceLocation idIn) {
    super(idIn);
  }

  @Override
  public boolean matches(CraftingInventory inv, World worldIn) {
    boolean flag = false;
    int i = 0;

    for (int j = 0; j < inv.getSizeInventory(); ++j) {
      ItemStack itemstack = inv.getStackInSlot(j);
      if (!itemstack.isEmpty()) {
        if (INGREDIENT_PAPER.test(itemstack)) {
          if (flag) {
            return false;
          }

          flag = true;
        } else if (INGREDIENT_BLAZE_POWDER.test(itemstack)) {
          ++i;
          if (i > 3) {
            return false;
          }
        } else if (!INGREDIENT_FIREWORK_STAR.test(itemstack)) {
          return false;
        }
      }
    }

    return flag && i >= 1;
  }

  @Override
  public ItemStack getCraftingResult(CraftingInventory inv) {
    ItemStack itemstack = new ItemStack(Items.FIREWORK_ROCKET, 5);
    CompoundNBT compoundnbt = itemstack.getOrCreateChildTag("Fireworks");
    ListNBT listnbt = new ListNBT();
    int i = 0;

    for (int j = 0; j < inv.getSizeInventory(); ++j) {
      ItemStack itemstack1 = inv.getStackInSlot(j);
      if (!itemstack1.isEmpty()) {
        if (INGREDIENT_BLAZE_POWDER.test(itemstack1)) {
          ++i;
        } else if (INGREDIENT_FIREWORK_STAR.test(itemstack1)) {
          CompoundNBT compoundnbt1 = itemstack1.getChildTag("Explosion");
          if (compoundnbt1 != null) {
            listnbt.add(compoundnbt1);
          }
        }
      }
    }

    compoundnbt.putByte("Flight", (byte) (i * 2));
    if (!listnbt.isEmpty()) {
      compoundnbt.put("Explosions", listnbt);
    }

    return itemstack;
  }

  /**
   * Used to determine if this recipe can fit in a grid of the given width/height
   */
  @Override
  public boolean canFit(int width, int height) {
    return width * height >= 2;
  }

  /**
   * Get the result of this recipe, usually for display purposes (e.g. recipe book). If your recipe has more than one
   * possible result (e.g. it's dynamic and depends on its inputs), then return an empty stack.
   */
  @Override
  public ItemStack getRecipeOutput() {
    return new ItemStack(Items.FIREWORK_ROCKET);
  }

  @Override
  public IRecipeSerializer<?> getSerializer() {
    return ModRecipes.BLAZE_SERIALIZER.get();
  }
}

