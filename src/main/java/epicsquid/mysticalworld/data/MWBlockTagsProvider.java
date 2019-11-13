package epicsquid.mysticalworld.data;

import epicsquid.mysticallib.data.DeferredBlockTagsProvider;
import epicsquid.mysticalworld.MWTags;
import epicsquid.mysticalworld.init.ModBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;

public class MWBlockTagsProvider extends DeferredBlockTagsProvider {
  public MWBlockTagsProvider(DataGenerator generatorIn) {
    super(generatorIn, "Mystical World Block Tags Provider");
  }

  @Override
  protected void registerTags() {
    createAndAppend(MWTags.Blocks.SLABS, BlockTags.SLABS, ModBlocks.MUD_BLOCK_SLAB, ModBlocks.MUD_BRICK_SLAB, ModBlocks.CHARRED_SLAB);
    createAndAppend(MWTags.Blocks.STAIRS, BlockTags.STAIRS, ModBlocks.MUD_BLOCK_STAIRS, ModBlocks.MUD_BRICK_STAIRS, ModBlocks.CHARRED_STAIRS);
    createAndAppend(MWTags.Blocks.FENCES, BlockTags.FENCES, ModBlocks.MUD_BLOCK_FENCE, ModBlocks.MUD_BRICK_FENCE, ModBlocks.CHARRED_FENCE);
    createAndAppend(MWTags.Blocks.WALLS, BlockTags.WALLS, ModBlocks.MUD_BLOCK_WALL, ModBlocks.MUD_BRICK_WALL, ModBlocks.CHARRED_WALL);

    createAndAppend(MWTags.Blocks.LOGS, BlockTags.LOGS, ModBlocks.CHARRED_LOG);
    createAndAppend(MWTags.Blocks.PLANKS, BlockTags.PLANKS, ModBlocks.CHARRED_PLANKS);

    createAndAppend(MWTags.Blocks.WOODEN_FENCES, BlockTags.WOODEN_FENCES, ModBlocks.CHARRED_FENCE);
    createAndAppend(MWTags.Blocks.WOODEN_SLABS, BlockTags.WOODEN_SLABS, ModBlocks.CHARRED_SLAB);
    createAndAppend(MWTags.Blocks.WOODEN_STAIRS, BlockTags.WOODEN_STAIRS, ModBlocks.CHARRED_STAIRS);

    createTag(MWTags.Blocks.PURPUR, () -> Blocks.PURPUR_BLOCK, () -> Blocks.PURPUR_PILLAR);
    createTag(MWTags.Blocks.NETHER_BRICKS, () -> Blocks.NETHER_BRICKS);
    createTag(MWTags.Blocks.RED_NETHER_BRICKS, () -> Blocks.RED_NETHER_BRICKS);
    createTag(MWTags.Blocks.TERRACOTTA, () -> Blocks.WHITE_TERRACOTTA, () -> Blocks.ORANGE_TERRACOTTA, () -> Blocks.MAGENTA_TERRACOTTA, () -> Blocks.LIGHT_BLUE_TERRACOTTA, () -> Blocks.YELLOW_TERRACOTTA, () -> Blocks.LIME_TERRACOTTA, () -> Blocks.PINK_TERRACOTTA, () -> Blocks.GRAY_TERRACOTTA, () -> Blocks.LIGHT_GRAY_TERRACOTTA, () -> Blocks.CYAN_TERRACOTTA, () -> Blocks.PURPLE_TERRACOTTA, () -> Blocks.BLUE_TERRACOTTA, () -> Blocks.BROWN_TERRACOTTA, () -> Blocks.GREEN_TERRACOTTA, () -> Blocks.RED_TERRACOTTA, () -> Blocks.BLACK_TERRACOTTA, () -> Blocks.TERRACOTTA);
  }
}
