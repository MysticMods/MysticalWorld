package epicsquid.mysticalworld.recipes;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.JsonUtils;
import net.minecraftforge.common.crafting.CompoundIngredient;
import net.minecraftforge.common.crafting.IIngredientFactory;
import net.minecraftforge.common.crafting.JsonContext;
import net.minecraftforge.oredict.OreIngredient;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MultiOreIngredient extends CompoundIngredient {
  public MultiOreIngredient(List<String> names) {
    super(names.stream().map(OreIngredient::new).collect(Collectors.toList()));
  }

  @SuppressWarnings("unused")
  public static class Factory implements IIngredientFactory {
    @Nonnull
    @Override
    public Ingredient parse(JsonContext context, JsonObject json) {
      List<String> names = new ArrayList<>();
      for (JsonElement element : JsonUtils.getJsonArray(json, "ores")) {
        names.add(element.getAsString());
      }
      return new MultiOreIngredient(names);
    }
  }
}
