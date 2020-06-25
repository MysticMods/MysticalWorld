package epicsquid.mysticalworld.entity.ai;

import epicsquid.mysticalworld.MysticalWorld;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.pathfinding.Path;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;


public class EntityAISpiritAttack extends EntityAIBase {
  private World world;
  protected EntityCreature attacker;
  protected int attackTick;
  double speedTowardsTarget;
  boolean longMemory;
  Path path;
  private int delayCounter;
  private double targetX;
  private double targetY;
  private double targetZ;
  protected final int attackInterval = 20;
  private int failedPathFindingPenalty = 0;

  public EntityAISpiritAttack(EntityCreature creature, double speedIn, boolean useLongMemory) {
    this.attacker = creature;
    this.world = creature.world;
    this.speedTowardsTarget = speedIn;
    this.longMemory = useLongMemory;
    this.setMutexBits(3);
  }

  /**
   * Returns whether the EntityAIBase should begin execution.
   */
  @Override
  public boolean shouldExecute() {
    EntityLivingBase entitylivingbase = this.attacker.getAttackTarget();

    if (entitylivingbase == null) {
      return false;
    } else if (!entitylivingbase.isEntityAlive()) {
      return false;
    } else {
      this.path = this.attacker.getNavigator().getPathToEntityLiving(entitylivingbase);

      if (this.path != null) {
        return true;
      } else {
        return 5 >= this.attacker.getDistanceSq(entitylivingbase.posX, entitylivingbase.getEntityBoundingBox().minY, entitylivingbase.posZ);
      }
    }
  }

  /**
   * Returns whether an in-progress EntityAIBase should continue executing
   */
  @Override
  public boolean shouldContinueExecuting() {
    EntityLivingBase entitylivingbase = this.attacker.getAttackTarget();

    if (entitylivingbase == null) {
      return false;
    } else if (!entitylivingbase.isEntityAlive()) {
      return false;
    } else if (!this.longMemory) {
      return !this.attacker.getNavigator().noPath();
    } else if (!this.attacker.isWithinHomeDistanceFromPosition(new BlockPos(entitylivingbase))) {
      return false;
    } else {
      return !(entitylivingbase instanceof EntityPlayer) || !((EntityPlayer) entitylivingbase).isSpectator() && !((EntityPlayer) entitylivingbase).isCreative();
    }
  }

  /**
   * Execute a one shot task or start executing a continuous task
   */
  @Override
  public void startExecuting() {
    this.attacker.getNavigator().setPath(this.path, this.speedTowardsTarget);
    this.delayCounter = 0;
  }

  /**
   * Reset the task's internal state. Called when this task is interrupted by another one
   */
  @Override
  public void resetTask() {
    EntityLivingBase entitylivingbase = this.attacker.getAttackTarget();

    if (entitylivingbase instanceof EntityPlayer && (((EntityPlayer) entitylivingbase).isSpectator() || ((EntityPlayer) entitylivingbase).isCreative())) {
      this.attacker.setAttackTarget((EntityLivingBase) null);
    }

    this.attacker.getNavigator().clearPath();
  }

  /**
   * Keep ticking a continuous task that has already been started
   */
  @Override
  public void updateTask() {
    EntityLivingBase entitylivingbase = this.attacker.getAttackTarget();
    this.attacker.getLookHelper().setLookPositionWithEntity(entitylivingbase, 30.0F, 30.0F);
    double d0 = this.attacker.getDistanceSq(entitylivingbase.posX, entitylivingbase.getEntityBoundingBox().minY, entitylivingbase.posZ);
    --this.delayCounter;

    if (this.delayCounter <= 0 && (this.targetX == 0.0D && this.targetY == 0.0D && this.targetZ == 0.0D || entitylivingbase.getDistanceSq(this.targetX, this.targetY, this.targetZ) >= 1.0D || this.attacker.getRNG().nextFloat() < 0.05F)) {
      this.targetX = entitylivingbase.posX;
      this.targetY = entitylivingbase.getEntityBoundingBox().minY;
      this.targetZ = entitylivingbase.posZ;
      this.delayCounter = 4 + this.attacker.getRNG().nextInt(7);

      if (d0 > 1024.0D) {
        this.delayCounter += 5;
      } else if (d0 > 256.0D) {
        this.delayCounter += 2;
      }

      if (!this.attacker.getNavigator().tryMoveToEntityLiving(entitylivingbase, this.speedTowardsTarget)) {
        this.delayCounter += 15;
      }
    }

    this.attackTick = Math.max(this.attackTick - 1, 0);
    this.checkAndPerformAttack(entitylivingbase, d0);
  }

  protected void checkAndPerformAttack(EntityLivingBase enemy, double distToEnemySqr) {
    double d0 = this.getAttackReachSqr(enemy);
    MysticalWorld.logger.info(d0);

    if (distToEnemySqr <= d0 && this.attackTick <= 0) {
      this.attackTick = 20;
      this.attacker.attackEntityAsMob(enemy);
      this.attacker.setDead();
    }
  }

  protected double getAttackReachSqr(EntityLivingBase attackTarget) {
    return (double) (this.attacker.width * 2.0F * this.attacker.width * 2.0F + attackTarget.width);
  }
}
