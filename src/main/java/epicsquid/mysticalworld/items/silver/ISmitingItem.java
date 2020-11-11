package epicsquid.mysticalworld.items.silver;

import com.google.common.collect.Multimap;
import epicsquid.mysticalworld.init.ModModifiers;
import epicsquid.mysticalworld.items.IModifiable;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EquipmentSlotType;
import noobanidus.libs.noobutil.material.MaterialType;

public interface ISmitingItem extends IModifiable {
  default Multimap<Attribute, AttributeModifier> modifyAttributes(Multimap<Attribute, AttributeModifier> map, EquipmentSlotType equipmentSlot, float amount) {

    if (equipmentSlot == EquipmentSlotType.MAINHAND) {
      map.put(ModModifiers.SMITE.get(), this.getOrCreateModifier(ModModifiers.SMITE.get(), () -> new AttributeModifier(MaterialType.MAIN_HAND_MODIFIER, "Smite addition", amount, AttributeModifier.Operation.ADDITION)));
    }

    return map;
  }
}
