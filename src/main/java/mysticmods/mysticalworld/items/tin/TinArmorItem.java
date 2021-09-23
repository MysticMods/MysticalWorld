package mysticmods.mysticalworld.items.tin;

import com.google.common.collect.Multimap;
import mysticmods.mysticalworld.items.ModifiedArmorItem;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraftforge.common.ForgeMod;
import noobanidus.libs.noobutil.material.MaterialType;

import net.minecraft.item.Item.Properties;

public class TinArmorItem extends ModifiedArmorItem implements ITinItem {
  public TinArmorItem(IArmorMaterial materialIn, EquipmentSlotType slot, Properties builder) {
    super(materialIn, slot, builder);
  }

  @Override
  public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlotType equipmentSlot) {
    Multimap<Attribute, AttributeModifier> map = super.getDefaultAttributeModifiers(equipmentSlot);

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
