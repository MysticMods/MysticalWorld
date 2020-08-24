package epicsquid.mysticalworld.entity;

import com.google.common.collect.Sets;
import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.init.ModSounds;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.UUID;

public class EntityLavaCat extends EntityTameable {
  public static ResourceLocation LOOT_TABLE = new ResourceLocation(MysticalWorld.MODID, "entity/lava_cat");

  private static final DataParameter<Boolean> IS_LAVA = EntityDataManager.createKey(EntityLavaCat.class, DataSerializers.BOOLEAN);
  private static final UUID OBSIDIAN_SPEED_DEBUFF_ID = UUID.fromString("f58f95e9-fb51-4604-a66d-89433c9dd8a5");
  private static final AttributeModifier OBSIDIAN_SPEED_DEBUFF = (new AttributeModifier(OBSIDIAN_SPEED_DEBUFF_ID, "Speed debuff for being obsidian", -0.1D, 0)).setSaved(false);

  public EntityLavaCat(World worldIn) {
    super(worldIn);
    this.setSize(0.75F, 0.875F);
  }

  @Override
  protected void initEntityAI() {
    this.aiSit = new EntityAISit(this);
    this.tasks.addTask(1, new EntityAISwimming(this));
    this.tasks.addTask(2, this.aiSit);
    this.tasks.addTask(3, new EntityAITempt(this, 0.6D, false, Sets.newHashSet(Items.BLAZE_ROD, Items.BLAZE_POWDER)));
    this.tasks.addTask(5, new EntityAIFollowOwner(this, 1.0D, 10.0F, 5.0F));
    this.tasks.addTask(7, new EntityAILeapAtTarget(this, 0.3F));
    this.tasks.addTask(8, new EntityAIOcelotAttack(this));
    this.tasks.addTask(9, new EntityAIMate(this, 0.8D));
    this.tasks.addTask(10, new EntityAIWander(this, 0.8D, 1));
    this.tasks.addTask(11, new EntityAIWatchClosest(this, EntityPlayer.class, 10.0F));
    this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
    this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
    this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true));
  }

  @Override
  protected void entityInit() {
    super.entityInit();
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
  protected boolean canDespawn() {
    return !this.isTamed() && this.ticksExisted > 2400;
  }

  @Override
  protected void applyEntityAttributes() {
    super.applyEntityAttributes();
    this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
    this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.4);
    this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.0D);
  }

  @Override
  public void fall(float distance, float damageMultiplier) {
  }

  @Override
  @Nullable
  protected SoundEvent getAmbientSound() {
    if (this.isTamed()) {
      if (this.isInLove()) {
        return ModSounds.LavaCat.PURR;
      } else {
        return this.rand.nextInt(4) == 0 ? ModSounds.LavaCat.PURREOW : ModSounds.LavaCat.AMBIENT;
      }
    } else {
      return null;
    }
  }

  @Override
  protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
    return ModSounds.LavaCat.HURT;
  }

  @Override
  protected SoundEvent getDeathSound() {
    return ModSounds.LavaCat.DEATH;
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
    if (this.isEntityInvulnerable(source)) {
      return false;
    } else {
      if (this.aiSit != null) {
        this.aiSit.setSitting(false);
      }

      if (source != null && source.isFireDamage()) {
        return false;
      }

      if (source == DamageSource.WITHER || source == DamageSource.ANVIL) {
        // You can't really damage stone (liquid or otherwise)
        return false;
      }

      boolean lava = getIsLava();

      // Obsidian cats take half damage from non-magic damage
      if (!lava && (source == null || !source.isMagicDamage())) {
        amount /= 2;
      }

      // Lava cats take half damage from magic
      if (lava && (source != null && source.isMagicDamage())) {
        amount /= 2;
      }

      // They don't take damage from their owners unless sneaking
      if (isTamed() && source != null && source.getTrueSource() != null && source.getTrueSource() == getOwner() && !source.getTrueSource().isSneaking()) {
        return false;
      }

      return super.attackEntityFrom(source, amount);
    }
  }

  @Override
  public void addPotionEffect(PotionEffect potioneffectIn) {
    Potion type = potioneffectIn.getPotion();
    if (type == MobEffects.POISON || type == MobEffects.WITHER) {
      return;
    }

    super.addPotionEffect(potioneffectIn);
  }

  @Override
  @Nullable
  public ResourceLocation getLootTable() {
    return LOOT_TABLE;
  }

  @Override
  public boolean processInteract(EntityPlayer player, EnumHand hand) {
    ItemStack itemstack = player.getHeldItem(hand);

    if (this.isTamed()) {
      if (this.isOwner(player) && !this.world.isRemote && !this.isBreedingItem(itemstack)) {
        this.aiSit.setSitting(!this.isSitting());
      } else if (this.isOwner(player) && !itemstack.isEmpty()) {
        boolean flag = this.handleEating(player, itemstack);
        if (flag) {
          if (!player.capabilities.isCreativeMode) {
            itemstack.shrink(1);
          }
        }

        return flag;
      }
    } else if (itemstack.getItem() == Items.BLAZE_ROD && player.getDistanceSq(this) < 9.0D) {
      if (!player.capabilities.isCreativeMode) {
        itemstack.shrink(1);
      }

      if (!this.world.isRemote) {
        if (this.rand.nextInt(3) == 0 && !net.minecraftforge.event.ForgeEventFactory.onAnimalTame(this, player)) {
          this.setTamedBy(player);
          this.playTameEffect(true);
          this.aiSit.setSitting(true);
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

    if (item == Items.BLAZE_POWDER) {
      f = 2.0F;
      i = 20;
    } else if (item == Items.MAGMA_CREAM) {
      f = 4.0f;
      i = 40;
    } else if (item == Items.BLAZE_ROD) {
      f = 8.0f;
      i = 80;
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
  public EntityLavaCat createChild(EntityAgeable ageable) {
    EntityLavaCat lavacat = new EntityLavaCat(this.world);

    if (this.isTamed()) {
      lavacat.setOwnerId(this.getOwnerId());
      lavacat.setTamed(true);
      lavacat.setIsLava(getIsLava());
    }

    return lavacat;
  }

  @Override
  public boolean isBreedingItem(ItemStack stack) {
    return stack.getItem() == Items.BLAZE_POWDER;
  }

  public boolean getIsLava() {
    return this.dataManager.get(IS_LAVA);
  }

  public void setIsLava(boolean val) {
    this.dataManager.set(IS_LAVA, val);
    this.dataManager.setDirty(IS_LAVA);
    IAttributeInstance attr = getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED);
    if (val) {
      attr.removeModifier(OBSIDIAN_SPEED_DEBUFF);
    } else {
      attr.applyModifier(OBSIDIAN_SPEED_DEBUFF);
    }
  }

  @Override
  public boolean getCanSpawnHere() {
    return this.world.rand.nextInt(3) != 0;
  }

  @Override
  public boolean isNotColliding() {
    if (this.world.checkNoEntityCollision(this.getEntityBoundingBox(), this) && this.world.getCollisionBoxes(this, this.getEntityBoundingBox()).isEmpty() && !this.world.containsAnyLiquid(this.getEntityBoundingBox())) {
      BlockPos blockpos = new BlockPos(this.posX, this.getEntityBoundingBox().minY, this.posZ);
      IBlockState iblockstate = this.world.getBlockState(blockpos.down());

      return iblockstate.isSideSolid(this.world, blockpos, EnumFacing.UP);
    }

    return false;
  }

  @Override
  protected void setupTamedAI() {
  }

  @Override
  public void onLivingUpdate() {
    super.onLivingUpdate();

    if (getIsLava() && world.isRainingAt(getPosition()) && world.canSeeSky(getPosition()) && rand.nextInt(30) == 0) {
      world.playSound(null, posX, posY, posZ, ModSounds.LavaCat.SIZZLE, SoundCategory.NEUTRAL, 0.2f, 1.3f);
    }

    if (getIsLava() && inWater) {
      setIsLava(false);
    } else if (!getIsLava() && isInLava()) {
      setIsLava(true);
    }
  }

  @Override
  public void readEntityFromNBT(NBTTagCompound compound) {
    super.readEntityFromNBT(compound);
    setIsLava(compound.getBoolean("IsLava"));
  }

  @Override
  public void writeEntityToNBT(NBTTagCompound compound) {
    super.writeEntityToNBT(compound);
    compound.setBoolean("IsLava", getIsLava());
  }

  @Override
  @SuppressWarnings("deprecation")
  public String getName() {
    if (this.hasCustomName()) {
      return this.getCustomNameTag();
    } else {
      return this.getIsLava() ? I18n.translateToLocal("entity.entity_lava_cat.name") : I18n.translateToLocal("entity.entity_obsidian_cat.name");
    }
  }

  @Nullable
  @Override
  public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
    this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).applyModifier(new AttributeModifier("Random spawn bonus", this.rand.nextGaussian() * 0.05D, 1));

    if (this.rand.nextFloat() < 0.05F) {
      this.setLeftHanded(true);
    } else {
      this.setLeftHanded(false);
    }

    return livingdata;
  }
}

