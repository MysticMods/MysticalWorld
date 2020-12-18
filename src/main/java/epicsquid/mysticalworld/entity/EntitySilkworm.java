package epicsquid.mysticalworld.entity;

import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.config.ConfigManager;
import epicsquid.mysticalworld.events.LeafHandler;
import epicsquid.mysticalworld.init.ModItems;
import epicsquid.mysticalworld.init.ModSounds;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntitySilkworm extends EntityAnimal {
  public static ResourceLocation LOOT_TABLE = new ResourceLocation(MysticalWorld.MODID, "entity/silkworm");

  private static final DataParameter<Integer> SIZE = EntityDataManager.createKey(EntitySilkworm.class, DataSerializers.VARINT);
  private static final DataParameter<Integer> LEAVES_CONSUMED = EntityDataManager.createKey(EntitySilkworm.class, DataSerializers.VARINT);
  private static final DataParameter<Integer> LIFETIME = EntityDataManager.createKey(EntitySilkworm.class, DataSerializers.VARINT);
  private static final int MAX_SIZE = 120;

  private int lastTickPlayed = 0;

  public EntitySilkworm(World worldIn) {
    super(worldIn);
    this.experienceValue = 1;
    this.setSize(0.8F, 0.6F);
    this.getDataManager().register(LIFETIME, 0);
  }

  @Nullable
  @Override
  public EntityAgeable createChild(EntityAgeable ageable) {
    return null;
  }

  @Override
  protected void initEntityAI() {
    this.tasks.addTask(1, new EntityAISwimming(this));
    this.tasks.addTask(2, new EntityAIAttackMelee(this, 1.0D, false));
    this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
    this.tasks.addTask(8, new EntityAILookIdle(this));
    if (ConfigManager.silkworm.territorial) {
      this.targetTasks.addTask(0, new EntityAINearestAttackableTarget<>(this, EntitySilkworm.class, false));
    }
  }

  @Override
  protected void entityInit() {
    super.entityInit();
    this.dataManager.register(SIZE, 0);
    this.dataManager.register(LEAVES_CONSUMED, 0);
  }

  public int getLeavesConsumed() {
    return this.dataManager.get(LEAVES_CONSUMED);
  }

  public void setLeavesConsumed(int amount) {
    this.dataManager.set(LEAVES_CONSUMED, amount);
    this.dataManager.setDirty(LEAVES_CONSUMED);
  }

  public void resetLeaves() {
    this.dataManager.set(LEAVES_CONSUMED, 0);
    this.dataManager.setDirty(LEAVES_CONSUMED);
  }

  private boolean shouldPlaySound() {
    return (ticksExisted - lastTickPlayed) > 8;
  }

  @Override
  public boolean attackEntityAsMob(Entity entityIn) {
    super.attackEntityAsMob(entityIn);
    if (entityIn instanceof EntitySilkworm) {
      return entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), Integer.MAX_VALUE);
    }
    return false;
  }

  public void eatLeaves() {
    incLeaves();
    for (int i = 0; i < ConfigManager.silkworm.initialGrowth + rand.nextInt(ConfigManager.silkworm.additionalGrowth); i++) {
      grow();
    }
    this.heal(1f);
    if (shouldPlaySound()) {
      world.playSound(null, posX, posY, posZ, ModSounds.Silkworm.EAT, SoundCategory.NEUTRAL, 0.5f, 1.2f + rand.nextFloat() * 0.02f);
      lastTickPlayed = ticksExisted;
    }
  }

  private void incLeaves() {
    this.dataManager.set(LEAVES_CONSUMED, getLeavesConsumed() + 1);
    this.dataManager.setDirty(LEAVES_CONSUMED);
  }

  public int getSize() {
    return this.dataManager.get(SIZE);
  }

  public void setSize(int size) {
    this.dataManager.set(SIZE, size);
    this.dataManager.setDirty(SIZE);
  }

  private void incSize() {
    if (!this.isChild()) {
      this.dataManager.set(SIZE, getSize() + 1);
      this.dataManager.setDirty(SIZE);
    }
  }

  @Override
  public boolean processInteract(EntityPlayer player, EnumHand hand) {
    if (!player.world.isRemote) {
      ItemStack itemstack = player.getHeldItem(hand);

      if (LeafHandler.getLeafItems().contains(itemstack.getItem())) {
        if (!player.capabilities.isCreativeMode) {
          itemstack.shrink(1);
        }
        eatLeaves();

        return true;
      }
    }

    return super.processInteract(player, hand);
  }

  @Override
  public float getEyeHeight() {
    return 0.1F;
  }

  @Override
  protected void applyEntityAttributes() {
    super.applyEntityAttributes();
    this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
    this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.15D);
  }

  // TODO: prevent crop trampling

  @Override
  protected SoundEvent getAmbientSound() {
    if (world.rand.nextInt(4) == 0) {
      return ModSounds.Silkworm.AMBIENT;
    }

    return null;
  }

  @Override
  protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
    return ModSounds.Silkworm.HURT;
  }

  @Override
  protected SoundEvent getDeathSound() {
    return ModSounds.Silkworm.DEATH;
  }

  @Override
  protected void playStepSound(BlockPos pos, Block blockIn) {
    this.playSound(ModSounds.Silkworm.STEP, 0.15F, 1.3F + (world.rand.nextFloat() - 0.5f));
  }

  @Override
  @Nullable
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
  public void readEntityFromNBT(NBTTagCompound compound) {
    super.readEntityFromNBT(compound);
    setSize(compound.getInteger("Size"));
    setLeavesConsumed(compound.getInteger("Leaves"));
  }

  @Override
  public void writeEntityToNBT(NBTTagCompound compound) {
    super.writeEntityToNBT(compound);
    compound.setInteger("Size", getSize());
    compound.setInteger("Leaves", getLeavesConsumed());
  }

  @Override
  public void fall(float distance, float damageMultiplier) {
    float[] ret = net.minecraftforge.common.ForgeHooks.onLivingFall(this, distance, damageMultiplier);
    if (ret == null) return;
    distance = ret[0];
    damageMultiplier = ret[1];
    PotionEffect potioneffect = this.getActivePotionEffect(MobEffects.JUMP_BOOST);
    float f = potioneffect == null ? 0.0F : (float) (potioneffect.getAmplifier() + 1);
    int i = MathHelper.ceil((distance - 3.0F - f) * damageMultiplier);

    if (i > 0) {
      this.playSound(this.getFallSound(i), 1.0F, 1.0F);
      int j = MathHelper.floor(this.posX);
      int k = MathHelper.floor(this.posY - 0.2);
      int l = MathHelper.floor(this.posZ);
      IBlockState iblockstate = this.world.getBlockState(new BlockPos(j, k, l));

      if (iblockstate.getMaterial() != Material.AIR) {
        SoundType soundtype = iblockstate.getBlock().getSoundType(iblockstate, world, new BlockPos(j, k, l), this);
        this.playSound(soundtype.getFallSound(), soundtype.getVolume() * 0.5F, soundtype.getPitch() * 0.75F);
      }
    }
  }

  @Override
  public void onLivingUpdate() {
    super.onLivingUpdate();

    if (!this.world.isRemote) {
      if (this.rand.nextInt(ConfigManager.safeInt(ConfigManager.silkworm.growthChance)) == 0) {
        grow();
      }
      if (this.ticksExisted % 10 == 0) {
        if (ConfigManager.silkworm.maxLifetime != -1) {
          int current = this.getDataManager().get(LIFETIME) + 10;
          if (current > ConfigManager.silkworm.maxLifetime) {
            this.attackEntityFrom(DamageSource.WITHER, Integer.MAX_VALUE);
          } else {
            this.getDataManager().set(LIFETIME, current);
            this.getDataManager().setDirty(LIFETIME);
          }
        }
      }
    }
  }

  public void grow() {
    int size = getSize();
    if (size == MAX_SIZE) {
      setSize(0);
      if (isServerWorld()) {
        int quantity = Math.max(1, Math.min(ConfigManager.silkworm.maxCocoons, (rand.nextInt(Math.max(getLeavesConsumed() / 4, 1)))));
        this.dropItem(ModItems.silk_cocoon, quantity);
        this.resetLeaves();
        world.playSound(null, posX, posY, posZ, ModSounds.Silkworm.PLOP, SoundCategory.NEUTRAL, 0.5f, 1.2f + rand.nextFloat() - 0.5f);
      }
    } else {
      incSize();
    }
  }

  @Override
  public EnumCreatureAttribute getCreatureAttribute() {
    return EnumCreatureAttribute.ARTHROPOD;
  }
}

