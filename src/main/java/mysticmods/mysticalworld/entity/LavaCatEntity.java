package mysticmods.mysticalworld.entity;

import mysticmods.mysticalworld.MysticalWorld;
import mysticmods.mysticalworld.init.ModEntities;
import mysticmods.mysticalworld.init.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.EntityDamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import javax.annotation.Nullable;
import java.util.Random;
import java.util.UUID;

@SuppressWarnings({"Duplicates", "NullableProblems", "unused"})
public class LavaCatEntity extends TamableAnimal {
  private static final ResourceLocation LOOT_TABLE = new ResourceLocation(MysticalWorld.MODID, "entity/lava_cat");

  private static final EntityDataAccessor<Boolean> IS_LAVA = SynchedEntityData.defineId(LavaCatEntity.class, EntityDataSerializers.BOOLEAN);
  private static final UUID OBSIDIAN_SPEED_MODIFIER = UUID.fromString("f58f95e9-fb51-4604-a66d-89433c9dd8a5");
  private static final AttributeModifier OBSIDIAN_SPEED = new AttributeModifier(OBSIDIAN_SPEED_MODIFIER, "Speed debuff for being obsidian", -0.05D, AttributeModifier.Operation.MULTIPLY_TOTAL);

  public LavaCatEntity(EntityType<? extends TamableAnimal> type, Level worldIn) {
    super(type, worldIn);
  }

  @Override
  protected void registerGoals() {
    goalSelector.addGoal(1, new FloatGoal(this));
    goalSelector.addGoal(2, new SitWhenOrderedToGoal(this));
    goalSelector.addGoal(3, new TemptGoal(this, 0.6D, Ingredient.of(Items.BLAZE_ROD), false));
    goalSelector.addGoal(5, new FollowOwnerGoal(this, 1.0D, 10.0F, 5.0F, false));
    goalSelector.addGoal(7, new LeapAtTargetGoal(this, 0.3F));
    goalSelector.addGoal(8, new OcelotAttackGoal(this));
    goalSelector.addGoal(9, new BreedGoal(this, 0.8D));
    goalSelector.addGoal(10, new RandomStrollGoal(this, 0.8D, 1));
    goalSelector.addGoal(11, new LookAtPlayerGoal(this, Player.class, 10.0F));
    this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
    this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
    this.targetSelector.addGoal(3, new HurtByTargetGoal(this));
  }

  public static boolean placement(EntityType<? extends Animal> pAnimal, LevelAccessor worldIn, MobSpawnType reason, BlockPos blockpos, Random pRandom) {
    Block block = worldIn.getBlockState(blockpos.below()).getBlock();
    return block == Blocks.NETHERRACK || block == Blocks.OBSIDIAN || block == Blocks.MAGMA_BLOCK || block == Blocks.SOUL_SAND || block == Blocks.SOUL_SOIL || block == Blocks.NETHER_BRICKS || block == Blocks.BONE_BLOCK || block == Blocks.NETHER_WART_BLOCK || block == Blocks.NETHER_GOLD_ORE || block == Blocks.ANCIENT_DEBRIS || block == Blocks.NETHER_QUARTZ_ORE || block == Blocks.RED_NETHER_BRICKS || block == Blocks.CHISELED_NETHER_BRICKS || block == Blocks.BASALT || block == Blocks.POLISHED_BASALT || block == Blocks.CRACKED_NETHER_BRICKS || block == Blocks.BLACKSTONE || block == Blocks.CHISELED_POLISHED_BLACKSTONE || block == Blocks.GILDED_BLACKSTONE || block == Blocks.POLISHED_BLACKSTONE || block == Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS || block == Blocks.CRIMSON_NYLIUM || block == Blocks.CRIMSON_HYPHAE || block == Blocks.WARPED_WART_BLOCK || block == Blocks.GRAVEL || block == Blocks.GLOWSTONE || block == Blocks.POLISHED_BLACKSTONE_BRICKS || block == Blocks.WARPED_NYLIUM || block == Blocks.SHROOMLIGHT;
  }

  @Override
  protected void defineSynchedData() {
    super.defineSynchedData();
    this.entityData.define(IS_LAVA, true);
  }

