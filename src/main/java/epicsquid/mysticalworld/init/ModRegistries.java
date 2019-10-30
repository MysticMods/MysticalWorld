package epicsquid.mysticalworld.init;

import epicsquid.mysticalworld.MysticalWorld;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.item.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class ModRegistries {
  public static final Supplier<Item.Properties> SIG = () -> new Item.Properties().group(MysticalWorld.ITEM_GROUP);
  public static final Supplier<Item.Properties> SMG = () -> new Item.Properties().group(MysticalWorld.METAL_ITEM_GROUP);

  public static Supplier<Item.Properties> foodProp (Food food) {
    return () -> new Item.Properties().group(MysticalWorld.ITEM_GROUP).food(food);
  }
}
