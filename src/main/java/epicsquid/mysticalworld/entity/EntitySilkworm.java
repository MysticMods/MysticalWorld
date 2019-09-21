package epicsquid.mysticalworld.entity;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntitySilkworm extends EntityAnimal {
  private static final DataParameter<Integer> SIZE = EntityDataManager.createKey(EntitySilkworm.class, DataSerializers.VARINT);
  public static final int MAX_SIZE = 120;

  public EntitySilkworm(World worldIn) {
    super(worldIn);
    this.experienceValue = 3;
    this.setSize(0.8F, 0.6F);
  }

  @Nullable
  @Override
  public EntityAgeable createChild(EntityAgeable ageable) {
    return new EntitySilkworm(this.world);
  }

  @Override
  protected void initEntityAI() {
    this.tasks.addTask(1, new EntityAISwimming(this));
    this.tasks.addTask(3, new EntityAIWander(this, 0.8));
    this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 1.0F));
    this.tasks.addTask(8, new EntityAILookIdle(this));
  }

  @Override
  protected void entityInit() {
    super.entityInit();
    this.dataManager.register(SIZE, 0);
  }

  public int getSize() {
    return this.dataManager.get(SIZE);
  }

  public void setSize(int size) {
    this.dataManager.set(SIZE, size);
    this.dataManager.setDirty(SIZE);
  }

  public void incSize() {
    if (!this.isChild()) {
      this.dataManager.set(SIZE, getSize() + 1);
      this.dataManager.setDirty(SIZE);
    }
  }

  @Override
  public float getEyeHeight() {
    return 0.1F;
  }

  @Override
  protected void applyEntityAttributes() {
    super.applyEntityAttributes();
    this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(8.0D);
    this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
  }

  // TODO: prevent crop trampling

  @Override
  protected SoundEvent getAmbientSound() {
    return SoundEvents.ENTITY_ENDERMITE_AMBIENT;
  }

  @Override
  protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
    return SoundEvents.ENTITY_ENDERMITE_HURT;
  }

  @Override
  protected SoundEvent getDeathSound() {
    return SoundEvents.ENTITY_ENDERMITE_DEATH;
  }

  @Override
  protected void playStepSound(BlockPos pos, Block blockIn) {
    this.playSound(SoundEvents.ENTITY_ENDERMITE_STEP, 0.15F, 1.0F);
  }

  @Override
  @Nullable
  protected ResourceLocation getLootTable() {
    // TODO
    return null;
  }

  @Override
  public void readEntityFromNBT(NBTTagCompound compound) {
    super.readEntityFromNBT(compound);
    setSize(compound.getInteger("Size"));
  }

  @Override
  public void writeEntityToNBT(NBTTagCompound compound) {
    super.writeEntityToNBT(compound);
    compound.setInteger("Size", getSize());
  }

  @Override
  public void onUpdate() {
    super.onUpdate();
  }

  @Override
  public void setRenderYawOffset(float offset) {
    super.setRenderYawOffset(offset);
  }

  @Override
  public double getYOffset() {
    return super.getYOffset();
    //return 0.1D;
  }

  @Override
  public void onLivingUpdate() {
    super.onLivingUpdate();

    if (this.world.isRemote) {
      /*for (int i = 0; i < 2; ++i) {
        this.world.spawnParticle(EnumParticleTypes.PORTAL, this.posX + (this.rand.nextDouble() - 0.5D) * (double) this.width, this.posY + this.rand.nextDouble() * (double) this.height, this.posZ + (this.rand.nextDouble() - 0.5D) * (double) this.width, (this.rand.nextDouble() - 0.5D) * 2.0D, -this.rand.nextDouble(), (this.rand.nextDouble() - 0.5D) * 2.0D);
      */
    }

    if (!this.world.isRemote) {
      if (this.rand.nextInt(20) == 0) {
        int size = getSize();
        if (size == MAX_SIZE) {
          setSize(0);
          // TODO: poop out cocoon
        } else {
          incSize();
        }
      }
    }
  }

  @Override
  public EnumCreatureAttribute getCreatureAttribute() {
    return EnumCreatureAttribute.ARTHROPOD;
  }
}

