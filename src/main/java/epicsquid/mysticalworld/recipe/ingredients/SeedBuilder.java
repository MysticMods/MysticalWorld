package epicsquid.mysticalworld.recipe.ingredients;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.regex.Pattern;

public class SeedBuilder {
  private static final Pattern SEED_PATTERN = Pattern.compile("(?:(?:(?:[A-Z-_.:]|^)seed)|(?:(?:[a-z-_.:]|^)Seed))(?:[sA-Z-_.:]|$)");
  private static Ingredient SEEDS = null;

  public static Ingredient get() {
    if (SEEDS == null) {
      NonNullList<ItemStack> matchingStacks = NonNullList.create();
      for (Item item : ForgeRegistries.ITEMS.getValues()) {
        if (!SEED_PATTERN.matcher(item.getTranslationKey()).find()) {
          continue;
        }

        if (item == Items.NETHER_WART) {
          continue;
        }

        if (item instanceof BlockItem) {
          Block block = ((BlockItem) item).getBlock();
          if (block instanceof IPlantable) {
            matchingStacks.add(new ItemStack(item));
          }
        }
      }
      SEEDS = Ingredient.fromStacks(matchingStacks.toArray(new ItemStack[0]));
    }

    return SEEDS;
  }
}
