package epicsquid.mysticalworld.entity;

import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.config.ConfigManager;
import epicsquid.mysticalworld.entity.ai.SpiritAttackGoal;
import epicsquid.mysticalworld.init.ModDamage;
import net.minecraft.block.BlockState;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.controller.FlyingMovementController;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.IFlyingAnimal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.pathfinding.FlyingPathNavigator;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class SpiritBeetleEntity extends AnimalEntity implements IFlyingAnimal {
  public SpiritBeetleEntity(EntityType<? extends SpiritBeetleEntity> type, World worldIn) {
    super(type, worldIn);
    this.experienceValue = 3;
    this.moveController = new FlyingMovementController(this, 20, true);
  }

  @Override
  public float getBlockPathWeight(BlockPos pos, IWorldReader worldIn) {
    return worldIn.getBlockState(pos).isAir(world, pos) ? 10.0F : 0.0F;
  }

  @Override
  protected PathNavigator createNavigator(World worldIn) {
    FlyingPathNavigator pathnavigateflying = new FlyingPathNavigator(this, worldIn) {
      @Override
      public boolean canEntityStandOnPos(BlockPos pos) {
        return true;
      }
    };
    pathnavigateflying.setCanOpenDoors(false);
    pathnavigateflying.setCanEnterDoors(true);
    return pathnavigateflying;
  }

  @Override
  protected void registerGoals() {
    goalSelector.addGoal(4, new SpiritAttackGoal(this, 1.8, true));
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
    if (pos.getY() < 0) {
      this.remove();
    }
  }

  @Override
  protected void registerAttributes() {
    super.registerAttributes();
    getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
    getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.15D);
    getAttributes().registerAttribute(SharedMonsterAttributes.FLYING_SPEED);
    getAttribute(SharedMonsterAttributes.FLYING_SPEED).setBaseValue(0.6000000059604645D);
  }

  @Override
  @Nullable
  public AgeableEntity createChild(@Nonnull AgeableEntity ageable) {
    return null;
  }

  @Override
  @Nonnull
  public ResourceLocation getLootTable() {
    return new ResourceLocation(MysticalWorld.MODID, "entities/spirit_beetle");
  }

  @Override
  public boolean attackEntityAsMob(Entity entityIn) {
    return entityIn.attackEntityFrom(ModDamage.causeSpiritDamage(this), ConfigManager.HAT_CONFIG.getMaskAttackDamage());
  }
}
