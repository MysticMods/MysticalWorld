package mysticmods.mysticalworld.entity;

import mysticmods.mysticalworld.MWTags;
import mysticmods.mysticalworld.init.deferred.ModSounds;
import mysticmods.mysticalworld.init.deferred.ModEntities;
import mysticmods.mysticalworld.init.deferred.ModItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class SproutEntity extends Animal {
  public static final EntityDataAccessor<Integer> variant = SynchedEntityData.defineId(SproutEntity.class, EntityDataSerializers.INT);

  public SproutEntity(EntityType<? extends SproutEntity> type, Level world) {
    super(type, world);
    this.xpReward = 3;
  }

  @Override
  public AgeableMob getBreedOffspring(ServerLevel world, AgeableMob ageable) {
    SproutEntity entity = ModEntities.SPROUT.get().create(world);
    if (entity != null) {
      entity.setVariant(entity.getVariant());
    }

    return entity;
  }

  @Override
  protected float getSoundVolume() {
    return 0.2f;
  }

  @Nullable
  @Override
  protected SoundEvent getAmbientSound() {
    if (random.nextInt(45) == 0) {
      return ModSounds.SPROUT_AMBIENT.get();
    }

    return null;
  }

  @Override
  protected void defineSynchedData() {
    super.defineSynchedData();
    this.getEntityData().define(variant, random.nextInt(4));
  }

  private void setVariant(int variant) {
    this.getEntityData().set(SproutEntity.variant, variant);
  }

  private int getVariant() {
    return this.getEntityData().get(variant);
  }

  @Override
  protected void registerGoals() {
    goalSelector.addGoal(0, new FloatGoal(this));
    goalSelector.addGoal(1, new PanicGoal(this, 1.5));
    goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
    goalSelector.addGoal(3, new TemptGoal(this, 1.25D, Ingredient.of(MWTags.Items.AUBERGINE), false));
    goalSelector.addGoal(5, new RandomStrollGoal(this, 1.0));
    goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0f));
    goalSelector.addGoal(7, new RandomLookAroundGoal(this));
  }

  public static AttributeSupplier.Builder attributes() {
    return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 8.0d).add(Attributes.MOVEMENT_SPEED, 0.2d);
  }

  @Override
  @Nonnull
  public ResourceLocation getDefaultLootTable() {
    return new ResourceLocation("mysticalworld:entities/sprout");
  }

  @Override
  public boolean isFood(ItemStack stack) {
    return stack.getItem() == ModItems.AUBERGINE.get();
  }


  @Override
  public float getStandingEyeHeight(Pose pose, EntityDimensions size) {
    return isBaby() ? getBbHeight() : 1.3F;
  }

  @Override
  public void readAdditionalSaveData(CompoundTag compound) {
    super.readAdditionalSaveData(compound);
    getEntityData().set(variant, compound.getInt("variant"));
  }

  @Override
  public void addAdditionalSaveData(CompoundTag compound) {
    super.addAdditionalSaveData(compound);
    compound.putInt("variant", getEntityData().get(variant));
  }

  public static int StringToVariant(String color) {
    switch (color) {
      case "tan":
        return 1;
      case "red":
        return 2;
      case "purple":
        return 3;
      case "green":
        return 0;
      default:
        return 0;
    }
  }

  public static String VariantToString(int variant) {
    switch (variant) {
      case 0:
        return "green";
      case 1:
        return "tan";
      case 2:
        return "red";
      case 3:
        return "purple";
      default:
        return "INVALID";
    }
  }
}
