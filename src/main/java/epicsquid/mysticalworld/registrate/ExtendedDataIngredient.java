package epicsquid.mysticalworld.registrate;

import com.google.common.collect.ObjectArrays;
import com.tterrag.registrate.util.DataIngredient;
import com.tterrag.registrate.util.RegistryEntry;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.registries.IForgeRegistryEntry;

import java.util.stream.Stream;

public class ExtendedDataIngredient {
  public static <T extends IItemProvider & IForgeRegistryEntry<? super T>> DataIngredient entry(RegistryEntry<T> first, RegistryEntry<T>... others) {
    return DataIngredient.ingredient(Ingredient.fromItems(Stream.of(ObjectArrays.concat(first, others)).map(RegistryEntry::get).map(IItemProvider::asItem).toArray(Item[]::new)), first.get());
  }
}
