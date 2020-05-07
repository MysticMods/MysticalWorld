/*package epicsquid.mysticalworld.entity.ai;

import epicsquid.mysticalworld.entity.SilkwormEntity;
import epicsquid.mysticalworld.events.LeafHandler;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.scoreboard.Team;
import net.minecraft.util.math.AxisAlignedBB;

import java.util.EnumSet;
import java.util.List;
import java.util.function.Predicate;

public class TargetLeavesGoal extends Goal {
  protected final SilkwormEntity taskOwner;
  protected boolean shouldCheckSight;
  private int targetUnseenTicks;
  protected ItemEntity target;
  protected int unseenMemoryTicks;
  private final int targetChance;
  private final Predicate<ItemEntity> targetEntitySelector;

  public TargetLeavesGoal(SilkwormEntity creature) {
    this.targetChance = 1;
    this.setMutexFlags(EnumSet.of(Flag.TARGET, Flag.MOVE));
    this.targetEntitySelector = (item) -> LeafHandler.getLeafItems().contains(item.getItem().getItem());
    this.unseenMemoryTicks = 60;
    this.taskOwner = creature;
    this.shouldCheckSight = false;
  }

  @Override
  public boolean shouldContinueExecuting() {
    ItemEntity leaf = this.taskOwner.getLeafTarget();

    if (leaf == null) {
      leaf = this.target;
    }

    if (leaf == null) {
      return false;
    } else if (!leaf.isAlive()) {
      return false;
    } else {
      Team team = this.taskOwner.getTeam();
      Team team1 = leaf.getTeam();

      if (team != null && team1 == team) {
        return false;
      } else {
        double d0 = this.getTargetDistance();

        if (this.taskOwner.getDistanceSq(leaf) > d0 * d0) {
          return false;
        } else {
          if (this.shouldCheckSight) {
            if (this.taskOwner.getEntitySenses().canSee(leaf)) {
              this.targetUnseenTicks = 0;
            } else if (++this.targetUnseenTicks > this.unseenMemoryTicks) {
              return false;
            }
          }

          this.taskOwner.setLeafTarget(leaf);
          return true;
        }
      }
    }
  }

  private double getTargetDistance() {
    IAttributeInstance iattributeinstance = this.taskOwner.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE);
    return iattributeinstance == null ? 16.0D : iattributeinstance.getValue();
  }

  @Override
  public void resetTask() {
    this.taskOwner.setLeafTarget(null);
    this.target = null;
  }

  @Override
  public boolean shouldExecute() {
    if (this.targetChance > 0 && this.taskOwner.getRNG().nextInt(this.targetChance) != 0) {
      return false;
    } else {
      List<ItemEntity> list = this.taskOwner.world.getEntitiesWithinAABB(ItemEntity.class, this.getTargetableArea(this.getTargetDistance()), this.targetEntitySelector);

      if (list.isEmpty()) {
        return false;
      } else {
        list.sort((p_compare_1_, p_compare_2_) -> {
          double d0 = taskOwner.getDistanceSq(p_compare_1_);
          double d1 = taskOwner.getDistanceSq(p_compare_2_);

          if (d0 < d1) {
            return -1;
          } else {
            return d0 > d1 ? 1 : 0;
          }
        });
        this.target = list.get(0);
        return true;
      }
    }
  }

  private AxisAlignedBB getTargetableArea(double targetDistance) {
    return this.taskOwner.getBoundingBox().grow(targetDistance, 4.0D, targetDistance);
  }

  @Override
  public void startExecuting() {
    this.taskOwner.setLeafTarget(this.target);
    this.targetUnseenTicks = 0;
    super.startExecuting();
  }
}*/
