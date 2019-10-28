package epicsquid.mysticalworld.items;

import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.item.AxeItem;
import net.minecraft.item.IItemTier;

import java.util.HashMap;
import java.util.Map;

public abstract class ModifiedAxeItem extends AxeItem {
  protected Map<IAttribute, AttributeModifier> modifiers = new HashMap<>();

  public Map<IAttribute, AttributeModifier> getModifiers() {
    return modifiers;
  }
  public ModifiedAxeItem(IItemTier tier, float attackDamageIn, float attackSpeedIn, Properties builder) {
    super(tier, attackDamageIn, attackSpeedIn, builder);
  }
}
