package epicsquid.mysticalworld;

import epicsquid.mysticallib.block.BaseCropBlock;
import epicsquid.mysticallib.item.SeedItem;
import epicsquid.mysticalworld.blocks.ModBlocks;
import epicsquid.mysticalworld.blocks.ThatchBlock;
import epicsquid.mysticalworld.items.ModFoods;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityType;
import net.minecraft.item.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.PlantType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = MysticalWorld.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegistryManager {

	private static List<Block> blocks = new ArrayList<>();

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		event.getRegistry().register(new Item(new Item.Properties().group(MysticalWorld.ITEM_GROUP)).setRegistryName(MysticalWorld.MODID, "carapace"));
		event.getRegistry().register(new Item(new Item.Properties().group(MysticalWorld.ITEM_GROUP)).setRegistryName(MysticalWorld.MODID, "antlers"));
		event.getRegistry().register(new DyeItem(DyeColor.BLACK, new Item.Properties().group(MysticalWorld.ITEM_GROUP).containerItem(Items.GLASS_BOTTLE)).setRegistryName(MysticalWorld.MODID, "ink_bottle"));
		event.getRegistry().register(new Item(new Item.Properties().group(MysticalWorld.ITEM_GROUP).food(ModFoods.VENISION)).setRegistryName(MysticalWorld.MODID, "venison"));
		event.getRegistry().register(new Item(new Item.Properties().group(MysticalWorld.ITEM_GROUP).food(ModFoods.COOKED_VENISION)).setRegistryName(MysticalWorld.MODID, "cooked_venison"));
		event.getRegistry().register(new Item(new Item.Properties().group(MysticalWorld.ITEM_GROUP).food(ModFoods.AUBERGINE)).setRegistryName(MysticalWorld.MODID, "aubergine"));
		event.getRegistry().register(new Item(new Item.Properties().group(MysticalWorld.ITEM_GROUP).food(ModFoods.COOKED_AUBERGINE)).setRegistryName(MysticalWorld.MODID, "cooked_aubergine"));
		event.getRegistry().register(new Item(new Item.Properties().group(MysticalWorld.ITEM_GROUP).food(ModFoods.STUFFED_AUBERGINE)).setRegistryName(MysticalWorld.MODID, "stuffed_aubergine"));
		event.getRegistry().register(new SeedItem(new Item.Properties().group(MysticalWorld.ITEM_GROUP), ModBlocks.AUBERGINE_CROP, PlantType.Crop).setRegistryName(MysticalWorld.MODID, "aubergine_seed"));

		blocks.forEach(block -> event.getRegistry().register(new BlockItem(block, new Item.Properties().group(MysticalWorld.ITEM_GROUP)).setRegistryName(block.getRegistryName())));
	}

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		event.getRegistry().register(new BaseCropBlock(Block.Properties.create(Material.PLANTS), PlantType.Crop).setRegistryName(new ResourceLocation(MysticalWorld.MODID, "aubergine_crop")));
		blocks.add(new ThatchBlock(Block.Properties.create(Material.WOOD).sound(SoundType.PLANT)).setRegistryName(new ResourceLocation(MysticalWorld.MODID, "thatch")));
		blocks.forEach(block -> event.getRegistry().register(block));
	}

	@SubscribeEvent
	public static void registerEntities(RegistryEvent.Register<EntityType<?>> event) {

	}
}
