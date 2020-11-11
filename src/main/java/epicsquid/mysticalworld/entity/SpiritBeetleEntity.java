package epicsquid.mysticalworld.entity;

import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.config.ConfigManager;
import epicsquid.mysticalworld.entity.ai.SpiritAttackGoal;
import epicsquid.mysticalworld.init.ModDamage;
import net.minecraft.block.BlockState;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
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
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nonnull;

public class SpiritBeetleEntity extends AnimalEntity implements IFlyingAnimal {
  public SpiritBeetleEntity(EntityType<? extends SpiritBeetleEntity> type, World worldIn) {
    super(type, worldIn);
    this.experienceValue = 3;
    this.moveController = new FlyingMovementController(this, 20, true);
  }

  @Override
  public float getBlockPathWeight(BlockPos pos, IWorldReader worldIn) {
    BlockState state = worldIn.getBlockState(pos);
    return state.getBlock().isAir(state, worldIn, pos) ? 10.0f : 0f;
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

  public static AttributeModifierMap.MutableAttribute attributes() {
    return LivingEntity.registerAttributes().createMutableAttribute(Attributes.MAX_HEALTH, 15.0d).createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.15d).createMutableAttribute(Attributes.FLYING_SPEED, 0.6d);
  }

  @Override
  @Nonnull
  public AgeableEntity func_241840_a(ServerWorld world, AgeableEntity ageable) {
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
