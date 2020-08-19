package epicsquid.mysticalworld.entity;

import epicsquid.mysticalworld.config.ConfigManager;
import epicsquid.mysticalworld.init.ModEntities;
import epicsquid.mysticalworld.init.ModItems;
import epicsquid.mysticalworld.init.ModSounds;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.NetherWartBlock;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.DirectionalPlaceContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.BlockSnapshot;
import net.minecraftforge.event.ForgeEventFactory;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Random;

public class HellSproutEntity extends AnimalEntity {
  public static ItemStack netherWart = new ItemStack(Items.NETHER_WART);

  public HellSproutEntity(EntityType<? extends HellSproutEntity> type, World world) {
    super(type, world);
//		setSize(0.5f, 1.0f);
    this.experienceValue = 3;
  }

  @Override
  public void livingTick() {
    if (this.world.isRemote) {
      if (rand.nextInt(7) == 0) {
        this.world.addParticle((rand.nextInt(3) == 0 ? ParticleTypes.SMOKE : ParticleTypes.FLAME), this.posX + (this.rand.nextDouble() - 0.5D) * 0.5, this.posY + 0.3 + (this.rand.nextDouble() - 0.5D) * 0.5, this.posZ + (this.rand.nextDouble() - 0.5D) * 0.3, 0, 0, 0);
      }
    }
    super.livingTick();
  }

  @Nullable
  @Override
  public AgeableEntity createChild(AgeableEntity ageable) {
    return ModEntities.HELL_SPROUT.get().create(ageable.world);
  }

  @Override
  protected float getSoundVolume() {
    return 0.3f;
  }

  @Nullable
  @Override
  protected SoundEvent getAmbientSound() {
    if (rand.nextInt(14) == 0) {
      return ModSounds.SPROUT_AMBIENT.get();
    }

    return null;
  }

  @Override
  protected void registerGoals() {
    goalSelector.addGoal(0, new SwimGoal(this));
    goalSelector.addGoal(1, new PanicGoal(this, 1.5));
    goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
    goalSelector.addGoal(3, new TemptGoal(this, 1.25D, Ingredient.fromItems(ModItems.COOKED_AUBERGINE.get()), false));
    goalSelector.addGoal(3, new PlantNetherWartGoal());
    goalSelector.addGoal(5, new RandomWalkingGoal(this, 1.0));
    goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 6.0f));
    goalSelector.addGoal(7, new LookRandomlyGoal(this));
  }

  @Override
  public boolean isBreedingItem(ItemStack stack) {
    return stack.getItem() == ModItems.COOKED_AUBERGINE.get();
  }

  @Override
  protected void registerAttributes() {
    super.registerAttributes();
    getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(8.0D);
    getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.20000000298023224D);
  }

  @Override
  @Nonnull
  public ResourceLocation getLootTable() {
    return new ResourceLocation("mysticalworld:entities/hell_sprout");
  }

  @Override
  public float getStandingEyeHeight(Pose pose, EntitySize size) {
    return isChild() ? getHeight() : 1.3F;
  }

  public class PlantNetherWartGoal extends Goal {
    private int ticker = 0;

    @Override
    public boolean shouldExecute() {
      if (isChild()) {
        return false;
      }
      if (onGround && ticker >= 20) {
        ticker = 0;
        if (ConfigManager.HELL_SPROUT_CONFIG.getGrowChance() == 0) {
          return false;
        }
        BlockPos pos = new BlockPos(posX, Math.round(posY), posZ);
        BlockState state = world.getBlockState(pos);
        BlockState state2 = world.getBlockState(pos.down());
        if (canPlaceBlock(world, pos, state, state2)) {
          return getRNG().nextInt(ConfigManager.HELL_SPROUT_CONFIG.getGrowChance()) == 0;
        }
      }

      ticker++;
      return false;
    }

    @Override
    public void startExecuting() {
      Random random = getRNG();
      BlockPos pos = new BlockPos(posX, Math.round(posY), posZ);
      BlockState netherCrop = Blocks.NETHER_WART.getDefaultState().with(NetherWartBlock.AGE, random.nextInt(3) + random.nextInt(5) == 0 ? 1 : 0);

      if (!ForgeEventFactory.onBlockPlace(HellSproutEntity.this, new BlockSnapshot(world, pos, netherCrop), Direction.UP)) {
        world.setBlockState(pos, netherCrop, 3);
      }
    }

    private boolean canPlaceBlock(World world, BlockPos pos, BlockState state, BlockState down) {
      DirectionalPlaceContext context = new DirectionalPlaceContext(world, pos, Direction.DOWN, netherWart, Direction.UP);
      if (!state.getBlock().isAir(state, world, pos) || !state.isReplaceable(context)) {
        return false;
      }
      return down.getBlock().canSustainPlant(down, world, pos.down(), Direction.UP, (NetherWartBlock) Blocks.NETHER_WART);
    }
  }
}
