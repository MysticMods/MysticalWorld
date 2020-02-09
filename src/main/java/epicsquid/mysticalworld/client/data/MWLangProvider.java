package epicsquid.mysticalworld.client.data;

import epicsquid.mysticallib.client.data.DeferredLanguageProvider;
import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.init.ModBlocks;
import epicsquid.mysticalworld.init.ModEntities;
import epicsquid.mysticalworld.init.ModItems;
import net.minecraft.data.DataGenerator;

public class MWLangProvider extends DeferredLanguageProvider {
  public MWLangProvider(DataGenerator gen) {
    super(gen, MysticalWorld.MODID);
  }

  @Override
  protected void addTranslations() {
    addBlock(ModBlocks.THATCH);
    addBlock(ModBlocks.WET_MUD_BRICK);
    addBlock(ModBlocks.WET_MUD_BLOCK);

    addBlock(ModBlocks.MUD_BLOCK);
    addBlock(ModBlocks.MUD_BLOCK_FENCE);
    addBlock(ModBlocks.MUD_BLOCK_SLAB);
    addBlock(ModBlocks.MUD_BLOCK_STAIRS);
    addBlock(ModBlocks.MUD_BLOCK_WALL);
    addBlock(ModBlocks.MUD_BLOCK_FENCE_GATE);

    addBlock(ModBlocks.MUD_BRICK);
    addBlock(ModBlocks.MUD_BRICK_FENCE);
    addBlock(ModBlocks.MUD_BRICK_SLAB);
    addBlock(ModBlocks.MUD_BRICK_STAIRS);
    addBlock(ModBlocks.MUD_BRICK_WALL);
    addBlock(ModBlocks.MUD_BRICK_FENCE_GATE);

    addBlock(ModBlocks.MUD_BLOCK_WIDE_POST);
    addBlock(ModBlocks.MUD_BLOCK_SMALL_POST);
    addBlock(ModBlocks.MUD_BRICK_WIDE_POST);
    addBlock(ModBlocks.MUD_BRICK_SMALL_POST);

    addBlock(ModBlocks.CHARRED_LOG);
    addBlock(ModBlocks.CHARRED_PLANKS);
    addBlock(ModBlocks.CHARRED_FENCE);
    addBlock(ModBlocks.CHARRED_SLAB);
    addBlock(ModBlocks.CHARRED_STAIRS);
    addBlock(ModBlocks.CHARRED_WALL);
    addBlock(ModBlocks.CHARRED_FENCE_GATE);
    addBlock(ModBlocks.CHARRED_WIDE_POST);
    addBlock(ModBlocks.CHARRED_SMALL_POST);

    addBlock(ModBlocks.AMETHYST_ORE);
    addBlock(ModBlocks.COPPER_ORE);
    addBlock(ModBlocks.LEAD_ORE);
    addBlock(ModBlocks.QUICKSILVER_ORE);
    addBlock(ModBlocks.SILVER_ORE);
    addBlock(ModBlocks.TIN_ORE);

    addBlock(ModBlocks.AMETHYST_BLOCK);
    addBlock(ModBlocks.AMETHYST_STAIRS);
    addBlock(ModBlocks.AMETHYST_SLAB);
    addBlock(ModBlocks.AMETHYST_WALL);
    addBlock(ModBlocks.AMETHYST_WIDE_POST);
    addBlock(ModBlocks.AMETHYST_SMALL_POST);

    addBlock(ModBlocks.COPPER_BLOCK);
    addBlock(ModBlocks.COPPER_STAIRS);
    addBlock(ModBlocks.COPPER_SLAB);
    addBlock(ModBlocks.COPPER_WALL);
    addBlock(ModBlocks.COPPER_WIDE_POST);
    addBlock(ModBlocks.COPPER_SMALL_POST);

    addBlock(ModBlocks.LEAD_BLOCK);
    addBlock(ModBlocks.LEAD_STAIRS);
    addBlock(ModBlocks.LEAD_SLAB);
    addBlock(ModBlocks.LEAD_WALL);
    addBlock(ModBlocks.LEAD_WIDE_POST);
    addBlock(ModBlocks.LEAD_SMALL_POST);

    addBlock(ModBlocks.QUICKSILVER_BLOCK);
    addBlock(ModBlocks.QUICKSILVER_STAIRS);
    addBlock(ModBlocks.QUICKSILVER_SLAB);
    addBlock(ModBlocks.QUICKSILVER_WALL);
    addBlock(ModBlocks.QUICKSILVER_WIDE_POST);
    addBlock(ModBlocks.QUICKSILVER_SMALL_POST);

    addBlock(ModBlocks.SILVER_BLOCK);
    addBlock(ModBlocks.SILVER_STAIRS);
    addBlock(ModBlocks.SILVER_SLAB);
    addBlock(ModBlocks.SILVER_WALL);
    addBlock(ModBlocks.SILVER_WIDE_POST);
    addBlock(ModBlocks.SILVER_SMALL_POST);

    addBlock(ModBlocks.TIN_BLOCK);
    addBlock(ModBlocks.TIN_STAIRS);
    addBlock(ModBlocks.TIN_SLAB);
    addBlock(ModBlocks.TIN_WALL);
    addBlock(ModBlocks.TIN_WIDE_POST);
    addBlock(ModBlocks.TIN_SMALL_POST);
    addBlock(ModBlocks.TERRACOTTA_BRICK_WIDE_POST);
    addBlock(ModBlocks.TERRACOTTA_BRICK_SMALL_POST);
    addBlock(ModBlocks.IRON_BRICK_WIDE_POST);
    addBlock(ModBlocks.IRON_BRICK_SMALL_POST);

    addBlock(ModBlocks.TERRACOTTA_BRICK);
    addBlock(ModBlocks.TERRACOTTA_BRICK_FENCE);
    addBlock(ModBlocks.TERRACOTTA_BRICK_SLAB);
    addBlock(ModBlocks.TERRACOTTA_BRICK_STAIRS);
    addBlock(ModBlocks.TERRACOTTA_BRICK_WALL);
    addBlock(ModBlocks.TERRACOTTA_BRICK_FENCE_GATE);

    addBlock(ModBlocks.IRON_BRICK);
    addBlock(ModBlocks.IRON_BRICK_SLAB);
    addBlock(ModBlocks.IRON_BRICK_STAIRS);
    addBlock(ModBlocks.IRON_BRICK_WALL);


    addItem(ModItems.ROTTEN_APPLE);

    addItem(ModItems.CARAPACE);
    addItem(ModItems.PELT);
    addItem(ModItems.ANTLERS);
    addItem(ModItems.INK_BOTTLE);
    addItem(ModItems.YOUNG_PEARL);
    addItem(ModItems.VENISON);
    addItem(ModItems.COOKED_VENISON);
    addItem(ModItems.AUBERGINE_SEEDS);
    addItem(ModItems.AUBERGINE);
    addItem(ModItems.COOKED_AUBERGINE);
    addItem(ModItems.STUFFED_AUBERGINE);
    addItem(ModItems.RAW_SQUID);
    addItem(ModItems.COOKED_SQUID);
    addItem(ModItems.EPIC_SQUID);

    addItem(ModItems.AMETHYST_GEM);
    addItem(ModItems.COPPER_INGOT);
    addItem(ModItems.LEAD_INGOT);
    addItem(ModItems.QUICKSILVER_INGOT);
    addItem(ModItems.SILVER_INGOT);
    addItem(ModItems.TIN_INGOT);
    addItem(ModItems.COPPER_NUGGET);
    addItem(ModItems.LEAD_NUGGET);
    addItem(ModItems.QUICKSILVER_NUGGET);
    addItem(ModItems.SILVER_NUGGET);
    addItem(ModItems.TIN_NUGGET);
    addItem(ModItems.COPPER_DUST);
    addItem(ModItems.LEAD_DUST);
    addItem(ModItems.QUICKSILVER_DUST);
    addItem(ModItems.SILVER_DUST);
    addItem(ModItems.TIN_DUST);
    addItem(ModItems.GOLD_DUST);
    addItem(ModItems.IRON_DUST);
    addItem(ModItems.AMETHYST_HELMET);
    addItem(ModItems.AMETHYST_CHESTPLATE);
    addItem(ModItems.AMETHYST_LEGGINGS);
    addItem(ModItems.AMETHYST_BOOTS);
    addItem(ModItems.COPPER_HELMET);
    addItem(ModItems.COPPER_CHESTPLATE);
    addItem(ModItems.COPPER_LEGGINGS);
    addItem(ModItems.COPPER_BOOTS);
    addItem(ModItems.LEAD_HELMET);
    addItem(ModItems.LEAD_CHESTPLATE);
    addItem(ModItems.LEAD_LEGGINGS);
    addItem(ModItems.LEAD_BOOTS);
    addItem(ModItems.QUICKSILVER_HELMET);
    addItem(ModItems.QUICKSILVER_CHESTPLATE);
    addItem(ModItems.QUICKSILVER_LEGGINGS);
    addItem(ModItems.QUICKSILVER_BOOTS);
    addItem(ModItems.SILVER_HELMET);
    addItem(ModItems.SILVER_CHESTPLATE);
    addItem(ModItems.SILVER_LEGGINGS);
    addItem(ModItems.SILVER_BOOTS);
    addItem(ModItems.TIN_HELMET);
    addItem(ModItems.TIN_CHESTPLATE);
    addItem(ModItems.TIN_LEGGINGS);
    addItem(ModItems.TIN_BOOTS);

    addItem(ModItems.AMETHYST_AXE);
    addItem(ModItems.AMETHYST_HOE);
    addItem(ModItems.AMETHYST_KNIFE);
    addItem(ModItems.AMETHYST_PICKAXE);
    addItem(ModItems.AMETHYST_SHOVEL);
    addItem(ModItems.AMETHYST_SWORD);
    addItem(ModItems.CACTUS_AXE);
    addItem(ModItems.CACTUS_HOE);
    addItem(ModItems.CACTUS_KNIFE);
    addItem(ModItems.CACTUS_PICKAXE);
    addItem(ModItems.CACTUS_SHOVEL);
    addItem(ModItems.CACTUS_SWORD);
    addItem(ModItems.COPPER_AXE);
    addItem(ModItems.COPPER_HOE);
    addItem(ModItems.COPPER_KNIFE);
    addItem(ModItems.COPPER_PICKAXE);
    addItem(ModItems.COPPER_SHOVEL);
    addItem(ModItems.COPPER_SWORD);
    addItem(ModItems.LEAD_AXE);
    addItem(ModItems.LEAD_HOE);
    addItem(ModItems.LEAD_KNIFE);
    addItem(ModItems.LEAD_PICKAXE);
    addItem(ModItems.LEAD_SHOVEL);
    addItem(ModItems.LEAD_SWORD);
    addItem(ModItems.QUICKSILVER_AXE);
    addItem(ModItems.QUICKSILVER_HOE);
    addItem(ModItems.QUICKSILVER_KNIFE);
    addItem(ModItems.QUICKSILVER_PICKAXE);
    addItem(ModItems.QUICKSILVER_SHOVEL);
    addItem(ModItems.QUICKSILVER_SWORD);
    addItem(ModItems.SILVER_AXE);
    addItem(ModItems.SILVER_HOE);
    addItem(ModItems.SILVER_KNIFE);
    addItem(ModItems.SILVER_PICKAXE);
    addItem(ModItems.SILVER_SHOVEL);
    addItem(ModItems.SILVER_SWORD);
    addItem(ModItems.TIN_AXE);
    addItem(ModItems.TIN_HOE);
    addItem(ModItems.TIN_KNIFE);
    addItem(ModItems.TIN_PICKAXE);
    addItem(ModItems.TIN_SHOVEL);
    addItem(ModItems.TIN_SWORD);
    addItem(ModItems.STONE_KNIFE);
    addItem(ModItems.WOODEN_KNIFE);
    addItem(ModItems.DIAMOND_KNIFE);
    addItem(ModItems.GOLD_KNIFE);
    addItem(ModItems.IRON_KNIFE);
    addItem(ModItems.NAUTILUS_HORN);
    addItem(ModItems.GLISTERING_HORN);

    addItem(ModItems.AMETHYST_SPEAR);
    addItem(ModItems.CACTUS_SPEAR);
    addItem(ModItems.COPPER_SPEAR);
    addItem(ModItems.LEAD_SPEAR);
    addItem(ModItems.QUICKSILVER_SPEAR);
    addItem(ModItems.SILVER_SPEAR);
    addItem(ModItems.TIN_SPEAR);
    addItem(ModItems.STONE_SPEAR);
    addItem(ModItems.WOODEN_SPEAR);
    addItem(ModItems.DIAMOND_SPEAR);
    addItem(ModItems.GOLD_SPEAR);
    addItem(ModItems.IRON_SPEAR);

    addItem(ModEntities.SPAWN_BEETLE);
    addItem(ModEntities.SPAWN_DEER);
    addItem(ModEntities.SPAWN_FROG);
    addItem(ModEntities.SPAWN_SPROUT);
    addItem(ModEntities.SPAWN_SILVER_FOX);
    addItem(ModEntities.SPAWN_ENDERMINI);
    addItem(ModEntities.SPAWN_LAVA_CAT);
    addItem(ModEntities.SPAWN_SILKWORM);
    addItem(ModEntities.SPAWN_OWL);

    addItem(ModItems.APPLE_CORDIAL);
    addItem(ModItems.CACTUS_SYRUP);
    addItem(ModItems.DANDELION_CORDIAL);
    addItem(ModItems.LILAC_CORDIAL);
    addItem(ModItems.PEONY_CORDIAL);
    addItem(ModItems.ROSE_CORDIAL);
    addItem(ModItems.VINEGAR);
    addItem(ModItems.VEGETABLE_JUICE);
    addItem(ModItems.AUBERGINE_SALAD);
    addItem(ModItems.BEETROOT_SALAD);
    addItem(ModItems.CACTUS_DANDELION_SALAD);
    addItem(ModItems.DANDELION_CORNFLOWER_SALAD);
    addItem(ModItems.STEWED_EGGPLANT);

    addItem(ModItems.SILKWORM_EGG);
    addItem(ModItems.SILK_THREAD);
    addItem(ModItems.SPINDLE);
    addItem(ModItems.SILK_COCOON);

    addItem(ModItems.COOKED_BEETROOT);
    addItem(ModItems.COOKED_CARROT);
    addItem(ModItems.SLICED_CARROT);

    addItem(ModItems.ENCYCLOPEDIA);

    addEntityType(ModEntities.BEETLE);
    addEntityType(ModEntities.DEER);
    addEntityType(ModEntities.FROG);
    addEntityType(ModEntities.SILVER_FOX);
    addEntityType(ModEntities.SPROUT);
    addEntityType(ModEntities.ENDERMINI);
    addEntityType(ModEntities.OWL);
    addEntityType(ModEntities.SILKWORM);
    add("mysticalworld.entity.lava_cat", "Lava Cat");
    add("mysticalworld.entity.obsidian_cat", "Obsidian Cat");

    addItemGroup(MysticalWorld.ITEM_GROUP, "Mystical World");
    addItemGroup(MysticalWorld.METAL_ITEM_GROUP, "Mystical Metals");

    add("message.dandelion_cordial", "You feel well-rested!");
    add("message.squid.cooldown", "Give it time to produce more ink!");
    add("attribute.name.generic.reachDistance", "Reach");
    add("attribute.name.mysticalworld.blessed", "Blessed");
    add("attribute.name.mysticalworld.smite", "Smite");
    add("attribute.name.mysticalworld.serendipity", "Serendipity");
    add("attribute.name.forge.swimSpeed", "Swim Speed");
    add("advancement.mysticalworld.root", "Mystical World");
    add("advancement.mysticalworld.root.desc", "There's magic everywhere!");
    add("advancement.mysticalworld.amethyst", "Sobriety Test");
    add("advancement.mysticalworld.amethyst.desc", "Find a gem more divine than diamond!");
    add("advancement.mysticalworld.aubergine", "That's No Carrot...");
    add("advancement.mysticalworld.aubergine.desc", "Once, purple carrots were all the rage. But this is an aubergine.");
    add("advancement.mysticalworld.epic_squid", "Epic Squid!");
    add("advancement.mysticalworld.epic_squid.desc", "Partake in some of the delicious, purple-y goodness of Epic Squid.");
    add("message.shoulder.occupied", "Your shoulders are otherwise occupied!");
  }
}
