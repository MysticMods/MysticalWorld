package mysticmods.mysticalworld.items.silver;

import com.google.common.collect.Multimap;
import mysticmods.mysticalworld.init.ModModifiers;
import mysticmods.mysticalworld.items.modified.ModifiedArmorItem;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ArmorMaterial;
import noobanidus.libs.noobutil.material.MaterialType;

public class SilverArmorItem extends ModifiedArmorItem implements ISilverItem {
  public SilverArmorItem(ArmorMaterial materialIn, EquipmentSlot slot, Properties builder) {
    super(materialIn, slot, builder);
  }

  @Override
  public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot equipmentSlot) {
    Multimap<Attribute, AttributeModifier> map = super.getDefaultAttributeModifiers(equipmentSlot);

    if (this.slot == equipmentSlot) {
      if (slot == EquipmentSlot.CHEST || slot == EquipmentSlot.LEGS) {
        map.put(Attributes.ATTACK_DAMAGE, getOrCreateModifier(Attributes.ATTACK_DAMAGE, () -> new AttributeModifier(MaterialType.ARMOR_MODIFIERS[slot.getIndex()], "Attack damage multiplier", 0.06f, AttributeModifier.Operation.MULTIPLY_TOTAL)));
      } else {
        map.put(Attributes.ATTACK_DAMAGE, getOrCreateModifier(Attributes.ATTACK_DAMAGE, () -> new AttributeModifier(MaterialType.ARMOR_MODIFIERS[slot.getIndex()], "Attack damage multiplier", 0.02f, AttributeModifier.Operation.MULTIPLY_TOTAL)));
      }

      map.put(ModModifiers.BLESSED.get(), getOrCreateModifier(ModModifiers.BLESSED.get(), () -> new AttributeModifier(MaterialType.ARMOR_MODIFIERS[slot.getIndex()], "Blessed addition", 1f, AttributeModifier.Operation.ADDITION)));
    }

    return map;
  }
}
