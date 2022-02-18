package mysticmods.mysticalworld.client.model;

import com.google.common.collect.ImmutableList;
import mysticmods.mysticalworld.entity.DeerEntity;
import net.minecraft.client.model.AgeableListModel;
import net.minecraft.client.model.geom.ModelPart;

import javax.annotation.Nonnull;
import java.util.Collections;

public class DeerModel extends AgeableListModel<DeerEntity> {

  //fields
  private final ModelPart head;
  private final ModelPart ear1;
  private final ModelPart ear2;
  private final ModelPart neck;
  private final ModelPart body;
  private final ModelPart tail;
  private final ModelPart legRF;
  private final ModelPart legLF;
  private final ModelPart legLB;
  private final ModelPart legRB;
  private final ModelPart horn1;
  private final ModelPart horn2;
  private final ModelPart horn3;
  private final ModelPart horn4;
  private final ModelPart horn5;
  private final ModelPart horn6;
  private final ModelPart horn7;
  private final ModelPart horn8;

  public DeerModel() {
    super(true, 5.0f, 2.0f);
    texWidth = 64;
    texHeight = 64;

    head = new ModelPart(this, 0, 16);
    head.addBox(-2F, 0F, -2.5F, 4, 7, 4);
    head.setPos(0F, 7F, -6.953333F);
    head.setTexSize(64, 64);
    head.mirror = true;
    setRotation(head, 1.047198F, 0F, 0F);
    ear1 = new ModelPart(this, 17, 0);
    ear1.addBox(-1.5F, -4F, -0.5F, 3, 4, 1);
    ear1.setPos(-2F, 5.5F, -6.5F);
    ear1.setTexSize(64, 64);
    ear1.mirror = true;
    setRotation(ear1, -0.174532925F, -0.174532925F, -1.178097F);
    ear2 = new ModelPart(this, 17, 0);
    ear2.mirror = true;
    ear2.addBox(-1.5F, -4F, -0.5F, 3, 4, 1);
    ear2.setPos(2F, 5.5F, -6.5F);
    ear2.setTexSize(64, 64);
    setRotation(ear2, 0.174532925F, 0.174532925F, 1.178097F);
    neck = new ModelPart(this, 0, 0);
    neck.addBox(-2.5F, -2.5F, -7F, 5, 5, 7);
    neck.setPos(0F, 6F, -5F);
    neck.setTexSize(64, 64);
    neck.mirror = true;
    setRotation(neck, 0.3926991F, 0F, 0F);
    body = new ModelPart(this, 16, 16);
    body.addBox(-2.5F, 0F, 0F, 5, 7, 9);
    body.setPos(0F, 8F, -3.953333F);
    body.setTexSize(64, 64);
    body.mirror = true;
    setRotation(body, 0F, 0F, 0F);
    tail = new ModelPart(this, 32, 0);
    tail.addBox(-1.5F, -1.5F, 0F, 3, 3, 4);
    tail.setPos(0F, 9F, 4F);
    tail.setTexSize(64, 64);
    tail.mirror = true;
    setRotation(tail, 0.7853982F, 0F, 0F);
    legRF = new ModelPart(this, 0, 32);
    legRF.addBox(-1F, 0F, -1F, 2, 9, 2);
    legRF.setPos(-1.5F, 15F, -2.953333F);
    legRF.setTexSize(64, 64);
    legRF.mirror = true;
    setRotation(legRF, 0F, 0F, 0F);
    legLF = new ModelPart(this, 0, 32);
    legLF.addBox(-1F, 0F, -1F, 2, 9, 2);
    legLF.setPos(1.5F, 15F, -2.953333F);
    legLF.setTexSize(64, 64);
    legLF.mirror = true;
    setRotation(legLF, 0F, 0F, 0F);
    legLB = new ModelPart(this, 0, 32);
    legLB.addBox(-1F, 0F, -1F, 2, 9, 2);
    legLB.setPos(-1.5F, 15F, 4F);
    legLB.setTexSize(64, 64);
    legLB.mirror = true;
    setRotation(legLB, 0F, 0F, 0F);
    legRB = new ModelPart(this, 0, 32);
    legRB.addBox(-1F, 0F, -1F, 2, 9, 2);
    legRB.setPos(1.5F, 15F, 4F);
    legRB.setTexSize(64, 64);
    legRB.mirror = true;
    setRotation(legRB, 0F, 0F, 0F);
    horn1 = new ModelPart(this, 16, 32);
    horn1.addBox(-0.5F, -5F, -0.5F, 1, 5, 1);
    horn1.setPos(1F, 4F, -6F);
    horn1.setTexSize(64, 64);
    horn1.mirror = true;
    setRotation(horn1, 0F, 0F, 0.2617994F);
    horn2 = new ModelPart(this, 16, 32);
    horn2.addBox(-0.5F, -5F, -0.5F, 1, 5, 1);
    horn2.setPos(-1F, 4F, -6F);
    horn2.setTexSize(64, 64);
    horn2.mirror = true;
    setRotation(horn2, 0F, 0F, -0.2617994F);
    horn3 = new ModelPart(this, 16, 32);
    horn3.addBox(-0.5F, -5F, -0.5F, 1, 5, 1);
    horn3.setPos(1.75F, 2F, -6F);
    horn3.setTexSize(64, 64);
    horn3.mirror = true;
    setRotation(horn3, 0F, 0.0872665F, 1.047198F);
    horn4 = new ModelPart(this, 16, 32);
    horn4.addBox(-0.5F, -5F, -0.5F, 1, 5, 1);
    horn4.setPos(-1.8F, 2F, -6F);
    horn4.setTexSize(64, 64);
    horn4.mirror = true;
    setRotation(horn4, 0F, -0.0872665F, -1.047198F);
    horn5 = new ModelPart(this, 16, 32);
    horn5.addBox(-0.5F, -5F, -0.5F, 1, 5, 1);
    horn5.setPos(-4.36F, 0.2F, -6.2F);
    horn5.setTexSize(64, 64);
    horn5.mirror = true;
    setRotation(horn5, 0F, -0.0872665F, 0.2617994F);
    horn6 = new ModelPart(this, 16, 32);
    horn6.addBox(-0.5F, -5F, -0.5F, 1, 5, 1);
    horn6.setPos(4.4F, 0.2F, -6.2F);
    horn6.setTexSize(64, 64);
    horn6.mirror = true;
    setRotation(horn6, 0F, 0.0872665F, -0.2617994F);
    horn7 = new ModelPart(this, 20, 32);
    horn7.addBox(-0.5F, -3F, -0.5F, 1, 3, 1);
    horn7.setPos(-3.8F, -2F, -6.2F);
    horn7.setTexSize(64, 64);
    horn7.mirror = true;
    setRotation(horn7, 0F, -0.1745329F, -0.7853982F);
    horn8 = new ModelPart(this, 20, 32);
    horn8.addBox(-0.5F, -3F, -0.5F, 1, 3, 1);
    horn8.setPos(3.8F, -2F, -6.2F);
    horn8.setTexSize(64, 64);
    horn8.mirror = true;
    setRotation(horn8, 0F, 0.1745329F, 0.7853982F);
  }

