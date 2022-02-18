package mysticmods.mysticalworld.entity;

import mysticmods.mysticalworld.MysticalWorld;
import mysticmods.mysticalworld.init.ModEntities;
import net.minecraft.block.*;
import net.minecraft.world.level.material.Material;
import net.minecraft.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.util.*;
import net.minecraft.core.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Random;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomFlyingGoal;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;

public class OwlEntity extends TamableAnimal implements FlyingAnimal {
  public static final ResourceLocation LOOT_TABLE = new ResourceLocation(MysticalWorld.MODID, "entity/owl");

  public float flap;
  public float flapSpeed;
  public float oFlapSpeed;
  public float oFlap;
  public float flapping = 1.0F;

  public OwlEntity(EntityType<? extends TamableAnimal> type, Level worldIn) {
    super(type, worldIn);
    this.moveControl = new FlyingMoveControl(this, 15, false);
  }

  @Override
  protected void registerGoals() {
    goalSelector.addGoal(0, new PanicGoal(this, 1.25D));
    goalSelector.addGoal(0, new FloatGoal(this));
    goalSelector.addGoal(1, new LookAtPlayerGoal(this, Player.class, 8.0F));
    goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
    goalSelector.addGoal(2, new WaterAvoidingRandomFlyingGoal(this, 1D));
  }

  public static AttributeSupplier.Builder attributes() {
    return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 6.0d).add(Attributes.MOVEMENT_SPEED, 0.2d).add(Attributes.FLYING_SPEED, 0.55d);
  }

  @Override
  protected PathNavigation createNavigation(Level worldIn) {
    FlyingPathNavigation pathnavigateflying = new FlyingPathNavigation(this, worldIn);
    pathnavigateflying.setCanOpenDoors(false);
    //pathnavigateflying.setCanFloat(true);
    pathnavigateflying.setCanPassDoors(true);
    return pathnavigateflying;
  }

  @Override
  public float getEyeHeight(Pose pose) {
    return this.getBbHeight() * 0.6F;
  }

  @Override
  public void tick() {
    super.tick();
    this.calculateFlapping();
  }

  private void calculateFlapping() {
    this.oFlap = this.flap;
    this.oFlapSpeed = this.flapSpeed;
    this.flapSpeed = (float) ((double) this.flapSpeed + (double) (this.onGround ? -1 : 4) * 0.3D);
    this.flapSpeed = Mth.clamp(this.flapSpeed, 0.0F, 1.0F);

    if (!this.onGround && this.flapping < 1.0F) {
      this.flapping = 1.0F;
    }

    this.flapping = (float) ((double) this.flapping * 0.9D);

    Vec3 motion = this.getDeltaMovement();

    if (!this.onGround && motion.y < 0.0D) {
      this.setDeltaMovement(motion.x, motion.y * 0.6D, motion.z);
    }

    this.flap += this.flapping * 2.0F;
  }

  /**
   * Checks if the parameter is an item which this animal can be fed to breed it (wheat, carrots or seeds depending on
   * the animal type)
   */
  @Override
  public boolean isFood(ItemStack stack) {
    return stack.getItem() == Items.RABBIT;
  }

  public static boolean placement(EntityType<? extends Animal> pAnimal, LevelAccessor worldIn, MobSpawnType reason, BlockPos blockpos, Random pRandom) {
    BlockState down = worldIn.getBlockState(blockpos.below());
    Block block = down.getBlock();
    return block instanceof LeavesBlock || block == net.minecraft.world.level.block.Blocks.GRASS || (block instanceof RotatedPillarBlock && down.getMaterial() == Material.WOOD) || block == Blocks.AIR && worldIn.getMaxLocalRawBrightness(blockpos) > 8;
  }

  // TODO: Fix fall damage
/*  @Override
  public void fall(float distance, float damageMultiplier) {
  }*/

  @Override
  protected void checkFallDamage(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
  }

  @Override
  @Nonnull
  public AgableMob getBreedOffspring(ServerLevel world, AgableMob ageable) {
    return ModEntities.OWL.get().create(ageable.level);
  }

  @Override
  public boolean doHurtTarget(Entity entityIn) {
    return entityIn.hurt(DamageSource.mobAttack(this), 3.0F);
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
    this.playSound(SoundEvents.PARROT_STEP, 0.15F, 1.0F);
  }

  @Override
  protected float playFlySound(float p_191954_1_) {
    this.playSound(SoundEvents.PARROT_FLY, 0.15F, 1.0F);
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
  protected float getVoicePitch() {
    return getPitch(this.random);
  }

  private static float getPitch(Random random) {
    return (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F;
  }

  @Override
  public SoundSource getSoundSource() {
    return SoundSource.NEUTRAL;
  }

  /**
   * Returns true if this entity should push and be pushed by other entities when colliding.
   */
  @Override
  public boolean isPushable() {
    return true;
  }

  @Override
  protected void doPush(Entity entityIn) {
    if (!(entityIn instanceof Player)) {
      super.doPush(entityIn);
    }
  }

  /**
   * Called when the entity is attacked.
   */
  @Override
  public boolean hurt(DamageSource source, float amount) {
    if (this.isInvulnerableTo(source)) {
      return false;
    } else {
      return super.hurt(source, amount);
    }
  }

  @Override
  public ResourceLocation getDefaultLootTable() {
    return LOOT_TABLE;
  }

  public boolean isFlying() {
    return !this.onGround;
  }
}