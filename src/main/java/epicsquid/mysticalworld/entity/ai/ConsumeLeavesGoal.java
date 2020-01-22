package epicsquid.mysticalworld.entity.ai;

import epicsquid.mysticalworld.entity.SilkwormEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.pathfinding.Path;
import net.minecraft.util.math.BlockPos;

import java.util.EnumSet;

public class ConsumeLeavesGoal extends Goal {
  private SilkwormEntity attacker;
  private double speedTowardsTarget;
  private boolean longMemory;
  private Path path;
  private int delayCounter;
  private double targetX;
  private double targetY;
  private double targetZ;
  private int failedPathFindingPenalty = 0;
  private boolean canPenalize = false;

  public ConsumeLeavesGoal(SilkwormEntity creature, double speedIn, boolean useLongMemory) {
    this.attacker = creature;
    this.speedTowardsTarget = speedIn;
    this.longMemory = useLongMemory;
    this.setMutexFlags(EnumSet.of(Flag.TARGET, Flag.MOVE));
  }

  @Override
  public boolean shouldExecute() {
    ItemEntity leaf = this.attacker.getLeafTarget();

    if (leaf == null) {
      return false;
    } else if (!leaf.isAlive()) {
      return false;
    } else {
      if (canPenalize) {
        if (--this.delayCounter <= 0) {
          this.path = this.attacker.getNavigator().func_75494_a(leaf, 1);
          this.delayCounter = 4 + this.attacker.getRNG().nextInt(7);
          return this.path != null;
        } else {
          return true;
        }
      }
      this.path = this.attacker.getNavigator().func_75494_a(leaf, 1);

      if (this.path != null) {
        return true;
      } else {
        return this.getAttackReachSqr(leaf) >= this.attacker.getDistanceSq(leaf.posX, leaf.getBoundingBox().minY, leaf.posZ);
      }
    }
  }

  @Override
  public boolean shouldContinueExecuting() {
    ItemEntity leaf = this.attacker.getLeafTarget();

    if (leaf == null) {
      return false;
    } else if (!leaf.isAlive()) {
      return false;
    } else if (!this.longMemory) {
      return !this.attacker.getNavigator().noPath();
    } else {
      return this.attacker.isWithinHomeDistanceFromPosition(new BlockPos(leaf));
    }
  }

  @Override
  public void startExecuting() {
    this.attacker.getNavigator().setPath(this.path, this.speedTowardsTarget);
    this.delayCounter = 0;
  }

  @Override
  public void resetTask() {
    this.attacker.getNavigator().clearPath();
  }

  @Override
  public void tick() {
    ItemEntity leaf = this.attacker.getLeafTarget();
    this.attacker.getLookController().setLookPositionWithEntity(leaf, 30.0F, 30.0F);
    double d0 = this.attacker.getDistanceSq(leaf.posX, leaf.getBoundingBox().minY, leaf.posZ);
    --this.delayCounter;

    if ((this.longMemory || this.attacker.getEntitySenses().canSee(leaf)) && this.delayCounter <= 0 && (this.targetX == 0.0D && this.targetY == 0.0D && this.targetZ == 0.0D || leaf.getDistanceSq(this.targetX, this.targetY, this.targetZ) >= 1.0D || this.attacker.getRNG().nextFloat() < 0.05F)) {
      this.targetX = leaf.posX;
      this.targetY = leaf.getBoundingBox().minY;
      this.targetZ = leaf.posZ;
      this.delayCounter = 4 + this.attacker.getRNG().nextInt(7);

      if (this.canPenalize) {
        this.delayCounter += failedPathFindingPenalty;
        if (this.attacker.getNavigator().getPath() != null) {
          net.minecraft.pathfinding.PathPoint finalPathPoint = this.attacker.getNavigator().getPath().getFinalPathPoint();
          if (finalPathPoint != null && leaf.getDistanceSq(finalPathPoint.x, finalPathPoint.y, finalPathPoint.z) < 1)
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

      if (!this.attacker.getNavigator().tryMoveToEntityLiving(leaf, this.speedTowardsTarget)) {
        this.delayCounter += 15;
      }
    }

    if (d0 < this.getAttackReachSqr(leaf)) {
      for (int i = 0; i <= leaf.getItem().getCount(); i++) {
        this.attacker.eatLeaves();
      }
      leaf.remove();
    }
  }

  private double getAttackReachSqr(ItemEntity attackTarget) {
    return (double) (this.attacker.getWidth() * this.attacker.getWidth() + attackTarget.getWidth());
  }
}
