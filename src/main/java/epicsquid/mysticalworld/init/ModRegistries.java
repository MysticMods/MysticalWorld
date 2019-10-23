package epicsquid.mysticalworld.init;

import epicsquid.mysticallib.block.BaseOreBlock;
import epicsquid.mysticallib.block.OreBlockProperties;
import epicsquid.mysticallib.item.KnifeItem;
import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.blocks.XPOreBlock;
import epicsquid.mysticalworld.materials.MaterialType;
import net.minecraft.block.Block;
import net.minecraft.block.OreBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Function;
import java.util.function.Supplier;

import static epicsquid.mysticalworld.materials.MaterialType.Type;

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

  public static Supplier<XPOreBlock> ore (OreBuilder<XPOreBlock> creator, MaterialType material) {
    return () -> creator.apply(material.getOreBlockProperties().get(), material.getMinXP(), material.getMaxXP());
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

  public static <T extends Item> Supplier<T> tool (ToolBuilder<T> builder, Type Q, MaterialType material, Supplier<Item.Properties> properties) {
    return () -> builder.apply(material, material.getDamage(Q), material.getSpeed(Q), properties.get());
  }

  public static Supplier<SwordItem> sword(ToolBuilder<SwordItem> builder, MaterialType material, Supplier<Item.Properties> properties) {
    return tool(builder, Type.SWORD, material, properties);
  }

  public static Supplier<PickaxeItem> pickaxe(ToolBuilder<PickaxeItem> builder, MaterialType material, Supplier<Item.Properties> properties) {
    return tool(builder, Type.PICKAXE, material, properties);
  }

  public static Supplier<AxeItem> axe(ToolBuilder<AxeItem> builder, MaterialType material, Supplier<Item.Properties> properties) {
    return tool(builder, Type.AXE, material, properties);
  }

  public static Supplier<ShovelItem> shovel(ToolBuilder<ShovelItem> builder, MaterialType material, Supplier<Item.Properties> properties) {
    return tool(builder, Type.SHOVEL, material, properties);
  }

  public static Supplier<KnifeItem> knife(ToolBuilder<KnifeItem> builder, MaterialType material, Supplier<Item.Properties> properties) {
    return tool(builder, Type.KNIFE, material, properties);
  }

  public static Supplier<HoeItem> hoe(HoeBuilder<HoeItem> builder, MaterialType material, Supplier<Item.Properties> properties) {
    return () -> builder.apply(material, material.getSpeed(Type.HOE), properties.get());
  }

  public static Supplier<ArmorItem> armor(ArmorBuilder<ArmorItem> builder, MaterialType material, EquipmentSlotType slot, Supplier<Item.Properties> properties) {
    return () -> builder.apply(material, slot, properties.get());
  }

  @FunctionalInterface
  public interface ToolBuilder<V extends Item> {
    V apply(IItemTier tier, int attackDamageIn, float attackSpeedIn, Item.Properties builder);
  }

  @FunctionalInterface
  public interface HoeBuilder<V extends Item> {
    V apply(IItemTier tier, float attackSpeedIn, Item.Properties builder);
  }

  @FunctionalInterface
  public interface ArmorBuilder<V extends Item> {
    V apply (IArmorMaterial materialIn, EquipmentSlotType slot, Item.Properties builder);
  }

  @FunctionalInterface
  public interface OreBuilder<V extends OreBlock> {
    V apply (Block.Properties properties, int maxXP, int minXP);
  }
}
