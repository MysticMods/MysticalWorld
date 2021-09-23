package mysticmods.mysticalworld.items;

import com.google.common.collect.Multimap;
import com.google.common.collect.MultimapBuilder;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.HoeItem;
import net.minecraft.item.IItemTier;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.item.Item.Properties;

public abstract class ModifiedHoeItem extends HoeItem implements IModifiable {
  protected Map<Attribute, AttributeModifier> modifiers = new HashMap<>();

  public ModifiedHoeItem(IItemTier itemTier, int attackDamage, float attackSpeed, Properties properties) {
    super(itemTier, attackDamage, attackSpeed, properties);
  }


  @Override
  public Map<Attribute, AttributeModifier> getModifiers() {
    return modifiers;
  }

  @Override
  public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlotType equipmentSlot) {
    Multimap<Attribute, AttributeModifier> result = super.getDefaultAttributeModifiers(equipmentSlot);
    if (result.isEmpty()) {
      //noinspection UnstableApiUsage
      return MultimapBuilder.hashKeys().hashSetValues().build();
    } else {
      //noinspection UnstableApiUsage
      return MultimapBuilder.hashKeys().hashSetValues().build(result);
    }
  }
}
