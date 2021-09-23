package mysticmods.mysticalworld.entity;

import com.google.common.collect.Sets;
import mysticmods.mysticalworld.MysticalWorld;
import mysticmods.mysticalworld.entity.ai.StalkGoal;
import mysticmods.mysticalworld.init.ModSounds;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.EndermiteEntity;
import net.minecraft.entity.monster.MonsterEntity;
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
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.function.Predicate;

// Base heavily on vanilla Enderman
@SuppressWarnings({"deprecation", "Duplicates", "NullableProblems"})
public class EnderminiEntity extends CreatureEntity {
  public static final ResourceLocation LOOT_TABLE = new ResourceLocation(MysticalWorld.MODID, "entity/endermini");
  private static final Set<Block> CARRIABLE_BLOCKS = Sets.newIdentityHashSet();
  private static final DataParameter<java.util.Optional<BlockState>> CARRIED_BLOCK = EntityDataManager.defineId(EnderminiEntity.class, DataSerializers.BLOCK_STATE);
  private static final DataParameter<Boolean> SCREAMING = EntityDataManager.defineId(EnderminiEntity.class, DataSerializers.BOOLEAN);
  private int lastCreepySound;
  private int targetChangeTime;
  private static final Predicate<LivingEntity> endermitePredicate = (entity) -> entity instanceof EndermiteEntity && ((EndermiteEntity) entity).isPlayerSpawned();

  public EnderminiEntity(EntityType<? extends EnderminiEntity> type, World worldIn) {
    super(type, worldIn);
    this.maxUpStep = 1.0F;
    this.setPathfindingMalus(PathNodeType.WATER, -1.0F);
  }

