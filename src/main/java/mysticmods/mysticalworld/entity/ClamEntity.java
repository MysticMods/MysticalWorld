package mysticmods.mysticalworld.entity;

import mysticmods.mysticalworld.MysticalWorld;
import mysticmods.mysticalworld.config.ConfigManager;
import mysticmods.mysticalworld.init.ModItems;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.passive.WaterMobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public class ClamEntity extends WaterMobEntity {
  public static final DataParameter<Boolean> isEnder = EntityDataManager.defineId(ClamEntity.class, DataSerializers.BOOLEAN);
  public static final DataParameter<Integer> age = EntityDataManager.defineId(ClamEntity.class, DataSerializers.INT);
  private static final DataParameter<Boolean> FROM_BUCKET = EntityDataManager.defineId(ClamEntity.class, DataSerializers.BOOLEAN);

  public ClamEntity(EntityType<? extends WaterMobEntity> type, World level) {
    super(type, level);
  }

  @Nullable
  @Override
  public ILivingEntityData finalizeSpawn(IServerWorld pLevel, DifficultyInstance pDifficulty, SpawnReason pReason, @Nullable ILivingEntityData pSpawnData, @Nullable CompoundNBT pDataTag) {
    return super.finalizeSpawn(pLevel, pDifficulty, pReason, pSpawnData, pDataTag);
  }

  @Override
  protected void defineSynchedData() {
    super.defineSynchedData();
    getEntityData().define(isEnder, random.nextInt(ConfigManager.CLAM_CONFIG.getEnderChance()) == 0);
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
  public void addAdditionalSaveData(CompoundNBT pCompound) {
    super.addAdditionalSaveData(pCompound);
    pCompound.putBoolean("isEnder", getEntityData().get(isEnder));
    pCompound.putInt("age", getEntityData().get(age));
    pCompound.putBoolean("FromBucket", this.fromBucket());
  }

  @Override
  public void readAdditionalSaveData(CompoundNBT pCompound) {
    super.readAdditionalSaveData(pCompound);
    getEntityData().set(isEnder, pCompound.getBoolean("isEnder"));
    getEntityData().set(age, pCompound.getInt("age"));
    this.setFromBucket(pCompound.getBoolean("FromBucket"));
  }

  @Override
  protected float getStandingEyeHeight(Pose pPose, EntitySize pSize) {
    return 0.3f;
  }

  @Override
  protected ResourceLocation getDefaultLootTable() {
    return new ResourceLocation(MysticalWorld.MODID, "entities/clam");
  }

  public static AttributeModifierMap.MutableAttribute attributes() {
    return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 20d).add(Attributes.MOVEMENT_SPEED, 0).add(Attributes.KNOCKBACK_RESISTANCE, 5d);
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

  @Override
  protected ActionResultType mobInteract(PlayerEntity pPlayer, Hand pHand) {
    ItemStack itemstack = pPlayer.getItemInHand(pHand);
    if (itemstack.getItem() == Items.WATER_BUCKET && this.isAlive()) {
      this.playSound(SoundEvents.BUCKET_FILL_FISH, 1.0F, 1.0F);
      itemstack.shrink(1);
      ItemStack itemstack1 = this.getBucketItemStack();
      this.saveToBucketTag(itemstack1);
      if (!this.level.isClientSide) {
        CriteriaTriggers.FILLED_BUCKET.trigger((ServerPlayerEntity) pPlayer, itemstack1);
      }

      if (itemstack.isEmpty()) {
        pPlayer.setItemInHand(pHand, itemstack1);
      } else if (!pPlayer.inventory.add(itemstack1)) {
        pPlayer.drop(itemstack1, false);
      }

      this.remove();
      return ActionResultType.sidedSuccess(this.level.isClientSide);
    } else {
      return super.mobInteract(pPlayer, pHand);
    }
  }

  protected void saveToBucketTag(ItemStack p_204211_1_) {
    if (this.hasCustomName()) {
      p_204211_1_.setHoverName(this.getCustomName());
    }

    CompoundNBT tag = p_204211_1_.getOrCreateTag();
    tag.putBoolean("isEnder", getEntityData().get(isEnder));
    tag.putInt("age", getEntityData().get(age));
  }

  protected ItemStack getBucketItemStack() {
    return new ItemStack(ModItems.CLAM_BUCKET.get());
  }

  public static boolean checkClamSpawnRules(EntityType<? extends ClamEntity> p_223363_0_, IWorld p_223363_1_, SpawnReason p_223363_2_, BlockPos p_223363_3_, Random p_223363_4_) {
    return p_223363_1_.getBlockState(p_223363_3_).is(Blocks.WATER) && p_223363_1_.getBlockState(p_223363_3_.above()).is(Blocks.WATER);
  }
}
