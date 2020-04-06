package epicsquid.mysticalworld.recipe;

import epicsquid.mysticalworld.init.ModRecipes;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.SpecialRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EmptyRecipe extends SpecialRecipe {
  public EmptyRecipe(ResourceLocation idIn) {
    super(idIn);
  }

  public boolean matches(CraftingInventory inv, World worldIn) {
    return false;
  }

  public ItemStack getCraftingResult(CraftingInventory inv) {
    return ItemStack.EMPTY;
  }

  public boolean canFit(int width, int height) {
    return false;
  }

  public ItemStack getRecipeOutput() {
    return ItemStack.EMPTY;
  }

  public IRecipeSerializer<?> getSerializer() {
    return ModRecipes.EMPTY_SERIALIZER.get();
  }
}

