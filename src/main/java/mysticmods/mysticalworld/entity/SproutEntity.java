package mysticmods.mysticalworld.entity;

import mysticmods.mysticalworld.MWTags;
import mysticmods.mysticalworld.init.ModEntities;
import mysticmods.mysticalworld.init.ModItems;
import mysticmods.mysticalworld.init.ModSounds;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
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

public class SproutEntity extends AnimalEntity {
  public static final DataParameter<Integer> variant = EntityDataManager.defineId(SproutEntity.class, DataSerializers.INT);

  public SproutEntity(EntityType<? extends SproutEntity> type, World world) {
    super(type, world);
    this.xpReward = 3;
  }

  @Override
  @Nonnull
  public AgeableEntity getBreedOffspring(ServerWorld world, AgeableEntity ageable) {
    SproutEntity entity = ModEntities.SPROUT.get().create(ageable.level);
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
    goalSelector.addGoal(0, new SwimGoal(this));
    goalSelector.addGoal(1, new PanicGoal(this, 1.5));
    goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
    goalSelector.addGoal(3, new TemptGoal(this, 1.25D, Ingredient.of(MWTags.Items.AUBERGINE), false));
    goalSelector.addGoal(5, new RandomWalkingGoal(this, 1.0));
    goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 6.0f));
    goalSelector.addGoal(7, new LookRandomlyGoal(this));
  }

  public static AttributeModifierMap.MutableAttribute attributes() {
    return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 8.0d).add(Attributes.MOVEMENT_SPEED, 0.2d);
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
  public float getStandingEyeHeight(Pose pose, EntitySize size) {
    return isBaby() ? getBbHeight() : 1.3F;
  }

  @Override
  public void readAdditionalSaveData(CompoundNBT compound) {
    super.readAdditionalSaveData(compound);
    getEntityData().set(variant, compound.getInt("variant"));
  }

  @Override
  public void addAdditionalSaveData(CompoundNBT compound) {
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
