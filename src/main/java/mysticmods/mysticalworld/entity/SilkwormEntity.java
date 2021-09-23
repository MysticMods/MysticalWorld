package mysticmods.mysticalworld.entity;

import mysticmods.mysticalworld.MysticalWorld;
import mysticmods.mysticalworld.config.ConfigManager;
import mysticmods.mysticalworld.events.LeafHandler;
import mysticmods.mysticalworld.init.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nonnull;

public class SilkwormEntity extends AnimalEntity {
  public static ResourceLocation LOOT_TABLE = new ResourceLocation(MysticalWorld.MODID, "entity/silkworm");

  private static final DataParameter<Integer> SIZE = EntityDataManager.defineId(SilkwormEntity.class, DataSerializers.INT);
  private static final DataParameter<Integer> LEAVES_CONSUMED = EntityDataManager.defineId(SilkwormEntity.class, DataSerializers.INT);
  private static final int MAX_SIZE = 120;

  private ItemEntity leafTarget;
  private int lastTickPlayed = 0;

  public SilkwormEntity(EntityType<? extends AnimalEntity> type, World worldIn) {
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
  @Nonnull
  public AgeableEntity getBreedOffspring(ServerWorld world, AgeableEntity ageable) {
    return null;
  }

  @Override
  protected void registerGoals() {
    goalSelector.addGoal(1, new SwimGoal(this));
    goalSelector.addGoal(2, new MeleeAttackGoal(this, 0.5d, false));
    goalSelector.addGoal(3, new RandomWalkingGoal(this, 0.5d));
    goalSelector.addGoal(3, new TemptGoal(this, 0.9d, false, Ingredient.of(LeafHandler.getLeafItems().toArray(new Item[0]))));
    goalSelector.addGoal(8, new LookRandomlyGoal(this));
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
      level.playSound(null, getX(), getY(), getZ(), SoundEvents.LLAMA_EAT, SoundCategory.NEUTRAL, 0.5f, 1.2f + random.nextFloat() * 0.02f);
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
  public ActionResultType mobInteract(PlayerEntity player, Hand hand) {
    if (!player.level.isClientSide) {
      ItemStack itemstack = player.getItemInHand(hand);

      if (LeafHandler.getLeafItems().contains(itemstack.getItem())) {
        if (!player.abilities.instabuild) {
          itemstack.shrink(1);
        }
        eatLeaves();

        return ActionResultType.SUCCESS;
      }
    }

    return super.mobInteract(player, hand);
  }

  @Override
  public float getEyeHeight(Pose pose) {
    return 0.1F;
  }

  public static AttributeModifierMap.MutableAttribute attributes() {
    return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 10.0d).add(Attributes.MOVEMENT_SPEED, 0.15d);
  }

  // TODO: prevent crop trampling

  @Override
  protected SoundEvent getAmbientSound() {
    if (level.random.nextInt(4) == 0) {
      return net.minecraft.util.SoundEvents.ENDERMITE_AMBIENT;
    }

    return null;
  }

  @Override
  protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
    return net.minecraft.util.SoundEvents.ENDERMITE_HURT;
  }

  @Override
  protected SoundEvent getDeathSound() {
    return net.minecraft.util.SoundEvents.ENDERMITE_DEATH;
  }

  @Override
  protected void playStepSound(BlockPos pos, BlockState state) {
    this.playSound(net.minecraft.util.SoundEvents.ENDERMITE_STEP, 0.15F, 1.3F + (level.random.nextFloat() - 0.5f));
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
  protected float getVoicePitch() {
    return 1.3f + random.nextFloat() - 0.5f;
  }

  @Override
  public void readAdditionalSaveData(CompoundNBT compound) {
    super.readAdditionalSaveData(compound);
    setSize(compound.getInt("Size"));
    setLeavesConsumed(compound.getInt("Leaves"));
  }

  @Override
  public void addAdditionalSaveData(CompoundNBT compound) {
    super.addAdditionalSaveData(compound);
    compound.putInt("Size", getSize());
    compound.putInt("Leaves", getLeavesConsumed());
  }

  @Override
  public boolean causeFallDamage(float distance, float damageMultiplier) {
    float[] ret = net.minecraftforge.common.ForgeHooks.onLivingFall(this, distance, damageMultiplier);
    if (ret == null) return false;
    distance = ret[0];
    damageMultiplier = ret[1];
    // This just handles riding entities
    // super.fall(distance, damageMultiplier);
    EffectInstance potioneffect = this.getEffect(Effects.JUMP);
    float f = potioneffect == null ? 0.0F : (float) (potioneffect.getAmplifier() + 1);
    int i = MathHelper.ceil((distance - 3.0F - f) * damageMultiplier);

    if (i > 0) {
      this.playSound(this.getFallDamageSound(i), 1.0F, 1.0F);
      // They take no fall damage
      // this.attackEntityFrom(DamageSource.FALL, (float) i);
      int j = MathHelper.floor(this.getX());
      int k = MathHelper.floor(this.getY() - 0.2);
      int l = MathHelper.floor(this.getZ());
      BlockState iblockstate = this.level.getBlockState(new BlockPos(j, k, l));

      if (iblockstate.getMaterial() != Material.AIR) {
        SoundType soundtype = iblockstate.getBlock().getSoundType(iblockstate, level, new BlockPos(j, k, l), this);
        this.playSound(soundtype.getFallSound(), soundtype.getVolume() * 0.5F, soundtype.getPitch() * 0.75F);
      }
    }

    return false;
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
        level.playSound(null, getX(), getY(), getZ(), net.minecraft.util.SoundEvents.CHICKEN_EGG, SoundCategory.NEUTRAL, 0.5f, 1.2f + random.nextFloat() - 0.5f);
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
  public CreatureAttribute getMobType() {
    return CreatureAttribute.ARTHROPOD;
  }
}

