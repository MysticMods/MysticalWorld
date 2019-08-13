package epicsquid.mysticalworld.entity.ai;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntityShoulderRiding;
import net.minecraft.entity.player.EntityPlayer;

public class EntityAIHopOnOwnersShoulder extends EntityAIBase {
  private final EntityShoulderRiding entity;
  private EntityPlayer owner;
  private boolean isSittingOnShoulder;

  public EntityAIHopOnOwnersShoulder(EntityShoulderRiding entityIn) {
    this.entity = entityIn;
  }

  @Override
  public boolean shouldExecute() {
    EntityLivingBase entitylivingbase = this.entity.getOwner();
    boolean flag = entitylivingbase != null && !((EntityPlayer) entitylivingbase).isSpectator() && !((EntityPlayer) entitylivingbase).capabilities.isFlying && !entitylivingbase.isInWater();
    return !this.entity.isSitting() && flag && this.entity.canSitOnShoulder();
  }

  @Override
  public boolean isInterruptible() {
    return !this.isSittingOnShoulder;
  }

  @Override
  public void startExecuting() {
    this.owner = (EntityPlayer) this.entity.getOwner();
    this.isSittingOnShoulder = false;
  }

  @Override
  public void updateTask() {
    if (!this.isSittingOnShoulder && !this.entity.isSitting() && !this.entity.getLeashed()) {
      if (this.entity.getEntityBoundingBox().intersects(this.owner.getEntityBoundingBox())) {
        this.isSittingOnShoulder = this.entity.setEntityOnShoulder(this.owner);
      }
    }
  }
}

