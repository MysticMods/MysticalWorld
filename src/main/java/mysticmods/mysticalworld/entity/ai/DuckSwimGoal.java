package mysticmods.mysticalworld.entity.ai;

import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.tags.FluidTags;

import java.util.EnumSet;

// Swim goal copied from Untitled Duck Mod, which is MIT-liccensed and thus compatible
// Original: https://github.com/Paspartout/UntitledDuckMod/blob/1.16/common/src/main/java/net/untitledduckmod/duck/DuckSwimGoal.java
public class DuckSwimGoal extends Goal {
  private final AnimalEntity entity;

  public DuckSwimGoal(AnimalEntity entity) {
    this.entity = entity;
    this.setFlags(EnumSet.of(Goal.Flag.JUMP));
    entity.getNavigation().setCanFloat(true);
  }

  @Override
  public boolean canUse () {
    return entity.isInWater() && entity.getFluidHeight(FluidTags.WATER) > (entity.isBaby() ? 0.1d : 0.2d) || entity.isInLava();
  }

  @Override
  public void tick () {
    if (entity.getRandom().nextFloat() < 0.8f) {
      entity.getJumpControl().jump();
    }
  }
}
