package epicsquid.mysticalworld.items.tin;

import com.google.common.collect.Multimap;
import epicsquid.mysticalworld.items.ModifiedArmorItem;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraftforge.common.ForgeMod;
import noobanidus.libs.noobutil.material.MaterialType;

public class TinArmorItem extends ModifiedArmorItem implements ITinItem {
  public TinArmorItem(IArmorMaterial materialIn, EquipmentSlotType slot, Properties builder) {
    super(materialIn, slot, builder);
  }

  @Override
  public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot) {
    Multimap<Attribute, AttributeModifier> map = super.getAttributeModifiers(equipmentSlot);

    if (this.slot == equipmentSlot) {
      float val = 0.25f;
      if (slot == EquipmentSlotType.CHEST || slot == EquipmentSlotType.LEGS) {
        val = 0.5f;
      }
      final float val2 = val;
      map.put(ForgeMod.REACH_DISTANCE.get(), getOrCreateModifier(ForgeMod.REACH_DISTANCE.get(), () -> new AttributeModifier(MaterialType.ARMOR_MODIFIERS[slot.getIndex()], "Reachiness", val2, AttributeModifier.Operation.ADDITION)));
    }

    return map;
  }
}
