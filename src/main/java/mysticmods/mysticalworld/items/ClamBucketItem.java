package mysticmods.mysticalworld.items;

import mysticmods.mysticalworld.entity.ClamEntity;
import mysticmods.mysticalworld.init.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.material.Fluid;

import javax.annotation.Nullable;

public class ClamBucketItem extends BucketItem {
  public ClamBucketItem(java.util.function.Supplier<? extends Fluid> p_i49022_2_, Item.Properties builder) {
    super(p_i49022_2_, builder);
  }

  public void checkExtraContent(Level level, ItemStack stack, BlockPos pos) {
    if (level instanceof ServerLevel) {
      this.spawn((ServerLevel) level, stack, pos);
    }
  }

  protected void playEmptySound(@Nullable Player pPlayer, LevelAccessor pLevel, BlockPos pPos) {
    pLevel.playSound(pPlayer, pPos, SoundEvents.BUCKET_EMPTY_FISH, SoundSource.NEUTRAL, 1.0F, 1.0F);
  }

  private void spawn(ServerLevel level, ItemStack stack, BlockPos pos) {
    Entity entity = ModEntities.CLAM.get().spawn(level, stack, null, pos, MobSpawnType.BUCKET, true, false);
    if (entity != null) {
      ClamEntity clam = (ClamEntity) entity;
      CompoundTag tag = stack.getOrCreateTag();
      clam.getEntityData().set(ClamEntity.isEnder, tag.getBoolean("isEnder"));
      clam.getEntityData().set(ClamEntity.age, tag.getInt("age"));
      clam.setFromBucket(true);
    }
  }
}
