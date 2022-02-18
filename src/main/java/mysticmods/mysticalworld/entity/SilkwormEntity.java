package mysticmods.mysticalworld.entity;

import mysticmods.mysticalworld.MysticalWorld;
import mysticmods.mysticalworld.config.ConfigManager;
import mysticmods.mysticalworld.events.LeafHandler;
import mysticmods.mysticalworld.init.ModItems;
import mysticmods.mysticalworld.init.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;

public class SilkwormEntity extends Animal {
  public static ResourceLocation LOOT_TABLE = new ResourceLocation(MysticalWorld.MODID, "entity/silkworm");

  private static final EntityDataAccessor<Integer> SIZE = SynchedEntityData.defineId(SilkwormEntity.class, EntityDataSerializers.INT);
  private static final EntityDataAccessor<Integer> LEAVES_CONSUMED = SynchedEntityData.defineId(SilkwormEntity.class, EntityDataSerializers.INT);
  private static final int MAX_SIZE = 120;

  private ItemEntity leafTarget;
  private int lastTickPlayed = 0;

  public SilkwormEntity(EntityType<? extends Animal> type, Level worldIn) {
    super(type, worldIn);
    this.xpReward = 1;
  }

  public void setLeafTarget(ItemEntity leaf) {
    this.leafTarget = leaf;
  }

  public ItemEntity getLeafTarget() {
    return this.leafTarget;
  }

  @Override
  public AgeableMob getBreedOffspring(ServerLevel world, AgeableMob ageable) {
    return null;
  }

