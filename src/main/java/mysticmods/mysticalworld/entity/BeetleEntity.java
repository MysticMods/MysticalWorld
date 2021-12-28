package mysticmods.mysticalworld.entity;

import mysticmods.mysticalworld.MysticalWorld;
import mysticmods.mysticalworld.api.Capabilities;
import mysticmods.mysticalworld.api.IPlayerShoulderCapability;
import mysticmods.mysticalworld.capability.PlayerShoulderCapability;
import mysticmods.mysticalworld.network.Networking;
import mysticmods.mysticalworld.network.ShoulderRide;
import mysticmods.mysticalworld.init.ModEntities;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
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
import net.minecraft.util.text.Style;
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
    this.xpReward = 3;
  }

  @Override
  protected void registerGoals() {
    goalSelector.addGoal(0, new SwimGoal(this));
    goalSelector.addGoal(1, new PanicGoal(this, 1.5D));
    goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
    goalSelector.addGoal(2, new SitGoal(this));
    goalSelector.addGoal(3, new TemptGoal(this, 1.25D, Ingredient.of(Items.MELON_SLICE), false));
    goalSelector.addGoal(4, new FollowParentGoal(this, 1.25D));
    goalSelector.addGoal(5, new RandomWalkingGoal(this, 1.0D));
    goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 6.0F));
    goalSelector.addGoal(7, new LookRandomlyGoal(this));
  }

  @Override
  public boolean isFood(@Nonnull ItemStack stack) {
    return stack.getItem() == Items.MELON_SLICE;
  }

  @Override
  public ActionResultType mobInteract(PlayerEntity player, Hand hand) {
    ItemStack itemstack = player.getItemInHand(hand);
    Item item = itemstack.getItem();
    if (this.level.isClientSide) {
      boolean flag = this.isOwnedBy(player) || this.isTame() || item == Items.MELON_SEEDS && !this.isTame();
      return flag ? ActionResultType.CONSUME : ActionResultType.PASS;
    } else {
      if (this.isTame()) {
        if (this.isOwnedBy(player) && itemstack.isEmpty() && player.isCrouching()) {
          LazyOptional<IPlayerShoulderCapability> laycap = player.getCapability(Capabilities.SHOULDER_CAPABILITY);
          if (laycap.isPresent()) {
            IPlayerShoulderCapability cap = laycap.orElseThrow(IllegalStateException::new);
            if (!cap.isShouldered() && player.getShoulderEntityRight().isEmpty()) {
              setOrderedToSit(false);
              this.setShiftKeyDown(false);
              cap.shoulder(this);
              player.swing(Hand.MAIN_HAND);
              try {
                PlayerShoulderCapability.setLeftShoulder.invokeExact(player, cap.generateShoulderNBT());
              } catch (Throwable throwable) {
                MysticalWorld.LOG.error("Unable to fake player having a shoulder entity", throwable);
              }

              ShoulderRide message = new ShoulderRide(player, cap);
              Networking.send(PacketDistributor.ALL.noArg(), message);
              this.remove();
              return ActionResultType.SUCCESS;
            } else {
              player.displayClientMessage(new TranslationTextComponent("message.shoulder.occupied").setStyle(Style.EMPTY.withColor(TextFormatting.GREEN).withBold(true)), true);
            }
          }
        } else if (this.isFood(itemstack) && this.getHealth() < this.getMaxHealth()) {
          if (!player.abilities.instabuild) {
            itemstack.shrink(1);
          }

          Food food = item.getFoodProperties();
          if (food != null) {
            this.heal((float) food.getNutrition());
            return ActionResultType.SUCCESS;
          }
        }

        ActionResultType actionresulttype = super.mobInteract(player, hand);
        if ((!actionresulttype.consumesAction() || this.isBaby()) && this.isOwnedBy(player)) {
          this.setOrderedToSit(!this.isOrderedToSit());
          this.jumping = false;
          this.navigation.stop();
          this.setTarget(null);
          return ActionResultType.SUCCESS;
        }

        return actionresulttype;
      } else if (item == Items.MELON_SEEDS) {
        if (!player.abilities.instabuild) {
          itemstack.shrink(1);
        }

        if (this.random.nextInt(3) == 0 && !net.minecraftforge.event.ForgeEventFactory.onAnimalTame(this, player)) {
          this.tame(player);
          this.navigation.stop();
          this.setTarget(null);
          this.setOrderedToSit(true);
          this.level.broadcastEntityEvent(this, (byte) 7);
        } else {
          this.level.broadcastEntityEvent(this, (byte) 6);
        }

        return ActionResultType.SUCCESS;
      }

      return super.mobInteract(player, hand);
    }
  }

  public static AttributeModifierMap.MutableAttribute attributes() {
    return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 10.0d).add(Attributes.MOVEMENT_SPEED, 0.15d);
  }

  @Override
  @Nonnull
  public AgeableEntity getBreedOffspring(ServerWorld world, AgeableEntity ageable) {
    return ModEntities.BEETLE.get().create(ageable.level);
  }

  @Override
  @Nonnull
  public ResourceLocation getDefaultLootTable() {
    return new ResourceLocation(MysticalWorld.MODID, "entities/beetle");
  }

}
