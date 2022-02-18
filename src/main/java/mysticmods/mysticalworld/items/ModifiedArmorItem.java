package mysticmods.mysticalworld.items;

import com.google.common.collect.Multimap;
import com.google.common.collect.MultimapBuilder;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.world.item.Item.Properties;

public abstract class ModifiedArmorItem extends ArmorItem implements IModifiable {
  protected Map<Attribute, AttributeModifier> modifiers = new HashMap<>();

  @Override
  public Map<Attribute, AttributeModifier> getModifiers() {
    return modifiers;
  }

  public ModifiedArmorItem(ArmorMaterial materialIn, EquipmentSlot slot, Properties builder) {
    super(materialIn, slot, builder);
  }

  @Override
  public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot equipmentSlot) {
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
