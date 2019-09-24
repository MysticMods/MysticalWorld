package epicsquid.mysticalworld.item;

import epicsquid.mysticallib.item.ItemBase;
import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.config.ConfigManager;
import epicsquid.mysticalworld.entity.EntitySilkworm;
import net.minecraft.block.BlockLiquid;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import java.util.List;

public class ItemSilkwormEgg extends ItemBase {
  private static ResourceLocation ENTITY_ID = new ResourceLocation(MysticalWorld.MODID, "entity_silkworm");

  public ItemSilkwormEgg(String name) {
    super(name);
  }

  @Override
  public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
    ItemStack itemstack = player.getHeldItem(hand);

    if (worldIn.isRemote) {
      return EnumActionResult.SUCCESS;
    } else if (!player.canPlayerEdit(pos.offset(facing), facing, itemstack)) {
      return EnumActionResult.FAIL;
    } else {
      BlockPos blockpos = pos.offset(facing);
      double d0 = this.getYOffset(worldIn, blockpos);
      boolean success = spawnCreature(worldIn, (double) blockpos.getX() + 0.5D, (double) blockpos.getY() + d0, (double) blockpos.getZ() + 0.5D);

      if (!player.capabilities.isCreativeMode) {
        itemstack.shrink(1);
      }

      if (!worldIn.isRemote) {
        if (success) {
          worldIn.playSound(null, blockpos.getX() + 0.5, blockpos.getY() + 0.5, blockpos.getZ() + 0.5, SoundEvents.ENTITY_CHICKEN_EGG, SoundCategory.NEUTRAL, 1f, 1.4f + (worldIn.rand.nextFloat() - 0.5f));
        } else {
          worldIn.playSound(null, blockpos.getX() + 0.5, blockpos.getY() + 0.5, blockpos.getZ() + 0.5, SoundEvents.BLOCK_GLASS_BREAK, SoundCategory.NEUTRAL, 0.5f, 2);
        }
      }

      return EnumActionResult.SUCCESS;
    }
  }

  @Override
  public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
    ItemStack itemstack = playerIn.getHeldItem(handIn);

    if (worldIn.isRemote) {
      return new ActionResult<>(EnumActionResult.PASS, itemstack);
    } else {
      RayTraceResult raytraceresult = this.rayTrace(worldIn, playerIn, true);

      if (raytraceresult != null && raytraceresult.typeOfHit == RayTraceResult.Type.BLOCK) {
        BlockPos blockpos = raytraceresult.getBlockPos();

        if (!(worldIn.getBlockState(blockpos).getBlock() instanceof BlockLiquid)) {
          return new ActionResult<>(EnumActionResult.PASS, itemstack);
        } else if (worldIn.isBlockModifiable(playerIn, blockpos) && playerIn.canPlayerEdit(blockpos, raytraceresult.sideHit, itemstack)) {
          boolean success = spawnCreature(worldIn, (double) blockpos.getX() + 0.5D, (double) blockpos.getY() + 0.5D, (double) blockpos.getZ() + 0.5D);
          if (!playerIn.capabilities.isCreativeMode) {
            itemstack.shrink(1);
          }

          if (!worldIn.isRemote) {
            if (success) {
              worldIn.playSound(blockpos.getX() + 0.5, blockpos.getY() + 0.5, blockpos.getZ() + 0.5, SoundEvents.ENTITY_CHICKEN_EGG, SoundCategory.NEUTRAL, 1f, 1.4f + (worldIn.rand.nextFloat() - 0.5f), false);
            } else {
              worldIn.playSound(blockpos.getX() + 0.5, blockpos.getY() + 0.5, blockpos.getZ() + 0.5, SoundEvents.BLOCK_GLASS_BREAK, SoundCategory.NEUTRAL, 0.5f, 2, false);
            }
          }

          return new ActionResult<>(EnumActionResult.SUCCESS, itemstack);
        } else {
          return new ActionResult<>(EnumActionResult.FAIL, itemstack);
        }
      } else {
        return new ActionResult<>(EnumActionResult.PASS, itemstack);
      }
    }
  }

  private boolean spawnCreature(World worldIn, double x, double y, double z) {
    if (worldIn.rand.nextInt(ConfigManager.silkworm.successChance) == 0) {
      doSpawnCreature(worldIn, x, y, z);
      return true;
    }

    return false;
  }

  public static void doSpawnCreature(World worldIn, double x, double y, double z) {
    EntitySilkworm entity = (EntitySilkworm) EntityList.createEntityByIDFromName(ENTITY_ID, worldIn);

    entity.setLocationAndAngles(x, y, z, MathHelper.wrapDegrees(worldIn.rand.nextFloat() * 360.0F), 0.0F);
    entity.rotationYawHead = entity.rotationYaw;
    entity.renderYawOffset = entity.rotationYaw;
    entity.onInitialSpawn(worldIn.getDifficultyForLocation(new BlockPos(entity)), null);
    entity.setGrowingAge(0);
    worldIn.spawnEntity(entity);
    entity.playLivingSound();
  }

  private double getYOffset(World p_190909_1_, BlockPos p_190909_2_) {
    AxisAlignedBB axisalignedbb = (new AxisAlignedBB(p_190909_2_)).expand(0.0D, -1.0D, 0.0D);
    List<AxisAlignedBB> list = p_190909_1_.getCollisionBoxes(null, axisalignedbb);

    if (list.isEmpty()) {
      return 0.0D;
    } else {
      double d0 = axisalignedbb.minY;

      for (AxisAlignedBB axisalignedbb1 : list) {
        d0 = Math.max(axisalignedbb1.maxY, d0);
      }

      return d0 - (double) p_190909_2_.getY();
    }

  }
}
