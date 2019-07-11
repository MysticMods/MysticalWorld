package epicsquid.mysticalworld;

import epicsquid.mysticallib.block.BaseCropBlock;
import epicsquid.mysticalworld.blocks.ThatchBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
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
