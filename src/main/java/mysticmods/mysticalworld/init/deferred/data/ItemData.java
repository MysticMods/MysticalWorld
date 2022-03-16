package mysticmods.mysticalworld.init.deferred.data;

import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashSet;
import java.util.Set;

public class ItemData {
  private static Set<RegistryObject<? extends Item>> ITEMS = new HashSet<>();

  public static <T extends Item> void storeItem(RegistryObject<T> item) {
    ITEMS.add(item);
  }

  public static Set<RegistryObject<? extends Item>> getAllItems () {
    return ITEMS;
  }
}
