package epicsquid.mysticalworld.client.data;

import epicsquid.mysticallib.client.data.DeferredItemModelProvider;
import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.init.ModBlocks;
import epicsquid.mysticalworld.init.ModEntities;
import epicsquid.mysticalworld.init.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ExistingFileHelper;

public class MWItemModelProvider extends DeferredItemModelProvider {
  public MWItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
    super("Mystical World Item Model Generator", generator, MysticalWorld.MODID, existingFileHelper);
  }

  @Override
  protected void registerModels() {
    blockItem(ModBlocks.AMETHYST_ORE);
    blockItem(ModBlocks.COPPER_ORE);
    blockItem(ModBlocks.LEAD_ORE);
    blockItem(ModBlocks.QUICKSILVER_ORE);
    blockItem(ModBlocks.SILVER_ORE);
    blockItem(ModBlocks.TIN_ORE);

    blockItem(ModBlocks.AMETHYST_BLOCK);
    blockItem(ModBlocks.COPPER_BLOCK);
    blockItem(ModBlocks.LEAD_BLOCK);
    blockItem(ModBlocks.QUICKSILVER_BLOCK);
    blockItem(ModBlocks.SILVER_BLOCK);
    blockItem(ModBlocks.TIN_BLOCK);

    blockItem(ModBlocks.WET_MUD_BLOCK);
    blockItem(ModBlocks.WET_MUD_BRICK);

    blockItem(ModBlocks.TERRACOTTA_BRICK);

    blockItem(ModBlocks.MUD_BLOCK);
    blockItem(ModBlocks.MUD_BLOCK_STAIRS);
    blockItem(ModBlocks.MUD_BLOCK_SLAB);
    blockWithInventoryModel(ModBlocks.MUD_BLOCK_WALL);
    blockWithInventoryModel(ModBlocks.MUD_BLOCK_FENCE);
    blockItem(ModBlocks.MUD_BLOCK_FENCE_GATE);

    blockItem(ModBlocks.MUD_BRICK);
    blockItem(ModBlocks.MUD_BRICK_STAIRS);
    blockItem(ModBlocks.MUD_BRICK_SLAB);
    blockWithInventoryModel(ModBlocks.MUD_BRICK_WALL);
    blockWithInventoryModel(ModBlocks.MUD_BRICK_FENCE);
    blockItem(ModBlocks.MUD_BRICK_FENCE_GATE);

    blockItem(ModBlocks.CHARRED_LOG);
    blockItem(ModBlocks.CHARRED_PLANKS);
    blockItem(ModBlocks.CHARRED_STAIRS);
    blockItem(ModBlocks.CHARRED_SLAB);
    blockWithInventoryModel(ModBlocks.CHARRED_WALL);
    blockWithInventoryModel(ModBlocks.CHARRED_FENCE);
    blockItem(ModBlocks.CHARRED_FENCE_GATE);

    generated(ModItems.CARAPACE);
    generated(ModItems.PELT);
    generated(ModItems.ANTLERS);
    generated(ModItems.INK_BOTTLE);
    generated(ModItems.UNRIPE_ENDER_PEARL);
    generated(ModItems.VENISON);
    generated(ModItems.COOKED_VENISON);
    generated(ModItems.AUBERGINE_SEEDS);
    generated(ModItems.AUBERGINE);
    generated(ModItems.COOKED_AUBERGINE);
    generated(ModItems.STUFFED_AUBERGINE);
    generated(ModItems.RAW_SQUID);
    generated(ModItems.COOKED_SQUID);
    generated(ModItems.EPIC_SQUID);

    generated(ModItems.AMETHYST_GEM);
    generated(ModItems.COPPER_INGOT);
    generated(ModItems.LEAD_INGOT);
    generated(ModItems.QUICKSILVER_INGOT);
    generated(ModItems.SILVER_INGOT);
    generated(ModItems.TIN_INGOT);
    generated(ModItems.COPPER_NUGGET);
    generated(ModItems.LEAD_NUGGET);
    generated(ModItems.QUICKSILVER_NUGGET);
    generated(ModItems.SILVER_NUGGET);
    generated(ModItems.TIN_NUGGET);
    generated(ModItems.COPPER_DUST);
    generated(ModItems.LEAD_DUST);
    generated(ModItems.QUICKSILVER_DUST);
    generated(ModItems.SILVER_DUST);
    generated(ModItems.TIN_DUST);
    generated(ModItems.GOLD_DUST);
    generated(ModItems.IRON_DUST);
    generated(ModItems.AMETHYST_HELMET);
    generated(ModItems.AMETHYST_CHESTPLATE);
    generated(ModItems.AMETHYST_LEGGINGS);
    generated(ModItems.AMETHYST_BOOTS);
    generated(ModItems.COPPER_HELMET);
    generated(ModItems.COPPER_CHESTPLATE);
    generated(ModItems.COPPER_LEGGINGS);
    generated(ModItems.COPPER_BOOTS);
    generated(ModItems.LEAD_HELMET);
    generated(ModItems.LEAD_CHESTPLATE);
    generated(ModItems.LEAD_LEGGINGS);
    generated(ModItems.LEAD_BOOTS);
    generated(ModItems.QUICKSILVER_HELMET);
    generated(ModItems.QUICKSILVER_CHESTPLATE);
    generated(ModItems.QUICKSILVER_LEGGINGS);
    generated(ModItems.QUICKSILVER_BOOTS);
    generated(ModItems.SILVER_HELMET);
    generated(ModItems.SILVER_CHESTPLATE);
    generated(ModItems.SILVER_LEGGINGS);
    generated(ModItems.SILVER_BOOTS);
    generated(ModItems.TIN_HELMET);
    generated(ModItems.TIN_CHESTPLATE);
    generated(ModItems.TIN_LEGGINGS);
    generated(ModItems.TIN_BOOTS);

    handheld(ModItems.AMETHYST_AXE);
    handheld(ModItems.AMETHYST_HOE);
    handheld(ModItems.AMETHYST_KNIFE);
    handheld(ModItems.AMETHYST_PICKAXE);
    handheld(ModItems.AMETHYST_SHOVEL);
    handheld(ModItems.AMETHYST_SWORD);
    handheld(ModItems.CACTUS_AXE);
    handheld(ModItems.CACTUS_HOE);
    handheld(ModItems.CACTUS_KNIFE);
    handheld(ModItems.CACTUS_PICKAXE);
    handheld(ModItems.CACTUS_SHOVEL);
    handheld(ModItems.CACTUS_SWORD);
    handheld(ModItems.COPPER_AXE);
    handheld(ModItems.COPPER_HOE);
    handheld(ModItems.COPPER_KNIFE);
    handheld(ModItems.COPPER_PICKAXE);
    handheld(ModItems.COPPER_SHOVEL);
    handheld(ModItems.COPPER_SWORD);
    handheld(ModItems.LEAD_AXE);
    handheld(ModItems.LEAD_HOE);
    handheld(ModItems.LEAD_KNIFE);
    handheld(ModItems.LEAD_PICKAXE);
    handheld(ModItems.LEAD_SHOVEL);
    handheld(ModItems.LEAD_SWORD);
    handheld(ModItems.QUICKSILVER_AXE);
    handheld(ModItems.QUICKSILVER_HOE);
    handheld(ModItems.QUICKSILVER_KNIFE);
    handheld(ModItems.QUICKSILVER_PICKAXE);
    handheld(ModItems.QUICKSILVER_SHOVEL);
    handheld(ModItems.QUICKSILVER_SWORD);
    handheld(ModItems.SILVER_AXE);
    handheld(ModItems.SILVER_HOE);
    handheld(ModItems.SILVER_KNIFE);
    handheld(ModItems.SILVER_PICKAXE);
    handheld(ModItems.SILVER_SHOVEL);
    handheld(ModItems.SILVER_SWORD);
    handheld(ModItems.TIN_AXE);
    handheld(ModItems.TIN_HOE);
    handheld(ModItems.TIN_KNIFE);
    handheld(ModItems.TIN_PICKAXE);
    handheld(ModItems.TIN_SHOVEL);
    handheld(ModItems.TIN_SWORD);
    handheld(ModItems.STONE_KNIFE);
    handheld(ModItems.WOODEN_KNIFE);
    handheld(ModItems.DIAMOND_KNIFE);
    handheld(ModItems.GOLD_KNIFE);
    handheld(ModItems.IRON_KNIFE);
    handheld(ModItems.NAUTILUS_HORN);
    withExistingParent(name(ModItems.GLISTERING_HORN), "item/handheld").texture("layer0", itemTexture(ModItems.NAUTILUS_HORN));

    spawnEgg(ModEntities.SPAWN_BEETLE);
    spawnEgg(ModEntities.SPAWN_DEER);
    spawnEgg(ModEntities.SPAWN_FROG);
    spawnEgg(ModEntities.SPAWN_SPROUT);
    spawnEgg(ModEntities.SPAWN_SILVER_FOX);
    spawnEgg(ModEntities.SPAWN_ENDERMINI);
    spawnEgg(ModEntities.SPAWN_LAVA_CAT);
    spawnEgg(ModEntities.SPAWN_SILKWORM);
    spawnEgg(ModEntities.SPAWN_OWL);

    generated(ModItems.SILKWORM_EGG);
    generated(ModItems.SILK_THREAD);
    generated(ModItems.SPINDLE);
    generated(ModItems.SILK_COCOON);

    generated(ModItems.COOKED_BEETROOT);
    generated(ModItems.COOKED_CARROT);
    generated(ModItems.SLICED_CARROT);
  }
}
