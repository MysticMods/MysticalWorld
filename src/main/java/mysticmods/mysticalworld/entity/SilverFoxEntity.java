package mysticmods.mysticalworld.entity;

import mysticmods.mysticalworld.MysticalWorld;
import mysticmods.mysticalworld.init.ModEntities;
import mysticmods.mysticalworld.init.ModSounds;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NonTameRandomTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.animal.Rabbit;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.EnumSet;

@SuppressWarnings("NullableProblems")
public class SilverFoxEntity extends TamableAnimal {
  private static final EntityDataAccessor<Float> DATA_HEALTH_ID = SynchedEntityData.defineId(SilverFoxEntity.class, EntityDataSerializers.FLOAT);
  /*  private static final DataParameter<Boolean> SLEEPING = EntityDataManager.createKey(SilverFoxEntity.class, DataSerializers.BOOLEAN);*/

  public SilverFoxEntity(EntityType<? extends SilverFoxEntity> type, Level worldIn) {
    super(type, worldIn);
    setTame(false);
    this.xpReward = 5;
  }

  @Override
  public boolean isFood(@Nonnull ItemStack stack) {
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
    goalSelector.addGoal(0, new FloatGoal(this));
    goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
    goalSelector.addGoal(2, new SitWhenOrderedToGoal(this));
    goalSelector.addGoal(3, new TemptGoal(this, 1.25D, Ingredient.of(Items.CHICKEN), false));
    goalSelector.addGoal(4, new LeapAtTargetGoal(this, 0.4F));
    goalSelector.addGoal(5, new MeleeAttackGoal(this, 1.0D, true));
    goalSelector.addGoal(6, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F, false));
    goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
    goalSelector.addGoal(7, new BreedGoal(this, 1.0D));
    goalSelector.addGoal(7, new RandomLookAroundGoal(this));
    goalSelector.addGoal(8, new RandomStrollGoal(this, 1.0D));
    //goalSelector.addGoal(9, new EntityAIBeg(this, 8.0F));
    targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
    targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
    targetSelector.addGoal(3, new HurtByTargetGoal(this));
    targetSelector.addGoal(4, new NonTameRandomTargetGoal<>(this, Animal.class, false, e -> e instanceof Chicken || e instanceof Rabbit));
  }

  public static AttributeSupplier.Builder attributes() {
    return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 10.0d).add(Attributes.MOVEMENT_SPEED, 0.3d).add(Attributes.ATTACK_DAMAGE, 2d);
  }

  @Override
  public void setTarget(@Nullable LivingEntity entitylivingbaseIn) {
    super.setTarget(entitylivingbaseIn);

    if (entitylivingbaseIn == null) {
      this.setAngry(false);
    } else if (!this.isTame()) {
      this.setAngry(true);
    }
  }

  @Override
  protected void customServerAiStep() {
    this.entityData.set(DATA_HEALTH_ID, this.getHealth());
  }

  @Override
  protected void defineSynchedData() {
    super.defineSynchedData();
    this.entityData.define(DATA_HEALTH_ID, this.getHealth());
    /*    this.dataManager.register(SLEEPING, false);*/
  }

  @Override
  protected float getSoundVolume() {
    return 0.3F;
  }

  @Override
  public void addAdditionalSaveData(CompoundTag compound) {
    super.addAdditionalSaveData(compound);
  }

  @Override
  public void readAdditionalSaveData(CompoundTag compound) {
    super.readAdditionalSaveData(compound);
  }

  @Nullable
  @Override
  protected SoundEvent getAmbientSound() {
    if (this.isAngry()) {
      return ModSounds.FOX_AGGRO.get();
/*    } else if (this.isSleeping()) {
      return ModSounds.FOX_SLEEP.get();*/
    } else {
      return ModSounds.FOX_IDLE.get();
    }
  }

  @Override
  public void aiStep() {
    super.aiStep();

    if (this.isAngry() && this.isSleeping()) {
      this.setInSittingPose(false);
    }

    if (!this.level.isClientSide && this.getTarget() == null && this.isAngry()) {
      this.setAngry(false);
    }
  }

  @Override
  public boolean hurt(DamageSource source, float amount) {
    if (isInvulnerableTo(source)) {
      return false;
    } else {
      Entity entity = source.getEntity();

      if (isSleeping()) {
        setInSittingPose(false);
      }

      this.setOrderedToSit(false);

      if (entity != null && !(entity instanceof Player) && !(entity instanceof Arrow)) {
        amount = (amount + 1.0F) / 2.0F;
      }

      return super.hurt(source, amount);
    }
  }

  @Override
  public boolean doHurtTarget(Entity entityIn) {
    boolean flag = entityIn.hurt(DamageSource.mobAttack(this), (float) ((int) getAttributeValue(Attributes.ATTACK_DAMAGE)));

    if (flag) {
      doEnchantDamageEffects(this, entityIn);
      playSound(ModSounds.FOX_BITE.get(), 1.0f, 1.0f);
    }

    return flag;
  }

  @Override
  public void setTame(boolean tamed) {
    super.setTame(tamed);
    if (tamed) {
      this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(20.0D);
      this.setHealth(20.0F);
    } else {
      this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(10.0D);
    }

    this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(5.0D);
  }

  @SuppressWarnings("Duplicates")
  @Override
  public InteractionResult mobInteract(Player player, InteractionHand hand) {
    ItemStack itemstack = player.getItemInHand(hand);
    Item item = itemstack.getItem();
    if (this.level.isClientSide) {
      boolean flag = this.isOwnedBy(player) || this.isTame() || item == Items.APPLE && !this.isTame();
      return flag ? InteractionResult.CONSUME : InteractionResult.PASS;
    } else {
      if (this.isTame()) {
        if (this.isFood(itemstack) && this.getHealth() < this.getMaxHealth()) {
          if (!player.isCreative()) {
            itemstack.shrink(1);
          }

          FoodProperties food = item.getFoodProperties();
          if (food != null) {
            this.heal((float) food.getNutrition());
            return InteractionResult.SUCCESS;
          }
        }

        /*            if (!(item instanceof DyeItem)) {*/
        InteractionResult actionresulttype = super.mobInteract(player, hand);
        if ((!actionresulttype.consumesAction() || this.isBaby()) && this.isOwnedBy(player)) {
          this.setOrderedToSit(!this.isOrderedToSit());
          this.jumping = false;
          this.navigation.stop();
          this.setTarget(null);
          return InteractionResult.SUCCESS;
        }

        return actionresulttype;
        /*            }*/

/*            DyeColor dyecolor = ((DyeItem)item).getDyeColor();
            if (dyecolor != this.getCollarColor()) {
               this.setCollarColor(dyecolor);
               if (!player.abilities.isCreativeMode) {
                  itemstack.shrink(1);
               }

               return ActionResultType.SUCCESS;
            }*/
      } else if (item == Items.APPLE) {
        if (!player.isCreative()) {
          itemstack.shrink(1);
        }

        if (this.random.nextInt(3) == 0 && !net.minecraftforge.event.ForgeEventFactory.onAnimalTame(this, player)) {
          this.tame(player);
          this.navigation.stop();
          this.setTarget(null);
          this.setOrderedToSit(true);
          this.level.broadcastEntityEvent(this, (byte) 7);
        } else {
          this.level.broadcastEntityEvent(this, (byte) 6);
        }

        return InteractionResult.SUCCESS;
      }

      return super.mobInteract(player, hand);
    }
  }

