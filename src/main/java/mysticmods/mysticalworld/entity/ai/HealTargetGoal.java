package mysticmods.mysticalworld.entity.ai;

import mysticmods.mysticalworld.config.ConfigManager;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.pathfinder.Path;

import java.util.EnumSet;

public class HealTargetGoal extends TargetGoal {
  private final PathfinderMob attacker;
  private int attackTick;
  private final double speedTowardsTarget;
  private final boolean longMemory;
  private Path path;
  private int delayCounter;
  private double targetX;
  private double targetY;
  private double targetZ;

  public HealTargetGoal(PathfinderMob attacker, double speedTowardsTarget) {
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

    return target instanceof Player;
  }

  @Override
  public boolean canContinueToUse() {
    LivingEntity target = attacker.getTarget();
    if (target == null) {
      return false;
    }

    if (target instanceof Player) {
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

    if (entitylivingbase instanceof Player && (entitylivingbase.isSpectator() || ((Player) entitylivingbase).isCreative())) {
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
      this.attacker.swing(InteractionHand.MAIN_HAND);
      enemy.heal(ConfigManager.HAT_CONFIG.getAntlerHealing());
      enemy.addEffect(new MobEffectInstance(MobEffects.REGENERATION, ConfigManager.HAT_CONFIG.getAntlerRegenDuration(), ConfigManager.HAT_CONFIG.getAntlerRegenAmplifier(), false, false));
      this.attacker.remove();
    }
  }
}
