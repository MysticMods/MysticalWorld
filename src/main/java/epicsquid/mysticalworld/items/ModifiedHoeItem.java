package epicsquid.mysticalworld.items;

import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.item.HoeItem;
import net.minecraft.item.IItemTier;

import java.util.HashMap;
import java.util.Map;

public abstract class ModifiedHoeItem extends HoeItem implements IModifiable {
  protected Map<Attribute, AttributeModifier> modifiers = new HashMap<>();

  public ModifiedHoeItem(IItemTier itemTier, int attackDamage, float attackSpeed, Properties properties) {
    super(itemTier, attackDamage, attackSpeed, properties);
  }


  @Override
  public Map<Attribute, AttributeModifier> getModifiers() {
    return modifiers;
  }
}