/*  @Override
  public ActionResultType mobInteract(PlayerEntity player, Hand hand) {
    ItemStack stack = player.getHeldItem(hand);

    if (isTamed()) {
      if (!stack.isEmpty()) {
        if (stack.getItem().isFood() && (stack.getItem() == Items.APPLE || stack.getItem() == Items.GOLDEN_APPLE)) {
          if (dataManager.get(DATA_HEALTH_ID) < 20.0F) {
            Food food = stack.getItem().getFood();
            if (food != null) {
              heal((float) food.getHealing());

              if (!player.isCreative()) {
                stack.shrink(1);
              }
              return ActionResultType.SUCCESS;
            }
          }
        }
      }

      if (this.isOwner(player) && !this.world.isRemote && !this.isBreedingItem(stack)) {
        this.setOrderedToSit(!this.isSitting());
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
          this.setOrderedToSit(true);
          this.setHealth(20.0F);
          this.world.setEntityState(this, (byte) 7);
        } else {
          this.world.setEntityState(this, (byte) 6);
        }
      }
      return ActionResultType.SUCCESS;
    }

    return super.mobInteract(player, hand);
  }*/

  public boolean isAngry() {
    return (this.entityData.get(DATA_FLAGS_ID) & 2) != 0;
  }

  public void setAngry(boolean angry) {
    byte b0 = this.entityData.get(DATA_FLAGS_ID);

    if (angry) {
      this.entityData.set(DATA_FLAGS_ID, (byte) (b0 | 2));
    } else {
      this.entityData.set(DATA_FLAGS_ID, (byte) (b0 & -3));
    }
  }

/*  @Override
  public boolean isSleeping() {
    try {
      return (this.dataManager.get(SLEEPING));
    } catch (ClassCastException e) {
      e.printStackTrace();
      return false;
    }
  }

  @Override
  public void setSleeping(boolean sleeping) {
    this.dataManager.set(SLEEPING, sleeping);
  }*/

  @Override
  public boolean wantsToAttack(LivingEntity target, LivingEntity owner) {
    if (!(target instanceof Creeper) && !(target instanceof Ghast)) {
      if (target instanceof SilverFoxEntity) {
        SilverFoxEntity entityfox = (SilverFoxEntity) target;

        if (entityfox.isTame() && entityfox.getOwner() == owner) {
          return false;
        }
      }

      if (target instanceof Player && owner instanceof Player && !((Player) owner).canHarmPlayer((Player) target)) {
        return false;
      } else {
        return !(target instanceof AbstractHorse) || !((AbstractHorse) target).isTamed();
      }
    } else {
      return false;
    }
  }

  @Override
  public boolean canBeLeashed(Player player) {
    return !this.isAngry() && super.canBeLeashed(player);
  }


  @Override
  public AgeableMob getBreedOffspring(ServerLevel world, AgeableMob ageable) {
    return ModEntities.SILVER_FOX.get().create(world);
  }

  @Override
  @Nonnull
  public ResourceLocation getDefaultLootTable() {
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
      EnumSet<Flag> mutexes = getFlags();
      mutexes.add(Flag.JUMP);
      setFlags(mutexes);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    @Override
    public boolean canUse() {
      if (!this.tameable.isTame()) {
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
          return (!(this.tameable.distanceToSqr(entitylivingbase) < 144.0D) || entitylivingbase.getLastHurtByMob() == null) && this.isSleeping;
        }
      }
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    @Override
    public void start() {
      this.tameable.getNavigation().stop();
      this.tameable.setInSittingPose(true);
    }

    /**
     * Reset the task's internal state. Called when this task is interrupted by another one
     */
    @Override
    public void stop() {
      this.tameable.setInSittingPose(false);
    }

    /**
     * Sets the sleeping flag.
     */
    public void setSleeping(boolean sleeping) {
      this.isSleeping = sleeping;
    }
  }
}
