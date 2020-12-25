package epicsquid.mysticalworld.entity;

import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.init.ModEntities;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.Random;
import java.util.UUID;

@SuppressWarnings({"Duplicates", "NullableProblems", "unused"})
public class LavaCatEntity extends TameableEntity {
  private static ResourceLocation LOOT_TABLE = new ResourceLocation(MysticalWorld.MODID, "entity/lava_cat");

  private static final DataParameter<Boolean> IS_LAVA = EntityDataManager.createKey(LavaCatEntity.class, DataSerializers.BOOLEAN);
  private static final UUID OBSIDIAN_SPEED_MODIFIER = UUID.fromString("f58f95e9-fb51-4604-a66d-89433c9dd8a5");
  private static final AttributeModifier OBSIDIAN_SPEED = new AttributeModifier(OBSIDIAN_SPEED_MODIFIER, "Speed debuff for being obsidian", -0.05D, AttributeModifier.Operation.MULTIPLY_TOTAL);

  public LavaCatEntity(EntityType<? extends TameableEntity> type, World worldIn) {
    super(type, worldIn);
  }

  @Override
  protected void registerGoals() {
    goalSelector.addGoal(1, new SwimGoal(this));
    goalSelector.addGoal(2, new SitGoal(this));
    goalSelector.addGoal(3, new TemptGoal(this, 0.6D, Ingredient.fromItems(Items.BLAZE_ROD), false));
    goalSelector.addGoal(5, new FollowOwnerGoal(this, 1.0D, 10.0F, 5.0F, false));
    goalSelector.addGoal(7, new LeapAtTargetGoal(this, 0.3F));
    goalSelector.addGoal(8, new OcelotAttackGoal(this));
    goalSelector.addGoal(9, new BreedGoal(this, 0.8D));
    goalSelector.addGoal(10, new RandomWalkingGoal(this, 0.8D, 1));
    goalSelector.addGoal(11, new LookAtGoal(this, PlayerEntity.class, 10.0F));
    this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
    this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
    this.targetSelector.addGoal(3, new HurtByTargetGoal(this));
  }

  public static boolean placement(EntityType<? extends AnimalEntity> p_223316_0_, IWorld worldIn, SpawnReason reason, BlockPos blockpos, Random p_223316_4_) {
    Block block = worldIn.getBlockState(blockpos.down()).getBlock();
    return block == Blocks.NETHERRACK || block == Blocks.OBSIDIAN || block == Blocks.MAGMA_BLOCK || block == Blocks.SOUL_SAND || block == Blocks.SOUL_SOIL || block == Blocks.NETHER_BRICKS || block == Blocks.BONE_BLOCK || block == Blocks.NETHER_WART_BLOCK || block == Blocks.NETHER_GOLD_ORE || block == Blocks.ANCIENT_DEBRIS || block == Blocks.NETHER_QUARTZ_ORE || block == Blocks.RED_NETHER_BRICKS || block == Blocks.CHISELED_NETHER_BRICKS || block == Blocks.BASALT || block == Blocks.POLISHED_BASALT || block == Blocks.CRACKED_NETHER_BRICKS || block == Blocks.BLACKSTONE || block == Blocks.CHISELED_POLISHED_BLACKSTONE || block == Blocks.GILDED_BLACKSTONE || block == Blocks.POLISHED_BLACKSTONE || block == Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS || block == Blocks.CRIMSON_NYLIUM || block == Blocks.CRIMSON_HYPHAE || block == Blocks.WARPED_WART_BLOCK || block == Blocks.GRAVEL || block == Blocks.GLOWSTONE || block == Blocks.POLISHED_BLACKSTONE_BRICKS || block == Blocks.WARPED_NYLIUM || block == Blocks.SHROOMLIGHT;
  }

  @Override
  protected void registerData() {
    super.registerData();
    this.dataManager.register(IS_LAVA, true);
  }

