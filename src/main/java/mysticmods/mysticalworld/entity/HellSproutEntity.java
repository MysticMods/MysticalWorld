package mysticmods.mysticalworld.entity;

import mysticmods.mysticalworld.config.ConfigManager;
import mysticmods.mysticalworld.init.ModEntities;
import mysticmods.mysticalworld.init.ModItems;
import mysticmods.mysticalworld.init.ModSounds;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.NetherWartBlock;
import net.minecraft.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.DirectionalPlaceContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.common.util.BlockSnapshot;
import net.minecraftforge.event.ForgeEventFactory;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Random;

import net.minecraft.world.entity.AgableMob;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;

public class HellSproutEntity extends Animal {
  public static ItemStack netherWart = new ItemStack(Items.NETHER_WART);

  public HellSproutEntity(EntityType<? extends HellSproutEntity> type, Level world) {
    super(type, world);
//		setSize(0.5f, 1.0f);
    this.xpReward = 3;
  }

  @Override
  public void aiStep() {
    if (this.level.isClientSide) {
      if (random.nextInt(7) == 0) {
        this.level.addParticle((random.nextInt(3) == 0 ? ParticleTypes.SMOKE : ParticleTypes.FLAME), this.getX() + (this.random.nextDouble() - 0.5D) * 0.5, this.getY() + 0.3 + (this.random.nextDouble() - 0.5D) * 0.5, this.getZ() + (this.random.nextDouble() - 0.5D) * 0.3, 0, 0, 0);
      }
    }
    super.aiStep();
  }

  @Override
  @Nonnull
  public AgableMob getBreedOffspring(ServerLevel world, AgableMob ageable) {
    return ModEntities.HELL_SPROUT.get().create(ageable.level);
  }

  @Override
  protected float getSoundVolume() {
    return 0.3f;
  }

  @Nullable
  @Override
  protected SoundEvent getAmbientSound() {
    if (random.nextInt(14) == 0) {
      return ModSounds.SPROUT_AMBIENT.get();
    }

    return null;
  }

  @Override
  protected void registerGoals() {
    goalSelector.addGoal(0, new FloatGoal(this));
    goalSelector.addGoal(1, new PanicGoal(this, 1.5));
    goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
    goalSelector.addGoal(3, new TemptGoal(this, 1.25D, Ingredient.of(ModItems.COOKED_AUBERGINE.get()), false));
    goalSelector.addGoal(3, new PlantNetherWartGoal());
    goalSelector.addGoal(5, new RandomStrollGoal(this, 1.0));
    goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0f));
    goalSelector.addGoal(7, new RandomLookAroundGoal(this));
  }

  @Override
  public boolean isFood(ItemStack stack) {
    return stack.getItem() == ModItems.COOKED_AUBERGINE.get();
  }

  public static AttributeSupplier.Builder attributes() {
    return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 8.0d).add(Attributes.MOVEMENT_SPEED, 0.2d);
  }

  @Override
  @Nonnull
  public ResourceLocation getDefaultLootTable() {
    return new ResourceLocation("mysticalworld:entities/hell_sprout");
  }

  @Override
  public float getStandingEyeHeight(Pose pose, EntityDimensions size) {
    return isBaby() ? getBbHeight() : 1.3F;
  }

  public class PlantNetherWartGoal extends Goal {
    private int ticker = 0;

    @Override
    public boolean canUse() {
      if (isBaby()) {
        return false;
      }
      if (onGround && ticker >= 20) {
        ticker = 0;
        if (ConfigManager.HELL_SPROUT_CONFIG.getGrowChance() == 0) {
          return false;
        }
        BlockPos pos = new BlockPos(getX(), Math.round(getY()), getZ());
        BlockState state = level.getBlockState(pos);
        BlockState state2 = level.getBlockState(pos.below());
        if (canPlaceBlock(level, pos, state, state2)) {
          return getRandom().nextInt(ConfigManager.HELL_SPROUT_CONFIG.getGrowChance()) == 0;
        }
      }

      ticker++;
      return false;
    }

    @Override
    public void start() {
      Random random = getRandom();
      BlockPos pos = new BlockPos(getX(), Math.round(getY()), getZ());
      BlockState netherCrop = Blocks.NETHER_WART.defaultBlockState().setValue(NetherWartBlock.AGE, random.nextInt(3) + random.nextInt(5) == 0 ? 1 : 0);

      if (!ForgeEventFactory.onBlockPlace(HellSproutEntity.this, BlockSnapshot.create(level.dimension(), level, pos), Direction.UP)) {
        level.setBlock(pos, netherCrop, 3);
      }
    }

    private boolean canPlaceBlock(Level world, BlockPos pos, BlockState state, BlockState down) {
      DirectionalPlaceContext context = new DirectionalPlaceContext(world, pos, Direction.DOWN, netherWart, Direction.UP);
      if (!state.getBlock().isAir(state, world, pos) || !state.canBeReplaced(context)) {
        return false;
      }
      return down.getBlock().canSustainPlant(down, world, pos.below(), Direction.UP, (NetherWartBlock) Blocks.NETHER_WART);
    }
  }
}
