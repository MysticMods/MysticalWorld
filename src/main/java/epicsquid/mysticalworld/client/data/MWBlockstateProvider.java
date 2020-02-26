package epicsquid.mysticalworld.client.data;

import epicsquid.mysticallib.client.data.DeferredBlockStateProvider;
import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.init.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.client.model.generators.ModelFile;

public class MWBlockstateProvider extends DeferredBlockStateProvider {

  public MWBlockstateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
    super("Mystical World Blockstate and Block Model provider", gen, MysticalWorld.MODID, exFileHelper);
  }

  @Override
  protected void registerStatesAndModels() {
    simpleBlock(ModBlocks.AMETHYST_ORE);
    simpleBlock(ModBlocks.COPPER_ORE);
    simpleBlock(ModBlocks.LEAD_ORE);
    simpleBlock(ModBlocks.QUICKSILVER_ORE);
    simpleBlock(ModBlocks.SILVER_ORE);
    simpleBlock(ModBlocks.TIN_ORE);

    simpleBlock(ModBlocks.WET_MUD_BLOCK, new ModelFile.ExistingModelFile(new ResourceLocation(MysticalWorld.MODID, "block/wet_mud_block"), existingFileHelper));
    simpleBlock(ModBlocks.WET_MUD_BRICK);

    simpleBlock(ModBlocks.TERRACOTTA_BRICK);
    stairsBlock(ModBlocks.TERRACOTTA_BRICK_STAIRS, "terracotta_brick");
    slabBlock(ModBlocks.TERRACOTTA_BRICK_SLAB, ModBlocks.TERRACOTTA_BRICK, "terracotta_brick");
    wallBlock(ModBlocks.TERRACOTTA_BRICK_WALL, "terracotta_brick");
    fenceBlock(ModBlocks.TERRACOTTA_BRICK_FENCE, "terracotta_brick");
    fenceGateBlock(ModBlocks.TERRACOTTA_BRICK_FENCE_GATE, "terracotta_brick");
    widePostBlock(ModBlocks.TERRACOTTA_BRICK_WIDE_POST, "terracotta_brick");
    narrowPostBlock(ModBlocks.TERRACOTTA_BRICK_SMALL_POST, "terracotta_brick");

    simpleBlock(ModBlocks.IRON_BRICK);
    stairsBlock(ModBlocks.IRON_BRICK_STAIRS, "iron_brick");
    slabBlock(ModBlocks.IRON_BRICK_SLAB, ModBlocks.IRON_BRICK, "iron_brick");
    wallBlock(ModBlocks.IRON_BRICK_WALL, "iron_brick");
    widePostBlock(ModBlocks.IRON_BRICK_WIDE_POST, "iron_brick");
    narrowPostBlock(ModBlocks.IRON_BRICK_SMALL_POST, "iron_brick");

    simpleBlock(ModBlocks.SOFT_STONE);
    stairsBlock(ModBlocks.SOFT_STONE_STAIRS, "soft_stone");
    slabBlock(ModBlocks.SOFT_STONE_SLAB, ModBlocks.SOFT_STONE, "soft_stone");
    wallBlock(ModBlocks.SOFT_STONE_WALL, "soft_stone");
    widePostBlock(ModBlocks.SOFT_STONE_WIDE_POST, "soft_stone");
    narrowPostBlock(ModBlocks.SOFT_STONE_SMALL_POST, "soft_stone");

    simpleBlock(ModBlocks.CRACKED_STONE);
    stairsBlock(ModBlocks.CRACKED_STONE_STAIRS, "cracked_stone");
    slabBlock(ModBlocks.CRACKED_STONE_SLAB, ModBlocks.CRACKED_STONE, "cracked_stone");
    wallBlock(ModBlocks.CRACKED_STONE_WALL, "cracked_stone");
    widePostBlock(ModBlocks.CRACKED_STONE_WIDE_POST, "cracked_stone");
    narrowPostBlock(ModBlocks.CRACKED_STONE_SMALL_POST, "cracked_stone");

    simpleBlock(ModBlocks.WEATHERED_STONE);
    stairsBlock(ModBlocks.WEATHERED_STONE_STAIRS, "weathered_stone");
    slabBlock(ModBlocks.WEATHERED_STONE_SLAB, ModBlocks.WEATHERED_STONE, "weathered_stone");
    wallBlock(ModBlocks.WEATHERED_STONE_WALL, "weathered_stone");
    widePostBlock(ModBlocks.WEATHERED_STONE_WIDE_POST, "weathered_stone");
    narrowPostBlock(ModBlocks.WEATHERED_STONE_SMALL_POST, "weathered_stone");

    simpleBlock(ModBlocks.WEATHERED_OBSIDIAN);
    stairsBlock(ModBlocks.WEATHERED_OBSIDIAN_STAIRS, "weathered_obsidian");
    slabBlock(ModBlocks.WEATHERED_OBSIDIAN_SLAB, ModBlocks.WEATHERED_OBSIDIAN, "weathered_obsidian");
    wallBlock(ModBlocks.WEATHERED_OBSIDIAN_WALL, "weathered_obsidian");
    widePostBlock(ModBlocks.WEATHERED_OBSIDIAN_WIDE_POST, "weathered_obsidian");
    narrowPostBlock(ModBlocks.WEATHERED_OBSIDIAN_SMALL_POST, "weathered_obsidian");

    simpleBlock(ModBlocks.SMOOTH_OBSIDIAN);
    stairsBlock(ModBlocks.SMOOTH_OBSIDIAN_STAIRS, "smooth_obsidian");
    slabBlock(ModBlocks.SMOOTH_OBSIDIAN_SLAB, ModBlocks.SMOOTH_OBSIDIAN, "smooth_obsidian");
    wallBlock(ModBlocks.SMOOTH_OBSIDIAN_WALL, "smooth_obsidian");
    widePostBlock(ModBlocks.SMOOTH_OBSIDIAN_WIDE_POST, "smooth_obsidian");
    narrowPostBlock(ModBlocks.SMOOTH_OBSIDIAN_SMALL_POST, "smooth_obsidian");

    simpleBlock(ModBlocks.MUD_BLOCK);
    stairsBlock(ModBlocks.MUD_BLOCK_STAIRS, "mud_block");
    slabBlock(ModBlocks.MUD_BLOCK_SLAB, ModBlocks.MUD_BLOCK, "mud_block");
    wallBlock(ModBlocks.MUD_BLOCK_WALL, "mud_block");
    fenceBlock(ModBlocks.MUD_BLOCK_FENCE, "mud_block");
    fenceGateBlock(ModBlocks.MUD_BLOCK_FENCE_GATE, "mud_block");
    widePostBlock(ModBlocks.MUD_BLOCK_WIDE_POST, "mud_block");
    narrowPostBlock(ModBlocks.MUD_BLOCK_SMALL_POST, "mud_block");

    simpleBlock(ModBlocks.MUD_BRICK);
    stairsBlock(ModBlocks.MUD_BRICK_STAIRS, "mud_brick");
    slabBlock(ModBlocks.MUD_BRICK_SLAB, ModBlocks.MUD_BRICK, "mud_brick");
    wallBlock(ModBlocks.MUD_BRICK_WALL, "mud_brick");
    fenceBlock(ModBlocks.MUD_BRICK_FENCE, "mud_brick");
    fenceGateBlock(ModBlocks.MUD_BRICK_FENCE_GATE, "mud_brick");
    widePostBlock(ModBlocks.MUD_BRICK_WIDE_POST, "mud_brick");
    narrowPostBlock(ModBlocks.MUD_BRICK_SMALL_POST, "mud_brick");

    simpleBlock(ModBlocks.CHARRED_PLANKS);
    logBlock(ModBlocks.CHARRED_LOG);
    stairsBlock(ModBlocks.CHARRED_STAIRS, "charred_planks");
    slabBlock(ModBlocks.CHARRED_SLAB, ModBlocks.CHARRED_PLANKS, "charred_planks");
    wallBlock(ModBlocks.CHARRED_WALL, "charred_planks");
    fenceBlock(ModBlocks.CHARRED_FENCE, "charred_planks");
    fenceGateBlock(ModBlocks.CHARRED_FENCE_GATE, "charred_planks");
    widePostBlock(ModBlocks.CHARRED_WIDE_POST, "charred_planks");
    narrowPostBlock(ModBlocks.CHARRED_SMALL_POST, "charred_planks");

    simpleBlock(ModBlocks.AMETHYST_BLOCK);
    stairsBlock(ModBlocks.AMETHYST_STAIRS, "amethyst_block");
    slabBlock(ModBlocks.AMETHYST_SLAB, ModBlocks.AMETHYST_BLOCK, "amethyst_block");
    wallBlock(ModBlocks.AMETHYST_WALL, "amethyst_block");
    widePostBlock(ModBlocks.AMETHYST_WIDE_POST, "amethyst_block");
    narrowPostBlock(ModBlocks.AMETHYST_SMALL_POST, "amethyst_block");

    simpleBlock(ModBlocks.COPPER_BLOCK);
    stairsBlock(ModBlocks.COPPER_STAIRS, "copper_block");
    slabBlock(ModBlocks.COPPER_SLAB, ModBlocks.COPPER_BLOCK, "copper_block");
    wallBlock(ModBlocks.COPPER_WALL, "copper_block");
    widePostBlock(ModBlocks.COPPER_WIDE_POST, "copper_block");
    narrowPostBlock(ModBlocks.COPPER_SMALL_POST, "copper_block");

    simpleBlock(ModBlocks.LEAD_BLOCK);
    stairsBlock(ModBlocks.LEAD_STAIRS, "lead_block");
    slabBlock(ModBlocks.LEAD_SLAB, ModBlocks.LEAD_BLOCK, "lead_block");
    wallBlock(ModBlocks.LEAD_WALL, "lead_block");
    widePostBlock(ModBlocks.LEAD_WIDE_POST, "lead_block");
    narrowPostBlock(ModBlocks.LEAD_SMALL_POST, "lead_block");

    simpleBlock(ModBlocks.QUICKSILVER_BLOCK);
    stairsBlock(ModBlocks.QUICKSILVER_STAIRS, "quicksilver_block");
    slabBlock(ModBlocks.QUICKSILVER_SLAB, ModBlocks.QUICKSILVER_BLOCK, "quicksilver_block");
    wallBlock(ModBlocks.QUICKSILVER_WALL, "quicksilver_block");
    widePostBlock(ModBlocks.QUICKSILVER_WIDE_POST, "quicksilver_block");
    narrowPostBlock(ModBlocks.QUICKSILVER_SMALL_POST, "quicksilver_block");

    simpleBlock(ModBlocks.SILVER_BLOCK);
    stairsBlock(ModBlocks.SILVER_STAIRS, "silver_block");
    slabBlock(ModBlocks.SILVER_SLAB, ModBlocks.SILVER_BLOCK, "silver_block");
    wallBlock(ModBlocks.SILVER_WALL, "silver_block");
    widePostBlock(ModBlocks.SILVER_WIDE_POST, "silver_block");
    narrowPostBlock(ModBlocks.SILVER_SMALL_POST, "silver_block");

    simpleBlock(ModBlocks.TIN_BLOCK);
    stairsBlock(ModBlocks.TIN_STAIRS, "tin_block");
    slabBlock(ModBlocks.TIN_SLAB, ModBlocks.TIN_BLOCK, "tin_block");
    wallBlock(ModBlocks.TIN_WALL, "tin_block");
    widePostBlock(ModBlocks.TIN_WIDE_POST, "tin_block");
    narrowPostBlock(ModBlocks.TIN_SMALL_POST, "tin_block");
  }
}
