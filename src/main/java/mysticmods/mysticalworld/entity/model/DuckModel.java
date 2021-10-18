package mysticmods.mysticalworld.entity.model;

import com.google.common.collect.ImmutableList;
import mysticmods.mysticalworld.entity.DuckEntity;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

import java.util.Collections;

public class DuckModel extends AgeableModel<DuckEntity> {
  private final ModelRenderer main;
  private final ModelRenderer neck;
  private final ModelRenderer head;
  private final ModelRenderer tail;
  private final ModelRenderer leg_L;
  private final ModelRenderer leg_R;
  private final ModelRenderer wing_L;
  private final ModelRenderer wing_R;

  public DuckModel() {
    texWidth = 64;
    texHeight = 64;

    main = new ModelRenderer(this);
    main.setPos(0.0F, 17.0F, 0.0F);
    main.texOffs(0, 0).addBox(-3.0F, -2.0F, -4.0F, 6.0F, 6.0F, 6.0F, 0.0F, false);
    main.texOffs(0, 12).addBox(-3.0F, -2.0F, 2.0F, 6.0F, 5.0F, 3.0F, 0.0F, false);

    neck = new ModelRenderer(this);
    neck.setPos(0.0F, 1.0F, -3.5F);
    main.addChild(neck);
    neck.texOffs(23, 23).addBox(-1.5F, -5.0F, -1.5F, 3.0F, 5.0F, 3.0F, 0.0F, false);

    head = new ModelRenderer(this);
    head.setPos(0.0F, -5.0F, 0.0F);
    neck.addChild(head);
    head.texOffs(20, 8).addBox(-2.0F, -4.0F, -2.5F, 4.0F, 4.0F, 4.0F, 0.0F, false);
    head.texOffs(20, 16).addBox(-1.5F, -2.0F, -4.5F, 3.0F, 2.0F, 2.0F, 0.0F, false);

    tail = new ModelRenderer(this);
    tail.setPos(0.0F, -2.0F, 5.0F);
    main.addChild(tail);
    setRotationAngle(tail, 0.4363F, 0.0F, 0.0F);
    tail.texOffs(18, 0).addBox(-2.0F, 0.0F, -1.0F, 4.0F, 2.0F, 2.0F, 0.0F, false);

    leg_L = new ModelRenderer(this);
    leg_L.setPos(0.25F, 4.0F, 1.0F);
    main.addChild(leg_L);
    leg_L.texOffs(30, 0).addBox(-3.25F, 0.0F, -3.0F, 3.0F, 3.0F, 3.0F, 0.0F, false);

    leg_R = new ModelRenderer(this);
    leg_R.setPos(3.0F, 4.0F, 1.0F);
    main.addChild(leg_R);
    leg_R.texOffs(30, 0).addBox(-3.0F, 0.0F, -3.0F, 3.0F, 3.0F, 3.0F, 0.0F, false);

    wing_L = new ModelRenderer(this);
    wing_L.setPos(-3.0F, -2.0F, 0.0F);
    main.addChild(wing_L);
    wing_L.texOffs(12, 14).addBox(-1.0F, 0.0F, -3.0F, 1.0F, 4.0F, 6.0F, 0.0F, false);

    wing_R = new ModelRenderer(this);
    wing_R.setPos(3.0F, -2.0F, 0.0F);
    main.addChild(wing_R);
    wing_R.texOffs(0, 20).addBox(0.0F, 0.0F, -3.0F, 1.0F, 4.0F, 6.0F, 0.0F, false);
  }

  @Override
  protected Iterable<ModelRenderer> headParts() {
    return Collections.emptyList();
  }

  @Override
  protected Iterable<ModelRenderer> bodyParts() {
    return ImmutableList.of(main);
  }

  @Override
  public void setupAnim(DuckEntity pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
    boolean vallen = false;
    if (pEntity.hasCustomName() && pEntity.getCustomName() != null && (pEntity.getCustomName().getString().equals("Vallen") || pEntity.getCustomName().getString().equals("VallenFrostweavr"))) {
      vallen = true;
    }

    if (pEntity.isInWater()) {
      this.leg_L.visible = false;
      this.leg_R.visible = false;
    } else {
      this.leg_L.visible = true;
      this.leg_R.visible = true;
    }
    this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
    this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
    // TODO: CHECK THESE
    this.leg_L.xRot = MathHelper.cos(pLimbSwing * 0.6662F) * 1.4F * pLimbSwingAmount;
    this.leg_R.xRot = MathHelper.cos(pLimbSwing * 0.6662F + (float)Math.PI) * 1.4F * pLimbSwingAmount;
    this.wing_L.zRot = pAgeInTicks;
    this.wing_R.zRot = -pAgeInTicks;

    if (vallen) {
      this.main.xRot = ((float) Math.PI / 2F);
    } else {
      this.main.xRot = 0;
    }
  }

  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.xRot = x;
    modelRenderer.yRot = y;
    modelRenderer.zRot = z;
  }
}
