package epicsquid.mysticalworld.entity;

import javax.annotation.Nonnull;

import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.config.ConfigManager;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
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
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityFrog extends EntityAnimal {
  public static final ResourceLocation LOOT_TABLE = new ResourceLocation(MysticalWorld.MODID, "entity/frog");
  public float offGround = 0f;
  public int timeUntilNextSlime;

  public EntityFrog(@Nonnull World worldIn) {
    super(worldIn);
    setSize(0.5f, 0.5f);
    this.experienceValue = 2;
    this.timeUntilNextSlime = this.rand.nextInt(getSlimeTime()) + getSlimeTime();
  }

  public static int getSlimeTime () {
    return ConfigManager.frog.slimeTime;
  }

  public static boolean shouldDropSlime () {
    return getSlimeTime() != -1;
  }

  public static class EntityAIFrogJump extends EntityAIBase {
    EntityFrog frog;

    public EntityAIFrogJump(EntityFrog entity) {
      this.frog = entity;
      this.setMutexBits(3);
    }

    @Override
    public boolean shouldExecute() {
      return (frog.onGround || frog.inWater) && (frog.ticksExisted + frog.getEntityId()) % 20 == 0 && frog.rand.nextBoolean();
    }

    @Override
    public void startExecuting() {
      float ang = frog.rand.nextFloat() * (float) Math.PI * 2.0f;
      frog.motionX = Math.sin(ang) * 0.25;
      frog.motionZ = Math.cos(ang) * 0.25;
      frog.getLookHelper().setLookPosition(frog.posX + frog.motionX * 60f, frog.posY, frog.posZ + frog.motionZ * 60f, frog.getHorizontalFaceSpeed(),
          frog.getVerticalFaceSpeed());
      frog.motionY = 0.375 + 0.125 * frog.rand.nextFloat();
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
    return stack.getItem() == Item.getItemFromBlock(Blocks.BROWN_MUSHROOM);
  }

  @Override
  protected void initEntityAI() {
    this.tasks.addTask(0, new EntityAISwimming(this));
    this.tasks.addTask(1, new EntityAIPanic(this, 1.0D));
    this.tasks.addTask(2, new EntityAIMate(this, 0.75D));
    this.tasks.addTask(3, new EntityAITempt(this, 0.75D, Item.getItemFromBlock(Blocks.BROWN_MUSHROOM), false));
    this.tasks.addTask(4, new EntityAIFollowParent(this, 0.75D));
    this.tasks.addTask(5, new EntityAIWander(this, 0.5D));
    this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
    this.tasks.addTask(7, new EntityAILookIdle(this));
  }

  @Override
  public void onUpdate() {
    super.onUpdate();
    if (onGround && (Math.abs(motionX) > 0.05 || Math.abs(motionZ) > 0.05)) {
      this.jump();
    }
    if (!onGround) {
      offGround += 0.25f;
    } else {
      offGround = 0;
    }
  }

  @Override
  public void onLivingUpdate() {
    super.onLivingUpdate();

    if (!this.world.isRemote && !this.isChild() && --this.timeUntilNextSlime <= 0 && shouldDropSlime())
    {
        this.playSound(SoundEvents.BLOCK_SLIME_PLACE, 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
        this.dropItem(Items.SLIME_BALL, 1);
        this.timeUntilNextSlime = this.rand.nextInt(getSlimeTime()) + getSlimeTime();
    }
  }

  public int getPersonalTicks() {
    return ticksExisted + getEntityId();
  }

  public float getOffGround(float pTicks) {
    if (!onGround) {
      return Math.min(1.0f, offGround + pTicks * 0.25f);
    } else {
      return 0f;
    }
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
    this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(6.0D);
    this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5D);
  }

  @Override
  @Nonnull
  public EntityAgeable createChild(@Nonnull EntityAgeable ageable) {
    return new EntityFrog(ageable.world);
  }

  @Override
  @Nonnull
  public ResourceLocation getLootTable() {
    return LOOT_TABLE;
  }

}
