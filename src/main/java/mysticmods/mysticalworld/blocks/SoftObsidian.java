package mysticmods.mysticalworld.blocks;

import net.minecraft.block.*;
import net.minecraft.block.material.PushReaction;
import noobanidus.libs.noobutil.block.BaseBlocks;

public class SoftObsidian {
  public static class SoftObsidianBlock extends Block {
    public SoftObsidianBlock(Properties p_i48440_1_) {
      super(p_i48440_1_);
    }

    @Override
    public PushReaction getPushReaction(BlockState p_149656_1_) {
      return PushReaction.BLOCK;
    }
  }

  public static class SoftObsidianSlabBlock extends SlabBlock {
    public SoftObsidianSlabBlock(Properties p_i48440_1_) {
      super(p_i48440_1_);
    }

    @Override
    public PushReaction getPushReaction(BlockState p_149656_1_) {
      return PushReaction.BLOCK;
    }
  }

  public static class SoftObsidianWallBlock extends WallBlock {
    public SoftObsidianWallBlock(Properties p_i48440_1_) {
      super(p_i48440_1_);
    }

    @Override
    public PushReaction getPushReaction(BlockState p_149656_1_) {
      return PushReaction.BLOCK;
    }
  }

  public static class SoftObsidianFenceBlock extends FenceBlock {

    public SoftObsidianFenceBlock(Properties p_i48399_1_) {
      super(p_i48399_1_);
    }

    @Override
    public PushReaction getPushReaction(BlockState p_149656_1_) {
      return PushReaction.BLOCK;
    }
  }

  public static class SoftObsidianWidePostBlock extends BaseBlocks.WidePostBlock {
    public SoftObsidianWidePostBlock(Properties properties) {
      super(properties);
    }

    @Override
    public PushReaction getPushReaction(BlockState p_149656_1_) {
      return PushReaction.BLOCK;
    }
  }

  public static class SoftObsidianNarrowPostBlock extends BaseBlocks.NarrowPostBlock {
    public SoftObsidianNarrowPostBlock(Properties properties) {
      super(properties);
    }

    @Override
    public PushReaction getPushReaction(BlockState p_149656_1_) {
      return PushReaction.BLOCK;
    }
  }
}
