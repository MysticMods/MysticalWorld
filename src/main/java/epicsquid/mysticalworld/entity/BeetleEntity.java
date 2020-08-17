package epicsquid.mysticalworld.entity;

import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.api.Capabilities;
import epicsquid.mysticalworld.api.IPlayerShoulderCapability;
import epicsquid.mysticalworld.capability.PlayerShoulderCapability;
import epicsquid.mysticalworld.init.ModEntities;
import epicsquid.mysticalworld.network.Networking;
import epicsquid.mysticalworld.network.ShoulderRide;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.network.PacketDistributor;

import javax.annotation.Nonnull;

public class BeetleEntity extends TameableEntity {

  public BeetleEntity(EntityType<? extends BeetleEntity> type, World worldIn) {
    super(type, worldIn);
//    setSize(0.75f, 0.75f);
    this.experienceValue = 3;
  }

  @Override
  protected void registerGoals() {
    this.sitGoal = new SitGoal(this);
    goalSelector.addGoal(0, new SwimGoal(this));
    goalSelector.addGoal(1, new PanicGoal(this, 1.5D));
    goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
    goalSelector.addGoal(2, this.sitGoal);
    goalSelector.addGoal(3, new TemptGoal(this, 1.25D, Ingredient.fromItems(Items.MELON_SLICE), false));
    goalSelector.addGoal(4, new FollowParentGoal(this, 1.25D));
    goalSelector.addGoal(5, new RandomWalkingGoal(this, 1.0D));
    goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 6.0F));
    goalSelector.addGoal(7, new LookRandomlyGoal(this));
  }

  @Override
  public boolean isBreedingItem(@Nonnull ItemStack stack) {
    return stack.getItem() == Items.MELON_SLICE;
  }

  @Override
  public boolean processInteract(PlayerEntity player, Hand hand) {
    if (super.processInteract(player, hand)) {
      return true;
    }

    ItemStack itemstack = player.getHeldItem(hand);

    if (this.isTamed()) {
      if (this.isOwner(player) && !this.world.isRemote && !this.isBreedingItem(itemstack)) {
        if (itemstack.isEmpty() && player.isSneaking()) {
          // TODO Temporarily disabled
          if (!world.isRemote && false) {
            // Try some shoulder surfing!
            LazyOptional<IPlayerShoulderCapability> laycap = player.getCapability(Capabilities.SHOULDER_CAPABILITY);
            if (laycap.isPresent()) {
              IPlayerShoulderCapability cap = laycap.orElseThrow(IllegalStateException::new);
              if (!cap.isShouldered() && player.getLeftShoulderEntity().isEmpty()) {
                setSitting(false);
                this.setSneaking(false);
                cap.shoulder(this);
                player.swingArm(Hand.MAIN_HAND);
                try {
                  PlayerShoulderCapability.setRightShoulder.invokeExact(player, cap.generateShoulderNBT());
                } catch (Throwable throwable) {
                  MysticalWorld.LOG.error("Unable to fake player having a shoulder entity", throwable);
                }

                ShoulderRide message = new ShoulderRide(player, cap);
                Networking.send(PacketDistributor.ALL.noArg(), message);
                this.remove();
                return true;
              } else {
                player.sendStatusMessage(new TranslationTextComponent("message.shoulder.occupied").setStyle(new Style().setColor(TextFormatting.GREEN).setBold(true)), true);
              }
            }
          }
        } else {
          this.sitGoal.setSitting(!this.isSitting());
          this.isJumping = false;
          this.navigator.clearPath();
          this.setAttackTarget(null);
        }
      }
    } else if (itemstack.getItem() == Items.MELON_SEEDS) {
      if (!player.isCreative()) {
        itemstack.shrink(1);
      }

      if (!this.world.isRemote) {
        if (this.rand.nextInt(3) == 0 && !net.minecraftforge.event.ForgeEventFactory.onAnimalTame(this, player)) {
          this.setTamedBy(player);
          this.navigator.clearPath();
          this.sitGoal.setSitting(true);
          this.world.setEntityState(this, (byte) 7);
        } else {
          this.world.setEntityState(this, (byte) 6);
        }
      }

      return true;
    }

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
    return ModEntities.BEETLE.get().create(ageable.world);
  }

  @Override
  @Nonnull
  public ResourceLocation getLootTable() {
    return new ResourceLocation(MysticalWorld.MODID, "entities/beetle");
  }

}
