package mysticmods.mysticalworld.items.orichalcum;

import com.google.common.collect.Multimap;
import mysticmods.mysticalworld.items.modified.ModifiedArmorItem;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraftforge.common.ForgeMod;
import noobanidus.libs.noobutil.material.MaterialType;

public class OrichalcumArmorItem extends ModifiedArmorItem implements IOrichalcumItem {
  public OrichalcumArmorItem(ArmorMaterial material, EquipmentSlot slot, Properties props) {
    super(material, slot, props);
  }

  @Override
  public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot equipmentSlot) {
    Multimap<Attribute, AttributeModifier> map = super.getDefaultAttributeModifiers(equipmentSlot);

    if (this.slot == equipmentSlot) {
      float val = 0.05f;
      if (slot == EquipmentSlot.CHEST || slot == EquipmentSlot.LEGS) {
        val = 0.1f;
      }
      final float val2 = val;
      map.put(Attributes.MOVEMENT_SPEED, getOrCreateModifier(Attributes.MOVEMENT_SPEED, () -> new AttributeModifier(MaterialType.ARMOR_MODIFIERS[slot.getIndex()], "Movement speed", val2, AttributeModifier.Operation.MULTIPLY_BASE)));
      map.put(ForgeMod.SWIM_SPEED.get(), getOrCreateModifier(ForgeMod.SWIM_SPEED.get(), () -> new AttributeModifier(MaterialType.ARMOR_MODIFIERS[slot.getIndex()], "Movement speed", val2, AttributeModifier.Operation.MULTIPLY_BASE)));
    }

    return map;
  }
}
