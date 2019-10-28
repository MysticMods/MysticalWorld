package epicsquid.mysticalworld.items;

import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.item.IItemTier;
import net.minecraft.item.SwordItem;

import java.util.HashMap;
import java.util.Map;

public abstract class ModifiedSwordItem extends SwordItem implements IModifiable {
  protected Map<IAttribute, AttributeModifier> modifiers = new HashMap<>();

  @Override
  public Map<IAttribute, AttributeModifier> getModifiers() {
    return modifiers;
  }

  public ModifiedSwordItem(IItemTier tier, int attackDamageIn, float attackSpeedIn, Properties builder) {
    super(tier, attackDamageIn, attackSpeedIn, builder);
  }
}
