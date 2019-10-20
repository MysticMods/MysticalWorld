package epicsquid.mysticalworld.init;

import epicsquid.mysticalworld.MysticalWorld;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Function;
import java.util.function.Supplier;

public class ModRegistries {
  public static final Supplier<Item.Properties> SIG = () -> new Item.Properties().group(MysticalWorld.ITEM_GROUP);
  public static final Supplier<Item.Properties> SMG = () -> new Item.Properties().group(MysticalWorld.METAL_ITEM_GROUP);
  public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, MysticalWorld.MODID);
  public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, MysticalWorld.MODID);
  public static final DeferredRegister<EntityType<?>> ENTITIES = new DeferredRegister<>(ForgeRegistries.ENTITIES, MysticalWorld.MODID);

  public static Supplier<Item.Properties> foodProp (Food food) {
    return () -> new Item.Properties().group(MysticalWorld.ITEM_GROUP).food(food);
  }

  public static <T extends Item> RegistryObject<T> registerItem(final String name, final Supplier<T> supplier) {
    return ITEMS.register(name, supplier);
  }

  public static <T extends EntityType<?>> RegistryObject<T> registerEntity(final String name, final Supplier<T> supplier) {
    return ENTITIES.register(name, supplier);
  }

  public static <T extends Block> RegistryObject<T> registerBlockWithoutItem(final String name, final Supplier<T> supplier) {
    return BLOCKS.register(name, supplier);
  }

  public static <T extends Block> RegistryObject<T> registerBlock(final String name, final Supplier<T> supplier, final Supplier<Item.Properties> group) {
    RegistryObject<T> result = BLOCKS.register(name, supplier);
    ITEMS.register(name, blockItem(result, group));
    return result;
  }

  public static <T extends Block> Supplier<T> block(Function<Block.Properties, T> creator, Supplier<Block.Properties> properties) {
    return () -> creator.apply(properties.get());
  }

  public static <T extends Block> Supplier<BlockItem> blockItem(RegistryObject<T> block, Supplier<Item.Properties> properties) {
    return () -> new BlockItem(block.get(), properties.get());
  }

  public static <T extends Block> Supplier<BlockNamedItem> blockNamedItem(RegistryObject<T> block, Supplier<Item.Properties> properties) {
    return () -> new BlockNamedItem(block.get(), properties.get());
  }

  public static <T extends Item> Supplier<T> item(Function<Item.Properties, T> creator, Supplier<Item.Properties> properties) {
    return () -> creator.apply(properties.get());
  }

  public static Supplier<DyeItem> dyeItem(DyeColor dye, Supplier<Item.Properties> properties) {
    return () -> new DyeItem(dye, properties.get());
  }

  public static <T extends Entity> Supplier<EntityType<T>> entity(String name, Supplier<EntityType.Builder<T>> builder) {
    return () -> builder.get().build(name);
  }

  public static Supplier<SpawnEggItem> spawnEgg(RegistryObject<? extends EntityType> entity, int color1, int color2, Supplier<Item.Properties> properties) {
    return () -> new SpawnEggItem(entity.get(), color1, color2, properties.get());
  }
}
