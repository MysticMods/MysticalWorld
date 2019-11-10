package epicsquid.mysticalworld.data;

import epicsquid.mysticallib.data.DeferredBlockTagsProvider;
import epicsquid.mysticalworld.Tags;
import epicsquid.mysticalworld.init.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;

public class MWBlockTagsProvider extends DeferredBlockTagsProvider {
  public MWBlockTagsProvider(DataGenerator generatorIn) {
    super(generatorIn, "Mystical World Block Tags Provider");
  }

  @Override
  protected void registerTags() {
    createAndAppend(Tags.Blocks.SLABS, BlockTags.SLABS, ModBlocks.MUD_BLOCK_SLAB, ModBlocks.MUD_BRICK_SLAB);
    createAndAppend(Tags.Blocks.STAIRS, BlockTags.STAIRS, ModBlocks.MUD_BLOCK_STAIRS, ModBlocks.MUD_BRICK_STAIRS);
    createAndAppend(Tags.Blocks.FENCES, BlockTags.FENCES, ModBlocks.MUD_BLOCK_FENCE, ModBlocks.MUD_BRICK_FENCE);
    createAndAppend(Tags.Blocks.WALLS, BlockTags.WALLS, ModBlocks.MUD_BLOCK_WALL, ModBlocks.MUD_BRICK_WALL);
  }
}
