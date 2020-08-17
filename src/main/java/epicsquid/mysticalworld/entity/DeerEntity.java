package epicsquid.mysticalworld.entity;

import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.init.ModEntities;
import net.minecraft.entity.*;
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
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class DeerEntity extends AnimalEntity {

  public static final DataParameter<Boolean> hasHorns = EntityDataManager.createKey(DeerEntity.class, DataSerializers.BOOLEAN);

  public DeerEntity(EntityType<? extends DeerEntity> type, World world) {
    super(type, world);
//    setSize(1.0f, 1.0f);
    this.experienceValue = 3;
  }

  @Override
  protected void registerData() {
    super.registerData();
    getDataManager().register(hasHorns, rand.nextBoolean());
  }

  @Override
  protected void registerGoals() {
    goalSelector.addGoal(0, new SwimGoal(this));
    goalSelector.addGoal(1, new PanicGoal(this, 1.5D));
    goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
    goalSelector.addGoal(3, new TemptGoal(this, 1.25D, Ingredient.fromItems(Items.WHEAT), false));
    goalSelector.addGoal(4, new FollowParentGoal(this, 1.25D));
    goalSelector.addGoal(5, new RandomWalkingGoal(this, 1.0D));
    goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 6.0F));
    goalSelector.addGoal(7, new LookRandomlyGoal(this));
  }

  @Override
  public void tick() {
    super.tick();
    this.rotationYaw = this.rotationYawHead;
  }

  @Override
  protected void registerAttributes() {
    super.registerAttributes();
    getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(15.0D);
    getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.20000000298023224D);
  }

  @Override
  @Nonnull
  public AgeableEntity createChild(@Nonnull AgeableEntity ageable) {
    return ModEntities.DEER.get().create(ageable.world);
  }

  @Override
  @Nonnull
  public ResourceLocation getLootTable() {
    return new ResourceLocation(MysticalWorld.MODID, "entities/deer");
  }

  @Override
  public float getStandingEyeHeight(Pose pose, EntitySize size) {
    return this.isChild() ? this.getHeight() : 1.3F;
  }

  @Override
  public void readAdditional(@Nonnull CompoundNBT compound) {
    super.readAdditional(compound);
    getDataManager().set(hasHorns, compound.getBoolean("hasHorns"));
  }

  @Override
  public void writeAdditional(@Nonnull CompoundNBT compound) {
    super.writeAdditional(compound);
    compound.putBoolean("hasHorns", getDataManager().get(hasHorns));
  }
}