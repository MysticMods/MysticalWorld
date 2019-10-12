package epicsquid.mysticalworld;

import epicsquid.mysticallib.block.BaseCropBlock;
import epicsquid.mysticallib.material.IMaterial;
import epicsquid.mysticallib.material.MaterialGenerator;
import epicsquid.mysticalworld.blocks.AubergineCropBlock;
import epicsquid.mysticalworld.blocks.ModBlocks;
import epicsquid.mysticalworld.blocks.ThatchBlock;
import epicsquid.mysticalworld.entity.*;
import epicsquid.mysticalworld.items.*;
import epicsquid.mysticalworld.items.materials.ModMaterials;
import epicsquid.mysticalworld.items.materials.QuicksilverMaterial;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.*;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.PlantType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Mod.EventBusSubscriber(modid = MysticalWorld.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegistryManager {

	private static final String SWORD = "SWORD";
	private static final String KNIFE = "KNIFE";
	private static final String PICKAXE = "PICKAXE";
	private static final String AXE = "AXE";
	private static final String SHOVEL = "SHOVEL";
	private static final String HOE = "HOE";
	private static final String SPEAR = "SPEAR";

	private static List<Block> blocks = new ArrayList<>();
	private static List<Block> metalBlocks = new ArrayList<>();
	//private static List<Block> chestBlocks = new ArrayList<>();

	private static IMaterial quicksilver = null;

	public static IMaterial getQuicksilver () {
		if (quicksilver == null) {
			quicksilver = new QuicksilverMaterial();
		}

		return quicksilver;
	}

	public static EntityType<BeetleEntity> BEETLE = EntityType.Builder.create(BeetleEntity::new, EntityClassification.CREATURE).size(0.75f, 0.75f).setTrackingRange(64).setShouldReceiveVelocityUpdates(true).setUpdateInterval(3).build(new ResourceLocation(MysticalWorld.MODID, "beetle").toString());
	public static EntityType<DeerEntity> DEER = EntityType.Builder.create(DeerEntity::new, EntityClassification.CREATURE).size(1.0f, 1.0f).setTrackingRange(64).setShouldReceiveVelocityUpdates(true).setUpdateInterval(3).build("deer");
	public static EntityType<FrogEntity> FROG = EntityType.Builder.create(FrogEntity::new, EntityClassification.AMBIENT).size(0.5f, 0.5f).setTrackingRange(64).setShouldReceiveVelocityUpdates(true).setUpdateInterval(3).build("frog");
	public static EntityType<FoxEntity> SILVER_FOX = EntityType.Builder.create(FoxEntity::new, EntityClassification.CREATURE).size(0.75f, 0.75f).setTrackingRange(64).setShouldReceiveVelocityUpdates(true).setUpdateInterval(3).build("silver_fox");
	public static EntityType<SproutEntity> SPROUT = EntityType.Builder.create(SproutEntity::new, EntityClassification.CREATURE).size(0.5f, 1.0f).setTrackingRange(64).setShouldReceiveVelocityUpdates(true).setUpdateInterval(3).build("sprout");

	@SubscribeEvent
	public static void registerItems (RegistryEvent.Register<Item> event) {
		event.getRegistry().register(ModItems.CARAPACE = new Item(new Item.Properties().group(MysticalWorld.ITEM_GROUP)).setRegistryName(MysticalWorld.MODID, "carapace"));
		event.getRegistry().register(ModItems.PELT = new Item(new Item.Properties().group(MysticalWorld.ITEM_GROUP)).setRegistryName(MysticalWorld.MODID, "pelt"));
		event.getRegistry().register(ModItems.ANTLERS = new Item(new Item.Properties().group(MysticalWorld.ITEM_GROUP)).setRegistryName(MysticalWorld.MODID, "antlers"));
		event.getRegistry().register(ModItems.INK_BOTTLE = new DyeItem(DyeColor.BLACK, new Item.Properties().group(MysticalWorld.ITEM_GROUP).containerItem(Items.GLASS_BOTTLE)).setRegistryName(MysticalWorld.MODID, "ink_bottle"));
		event.getRegistry().register(ModItems.VENISON = new Item(new Item.Properties().group(MysticalWorld.ITEM_GROUP).food(ModFoods.VENISION)).setRegistryName(MysticalWorld.MODID, "venison"));
		event.getRegistry().register(ModItems.COOKED_VENISON = new Item(new Item.Properties().group(MysticalWorld.ITEM_GROUP).food(ModFoods.COOKED_VENISION)).setRegistryName(MysticalWorld.MODID, "cooked_venison"));
		event.getRegistry().register(ModItems.AUBERGINE = new Item(new Item.Properties().group(MysticalWorld.ITEM_GROUP).food(ModFoods.AUBERGINE)).setRegistryName(MysticalWorld.MODID, "aubergine"));
		event.getRegistry().register(ModItems.COOKED_AUBERGINE = new Item(new Item.Properties().group(MysticalWorld.ITEM_GROUP).food(ModFoods.COOKED_AUBERGINE)).setRegistryName(MysticalWorld.MODID, "cooked_aubergine"));
		event.getRegistry().register(ModItems.STUFFED_AUBERGINE = new Item(new Item.Properties().group(MysticalWorld.ITEM_GROUP).food(ModFoods.STUFFED_AUBERGINE)).setRegistryName(MysticalWorld.MODID, "stuffed_aubergine"));
		event.getRegistry().register(ModItems.AUBERGINE_SEEDS = new BlockNamedItem(ModBlocks.AUBERGINE_CROP, new Item.Properties().group(MysticalWorld.ITEM_GROUP)).setRegistryName(MysticalWorld.MODID, "aubergine_seed"));
		event.getRegistry().register(ModItems.RAW_SQUID = new Item(new Item.Properties().group(MysticalWorld.ITEM_GROUP).food(ModFoods.RAW_SQUID)).setRegistryName(MysticalWorld.MODID, "raw_squid"));
		event.getRegistry().register(ModItems.COOKED_SQUID = new Item(new Item.Properties().group(MysticalWorld.ITEM_GROUP).food(ModFoods.COOKED_SQUID)).setRegistryName(MysticalWorld.MODID, "cooked_squid"));
		event.getRegistry().register(ModItems.EPIC_SQUID = new EffectItem(new Item.Properties().group(MysticalWorld.ITEM_GROUP).food(ModFoods.EPIC_SQUID).rarity(Rarity.EPIC)).setRegistryName(MysticalWorld.MODID, "epic_squid"));
		//event.getRegistry().register(new UnripePearlItem(new Item.Properties().group(MysticalWorld.ITEM_GROUP)).setRegistryName(MysticalWorld.MODID, "unripe_ender_pearl"));

		event.getRegistry().register(ModItems.SPAWN_BEETLE = new SpawnEggItem(BEETLE, 0x418594, 0x211D15, new Item.Properties().group(MysticalWorld.ITEM_GROUP)).setRegistryName(MysticalWorld.MODID, "beetle_spawn_egg"));
		event.getRegistry().register(ModItems.SPAWN_DEER = new SpawnEggItem(DEER, 0xA18458, 0x5E4D33, new Item.Properties().group(MysticalWorld.ITEM_GROUP)).setRegistryName(MysticalWorld.MODID, "deer_spawn_egg"));
		event.getRegistry().register(ModItems.SPAWN_FROG = new SpawnEggItem(FROG, 0x285234, 0xDBE697, new Item.Properties().group(MysticalWorld.ITEM_GROUP)).setRegistryName(MysticalWorld.MODID, "frog_spawn_egg"));
		event.getRegistry().register(ModItems.SPAWN_SPROUT = new SpawnEggItem(SPROUT, 0xe8F442, 0xD11f5A, new Item.Properties().group(MysticalWorld.ITEM_GROUP)).setRegistryName(MysticalWorld.MODID, "sprout_spawn_egg"));
		event.getRegistry().register(ModItems.SPAWN_SILVER_FOX = new SpawnEggItem(SILVER_FOX, 0xD46724, 0xF5E0D3, new Item.Properties().group(MysticalWorld.ITEM_GROUP)).setRegistryName(MysticalWorld.MODID, "silver_fox_spawn_egg"));

		IMaterial quickMat = getQuicksilver();
		event.getRegistry().register(new Item(quickMat.getItemProps()).setRegistryName(MysticalWorld.MODID, quickMat.getName() + "_ingot"));
		event.getRegistry().register(new Item(quickMat.getItemProps()).setRegistryName(MysticalWorld.MODID, quickMat.getName() + "_nugget"));
		event.getRegistry().register(new Item(quickMat.getItemProps()).setRegistryName(MysticalWorld.MODID, quickMat.getName() + "_dust"));
		event.getRegistry().register(new QuicksilverSwordItem(quickMat.getTier(), (int) quickMat.getAttackDamage(SWORD), quickMat.getAttackSpeed(SWORD), quickMat.getItemProps()).setRegistryName(MysticalWorld.MODID, quickMat.getName() + "_sword"));
		event.getRegistry().register(new QuicksilverPickaxeItem(quickMat.getTier(), (int) quickMat.getAttackDamage(PICKAXE), quickMat.getAttackSpeed(PICKAXE), quickMat.getItemProps()).setRegistryName(MysticalWorld.MODID, quickMat.getName() + "_pickaxe"));
		event.getRegistry().register(new QuicksilverAxeItem(quickMat.getTier(), quickMat.getAttackDamage(AXE), quickMat.getAttackSpeed(AXE), quickMat.getItemProps()).setRegistryName(MysticalWorld.MODID, quickMat.getName() + "_axe"));
		event.getRegistry().register(new QuicksilverShovelItem(quickMat.getTier(), quickMat.getAttackDamage(SHOVEL), quickMat.getAttackSpeed(SHOVEL), quickMat.getItemProps()).setRegistryName(MysticalWorld.MODID, quickMat.getName() + "_shovel"));
		event.getRegistry().register(new QuicksilverKnifeItem(quickMat.getTier(), quickMat.getAttackDamage(KNIFE), quickMat.getAttackSpeed(KNIFE), quickMat.getItemProps()).setRegistryName(MysticalWorld.MODID, quickMat.getName() + "_knife"));
		event.getRegistry().register(new QuicksilverHoeItem(quickMat.getTier(), quickMat.getAttackSpeed(HOE), quickMat.getItemProps()).setRegistryName(MysticalWorld.MODID, quickMat.getName() + "_hoe"));
		event.getRegistry().register(new QuicksilverArmorItem(quickMat.getArmor(), EquipmentSlotType.HEAD, quickMat.getItemProps()).setRegistryName(MysticalWorld.MODID, quickMat.getName() + "_helmet"));
		event.getRegistry().register(new QuicksilverArmorItem(quickMat.getArmor(), EquipmentSlotType.CHEST, quickMat.getItemProps()).setRegistryName(MysticalWorld.MODID, quickMat.getName() + "_chestplate"));
		event.getRegistry().register(new QuicksilverArmorItem(quickMat.getArmor(), EquipmentSlotType.LEGS, quickMat.getItemProps()).setRegistryName(MysticalWorld.MODID, quickMat.getName() + "_leggings"));
		event.getRegistry().register(new QuicksilverArmorItem(quickMat.getArmor(), EquipmentSlotType.FEET, quickMat.getItemProps()).setRegistryName(MysticalWorld.MODID, quickMat.getName() + "_boots"));

		ModMaterials.getMaterials().forEach(mat -> MaterialGenerator.getInstance().generateItems(mat, event.getRegistry(), MysticalWorld.MODID));
		ModMaterials.addMaterial(getQuicksilver());

		blocks.forEach(block -> event.getRegistry().register(new BlockItem(block, new Item.Properties().group(MysticalWorld.ITEM_GROUP)).setRegistryName(Objects.requireNonNull(block.getRegistryName()))));
		metalBlocks.forEach(block -> event.getRegistry().register(new BlockItem(block, new Item.Properties().group(MysticalWorld.METAL_ITEM_GROUP)).setRegistryName(Objects.requireNonNull(block.getRegistryName()))));
		//chestBlocks.forEach(block -> event.getRegistry().register(new BlockItem(block, new Item.Properties().group(MysticalWorld.ITEM_GROUP).setTEISR(() -> PaintedChestItemStackRenderer::new)).setRegistryName(block.getRegistryName())));
	}

	@SubscribeEvent
	public static void registerBlocks (RegistryEvent.Register<Block> event) {
		IForgeRegistry<Block> registry = event.getRegistry();
		event.getRegistry().register(ModBlocks.AUBERGINE_CROP = new AubergineCropBlock(Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0f).sound(SoundType.CROP)).setRegistryName(new ResourceLocation(MysticalWorld.MODID, "aubergine_crop")));
		blocks.add(ModBlocks.THATCH = new ThatchBlock(Block.Properties.create(Material.WOOD).sound(SoundType.PLANT)).setRegistryName(new ResourceLocation(MysticalWorld.MODID, "thatch")));
		/*for (DyeColor dye : DyeColor.values()) {
			chestBlocks.add(new PaintedChestBlock(Block.Properties.create(Material.WOOD).hardnessAndResistance(2.5F).sound(SoundType.WOOD), dye).setRegistryName(MysticalWorld.MODID, dye.getName() + "_painted_chest"));
		}*/
		blocks.forEach(registry::register);
		//chestBlocks.forEach(registry::register);

		// These register themselves just fine
		// TODO clean this up
		metalBlocks.addAll(MaterialGenerator.getInstance().generateBlocks(getQuicksilver(), event.getRegistry(), MysticalWorld.MODID));
		ModMaterials.getMaterials().forEach(mat -> metalBlocks.addAll(MaterialGenerator.getInstance().generateBlocks(mat, event.getRegistry(), MysticalWorld.MODID)));
	}

	@SubscribeEvent
	public static void registerEntities (RegistryEvent.Register<EntityType<?>> event) {
		event.getRegistry().register(BEETLE.setRegistryName(MysticalWorld.MODID, "beetle"));
		event.getRegistry().register(DEER.setRegistryName(MysticalWorld.MODID, "deer"));
		event.getRegistry().register(SILVER_FOX.setRegistryName(MysticalWorld.MODID, "silver_fox"));
		event.getRegistry().register(FROG.setRegistryName(MysticalWorld.MODID, "frog"));
		event.getRegistry().register(SPROUT.setRegistryName(MysticalWorld.MODID, "sprout"));

		// Register spawns
		ModEntities.registerMobSpawns();
	}

	@SubscribeEvent
	public static void onTileEntityRegistry (RegistryEvent.Register<TileEntityType<?>> event) {
		/*event.getRegistry().register(TileEntityType.Builder.create(() -> new PaintedChestTileEntity(ModTileEntities.WHITE_PAINTED_CHEST, DyeColor.WHITE), ModBlocks.WHITE_PAINTED_CHEST).build(null).setRegistryName(MysticalWorld.MODID, "white_painted_chest"));
		event.getRegistry().register(TileEntityType.Builder.create(() -> new PaintedChestTileEntity(ModTileEntities.ORANGE_PAINTED_CHEST, DyeColor.ORANGE), ModBlocks.ORANGE_PAINTED_CHEST).build(null).setRegistryName(MysticalWorld.MODID, "orange_painted_chest"));
		event.getRegistry().register(TileEntityType.Builder.create(() -> new PaintedChestTileEntity(ModTileEntities.MAGENTA_PAINTED_CHEST, DyeColor.MAGENTA), ModBlocks.MAGENTA_PAINTED_CHEST).build(null).setRegistryName(MysticalWorld.MODID, "magenta_painted_chest"));
		event.getRegistry().register(TileEntityType.Builder.create(() -> new PaintedChestTileEntity(ModTileEntities.LIGHT_BLUE_PAINTED_CHEST, DyeColor.LIGHT_BLUE), ModBlocks.LIGHT_BLUE_PAINTED_CHEST).build(null).setRegistryName(MysticalWorld.MODID, "light_blue_painted_chest"));
		event.getRegistry().register(TileEntityType.Builder.create(() -> new PaintedChestTileEntity(ModTileEntities.YELLOW_PAINTED_CHEST, DyeColor.YELLOW), ModBlocks.YELLOW_PAINTED_CHEST).build(null).setRegistryName(MysticalWorld.MODID, "yellow_painted_chest"));
		event.getRegistry().register(TileEntityType.Builder.create(() -> new PaintedChestTileEntity(ModTileEntities.LIME_PAINTED_CHEST, DyeColor.LIME), ModBlocks.LIME_PAINTED_CHEST).build(null).setRegistryName(MysticalWorld.MODID, "lime_painted_chest"));
		event.getRegistry().register(TileEntityType.Builder.create(() -> new PaintedChestTileEntity(ModTileEntities.PINK_PAINTED_CHEST, DyeColor.PINK), ModBlocks.PINK_PAINTED_CHEST).build(null).setRegistryName(MysticalWorld.MODID, "pink_painted_chest"));
		event.getRegistry().register(TileEntityType.Builder.create(() -> new PaintedChestTileEntity(ModTileEntities.GRAY_PAINTED_CHEST, DyeColor.GRAY), ModBlocks.GRAY_PAINTED_CHEST).build(null).setRegistryName(MysticalWorld.MODID, "gray_painted_chest"));
		event.getRegistry().register(TileEntityType.Builder.create(() -> new PaintedChestTileEntity(ModTileEntities.LIGHT_GRAY_PAINTED_CHEST, DyeColor.LIGHT_GRAY), ModBlocks.LIGHT_GRAY_PAINTED_CHEST).build(null).setRegistryName(MysticalWorld.MODID, "light_gray_painted_chest"));
		event.getRegistry().register(TileEntityType.Builder.create(() -> new PaintedChestTileEntity(ModTileEntities.CYAN_PAINTED_CHEST, DyeColor.CYAN), ModBlocks.CYAN_PAINTED_CHEST).build(null).setRegistryName(MysticalWorld.MODID, "cyan_painted_chest"));
		event.getRegistry().register(TileEntityType.Builder.create(() -> new PaintedChestTileEntity(ModTileEntities.PURPLE_PAINTED_CHEST, DyeColor.PURPLE), ModBlocks.PURPLE_PAINTED_CHEST).build(null).setRegistryName(MysticalWorld.MODID, "purple_painted_chest"));
		event.getRegistry().register(TileEntityType.Builder.create(() -> new PaintedChestTileEntity(ModTileEntities.BLUE_PAINTED_CHEST, DyeColor.BLUE), ModBlocks.BLUE_PAINTED_CHEST).build(null).setRegistryName(MysticalWorld.MODID, "blue_painted_chest"));
		event.getRegistry().register(TileEntityType.Builder.create(() -> new PaintedChestTileEntity(ModTileEntities.BROWN_PAINTED_CHEST, DyeColor.BROWN), ModBlocks.BROWN_PAINTED_CHEST).build(null).setRegistryName(MysticalWorld.MODID, "brown_painted_chest"));
		event.getRegistry().register(TileEntityType.Builder.create(() -> new PaintedChestTileEntity(ModTileEntities.GREEN_PAINTED_CHEST, DyeColor.GREEN), ModBlocks.GREEN_PAINTED_CHEST).build(null).setRegistryName(MysticalWorld.MODID, "green_painted_chest"));
		event.getRegistry().register(TileEntityType.Builder.create(() -> new PaintedChestTileEntity(ModTileEntities.RED_PAINTED_CHEST, DyeColor.RED), ModBlocks.RED_PAINTED_CHEST).build(null).setRegistryName(MysticalWorld.MODID, "red_painted_chest"));
		event.getRegistry().register(TileEntityType.Builder.create(() -> new PaintedChestTileEntity(ModTileEntities.BLACK_PAINTED_CHEST, DyeColor.BLACK), ModBlocks.BLACK_PAINTED_CHEST).build(null).setRegistryName(MysticalWorld.MODID, "black_painted_chest"));*/
	}

	@SubscribeEvent
	public static void onContainerRegistry (RegistryEvent.Register<ContainerType<?>> event) {
		//event.getRegistry().register(IForgeContainerType.create(((windowId, inv, data) -> ChestContainer.createGeneric9X3(windowId, MysticalLib.proxy.getClientPlayer().inventory, inv))).setRegistryName(MysticalWorld.MODID, "red_painted_chest"));
	}
}
