package epicsquid.mysticalworld.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntityLavaCat extends EntityOcelot {
  private static final DataParameter<Boolean> IS_LAVA = EntityDataManager.createKey(EntityOcelot.class, DataSerializers.BOOLEAN);

  private EntityAITempt aiTempt;

  public EntityLavaCat(World worldIn) {
    super(worldIn);
    this.setSize(0.75F, 0.875F);
  }

  @Override
  protected void initEntityAI() {
    this.aiSit = new EntityAISit(this);
    this.aiTempt = new EntityAITempt(this, 0.6D, Items.BLAZE_ROD, true);
    this.tasks.addTask(1, new EntityAISwimming(this));
    this.tasks.addTask(2, this.aiSit);
    this.tasks.addTask(3, this.aiTempt);
    this.tasks.addTask(5, new EntityAIFollowOwner(this, 1.0D, 10.0F, 5.0F));
    this.tasks.addTask(7, new EntityAILeapAtTarget(this, 0.3F));
    this.tasks.addTask(8, new EntityAIOcelotAttack(this));
    this.tasks.addTask(9, new EntityAIMate(this, 0.8D));
    this.tasks.addTask(10, new EntityAIWanderAvoidWater(this, 0.8D, 1.0000001E-5F));
    this.tasks.addTask(11, new EntityAIWatchClosest(this, EntityPlayer.class, 10.0F));
  }

  @Override
  protected void entityInit() {
    super.entityInit();
    this.dataManager.register(IS_LAVA, true);
  }

  @Override
  public void updateAITasks() {
    if (this.getMoveHelper().isUpdating()) {
      double d0 = this.getMoveHelper().getSpeed();

      if (d0 == 0.6D) {
        this.setSneaking(true);
        this.setSprinting(false);
      } else if (d0 == 1.33D) {
        this.setSneaking(false);
        this.setSprinting(true);
      } else {
        this.setSneaking(false);
        this.setSprinting(false);
      }
    } else {
      this.setSneaking(false);
      this.setSprinting(false);
    }
  }

  /**
   * Determines if an entity can be despawned, used on idle far away entities
   */
  @Override
  protected boolean canDespawn() {
    return !this.isTamed() && this.ticksExisted > 2400;
  }

  @Override
  protected void applyEntityAttributes() {
    super.applyEntityAttributes();
    this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
    this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.30000001192092896D);
  }

  @Override
  public void fall(float distance, float damageMultiplier) {
  }

  @Override
  public void writeEntityToNBT(NBTTagCompound compound) {
    super.writeEntityToNBT(compound);
  }

  /**
   * (abstract) Protected helper method to read subclass entity data from NBT.
   */
  @Override
  public void readEntityFromNBT(NBTTagCompound compound) {
    super.readEntityFromNBT(compound);
  }

  @Override
  @Nullable
  protected SoundEvent getAmbientSound() {
    if (this.isTamed()) {
      if (this.isInLove()) {
        return SoundEvents.ENTITY_CAT_PURR;
      } else {
        return this.rand.nextInt(4) == 0 ? SoundEvents.ENTITY_CAT_PURREOW : SoundEvents.ENTITY_CAT_AMBIENT;
      }
    } else {
      return null;
    }
  }

  @Override
  protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
    return SoundEvents.ENTITY_CAT_HURT;
  }

  @Override
  protected SoundEvent getDeathSound() {
    return SoundEvents.ENTITY_CAT_DEATH;
  }

  /**
   * Returns the volume for the sounds this mob makes.
   */
  @Override
  protected float getSoundVolume() {
    return 0.4F;
  }

  @Override
  public boolean attackEntityAsMob(Entity entityIn) {
    return entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), 3.0F);
  }

  @Override
  public void setFire(int seconds) {
  }

  /**
   * Called when the entity is attacked.
   */
  @Override
  public boolean attackEntityFrom(DamageSource source, float amount) {
    if (this.isEntityInvulnerable(source)) {
      return false;
    } else {
      if (this.aiSit != null) {
        this.aiSit.setSitting(false);
      }

      if (source.isFireDamage()) {
        return false;
      }

      return super.attackEntityFrom(source, amount);
    }
  }

  @Override
  @Nullable
  protected ResourceLocation getLootTable() {
    // TODO: This
    return null;
  }

  @Override
  public boolean processInteract(EntityPlayer player, EnumHand hand) {
    ItemStack itemstack = player.getHeldItem(hand);

    if (this.isTamed()) {
      if (this.isOwner(player) && !this.world.isRemote && !this.isBreedingItem(itemstack)) {
        this.aiSit.setSitting(!this.isSitting());
      }
    } else if ((this.aiTempt == null || this.aiTempt.isRunning()) && itemstack.getItem() == Items.BLAZE_ROD && player.getDistanceSq(this) < 9.0D) {
      if (!player.capabilities.isCreativeMode) {
        itemstack.shrink(1);
      }

      if (!this.world.isRemote) {
        if (this.rand.nextInt(3) == 0 && !net.minecraftforge.event.ForgeEventFactory.onAnimalTame(this, player)) {
          this.setTamedBy(player);
          this.playTameEffect(true);
          this.aiSit.setSitting(true);
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

  @Override
  public EntityLavaCat createChild(EntityAgeable ageable) {
    EntityLavaCat entityocelot = new EntityLavaCat(this.world);

    if (this.isTamed()) {
      entityocelot.setOwnerId(this.getOwnerId());
      entityocelot.setTamed(true);
    }

    return entityocelot;
  }

  /**
   * Checks if the parameter is an item which this animal can be fed to breed it (wheat, carrots or seeds depending on
   * the animal type)
   */
  @Override
  public boolean isBreedingItem(ItemStack stack) {
    return stack.getItem() == Items.BLAZE_ROD;
  }

  /**
   * Returns true if the mob is currently able to mate with the specified mob.
   */
  @Override
  public boolean canMateWith(EntityAnimal otherAnimal) {
    if (otherAnimal == this) {
      return false;
    } else if (!this.isTamed()) {
      return false;
    } else if (!(otherAnimal instanceof EntityLavaCat)) {
      return false;
    } else {
      EntityLavaCat entityocelot = (EntityLavaCat) otherAnimal;

      if (!entityocelot.isTamed()) {
        return false;
      } else {
        return this.isInLove() && entityocelot.isInLove();
      }
    }
  }

  @Override
  public int getTameSkin() {
    return 0;
  }

  @Override
  public void setTameSkin(int skinId) {
  }

  public boolean getIsLava () {
    return this.dataManager.get(IS_LAVA);
  }

  public void setIsLava (boolean val) {
    this.dataManager.set(IS_LAVA, val);
    this.dataManager.setDirty(IS_LAVA);
  }

  @Override
  public boolean getCanSpawnHere() {
    return this.world.rand.nextInt(3) != 0;
  }

  @Override
  public boolean isNotColliding() {
    if (this.world.checkNoEntityCollision(this.getEntityBoundingBox(), this) && this.world.getCollisionBoxes(this, this.getEntityBoundingBox()).isEmpty() && !this.world.containsAnyLiquid(this.getEntityBoundingBox())) {
      BlockPos blockpos = new BlockPos(this.posX, this.getEntityBoundingBox().minY, this.posZ);

      if (blockpos.getY() < this.world.getSeaLevel()) {
        return false;
      }
    }

    return false;
  }

  @Override
  protected void setupTamedAI() {
  }

  @Override
  public void onLivingUpdate() {
    super.onLivingUpdate();

    if (world.isRainingAt(getPosition()) && world.canSeeSky(getPosition())) {

    }

    if (getIsLava() && inWater) {
      setIsLava(false);
    } else if (!getIsLava() && isInLava()) {
      setIsLava(true);
    }
  }
}

