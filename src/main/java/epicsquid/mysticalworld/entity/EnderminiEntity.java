package epicsquid.mysticalworld.entity;

import com.google.common.collect.Sets;
import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.entity.ai.StalkGoal;
import epicsquid.mysticalworld.init.ModSounds;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.EndermiteEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IndirectEntityDamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.*;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.function.Predicate;

// Base heavily on vanilla Enderman
@SuppressWarnings("deprecation")
public class EnderminiEntity extends CreatureEntity {
  public static final ResourceLocation LOOT_TABLE = new ResourceLocation(MysticalWorld.MODID, "entity/endermini");
  private static final Set<Block> CARRIABLE_BLOCKS = Sets.newIdentityHashSet();
  private static final DataParameter<java.util.Optional<BlockState>> CARRIED_BLOCK = EntityDataManager.createKey(EnderminiEntity.class, DataSerializers.OPTIONAL_BLOCK_STATE);
  private static final DataParameter<Boolean> SCREAMING = EntityDataManager.createKey(EnderminiEntity.class, DataSerializers.BOOLEAN);
  private int lastCreepySound;
  private int targetChangeTime;
  private static final Predicate<LivingEntity> endermitePredicate = (entity) -> entity instanceof EndermiteEntity && ((EndermiteEntity) entity).isSpawnedByPlayer();

  public EnderminiEntity(EntityType<? extends EnderminiEntity> type, World worldIn) {
    super(type, worldIn);
    this.stepHeight = 1.0F;
    this.setPathPriority(PathNodeType.WATER, -1.0F);
  }

