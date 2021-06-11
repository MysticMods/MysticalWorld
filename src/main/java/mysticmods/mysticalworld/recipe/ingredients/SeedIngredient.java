package mysticmods.mysticalworld.recipe.ingredients;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import it.unimi.dsi.fastutil.ints.IntList;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.IIngredientSerializer;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class SeedIngredient extends Ingredient {
  public static final SeedIngredient INSTANCE = new SeedIngredient();
  private static final Pattern SEED_PATTERN = Pattern.compile("(?:(?:(?:[A-Z-_.:]|^)seed)|(?:(?:[a-z-_.:]|^)Seed))(?:[sA-Z-_.:]|$)");
  private static Ingredient SEEDS = null;

  public SeedIngredient() {
    super(Stream.empty());
  }

  @Override
  public boolean isSimple() {
    return false;
  }

  @Override
  public ItemStack[] getMatchingStacks() {
    return get().getMatchingStacks();
  }

  @Override
  public IntList getValidItemStacksPacked() {
    return get().getValidItemStacksPacked();
  }

  @Override
  public boolean hasNoMatchingItems() {
    return get().hasNoMatchingItems();
  }

  @Override
  protected void invalidate() {
    super.invalidate();
    SEEDS = null;
  }

  @Override
  public IIngredientSerializer<? extends Ingredient> getSerializer() {
    return Serializer.INSTANCE;
  }

  @Override
  public JsonElement serialize() {
    JsonObject obj = new JsonObject();
    obj.addProperty("type", CraftingHelper.getID(Serializer.INSTANCE).toString());
    return obj;
  }

  @Override
  public boolean test(@Nullable ItemStack p_test_1_) {
    return get().test(p_test_1_);
  }

  private static Ingredient get() {
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

  public static class Serializer implements IIngredientSerializer<SeedIngredient> {
    public static final Serializer INSTANCE = new Serializer();
    private static final short ID = 0x123;

    @Override
    public SeedIngredient parse(PacketBuffer buffer) {
      return SeedIngredient.INSTANCE;
    }

    @Override
    public SeedIngredient parse(JsonObject json) {
      return SeedIngredient.INSTANCE;
    }

    @Override
    public void write(PacketBuffer buffer, SeedIngredient ingredient) {
    }
  }
}
