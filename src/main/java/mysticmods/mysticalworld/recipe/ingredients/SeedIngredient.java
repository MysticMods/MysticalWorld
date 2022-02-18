package mysticmods.mysticalworld.recipe.ingredients;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import it.unimi.dsi.fastutil.ints.IntList;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.NonNullList;
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
  public ItemStack[] getItems() {
    return get().getItems();
  }

  @Override
  public IntList getStackingIds() {
    return get().getStackingIds();
  }

  @Override
  public boolean isEmpty() {
    return get().isEmpty();
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
  public JsonElement toJson() {
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
        if (!SEED_PATTERN.matcher(item.getDescriptionId()).find()) {
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
      SEEDS = Ingredient.of(matchingStacks.toArray(new ItemStack[0]));
    }
    return SEEDS;
  }

  public static class Serializer implements IIngredientSerializer<SeedIngredient> {
    public static final Serializer INSTANCE = new Serializer();
    private static final short ID = 0x123;

    @Override
    public SeedIngredient parse(FriendlyByteBuf buffer) {
      return SeedIngredient.INSTANCE;
    }

    @Override
    public SeedIngredient parse(JsonObject json) {
      return SeedIngredient.INSTANCE;
    }

    @Override
    public void write(FriendlyByteBuf buffer, SeedIngredient ingredient) {
    }
  }
}
