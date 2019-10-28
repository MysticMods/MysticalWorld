package epicsquid.mysticalworld.items;

import epicsquid.mysticallib.item.KnifeItem;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.item.IItemTier;

import java.util.HashMap;
import java.util.Map;

public abstract class ModifiedKnifeItem extends KnifeItem implements IModifiable {
  protected Map<IAttribute, AttributeModifier> modifiers = new HashMap<>();

  @Override
  public Map<IAttribute, AttributeModifier> getModifiers() {
    return modifiers;
  }

  public ModifiedKnifeItem(IItemTier tier, float attackDamage, float attackSpeed, Properties props) {
    super(tier, attackDamage, attackSpeed, props);
  }
}
