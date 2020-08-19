package epicsquid.mysticalworld.entity;

import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.init.ModEntities;
import epicsquid.mysticalworld.init.ModSounds;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.GhastEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.passive.RabbitEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.passive.horse.AbstractHorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.EnumSet;

public class SilverFoxEntity extends TameableEntity {
  private static final DataParameter<Float> DATA_HEALTH_ID = EntityDataManager.createKey(SilverFoxEntity.class, DataSerializers.FLOAT);
  private static final DataParameter<Boolean> SLEEPING = EntityDataManager.createKey(SilverFoxEntity.class, DataSerializers.BOOLEAN);
  private SleepGoal sleepGoal;

  public SilverFoxEntity(EntityType<? extends SilverFoxEntity> type, World worldIn) {
    super(type, worldIn);
//    setSize(0.75f, 0.75f);
    setTamed(false);
    this.experienceValue = 5;
  }

  @Override
  public boolean isBreedingItem(@Nonnull ItemStack stack) {
    return stack.getItem() == Items.CHICKEN;
  }

  @Nullable
  @Override
  protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
    return ModSounds.FOX_BARK.get();
  }

  @Nullable
  @Override
  protected SoundEvent getDeathSound() {
    return ModSounds.FOX_DEATH.get();
  }

  @Override
  protected void registerGoals() {
    this.sitGoal = new SitGoal(this);
    this.sleepGoal = new SleepGoal(this);
    goalSelector.addGoal(0, new SwimGoal(this));
    goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
    goalSelector.addGoal(2, this.sitGoal);
    goalSelector.addGoal(2, this.sleepGoal);
    goalSelector.addGoal(3, new TemptGoal(this, 1.25D, Ingredient.fromItems(Items.CHICKEN), false));
    goalSelector.addGoal(4, new LeapAtTargetGoal(this, 0.4F));
    goalSelector.addGoal(5, new MeleeAttackGoal(this, 1.0D, true));
    goalSelector.addGoal(6, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F, false));
    goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 6.0F));
    goalSelector.addGoal(7, new BreedGoal(this, 1.0D));
    goalSelector.addGoal(7, new LookRandomlyGoal(this));
    goalSelector.addGoal(8, new RandomWalkingGoal(this, 1.0D));
    //goalSelector.addGoal(9, new EntityAIBeg(this, 8.0F));
    targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
    targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
    targetSelector.addGoal(3, new HurtByTargetGoal(this));
    targetSelector.addGoal(4, new NonTamedTargetGoal<>(this, AnimalEntity.class, false, e -> e instanceof ChickenEntity || e instanceof RabbitEntity));
  }

  @Override
  protected void registerAttributes() {
    super.registerAttributes();
    if (this.isTamed()) {
      getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.30000001192092896D);
      getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
      getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5D);
    } else {
      getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
      getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.20000000298023224D);
      getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2D);
    }
  }

  @Override
  public void setAttackTarget(@Nullable LivingEntity entitylivingbaseIn) {
    super.setAttackTarget(entitylivingbaseIn);

    if (entitylivingbaseIn == null) {
      this.setAngry(false);
    } else if (!this.isTamed()) {
      this.setAngry(true);
    }
  }

  @Override
  protected void updateAITasks() {
    this.dataManager.set(DATA_HEALTH_ID, this.getHealth());
  }

  @Override
  protected void registerData() {
    super.registerData();
    this.dataManager.register(DATA_HEALTH_ID, this.getHealth());
    this.dataManager.register(SLEEPING, false);
  }

  @Override
  protected float getSoundVolume() {
    return 0.3F;
  }

  @Override
  public void writeAdditional(CompoundNBT compound) {
    super.writeAdditional(compound);
    compound.putBoolean("Angry", this.isAngry());
    compound.putBoolean("Sleeping", this.isSleeping());
  }

  @Override
  public void readAdditional(CompoundNBT compound) {
    super.readAdditional(compound);
    this.setAngry(compound.getBoolean("Angry"));

    if (compound.get("Sleeping") != null) {
      this.setSleeping(compound.getBoolean("Sleeping"));
      if (this.sleepGoal != null) {
        this.sleepGoal.setSleeping(this.isSleeping());
      }
    }
  }

  @Nullable
  @Override
  protected SoundEvent getAmbientSound() {
    if (this.isAngry()) {
      return ModSounds.FOX_AGGRO.get();
    } else if (this.isSleeping()) {
      return ModSounds.FOX_SLEEP.get();
    } else {
      return ModSounds.FOX_IDLE.get();
    }
  }

  @Override
  public void livingTick() {
    super.livingTick();

    if (this.isAngry() && this.isSleeping()) {
      this.setSleeping(false);
    }

    if (!this.world.isRemote && this.getAttackTarget() == null && this.isAngry()) {
      this.setAngry(false);
    }
  }

  @Override
  public boolean attackEntityFrom(DamageSource source, float amount) {
    if (isInvulnerableTo(source)) {
      return false;
    } else {
      Entity entity = source.getTrueSource();

      if (isSleeping()) {
        setSleeping(false);
      }

      if (sitGoal != null) {
        sitGoal.setSitting(false);
      }

      if (entity != null && !(entity instanceof PlayerEntity) && !(entity instanceof ArrowEntity)) {
        amount = (amount + 1.0F) / 2.0F;
      }

      return super.attackEntityFrom(source, amount);
    }
  }

  @Override
  public boolean attackEntityAsMob(Entity entityIn) {
    boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), (float) ((int) getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getValue()));

    if (flag) {
      applyEnchantments(this, entityIn);
      playSound(ModSounds.FOX_BITE.get(), 1.0f, 1.0f);
    }

    return flag;
  }

  @Override
  public void setTamed(boolean tamed) {
    super.setTamed(tamed);

    if (tamed) {
      getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
    } else {
      getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
    }
  }

  @Override
  public boolean processInteract(PlayerEntity player, Hand hand) {
    ItemStack stack = player.getHeldItem(hand);

    if (isTamed()) {
      if (!stack.isEmpty()) {
        if (stack.getItem().isFood() && (stack.getItem() == Items.APPLE || stack.getItem() == Items.GOLDEN_APPLE)) {
          if (dataManager.get(DATA_HEALTH_ID) < 20.0F) {
            if (!player.isCreative()) {
              stack.shrink(1);
            }

            heal((float) stack.getItem().getFood().getHealing());
            return true;
          }
        }
      }

      if (this.isOwner(player) && !this.world.isRemote && !this.isBreedingItem(stack)) {
        this.sitGoal.setSitting(!this.isSitting());
        this.isJumping = false;
        this.navigator.clearPath();
        this.setAttackTarget(null);
      }
    } else if (stack.getItem() == Items.APPLE && !this.isAngry()) {
      if (!player.isCreative()) {
        stack.shrink(1);
      }

      if (!this.world.isRemote) {
        if (this.rand.nextInt(3) == 0 && !net.minecraftforge.event.ForgeEventFactory.onAnimalTame(this, player)) {
          this.setTamedBy(player);
          this.navigator.clearPath();
          this.setAttackTarget(null);
          this.sitGoal.setSitting(true);
          this.setHealth(20.0F);
          this.world.setEntityState(this, (byte) 7);
        } else {
          this.world.setEntityState(this, (byte) 6);
        }
      }

      return true;
    }

    return super.processInteract(player, hand);
  }

  public boolean isAngry() {
    return (this.dataManager.get(TAMED) & 2) != 0;
  }

  public void setAngry(boolean angry) {
    byte b0 = this.dataManager.get(TAMED);

    if (angry) {
      this.dataManager.set(TAMED, (byte) (b0 | 2));
    } else {
      this.dataManager.set(TAMED, (byte) (b0 & -3));
    }
  }

  @Override
  public boolean isSleeping() {
    try {
      return (this.dataManager.get(SLEEPING));
    } catch (ClassCastException e) {
      e.printStackTrace();
      return false;
    }
  }

  public void setSleeping(boolean sleeping) {
    this.dataManager.set(SLEEPING, sleeping);
  }

  @Override
  public boolean shouldAttackEntity(LivingEntity target, LivingEntity owner) {
    if (!(target instanceof CreeperEntity) && !(target instanceof GhastEntity)) {
      if (target instanceof SilverFoxEntity) {
        SilverFoxEntity entityfox = (SilverFoxEntity) target;

        if (entityfox.isTamed() && entityfox.getOwner() == owner) {
          return false;
        }
      }

      if (target instanceof PlayerEntity && owner instanceof PlayerEntity && !((PlayerEntity) owner).canAttackPlayer((PlayerEntity) target)) {
        return false;
      } else {
        return !(target instanceof AbstractHorseEntity) || !((AbstractHorseEntity) target).isTame();
      }
    } else {
      return false;
    }
  }

  @Override
  public boolean canBeLeashedTo(PlayerEntity player) {
    return !this.isAngry() && super.canBeLeashedTo(player);
  }


  @Override
  @Nonnull
  public AgeableEntity createChild(@Nonnull AgeableEntity ageable) {
    return ModEntities.SILVER_FOX.get().create(ageable.world);
  }

  @Override
  @Nonnull
  public ResourceLocation getLootTable() {
    return new ResourceLocation(MysticalWorld.MODID, "entities/silver_fox");
  }

  public static class SleepGoal extends Goal {
    private final SilverFoxEntity tameable;
    /**
     * If the EntityTameable is sitting.
     */
    private boolean isSleeping;

    public SleepGoal(SilverFoxEntity entityIn) {
      this.tameable = entityIn;
      EnumSet<Flag> mutexes = getMutexFlags();
      mutexes.add(Flag.JUMP);
      setMutexFlags(mutexes);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    @Override
    public boolean shouldExecute() {
      if (!this.tameable.isTamed()) {
        return false;
      } else if (this.tameable.isInWater()) {
        return false;
      } else if (!this.tameable.onGround) {
        return false;
      } else {
        LivingEntity entitylivingbase = this.tameable.getOwner();

        if (entitylivingbase == null) {
          return true;
        } else {
          return (!(this.tameable.getDistanceSq(entitylivingbase) < 144.0D) || entitylivingbase.getRevengeTarget() == null) && this.isSleeping;
        }
      }
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    @Override
    public void startExecuting() {
      this.tameable.getNavigator().clearPath();
      this.tameable.setSleeping(true);
    }

    /**
     * Reset the task's internal state. Called when this task is interrupted by another one
     */
    @Override
    public void resetTask() {
      this.tameable.setSleeping(false);
    }

    /**
     * Sets the sleeping flag.
     */
    public void setSleeping(boolean sleeping) {
      this.isSleeping = sleeping;
    }
  }
}
