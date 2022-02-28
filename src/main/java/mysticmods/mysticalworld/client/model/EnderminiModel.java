package mysticmods.mysticalworld.client.model;

import net.minecraft.client.model.EndermanModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.LivingEntity;

public class EnderminiModel<T extends LivingEntity> extends EndermanModel<T> {
  public EnderminiModel(ModelPart pRoot) {
    super(pRoot);
  }
}