  @Override
  public void customServerAiStep() {
    if (this.getMoveControl().hasWanted()) {
      double d0 = this.getMoveControl().getSpeedModifier();

      if (d0 == 0.6D) {
        this.setShiftKeyDown(true);
        this.setSprinting(false);
      } else if (d0 == 1.33D) {
        this.setShiftKeyDown(false);
        this.setSprinting(true);
      } else {
        this.setShiftKeyDown(false);
        this.setSprinting(false);
      }
    } else {
      this.setShiftKeyDown(false);
      this.setSprinting(false);
    }
  }

  @Override
  public boolean removeWhenFarAway(double distance) {
    return !this.isTame() && this.tickCount > 2400;
  }

  public static AttributeSupplier.Builder attributes() {
    return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 20.0d).add(Attributes.MOVEMENT_SPEED, 0.25d).add(Attributes.ATTACK_DAMAGE, 2.0d);
  }

  // TODO: Fix fall damage
/*  @Override
  public void fall(float distance, float damageMultiplier) {
  }*/

  @Override
  @Nullable
  protected SoundEvent getAmbientSound() {
    if (this.isTame()) {
      if (this.isInLove()) {
        return ModSounds.LAVA_CAT_PURR.get();
      } else {
        return this.random.nextInt(4) == 0 ? ModSounds.LAVA_CAT_PURREOW.get() : ModSounds.LAVA_CAT_AMBIENT.get();
      }
    } else {
      return null;
    }
  }

  @Override
  protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
    return ModSounds.LAVA_CAT_HURT.get();
  }

  @Override
  protected SoundEvent getDeathSound() {
    return ModSounds.LAVA_CAT_DEATH.get();
  }

  @Override
  protected float getSoundVolume() {
    return 0.4F;
  }

  @Override
  public boolean doHurtTarget(Entity entityIn) {
    EntityDamageSource damage = new EntityDamageSource("mob", this);
    damage.setScalesWithDifficulty();
    float amount = 3.0f;
    if (getIsLava()) {
      damage.setIsFire().bypassArmor();
    } else {
      amount = 6.0f;
    }
    return entityIn.hurt(damage, amount);
  }

  @Override
  public void setSecondsOnFire(int seconds) {
  }

  @Override
  public boolean hurt(DamageSource source, float amount) {
    if (this.isInvulnerableTo(source)) {
      return false;
    } else {
      this.setOrderedToSit(false);

      if (source.isFire()) {
        return false;
      }

      if (source == DamageSource.WITHER || source == DamageSource.ANVIL) {
        // You can't really damage stone (liquid or otherwise)
        return false;
      }

      boolean lava = getIsLava();

      // Obsidian cats take half damage from non-magic damage
      if (!lava && !source.isMagic()) {
        amount /= 2;
      }

      // Lava cats take half damage from magic
      if (lava && source.isMagic()) {
        amount /= 2;
      }

      // They don't take damage from their owners unless sneaking
      if (isTame() && source.getEntity() != null && source.getEntity() == getOwner() && !source.getEntity().isShiftKeyDown()) {
        return false;
      }

      return super.hurt(source, amount);
    }
  }

  @Override
  public boolean addEffect(MobEffectInstance potioneffectIn) {
    MobEffect type = potioneffectIn.getEffect();
    if (type == MobEffects.POISON || type == MobEffects.WITHER) {
      return false;
    }

    return super.addEffect(potioneffectIn);
  }

  @Override
  public ResourceLocation getDefaultLootTable() {
    return LOOT_TABLE;
  }

  @Override
  public InteractionResult mobInteract(Player player, InteractionHand hand) {
    ItemStack itemstack = player.getItemInHand(hand);
    Item item = itemstack.getItem();
    if (this.level.isClientSide) {
      boolean flag = this.isOwnedBy(player) || this.isTame() || item == Items.BLAZE_ROD && !this.isTame();
      return flag ? InteractionResult.CONSUME : InteractionResult.PASS;
    } else {
      if (this.isTame()) {
        if (this.isFood(itemstack) && this.getHealth() < this.getMaxHealth()) {
          if (!player.abilities.instabuild) {
            itemstack.shrink(1);
          }

          this.heal(2f);
          return InteractionResult.SUCCESS;
        }

        InteractionResult actionresulttype = super.mobInteract(player, hand);
        if ((!actionresulttype.consumesAction() || this.isBaby()) && this.isOwnedBy(player)) {
          this.setOrderedToSit(!this.isOrderedToSit());
          this.jumping = false;
          this.navigation.stop();
          this.setTarget(null);
          return InteractionResult.SUCCESS;
        }

        return actionresulttype;
      } else if (item == Items.BLAZE_ROD) {
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

        return InteractionResult.SUCCESS;
      }

      return super.mobInteract(player, hand);
    }
  }

