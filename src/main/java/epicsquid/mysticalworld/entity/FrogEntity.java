package epicsquid.mysticalworld.entity;

import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.init.ModEntities;
import net.minecraft.block.Blocks;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.EnumSet;

public class FrogEntity extends AnimalEntity {
  public float offGround = 0f;

  public FrogEntity(EntityType<? extends FrogEntity> type, World worldIn) {
    super(type, worldIn);
//    setSize(0.5f, 0.5f);
    this.experienceValue = 2;
  }

  public static class GoalFrogJump extends Goal {
    FrogEntity frog;

    public GoalFrogJump(FrogEntity entity) {
      this.frog = entity;
      EnumSet<Flag> mutexes = getMutexFlags();
      mutexes.add(Flag.JUMP);
      setMutexFlags(mutexes);
    }

    @Override
    public boolean shouldExecute() {
      return (frog.onGround || frog.inWater) && (frog.ticksExisted + frog.getEntityId()) % 20 == 0 && frog.rand.nextBoolean();
    }

    @Override
    public void startExecuting() {
      float ang = frog.rand.nextFloat() * (float) Math.PI * 2.0f;
      frog.setMotion(new Vec3d(Math.sin(ang) * 0.25, 0.375 + 0.125 * frog.rand.nextFloat(), Math.cos(ang) * 0.25));
      frog.getLookController().setLookPosition(frog.posX + frog.getMotion().x * 60f, frog.posY, frog.posZ + frog.getMotion().z * 60f, frog.getHorizontalFaceSpeed(), frog.getVerticalFaceSpeed());
    }
  }

  @Override
  public void damageEntity(@Nonnull DamageSource source, float amount) {
    if (!source.getDamageType().equalsIgnoreCase(DamageSource.FALL.getDamageType())) {
      super.damageEntity(source, amount);
    }
  }

  @Override
  public boolean isBreedingItem(@Nonnull ItemStack stack) {
    return stack.getItem() == Items.BROWN_MUSHROOM;
  }

  @Override
  protected void registerGoals() {
    goalSelector.addGoal(0, new SwimGoal(this));
    goalSelector.addGoal(1, new PanicGoal(this, 1.0D));
    goalSelector.addGoal(2, new BreedGoal(this, 0.75D));
    goalSelector.addGoal(3, new TemptGoal(this, 0.75D, Ingredient.fromItems(Blocks.BROWN_MUSHROOM), false));
    goalSelector.addGoal(4, new FollowParentGoal(this, 0.75D));
    goalSelector.addGoal(5, new RandomWalkingGoal(this, 0.5D));
    goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 6.0F));
    goalSelector.addGoal(7, new LookRandomlyGoal(this));
  }

  @Override
  public void tick() {
    super.tick();
    if (onGround && (Math.abs(getMotion().x) > 0.05 || Math.abs(getMotion().z) > 0.05)) {
      this.jump();
    }
    if (!onGround) {
      offGround += 0.25f;
    } else {
      offGround = 0;
    }
  }

  public float getOffGround(float pTicks) {
    if (!onGround) {
      return Math.min(1.0f, offGround + pTicks * 0.25f);
    } else {
      return 0f;
    }
  }

  @Override
  protected void registerAttributes() {
    super.registerAttributes();
    getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(6.0D);
    getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5D);
  }

  @Override
  @Nonnull
  public AgeableEntity createChild(@Nonnull AgeableEntity ageable) {
    return ModEntities.FROG.get().create(ageable.world);
  }

  @Override
  @Nonnull
  public ResourceLocation getLootTable() {
    return new ResourceLocation(MysticalWorld.MODID, "entities/frog");
  }

}
