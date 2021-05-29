package epicsquid.mysticalworld.entity;

import com.google.common.base.Optional;
import com.google.common.collect.Sets;
import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.entity.ai.EntityAIStalk;
import epicsquid.mysticalworld.init.ModSounds;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;
import java.util.Set;

public class EntityEndermini extends EntityCreature {
  public static final ResourceLocation LOOT_TABLE = new ResourceLocation(MysticalWorld.MODID, "entity/endermini");
  private static final Set<Block> CARRIABLE_BLOCKS = Sets.newIdentityHashSet();
  private static final DataParameter<Optional<IBlockState>> CARRIED_BLOCK = EntityDataManager.createKey(EntityEndermini.class, DataSerializers.OPTIONAL_BLOCK_STATE);
  private static final DataParameter<Boolean> SCREAMING = EntityDataManager.createKey(EntityEndermini.class, DataSerializers.BOOLEAN);
  private int lastCreepySound;
  private int targetChangeTime;

  public EntityEndermini(World worldIn) {
    super(worldIn);

    this.setSize(0.3F, 1.45F);
    this.stepHeight = 1.0F;
    this.setPathPriority(PathNodeType.WATER, -1.0F);
  }

  @Override
  protected void initEntityAI() {
    this.tasks.addTask(0, new EntityAISwimming(this));
    this.tasks.addTask(1, new EntityAIPanic(this, 1.5D));
    this.tasks.addTask(2, new EntityAIStalk(this, 2.0D, false));
    this.tasks.addTask(7, new EntityAIWanderAvoidWater(this, 1.0D, 0.0F));
    this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 32.0F));
    this.tasks.addTask(8, new EntityAILookIdle(this));
    this.tasks.addTask(10, new EntityEndermini.AIPlaceBlock(this));
    this.tasks.addTask(11, new EntityEndermini.AITakeBlock(this));
    this.targetTasks.addTask(1, new EntityEndermini.AIFindPlayer(this));
    this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true));
  }

  @Override
  protected void applyEntityAttributes() {
    super.applyEntityAttributes();
    this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
    this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
    this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.35D);
    this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(0.5D);
    this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(64.0D);
  }

  @Override
  public void setAttackTarget(@Nullable EntityLivingBase entitylivingbaseIn) {
    super.setAttackTarget(entitylivingbaseIn);
    if (entitylivingbaseIn == null) {
      this.targetChangeTime = 0;
      this.dataManager.set(SCREAMING, false);
    } else {
      this.targetChangeTime = this.ticksExisted;
      this.dataManager.set(SCREAMING, true);
    }
  }

  @Override
  protected void entityInit() {
    super.entityInit();
    this.dataManager.register(CARRIED_BLOCK, Optional.absent());
    this.dataManager.register(SCREAMING, false);
  }

  public void playEnderminiSound() {
    if (this.ticksExisted >= this.lastCreepySound + 400) {
      this.lastCreepySound = this.ticksExisted;

      if (!this.isSilent()) {
        this.world.playSound(this.posX, this.posY + (double) this.getEyeHeight(), this.posZ, ModSounds.Endermini.STARE, this.getSoundCategory(), 2.5F, 1.0F, false);
      }
    }
  }

  @Override
  public void notifyDataManagerChange(DataParameter<?> key) {
    if (SCREAMING.equals(key) && this.isScreaming() && this.world.isRemote) {
      this.playEnderminiSound();
    }

    super.notifyDataManagerChange(key);
  }

  @Override
  public void writeEntityToNBT(NBTTagCompound compound) {
    super.writeEntityToNBT(compound);
    IBlockState iblockstate = this.getHeldBlockState();

    if (iblockstate != null) {
      compound.setShort("carried", (short) Block.getIdFromBlock(iblockstate.getBlock()));
      compound.setShort("carriedData", (short) iblockstate.getBlock().getMetaFromState(iblockstate));
    }
  }

  @SuppressWarnings("deprecation")
  @Override
  public void readEntityFromNBT(NBTTagCompound compound) {
    super.readEntityFromNBT(compound);
    IBlockState iblockstate;

    if (compound.hasKey("carried", 8)) {
      iblockstate = Block.getBlockFromName(compound.getString("carried")).getStateFromMeta(compound.getShort("carriedData") & 65535);
    } else {
      iblockstate = Block.getBlockById(compound.getShort("carried")).getStateFromMeta(compound.getShort("carriedData") & 65535);
    }

    if (iblockstate == null || iblockstate.getBlock() == null || iblockstate.getMaterial() == Material.AIR) {
      iblockstate = null;
    }

    this.setHeldBlockState(iblockstate);
  }

  @Override
  public float getEyeHeight() {
    return 1F;
  }

  @Override
  public void onLivingUpdate() {
    if (this.world.isRemote) {
      for (int i = 0; i < 2; ++i) {
        this.world.spawnParticle(EnumParticleTypes.PORTAL, this.posX + (this.rand.nextDouble() - 0.5D) * (double) this.width, this.posY + this.rand.nextDouble() * (double) this.height - 0.25D, this.posZ + (this.rand.nextDouble() - 0.5D) * (double) this.width, (this.rand.nextDouble() - 0.5D) * 2.0D, -this.rand.nextDouble(), (this.rand.nextDouble() - 0.5D) * 2.0D);
      }
    }

    this.isJumping = false;
    super.onLivingUpdate();
  }

  @Override
  protected void updateAITasks() {
    if (this.isWet()) {
      this.attackEntityFrom(DamageSource.DROWN, 1.0F);
    }

    if (this.ticksExisted >= this.targetChangeTime + 600) {
      if (this.world.canSeeSky(new BlockPos(this)) && this.rand.nextFloat() * 30.0F < 1.2F) {
        this.setAttackTarget(null);
        this.teleportRandomly();
      }
    }

    super.updateAITasks();
  }

  protected boolean teleportRandomly() {
    double d0 = this.posX + (this.rand.nextDouble() - 0.5D) * 64.0D;
    double d1 = this.posY + (double) (this.rand.nextInt(64) - 32);
    double d2 = this.posZ + (this.rand.nextDouble() - 0.5D) * 64.0D;
    return this.teleportTo(d0, d1, d2);
  }

  protected boolean teleportToEntity(Entity p_70816_1_) {
    Vec3d vec3d = new Vec3d(this.posX - p_70816_1_.posX, this.getEntityBoundingBox().minY + (double) (this.height / 2.0F) - p_70816_1_.posY + (double) p_70816_1_.getEyeHeight(), this.posZ - p_70816_1_.posZ);
    vec3d = vec3d.normalize();
    double d1 = this.posX + (this.rand.nextDouble() - 0.5D) * 8.0D - vec3d.x * 16.0D;
    double d2 = this.posY + (double) (this.rand.nextInt(16) - 8) - vec3d.y * 16.0D;
    double d3 = this.posZ + (this.rand.nextDouble() - 0.5D) * 8.0D - vec3d.z * 16.0D;
    return this.teleportTo(d1, d2, d3);
  }

  /**
   * Teleport the endermini
   */
  private boolean teleportTo(double x, double y, double z) {
    net.minecraftforge.event.entity.living.EnderTeleportEvent event = new net.minecraftforge.event.entity.living.EnderTeleportEvent(this, x, y, z, 0);
    if (net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(event)) return false;
    boolean flag = this.attemptTeleport(event.getTargetX(), event.getTargetY(), event.getTargetZ());

    if (flag) {
      this.world.playSound(null, this.prevPosX, this.prevPosY, this.prevPosZ, ModSounds.Endermini.PORTAL, this.getSoundCategory(), 1.0F, 1.0F);
      this.playSound(ModSounds.Endermini.PORTAL, 1.0F, 1.0F);
    }

    return flag;
  }

  @Override
  protected SoundEvent getAmbientSound() {
    return this.isScreaming() ? ModSounds.Endermini.SCREAM : ModSounds.Endermini.IDLE;
  }

  @Override
  protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
    return ModSounds.Endermini.HIT;
  }

  @Override
  protected SoundEvent getDeathSound() {
    return ModSounds.Endermini.DEATH;
  }

  @Override
  protected void dropEquipment(boolean wasRecentlyHit, int lootingModifier) {
    super.dropEquipment(wasRecentlyHit, lootingModifier);
    IBlockState iblockstate = this.getHeldBlockState();

    if (iblockstate != null) {
      Item item = Item.getItemFromBlock(iblockstate.getBlock());
      int i = item.getHasSubtypes() ? iblockstate.getBlock().getMetaFromState(iblockstate) : 0;
      this.entityDropItem(new ItemStack(item, 1, i), 0.0F);
    }
  }

  @Override
  @Nullable
  public ResourceLocation getLootTable() {
    return LOOT_TABLE;
  }

  /**
   * Sets this endermini's held block state
   */
  public void setHeldBlockState(@Nullable IBlockState state) {
    this.dataManager.set(CARRIED_BLOCK, Optional.fromNullable(state));
  }

  /**
   * Gets this endermini's held block state
   */
  @Nullable
  public IBlockState getHeldBlockState() {
    return (IBlockState) ((Optional) this.dataManager.get(CARRIED_BLOCK)).orNull();
  }

  /**
   * Called when the entity is attacked.
   */
  @Override
  public boolean attackEntityFrom(DamageSource source, float amount) {
    if (this.isEntityInvulnerable(source)) {
      return false;
    } else if (source instanceof EntityDamageSourceIndirect) {
      for (int i = 0; i < 64; ++i) {
        if (this.teleportRandomly()) {
          return true;
        }
      }

      return false;
    } else {
      boolean flag = super.attackEntityFrom(source, amount);

      if (source.isUnblockable() && this.rand.nextInt(10) != 0) {
        this.teleportRandomly();
      }

      return flag;
    }
  }

  public static void setCarriable(Block block, boolean canCarry) {
    if (canCarry) CARRIABLE_BLOCKS.add(block);
    else CARRIABLE_BLOCKS.remove(block);
  }

  public static boolean getCarriable(Block block) {
    return CARRIABLE_BLOCKS.contains(block);
  }


  public boolean isScreaming() {
    return this.dataManager.get(SCREAMING);
  }

  private boolean shouldFollowPlayer(EntityPlayer player) {
    ItemStack itemstack = player.inventory.armorInventory.get(3);

    if (itemstack.getItem() == Item.getItemFromBlock(Blocks.PUMPKIN)) {
      return false;
    } else {
      Vec3d vec3d = player.getLook(1.0F).normalize();
      Vec3d vec3d1 = new Vec3d(this.posX - player.posX, this.getEntityBoundingBox().minY + (double) this.getEyeHeight() - (player.posY + (double) player.getEyeHeight()), this.posZ - player.posZ);
      double d0 = vec3d1.length();
      vec3d1 = vec3d1.normalize();
      double d1 = vec3d.dotProduct(vec3d1);
      return d1 > 1.0D - 0.025D / d0 && player.canEntityBeSeen(this);
    }
  }


  static {
    CARRIABLE_BLOCKS.add(Blocks.GRASS);
    CARRIABLE_BLOCKS.add(Blocks.DIRT);
    CARRIABLE_BLOCKS.add(Blocks.SAND);
    CARRIABLE_BLOCKS.add(Blocks.GRAVEL);
    CARRIABLE_BLOCKS.add(Blocks.YELLOW_FLOWER);
    CARRIABLE_BLOCKS.add(Blocks.RED_FLOWER);
    CARRIABLE_BLOCKS.add(Blocks.BROWN_MUSHROOM);
    CARRIABLE_BLOCKS.add(Blocks.RED_MUSHROOM);
    CARRIABLE_BLOCKS.add(Blocks.TNT);
    CARRIABLE_BLOCKS.add(Blocks.CACTUS);
    CARRIABLE_BLOCKS.add(Blocks.CLAY);
    CARRIABLE_BLOCKS.add(Blocks.PUMPKIN);
    CARRIABLE_BLOCKS.add(Blocks.MELON_BLOCK);
    CARRIABLE_BLOCKS.add(Blocks.MYCELIUM);
    CARRIABLE_BLOCKS.add(Blocks.NETHERRACK);
  }

  static class AIFindPlayer extends EntityAINearestAttackableTarget<EntityPlayer> {
    private final EntityEndermini endermini;
    /**
     * The player
     */
    private EntityPlayer player;
    private int aggroTime;
    private int teleportTime;

    public AIFindPlayer(EntityEndermini p_i45842_1_) {
      super(p_i45842_1_, EntityPlayer.class, false);
      this.endermini = p_i45842_1_;
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    @Override
    public boolean shouldExecute() {
      double d0 = this.getTargetDistance();
      this.player = this.endermini.world.getNearestAttackablePlayer(this.endermini.posX, this.endermini.posY, this.endermini.posZ, d0, d0, null, p_apply_1_ -> p_apply_1_ != null && AIFindPlayer.this.endermini.shouldFollowPlayer(p_apply_1_));
      return this.player != null;
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    @Override
    public void startExecuting() {
      this.aggroTime = 5;
      this.teleportTime = 0;
    }

    /**
     * Reset the task's thaumcraft.internal state. Called when this task is interrupted by another one
     */
    @Override
    public void resetTask() {
      this.player = null;
      super.resetTask();
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    @Override
    public boolean shouldContinueExecuting() {
      if (this.player != null) {
        if (!this.endermini.shouldFollowPlayer(this.player)) {
          return false;
        } else {
          this.endermini.faceEntity(this.player, 10.0F, 10.0F);
          return true;
        }
      } else {
        return this.targetEntity != null && this.targetEntity.isEntityAlive() || super.shouldContinueExecuting();
      }
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    @Override
    public void updateTask() {
      if (this.player != null) {
        if (--this.aggroTime <= 0) {
          this.targetEntity = this.player;
          this.player = null;
          super.startExecuting();
        }
      } else {
        if (this.targetEntity != null) {
          if (this.endermini.shouldFollowPlayer(this.targetEntity)) {
            if (this.targetEntity.getDistanceSq(this.endermini) < 16.0D) {
              this.endermini.teleportRandomly();
            }

            this.teleportTime = 0;
          } else if (this.targetEntity.getDistanceSq(this.endermini) > 256.0D && this.teleportTime++ >= 30 && this.endermini.teleportToEntity(this.targetEntity)) {
            this.teleportTime = 0;
          }
        }

        super.updateTask();
      }
    }
  }

  static class AIPlaceBlock extends EntityAIBase {
    private final EntityEndermini endermini;

    public AIPlaceBlock(EntityEndermini p_i45843_1_) {
      this.endermini = p_i45843_1_;
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    @Override
    public boolean shouldExecute() {
      if (this.endermini.getHeldBlockState() == null) {
        return false;
      } else if (!net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.endermini.world, this.endermini)) {
        return false;
      } else {
        return this.endermini.getRNG().nextInt(2000) == 0;
      }
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    @Override
    public void updateTask() {
      Random random = this.endermini.getRNG();
      World world = this.endermini.world;
      int i = MathHelper.floor(this.endermini.posX - 1.0D + random.nextDouble() * 2.0D);
      int j = MathHelper.floor(this.endermini.posY + random.nextDouble() * 2.0D);
      int k = MathHelper.floor(this.endermini.posZ - 1.0D + random.nextDouble() * 2.0D);
      BlockPos blockpos = new BlockPos(i, j, k);
      IBlockState iblockstate = world.getBlockState(blockpos);
      IBlockState iblockstate1 = world.getBlockState(blockpos.down());
      IBlockState iblockstate2 = this.endermini.getHeldBlockState();

      if (iblockstate2 != null && this.canPlaceBlock(world, blockpos, iblockstate2.getBlock(), iblockstate, iblockstate1)) {
        world.setBlockState(blockpos, iblockstate2, 3);
        this.endermini.setHeldBlockState(null);
      }
    }

    private boolean canPlaceBlock(World p_188518_1_, BlockPos p_188518_2_, Block p_188518_3_, IBlockState p_188518_4_, IBlockState p_188518_5_) {
      if (!p_188518_3_.canPlaceBlockAt(p_188518_1_, p_188518_2_)) {
        return false;
      } else if (p_188518_4_.getMaterial() != Material.AIR) {
        return false;
      } else if (p_188518_5_.getMaterial() == Material.AIR) {
        return false;
      } else {
        return p_188518_5_.isFullCube();
      }
    }
  }

  static class AITakeBlock extends EntityAIBase {
    private final EntityEndermini endermini;

    public AITakeBlock(EntityEndermini p_i45841_1_) {
      this.endermini = p_i45841_1_;
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    @Override
    public boolean shouldExecute() {
      if (this.endermini.getHeldBlockState() != null) {
        return false;
      } else if (!net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.endermini.world, this.endermini)) {
        return false;
      } else {
        return this.endermini.getRNG().nextInt(20) == 0;
      }
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    @Override
    public void updateTask() {
      Random random = this.endermini.getRNG();
      World world = this.endermini.world;
      int i = MathHelper.floor(this.endermini.posX - 2.0D + random.nextDouble() * 4.0D);
      int j = MathHelper.floor(this.endermini.posY + random.nextDouble() * 3.0D);
      int k = MathHelper.floor(this.endermini.posZ - 2.0D + random.nextDouble() * 4.0D);
      BlockPos blockpos = new BlockPos(i, j, k);
      IBlockState iblockstate = world.getBlockState(blockpos);
      Block block = iblockstate.getBlock();
      RayTraceResult raytraceresult = world.rayTraceBlocks(new Vec3d((double) ((float) MathHelper.floor(this.endermini.posX) + 0.5F), (double) ((float) j + 0.5F), (double) ((float) MathHelper.floor(this.endermini.posZ) + 0.5F)), new Vec3d((double) ((float) i + 0.5F), (double) ((float) j + 0.5F), (double) ((float) k + 0.5F)), false, true, false);
      boolean flag = raytraceresult != null && raytraceresult.getBlockPos().equals(blockpos);

      if (EntityEndermini.CARRIABLE_BLOCKS.contains(block) && flag) {
        this.endermini.setHeldBlockState(iblockstate);
        world.setBlockToAir(blockpos);
      }
    }
  }
}
