package mysticmods.mysticalworld.items.silver;

import com.google.common.collect.Multimap;
import mysticmods.mysticalworld.init.ModModifiers;
import mysticmods.mysticalworld.items.IModifiable;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.EquipmentSlot;
import noobanidus.libs.noobutil.material.MaterialType;

public interface ISmitingItem extends IModifiable {
  default Multimap<Attribute, AttributeModifier> modifyAttributes(Multimap<Attribute, AttributeModifier> map, EquipmentSlot equipmentSlot, float amount) {

    if (equipmentSlot == EquipmentSlot.MAINHAND) {
      map.put(ModModifiers.SMITE.get(), this.getOrCreateModifier(ModModifiers.SMITE.get(), () -> new AttributeModifier(MaterialType.MAIN_HAND_MODIFIER, "Smite addition", amount, AttributeModifier.Operation.ADDITION)));
    }

    return map;
  }
}
