package epicsquid.mysticalworld.recipe;

import com.google.gson.JsonObject;
import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.init.ModItems;
import epicsquid.mysticalworld.init.ModRecipes;
import epicsquid.mysticalworld.recipe.ingredients.SeedBuilder;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapelessRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistryEntry;
import noobanidus.libs.noobutil.types.ItemStackStore;

public class AssortedSeedsRecipe extends ShapelessRecipe {
  public static ResourceLocation SEEDS_RECIPE = new ResourceLocation(MysticalWorld.MODID, "assorted_seeds");
  public static ItemStackStore RESULT = new ItemStackStore(ModItems.ASSORTED_SEEDS, 4);

  public AssortedSeedsRecipe() {
    super(SEEDS_RECIPE, "", RESULT.get(), NonNullList.withSize(4, SeedBuilder.get()));
  }

  @Override
  public boolean matches(CraftingInventory inv, World worldIn) {
    int i = 0;

    Ingredient seed = SeedBuilder.get();

    for (int j = 0; j < inv.getSizeInventory(); ++j) {
      ItemStack itemstack = inv.getStackInSlot(j);
      if (seed.test(itemstack)) {
        i++;
        if (i > 4) {
          return false;
        }
      }
    }

    return i == 4;
  }

  @Override
  public boolean canFit(int width, int height) {
    return width * height >= 2;
  }

  @Override
  public ItemStack getRecipeOutput() {
    return new ItemStack(ModItems.ASSORTED_SEEDS.get(), 4);
  }

  @Override
  public IRecipeSerializer<?> getSerializer() {
    // TODO
    return ModRecipes.SEED_SERIALIZER.get();
  }

  public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<AssortedSeedsRecipe> {
    public AssortedSeedsRecipe read(ResourceLocation recipeId, JsonObject json) {
      return new AssortedSeedsRecipe();
    }

    public AssortedSeedsRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
      String s = buffer.readString(32767);
      int i = buffer.readVarInt();
      ItemStack itemstack = buffer.readItemStack();
      return new AssortedSeedsRecipe();
    }

    public void write(PacketBuffer buffer, AssortedSeedsRecipe recipe) {
      buffer.writeString("seeds");
      buffer.writeVarInt(0);
      buffer.writeItemStack(RESULT.get());
    }
  }
}

