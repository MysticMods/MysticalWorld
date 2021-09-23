package mysticmods.mysticalworld.items;

import mysticmods.mysticalworld.config.ConfigManager;
import mysticmods.mysticalworld.entity.SilkwormEntity;
import mysticmods.mysticalworld.init.ModEntities;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.stats.Stats;
import net.minecraft.tileentity.MobSpawnerTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.spawner.AbstractSpawner;

import java.util.Objects;

import net.minecraft.item.Item.Properties;

public class SilkwormEgg extends Item {
  public SilkwormEgg(Properties builder) {
    super(builder);
  }

  private ActionResultType use(ItemUseContext context) {
    World world = context.getLevel();
    if (world.isClientSide) {
      return ActionResultType.SUCCESS;
    } else {
      ItemStack itemstack = context.getItemInHand();
      BlockPos blockpos = context.getClickedPos();
      Direction direction = context.getClickedFace();
      BlockState blockstate = world.getBlockState(blockpos);
      Block block = blockstate.getBlock();
      if (block == Blocks.SPAWNER) {
        TileEntity tileentity = world.getBlockEntity(blockpos);
        if (tileentity instanceof MobSpawnerTileEntity) {
          AbstractSpawner abstractspawner = ((MobSpawnerTileEntity) tileentity).getSpawner();
          EntityType<SilkwormEntity> entitytype1 = ModEntities.SILKWORM.get();
          abstractspawner.setEntityId(entitytype1);
          tileentity.setChanged();
          world.sendBlockUpdated(blockpos, blockstate, blockstate, 3);
          itemstack.shrink(1);
          return ActionResultType.SUCCESS;
        }
      }

      BlockPos blockpos1;
      if (blockstate.getCollisionShape(world, blockpos).isEmpty()) {
        blockpos1 = blockpos;
      } else {
        blockpos1 = blockpos.relative(direction);
      }

      EntityType<SilkwormEntity> entitytype = ModEntities.SILKWORM.get();
      if (entitytype.spawn((ServerWorld) world, itemstack, context.getPlayer(), blockpos1, SpawnReason.SPAWN_EGG, true, !Objects.equals(blockpos, blockpos1) && direction == Direction.UP) != null) {
        itemstack.shrink(1);
      }

      return ActionResultType.SUCCESS;
    }
  }

  private ActionResult<ItemStack> rightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
    ItemStack itemstack = playerIn.getItemInHand(handIn);
    if (worldIn.isClientSide) {
      return new ActionResult<>(ActionResultType.PASS, itemstack);
    } else {
      RayTraceResult raytraceresult = getPlayerPOVHitResult(worldIn, playerIn, RayTraceContext.FluidMode.SOURCE_ONLY);
      if (raytraceresult.getType() != RayTraceResult.Type.BLOCK) {
        return new ActionResult<>(ActionResultType.PASS, itemstack);
      } else {
        BlockRayTraceResult blockraytraceresult = (BlockRayTraceResult) raytraceresult;
        BlockPos blockpos = blockraytraceresult.getBlockPos();
        if (!(worldIn.getBlockState(blockpos).getBlock() instanceof FlowingFluidBlock)) {
          return new ActionResult<>(ActionResultType.PASS, itemstack);
        } else if (worldIn.mayInteract(playerIn, blockpos) && playerIn.mayUseItemAt(blockpos, blockraytraceresult.getDirection(), itemstack)) {
          EntityType<SilkwormEntity> entitytype = ModEntities.SILKWORM.get();
          if (entitytype.spawn((ServerWorld) worldIn, itemstack, playerIn, blockpos, SpawnReason.SPAWN_EGG, false, false) == null) {
            return new ActionResult<>(ActionResultType.PASS, itemstack);
          } else {
            if (!playerIn.abilities.instabuild) {
              itemstack.shrink(1);
            }

            playerIn.awardStat(Stats.ITEM_USED.get(this));
            return new ActionResult<>(ActionResultType.SUCCESS, itemstack);
          }
        } else {
          return new ActionResult<>(ActionResultType.FAIL, itemstack);
        }
      }
    }
  }

  @Override
  public ActionResultType useOn(ItemUseContext context) {
    World world = context.getLevel();
    if (!world.isClientSide()) {
      if (world.getRandom().nextInt(ConfigManager.SILKWORM_CONFIG.getSuccessChance()) == 0) {
        return use(context);
      } else {
        if (context.getPlayer() == null || !context.getPlayer().abilities.instabuild) {
          context.getItemInHand().shrink(1);
        }
        return ActionResultType.SUCCESS;
      }
    } else {
      return use(context);
    }
  }

  @Override
  public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
    ItemStack itemstack = playerIn.getItemInHand(handIn);
    if (!worldIn.isClientSide()) {
      if (worldIn.getRandom().nextInt(ConfigManager.SILKWORM_CONFIG.getSuccessChance()) == 0) {
        return rightClick(worldIn, playerIn, handIn);
      } else {
        if (!playerIn.abilities.instabuild) {
          itemstack.shrink(1);
        }
        return new ActionResult<>(ActionResultType.SUCCESS, itemstack);
      }
    } else {
      return rightClick(worldIn, playerIn, handIn);
    }
  }
}
