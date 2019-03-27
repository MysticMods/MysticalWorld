package epicsquid.mysticalworld.entity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.init.ModSounds;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityFox extends EntityAnimal {

  public EntityFox(@Nonnull World worldIn) {
    super(worldIn);
    setSize(0.75f, 0.75f);
    this.experienceValue = 5;
  }

  @Override
  public boolean isBreedingItem(@Nonnull ItemStack stack) {
    return stack.getItem() == Items.CHICKEN;
  }

  @Nullable
  @Override
  protected SoundEvent getAmbientSound() {
    return ModSounds.Fox.IDLE;
  }

  @Nullable
  @Override
  protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
    return ModSounds.Fox.BARK;
  }

  @Nullable
  @Override
  protected SoundEvent getDeathSound() {
    return ModSounds.Fox.DEATH;
  }

  @Override
  protected void initEntityAI() {
    this.tasks.addTask(0, new EntityAISwimming(this));
    this.tasks.addTask(1, new EntityAIPanic(this, 1.5D));
    this.tasks.addTask(2, new EntityAIMate(this, 1.0D));
    this.tasks.addTask(3, new EntityAITempt(this, 1.25D, Items.CHICKEN, false));
    this.tasks.addTask(4, new EntityAIFollowParent(this, 1.25D));
    this.tasks.addTask(5, new EntityAIWander(this, 1.0D));
    this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
    this.tasks.addTask(7, new EntityAILookIdle(this));
  }

  @Override
  public void setScaleForAge(boolean child) {
    this.setScale(child ? 0.5f : 1.0f);
  }

  @Override
  public boolean isAIDisabled() {
    return false;
  }

  @Override
  protected void applyEntityAttributes() {
    super.applyEntityAttributes();
    this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
    this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.20000000298023224D);
  }

  @Override
  @Nonnull
  public EntityAgeable createChild(@Nonnull EntityAgeable ageable) {
    return new EntityFox(ageable.world);
  }

  @Override
  @Nonnull
  public ResourceLocation getLootTable() {
    return new ResourceLocation(MysticalWorld.MODID + ":fox");
  }

}
