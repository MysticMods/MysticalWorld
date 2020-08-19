package epicsquid.mysticalworld.entity;

import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.entity.ai.EntityAIHealTarget;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityFlyHelper;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateFlying;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class EntitySpiritDeer extends EntityAnimal {
  public static final ResourceLocation LOOT_TABLE = new ResourceLocation(MysticalWorld.MODID, "entity/deer");

  public static final DataParameter<Boolean> hasHorns = EntityDataManager.createKey(EntitySpiritDeer.class, DataSerializers.BOOLEAN);

  public EntitySpiritDeer(@Nonnull World world) {
    super(world);
    setSize(0.8f, 0.8f);
    this.experienceValue = 3;
    this.moveHelper = new EntityFlyHelper(this);
    this.setDropItemsWhenDead(false);
  }

  @Override
  protected void entityInit() {
    super.entityInit();
    this.getDataManager().register(hasHorns, true);
  }

  @Override
  protected PathNavigate createNavigator(World worldIn) {
    PathNavigateFlying pathnavigateflying = new PathNavigateFlying(this, worldIn);
    pathnavigateflying.setCanOpenDoors(false);
    pathnavigateflying.setCanFloat(true);
    pathnavigateflying.setCanEnterDoors(true);
    return pathnavigateflying;
  }

  @Override
  public boolean canBePushed() {
    return false;
  }

  @Override
  protected void collideWithEntity(Entity entityIn) {
  }

  @Override
  protected void collideWithNearbyEntities() {
  }

  @Override
  protected boolean canTriggerWalking() {
    return false;
  }

  @Override
  public void fall(float distance, float damageMultiplier) {
  }

  @Override
  protected void updateFallState(double y, boolean onGroundIn, IBlockState state, BlockPos pos) {
  }

  @Override
  public boolean doesEntityNotTriggerPressurePlate() {
    return true;
  }

  @Override
  protected void initEntityAI() {
    this.tasks.addTask(4, new EntityAIHealTarget(this, 1.5D));
    this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
    this.tasks.addTask(7, new EntityAILookIdle(this));
  }

  @Override
  public void onUpdate() {
    super.onUpdate();
    this.rotationYaw = this.rotationYawHead;
  }

  @Override
  public boolean attackEntityFrom(DamageSource source, float amount) {
    if (source == DamageSource.OUT_OF_WORLD) {
      return super.attackEntityFrom(source, amount);
    }

    return false;
  }

  @Override
  public void setScaleForAge(boolean child) {
    this.setScale(child ? 0.5f : 1.0f);
  }

  @Override
  protected void applyEntityAttributes() {
    super.applyEntityAttributes();
    this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(15.0D);
    this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.20000000298023224D);
    this.getAttributeMap().registerAttribute(SharedMonsterAttributes.FLYING_SPEED);
    this.getEntityAttribute(SharedMonsterAttributes.FLYING_SPEED).setBaseValue(0.4000000059604645D);
  }

  @Override
  public EntityAgeable createChild(@Nonnull EntityAgeable ageable) {
    return null;
  }

  @Override
  @Nonnull
  public ResourceLocation getLootTable() {
    return LOOT_TABLE;
  }

  @Override
  public float getEyeHeight() {
    return this.isChild() ? this.height : 1.3F;
  }

  @Override
  public void readEntityFromNBT(@Nonnull NBTTagCompound compound) {
    super.readEntityFromNBT(compound);
    getDataManager().set(hasHorns, compound.getBoolean("hasHorns"));
    getDataManager().setDirty(hasHorns);
  }

  @Override
  public void writeEntityToNBT(@Nonnull NBTTagCompound compound) {
    super.writeEntityToNBT(compound);
    compound.setBoolean("hasHorns", getDataManager().get(hasHorns));
  }
}