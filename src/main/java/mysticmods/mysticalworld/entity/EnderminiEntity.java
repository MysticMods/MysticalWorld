package mysticmods.mysticalworld.entity;

import com.google.common.collect.Sets;
import mysticmods.mysticalworld.MysticalWorld;
import mysticmods.mysticalworld.entity.ai.StalkGoal;
import mysticmods.mysticalworld.init.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.IndirectEntityDamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.monster.Endermite;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

// TODO: Resynchronize this with EnderMan
@SuppressWarnings({"deprecation", "Duplicates", "NullableProblems"})
public class EnderminiEntity extends PathfinderMob {
  public static final ResourceLocation LOOT_TABLE = new ResourceLocation(MysticalWorld.MODID, "entity/endermini");
  private static final Set<Block> CARRIABLE_BLOCKS = Sets.newIdentityHashSet();
  private static final EntityDataAccessor<java.util.Optional<BlockState>> CARRIED_BLOCK = SynchedEntityData.defineId(EnderminiEntity.class, EntityDataSerializers.BLOCK_STATE);
  private static final EntityDataAccessor<Boolean> SCREAMING = SynchedEntityData.defineId(EnderminiEntity.class, EntityDataSerializers.BOOLEAN);
  private int lastCreepySound;
  private int targetChangeTime;

  public EnderminiEntity(EntityType<? extends EnderminiEntity> type, Level worldIn) {
    super(type, worldIn);
    this.maxUpStep = 1.0F;
    this.setPathfindingMalus(BlockPathTypes.WATER, -1.0F);
  }

