package mysticmods.mysticalworld.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.PushReaction;
import noobanidus.libs.noobutil.block.BaseBlocks;

public class SoftObsidian {
  public static class SoftObsidianBlock extends Block {
    public SoftObsidianBlock(Properties p_i48440_1_) {
      super(p_i48440_1_);
    }

    @Override
    public PushReaction getPistonPushReaction(BlockState pState) {
      return PushReaction.BLOCK;
    }
  }

  public static class SoftObsidianSlabBlock extends SlabBlock {
    public SoftObsidianSlabBlock(Properties p_i48440_1_) {
      super(p_i48440_1_);
    }

    @Override
    public PushReaction getPistonPushReaction(BlockState pState) {
      return PushReaction.BLOCK;
    }
  }

  public static class SoftObsidianWallBlock extends WallBlock {
    public SoftObsidianWallBlock(Properties p_i48440_1_) {
      super(p_i48440_1_);
    }

    @Override
    public PushReaction getPistonPushReaction(BlockState pState) {
      return PushReaction.BLOCK;
    }
  }

  public static class SoftObsidianFenceBlock extends FenceBlock {

    public SoftObsidianFenceBlock(Properties p_i48399_1_) {
      super(p_i48399_1_);
    }

    @Override
    public PushReaction getPistonPushReaction(BlockState pState) {
      return PushReaction.BLOCK;
    }
  }

  public static class SoftObsidianWidePostBlock extends BaseBlocks.WidePostBlock {
    public SoftObsidianWidePostBlock(Properties properties) {
      super(properties);
    }

    @Override
    public PushReaction getPistonPushReaction(BlockState pState) {
      return PushReaction.BLOCK;
    }
  }

  public static class SoftObsidianNarrowPostBlock extends BaseBlocks.NarrowPostBlock {
    public SoftObsidianNarrowPostBlock(Properties properties) {
      super(properties);
    }

    @Override
    public PushReaction getPistonPushReaction(BlockState pState) {
      return PushReaction.BLOCK;
    }
  }

  public static class SoftObsidianButtonBlock extends BaseBlocks.StoneButtonBlock {
    public SoftObsidianButtonBlock(Properties properties) {
      super(properties);
    }

    @Override
    public PushReaction getPistonPushReaction(BlockState pState) {
      return PushReaction.BLOCK;
    }
  }

  public static class SoftObsidianPressurePlateBlock extends BaseBlocks.PressurePlateBlock {
    public SoftObsidianPressurePlateBlock(Sensitivity sensitivityIn, Properties propertiesIn) {
      super(sensitivityIn, propertiesIn);
    }

    @Override
    public PushReaction getPistonPushReaction(BlockState pState) {
      return PushReaction.BLOCK;
    }
  }
}
