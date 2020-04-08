/*package epicsquid.mysticalworld.recipe;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.Tag;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class DamagedIngredient extends Ingredient {
  protected DamagedIngredient(Stream<? extends IItemList> itemLists) {
    super(itemLists);
  }

  public static DamagedIngredient getInstance(Tag<Item> tag) {
    return getInstance(tag.getAllElements().toArray(new Item[0]));
  }

  public static DamagedIngredient getInstance(Item... items) {
    List<ItemStack> itemStream = new ArrayList<>();
    for (Item item : items) {
      ItemStack stack = new ItemStack(item);
      for (int i = 0; i < item.getMaxDamage(stack); i++) {
        ItemStack copy = stack.copy();
        copy.setDamage(i);
        itemStream.add(copy);
      }
    }

    return new DamagedIngredient(itemStream.stream().map(SingleItemList::new));
  }
}*/
