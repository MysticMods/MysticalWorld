package mysticmods.mysticalworld.items;

import mysticmods.mysticalworld.config.ConfigManager;
import mysticmods.mysticalworld.entity.SilkwormEntity;
import mysticmods.mysticalworld.init.deferred.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

import java.util.Objects;

public class SilkwormEgg extends Item {
  public SilkwormEgg(Properties builder) {
    super(builder);
  }

  private InteractionResult use(UseOnContext context) {
    Level world = context.getLevel();
    if (world.isClientSide) {
      return InteractionResult.SUCCESS;
    } else {
      ItemStack itemstack = context.getItemInHand();
      BlockPos blockpos = context.getClickedPos();
      Direction direction = context.getClickedFace();
      BlockState blockstate = world.getBlockState(blockpos);
      Block block = blockstate.getBlock();
      if (block == Blocks.SPAWNER) {
        return InteractionResult.FAIL;
      }

      BlockPos blockpos1;
      if (blockstate.getCollisionShape(world, blockpos).isEmpty()) {
        blockpos1 = blockpos;
      } else {
        blockpos1 = blockpos.relative(direction);
      }

      EntityType<SilkwormEntity> entitytype = ModEntities.SILKWORM.get();
      if (entitytype.spawn((ServerLevel) world, itemstack, context.getPlayer(), blockpos1, MobSpawnType.SPAWN_EGG, true, !Objects.equals(blockpos, blockpos1) && direction == Direction.UP) != null) {
        itemstack.shrink(1);
      }

      return InteractionResult.SUCCESS;
    }
  }

  private InteractionResultHolder<ItemStack> rightClick(Level worldIn, Player playerIn, InteractionHand handIn) {
    ItemStack itemstack = playerIn.getItemInHand(handIn);
    if (worldIn.isClientSide) {
      return new InteractionResultHolder<>(InteractionResult.PASS, itemstack);
    } else {
      HitResult raytraceresult = getPlayerPOVHitResult(worldIn, playerIn, ClipContext.Fluid.SOURCE_ONLY);
      if (raytraceresult.getType() != HitResult.Type.BLOCK) {
        return new InteractionResultHolder<>(InteractionResult.PASS, itemstack);
      } else {
        BlockHitResult blockraytraceresult = (BlockHitResult) raytraceresult;
        BlockPos blockpos = blockraytraceresult.getBlockPos();
        if (!(worldIn.getBlockState(blockpos).getBlock() instanceof LiquidBlock)) {
          return new InteractionResultHolder<>(InteractionResult.PASS, itemstack);
        } else if (worldIn.mayInteract(playerIn, blockpos) && playerIn.mayUseItemAt(blockpos, blockraytraceresult.getDirection(), itemstack)) {
          EntityType<SilkwormEntity> entitytype = ModEntities.SILKWORM.get();
          if (entitytype.spawn((ServerLevel) worldIn, itemstack, playerIn, blockpos, MobSpawnType.SPAWN_EGG, false, false) == null) {
            return new InteractionResultHolder<>(InteractionResult.PASS, itemstack);
          } else {
            if (!playerIn.isCreative()) {
              itemstack.shrink(1);
            }

            playerIn.awardStat(Stats.ITEM_USED.get(this));
            return new InteractionResultHolder<>(InteractionResult.SUCCESS, itemstack);
          }
        } else {
          return new InteractionResultHolder<>(InteractionResult.FAIL, itemstack);
        }
      }
    }
  }

  @Override
  public InteractionResult useOn(UseOnContext context) {
    Level world = context.getLevel();
    if (!world.isClientSide()) {
      if (world.getRandom().nextInt(ConfigManager.SILKWORM_CONFIG.getSuccessChance()) == 0) {
        return use(context);
      } else {
        if (context.getPlayer() == null || !context.getPlayer().isCreative()) {
          context.getItemInHand().shrink(1);
        }
        return InteractionResult.SUCCESS;
      }
    } else {
      return use(context);
    }
  }

  @Override
  public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
    ItemStack itemstack = playerIn.getItemInHand(handIn);
    if (!worldIn.isClientSide()) {
      if (worldIn.getRandom().nextInt(ConfigManager.SILKWORM_CONFIG.getSuccessChance()) == 0) {
        return rightClick(worldIn, playerIn, handIn);
      } else {
        if (!playerIn.isCreative()) {
          itemstack.shrink(1);
        }
        return new InteractionResultHolder<>(InteractionResult.SUCCESS, itemstack);
      }
    } else {
      return rightClick(worldIn, playerIn, handIn);
    }
  }
}
