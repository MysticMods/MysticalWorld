package epicsquid.mysticalworld.entity;

import epicsquid.mysticalworld.MysticalWorld;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLog;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityFlying;
import net.minecraft.entity.passive.EntityShoulderRiding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateFlying;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public class EntityOwl extends EntityShoulderRiding implements EntityFlying {
  public static final ResourceLocation LOOT_TABLE = new ResourceLocation(MysticalWorld.MODID, "entity/owl");

  public float flap;
  public float flapSpeed;
  public float oFlapSpeed;
  public float oFlap;
  public float flapping = 1.0F;

  public EntityOwl(World worldIn) {
    super(worldIn);
    this.setSize(0.5F, 0.9F);
    this.moveHelper = new EntityFlyHelper(this);
  }

  @Override
  protected void initEntityAI() {
    this.tasks.addTask(0, new EntityAIPanic(this, 1.25D));
    this.tasks.addTask(0, new EntityAISwimming(this));
    this.tasks.addTask(1, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
    this.tasks.addTask(2, new EntityAIMate(this, 1.0D));
    this.tasks.addTask(2, new EntityAIWanderAvoidWaterFlying(this, 1D));
  }

  @Override
  protected void applyEntityAttributes() {
    super.applyEntityAttributes();
    this.getAttributeMap().registerAttribute(SharedMonsterAttributes.FLYING_SPEED);
    this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(6.0D);
    this.getEntityAttribute(SharedMonsterAttributes.FLYING_SPEED).setBaseValue(0.55000000059604645D);
    this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.20000000298023224D);
  }

  /**
   * Returns new PathNavigateGround instance
   */
  @Override
  protected PathNavigate createNavigator(World worldIn) {
    PathNavigateFlying pathnavigateflying = new PathNavigateFlying(this, worldIn);
    pathnavigateflying.setCanOpenDoors(false);
    pathnavigateflying.setCanFloat(true);
    pathnavigateflying.setCanEnterDoors(true);
    return pathnavigateflying;
  }

  @Override
  public float getEyeHeight() {
    return this.height * 0.6F;
  }

  /**
   * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
   * use this to react to sunlight and start to burn.
   */
  @Override
  public void onLivingUpdate() {
    super.onLivingUpdate();
    this.calculateFlapping();
  }

  private void calculateFlapping() {
    this.oFlap = this.flap;
    this.oFlapSpeed = this.flapSpeed;
    this.flapSpeed = (float) ((double) this.flapSpeed + (double) (this.onGround ? -1 : 4) * 0.3D);
    this.flapSpeed = MathHelper.clamp(this.flapSpeed, 0.0F, 1.0F);

    if (!this.onGround && this.flapping < 1.0F) {
      this.flapping = 1.0F;
    }

    this.flapping = (float) ((double) this.flapping * 0.9D);

    if (!this.onGround && this.motionY < 0.0D) {
      this.motionY *= 0.6D;
    }

    this.flap += this.flapping * 2.0F;
  }

  /**
   * Checks if the parameter is an item which this animal can be fed to breed it (wheat, carrots or seeds depending on
   * the animal type)
   */
  @Override
  public boolean isBreedingItem(ItemStack stack) {
    return stack.getItem() == Items.RABBIT;
  }

  /**
   * Checks if the entity's current position is a valid location to spawn this entity.
   */
  @Override
  public boolean getCanSpawnHere() {
    int i = MathHelper.floor(this.posX);
    int j = MathHelper.floor(this.getEntityBoundingBox().minY);
    int k = MathHelper.floor(this.posZ);
    BlockPos blockpos = new BlockPos(i, j, k);
    Block block = this.world.getBlockState(blockpos.down()).getBlock();
    return block instanceof BlockLeaves || block == Blocks.GRASS || block instanceof BlockLog || block == Blocks.AIR && this.world.getLight(blockpos) > 8 && super.getCanSpawnHere();
  }

  @Override
  public void fall(float distance, float damageMultiplier) {
  }

  @Override
  protected void updateFallState(double y, boolean onGroundIn, IBlockState state, BlockPos pos) {
  }

  @Override
  @Nullable
  public EntityAgeable createChild(EntityAgeable ageable) {
    return new EntityOwl(ageable.world);
  }

  @Override
  public boolean attackEntityAsMob(Entity entityIn) {
    return entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), 3.0F);
  }

  @Override
  @Nullable
  public SoundEvent getAmbientSound() {
    return null; // TODO: Sounds
  }

  @Override
  protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
    return null; // TODO: Sounds
  }

  @Override
  protected SoundEvent getDeathSound() {
    return null; // TODO: Sounds
  }

  @Override
  protected void playStepSound(BlockPos pos, Block blockIn) {
    this.playSound(SoundEvents.ENTITY_PARROT_STEP, 0.15F, 1.0F);
  }

  @Override
  protected float playFlySound(float p_191954_1_) {
    this.playSound(SoundEvents.ENTITY_PARROT_FLY, 0.15F, 1.0F);
    return p_191954_1_ + this.flapSpeed / 2.0F;
  }

  @Override
  protected boolean makeFlySound() {
    return true;
  }

  /**
   * Gets the pitch of living sounds in living entities.
   */
  @Override
  protected float getSoundPitch() {
    return getPitch(this.rand);
  }

  private static float getPitch(Random random) {
    return (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F;
  }

  @Override
  public SoundCategory getSoundCategory() {
    return SoundCategory.NEUTRAL;
  }

  /**
   * Returns true if this entity should push and be pushed by other entities when colliding.
   */
  @Override
  public boolean canBePushed() {
    return true;
  }

  @Override
  protected void collideWithEntity(Entity entityIn) {
    if (!(entityIn instanceof EntityPlayer)) {
      super.collideWithEntity(entityIn);
    }
  }

  /**
   * Called when the entity is attacked.
   */
  @Override
  public boolean attackEntityFrom(DamageSource source, float amount) {
    if (this.isEntityInvulnerable(source)) {
      return false;
    } else {
      return super.attackEntityFrom(source, amount);
    }
  }

  @Override
  @Nullable
  protected ResourceLocation getLootTable() {
    return LOOT_TABLE;
  }

  public boolean isFlying() {
    return !this.onGround;
  }
}