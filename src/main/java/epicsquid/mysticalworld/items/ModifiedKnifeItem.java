package epicsquid.mysticalworld.items;

import com.google.common.collect.Multimap;
import com.google.common.collect.MultimapBuilder;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;
import noobanidus.libs.noobutil.item.BaseItems;

import java.util.HashMap;
import java.util.Map;

public abstract class ModifiedKnifeItem extends BaseItems.KnifeItem implements IModifiable {
  protected Map<Attribute, AttributeModifier> modifiers = new HashMap<>();

  @Override
  public Map<Attribute, AttributeModifier> getModifiers() {
    return modifiers;
  }

  public ModifiedKnifeItem(IItemTier tier, float attackDamage, float attackSpeed, Properties props) {
    super(tier, attackDamage, attackSpeed, props);
  }

  @Override
  public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot) {
    Multimap<Attribute, AttributeModifier> result = super.getAttributeModifiers(equipmentSlot);
    if (result.isEmpty()) {
      //noinspection UnstableApiUsage
      return MultimapBuilder.hashKeys().hashSetValues().build();
    } else {
      //noinspection UnstableApiUsage
      return MultimapBuilder.hashKeys().hashSetValues().build(result);
    }
  }
}
