package epicsquid.mysticalworld.entity;

import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.init.ModEntities;
import net.minecraft.block.*;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.controller.FlyingMovementController;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.IFlyingAnimal;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.pathfinding.FlyingPathNavigator;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public class OwlEntity extends TameableEntity implements IFlyingAnimal {
  public static final ResourceLocation LOOT_TABLE = new ResourceLocation(MysticalWorld.MODID, "entity/owl");

  public float flap;
  public float flapSpeed;
  public float oFlapSpeed;
  public float oFlap;
  public float flapping = 1.0F;

  public OwlEntity(EntityType<? extends TameableEntity> type, World worldIn) {
    super(type, worldIn);
    this.moveController = new FlyingMovementController(this, 15, false);
  }

  @Override
  protected void registerGoals() {
    goalSelector.addGoal(0, new PanicGoal(this, 1.25D));
    goalSelector.addGoal(0, new SwimGoal(this));
    goalSelector.addGoal(1, new LookAtGoal(this, PlayerEntity.class, 8.0F));
    goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
    goalSelector.addGoal(2, new WaterAvoidingRandomFlyingGoal(this, 1D));
  }

  @Override
  protected void registerAttributes() {
    super.registerAttributes();
    this.getAttributes().registerAttribute(SharedMonsterAttributes.FLYING_SPEED);
    this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(6.0D);
    this.getAttribute(SharedMonsterAttributes.FLYING_SPEED).setBaseValue(0.55000000059604645D);
    this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.20000000298023224D);
  }

  /**
   * Returns new PathNavigateGround instance
   */
  @Override
  protected PathNavigator createNavigator(World worldIn) {
    FlyingPathNavigator pathnavigateflying = new FlyingPathNavigator(this, worldIn);
    pathnavigateflying.setCanOpenDoors(false);
    //pathnavigateflying.setCanFloat(true);
    pathnavigateflying.setCanEnterDoors(true);
    return pathnavigateflying;
  }

  @Override
  public float getEyeHeight(Pose pose) {
    return this.getHeight() * 0.6F;
  }

  /**
   * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
   * use this to react to sunlight and start to burn.
   */
  @Override
  public void tick() {
    super.tick();
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

    Vec3d motion = this.getMotion();

    if (!this.onGround && motion.y < 0.0D) {
      this.setMotion(motion.x, motion.y * 0.6D, motion.z);
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

  @Override
  public boolean canSpawn(IWorld worldIn, SpawnReason spawnReasonIn) {
    return super.canSpawn(worldIn, spawnReasonIn);
    /*int i = MathHelper.floor(this.posX);
    int j = MathHelper.floor(this.getBoundingBox().minY);
    int k = MathHelper.floor(this.posZ);
    BlockPos blockpos = new BlockPos(i, j, k);
    Block block = worldIn.getBlockState(blockpos.down()).getBlock();
    return block instanceof LeavesBlock || block == net.minecraft.block.Blocks.GRASS || block instanceof LogBlock || block == Blocks.AIR && worldIn.getLight(blockpos) > 8 && super.canSpawn(worldIn, spawnReasonIn);*/
  }

  public static boolean placement(EntityType<? extends AnimalEntity> p_223316_0_, IWorld worldIn, SpawnReason reason, BlockPos blockpos, Random p_223316_4_) {
    Block block = worldIn.getBlockState(blockpos.down()).getBlock();
    return block instanceof LeavesBlock || block == net.minecraft.block.Blocks.GRASS || block instanceof LogBlock || block == Blocks.AIR && worldIn.getLight(blockpos) > 8;
  }

  // TODO: Fix fall damage
/*  @Override
  public void fall(float distance, float damageMultiplier) {
  }*/

  @Override
  protected void updateFallState(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
  }

  @Override
  @Nullable
  public AgeableEntity createChild(AgeableEntity ageable) {
    return ModEntities.OWL.get().create(ageable.world);
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
  protected void playStepSound(BlockPos pos, BlockState blockIn) {
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
    if (!(entityIn instanceof PlayerEntity)) {
      super.collideWithEntity(entityIn);
    }
  }

  /**
   * Called when the entity is attacked.
   */
  @Override
  public boolean attackEntityFrom(DamageSource source, float amount) {
    if (this.isInvulnerableTo(source)) {
      return false;
    } else {
      return super.attackEntityFrom(source, amount);
    }
  }

  @Override
  public ResourceLocation getLootTable() {
    return LOOT_TABLE;
  }

  public boolean isFlying() {
    return !this.onGround;
  }
}