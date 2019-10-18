package epicsquid.mysticalworld.entity;

import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.RegistryManager;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class BeetleEntity extends AnimalEntity {

  public BeetleEntity(EntityType<? extends BeetleEntity> type, World worldIn) {
    super(type, worldIn);
//    setSize(0.75f, 0.75f);
    this.experienceValue = 3;
  }

  @Override
  protected void registerGoals() {
    goalSelector.addGoal(0, new SwimGoal(this));
    goalSelector.addGoal(1, new PanicGoal(this, 1.5D));
    goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
    goalSelector.addGoal(3, new TemptGoal(this, 1.25D, Ingredient.fromItems(Items.MELON), false));
    goalSelector.addGoal(4, new FollowParentGoal(this, 1.25D));
    goalSelector.addGoal(5, new RandomWalkingGoal(this, 1.0D));
    goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 6.0F));
    goalSelector.addGoal(7, new LookRandomlyGoal(this));
  }

  @Override
  public boolean isBreedingItem(@Nonnull ItemStack stack) {
    return stack.getItem() == Items.MELON;
  }

//	@Override
//	public void setScaleForAge(boolean child) {
//		this.setScale(child ? 0.5f : 1.0f);
//	}

  @Override
  public boolean isAIDisabled() {
    return false;
  }


  @Override
  protected void registerAttributes() {
    super.registerAttributes();
    getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
    getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.15D);
  }

  @Override
  public AgeableEntity createChild(@Nonnull AgeableEntity ageable) {
    return RegistryManager.BEETLE.create(ageable.world);
  }

  @Override
  @Nonnull
  public ResourceLocation getLootTable() {
    return new ResourceLocation(MysticalWorld.MODID, "entities/beetle");
  }

}