  @Override
  public void updateAITasks() {
    if (this.getMoveHelper().isUpdating()) {
      double d0 = this.getMoveHelper().getSpeed();

      if (d0 == 0.6D) {
        this.setSneaking(true);
        this.setSprinting(false);
      } else if (d0 == 1.33D) {
        this.setSneaking(false);
        this.setSprinting(true);
      } else {
        this.setSneaking(false);
        this.setSprinting(false);
      }
    } else {
      this.setSneaking(false);
      this.setSprinting(false);
    }
  }

  @Override
  public boolean canDespawn(double distance) {
    return !this.isTamed() && this.ticksExisted > 2400;
  }

  public static AttributeModifierMap.MutableAttribute attributes() {
    return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MAX_HEALTH, 20.0d).createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.25d).createMutableAttribute(Attributes.ATTACK_DAMAGE, 2.0d);
  }

  // TODO: Fix fall damage
/*  @Override
  public void fall(float distance, float damageMultiplier) {
  }*/

  @Override
  @Nullable
  protected SoundEvent getAmbientSound() {
    if (this.isTamed()) {
      if (this.isInLove()) {
        return SoundEvents.ENTITY_CAT_PURR;
      } else {
        return this.rand.nextInt(4) == 0 ? SoundEvents.ENTITY_CAT_PURREOW : SoundEvents.ENTITY_CAT_AMBIENT;
      }
    } else {
      return null;
    }
  }

  @Override
  protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
    return net.minecraft.util.SoundEvents.ENTITY_CAT_HURT;
  }

  @Override
  protected SoundEvent getDeathSound() {
    return SoundEvents.ENTITY_CAT_DEATH;
  }

  @Override
  protected float getSoundVolume() {
    return 0.4F;
  }

  @Override
  public boolean attackEntityAsMob(Entity entityIn) {
    EntityDamageSource damage = new EntityDamageSource("mob", this);
    damage.setDifficultyScaled();
    float amount = 3.0f;
    if (getIsLava()) {
      damage.setFireDamage().setDamageBypassesArmor();
    } else {
      amount = 6.0f;
    }
    return entityIn.attackEntityFrom(damage, amount);
  }

  @Override
  public void setFire(int seconds) {
  }

  @Override
  public boolean attackEntityFrom(DamageSource source, float amount) {
    if (this.isInvulnerableTo(source)) {
      return false;
    } else {
      this.func_233687_w_(false);

      if (source.isFireDamage()) {
        return false;
      }

      if (source == DamageSource.WITHER || source == DamageSource.ANVIL) {
        // You can't really damage stone (liquid or otherwise)
        return false;
      }

      boolean lava = getIsLava();

      // Obsidian cats take half damage from non-magic damage
      if (!lava && !source.isMagicDamage()) {
        amount /= 2;
      }

      // Lava cats take half damage from magic
      if (lava && source.isMagicDamage()) {
        amount /= 2;
      }

      // They don't take damage from their owners unless sneaking
      if (isTamed() && source.getTrueSource() != null && source.getTrueSource() == getOwner() && !source.getTrueSource().isSneaking()) {
        return false;
      }

      return super.attackEntityFrom(source, amount);
    }
  }

  @Override
  public boolean addPotionEffect(EffectInstance potioneffectIn) {
    Effect type = potioneffectIn.getPotion();
    if (type == Effects.POISON || type == Effects.WITHER) {
      return false;
    }

    return super.addPotionEffect(potioneffectIn);
  }

  @Override
  public ResourceLocation getLootTable() {
    return LOOT_TABLE;
  }

  @Override
  public ActionResultType func_230254_b_(PlayerEntity player, Hand hand) {
    ItemStack itemstack = player.getHeldItem(hand);
    Item item = itemstack.getItem();
    if (this.world.isRemote) {
      boolean flag = this.isOwner(player) || this.isTamed() || item == Items.BLAZE_ROD && !this.isTamed();
      return flag ? ActionResultType.CONSUME : ActionResultType.PASS;
    } else {
      if (this.isTamed()) {
        if (this.isBreedingItem(itemstack) && this.getHealth() < this.getMaxHealth()) {
          if (!player.abilities.isCreativeMode) {
            itemstack.shrink(1);
          }

          this.heal(2f);
          return ActionResultType.SUCCESS;
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
      } else if (item == Items.BLAZE_ROD) {
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
    ItemStack itemstack = player.getHeldItem(hand);

    if (this.isTamed()) {
      if (this.isOwner(player) && !this.world.isRemote && !this.isBreedingItem(itemstack)) {
        this.func_233687_w_(!this.isSitting());
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
          this.func_233687_w_(true);
          this.world.setEntityState(this, (byte) 7);
        } else {
          this.world.setEntityState(this, (byte) 6);
        }
      }

      return ActionResultType.SUCCESS;
    }

    return super.func_230254_b_(player, hand);
  }*/

  @Override
  public AgeableEntity func_241840_a(ServerWorld world, AgeableEntity ageable) {
    LavaCatEntity lavacat = ModEntities.LAVA_CAT.get().create(ageable.world);

    if (this.isTamed() && lavacat != null) {
      lavacat.setOwnerId(this.getOwnerId());
      lavacat.setTamed(true);
      lavacat.setIsLava(getIsLava());
    }

    return lavacat;
  }

  @Override
  public boolean isBreedingItem(ItemStack stack) {
    return stack.getItem() == net.minecraft.item.Items.BLAZE_POWDER;
  }

  @Override
  public boolean canMateWith(AnimalEntity otherAnimal) {
    if (otherAnimal == this) {
      return false;
    } else if (!this.isTamed()) {
      return false;
    } else if (!(otherAnimal instanceof LavaCatEntity)) {
      return false;
    } else {
      LavaCatEntity lavacat = (LavaCatEntity) otherAnimal;

      if (!lavacat.isTamed()) {
        return false;
      } else {
        return this.isInLove() && lavacat.isInLove();
      }
    }
  }

  public boolean getIsLava() {
    return this.dataManager.get(IS_LAVA);
  }

  public void setIsLava(boolean val) {
    this.dataManager.set(IS_LAVA, val);
    ModifiableAttributeInstance instance = this.getAttribute(Attributes.MOVEMENT_SPEED);
    if (instance != null) {
      if (val && instance.hasModifier(OBSIDIAN_SPEED)) {
        instance.removeModifier(OBSIDIAN_SPEED);
      } else if (!instance.hasModifier(OBSIDIAN_SPEED)) {
        instance.applyNonPersistentModifier(OBSIDIAN_SPEED);
      }
    }
  }

  @Override
  protected void setupTamedAI() {
  }

  @Override
  public void tick() {
    super.tick();

    if (getIsLava() && world.isRainingAt(getPosition()) && world.canBlockSeeSky(getPosition()) && rand.nextInt(30) == 0) {
      world.playSound(null, getPosX(), getPosY(), getPosZ(), SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.NEUTRAL, 0.2f, 1.3f);
    }

    if (getIsLava() && inWater) {
      setIsLava(false);
    } else if (!getIsLava() && isInLava()) {
      setIsLava(true);
    }
  }

  @Override
  public void writeAdditional(CompoundNBT compound) {
    super.writeAdditional(compound);
    compound.putBoolean("IsLava", getIsLava());
  }

  @Override
  public void readAdditional(CompoundNBT compound) {
    super.readAdditional(compound);
    setIsLava(compound.getBoolean("IsLava"));
  }

  @Override
  public ITextComponent getName() {
    ITextComponent itextcomponent = this.getCustomName();
    if (itextcomponent != null) {
      return super.getName();
    } else {
      if (this.getIsLava()) {
        return new TranslationTextComponent("mysticalworld.entity.lava_cat");
      } else {
        return new TranslationTextComponent("mysticalworld.entity.obsidian_cat");
      }
    }
  }
}

