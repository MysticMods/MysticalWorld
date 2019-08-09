package epicsquid.mysticalworld.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.state.IntegerProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class MudBlock extends Block {
	protected static final VoxelShape SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 14.0D, 16.0D);

	public static final IntegerProperty DRYNESS = IntegerProperty.create("dryness", 0, 5);

	private Block dryEquivalent;

	public MudBlock(Properties props) {
		super(props);
		this.setDefaultState(this.getDefaultState().with(DRYNESS, 5));
	}

	public Block getDryEquivalent() {
		return dryEquivalent;
	}

	public MudBlock setDryEquivalent(Block dryEquivalent) {
		this.dryEquivalent = dryEquivalent;
		return this;
	}

	@Override
	public void tick(BlockState state, World worldIn, BlockPos pos, Random random) {
		super.tick(state, worldIn, pos, random);

		int i = state.get(DRYNESS);
		if (i == 0) {
			worldIn.setBlockState(pos, getDryEquivalent().getDefaultState(), 2);
		} else {
			worldIn.setBlockState(pos, state.with(DRYNESS, i - 1), 2);
		}
	}

	public void onEntityCollision(World worldIn, BlockPos pos, BlockState state, Entity entityIn) {
		entityIn.setMotion(entityIn.getMotion().getX() * 0.4, entityIn.getMotion().getY(), entityIn.getMotion().getZ() * 0.4);
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return SHAPE;
	}
}