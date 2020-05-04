package epicsquid.mysticalworld.entity;

import epicsquid.mysticallib.network.PacketHandler;
import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.capability.PlayerShoulderCapability;
import epicsquid.mysticalworld.capability.PlayerShoulderCapabilityProvider;
import epicsquid.mysticalworld.network.MessagePlayerShoulderUpdate;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityShoulderRiding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class EntityBeetle extends EntityShoulderRiding {
  public static ResourceLocation LOOT_TABLE = new ResourceLocation(MysticalWorld.MODID, "entity/beetle");

  public EntityBeetle(@Nonnull World worldIn) {
    super(worldIn);
    setSize(0.75f, 0.75f);
    this.experienceValue = 3;
  }

  @Override
  protected void initEntityAI() {
    this.aiSit = new EntityAISit(this);
    this.tasks.addTask(0, new EntityAISwimming(this));
    this.tasks.addTask(1, new EntityAIPanic(this, 1.5D));
    this.tasks.addTask(2, this.aiSit);
    this.tasks.addTask(2, new EntityAIMate(this, 1.0D));
    this.tasks.addTask(3, new EntityAITempt(this, 1.25D, Items.MELON, false));
    //this.tasks.addTask(3, new EntityAIHopOnOwnersShoulder(this));
    this.tasks.addTask(4, new EntityAIFollowParent(this, 1.25D));
    this.tasks.addTask(5, new EntityAIWander(this, 1.0D));
    this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
    this.tasks.addTask(6, new EntityAIFollowOwner(this, 1.0D, 10.0F, 2.0F));
    this.tasks.addTask(7, new EntityAILookIdle(this));
  }

  @Override
  public boolean isBreedingItem(@Nonnull ItemStack stack) {
    return stack.getItem() == Items.MELON;
  }

  @Override
  public void setScaleForAge(boolean child) {
    this.setScale(child ? 0.5f : 1.0f);
  }

  @Override
  protected void applyEntityAttributes() {
    super.applyEntityAttributes();
    this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
    this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.15D);
  }

  @Override
  public EntityAgeable createChild(@Nonnull EntityAgeable ageable) {
    return new EntityBeetle(ageable.world);
  }

  @Override
  public boolean processInteract(EntityPlayer player, EnumHand hand) {
    ItemStack itemstack = player.getHeldItem(hand);

    if (this.isTamed()) {
      if (this.isOwner(player) && !this.world.isRemote && !this.isBreedingItem(itemstack)) {
        if (itemstack.isEmpty() && player.isSneaking()) {
          if (!world.isRemote) {
            // Try some shoulder surfing!
            PlayerShoulderCapability cap = player.getCapability(PlayerShoulderCapabilityProvider.PLAYER_SHOULDER_CAPABILITY, null);
            if (cap != null) {
              if (!cap.isShouldered()) {
                setSitting(false);
                this.setSneaking(false);
                cap.shoulder(this);
                player.swingArm(EnumHand.MAIN_HAND);
                MessagePlayerShoulderUpdate message = new MessagePlayerShoulderUpdate(player, cap);
                PacketHandler.sendToAllTracking(message, this);
                this.setDead();
                return true;
              }
            }
          }
        } else {
          this.aiSit.setSitting(!this.isSitting());
          this.isJumping = false;
          this.navigator.clearPath();
          this.setAttackTarget(null);
        }
      } else if (this.isOwner(player) && !itemstack.isEmpty()) {
        boolean flag = this.handleEating(player, itemstack);
        if (flag) {
          if (!player.capabilities.isCreativeMode) {
            itemstack.shrink(1);
          }
        }

        return flag;
      }
    } else if (itemstack.getItem() == Items.MELON_SEEDS) {
      if (!player.capabilities.isCreativeMode) {
        itemstack.shrink(1);
      }

      if (!this.world.isRemote) {
        if (this.rand.nextInt(3) == 0 && !net.minecraftforge.event.ForgeEventFactory.onAnimalTame(this, player)) {
          this.setTamedBy(player);
          this.navigator.clearPath();
          this.aiSit.setSitting(true);
          this.playTameEffect(true);
          this.world.setEntityState(this, (byte) 7);
        } else {
          this.playTameEffect(false);
          this.world.setEntityState(this, (byte) 6);
        }
      }

      return true;
    }

    return super.processInteract(player, hand);
  }

  protected boolean handleEating(EntityPlayer player, ItemStack stack) {
    boolean flag = false;
    float f = 0.0F;
    int i = 0;
    Item item = stack.getItem();

    if (item == Items.MELON || item == Items.MELON_SEEDS) {
      f = 2.0F;
      i = 20;
    }

    if (this.getHealth() < this.getMaxHealth() && f > 0.0F) {
      this.heal(f);
      flag = true;
    }

    if (this.isChild() && i > 0) {
      this.world.spawnParticle(EnumParticleTypes.VILLAGER_HAPPY, this.posX + (double) (this.rand.nextFloat() * this.width * 2.0F) - (double) this.width, this.posY + 0.5D + (double) (this.rand.nextFloat() * this.height), this.posZ + (double) (this.rand.nextFloat() * this.width * 2.0F) - (double) this.width, 0.0D, 0.0D, 0.0D);

      if (!this.world.isRemote) {
        this.addGrowth(i);
      }

      flag = true;
    }

    return flag;
  }

  @Override
  public EnumCreatureAttribute getCreatureAttribute() {
    return EnumCreatureAttribute.ARTHROPOD;
  }

  @Override
  @Nonnull
  public ResourceLocation getLootTable() {
    return LOOT_TABLE;
  }

}
