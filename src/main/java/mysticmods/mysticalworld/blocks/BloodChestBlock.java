/*package mysticmods.mysticalworld.blocks;

import it.unimi.dsi.fastutil.floats.Float2FloatFunction;
import net.minecraft.block.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.piglin.PiglinTasks;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.stats.Stat;
import net.minecraft.stats.Stats;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.IChestLid;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMerger;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;

@SuppressWarnings({"deprecation", "NullableProblems", "WeakerAccess", "unused"}) // TODO: remove unused
public class BloodChestBlock extends Block implements IWaterLoggable {
  public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
  public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
  protected static final VoxelShape SHAPE_SINGLE = Block.makeCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 14.0D, 15.0D);

  public BloodChestBlock(AbstractBlock.Properties builder) {
    super(builder);
    this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH).with(WATERLOGGED, false));
  }

  @Override
  public BlockRenderType getRenderType(BlockState state) {
    return BlockRenderType.ENTITYBLOCK_ANIMATED;
  }

  @Override
  public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
    if (stateIn.get(WATERLOGGED)) {
      worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
    }

    return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
  }

  @Override
  public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
    return SHAPE_SINGLE;
  }

  @Override
  public BlockState getStateForPlacement(BlockItemUseContext context) {
    Direction direction = context.getPlacementHorizontalFacing().getOpposite();
    FluidState fluidstate = context.getWorld().getFluidState(context.getPos());
    return this.getDefaultState().with(FACING, direction).with(WATERLOGGED, fluidstate.getFluid() == Fluids.WATER);
  }

  @Override
  public FluidState getFluidState(BlockState state) {
    return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
  }

  @Override
  public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
    if (stack.hasDisplayName()) {
      TileEntity tileentity = worldIn.getTileEntity(pos);
      if (tileentity instanceof ChestTileEntity) {
        ((ChestTileEntity) tileentity).setCustomName(stack.getDisplayName());
      }
    }
  }

  @Override
  public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
    if (!state.isIn(newState.getBlock())) {
      TileEntity tileentity = worldIn.getTileEntity(pos);
      if (tileentity instanceof IInventory) {
        InventoryHelper.dropInventoryItems(worldIn, pos, (IInventory) tileentity);
        worldIn.updateComparatorOutputLevel(pos, this);
      }

      super.onReplaced(state, worldIn, pos, newState, isMoving);
    }
  }

  @Override
  public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
    if (worldIn.isRemote) {
      return ActionResultType.SUCCESS;
    } else {
      INamedContainerProvider inamedcontainerprovider = this.getContainer(state, worldIn, pos);
      if (inamedcontainerprovider != null) {
        player.openContainer(inamedcontainerprovider);
        player.addStat(this.getOpenStat());
        PiglinTasks.angerNearbyPiglins(player, true);
      }

      return ActionResultType.CONSUME;
    }
  }

  protected Stat<ResourceLocation> getOpenStat() {
    return Stats.CUSTOM.get(Stats.OPEN_CHEST);
  }

  @OnlyIn(Dist.CLIENT)
  public static TileEntityMerger.ICallback<ChestTileEntity, Float2FloatFunction> getLidRotationCallback(final IChestLid lid) {
    return new TileEntityMerger.ICallback<ChestTileEntity, Float2FloatFunction>() {
      @Override
      public Float2FloatFunction acceptDouble(ChestTileEntity p_225539_1_, ChestTileEntity p_225539_2_) {
        return (angle) -> Math.max(p_225539_1_.getLidAngle(angle), p_225539_2_.getLidAngle(angle));
      }

      @Override
      public Float2FloatFunction acceptSingle(ChestTileEntity p_225538_1_) {
        return p_225538_1_::getLidAngle;
      }

      @Override
      public Float2FloatFunction acceptNone() {
        return lid::getLidAngle;
      }
    };
  }

  @Override
  public TileEntity createTileEntity(BlockState state, IBlockReader worldIn) {
    return new ChestTileEntity();
  }

  @Override
  public boolean hasTileEntity(BlockState state) {
    return true;
  }

  public static boolean isBlocked(IWorld world, BlockPos pos) {
    return isBelowSolidBlock(world, pos) || isCatSittingOn(world, pos);
  }

  private static boolean isBelowSolidBlock(IBlockReader reader, BlockPos worldIn) {
    BlockPos blockpos = worldIn.up();
    return reader.getBlockState(blockpos).isNormalCube(reader, blockpos);
  }

  private static boolean isCatSittingOn(IWorld world, BlockPos pos) {
    List<CatEntity> list = world.getEntitiesWithinAABB(CatEntity.class, new AxisAlignedBB((double) pos.getX(), (double) (pos.getY() + 1), (double) pos.getZ(), (double) (pos.getX() + 1), (double) (pos.getY() + 2), (double) (pos.getZ() + 1)));
    if (!list.isEmpty()) {
      for (CatEntity catentity : list) {
        if (catentity.isEntitySleeping()) {
          return true;
        }
      }
    }

    return false;
  }

  @Override
  public boolean hasComparatorInputOverride(BlockState state) {
    return true;
  }

  @Override
  public int getComparatorInputOverride(BlockState blockState, World worldIn, BlockPos pos) {
    // TODO
    return 0;
  }

  @Override
  public BlockState rotate(BlockState state, Rotation rot) {
    return state.with(FACING, rot.rotate(state.get(FACING)));
  }

  @Override
  public BlockState mirror(BlockState state, Mirror mirrorIn) {
    return state.rotate(mirrorIn.toRotation(state.get(FACING)));
  }

  @Override
  protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
    builder.add(FACING, WATERLOGGED);
  }

  @Override
  public boolean allowsMovement(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type) {
    return false;
  }

  @Override
  public boolean eventReceived(BlockState state, World worldIn, BlockPos pos, int id, int param) {
    super.eventReceived(state, worldIn, pos, id, param);
    TileEntity tileentity = worldIn.getTileEntity(pos);
    return tileentity != null && tileentity.receiveClientEvent(id, param);
  }
}*/
