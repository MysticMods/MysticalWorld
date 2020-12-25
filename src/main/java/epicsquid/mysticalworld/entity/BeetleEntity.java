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
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.network.PacketDistributor;

import javax.annotation.Nonnull;

@SuppressWarnings({"NullableProblems", "Duplicates", "ConstantConditions"})
public class BeetleEntity extends TameableEntity {

  public BeetleEntity(EntityType<? extends BeetleEntity> type, World worldIn) {
    super(type, worldIn);
    this.experienceValue = 3;
  }

  @Override
  protected void registerGoals() {
    goalSelector.addGoal(0, new SwimGoal(this));
    goalSelector.addGoal(1, new PanicGoal(this, 1.5D));
    goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
    goalSelector.addGoal(2, new SitGoal(this));
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
  public ActionResultType func_230254_b_(PlayerEntity player, Hand hand) {
    ItemStack itemstack = player.getHeldItem(hand);
    Item item = itemstack.getItem();
    if (this.world.isRemote) {
      boolean flag = this.isOwner(player) || this.isTamed() || item == Items.MELON_SEEDS && !this.isTamed();
      return flag ? ActionResultType.CONSUME : ActionResultType.PASS;
    } else {
      if (this.isTamed()) {
        if (this.isBreedingItem(itemstack) && this.getHealth() < this.getMaxHealth()) {
          if (!player.abilities.isCreativeMode) {
            itemstack.shrink(1);
          }

          Food food = item.getFood();
          if (food != null) {
            this.heal((float) food.getHealing());
            return ActionResultType.SUCCESS;
          }
        }

        ActionResultType actionresulttype = super.func_230254_b_(player, hand);
        if ((!actionresulttype.isSuccessOrConsume() || this.isChild()) && this.isOwner(player)) {
          this.func_233687_w_(!this.isSitting());
          this.isJumping = false;
          this.navigator.clearPath();
          this.setAttackTarget(null);
          return ActionResultType.SUCCESS;
        }

        return actionresulttype;
      } else if (item == Items.MELON_SEEDS) {
        if (!player.abilities.isCreativeMode) {
          itemstack.shrink(1);
        }

        if (this.rand.nextInt(3) == 0 && !net.minecraftforge.event.ForgeEventFactory.onAnimalTame(this, player)) {
          this.setTamedBy(player);
          this.navigator.clearPath();
          this.setAttackTarget(null);
          this.func_233687_w_(true);
          this.world.setEntityState(this, (byte) 7);
        } else {
          this.world.setEntityState(this, (byte) 6);
        }

        return ActionResultType.SUCCESS;
      }

      return super.func_230254_b_(player, hand);
    }
  }

/*  @Override
  public ActionResultType func_230254_b_(PlayerEntity player, Hand hand) {
    ActionResultType type = super.func_230254_b_(player, hand);
    if (type != ActionResultType.PASS) {
      return type;
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
          this.func_233687_w_(!this.isSitting());
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
          this.func_233687_w_(true);
          this.world.setEntityState(this, (byte) 7);
        } else {
          this.world.setEntityState(this, (byte) 6);
        }
      }

      return ActionResultType.SUCCESS;
    }

    return ActionResultType.PASS;
  }*/

  public static AttributeModifierMap.MutableAttribute attributes() {
    return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MAX_HEALTH, 10.0d).createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.15d);
  }

  @Override
  @Nonnull
  public AgeableEntity func_241840_a(ServerWorld world, AgeableEntity ageable) {
    return ModEntities.BEETLE.get().create(ageable.world);
  }

  @Override
  @Nonnull
  public ResourceLocation getLootTable() {
    return new ResourceLocation(MysticalWorld.MODID, "entities/beetle");
  }

}
