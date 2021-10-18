package mysticmods.mysticalworld.entity;

import mysticmods.mysticalworld.entity.ai.DuckSwimGoal;
import mysticmods.mysticalworld.init.ModEntities;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.tags.ITag;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.Tags;

import javax.annotation.Nullable;

public class DuckEntity extends AnimalEntity {
  public static Ingredient BREEDING_INGREDIENT;

  public float flap;
  public float flapSpeed;
  public float oFlapSpeed;
  public float oFlap;
  public float flapping = 1.0F;

  public DuckEntity(EntityType<? extends AnimalEntity> entityType, World level) {
    super(entityType, level);
    this.setPathfindingMalus(PathNodeType.WATER, 0.0f);
  }

  public static AttributeModifierMap.MutableAttribute attributes() {
    return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 10.0d).add(Attributes.MOVEMENT_SPEED, 0.2d);
  }

  @Override
  public boolean causeFallDamage(float p_225503_1_, float p_225503_2_) {
    return false;
  }

  @Nullable
  @Override
  public AgeableEntity getBreedOffspring(ServerWorld level, AgeableEntity parent) {
    return ModEntities.DUCK.get().create(level);
  }

  @Override
  protected void registerGoals() {
    if (BREEDING_INGREDIENT == null) {
       BREEDING_INGREDIENT = Ingredient.of(Tags.Items.SEEDS);
    }
    goalSelector.addGoal(0, new DuckSwimGoal(this));
    goalSelector.addGoal(1, new PanicGoal(this, 1.6d));
    goalSelector.addGoal(2, new BreedGoal(this, 1.0d));
    goalSelector.addGoal(3, new TemptGoal(this, 1.0d, false, BREEDING_INGREDIENT));
    goalSelector.addGoal(4, new FollowParentGoal(this, 1.0d));
    goalSelector.addGoal(5, new RandomWalkingGoal(this, 1.0D));
    goalSelector.addGoal(5, new RandomSwimmingGoal(this, 1.0d, 120));
    goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 6.0F));
    goalSelector.addGoal(7, new LookRandomlyGoal(this));
  }

  @Override
  public boolean isFood(ItemStack pStack) {
    return BREEDING_INGREDIENT.test(pStack);
  }

  public void aiStep() {
    super.aiStep();
    this.oFlap = this.flap;
    this.oFlapSpeed = this.flapSpeed;
    this.flapSpeed = (float)((double)this.flapSpeed + (double)(this.onGround ? -1 : 4) * 0.3D);
    this.flapSpeed = MathHelper.clamp(this.flapSpeed, 0.0F, 1.0F);
    if (!this.isInWater() && !this.onGround && this.flapping < 1.0F) {
      this.flapping = 1.0F;
    }

    this.flapping = (float)((double)this.flapping * 0.9D);
    Vector3d vector3d = this.getDeltaMovement();
    if (!this.onGround && vector3d.y < 0.0D) {
      this.setDeltaMovement(vector3d.multiply(1.0D, 0.6D, 1.0D));
    }

    this.flap += this.flapping * 2.0F;
  }

  @Override
  protected boolean shouldDespawnInPeaceful() {
    return false;
  }

  @Override
  protected void jumpInLiquid(ITag<Fluid> pFluidTag) {
    this.setDeltaMovement(this.getDeltaMovement().add(0, this.getNavigation().canFloat() ? 0.039 : 0.2, 0.0));
  }
}
