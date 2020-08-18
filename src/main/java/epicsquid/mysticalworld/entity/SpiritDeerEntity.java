package epicsquid.mysticalworld.entity;

import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.entity.ai.HealTargetGoal;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.controller.FlyingMovementController;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.pathfinding.FlyingPathNavigator;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class SpiritDeerEntity extends AnimalEntity {
  public SpiritDeerEntity(EntityType<? extends SpiritDeerEntity> type, World world) {
    super(type, world);
    this.experienceValue = 3;
    this.moveController = new FlyingMovementController(this, 20, true);
  }

  @Override
  protected PathNavigator createNavigator(World worldIn) {
    FlyingPathNavigator pathnavigateflying = new FlyingPathNavigator(this, worldIn);
    pathnavigateflying.setCanOpenDoors(false);
    pathnavigateflying.setCanEnterDoors(true);
    return pathnavigateflying;
  }

  @Override
  protected void registerGoals() {
    goalSelector.addGoal(4, new HealTargetGoal(this, 1.5d));
    goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 6.0F));
    goalSelector.addGoal(7, new LookRandomlyGoal(this));
  }

  @Override
  public boolean onLivingFall(float distance, float damageMultiplier) {
    return false;
  }

  @Override
  public boolean doesEntityNotTriggerPressurePlate() {
    return true;
  }

  @Override
  protected void updateFallState(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
  }

  @Override
  public void tick() {
    super.tick();
    this.rotationYaw = this.rotationYawHead;
  }

  @Override
  protected void registerAttributes() {
    super.registerAttributes();
    getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(15.0D);
    getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.20000000298023224D);
    getAttributes().registerAttribute(SharedMonsterAttributes.FLYING_SPEED);
    getAttribute(SharedMonsterAttributes.FLYING_SPEED).setBaseValue(0.4000000059604645D);
  }

  @Override
  @Nonnull
  public AgeableEntity createChild(@Nonnull AgeableEntity ageable) {
    return null;
  }

  @Override
  @Nonnull
  public ResourceLocation getLootTable() {
    return new ResourceLocation(MysticalWorld.MODID, "entities/spirit_deer");
  }

  @Override
  public float getStandingEyeHeight(Pose pose, EntitySize size) {
    return this.isChild() ? this.getHeight() : 1.3F;
  }
}