package epicsquid.mysticalworld;

import epicsquid.mysticallib.LibRegistry;
import epicsquid.mysticallib.block.BaseCropBlock;
import epicsquid.mysticallib.item.KnifeItem;
import epicsquid.mysticallib.item.SeedItem;
import epicsquid.mysticalworld.blocks.ModBlocks;
import epicsquid.mysticalworld.blocks.ThatchBlock;
import epicsquid.mysticalworld.entity.*;
import epicsquid.mysticalworld.items.ModFoods;
import epicsquid.mysticalworld.items.materials.ModMaterials;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.inventory.EquipmentSlotType;
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
	private static List<Block> metalBlocks = new ArrayList<>();

	public static EntityType<BeetleEntity> BEETLE = EntityType.Builder.create(BeetleEntity::new, EntityClassification.CREATURE).size(0.75f, 0.75f).build("beetle");
	public static EntityType<DeerEntity> DEER = EntityType.Builder.create(DeerEntity::new, EntityClassification.CREATURE).size(1.0f, 1.0f).build("deer");
	public static EntityType<FrogEntity> FROG = EntityType.Builder.create(FrogEntity::new, EntityClassification.AMBIENT).size(0.5f, 0.5f).build("frog");
	public static EntityType<FoxEntity> SILVER_FOX = EntityType.Builder.create(FoxEntity::new, EntityClassification.CREATURE).size(0.75f, 0.75f).build("silver_fox");
	public static EntityType<SproutEntity> SPROUT = EntityType.Builder.create(SproutEntity::new, EntityClassification.CREATURE).size(0.5f, 1.0f).build("sprout");

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		event.getRegistry().register(new Item(new Item.Properties().group(MysticalWorld.ITEM_GROUP)).setRegistryName(MysticalWorld.MODID, "carapace"));
		event.getRegistry().register(new Item(new Item.Properties().group(MysticalWorld.ITEM_GROUP)).setRegistryName(MysticalWorld.MODID, "pelt"));
		event.getRegistry().register(new Item(new Item.Properties().group(MysticalWorld.ITEM_GROUP)).setRegistryName(MysticalWorld.MODID, "antlers"));
		event.getRegistry().register(new DyeItem(DyeColor.BLACK, new Item.Properties().group(MysticalWorld.ITEM_GROUP).containerItem(Items.GLASS_BOTTLE)).setRegistryName(MysticalWorld.MODID, "ink_bottle"));
		event.getRegistry().register(new Item(new Item.Properties().group(MysticalWorld.ITEM_GROUP).food(ModFoods.VENISION)).setRegistryName(MysticalWorld.MODID, "venison"));
		event.getRegistry().register(new Item(new Item.Properties().group(MysticalWorld.ITEM_GROUP).food(ModFoods.COOKED_VENISION)).setRegistryName(MysticalWorld.MODID, "cooked_venison"));
		event.getRegistry().register(new Item(new Item.Properties().group(MysticalWorld.ITEM_GROUP).food(ModFoods.AUBERGINE)).setRegistryName(MysticalWorld.MODID, "aubergine"));
		event.getRegistry().register(new Item(new Item.Properties().group(MysticalWorld.ITEM_GROUP).food(ModFoods.COOKED_AUBERGINE)).setRegistryName(MysticalWorld.MODID, "cooked_aubergine"));
		event.getRegistry().register(new Item(new Item.Properties().group(MysticalWorld.ITEM_GROUP).food(ModFoods.STUFFED_AUBERGINE)).setRegistryName(MysticalWorld.MODID, "stuffed_aubergine"));
		event.getRegistry().register(new SeedItem(new Item.Properties().group(MysticalWorld.ITEM_GROUP), ModBlocks.AUBERGINE_CROP, PlantType.Crop).setRegistryName(MysticalWorld.MODID, "aubergine_seed"));

		event.getRegistry().register(new SpawnEggItem(BEETLE, 0x418594, 0x211D15, new Item.Properties().group(MysticalWorld.ITEM_GROUP)).setRegistryName(MysticalWorld.MODID, "beetle_spawn_egg"));
		event.getRegistry().register(new SpawnEggItem(DEER, 0xA18458, 0x5E4D33, new Item.Properties().group(MysticalWorld.ITEM_GROUP)).setRegistryName(MysticalWorld.MODID, "deer_spawn_egg"));
		event.getRegistry().register(new SpawnEggItem(FROG, 0x418594, 0x211D15, new Item.Properties().group(MysticalWorld.ITEM_GROUP)).setRegistryName(MysticalWorld.MODID, "frog_spawn_egg"));
		event.getRegistry().register(new SpawnEggItem(SPROUT, 0xe8F442, 0xD11f5A, new Item.Properties().group(MysticalWorld.ITEM_GROUP)).setRegistryName(MysticalWorld.MODID, "sprout_spawn_egg"));
		event.getRegistry().register(new SpawnEggItem(SILVER_FOX, 0xD46724, 0xF5E0D3, new Item.Properties().group(MysticalWorld.ITEM_GROUP)).setRegistryName(MysticalWorld.MODID, "silver_fox_spawn_egg"));

		ModMaterials.getMaterials().forEach(mat -> LibRegistry.registerMetalSetItems(mat, event.getRegistry(), MysticalWorld.MODID));

		blocks.forEach(block -> event.getRegistry().register(new BlockItem(block, new Item.Properties().group(MysticalWorld.ITEM_GROUP)).setRegistryName(block.getRegistryName())));
		metalBlocks.forEach(block -> event.getRegistry().register(new BlockItem(block, new Item.Properties().group(MysticalWorld.METAL_ITEM_GROUP)).setRegistryName(block.getRegistryName())));
	}

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		event.getRegistry().register(new BaseCropBlock(Block.Properties.create(Material.PLANTS), PlantType.Crop).setRegistryName(new ResourceLocation(MysticalWorld.MODID, "aubergine_crop")));
		blocks.add(new ThatchBlock(Block.Properties.create(Material.WOOD).sound(SoundType.PLANT)).setRegistryName(new ResourceLocation(MysticalWorld.MODID, "thatch")));
		blocks.forEach(block -> event.getRegistry().register(block));

		// These register themselves just fine
		ModMaterials.getMaterials().forEach(mat -> metalBlocks.addAll(LibRegistry.registerMetalSetBlocks(mat, event.getRegistry(), MysticalWorld.MODID)));
	}

	@SubscribeEvent
	public static void registerEntities(RegistryEvent.Register<EntityType<?>> event) {
		event.getRegistry().register(BEETLE.setRegistryName(MysticalWorld.MODID, "beetle"));
		event.getRegistry().register(DEER.setRegistryName(MysticalWorld.MODID, "deer"));
		event.getRegistry().register(SILVER_FOX.setRegistryName(MysticalWorld.MODID, "silver_fox"));
		event.getRegistry().register(FROG.setRegistryName(MysticalWorld.MODID, "frog"));
		event.getRegistry().register(SPROUT.setRegistryName(MysticalWorld.MODID, "sprout"));
	}
}
