package mysticmods.mysticalworld.items;

import mysticmods.mysticalworld.entity.ClamEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import mysticmods.mysticalworld.init.ModEntities;

import javax.annotation.Nullable;

public class ClamBucketItem extends BucketItem {
  public final boolean isEnder;

  public ClamBucketItem(boolean isEnder, java.util.function.Supplier<? extends Fluid> p_i49022_2_, Item.Properties builder) {
    super(p_i49022_2_, builder);
    this.isEnder = isEnder;
  }

  public void checkExtraContent(World level, ItemStack stack, BlockPos pos) {
    if (level instanceof ServerWorld) {
      this.spawn((ServerWorld) level, stack, pos);
    }
  }

  protected void playEmptySound(@Nullable PlayerEntity pPlayer, IWorld pLevel, BlockPos pPos) {
    pLevel.playSound(pPlayer, pPos, SoundEvents.BUCKET_EMPTY_FISH, SoundCategory.NEUTRAL, 1.0F, 1.0F);
  }

  private void spawn(ServerWorld level, ItemStack stack, BlockPos pos) {
    Entity entity = ModEntities.CLAM.get().spawn(level, stack, (PlayerEntity) null, pos, SpawnReason.BUCKET, true, false);
    if (entity != null) {
      ClamEntity clam = (ClamEntity) entity;
      if (isEnder) {
        clam.getEntityData().set(ClamEntity.isEnder, true);
      }
    }
    if (entity != null) {
      ((AbstractFishEntity) entity).setFromBucket(true);
    }

  }
}
