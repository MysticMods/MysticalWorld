package mysticmods.mysticalworld.entity;

import mysticmods.mysticalworld.MysticalWorld;
import mysticmods.mysticalworld.init.ModEntities;
import mysticmods.mysticalworld.init.ModSounds;
import net.minecraft.block.Blocks;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.EnumSet;

import net.minecraft.entity.ai.goal.Goal.Flag;

public class FrogEntity extends AnimalEntity {
  public float offGround = 0f;

  public FrogEntity(EntityType<? extends FrogEntity> type, World worldIn) {
    super(type, worldIn);
    this.xpReward = 2;
  }

  public static class GoalFrogJump extends Goal {
    FrogEntity frog;

    public GoalFrogJump(FrogEntity entity) {
      this.frog = entity;
      EnumSet<Flag> mutexes = getFlags();
      mutexes.add(Flag.JUMP);
      setFlags(mutexes);
    }

    @Override
    public boolean canUse() {
      return (frog.onGround || frog.wasTouchingWater) && (frog.tickCount + frog.getId()) % 20 == 0 && frog.random.nextBoolean();
    }

    @Override
    public void start() {
      float ang = frog.random.nextFloat() * (float) Math.PI * 2.0f;
      frog.setDeltaMovement(new Vector3d(Math.sin(ang) * 0.25, 0.375 + 0.125 * frog.random.nextFloat(), Math.cos(ang) * 0.25));
      frog.getLookControl().setLookAt(frog.getX() + frog.getDeltaMovement().x * 60f, frog.getY(), frog.getZ() + frog.getDeltaMovement().z * 60f, frog.getMaxHeadYRot(), frog.getMaxHeadXRot());
    }
  }

  @Override
  public void actuallyHurt(@Nonnull DamageSource source, float amount) {
    if (!source.getMsgId().equalsIgnoreCase(DamageSource.FALL.getMsgId())) {
      super.actuallyHurt(source, amount);
    }
  }

  @Override
  public boolean isFood(@Nonnull ItemStack stack) {
    return stack.getItem() == Items.BROWN_MUSHROOM;
  }

  @Override
  protected void registerGoals() {
    goalSelector.addGoal(0, new SwimGoal(this));
    goalSelector.addGoal(1, new PanicGoal(this, 1.0D));
    goalSelector.addGoal(2, new BreedGoal(this, 0.75D));
    goalSelector.addGoal(3, new TemptGoal(this, 0.75D, Ingredient.of(Blocks.BROWN_MUSHROOM), false));
    goalSelector.addGoal(4, new FollowParentGoal(this, 0.75D));
    goalSelector.addGoal(4, new GoalFrogJump(this));
    goalSelector.addGoal(5, new RandomWalkingGoal(this, 0.5D));
    goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 6.0F));
    goalSelector.addGoal(7, new LookRandomlyGoal(this));
  }

  @Override
  public void tick() {
    super.tick();
    if (onGround && (Math.abs(getDeltaMovement().x) > 0.05 || Math.abs(getDeltaMovement().z) > 0.05)) {
      this.jumpFromGround();
    }
    if (!onGround) {
      offGround += 0.25f;
    } else {
      offGround = 0;
    }
  }

  @Nullable
  @Override
  protected SoundEvent getAmbientSound() {
    if (random.nextInt(6) == 0) {
      return ModSounds.FROG_AMBIENT.get();
    }
    return null;
  }

  public float getOffGround(float pTicks) {
    if (!onGround) {
      return Math.min(1.0f, offGround + pTicks * 0.25f);
    } else {
      return 0f;
    }
  }

  public static AttributeModifierMap.MutableAttribute attributes() {
    return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 6.0d).add(Attributes.MOVEMENT_SPEED, 0.5d);
  }

  @Override
  @Nonnull
  public AgeableEntity getBreedOffspring(ServerWorld world, AgeableEntity ageable) {
    return ModEntities.FROG.get().create(world);
  }

  @Override
  @Nonnull
  public ResourceLocation getDefaultLootTable() {
    return new ResourceLocation(MysticalWorld.MODID, "entities/frog");
  }

}
