package epicsquid.mysticalworld.entity;

import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.config.ConfigManager;
import epicsquid.mysticalworld.entity.ai.EntityAISpiritAttack;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityFlyHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateFlying;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class EntitySpiritBeetle extends EntityBeetle {
  public static ResourceLocation LOOT_TABLE = new ResourceLocation(MysticalWorld.MODID, "entity/beetle");

  public EntitySpiritBeetle(@Nonnull World worldIn) {
    super(worldIn);
    setSize(0.45f, 0.45f);
    this.experienceValue = 3;
    this.moveHelper = new EntityFlyHelper(this);
    this.setDropItemsWhenDead(false);
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
  protected void initEntityAI() {
    this.tasks.addTask(4, new EntityAISpiritAttack(this, 1, true));
    this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
    this.tasks.addTask(7, new EntityAILookIdle(this));
  }

  @Override
  public void onLivingUpdate() {
    this.motionY *= 0.6000000238418579D;

    super.onLivingUpdate();
  }

  @Override
  public void onUpdate() {
    super.onUpdate();

    if (getAttackTarget() != null && getAttackTarget().isDead) {
      this.setDead();
    }
  }

  @Override
  protected void applyEntityAttributes() {
    super.applyEntityAttributes();
    this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
    this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.35D);
    this.getAttributeMap().registerAttribute(SharedMonsterAttributes.FLYING_SPEED);
    this.getEntityAttribute(SharedMonsterAttributes.FLYING_SPEED).setBaseValue(0.9000000059604645D);
  }

  @Override
  @Nullable
  public EntityAgeable createChild(@Nonnull EntityAgeable ageable) {
    return null;
  }

  @Override
  public EnumCreatureAttribute getCreatureAttribute() {
    return EnumCreatureAttribute.ARTHROPOD;
  }

  @Override
  @Nonnull
  public ResourceLocation getLootTable() {
    return LOOT_TABLE;
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
    if (pos.getY() < 0) {
      this.setDead();
    }
  }

  @Override
  public boolean doesEntityNotTriggerPressurePlate() {
    return true;
  }

  @Override
  public boolean attackEntityAsMob(Entity entityIn) {
    DamageSource source = new EntityDamageSource("spirit", this).setDamageBypassesArmor().setMagicDamage();
    return entityIn.attackEntityFrom(source, ConfigManager.hats.maskAttackDamage);
  }

  @Override
  public boolean attackEntityFrom(DamageSource source, float amount) {
    if (source == DamageSource.OUT_OF_WORLD) {
      return super.attackEntityFrom(source, amount);
    }

    return false;
  }
}
