package mysticmods.mysticalworld.entity.ai;

import mysticmods.mysticalworld.config.ConfigManager;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.TargetGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.pathfinding.Path;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.Hand;

import java.util.EnumSet;

import net.minecraft.entity.ai.goal.Goal.Flag;

public class HealTargetGoal extends TargetGoal {
  private final CreatureEntity attacker;
  private int attackTick;
  private final double speedTowardsTarget;
  private final boolean longMemory;
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
    this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
  }

  @Override
  public boolean canUse() {
    LivingEntity target = attacker.getTarget();
    if (target == null) {
      return false;
    }

    return target instanceof PlayerEntity;
  }

  @Override
  public boolean canContinueToUse() {
    LivingEntity target = attacker.getTarget();
    if (target == null) {
      return false;
    }

    if (target instanceof PlayerEntity) {
      this.path = attacker.getNavigation().createPath(target, 0);
      return this.path != null;
    }

    return false;
  }

  @Override
  public void start() {
    this.attacker.getNavigation().moveTo(this.path, this.speedTowardsTarget);
  }

  @Override
  public void stop() {
    LivingEntity entitylivingbase = this.attacker.getTarget();

    if (entitylivingbase instanceof PlayerEntity && (entitylivingbase.isSpectator() || ((PlayerEntity) entitylivingbase).isCreative())) {
      this.attacker.setTarget(null);
    }

    this.attacker.getNavigation().stop();
  }

  @Override
  public void tick() {
    LivingEntity entitylivingbase = this.attacker.getTarget();
    if (entitylivingbase != null) {
      this.attacker.getLookControl().setLookAt(entitylivingbase, 30.0F, 30.0F);
      double d0 = this.attacker.distanceToSqr(entitylivingbase.getX(), entitylivingbase.getBoundingBox().minY, entitylivingbase.getZ());
      --this.delayCounter;

      this.targetX = entitylivingbase.getX();
      this.targetY = entitylivingbase.getBoundingBox().minY;
      this.targetZ = entitylivingbase.getZ();
      this.delayCounter = 4 + this.attacker.getRandom().nextInt(7);

      if (d0 > 1024.0D) {
        this.delayCounter += 10;
      } else if (d0 > 256.0D) {
        this.delayCounter += 5;
      }

      if (!this.attacker.getNavigation().moveTo(entitylivingbase, this.speedTowardsTarget)) {
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
      this.attacker.swing(Hand.MAIN_HAND);
      enemy.heal(ConfigManager.HAT_CONFIG.getAntlerHealing());
      enemy.addEffect(new EffectInstance(Effects.REGENERATION, ConfigManager.HAT_CONFIG.getAntlerRegenDuration(), ConfigManager.HAT_CONFIG.getAntlerRegenAmplifier(), false, false));
      this.attacker.remove();
    }
  }
}
