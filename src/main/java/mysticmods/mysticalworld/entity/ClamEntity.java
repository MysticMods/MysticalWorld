package mysticmods.mysticalworld.entity;

import mysticmods.mysticalworld.MysticalWorld;
import mysticmods.mysticalworld.config.ConfigManager;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.passive.WaterMobEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public class ClamEntity extends WaterMobEntity {
  public static final DataParameter<Boolean> isEnder = EntityDataManager.defineId(ClamEntity.class, DataSerializers.BOOLEAN);
  public static final DataParameter<Integer> age = EntityDataManager.defineId(ClamEntity.class, DataSerializers.INT);

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
  }

  @Override
  protected void registerGoals() {
  }

  @Override
  public void addAdditionalSaveData(CompoundNBT pCompound) {
    super.addAdditionalSaveData(pCompound);
    getEntityData().set(isEnder, pCompound.getBoolean("isEnder"));
    getEntityData().set(age, pCompound.getInt("age"));
  }

  @Override
  public void readAdditionalSaveData(CompoundNBT pCompound) {
    super.readAdditionalSaveData(pCompound);
    pCompound.putBoolean("isEnder", getEntityData().get(isEnder));
    pCompound.putInt("age", getEntityData().get(age));
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
      if (getEntityData().get(age) > ConfigManager.CLAM_CONFIG.getMaxAge() && random.nextInt(6) == 0) {
        for (int i = 0; i < 2; ++i) {
          this.level.addParticle(getEntityData().get(isEnder) ? ParticleTypes.PORTAL : random.nextInt(5) == 0 ? ParticleTypes.BUBBLE_POP : ParticleTypes.BUBBLE, this.getX() + (this.random.nextDouble() - 0.5D) * (double) this.getBbWidth(), this.getY() + this.random.nextDouble() * (double) this.getBbHeight() - 0.25D, this.getZ() + (this.random.nextDouble() - 0.5D) * (double) this.getBbWidth(), (this.random.nextDouble() - 0.5D) * 2.0D, -this.random.nextDouble(), (this.random.nextDouble() - 0.5D) * 2.0D);
        }
      }
    }
  }

  public static boolean checkClamSpawnRules(EntityType<? extends ClamEntity> p_223363_0_, IWorld p_223363_1_, SpawnReason p_223363_2_, BlockPos p_223363_3_, Random p_223363_4_) {
    return p_223363_1_.getBlockState(p_223363_3_).is(Blocks.WATER) && p_223363_1_.getBlockState(p_223363_3_.above()).is(Blocks.WATER);
  }
}
