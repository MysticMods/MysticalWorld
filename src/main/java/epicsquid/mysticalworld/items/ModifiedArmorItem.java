package epicsquid.mysticalworld.items;

import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;

import java.util.HashMap;
import java.util.Map;

public abstract class ModifiedArmorItem extends ArmorItem implements IModifiable {
  protected Map<IAttribute, AttributeModifier> modifiers = new HashMap<>();

  @Override
  public Map<IAttribute, AttributeModifier> getModifiers() {
    return modifiers;
  }

  public ModifiedArmorItem(IArmorMaterial materialIn, EquipmentSlotType slot, Properties builder) {
    super(materialIn, slot, builder);
  }
}
