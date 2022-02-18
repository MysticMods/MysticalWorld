package mysticmods.mysticalworld.recipe;

import mysticmods.mysticalworld.init.ModRecipes;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;

public class EmptyRecipe extends CustomRecipe {
  public EmptyRecipe(ResourceLocation idIn) {
    super(idIn);
  }

  @Override
  public boolean matches(CraftingContainer inv, Level worldIn) {
    return false;
  }

  @Override
  public ItemStack assemble(CraftingContainer inv) {
    return ItemStack.EMPTY;
  }

  @Override
  public boolean canCraftInDimensions(int width, int height) {
    return false;
  }

  @Override
  public ItemStack getResultItem() {
    return ItemStack.EMPTY;
  }

  @Override
  public RecipeSerializer<?> getSerializer() {
    return ModRecipes.EMPTY_SERIALIZER.get();
  }
}

