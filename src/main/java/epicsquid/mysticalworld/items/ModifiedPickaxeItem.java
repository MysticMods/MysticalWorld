package epicsquid.mysticalworld.items;

import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.item.IItemTier;
import net.minecraft.item.PickaxeItem;

import java.util.HashMap;
import java.util.Map;

public abstract class ModifiedPickaxeItem extends PickaxeItem implements IModifiable {
  protected Map<IAttribute, AttributeModifier> modifiers = new HashMap<>();

  @Override
  public Map<IAttribute, AttributeModifier> getModifiers() {
    return modifiers;
  }

  public ModifiedPickaxeItem(IItemTier tier, int attackDamageIn, float attackSpeedIn, Properties builder) {
    super(tier, attackDamageIn, attackSpeedIn, builder);
  }
}
