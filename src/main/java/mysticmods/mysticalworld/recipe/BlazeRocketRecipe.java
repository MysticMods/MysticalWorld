package mysticmods.mysticalworld.recipe;

import mysticmods.mysticalworld.init.ModRecipes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;

public class BlazeRocketRecipe extends CustomRecipe {
  private static final Ingredient INGREDIENT_PAPER = Ingredient.of(Items.PAPER);
  private static final Ingredient INGREDIENT_BLAZE_POWDER = Ingredient.of(Items.BLAZE_POWDER);
  private static final Ingredient INGREDIENT_FIREWORK_STAR = Ingredient.of(Items.FIREWORK_STAR);

  public BlazeRocketRecipe(ResourceLocation idIn) {
    super(idIn);
  }

  @Override
  public boolean matches(CraftingContainer inv, Level worldIn) {
    boolean flag = false;
    int i = 0;

    for (int j = 0; j < inv.getContainerSize(); ++j) {
      ItemStack itemstack = inv.getItem(j);
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
  public ItemStack assemble(CraftingContainer inv) {
    ItemStack itemstack = new ItemStack(Items.FIREWORK_ROCKET, 5);
    CompoundTag compoundnbt = itemstack.getOrCreateTagElement("Fireworks");
    ListTag listnbt = new ListTag();
    int i = 0;

    for (int j = 0; j < inv.getContainerSize(); ++j) {
      ItemStack itemstack1 = inv.getItem(j);
      if (!itemstack1.isEmpty()) {
        if (INGREDIENT_BLAZE_POWDER.test(itemstack1)) {
          ++i;
        } else if (INGREDIENT_FIREWORK_STAR.test(itemstack1)) {
          CompoundTag compoundnbt1 = itemstack1.getTagElement("Explosion");
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
  public boolean canCraftInDimensions(int width, int height) {
    return width * height >= 2;
  }

  /**
   * Get the result of this recipe, usually for display purposes (e.g. recipe book). If your recipe has more than one
   * possible result (e.g. it's dynamic and depends on its inputs), then return an empty stack.
   */
  @Override
  public ItemStack getResultItem() {
    return new ItemStack(Items.FIREWORK_ROCKET);
  }

  @Override
  public RecipeSerializer<?> getSerializer() {
    return ModRecipes.BLAZE_SERIALIZER.get();
  }
}

