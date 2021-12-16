package mysticmods.mysticalworld.entity;

import mysticmods.mysticalworld.MysticalWorld;
import mysticmods.mysticalworld.init.ModEntities;
import mysticmods.mysticalworld.init.ModSounds;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class DeerEntity extends AnimalEntity {

  public static final DataParameter<Boolean> hasHorns = EntityDataManager.defineId(DeerEntity.class, DataSerializers.BOOLEAN);

  public DeerEntity(EntityType<? extends DeerEntity> type, World world) {
    super(type, world);
    this.xpReward = 3;
  }

  @Override
  protected void defineSynchedData() {
    super.defineSynchedData();
    getEntityData().define(hasHorns, random.nextBoolean());
  }

  @Override
  protected void registerGoals() {
    goalSelector.addGoal(0, new SwimGoal(this));
    goalSelector.addGoal(1, new PanicGoal(this, 1.5D));
    goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
    goalSelector.addGoal(3, new TemptGoal(this, 1.25D, Ingredient.of(Items.WHEAT), false));
    goalSelector.addGoal(4, new FollowParentGoal(this, 1.25D));
    goalSelector.addGoal(5, new RandomWalkingGoal(this, 1.0D));
    goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 6.0F));
    goalSelector.addGoal(7, new LookRandomlyGoal(this));
  }

  @Override
  public void tick() {
    super.tick();
    this.yRot = this.yHeadRot;
  }

  public static AttributeModifierMap.MutableAttribute attributes() {
    return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 15.0d).add(Attributes.MOVEMENT_SPEED, 0.2d);
  }

  @Override
  @Nonnull
  public AgeableEntity getBreedOffspring(ServerWorld world, AgeableEntity ageable) {
    return ModEntities.DEER.get().create(ageable.level);
  }

  @Override
  @Nonnull
  public ResourceLocation getDefaultLootTable() {
    return new ResourceLocation(MysticalWorld.MODID, "entities/deer");
  }

  @Override
  public float getStandingEyeHeight(Pose pose, EntitySize size) {
    return this.isBaby() ? this.getBbHeight() : 1.3F;
  }

  @Nullable
  @Override
  protected SoundEvent getAmbientSound() {
    if (random.nextInt(15) == 0) {
      return ModSounds.DEER_AMBIENT.get();
    }
    return null;
  }

  @Override
  public void readAdditionalSaveData(@Nonnull CompoundNBT compound) {
    super.readAdditionalSaveData(compound);
    getEntityData().set(hasHorns, compound.getBoolean("hasHorns"));
  }

  @Override
  public void addAdditionalSaveData(@Nonnull CompoundNBT compound) {
    super.addAdditionalSaveData(compound);
    compound.putBoolean("hasHorns", getEntityData().get(hasHorns));
  }
}