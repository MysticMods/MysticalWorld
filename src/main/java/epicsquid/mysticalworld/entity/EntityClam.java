package epicsquid.mysticalworld.entity;

import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.config.ConfigManager;
import net.minecraft.block.material.Material;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class EntityClam extends EntityWaterMob {
  public static final ResourceLocation LOOT_TABLE = new ResourceLocation(MysticalWorld.MODID, "entity/clam");

  public static final DataParameter<Boolean> ender = EntityDataManager.createKey(EntityClam.class, DataSerializers.BOOLEAN);
  public static final DataParameter<Integer> age = EntityDataManager.createKey(EntityClam.class, DataSerializers.VARINT);

  public EntityClam(@Nonnull World world) {
    super(world);
    setSize(0.75f, 0.75f);
    this.experienceValue = 3;
    this.rotationYaw = MathHelper.wrapDegrees(rand.nextFloat());
    this.prevRotationYaw = this.rotationYaw;
    this.setRotationYawHead(this.rotationYaw);
    this.setRenderYawOffset(this.rotationYaw);
    this.setRotation(this.rotationYaw, this.rotationPitch);
  }

  @Override
  public void addPotionEffect(PotionEffect potioneffectIn) {
    super.addPotionEffect(potioneffectIn);
  }

  @Override
  protected void entityInit() {
    super.entityInit();
    this.getDataManager().register(ender, rand.nextInt(ConfigManager.clam.ender) == 0);
    this.getDataManager().register(age, rand.nextInt(ConfigManager.clam.initialAge));
  }

  @Override
  protected void initEntityAI() {
    /*    this.tasks.addTask(0, new EntityAISwimming(this));*/
  }

  @Override
  protected void applyEntityAttributes() {
    super.applyEntityAttributes();
    this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
    this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0);
  }

  @Override
  public void onEntityUpdate() {
    getDataManager().set(age, getDataManager().get(age) + 1);
    super.onEntityUpdate();
  }

  @Override
  @Nonnull
  public ResourceLocation getLootTable() {
    return LOOT_TABLE;
  }

  @Override
  public float getEyeHeight() {
    return this.isChild() ? this.height : 1.3F;
  }

  @Override
  protected boolean canTriggerWalking() {
    return false;
  }

  @Override
  public boolean getCanSpawnHere() {
    return this.posY < (double) this.world.getSeaLevel() && world.getBlockState(getPosition()).getMaterial() == Material.WATER && super.getCanSpawnHere()/* && (ConfigManager.clam.spawn_failure >= 0 || rand.nextInt(ConfigManager.clam.spawn_failure) != 0)*/;
  }

  @Override
  protected boolean canDespawn() {
    return false;
  }

  @Override
  public void readEntityFromNBT(@Nonnull NBTTagCompound compound) {
    super.readEntityFromNBT(compound);
    getDataManager().set(ender, compound.getBoolean("ender"));
    getDataManager().setDirty(ender);
    getDataManager().set(age, compound.getInteger("age"));
    getDataManager().setDirty(age);
  }

  @Override
  public void writeEntityToNBT(@Nonnull NBTTagCompound compound) {
    super.writeEntityToNBT(compound);
    compound.setBoolean("ender", getDataManager().get(ender));
    compound.setInteger("age", getDataManager().get(age));
  }

  @Override
  public void onLivingUpdate() {
    if (this.world.isRemote) {
      if (this.dataManager.get(age) >= ConfigManager.clam.maturity) {
        EnumParticleTypes type = this.dataManager.get(ender) ? EnumParticleTypes.PORTAL : EnumParticleTypes.WATER_BUBBLE;
        for (int i = 0; i < 2; ++i) {
          this.world.spawnParticle(type, this.posX + (this.rand.nextDouble() - 0.5D) * (double) this.width, this.posY + this.rand.nextDouble() * (double) this.height - 0.25D, this.posZ + (this.rand.nextDouble() - 0.5D) * (double) this.width, (this.rand.nextDouble() - 0.5D) * 2.0D, -this.rand.nextDouble(), (this.rand.nextDouble() - 0.5D) * 2.0D);
        }
      }
    }

    super.onLivingUpdate();
  }
}