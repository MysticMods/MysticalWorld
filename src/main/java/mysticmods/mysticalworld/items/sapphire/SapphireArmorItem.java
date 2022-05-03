package mysticmods.mysticalworld.items.sapphire;

import com.google.common.collect.Multimap;
import mysticmods.mysticalworld.init.ModModifiers;
import mysticmods.mysticalworld.items.lead.ILeadItem;
import mysticmods.mysticalworld.items.modified.ModifiedArmorItem;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ArmorMaterial;
import noobanidus.libs.noobutil.material.MaterialType;

public class SapphireArmorItem extends ModifiedArmorItem implements ILeadItem {
  public SapphireArmorItem(ArmorMaterial materialIn, EquipmentSlot slot, Properties builder) {
    super(materialIn, slot, builder);
  }

  @Override
  public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot equipmentSlot) {
    Multimap<Attribute, AttributeModifier> map = super.getDefaultAttributeModifiers(equipmentSlot);

    if (this.slot == equipmentSlot) {
      if (slot == EquipmentSlot.CHEST || slot == EquipmentSlot.LEGS) {
        map.put(ModModifiers.SERENDIPITY.get(), getOrCreateModifier(ModModifiers.SERENDIPITY.get(), () -> new AttributeModifier(MaterialType.ARMOR_MODIFIERS[slot.getIndex()], "Serendipity addition", 0.50f, AttributeModifier.Operation.ADDITION)));
      } else {
        map.put(ModModifiers.SERENDIPITY.get(), getOrCreateModifier(ModModifiers.SERENDIPITY.get(), () -> new AttributeModifier(MaterialType.ARMOR_MODIFIERS[slot.getIndex()], "Serendipity addition", 0.25f, AttributeModifier.Operation.ADDITION)));
      }

      map.put(Attributes.LUCK, getOrCreateModifier(Attributes.LUCK, () -> new AttributeModifier(MaterialType.ARMOR_MODIFIERS[slot.getIndex()], "Luck multiplier", 1f, AttributeModifier.Operation.ADDITION)));
    }

    return map;
  }
}