  @Override
  protected void registerGoals() {
    goalSelector.addGoal(1, new FloatGoal(this));
    goalSelector.addGoal(2, new MeleeAttackGoal(this, 0.5d, false));
    goalSelector.addGoal(3, new RandomStrollGoal(this, 0.5d));
    // TODO: TAG
    goalSelector.addGoal(3, new TemptGoal(this, 0.9d, Ingredient.of(LeafHandler.getLeafItems().toArray(new Item[0])), false));
    goalSelector.addGoal(8, new RandomLookAroundGoal(this));
    targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, SilkwormEntity.class, false));
  }

  @Override
  protected void defineSynchedData() {
    super.defineSynchedData();
    this.entityData.define(SIZE, 0);
    this.entityData.define(LEAVES_CONSUMED, 0);
  }

  public int getLeavesConsumed() {
    return this.entityData.get(LEAVES_CONSUMED);
  }

  public void setLeavesConsumed(int amount) {
    this.entityData.set(LEAVES_CONSUMED, amount);
  }

  public void resetLeaves() {
    this.entityData.set(LEAVES_CONSUMED, 0);
  }

  private boolean shouldPlaySound() {
    return (tickCount - lastTickPlayed) > 8;
  }

  public void eatLeaves() {
    incLeaves();
    for (int i = 0; i < 1 + random.nextInt(3); i++) {
      grow();
    }
    this.heal(1f);
    if (shouldPlaySound()) {
      level.playSound(null, getX(), getY(), getZ(), ModSounds.SILKWORM_EAT.get(), SoundSource.NEUTRAL, 0.5f, 1.2f + random.nextFloat() * 0.02f);
      lastTickPlayed = tickCount;
    }
  }

  private void incLeaves() {
    this.entityData.set(LEAVES_CONSUMED, getLeavesConsumed() + 1);
  }

  public int getSize() {
    return this.entityData.get(SIZE);
  }

  public void setSize(int size) {
    this.entityData.set(SIZE, size);
  }

  private void incSize() {
    if (!this.isBaby()) {
      this.entityData.set(SIZE, getSize() + 1);
    }
  }

  @Override
  public InteractionResult mobInteract(Player player, InteractionHand hand) {
    if (!player.level.isClientSide) {
      ItemStack itemstack = player.getItemInHand(hand);

      if (LeafHandler.getLeafItems().contains(itemstack.getItem())) {
        if (!player.isCreative()) {
          itemstack.shrink(1);
        }
        eatLeaves();

        return InteractionResult.SUCCESS;
      }
    }

    return super.mobInteract(player, hand);
  }

  @Override
  public float getEyeHeight(Pose pose) {
    return 0.1F;
  }

  public static AttributeSupplier.Builder attributes() {
    return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 10.0d).add(Attributes.MOVEMENT_SPEED, 0.15d);
  }

  // TODO: prevent crop trampling

  @Override
  protected SoundEvent getAmbientSound() {
    if (level.random.nextInt(4) == 0) {
      return ModSounds.SILKWORM_AMBIENT.get();
    }

    return null;
  }

  @Override
  protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
    return ModSounds.SILKWORM_HURT.get();
  }

  @Override
  protected SoundEvent getDeathSound() {
    return ModSounds.SILKWORM_DEATH.get();
  }

  @Override
  protected void playStepSound(BlockPos pos, BlockState state) {
    this.playSound(ModSounds.SILKWORM_STEP.get(), 0.15F, 1.3F + (level.random.nextFloat() - 0.5f));
  }

  @Override
  public ResourceLocation getDefaultLootTable() {
    return LOOT_TABLE;
  }

  @Override
  protected float getSoundVolume() {
    return 0.2f;
  }

  @Override
  public float getVoicePitch() {
    return 1.3f + random.nextFloat() - 0.5f;
  }

  @Override
  public void readAdditionalSaveData(CompoundTag compound) {
    super.readAdditionalSaveData(compound);
    setSize(compound.getInt("Size"));
    setLeavesConsumed(compound.getInt("Leaves"));
  }

  @Override
  public void addAdditionalSaveData(CompoundTag compound) {
    super.addAdditionalSaveData(compound);
    compound.putInt("Size", getSize());
    compound.putInt("Leaves", getLeavesConsumed());
  }

  @Override
  public boolean causeFallDamage(float distance, float damageMultiplier, DamageSource pDamageSource) {
    float[] ret = net.minecraftforge.common.ForgeHooks.onLivingFall(this, distance, damageMultiplier);
    if (ret == null) return false;
    distance = ret[0];
    damageMultiplier = ret[1];
    MobEffectInstance potioneffect = this.getEffect(MobEffects.JUMP);
    float f = potioneffect == null ? 0.0F : (float) (potioneffect.getAmplifier() + 1);
    int i = Mth.ceil((distance - 3.0F - f) * damageMultiplier);

    if (i > 0) {
      this.playSound(this.getFallDamageSound(i), 1.0F, 1.0F);
      int j = Mth.floor(this.getX());
      int k = Mth.floor(this.getY() - 0.2);
      int l = Mth.floor(this.getZ());
      BlockState iblockstate = this.level.getBlockState(new BlockPos(j, k, l));

      if (iblockstate.getMaterial() != Material.AIR) {
        SoundType soundtype = iblockstate.getBlock().getSoundType(iblockstate, level, new BlockPos(j, k, l), this);
        this.playSound(soundtype.getFallSound(), soundtype.getVolume() * 0.5F, soundtype.getPitch() * 0.75F);
      }
    }

    return false;
  }

  private SoundEvent getFallDamageSound(int pHeight) {
    return pHeight > 4 ? this.getFallSounds().big() : this.getFallSounds().small();
  }

  @Override
  public void tick() {
    super.tick();

    if (!this.level.isClientSide) {
      if (this.random.nextInt(ConfigManager.SILKWORM_CONFIG.getGrowthChance()) == 0) {
        grow();
      }
    }
  }

  public void grow() {
    int size = getSize();
    if (size == MAX_SIZE) {
      setSize(0);
      if (isEffectiveAi()) {
        int quantity = Math.max(1, Math.min(5, (random.nextInt(Math.max(getLeavesConsumed() % 8, 1)))));
        this.spawnAtLocation(ModItems.SILK_COCOON.get(), quantity);
        this.resetLeaves();
        level.playSound(null, getX(), getY(), getZ(), ModSounds.SILKWORM_PLOP.get(), SoundSource.NEUTRAL, 0.5f, 1.2f + random.nextFloat() - 0.5f);
      }
    } else {
      incSize();
    }
  }

  @Override
  public boolean doHurtTarget(Entity entityIn) {
    if (entityIn instanceof SilkwormEntity) {
      SilkwormEntity other = (SilkwormEntity) entityIn;
      if (other.getSize() > this.getSize()) {
        return false; // Don't attack things that are bigger than us.
      }

      boolean result = entityIn.hurt(DamageSource.mobAttack(this), Integer.MAX_VALUE);
      if (result) {
        grow();
      }
      return result;
    }
    return false;
  }

  @Override
  public MobType getMobType() {
    return MobType.ARTHROPOD;
  }
}

