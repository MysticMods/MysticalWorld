package epicsquid.mysticalworld.entity.ai;

import epicsquid.mysticalworld.config.ConfigManager;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.TargetGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.pathfinding.Path;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.Hand;

import java.util.EnumSet;

public class HealTargetGoal extends TargetGoal {
  private CreatureEntity attacker;
  private int attackTick;
  private double speedTowardsTarget;
  private boolean longMemory;
  private Path path;
  private int delayCounter;
  private double targetX;
  private double targetY;
  private double targetZ;

  public HealTargetGoal(CreatureEntity attacker, double speedTowardsTarget) {
    super(attacker, false, false);
    this.attacker = attacker;
    this.speedTowardsTarget = speedTowardsTarget;
    this.longMemory = true;
    this.setMutexFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
  }

  @Override
  public boolean shouldExecute() {
    LivingEntity target = attacker.getAttackTarget();
    if (target == null) {
      return false;
    }

    return target instanceof PlayerEntity;
  }

  @Override
  public boolean shouldContinueExecuting() {
    LivingEntity target = attacker.getAttackTarget();
    if (target == null) {
      return false;
    }

    if (target instanceof PlayerEntity) {
      this.path = attacker.getNavigator().getPathToEntity(target, 0);
      return this.path != null;
    }

    return false;
  }

  @Override
  public void startExecuting() {
    this.attacker.getNavigator().setPath(this.path, this.speedTowardsTarget);
  }

  @Override
  public void resetTask() {
    LivingEntity entitylivingbase = this.attacker.getAttackTarget();

    if (entitylivingbase instanceof PlayerEntity && (entitylivingbase.isSpectator() || ((PlayerEntity) entitylivingbase).isCreative())) {
      this.attacker.setAttackTarget(null);
    }

    this.attacker.getNavigator().clearPath();
  }

  @Override
  public void tick() {
    LivingEntity entitylivingbase = this.attacker.getAttackTarget();
    if (entitylivingbase != null) {
      this.attacker.getLookController().setLookPositionWithEntity(entitylivingbase, 30.0F, 30.0F);
      double d0 = this.attacker.getDistanceSq(entitylivingbase.posX, entitylivingbase.getBoundingBox().minY, entitylivingbase.posZ);
      --this.delayCounter;

      this.targetX = entitylivingbase.posX;
      this.targetY = entitylivingbase.getBoundingBox().minY;
      this.targetZ = entitylivingbase.posZ;
      this.delayCounter = 4 + this.attacker.getRNG().nextInt(7);

      if (d0 > 1024.0D) {
        this.delayCounter += 10;
      } else if (d0 > 256.0D) {
        this.delayCounter += 5;
      }

      if (!this.attacker.getNavigator().tryMoveToEntityLiving(entitylivingbase, this.speedTowardsTarget)) {
        this.delayCounter += 15;
      }
      this.attackTick = Math.max(this.attackTick - 1, 0);
      this.checkAndHeal(entitylivingbase, d0);
    }
  }

  protected void checkAndHeal(LivingEntity enemy, double distToEnemySqr) {
    double d0 = 1.5;

    if (distToEnemySqr <= d0 && this.attackTick <= 0) {
      this.attackTick = 20;
      this.attacker.swingArm(Hand.MAIN_HAND);
      enemy.heal(ConfigManager.HAT_CONFIG.getAntlerHealing());
      enemy.addPotionEffect(new EffectInstance(Effects.REGENERATION, ConfigManager.HAT_CONFIG.getAntlerRegenDuration(), ConfigManager.HAT_CONFIG.getAntlerRegenAmplifier(), false, false));
      this.attacker.remove();
    }
  }
}
