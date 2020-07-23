package epicsquid.mysticalworld.entity;

import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.config.ConfigManager;
import epicsquid.mysticalworld.events.LeafHandler;
import epicsquid.mysticalworld.init.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class SilkwormEntity extends AnimalEntity {
  public static ResourceLocation LOOT_TABLE = new ResourceLocation(MysticalWorld.MODID, "entity/silkworm");

  private static final DataParameter<Integer> SIZE = EntityDataManager.createKey(SilkwormEntity.class, DataSerializers.VARINT);
  private static final DataParameter<Integer> LEAVES_CONSUMED = EntityDataManager.createKey(SilkwormEntity.class, DataSerializers.VARINT);
  private static final int MAX_SIZE = 120;

  private ItemEntity leafTarget;
  private int lastTickPlayed = 0;

  public SilkwormEntity(EntityType<? extends AnimalEntity> type, World worldIn) {
    super(type, worldIn);
    this.experienceValue = 1;
  }

  public void setLeafTarget(ItemEntity leaf) {
    this.leafTarget = leaf;
  }

  public ItemEntity getLeafTarget() {
    return this.leafTarget;
  }

  @Nullable
  @Override
  public AgeableEntity createChild(AgeableEntity ageable) {
    return null;
  }

  @Override
  protected void registerGoals() {
    goalSelector.addGoal(1, new SwimGoal(this));
    goalSelector.addGoal(2, new MeleeAttackGoal(this, 0.5d, false));
    goalSelector.addGoal(3, new RandomWalkingGoal(this, 0.5d));
    goalSelector.addGoal(3, new TemptGoal(this, 0.9d, false, Ingredient.fromItems(LeafHandler.getLeafItems().toArray(new Item[0]))));
    goalSelector.addGoal(8, new LookRandomlyGoal(this));
    targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, SilkwormEntity.class, false));
  }

  @Override
  protected void registerData() {
    super.registerData();
    this.dataManager.register(SIZE, 0);
    this.dataManager.register(LEAVES_CONSUMED, 0);
  }

  public int getLeavesConsumed() {
    return this.dataManager.get(LEAVES_CONSUMED);
  }

  public void setLeavesConsumed(int amount) {
    this.dataManager.set(LEAVES_CONSUMED, amount);
  }

  public void resetLeaves() {
    this.dataManager.set(LEAVES_CONSUMED, 0);
  }

  private boolean shouldPlaySound() {
    return (ticksExisted - lastTickPlayed) > 8;
  }

  public void eatLeaves() {
    incLeaves();
    for (int i = 0; i < 1 + rand.nextInt(3); i++) {
      grow();
    }
    this.heal(1f);
    if (shouldPlaySound()) {
      world.playSound(null, posX, posY, posZ, SoundEvents.ENTITY_LLAMA_EAT, SoundCategory.NEUTRAL, 0.5f, 1.2f + rand.nextFloat() * 0.02f);
      lastTickPlayed = ticksExisted;
    }
  }

  private void incLeaves() {
    this.dataManager.set(LEAVES_CONSUMED, getLeavesConsumed() + 1);
  }

  public int getSize() {
    return this.dataManager.get(SIZE);
  }

  public void setSize(int size) {
    this.dataManager.set(SIZE, size);
  }

  private void incSize() {
    if (!this.isChild()) {
      this.dataManager.set(SIZE, getSize() + 1);
    }
  }

  @Override
  public boolean processInteract(PlayerEntity player, Hand hand) {
    if (!player.world.isRemote) {
      ItemStack itemstack = player.getHeldItem(hand);

      if (LeafHandler.getLeafItems().contains(itemstack.getItem())) {
        if (!player.abilities.isCreativeMode) {
          itemstack.shrink(1);
        }
        eatLeaves();

        return true;
      }
    }

    return super.processInteract(player, hand);
  }

  @Override
  public float getEyeHeight(Pose pose) {
    return 0.1F;
  }

  @Override
  protected void registerAttributes() {
    super.registerAttributes();
    this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
    this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.15D);
  }

  // TODO: prevent crop trampling

  @Override
  protected SoundEvent getAmbientSound() {
    if (world.rand.nextInt(4) == 0) {
      return net.minecraft.util.SoundEvents.ENTITY_ENDERMITE_AMBIENT;
    }

    return null;
  }

  @Override
  protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
    return net.minecraft.util.SoundEvents.ENTITY_ENDERMITE_HURT;
  }

  @Override
  protected SoundEvent getDeathSound() {
    return net.minecraft.util.SoundEvents.ENTITY_ENDERMITE_DEATH;
  }

  @Override
  protected void playStepSound(BlockPos pos, BlockState state) {
    this.playSound(net.minecraft.util.SoundEvents.ENTITY_ENDERMITE_STEP, 0.15F, 1.3F + (world.rand.nextFloat() - 0.5f));
  }

  @Override
  public ResourceLocation getLootTable() {
    return LOOT_TABLE;
  }

  @Override
  protected float getSoundVolume() {
    return 0.2f;
  }

  @Override
  protected float getSoundPitch() {
    return 1.3f + rand.nextFloat() - 0.5f;
  }

  @Override
  public void readAdditional(CompoundNBT compound) {
    super.readAdditional(compound);
    setSize(compound.getInt("Size"));
    setLeavesConsumed(compound.getInt("Leaves"));
  }

  @Override
  public void writeAdditional(CompoundNBT compound) {
    super.writeAdditional(compound);
    compound.putInt("Size", getSize());
    compound.putInt("Leaves", getLeavesConsumed());
  }

  @Override
  public boolean onLivingFall(float distance, float damageMultiplier) {
    float[] ret = net.minecraftforge.common.ForgeHooks.onLivingFall(this, distance, damageMultiplier);
    if (ret == null) return false;
    distance = ret[0];
    damageMultiplier = ret[1];
    // This just handles riding entities
    // super.fall(distance, damageMultiplier);
    EffectInstance potioneffect = this.getActivePotionEffect(Effects.JUMP_BOOST);
    float f = potioneffect == null ? 0.0F : (float) (potioneffect.getAmplifier() + 1);
    int i = MathHelper.ceil((distance - 3.0F - f) * damageMultiplier);

    if (i > 0) {
      this.playSound(this.getFallSound(i), 1.0F, 1.0F);
      // They take no fall damage
      // this.attackEntityFrom(DamageSource.FALL, (float) i);
      int j = MathHelper.floor(this.posX);
      int k = MathHelper.floor(this.posY - 0.2);
      int l = MathHelper.floor(this.posZ);
      BlockState iblockstate = this.world.getBlockState(new BlockPos(j, k, l));

      if (iblockstate.getMaterial() != Material.AIR) {
        SoundType soundtype = iblockstate.getBlock().getSoundType(iblockstate, world, new BlockPos(j, k, l), this);
        this.playSound(soundtype.getFallSound(), soundtype.getVolume() * 0.5F, soundtype.getPitch() * 0.75F);
      }
    }

    return false;
  }

  @Override
  public void tick() {
    super.tick();

    if (!this.world.isRemote) {
      if (this.rand.nextInt(ConfigManager.SILKWORM_CONFIG.getGrowthChance()) == 0) {
        grow();
      }
    }
  }

  public void grow() {
    int size = getSize();
    if (size == MAX_SIZE) {
      setSize(0);
      if (isServerWorld()) {
        int quantity = Math.max(1, Math.min(5, (rand.nextInt(Math.max(getLeavesConsumed() % 8, 1)))));
        this.entityDropItem(ModItems.SILK_COCOON.get(), quantity);
        this.resetLeaves();
        world.playSound(null, posX, posY, posZ, net.minecraft.util.SoundEvents.ENTITY_CHICKEN_EGG, SoundCategory.NEUTRAL, 0.5f, 1.2f + rand.nextFloat() - 0.5f);
      }
    } else {
      incSize();
    }
  }

  @Override
  public boolean attackEntityAsMob(Entity entityIn) {
    if (entityIn instanceof SilkwormEntity) {
      SilkwormEntity other = (SilkwormEntity) entityIn;
      if (other.getSize() > this.getSize()) {
        return false; // Don't attack things that are bigger than us.
      }

      boolean result = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), Integer.MAX_VALUE);
      if (result) {
        grow();
      }
      return result;
    }
    return false;
  }

  @Override
  public CreatureAttribute getCreatureAttribute() {
    return CreatureAttribute.ARTHROPOD;
  }
}

