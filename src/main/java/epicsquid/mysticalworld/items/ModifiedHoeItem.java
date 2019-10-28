package epicsquid.mysticalworld.items;

import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.item.HoeItem;
import net.minecraft.item.IItemTier;

import java.util.HashMap;
import java.util.Map;

public abstract class ModifiedHoeItem extends HoeItem implements IModifiable {
  protected Map<IAttribute, AttributeModifier> modifiers = new HashMap<>();

  public ModifiedHoeItem(IItemTier tier, float attackSpeedIn, Properties builder) {
    super(tier, attackSpeedIn, builder);
  }

  @Override
  public Map<IAttribute, AttributeModifier> getModifiers() {
    return modifiers;
  }
}
