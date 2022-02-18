package mysticmods.mysticalworld.items.tin;

import com.google.common.collect.Multimap;
import mysticmods.mysticalworld.items.ModifiedArmorItem;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraftforge.common.ForgeMod;
import noobanidus.libs.noobutil.material.MaterialType;

public class TinArmorItem extends ModifiedArmorItem implements ITinItem {
  public TinArmorItem(ArmorMaterial materialIn, EquipmentSlot slot, Properties builder) {
    super(materialIn, slot, builder);
  }

  @Override
  public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot equipmentSlot) {
    Multimap<Attribute, AttributeModifier> map = super.getDefaultAttributeModifiers(equipmentSlot);

    if (this.slot == equipmentSlot) {
      float val = 0.25f;
      if (slot == EquipmentSlot.CHEST || slot == EquipmentSlot.LEGS) {
        val = 0.5f;
      }
      final float val2 = val;
      map.put(ForgeMod.REACH_DISTANCE.get(), getOrCreateModifier(ForgeMod.REACH_DISTANCE.get(), () -> new AttributeModifier(MaterialType.ARMOR_MODIFIERS[slot.getIndex()], "Reachiness", val2, AttributeModifier.Operation.ADDITION)));
    }

    return map;
  }
}
