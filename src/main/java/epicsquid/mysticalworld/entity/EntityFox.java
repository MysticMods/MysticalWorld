package epicsquid.mysticalworld.entity;

import com.google.common.base.Predicate;
import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.init.ModSounds;
import net.minecraft.client.audio.SoundManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class EntityFox extends EntityTameable {
  public static final ResourceLocation LOOT_TABLE = new ResourceLocation(MysticalWorld.MODID, "entity/fox");
  private static final DataParameter<Float> DATA_HEALTH_ID = EntityDataManager.createKey(EntityFox.class, DataSerializers.FLOAT);
  private static final DataParameter<Boolean> SLEEPING = EntityDataManager.createKey(EntityFox.class, DataSerializers.BOOLEAN);
  private EntityAISleep aiSleep;

  public EntityFox(@Nonnull World worldIn) {
    super(worldIn);
    setSize(0.75f, 0.75f);
    setTamed(false);
    this.experienceValue = 5;
  }

  @Override
  public boolean isBreedingItem(@Nonnull ItemStack stack) {
    return stack.getItem() == Items.APPLE;
  }

  @Nullable
  @Override
  protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
    return ModSounds.Fox.BARK;
  }

  @Nullable
  @Override
  protected SoundEvent getDeathSound() {
    return ModSounds.Fox.DEATH;
  }

  @Override
  protected void initEntityAI() {
    this.aiSit = new EntityAISit(this);
    this.aiSleep = new EntityAISleep(this);
    this.tasks.addTask(0, new EntityAISwimming(this));
    this.tasks.addTask(2, new EntityAIMate(this, 1.0D));
    this.tasks.addTask(2, this.aiSit);
    this.tasks.addTask(2, this.aiSleep);
    this.tasks.addTask(3, new EntityAITempt(this, 1.25D, Items.APPLE, false));
    this.tasks.addTask(4, new EntityAILeapAtTarget(this, 0.4F));
    this.tasks.addTask(5, new EntityAIAttackMelee(this, 1.0D, true));
    this.tasks.addTask(6, new EntityAIFollowOwner(this, 1.0D, 10.0F, 2.0F));
    this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
    this.tasks.addTask(7, new EntityAIMate(this, 1.0D));
    this.tasks.addTask(7, new EntityAILookIdle(this));
    this.tasks.addTask(8, new EntityAIWander(this, 1.0D));
    //this.tasks.addTask(9, new EntityAIBeg(this, 8.0F));
    this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
    this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
    this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true));
    this.targetTasks.addTask(4, new EntityAITargetNonTamed<>(this, EntityChicken.class, false, (Predicate<Entity>) p_apply_1_ -> p_apply_1_ instanceof EntityChicken));
    this.targetTasks.addTask(4, new EntityAITargetNonTamed<>(this, EntityRabbit.class, false, (Predicate<Entity>) p_apply_1_ -> p_apply_1_ instanceof EntityRabbit));
  }

  @Override
  public void setScaleForAge(boolean child) {
    this.setScale(child ? 0.5f : 1.0f);
  }

  @Override
  protected void applyEntityAttributes() {
    super.applyEntityAttributes();
    if (this.isTamed()) {
      this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.30000001192092896D);
      this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
      this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5D);
    } else {
      this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
      this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.20000000298023224D);
      this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2D);
    }
  }

  @Override
  public void setAttackTarget(@Nullable EntityLivingBase entitylivingbaseIn) {
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
  protected void entityInit() {
    super.entityInit();
    this.dataManager.register(DATA_HEALTH_ID, this.getHealth());
    this.dataManager.register(SLEEPING, false);
  }

  @Override
  protected float getSoundVolume() {
    return 0.3F;
  }

  @Override
  public void writeEntityToNBT(NBTTagCompound compound) {
    super.writeEntityToNBT(compound);
    compound.setBoolean("Angry", this.isAngry());
    compound.setBoolean("Sleeping", this.isSleeping());
  }

  @Override
  public void readEntityFromNBT(NBTTagCompound compound) {
    super.readEntityFromNBT(compound);
    this.setAngry(compound.getBoolean("Angry"));

    if (compound.hasKey("Sleeping")) {
      this.setSleeping(compound.getBoolean("Sleeping"));
      if (this.aiSleep != null) {
        this.aiSleep.setSleeping(this.isSleeping());
      }
    }
  }

  @Nullable
  @Override
  protected SoundEvent getAmbientSound() {
    if (this.isAngry()) {
      return ModSounds.Fox.AGGRO;
    } else if (this.isSleeping()) {
      return ModSounds.Fox.SLEEP;
    } else {
      return ModSounds.Fox.IDLE;
    }
  }

  @Override
  public void onLivingUpdate() {
    super.onLivingUpdate();

    if (this.isAngry() && this.isSleeping()) {
      this.setSleeping(false);
    }

    if (!this.world.isRemote && this.getAttackTarget() == null && this.isAngry()) {
      this.setAngry(false);
    }
  }

  @Override
  public boolean attackEntityFrom(DamageSource source, float amount) {
    if (this.isEntityInvulnerable(source)) {
      return false;
    } else {
      Entity entity = source.getTrueSource();

      if (this.isSleeping()) {
        this.setSleeping(false);
      }

      if (this.aiSit != null) {
        this.aiSit.setSitting(false);
      }

      if (entity != null && !(entity instanceof EntityPlayer) && !(entity instanceof EntityArrow)) {
        amount = (amount + 1.0F) / 2.0F;
      }

      return super.attackEntityFrom(source, amount);
    }
  }

  @Override
  public boolean attackEntityAsMob(Entity entityIn) {
    boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), (float) ((int) this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue()));

    if (flag) {
      this.applyEnchantments(this, entityIn);
      playSound(ModSounds.Fox.BITE, 1.0f, 1.0f);
    }

    return flag;
  }

  @Override
  public void setTamed(boolean tamed) {
    super.setTamed(tamed);

    if (tamed) {
      this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
    } else {
      this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
    }
  }

  @Override
  public boolean processInteract(EntityPlayer player, EnumHand hand) {
    ItemStack itemstack = player.getHeldItem(hand);

    if (this.isTamed()) {
      if (!itemstack.isEmpty()) {
        if (itemstack.getItem() == Items.APPLE || itemstack.getItem() == Items.GOLDEN_APPLE) {
          if (this.dataManager.get(DATA_HEALTH_ID) < 20.0F) {
            this.heal((float) ((ItemFood) itemstack.getItem()).getHealAmount(itemstack));

            if (!player.capabilities.isCreativeMode) {
              itemstack.shrink(1);
            }

            return true;
          }
        }
      }

      if (this.isOwner(player) && !this.world.isRemote && !this.isBreedingItem(itemstack)) {
        this.aiSit.setSitting(!this.isSitting());
        this.isJumping = false;
        this.navigator.clearPath();
        this.setAttackTarget(null);
      }
    } else if (itemstack.getItem() == Items.APPLE && !this.isAngry()) {
      if (!player.capabilities.isCreativeMode) {
        itemstack.shrink(1);
      }

      if (!this.world.isRemote) {
        if (this.rand.nextInt(3) == 0 && !net.minecraftforge.event.ForgeEventFactory.onAnimalTame(this, player)) {
          this.setTamedBy(player);
          this.navigator.clearPath();
          this.setAttackTarget(null);
          this.aiSit.setSitting(true);
          this.setHealth(20.0F);
          this.playTameEffect(true);
          this.world.setEntityState(this, (byte) 7);
        } else {
          this.playTameEffect(false);
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
  public boolean shouldAttackEntity(EntityLivingBase target, EntityLivingBase owner) {
    if (!(target instanceof EntityCreeper) && !(target instanceof EntityGhast)) {
      if (target instanceof EntityFox) {
        EntityFox entityfox = (EntityFox) target;

        if (entityfox.isTamed() && entityfox.getOwner() == owner) {
          return false;
        }
      }

      if (target instanceof EntityPlayer && owner instanceof EntityPlayer && !((EntityPlayer) owner).canAttackPlayer((EntityPlayer) target)) {
        return false;
      } else {
        return !(target instanceof AbstractHorse) || !((AbstractHorse) target).isTame();
      }
    } else {
      return false;
    }
  }

  @Override
  public boolean canBeLeashedTo(EntityPlayer player) {
    return !this.isAngry() && super.canBeLeashedTo(player);
  }


  @Override
  @Nonnull
  public EntityAgeable createChild(@Nonnull EntityAgeable ageable) {
    EntityFox bebe = new EntityFox(ageable.world);
    if (this.isTamed()) {
      bebe.setTamed(true);
      bebe.setOwnerId(this.getOwnerId());
    }
    return bebe;
  }

  @Override
  @Nonnull
  public ResourceLocation getLootTable() {
    return LOOT_TABLE;
  }

  public static class EntityAISleep extends EntityAIBase {
    private final EntityFox tameable;
    /**
     * If the EntityTameable is sitting.
     */
    private boolean isSleeping;

    public EntityAISleep(EntityFox entityIn) {
      this.tameable = entityIn;
      this.setMutexBits(5);
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
        EntityLivingBase entitylivingbase = this.tameable.getOwner();

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