  @Override
  protected void registerGoals() {
    goalSelector.addGoal(0, new SwimGoal(this));
    goalSelector.addGoal(1, new EnderminiEntity.StareGoal(this));
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

  public static AttributeModifierMap.MutableAttribute attributes() {
    return MonsterEntity.createMonsterAttributes().add(Attributes.MAX_HEALTH, 10.0D).add(Attributes.MOVEMENT_SPEED, 0.35).add(Attributes.ATTACK_DAMAGE, 0.5D).add(Attributes.FOLLOW_RANGE, 64.0D);
  }

  @Override
  public void setTarget(@Nullable LivingEntity entitylivingbaseIn) {
    super.setTarget(entitylivingbaseIn);
    if (entitylivingbaseIn == null) {
      this.targetChangeTime = 0;
      this.entityData.set(SCREAMING, false);
    } else {
      this.targetChangeTime = this.tickCount;
      this.entityData.set(SCREAMING, true);
    }
  }

  @Override
  protected void defineSynchedData() {
    super.defineSynchedData();
    this.entityData.define(CARRIED_BLOCK, Optional.empty());
    this.entityData.define(SCREAMING, false);
  }

  public void playEnderminiSound() {
    if (this.tickCount >= this.lastCreepySound + 400) {
      this.lastCreepySound = this.tickCount;

      if (!this.isSilent()) {
        this.level.playSound(null, this.getX(), this.getY() + (double) this.getEyeHeight(), this.getZ(), ModSounds.ENDERMINI_STARE.get(), this.getSoundSource(), 2.5F, 1.0F);
      }
    }
  }

  @Override
  public void onSyncedDataUpdated(DataParameter<?> key) {
    if (SCREAMING.equals(key) && this.isScreaming() && this.level.isClientSide) {
      this.playEnderminiSound();
    }

    super.onSyncedDataUpdated(key);
  }

  @Override
  public void addAdditionalSaveData(CompoundNBT compound) {
    super.addAdditionalSaveData(compound);
    BlockState iblockstate = getHeldBlockState();

    if (iblockstate != null) {
      compound.putShort("carried", (short) Block.getId(iblockstate));
    }
  }

  @Override
  public void readAdditionalSaveData(CompoundNBT compound) {
    super.readAdditionalSaveData(compound);
    BlockState iblockstate = null;

    if (compound.contains("carried", 8)) {
      iblockstate = Block.stateById(compound.getShort("carried"));
    }

    if (iblockstate == null || iblockstate.getBlock() == null || iblockstate.getMaterial() == Material.AIR) {
      iblockstate = null;
    }

    this.setHeldBlock(iblockstate);
  }

  @Override
  public float getEyeHeight(Pose pPose) {
    return 1F;
  }

  @Override
  public void aiStep() {
    if (this.level.isClientSide) {
      for (int i = 0; i < 2; ++i) {
        this.level.addParticle(ParticleTypes.PORTAL, this.getX() + (this.random.nextDouble() - 0.5D) * (double) this.getBbWidth(), this.getY() + this.random.nextDouble() * (double) this.getBbHeight() - 0.25D, this.getZ() + (this.random.nextDouble() - 0.5D) * (double) this.getBbWidth(), (this.random.nextDouble() - 0.5D) * 2.0D, -this.random.nextDouble(), (this.random.nextDouble() - 0.5D) * 2.0D);
      }
    }

    this.jumping = false;
    super.aiStep();
  }

  @Override
  protected void customServerAiStep() {
    if (this.isInWaterOrRain()) {
      this.hurt(DamageSource.DROWN, 1.0F);
    }

    if (this.tickCount >= this.targetChangeTime + 600) {
      if (this.level.canSeeSkyFromBelowWater(blockPosition()) && this.random.nextFloat() * 30.0F < 1.2F) {
        this.setTarget(null);
        this.teleportRandomly();
      }
    }

    super.customServerAiStep();
  }

  protected boolean teleportRandomly() {
    double d0 = this.getX() + (this.random.nextDouble() - 0.5D) * 64.0D;
    double d1 = this.getY() + (double) (this.random.nextInt(64) - 32);
    double d2 = this.getZ() + (this.random.nextDouble() - 0.5D) * 64.0D;
    return this.teleportTo(d0, d1, d2);
  }

  protected boolean teleportToEntity(Entity p_70816_1_) {
    Vector3d vec3d = new Vector3d(this.getX() - p_70816_1_.getX(),
        this.getBoundingBox().minY + (double) (this.getBbHeight() / 2.0F) - p_70816_1_.getY() + (double) p_70816_1_.getEyeHeight(), this.getZ() - p_70816_1_.getZ());
    vec3d = vec3d.normalize();
    double d1 = this.getX() + (this.random.nextDouble() - 0.5D) * 8.0D - vec3d.x * 16.0D;
    double d2 = this.getY() + (double) (this.random.nextInt(16) - 8) - vec3d.y * 16.0D;
    double d3 = this.getZ() + (this.random.nextDouble() - 0.5D) * 8.0D - vec3d.z * 16.0D;
    return this.teleportTo(d1, d2, d3);
  }

  /**
   * Teleport the endermini
   */
  private boolean teleportTo(double x, double y, double z) {
    net.minecraftforge.event.entity.living.EnderTeleportEvent event = new net.minecraftforge.event.entity.living.EnderTeleportEvent(this, x, y, z, 0);
    if (net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(event))
      return false;
    boolean flag = this.randomTeleport(event.getTargetX(), event.getTargetY(), event.getTargetZ(), false);

    if (flag) {
      this.level.playSound(null, this.xo, this.yo, this.zo, ModSounds.ENDERMINI_PORTAL.get(), this.getSoundSource(), 1.0F, 1.0F);
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
  protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHitIn) {
    super.dropCustomDeathLoot(source, looting, recentlyHitIn);
    BlockState iblockstate = this.getHeldBlockState();

    if (iblockstate != null) {
      Item item = Item.byBlock(iblockstate.getBlock());
      this.spawnAtLocation(new ItemStack(item, 1), 0.0F);
    }
  }

  @Override
  public ResourceLocation getDefaultLootTable() {
    return new ResourceLocation(MysticalWorld.MODID, "entities/endermini");
  }

  /**
   * Sets this endermini's held block state
   */
  private void setHeldBlock(@Nullable BlockState state) {
    this.entityData.set(CARRIED_BLOCK, Optional.ofNullable(state));
  }

  /**
   * Gets this endermini's held block state
   */
  @Nullable
  public BlockState getHeldBlockState() {
    return this.entityData.get(CARRIED_BLOCK).orElse(null);
  }

  /**
   * Called when the entity is attacked.
   */
  @Override
  public boolean hurt(DamageSource source, float amount) {
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
      boolean flag = super.hurt(source, amount);

      if (source.isBypassArmor() && this.random.nextInt(10) != 0) {
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
    return this.entityData.get(SCREAMING);
  }

  private boolean shouldFollowPlayer(PlayerEntity player) {
    ItemStack itemstack = player.inventory.armor.get(3);

    if (itemstack.getItem() == Items.PUMPKIN) {
      return false;
    } else {
      Vector3d vec3d = player.getViewVector(1.0F).normalize();
      Vector3d vec3d1 = new Vector3d(this.getX() - player.getX(),
          this.getBoundingBox().minY + (double) this.getEyeHeight() - (player.getY() + (double) player.getEyeHeight()), this.getZ() - player.getZ());
      double d0 = vec3d1.length();
      vec3d1 = vec3d1.normalize();
      double d1 = vec3d.dot(vec3d1);
      return d1 > 1.0D - 0.025D / d0 && player.canSee(this);
    }
  }

  /**
   * Checks to see if this enderman should be attacking this player
   */
  private boolean shouldAttackPlayer(PlayerEntity player) {
    ItemStack itemstack = player.inventory.armor.get(3);
    if (itemstack.getItem() == Blocks.CARVED_PUMPKIN.asItem()) {
      return false;
    } else {
      Vector3d vec3d = player.getViewVector(1.0F).normalize();
      Vector3d vec3d1 = new Vector3d(this.getX() - player.getX(), this.getBoundingBox().minY + (double) this.getEyeHeight() - (player.getY() + (double) player.getEyeHeight()), this.getZ() - player.getZ());
      double d0 = vec3d1.length();
      vec3d1 = vec3d1.normalize();
      double d1 = vec3d.dot(vec3d1);
      return d1 > 1.0D - 0.025D / d0 && player.canSee(this);
    }
  }

  public void setCarriedBlock(@Nullable BlockState pState) {
    this.entityData.set(CARRIED_BLOCK, java.util.Optional.ofNullable(pState));
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
    private final EntityPredicate startAggroTargetConditions;
    private final EntityPredicate continueAggroTargetConditions = (new EntityPredicate()).allowUnseeable();

    public FindPlayerGoal(EnderminiEntity p_i45842_1_) {
      super(p_i45842_1_, PlayerEntity.class, false);
      this.enderman = p_i45842_1_;
      this.startAggroTargetConditions = (new EntityPredicate()).range(this.getFollowDistance()).selector((p_220790_1_) -> p_i45842_1_.shouldAttackPlayer((PlayerEntity) p_220790_1_));
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    @Override
    public boolean canUse() {
      this.player = this.enderman.level.getNearestPlayer(this.startAggroTargetConditions, this.enderman);
      return this.player != null;
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    @Override
    public void start() {
      this.aggroTime = 5;
      this.teleportTime = 0;
    }

    /**
     * Reset the task's internal state. Called when this task is interrupted by another one
     */
    @Override
    public void stop() {
      this.player = null;
      super.stop();
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    @Override
    public boolean canContinueToUse() {
      if (this.player != null) {
        if (!this.enderman.shouldAttackPlayer(this.player)) {
          return false;
        } else {
          this.enderman.lookAt(this.player, 10.0F, 10.0F);
          return true;
        }
      } else {
        return this.target != null && this.continueAggroTargetConditions.test(this.enderman, this.target) || super.canContinueToUse();
      }
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    @Override
    public void tick() {
      if (this.player != null) {
        if (--this.aggroTime <= 0) {
          this.target = this.player;
          this.player = null;
          super.start();
        }
      } else {
        if (this.target != null && !this.enderman.isPassenger()) {
          if (this.enderman.shouldAttackPlayer((PlayerEntity) this.target)) {
            if (this.target.distanceToSqr(this.enderman) < 16.0D) {
              this.enderman.teleportRandomly();
            }

            this.teleportTime = 0;
          } else if (this.target.distanceToSqr(this.enderman) > 256.0D && this.teleportTime++ >= 30 && this.enderman.teleportToEntity(this.target)) {
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
    public boolean canUse() {
      if (this.enderman.getHeldBlockState() == null) {
        return false;
      } else if (!net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.enderman.level, this.enderman)) {
        return false;
      } else {
        return this.enderman.getRandom().nextInt(2000) == 0;
      }
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    @Override
    public void tick() {
      Random random = this.enderman.getRandom();
      World world = this.enderman.level;
      int i = MathHelper.floor(this.enderman.getX() - 1.0D + random.nextDouble() * 2.0D);
      int j = MathHelper.floor(this.enderman.getY() + random.nextDouble() * 2.0D);
      int k = MathHelper.floor(this.enderman.getZ() - 1.0D + random.nextDouble() * 2.0D);
      BlockPos blockpos = new BlockPos(i, j, k);
      BlockState blockstate = world.getBlockState(blockpos);
      BlockPos blockpos1 = blockpos.below();
      BlockState blockstate1 = world.getBlockState(blockpos1);
      BlockState blockstate2 = this.enderman.getHeldBlockState();
      if (blockstate2 != null) {
        blockstate2 = Block.updateFromNeighbourShapes(blockstate2, this.enderman.level, blockpos);
        if (this.canPlaceBlock(world, blockpos, blockstate2, blockstate, blockstate1, blockpos1) && !net.minecraftforge.event.ForgeEventFactory.onBlockPlace(enderman, net.minecraftforge.common.util.BlockSnapshot.create(world.dimension(), world, blockpos1), net.minecraft.util.Direction.UP)) {
          world.setBlock(blockpos, blockstate2, 3);
          this.enderman.setCarriedBlock(null);
        }

      }
    }

    private boolean canPlaceBlock(World p_220836_1_, BlockPos p_220836_2_, BlockState p_220836_3_, BlockState p_220836_4_, BlockState p_220836_5_, BlockPos p_220836_6_) {
      return p_220836_4_.isAir(p_220836_1_, p_220836_2_) && !p_220836_5_.isAir(p_220836_1_, p_220836_6_) && !p_220836_5_.is(Blocks.BEDROCK) && p_220836_5_.isCollisionShapeFullBlock(p_220836_1_, p_220836_6_) && p_220836_3_.canSurvive(p_220836_1_, p_220836_2_) && p_220836_1_.getEntities(this.enderman, AxisAlignedBB.unitCubeFromLowerCorner(Vector3d.atLowerCornerOf(p_220836_2_))).isEmpty();
    }
  }

  static class StareGoal extends Goal {
    private final EnderminiEntity enderman;

    public StareGoal(EnderminiEntity p_i50520_1_) {
      this.enderman = p_i50520_1_;
      this.setFlags(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE));
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    @Override
    public boolean canUse() {
      LivingEntity livingentity = this.enderman.getTarget();
      if (!(livingentity instanceof PlayerEntity)) {
        return false;
      } else {
        double d0 = livingentity.distanceToSqr(this.enderman);
        return !(d0 > 256.0D) && this.enderman.shouldAttackPlayer((PlayerEntity) livingentity);
      }
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    @Override
    public void start() {
      this.enderman.getNavigation().stop();
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
    public boolean canUse() {
      if (this.enderman.getHeldBlockState() != null) {
        return false;
      } else if (!net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.enderman.level, this.enderman)) {
        return false;
      } else {
        return this.enderman.getRandom().nextInt(20) == 0;
      }
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    @Override
    public void tick() {
      Random random = this.enderman.getRandom();
      World world = this.enderman.level;
      int i = MathHelper.floor(this.enderman.getX() - 2.0D + random.nextDouble() * 4.0D);
      int j = MathHelper.floor(this.enderman.getY() + random.nextDouble() * 3.0D);
      int k = MathHelper.floor(this.enderman.getZ() - 2.0D + random.nextDouble() * 4.0D);
      BlockPos blockpos = new BlockPos(i, j, k);
      BlockState blockstate = world.getBlockState(blockpos);
      Block block = blockstate.getBlock();
      Vector3d vec3d = new Vector3d((double) MathHelper.floor(this.enderman.getX()) + 0.5D, (double) j + 0.5D, (double) MathHelper.floor(this.enderman.getZ()) + 0.5D);
      Vector3d vec3d1 = new Vector3d((double) i + 0.5D, (double) j + 0.5D, (double) k + 0.5D);
      BlockRayTraceResult blockraytraceresult = world.clip(new RayTraceContext(vec3d, vec3d1, RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.NONE, this.enderman));
      boolean flag = blockraytraceresult.getType() != RayTraceResult.Type.MISS && blockraytraceresult.getBlockPos().equals(blockpos);
      if (block.is(BlockTags.ENDERMAN_HOLDABLE) && flag) {
        this.enderman.setCarriedBlock(blockstate);
        world.removeBlock(blockpos, false);
      }

    }
  }
}
