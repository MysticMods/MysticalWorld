package mysticmods.mysticalworld.items;

import mysticmods.mysticalworld.entity.ClamEntity;
import mysticmods.mysticalworld.init.ModEntities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;

public class ClamBucketItem extends BucketItem {
  public ClamBucketItem(java.util.function.Supplier<? extends Fluid> p_i49022_2_, Item.Properties builder) {
    super(p_i49022_2_, builder);
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
    Entity entity = ModEntities.CLAM.get().spawn(level, stack, null, pos, SpawnReason.BUCKET, true, false);
    if (entity != null) {
      ClamEntity clam = (ClamEntity) entity;
      CompoundNBT tag = stack.getOrCreateTag();
      clam.getEntityData().set(ClamEntity.isEnder, tag.getBoolean("isEnder"));
      clam.getEntityData().set(ClamEntity.age, tag.getInt("age"));
      clam.setFromBucket(true);
    }
  }
}