/*  @Override
  public ActionResultType mobInteract(PlayerEntity player, Hand hand) {
    ItemStack itemstack = player.getHeldItem(hand);

    if (this.isTamed()) {
      if (this.isOwner(player) && !this.world.isRemote && !this.isBreedingItem(itemstack)) {
        this.setOrderedToSit(!this.isSitting());
        this.isJumping = false;
        this.navigator.clearPath();
        this.setAttackTarget(null);
        return ActionResultType.SUCCESS;
      }
    } else if (itemstack.getItem() == Items.BLAZE_ROD && player.getDistanceSq(this) < 9.0D) {
      if (!player.isCreative()) {
        itemstack.shrink(1);
      }

      if (!this.world.isRemote) {
        if (this.rand.nextInt(3) == 0 && !net.minecraftforge.event.ForgeEventFactory.onAnimalTame(this, player)) {
          this.setTamedBy(player);
          this.setOrderedToSit(true);
          this.world.setEntityState(this, (byte) 7);
        } else {
          this.world.setEntityState(this, (byte) 6);
        }
      }

      return ActionResultType.SUCCESS;
    }

    return super.mobInteract(player, hand);
  }*/

  @Override
  public AgableMob getBreedOffspring(ServerLevel world, AgableMob ageable) {
    LavaCatEntity lavacat = ModEntities.LAVA_CAT.get().create(ageable.level);

    if (this.isTame() && lavacat != null) {
      lavacat.setOwnerUUID(this.getOwnerUUID());
      lavacat.setTame(true);
      lavacat.setIsLava(getIsLava());
    }

    return lavacat;
  }

  @Override
  public boolean isFood(ItemStack stack) {
    return stack.getItem() == net.minecraft.world.item.Items.BLAZE_POWDER;
  }

  @Override
  public boolean canMate(Animal otherAnimal) {
    if (otherAnimal == this) {
      return false;
    } else if (!this.isTame()) {
      return false;
    } else if (!(otherAnimal instanceof LavaCatEntity)) {
      return false;
    } else {
      LavaCatEntity lavacat = (LavaCatEntity) otherAnimal;

      if (!lavacat.isTame()) {
        return false;
      } else {
        return this.isInLove() && lavacat.isInLove();
      }
    }
  }

  public boolean getIsLava() {
    return this.entityData.get(IS_LAVA);
  }

  public void setIsLava(boolean val) {
    this.entityData.set(IS_LAVA, val);
    AttributeInstance instance = this.getAttribute(Attributes.MOVEMENT_SPEED);
    if (instance != null) {
      if (val && instance.hasModifier(OBSIDIAN_SPEED)) {
        instance.removeModifier(OBSIDIAN_SPEED);
      } else if (!instance.hasModifier(OBSIDIAN_SPEED)) {
        instance.addTransientModifier(OBSIDIAN_SPEED);
      }
    }
  }

  @Override
  protected void reassessTameGoals() {
  }

  @Override
  public void tick() {
    super.tick();

    if (getIsLava() && level.isRainingAt(blockPosition()) && level.canSeeSkyFromBelowWater(blockPosition()) && random.nextInt(30) == 0) {
      level.playSound(null, getX(), getY(), getZ(), ModSounds.LAVA_CAT_SIZZLE.get(), SoundSource.NEUTRAL, 0.2f, 1.3f);
    }

    if (getIsLava() && wasTouchingWater) {
      setIsLava(false);
    } else if (!getIsLava() && isInLava()) {
      setIsLava(true);
    }
  }

  @Override
  public void addAdditionalSaveData(CompoundTag compound) {
    super.addAdditionalSaveData(compound);
    compound.putBoolean("IsLava", getIsLava());
  }

  @Override
  public void readAdditionalSaveData(CompoundTag compound) {
    super.readAdditionalSaveData(compound);
    setIsLava(compound.getBoolean("IsLava"));
  }

  @Override
  public Component getName() {
    Component itextcomponent = this.getCustomName();
    if (itextcomponent != null) {
      return super.getName();
    } else {
      if (this.getIsLava()) {
        return new TranslatableComponent("mysticalworld.entity.lava_cat");
      } else {
        return new TranslatableComponent("mysticalworld.entity.obsidian_cat");
      }
    }
  }
}

