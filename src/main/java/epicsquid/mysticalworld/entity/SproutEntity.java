package epicsquid.mysticalworld.entity;

import epicsquid.mysticalworld.init.ModSounds;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class SproutEntity extends AnimalEntity {
	public static final DataParameter<Integer> variant = EntityDataManager.<Integer>createKey(SproutEntity.class, DataSerializers.VARINT);

	public SproutEntity(EntityType<? extends SproutEntity> type, World world) {
		super(type, world);
//		setSize(0.5f, 1.0f);
		this.experienceValue = 3;
	}

	@Nullable
	@Override
	public AgeableEntity createChild(AgeableEntity ageable) {
		return null;
	}

	@Override
	protected float getSoundVolume() {
		return 0.3f;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		if (rand.nextInt(14) == 0) {
			return ModSounds.Sprout.AMBIENT;
		}

		return null;
	}

	@Override
	protected void registerData() {
		super.registerData();
		this.getDataManager().register(variant, rand.nextInt(3));
	}

	@Override
	protected void registerGoals() {
		goalSelector.addGoal(0, new SwimGoal(this));
		goalSelector.addGoal(1, new PanicGoal(this, 1.5));
		goalSelector.addGoal(5, new RandomWalkingGoal(this, 1.0));
		goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 6.0f));
		goalSelector.addGoal(7, new LookRandomlyGoal(this));
	}

	@Override
	public boolean isAIDisabled() {
		return false;
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
		switch (getDataManager().get(SproutEntity.variant)) {
			case 1: {
				return new ResourceLocation("mysticalworld:entities/sprout_tan");
			}
			case 2: {
				return new ResourceLocation("mysticalworld:entities/sprout_red");
			}
			case 3: {
				return new ResourceLocation("mysticalworld:entities/sprout_purple");
			}
			default: {
				return new ResourceLocation("mysticalworld:entities/sprout_green");
			}
		}
	}

	public float getStandingEyeHeight(Pose pose, EntitySize size) {
		return isChild() ? getHeight() : 1.3F;
	}

	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		getDataManager().set(variant, compound.getInt("variant"));
	}

	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putInt("variant", getDataManager().get(variant));
	}
}