  @Override
  protected void registerGoals() {
    goalSelector.addGoal(0, new FloatGoal(this));
    goalSelector.addGoal(1, new EnderminiEntity.StareGoal(this));
    goalSelector.addGoal(1, new PanicGoal(this, 1.5D));
    goalSelector.addGoal(2, new StalkGoal(this, 2.0D, false));
    goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0D, 0.0F));
    goalSelector.addGoal(8, new InteractGoal(this, Player.class, 32.0F, 1.0f));
    goalSelector.addGoal(8, new RandomLookAroundGoal(this));
    goalSelector.addGoal(10, new PlaceBlockGoal(this));
    goalSelector.addGoal(11, new EnderminiEntity.TakeBlockGoal(this));
    targetSelector.addGoal(1, new FindPlayerGoal(this));
    targetSelector.addGoal(2, new HurtByTargetGoal(this, Player.class));
    targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Endermite.class, true, false));
  }

  public static AttributeSupplier.Builder attributes() {
    return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 10.0D).add(Attributes.MOVEMENT_SPEED, 0.35).add(Attributes.ATTACK_DAMAGE, 0.5D).add(Attributes.FOLLOW_RANGE, 64.0D);
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
  public void onSyncedDataUpdated(EntityDataAccessor<?> key) {
    if (SCREAMING.equals(key) && this.isScreaming() && this.level.isClientSide) {
      this.playEnderminiSound();
    }

    super.onSyncedDataUpdated(key);
  }

  @Override
  public void addAdditionalSaveData(CompoundTag compound) {
    super.addAdditionalSaveData(compound);
    BlockState iblockstate = getHeldBlockState();

    if (iblockstate != null) {
      compound.putShort("carried", (short) Block.getId(iblockstate));
    }
  }

  @Override
  public void readAdditionalSaveData(CompoundTag compound) {
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
    return this.teleport(d0, d1, d2);
  }

  protected boolean teleportToEntity(Entity p_70816_1_) {
    Vec3 vec3d = new Vec3(this.getX() - p_70816_1_.getX(),
        this.getBoundingBox().minY + (double) (this.getBbHeight() / 2.0F) - p_70816_1_.getY() + (double) p_70816_1_.getEyeHeight(), this.getZ() - p_70816_1_.getZ());
    vec3d = vec3d.normalize();
    double d1 = this.getX() + (this.random.nextDouble() - 0.5D) * 8.0D - vec3d.x * 16.0D;
    double d2 = this.getY() + (double) (this.random.nextInt(16) - 8) - vec3d.y * 16.0D;
    double d3 = this.getZ() + (this.random.nextDouble() - 0.5D) * 8.0D - vec3d.z * 16.0D;
    return this.teleport(d1, d2, d3);
  }

  /**
   * Teleport the endermini
   */
  private boolean teleport(double pX, double pY, double pZ) {
    BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos(pX, pY, pZ);

    while (blockpos$mutableblockpos.getY() > this.level.getMinBuildHeight() && !this.level.getBlockState(blockpos$mutableblockpos).getMaterial().blocksMotion()) {
      blockpos$mutableblockpos.move(Direction.DOWN);
    }

    BlockState blockstate = this.level.getBlockState(blockpos$mutableblockpos);
    boolean flag = blockstate.getMaterial().blocksMotion();
    boolean flag1 = blockstate.getFluidState().is(FluidTags.WATER);
    if (flag && !flag1) {
      net.minecraftforge.event.entity.EntityTeleportEvent.EnderEntity event = net.minecraftforge.event.ForgeEventFactory.onEnderTeleport(this, pX, pY, pZ);
      if (event.isCanceled()) return false;
      boolean flag2 = this.randomTeleport(event.getTargetX(), event.getTargetY(), event.getTargetZ(), true);
      if (flag2 && !this.isSilent()) {
        this.level.playSound(null, this.xo, this.yo, this.zo, ModSounds.ENDERMINI_PORTAL.get(), this.getSoundSource(), 1.0F, 1.0F);
        this.playSound(ModSounds.ENDERMINI_PORTAL.get(), 1.0F, 1.0F);
      }

      return flag2;
    } else {
      return false;
    }
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

  private boolean shouldFollowPlayer(Player player) {
    ItemStack itemstack = player.getInventory().armor.get(3);

    if (itemstack.getItem() == Items.PUMPKIN) {
      return false;
    } else {
      Vec3 vec3d = player.getViewVector(1.0F).normalize();
      Vec3 vec3d1 = new Vec3(this.getX() - player.getX(),
          this.getBoundingBox().minY + (double) this.getEyeHeight() - (player.getY() + (double) player.getEyeHeight()), this.getZ() - player.getZ());
      double d0 = vec3d1.length();
      vec3d1 = vec3d1.normalize();
      double d1 = vec3d.dot(vec3d1);
      return d1 > 1.0D - 0.025D / d0 && player.hasLineOfSight(this);
    }
  }

  private final static String BETTERENDFORGE = "betterendforge";
  private static MobEffect END_VEIL_EFFECT = null;
  private static Enchantment END_VEIL_ENCHANTMENT = null;
  private static int betterEnd = 0;

  /**
   * Checks to see if this enderman should be attacking this player
   */
  private boolean shouldAttackPlayer(Player player) {
    if (betterEnd == 0) {
      if (END_VEIL_EFFECT == null) {
        END_VEIL_EFFECT = ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation(BETTERENDFORGE, "end_veil"));
      }
      if (END_VEIL_ENCHANTMENT == null) {
        END_VEIL_ENCHANTMENT = ForgeRegistries.ENCHANTMENTS.getValue(new ResourceLocation(BETTERENDFORGE, "end_veil"));
      }
      if (END_VEIL_EFFECT == null || END_VEIL_ENCHANTMENT == null) {
        betterEnd = -1;
      } else {
        betterEnd = 1;
      }
    }
    ItemStack itemstack = player.getInventory().armor.get(3);
    if (itemstack.getItem() == Blocks.CARVED_PUMPKIN.asItem()) {
      return false;
    }
    if (betterEnd == 1) {
      if (player.hasEffect(END_VEIL_EFFECT) || EnchantmentHelper.getEnchantmentLevel(END_VEIL_ENCHANTMENT, player) > 0) {
        return false;
      }
    }
    Vec3 vec3d = player.getViewVector(1.0F).normalize();
    Vec3 vec3d1 = new Vec3(this.getX() - player.getX(), this.getBoundingBox().minY + (double) this.getEyeHeight() - (player.getY() + (double) player.getEyeHeight()), this.getZ() - player.getZ());
    double d0 = vec3d1.length();
    vec3d1 = vec3d1.normalize();
    double d1 = vec3d.dot(vec3d1);
    return d1 > 1.0D - 0.025D / d0 && player.hasLineOfSight(this);
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

  public static boolean checkMonsterSpawnRules(EntityType<EnderminiEntity> pType, ServerLevelAccessor pLevel, MobSpawnType
      pReason, BlockPos pPos, Random pRandom) {
    return checkMobSpawnRules(pType, pLevel, pReason, pPos, pRandom);
  }

  static class FindPlayerGoal extends NearestAttackableTargetGoal<Player> {
    private final EnderminiEntity enderman;
    private Player player;
    private int aggroTime;
    private int teleportTime;
    private final TargetingConditions startAggroTargetConditions;
    private final TargetingConditions continueAggroTargetConditions = (TargetingConditions.forCombat().ignoreInvisibilityTesting());

    public FindPlayerGoal(EnderminiEntity p_i45842_1_) {
      super(p_i45842_1_, Player.class, false);
      this.enderman = p_i45842_1_;
      this.startAggroTargetConditions = (TargetingConditions.forCombat()).range(this.getFollowDistance()).selector((p_220790_1_) -> p_i45842_1_.shouldAttackPlayer((Player) p_220790_1_));
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
          if (this.enderman.shouldAttackPlayer((Player) this.target)) {
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
      Level world = this.enderman.level;
      int i = Mth.floor(this.enderman.getX() - 1.0D + random.nextDouble() * 2.0D);
      int j = Mth.floor(this.enderman.getY() + random.nextDouble() * 2.0D);
      int k = Mth.floor(this.enderman.getZ() - 1.0D + random.nextDouble() * 2.0D);
      BlockPos blockpos = new BlockPos(i, j, k);
      BlockState blockstate = world.getBlockState(blockpos);
      BlockPos blockpos1 = blockpos.below();
      BlockState blockstate1 = world.getBlockState(blockpos1);
      BlockState blockstate2 = this.enderman.getHeldBlockState();
      if (blockstate2 != null) {
        blockstate2 = Block.updateFromNeighbourShapes(blockstate2, this.enderman.level, blockpos);
        if (this.canPlaceBlock(world, blockpos, blockstate2, blockstate, blockstate1, blockpos1) && !net.minecraftforge.event.ForgeEventFactory.onBlockPlace(enderman, net.minecraftforge.common.util.BlockSnapshot.create(world.dimension(), world, blockpos1), net.minecraft.core.Direction.UP)) {
          world.setBlock(blockpos, blockstate2, 3);
          this.enderman.setCarriedBlock(null);
        }

      }
    }

    private boolean canPlaceBlock(Level p_220836_1_, BlockPos p_220836_2_, BlockState p_220836_3_, BlockState p_220836_4_, BlockState p_220836_5_, BlockPos p_220836_6_) {
      return p_220836_4_.isAir() && !p_220836_5_.isAir() && !p_220836_5_.is(Blocks.BEDROCK) && p_220836_5_.isCollisionShapeFullBlock(p_220836_1_, p_220836_6_) && p_220836_3_.canSurvive(p_220836_1_, p_220836_2_) && p_220836_1_.getEntities(this.enderman, AABB.unitCubeFromLowerCorner(Vec3.atLowerCornerOf(p_220836_2_))).isEmpty();
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
      if (!(livingentity instanceof Player)) {
        return false;
      } else {
        double d0 = livingentity.distanceToSqr(this.enderman);
        return !(d0 > 256.0D) && this.enderman.shouldAttackPlayer((Player) livingentity);
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
      Level world = this.enderman.level;
      int i = Mth.floor(this.enderman.getX() - 2.0D + random.nextDouble() * 4.0D);
      int j = Mth.floor(this.enderman.getY() + random.nextDouble() * 3.0D);
      int k = Mth.floor(this.enderman.getZ() - 2.0D + random.nextDouble() * 4.0D);
      BlockPos blockpos = new BlockPos(i, j, k);
      BlockState blockstate = world.getBlockState(blockpos);
      Vec3 vec3d = new Vec3((double) Mth.floor(this.enderman.getX()) + 0.5D, (double) j + 0.5D, (double) Mth.floor(this.enderman.getZ()) + 0.5D);
      Vec3 vec3d1 = new Vec3((double) i + 0.5D, (double) j + 0.5D, (double) k + 0.5D);
      BlockHitResult blockraytraceresult = world.clip(new ClipContext(vec3d, vec3d1, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, this.enderman));
      boolean flag = blockraytraceresult.getType() != HitResult.Type.MISS && blockraytraceresult.getBlockPos().equals(blockpos);
      if (blockstate.is(BlockTags.ENDERMAN_HOLDABLE) && flag) {
        this.enderman.setCarriedBlock(blockstate);
        world.removeBlock(blockpos, false);
      }

    }
  }
}