  @Override
  protected void registerGoals() {
    goalSelector.addGoal(0, new SwimGoal(this));
    goalSelector.addGoal(1, new PanicGoal(this, 1.5D));
    goalSelector.addGoal(2, new StalkGoal(this, 2.0D, false));
    goalSelector.addGoal(7, new WaterAvoidingRandomWalkingGoal(this, 1.0D, 0.0F));
    goalSelector.addGoal(8, new LookAtWithoutMovingGoal(this, PlayerEntity.class, 32.0F, 1.0f));
    goalSelector.addGoal(8, new LookRandomlyGoal(this));
    goalSelector.addGoal(10, new PlaceBlockGoal(this));
    goalSelector.addGoal(11, new EnderminiEntity.TakeBlockGoal(this));
    targetSelector.addGoal(1, new FindPlayerGoal(this));
    targetSelector.addGoal(2, new HurtByTargetGoal(this, PlayerEntity.class));
    targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, EndermiteEntity.class, 10, true, false, endermitePredicate));
  }

  @Override
  protected void registerAttributes() {
    super.registerAttributes();
    getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
    getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
    getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.35D);
    getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(0.5D);
    getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(64.0D);
  }

  @Override
  public void setAttackTarget(@Nullable LivingEntity entitylivingbaseIn) {
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
  protected void registerData() {
    super.registerData();
    this.dataManager.register(CARRIED_BLOCK, Optional.empty());
    this.dataManager.register(SCREAMING, false);
  }

  public void playEnderminiSound() {
    if (this.ticksExisted >= this.lastCreepySound + 400) {
      this.lastCreepySound = this.ticksExisted;

      if (!this.isSilent()) {
        this.world.playSound(null, this.posX, this.posY + (double) this.getEyeHeight(), this.posZ, ModSounds.ENDERMINI_STARE.get(), this.getSoundCategory(), 2.5F, 1.0F);
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
  public void writeAdditional(CompoundNBT compound) {
    super.writeAdditional(compound);
    BlockState iblockstate = getHeldBlockState();

    if (iblockstate != null) {
      compound.putShort("carried", (short) Block.getStateId(iblockstate));
    }
  }

  @Override
  public void readAdditional(CompoundNBT compound) {
    super.readAdditional(compound);
    BlockState iblockstate = null;

    if (compound.contains("carried", 8)) {
      iblockstate = Block.getStateById(compound.getShort("carried"));
    }

    if (iblockstate == null || iblockstate.getBlock() == null || iblockstate.getMaterial() == Material.AIR) {
      iblockstate = null;
    }

    this.setHeldBlock(iblockstate);
  }

  @Override
  public float getEyeHeight(Pose p_213307_1_) {
    return 1F;
  }

  @Override
  public void livingTick() {
    if (this.world.isRemote) {
      for (int i = 0; i < 2; ++i) {
        this.world.addParticle(ParticleTypes.PORTAL, this.posX + (this.rand.nextDouble() - 0.5D) * (double) this.getWidth(),
            this.posY + this.rand.nextDouble() * (double) this.getHeight() - 0.25D, this.posZ + (this.rand.nextDouble() - 0.5D) * (double) this.getWidth(),
            (this.rand.nextDouble() - 0.5D) * 2.0D, -this.rand.nextDouble(), (this.rand.nextDouble() - 0.5D) * 2.0D);
      }
    }

    this.isJumping = false;
    super.livingTick();
  }

  @Override
  protected void updateAITasks() {
    if (this.isWet()) {
      this.attackEntityFrom(DamageSource.DROWN, 1.0F);
    }

    if (this.ticksExisted >= this.targetChangeTime + 600) {
      if (this.world.canBlockSeeSky(new BlockPos(this)) && this.rand.nextFloat() * 30.0F < 1.2F) {
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
    Vec3d vec3d = new Vec3d(this.posX - p_70816_1_.posX,
        this.getBoundingBox().minY + (double) (this.getHeight() / 2.0F) - p_70816_1_.posY + (double) p_70816_1_.getEyeHeight(), this.posZ - p_70816_1_.posZ);
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
    if (net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(event))
      return false;
    boolean flag = this.attemptTeleport(event.getTargetX(), event.getTargetY(), event.getTargetZ(), false);

    if (flag) {
      this.world.playSound(null, this.prevPosX, this.prevPosY, this.prevPosZ, ModSounds.ENDERMINI_PORTAL.get(), this.getSoundCategory(), 1.0F, 1.0F);
      /*this.playSound(ModSounds.Endermini.ENDERMINI_PORTAL.get(), 1.0F, 1.0F);*/
    }

    return flag;
  }

  @Override
  protected SoundEvent getAmbientSound() {
    return this.isScreaming() ? ModSounds.ENDERMINI_SCREAM.get() : ModSounds.ENDERMINI_IDLE.get();
  }

  @Override
  protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
    return ModSounds.ENDERMINI_HIT.get();
  }

  @Override
  protected SoundEvent getDeathSound() {
    return ModSounds.ENDERMINI_DEATH.get();
  }

  @Override
  protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) {
    super.dropSpecialItems(source, looting, recentlyHitIn);
    BlockState iblockstate = this.getHeldBlockState();

    if (iblockstate != null) {
      Item item = Item.getItemFromBlock(iblockstate.getBlock());
      this.entityDropItem(new ItemStack(item, 1), 0.0F);
    }
  }

  @Override
  @Nullable
  public ResourceLocation getLootTable() {
    return new ResourceLocation(MysticalWorld.MODID, "entities/endermini");
  }

  /**
   * Sets this endermini's held block state
   */
  public void setHeldBlock(@Nullable BlockState state) {
    this.dataManager.set(CARRIED_BLOCK, Optional.ofNullable(state));
  }

  /**
   * Gets this endermini's held block state
   */
  @Nullable
  public BlockState getHeldBlockState() {
    return (BlockState) ((Optional) this.dataManager.get(CARRIED_BLOCK)).orElse(null);
  }

  /**
   * Called when the entity is attacked.
   */
  @Override
  public boolean attackEntityFrom(DamageSource source, float amount) {
    if (isInvulnerableTo(source)) {
      return false;
    } else if (source instanceof IndirectEntityDamageSource) {
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
    if (canCarry)
      CARRIABLE_BLOCKS.add(block);
    else
      CARRIABLE_BLOCKS.remove(block);
  }

  public static boolean getCarriable(Block block) {
    return CARRIABLE_BLOCKS.contains(block);
  }

  public boolean isScreaming() {
    return this.dataManager.get(SCREAMING);
  }

  private boolean shouldFollowPlayer(PlayerEntity player) {
    ItemStack itemstack = player.inventory.armorInventory.get(3);

    if (itemstack.getItem() == Items.PUMPKIN) {
      return false;
    } else {
      Vec3d vec3d = player.getLook(1.0F).normalize();
      Vec3d vec3d1 = new Vec3d(this.posX - player.posX,
          this.getBoundingBox().minY + (double) this.getEyeHeight() - (player.posY + (double) player.getEyeHeight()), this.posZ - player.posZ);
      double d0 = vec3d1.length();
      vec3d1 = vec3d1.normalize();
      double d1 = vec3d.dotProduct(vec3d1);
      return d1 > 1.0D - 0.025D / d0 && player.canEntityBeSeen(this);
    }
  }

  /**
   * Checks to see if this enderman should be attacking this player
   */
  private boolean shouldAttackPlayer(PlayerEntity player) {
    ItemStack itemstack = player.inventory.armorInventory.get(3);
    if (itemstack.getItem() == Blocks.CARVED_PUMPKIN.asItem()) {
      return false;
    } else {
      Vec3d vec3d = player.getLook(1.0F).normalize();
      Vec3d vec3d1 = new Vec3d(this.posX - player.posX, this.getBoundingBox().minY + (double) this.getEyeHeight() - (player.posY + (double) player.getEyeHeight()), this.posZ - player.posZ);
      double d0 = vec3d1.length();
      vec3d1 = vec3d1.normalize();
      double d1 = vec3d.dotProduct(vec3d1);
      return d1 > 1.0D - 0.025D / d0 && player.canEntityBeSeen(this);
    }
  }

  public void func_195406_b(@Nullable BlockState p_195406_1_) {
    this.dataManager.set(CARRIED_BLOCK, java.util.Optional.ofNullable(p_195406_1_));
  }

  static {
    CARRIABLE_BLOCKS.add(Blocks.GRASS);
    CARRIABLE_BLOCKS.add(Blocks.DIRT);
    CARRIABLE_BLOCKS.add(Blocks.SAND);
    CARRIABLE_BLOCKS.add(Blocks.GRAVEL);
    CARRIABLE_BLOCKS.add(Blocks.DANDELION);
    CARRIABLE_BLOCKS.add(Blocks.POPPY);
    CARRIABLE_BLOCKS.add(Blocks.BROWN_MUSHROOM);
    CARRIABLE_BLOCKS.add(Blocks.RED_MUSHROOM);
    CARRIABLE_BLOCKS.add(Blocks.TNT);
    CARRIABLE_BLOCKS.add(Blocks.CACTUS);
    CARRIABLE_BLOCKS.add(Blocks.CLAY);
    CARRIABLE_BLOCKS.add(Blocks.PUMPKIN);
    CARRIABLE_BLOCKS.add(Blocks.MELON);
    CARRIABLE_BLOCKS.add(Blocks.MYCELIUM);
    CARRIABLE_BLOCKS.add(Blocks.NETHERRACK);
  }

  static class FindPlayerGoal extends NearestAttackableTargetGoal<PlayerEntity> {
    private final EnderminiEntity enderman;
    private PlayerEntity player;
    private int aggroTime;
    private int teleportTime;
    private final EntityPredicate field_220791_m;
    private final EntityPredicate field_220792_n = (new EntityPredicate()).setLineOfSiteRequired();

    public FindPlayerGoal(EnderminiEntity p_i45842_1_) {
      super(p_i45842_1_, PlayerEntity.class, false);
      this.enderman = p_i45842_1_;
      this.field_220791_m = (new EntityPredicate()).setDistance(this.getTargetDistance()).setCustomPredicate((p_220790_1_) -> {
        return p_i45842_1_.shouldAttackPlayer((PlayerEntity) p_220790_1_);
      });
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    @Override
    public boolean shouldExecute() {
      this.player = this.enderman.world.getClosestPlayer(this.field_220791_m, this.enderman);
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
     * Reset the task's internal state. Called when this task is interrupted by another one
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
        if (!this.enderman.shouldAttackPlayer(this.player)) {
          return false;
        } else {
          this.enderman.faceEntity(this.player, 10.0F, 10.0F);
          return true;
        }
      } else {
        return this.nearestTarget != null && this.field_220792_n.canTarget(this.enderman, this.nearestTarget) || super.shouldContinueExecuting();
      }
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    @Override
    public void tick() {
      if (this.player != null) {
        if (--this.aggroTime <= 0) {
          this.nearestTarget = this.player;
          this.player = null;
          super.startExecuting();
        }
      } else {
        if (this.nearestTarget != null && !this.enderman.isPassenger()) {
          if (this.enderman.shouldAttackPlayer((PlayerEntity) this.nearestTarget)) {
            if (this.nearestTarget.getDistanceSq(this.enderman) < 16.0D) {
              this.enderman.teleportRandomly();
            }

            this.teleportTime = 0;
          } else if (this.nearestTarget.getDistanceSq(this.enderman) > 256.0D && this.teleportTime++ >= 30 && this.enderman.teleportToEntity(this.nearestTarget)) {
            this.teleportTime = 0;
          }
        }

        super.tick();
      }

    }
  }

  static class PlaceBlockGoal extends Goal {
    private final EnderminiEntity enderman;

    public PlaceBlockGoal(EnderminiEntity p_i45843_1_) {
      this.enderman = p_i45843_1_;
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    @Override
    public boolean shouldExecute() {
      if (this.enderman.getHeldBlockState() == null) {
        return false;
      } else if (!net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.enderman.world, this.enderman)) {
        return false;
      } else {
        return this.enderman.getRNG().nextInt(2000) == 0;
      }
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    @Override
    public void tick() {
      Random random = this.enderman.getRNG();
      IWorld iworld = this.enderman.world;
      int i = MathHelper.floor(this.enderman.posX - 1.0D + random.nextDouble() * 2.0D);
      int j = MathHelper.floor(this.enderman.posY + random.nextDouble() * 2.0D);
      int k = MathHelper.floor(this.enderman.posZ - 1.0D + random.nextDouble() * 2.0D);
      BlockPos blockpos = new BlockPos(i, j, k);
      BlockState blockstate = iworld.getBlockState(blockpos);
      BlockPos blockpos1 = blockpos.down();
      BlockState blockstate1 = iworld.getBlockState(blockpos1);
      BlockState blockstate2 = this.enderman.getHeldBlockState();
      if (blockstate2 != null && this.func_220836_a(iworld, blockpos, blockstate2, blockstate, blockstate1, blockpos1) && !net.minecraftforge.event.ForgeEventFactory.onBlockPlace(enderman, new net.minecraftforge.common.util.BlockSnapshot(iworld, blockpos, blockstate1), net.minecraft.util.Direction.UP)) {
        iworld.setBlockState(blockpos, blockstate2, 3);
        this.enderman.func_195406_b(null);
      }

    }

    private boolean func_220836_a(IWorldReader p_220836_1_, BlockPos p_220836_2_, BlockState p_220836_3_, BlockState p_220836_4_, BlockState p_220836_5_, BlockPos p_220836_6_) {
      return p_220836_4_.isAir(p_220836_1_, p_220836_2_) && !p_220836_5_.isAir(p_220836_1_, p_220836_6_) && p_220836_5_.func_224756_o(p_220836_1_, p_220836_6_) && p_220836_3_.isValidPosition(p_220836_1_, p_220836_2_);
    }
  }

  static class StareGoal extends Goal {
    private final EnderminiEntity field_220835_a;

    public StareGoal(EnderminiEntity p_i50520_1_) {
      this.field_220835_a = p_i50520_1_;
      this.setMutexFlags(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE));
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    @Override
    public boolean shouldExecute() {
      LivingEntity livingentity = this.field_220835_a.getAttackTarget();
      if (!(livingentity instanceof PlayerEntity)) {
        return false;
      } else {
        double d0 = livingentity.getDistanceSq(this.field_220835_a);
        return !(d0 > 256.0D) && this.field_220835_a.shouldAttackPlayer((PlayerEntity) livingentity);
      }
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    @Override
    public void startExecuting() {
      this.field_220835_a.getNavigator().clearPath();
    }
  }

  static class TakeBlockGoal extends Goal {
    private final EnderminiEntity enderman;

    public TakeBlockGoal(EnderminiEntity p_i45841_1_) {
      this.enderman = p_i45841_1_;
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    @Override
    public boolean shouldExecute() {
      if (this.enderman.getHeldBlockState() != null) {
        return false;
      } else if (!net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.enderman.world, this.enderman)) {
        return false;
      } else {
        return this.enderman.getRNG().nextInt(20) == 0;
      }
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    @Override
    public void tick() {
      Random random = this.enderman.getRNG();
      World world = this.enderman.world;
      int i = MathHelper.floor(this.enderman.posX - 2.0D + random.nextDouble() * 4.0D);
      int j = MathHelper.floor(this.enderman.posY + random.nextDouble() * 3.0D);
      int k = MathHelper.floor(this.enderman.posZ - 2.0D + random.nextDouble() * 4.0D);
      BlockPos blockpos = new BlockPos(i, j, k);
      BlockState blockstate = world.getBlockState(blockpos);
      Block block = blockstate.getBlock();
      Vec3d vec3d = new Vec3d((double) MathHelper.floor(this.enderman.posX) + 0.5D, (double) j + 0.5D, (double) MathHelper.floor(this.enderman.posZ) + 0.5D);
      Vec3d vec3d1 = new Vec3d((double) i + 0.5D, (double) j + 0.5D, (double) k + 0.5D);
      BlockRayTraceResult blockraytraceresult = world.rayTraceBlocks(new RayTraceContext(vec3d, vec3d1, RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.NONE, this.enderman));
      boolean flag = blockraytraceresult.getType() != RayTraceResult.Type.MISS && blockraytraceresult.getPos().equals(blockpos);
      if (block.isIn(BlockTags.ENDERMAN_HOLDABLE) && flag) {
        this.enderman.func_195406_b(blockstate);
        world.removeBlock(blockpos, false);
      }

    }
  }
}
