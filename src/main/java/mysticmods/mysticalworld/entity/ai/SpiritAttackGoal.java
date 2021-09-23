package mysticmods.mysticalworld.entity.ai;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.TargetGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.pathfinding.Path;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.Hand;

import java.util.EnumSet;

public class SpiritAttackGoal extends TargetGoal {
  protected final CreatureEntity attacker;
  protected int attackTick;
  private final double speedTowardsTarget;
  private final boolean longMemory;
  private Path path;
  private int delayCounter;
  private double targetX;
  private double targetY;
  private double targetZ;
  private long lastCanUseCheck;
  private int failedPathFindingPenalty = 0;
  private boolean canPenalize = false;

  public SpiritAttackGoal(CreatureEntity creature, double speedIn, boolean useLongMemory) {
    super(creature, false, false);
    this.attacker = creature;
    this.speedTowardsTarget = speedIn;
    this.longMemory = useLongMemory;
    this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
  }

  @Override
  public boolean canUse() {
    long i = this.attacker.level.getGameTime();
    if (i - this.lastCanUseCheck < 20L) {
      return false;
    } else {
      this.lastCanUseCheck = i;
      LivingEntity livingentity = this.attacker.getTarget();
      if (livingentity == null) {
        return false;
      } else if (!livingentity.isAlive()) {
        return false;
      } else {
        if (canPenalize) {
          if (--this.delayCounter <= 0) {
            this.path = this.attacker.getNavigation().createPath(livingentity, 0);
            this.delayCounter = 4 + this.attacker.getRandom().nextInt(7);
            return this.path != null;
          } else {
            return true;
          }
        }
        this.path = this.attacker.getNavigation().createPath(livingentity, 0);
        if (this.path != null) {
          return true;
        } else {
          return 5 >= this.attacker.distanceToSqr(livingentity.getX(), livingentity.getY(), livingentity.getZ());
        }
      }
    }
  }

  @Override
  public boolean canContinueToUse() {
    LivingEntity livingentity = this.attacker.getTarget();
    if (livingentity == null) {
      return false;
    } else if (!livingentity.isAlive()) {
      return false;
    } else if (!this.longMemory) {
      return !this.attacker.getNavigation().isDone();
    } else if (!this.attacker.isWithinRestriction(livingentity.blockPosition())) {
      return false;
    } else {
      return !(livingentity instanceof PlayerEntity) || !livingentity.isSpectator() && !((PlayerEntity) livingentity).isCreative();
    }
  }

  @Override
  public void start() {
    this.attacker.getNavigation().moveTo(this.path, this.speedTowardsTarget);
    this.attacker.setAggressive(true);
    this.delayCounter = 0;
  }

  @Override
  public void stop() {
    LivingEntity livingentity = this.attacker.getTarget();
    if (!EntityPredicates.NO_CREATIVE_OR_SPECTATOR.test(livingentity)) {
      this.attacker.setTarget(null);
    }

    this.attacker.setAggressive(false);
    this.attacker.getNavigation().stop();
  }

  @Override
  public void tick() {
    LivingEntity livingentity = this.attacker.getTarget();
    this.attacker.getLookControl().setLookAt(livingentity, 30.0F, 30.0F);
    double d0 = this.attacker.distanceToSqr(livingentity.getX(), livingentity.getY(), livingentity.getZ());
    --this.delayCounter;
    if ((this.longMemory || this.attacker.getSensing().canSee(livingentity)) && this.delayCounter <= 0 && (this.targetX == 0.0D && this.targetY == 0.0D && this.targetZ == 0.0D || livingentity.distanceToSqr(this.targetX, this.targetY, this.targetZ) >= 1.0D || this.attacker.getRandom().nextFloat() < 0.05F)) {
      this.targetX = livingentity.getX();
      this.targetY = livingentity.getY();
      this.targetZ = livingentity.getZ();
      this.delayCounter = 4 + this.attacker.getRandom().nextInt(7);

      if (d0 > 1024.0D) {
        this.delayCounter += 10;
      } else if (d0 > 256.0D) {
        this.delayCounter += 5;
      }

      if (!this.attacker.getNavigation().moveTo(livingentity, this.speedTowardsTarget)) {
        this.delayCounter += 15;
      }
    }

    this.attackTick = Math.max(this.attackTick - 1, 0);
    this.checkAndPerformAttack(livingentity, d0);
  }

  protected void checkAndPerformAttack(LivingEntity enemy, double distToEnemySqr) {
    double d0 = this.getAttackReachSqr(enemy);
    if (distToEnemySqr <= d0 && this.attackTick <= 0) {
      this.attackTick = 20;
      this.attacker.swing(Hand.MAIN_HAND);
      this.attacker.doHurtTarget(enemy);
      this.attacker.remove();
    }

  }

  protected double getAttackReachSqr(LivingEntity attackTarget) {
    return (double) (this.attacker.getBbWidth() * 2.0F * this.attacker.getBbWidth() * 2.0F + attackTarget.getBbWidth());
  }
}
