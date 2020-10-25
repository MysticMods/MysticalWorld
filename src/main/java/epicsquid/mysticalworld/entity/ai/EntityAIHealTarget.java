package epicsquid.mysticalworld.entity.ai;

import epicsquid.mysticalworld.config.ConfigManager;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.pathfinding.Path;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class EntityAIHealTarget extends EntityAIBase {
  private World world;
  protected EntityCreature attacker;
  /**
   * An amount of decrementing ticks that allows the entity to attack once the tick reaches 0.
   */
  protected int attackTick;
  /**
   * The speed with which the mob will approach the target
   */
  double speedTowardsTarget;
  /**
   * When true, the mob will continue chasing its target, even if it can't find a path to them right now.
   */
  boolean longMemory;
  /**
   * The PathEntity of our entity.
   */
  private Path path;
  private int delayCounter;
  private double targetX;
  private double targetY;
  private double targetZ;

  public EntityAIHealTarget(EntityCreature creature, double speedIn) {
    this.attacker = creature;
    this.world = creature.world;
    this.speedTowardsTarget = speedIn;
    this.longMemory = true;
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
    }

    return entitylivingbase instanceof EntityPlayer;
  }

  /**
   * Returns whether an in-progress EntityAIBase should continue executing
   */
  @Override
  public boolean shouldContinueExecuting() {
    EntityLivingBase entitylivingbase = this.attacker.getAttackTarget();

    if (entitylivingbase == null) {
      return false;
    }

    if (entitylivingbase instanceof EntityPlayer) {
      this.path = this.attacker.getNavigator().getPathToEntityLiving(entitylivingbase);
      this.delayCounter = 4 + this.attacker.getRNG().nextInt(7);
      return this.path != null;
    }

    return false;
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
      this.attacker.setAttackTarget(null);
    }

    this.attacker.getNavigator().clearPath();
  }

  /**
   * Keep ticking a continuous task that has already been started
   */
  @Override
  public void updateTask() {
    EntityLivingBase entitylivingbase = this.attacker.getAttackTarget();
    if (entitylivingbase == null) {
      return;
    }

    this.attacker.getLookHelper().setLookPositionWithEntity(entitylivingbase, 30.0F, 30.0F);
    double d0 = this.attacker.getDistanceSq(entitylivingbase.posX, entitylivingbase.getEntityBoundingBox().minY, entitylivingbase.posZ);
    --this.delayCounter;

    this.targetX = entitylivingbase.posX;
    this.targetY = entitylivingbase.getEntityBoundingBox().minY;
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

  protected void checkAndHeal(EntityLivingBase enemy, double distToEnemySqr) {
    double d0 = 1.5;

    if (distToEnemySqr <= d0 && this.attackTick <= 0) {
      this.attackTick = 20;
      this.attacker.swingArm(EnumHand.MAIN_HAND);
      enemy.heal(ConfigManager.hats.antlerHealing);
      enemy.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, ConfigManager.hats.antlerRegenDuration, ConfigManager.hats.antlerRegenAmplifier, false, false));
      this.attacker.setDead();
    }
  }
}

