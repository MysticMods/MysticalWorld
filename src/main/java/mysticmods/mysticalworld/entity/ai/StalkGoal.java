package mysticmods.mysticalworld.entity.ai;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.Path;

import java.util.EnumSet;

public class StalkGoal extends Goal {
  private final Level world;
  protected PathfinderMob attacker;
  /**
   * An amount of decrementing ticks that allows the entity to attack once the tick reaches 0.
   */
  protected int attackTick;
  /**
   * The speed with which the mob will approach the target
   */
  private final double speedTowardsTarget;
  /**
   * When true, the mob will continue chasing its target, even if it can't find a path to them right now.
   */
  private final boolean longMemory;
  /**
   * The PathEntity of our entity.
   */
  private Path path;
  private int delayCounter;
  private double targetX;
  private double targetY;
  private double targetZ;
  protected final int attackInterval = 20;
  private int failedPathFindingPenalty = 0;
  private final boolean canPenalize = false;

  public StalkGoal(PathfinderMob creature, double speedIn, boolean useLongMemory) {
    this.attacker = creature;
    this.world = creature.level;
    this.speedTowardsTarget = speedIn;
    this.longMemory = useLongMemory;

    EnumSet<Flag> set = getFlags();
    set.add(Flag.TARGET);
    setFlags(set);
  }

  /**
   * Returns whether the EntityAIBase should begin execution.
   */
  @Override
  public boolean canUse() {
    LivingEntity entitylivingbase = this.attacker.getTarget();

    if (entitylivingbase == null) {
      return false;
    } else if (!entitylivingbase.isAlive()) {
      return false;
    } else {
      if (canPenalize) {
        if (--this.delayCounter <= 0) {
          this.path = this.attacker.getNavigation().createPath(entitylivingbase, 32);
          this.delayCounter = 4 + this.attacker.getRandom().nextInt(7);
          return this.path != null;
        } else {
          return true;
        }
      }
      this.path = this.attacker.getNavigation().createPath(entitylivingbase, 32);

      if (this.path != null) {
        return true;
      } else {
        return this.getAttackReachSqr(entitylivingbase) >= this.attacker
            .distanceToSqr(entitylivingbase.getX(), entitylivingbase.getBoundingBox().minY, entitylivingbase.getZ());
      }
    }
  }

  /**
   * Returns whether an in-progress EntityAIBase should continue executing
   */
  @Override
  public boolean canContinueToUse() {
    LivingEntity entitylivingbase = this.attacker.getTarget();

    if (entitylivingbase == null) {
      return false;
    } else if (!entitylivingbase.isAlive()) {
      return false;
    } else if (!this.longMemory) {
      return !this.attacker.getNavigation().isDone();
    } else if (!this.attacker.isWithinRestriction(entitylivingbase.blockPosition())) {
      return false;
    } else {
      return !(entitylivingbase instanceof Player) || !entitylivingbase.isSpectator() && !((Player) entitylivingbase).isCreative();
    }
  }

  /**
   * Execute a one shot task or start executing a continuous task
   */
  @Override
  public void start() {
    this.attacker.getNavigation().moveTo(this.path, this.speedTowardsTarget);
    this.delayCounter = 0;
  }

  /**
   * Reset the task's internal state. Called when this task is interrupted by another one
   */
  @Override
  public void stop() {
    LivingEntity entitylivingbase = this.attacker.getTarget();

    if (entitylivingbase instanceof Player && (entitylivingbase.isSpectator() || ((Player) entitylivingbase).isCreative())) {
      this.attacker.setTarget(null);
    }

    this.attacker.getNavigation().stop();
  }

  /**
   * Keep ticking a continuous task that has already been started
   */
  @Override
  public void tick() {
    super.tick();
    LivingEntity entitylivingbase = this.attacker.getTarget();
    this.attacker.getLookControl().setLookAt(entitylivingbase, 30.0F, 30.0F);
    double d0 = this.attacker.distanceToSqr(entitylivingbase.getX(), entitylivingbase.getBoundingBox().minY, entitylivingbase.getZ());
    --this.delayCounter;

    if ((this.longMemory || this.attacker.getSensing().hasLineOfSight(entitylivingbase)) && this.delayCounter <= 0 && (
        this.targetX == 0.0D && this.targetY == 0.0D && this.targetZ == 0.0D || entitylivingbase.distanceToSqr(this.targetX, this.targetY, this.targetZ) >= 1.0D
            || this.attacker.getRandom().nextFloat() < 0.05F)) {
      this.targetX = entitylivingbase.getX();
      this.targetY = entitylivingbase.getBoundingBox().minY;
      this.targetZ = entitylivingbase.getZ();
      this.delayCounter = 4 + this.attacker.getRandom().nextInt(7);

      if (this.canPenalize) {
        this.delayCounter += failedPathFindingPenalty;
        if (this.attacker.getNavigation().getPath() != null) {
          net.minecraft.world.level.pathfinder.Node finalPathPoint = this.attacker.getNavigation().getPath().getEndNode();
          if (finalPathPoint != null && entitylivingbase.distanceToSqr(finalPathPoint.x, finalPathPoint.y, finalPathPoint.z) < 1)
            failedPathFindingPenalty = 0;
          else
            failedPathFindingPenalty += 10;
        } else {
          failedPathFindingPenalty += 10;
        }
      }

      if (d0 > 1024.0D) {
        this.delayCounter += 10;
      } else if (d0 > 256.0D) {
        this.delayCounter += 5;
      }

      if (!this.attacker.getNavigation().moveTo(entitylivingbase, this.speedTowardsTarget)) {
        this.delayCounter += 15;
      }
    }

    this.attackTick = Math.max(this.attackTick - 1, 0);
  }

  private double getAttackReachSqr(LivingEntity attackTarget) {
    return (this.attacker.getBbWidth() * 2.0F * this.attacker.getBbWidth() * 2.0F + attackTarget.getBbWidth());
  }
}