  @Override
  protected Iterable<ModelPart> headParts() {
    return Collections.emptyList();
  }

  @Override
  protected Iterable<ModelPart> bodyParts() {
    return ImmutableList.of(ear1, ear2, neck, body, tail, legRF, legLF, legLB, legRB, horn1, horn2, horn3, horn4, horn5, horn6, horn7, horn8, head);
  }

  @Override
  public void setupAnim(DeerEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    float sin = (float) Math.sin(ageInTicks * 0.125f * (Math.PI * 2.0f));
    legRF.xRot = limbSwingAmount * sin;
    legLF.xRot = -limbSwingAmount * sin;
    legLB.xRot = limbSwingAmount * sin;
    legRB.xRot = -limbSwingAmount * sin;
    if (!entityIn.getEntityData().get(DeerEntity.hasHorns)) {
      horn1.visible = false;
      horn2.visible = false;
      horn3.visible = false;
      horn4.visible = false;
      horn5.visible = false;
      horn6.visible = false;
      horn7.visible = false;
      horn8.visible = false;
    } else {
      horn1.visible = true;
      horn2.visible = true;
      horn3.visible = true;
      horn4.visible = true;
      horn5.visible = true;
      horn6.visible = true;
      horn7.visible = true;
      horn8.visible = true;
    }
  }

  private void setRotation(@Nonnull ModelPart model, float x, float y, float z) {
    model.xRot = x;
    model.yRot = y;
    model.zRot = z;
  }
}
