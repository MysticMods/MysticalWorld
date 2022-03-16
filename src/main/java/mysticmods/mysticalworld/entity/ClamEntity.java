package mysticmods.mysticalworld.entity;

import mysticmods.mysticalworld.config.ConfigManager;
import mysticmods.mysticalworld.init.deferred.ModItems;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;

import javax.annotation.Nullable;
import java.util.Random;

public class ClamEntity extends WaterAnimal {
  public static final EntityDataAccessor<Boolean> isEnder = SynchedEntityData.defineId(ClamEntity.class, EntityDataSerializers.BOOLEAN);
  public static final EntityDataAccessor<Integer> age = SynchedEntityData.defineId(ClamEntity.class, EntityDataSerializers.INT);
  private static final EntityDataAccessor<Boolean> FROM_BUCKET = SynchedEntityData.defineId(ClamEntity.class, EntityDataSerializers.BOOLEAN);

  public ClamEntity(EntityType<? extends WaterAnimal> type, Level level) {
    super(type, level);
  }

  @Nullable
  @Override
  public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pReason, @Nullable SpawnGroupData pSpawnData, @Nullable CompoundTag pDataTag) {
    return super.finalizeSpawn(pLevel, pDifficulty, pReason, pSpawnData, pDataTag);
  }

  @Override
  protected void defineSynchedData() {
    super.defineSynchedData();
    getEntityData().define(isEnder, ConfigManager.CLAM_CONFIG.getEnderChance() != 0 && random.nextInt(ConfigManager.CLAM_CONFIG.getEnderChance()) == 0);
    getEntityData().define(age, random.nextInt(ConfigManager.CLAM_CONFIG.getInitialAge()));
    getEntityData().define(FROM_BUCKET, false);
  }

  @Override
  protected void registerGoals() {
  }

  private boolean fromBucket() {
    return this.entityData.get(FROM_BUCKET);
  }

  public void setFromBucket(boolean p_203706_1_) {
    this.entityData.set(FROM_BUCKET, p_203706_1_);
  }

  @Override
  public void addAdditionalSaveData(CompoundTag pCompound) {
    super.addAdditionalSaveData(pCompound);
    pCompound.putBoolean("isEnder", getEntityData().get(isEnder));
    pCompound.putInt("age", getEntityData().get(age));
    pCompound.putBoolean("FromBucket", this.fromBucket());
  }

  @Override
  public void readAdditionalSaveData(CompoundTag pCompound) {
    super.readAdditionalSaveData(pCompound);
    getEntityData().set(isEnder, pCompound.getBoolean("isEnder"));
    getEntityData().set(age, pCompound.getInt("age"));
    this.setFromBucket(pCompound.getBoolean("FromBucket"));
  }

  @Override
  protected float getStandingEyeHeight(Pose pPose, EntityDimensions pSize) {
    return 0.3f;
  }

  public static AttributeSupplier.Builder attributes() {
    return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 20d).add(Attributes.MOVEMENT_SPEED, 0).add(Attributes.KNOCKBACK_RESISTANCE, 5d);
  }

  @Override
  public void push(double x, double y, double z) {
    // fixed!
  }

  @Override
  public void tick() {
    super.tick();
  }

  @Override
  public void aiStep() {
    super.aiStep();
    if (this.isInWater() && !this.isOnGround()) {
      this.setDeltaMovement(this.getDeltaMovement().add(0, -0.05, 0));
    }
    getEntityData().set(age, getEntityData().get(age) + 1);
    if (this.level.isClientSide) {
      if (getEntityData().get(age) > ConfigManager.CLAM_CONFIG.getMaxAge()) {
        if (getEntityData().get(isEnder)) {
          this.level.addParticle(ParticleTypes.PORTAL, this.getX() + (this.random.nextDouble() - 0.5D) * (double) this.getBbWidth(), this.getY() + this.random.nextDouble() * (double) this.getBbHeight() - 0.25D, this.getZ() + (this.random.nextDouble() - 0.5D) * (double) this.getBbWidth(), (this.random.nextDouble() - 0.5D) * 2.0D, -this.random.nextDouble(), (this.random.nextDouble() - 0.5D) * 2.0D);
        } else if (random.nextInt(12) == 0) {
          for (int i = 0; i < 2; ++i) {
            this.level.addParticle(getEntityData().get(isEnder) ? ParticleTypes.PORTAL : random.nextInt(5) == 0 ? ParticleTypes.BUBBLE_POP : ParticleTypes.BUBBLE, this.getX() + (this.random.nextDouble() - 0.5D) * (double) this.getBbWidth(), this.getY() + this.random.nextDouble() * (double) this.getBbHeight() - 0.25D, this.getZ() + (this.random.nextDouble() - 0.5D) * (double) this.getBbWidth(), (this.random.nextDouble() - 0.5D) * 2.0D, -this.random.nextDouble(), (this.random.nextDouble() - 0.5D) * 2.0D);
          }
        }
      }
    }
  }

  @Override
  public boolean requiresCustomPersistence() {
    return super.requiresCustomPersistence() || this.fromBucket();
  }

  @Override
  public boolean removeWhenFarAway(double pDistanceToClosestPlayer) {
    return !this.fromBucket() && !this.hasCustomName();
  }

  // TODO: Migrate to `Bucketable`?
  @Override
  protected InteractionResult mobInteract(Player pPlayer, InteractionHand pHand) {
    ItemStack itemstack = pPlayer.getItemInHand(pHand);
    if (itemstack.getItem() == Items.WATER_BUCKET && this.isAlive()) {
      this.playSound(SoundEvents.BUCKET_FILL_FISH, 1.0F, 1.0F);
      itemstack.shrink(1);
      ItemStack itemstack1 = this.getBucketItemStack();
      this.saveToBucketTag(itemstack1);
      if (!this.level.isClientSide) {
        CriteriaTriggers.FILLED_BUCKET.trigger((ServerPlayer) pPlayer, itemstack1);
      }

      if (itemstack.isEmpty()) {
        pPlayer.setItemInHand(pHand, itemstack1);
      } else if (!pPlayer.getInventory().add(itemstack1)) {
        pPlayer.drop(itemstack1, false);
      }

      this.discard();
      return InteractionResult.sidedSuccess(this.level.isClientSide);
    } else {
      return super.mobInteract(pPlayer, pHand);
    }
  }

  protected void saveToBucketTag(ItemStack p_204211_1_) {
    if (this.hasCustomName()) {
      p_204211_1_.setHoverName(this.getCustomName());
    }

    CompoundTag tag = p_204211_1_.getOrCreateTag();
    tag.putBoolean("isEnder", getEntityData().get(isEnder));
    tag.putInt("age", getEntityData().get(age));
  }

  protected ItemStack getBucketItemStack() {
    return new ItemStack(ModItems.CLAM_BUCKET.get());
  }

  public static boolean checkClamSpawnRules(EntityType<? extends ClamEntity> p_223363_0_, LevelAccessor p_223363_1_, MobSpawnType p_223363_2_, BlockPos p_223363_3_, Random p_223363_4_) {
    return p_223363_1_.getBlockState(p_223363_3_).is(Blocks.WATER) && p_223363_1_.getBlockState(p_223363_3_.above()).is(Blocks.WATER);
  }
}
