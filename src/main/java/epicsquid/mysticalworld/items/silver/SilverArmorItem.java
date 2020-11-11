package epicsquid.mysticalworld.items.silver;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import epicsquid.mysticalworld.init.ModModifiers;
import epicsquid.mysticalworld.items.ModifiedArmorItem;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import noobanidus.libs.noobutil.material.MaterialType;

public class SilverArmorItem extends ModifiedArmorItem implements ISilverItem {
  private final Multimap<Attribute, AttributeModifier> largeSlot;
  private final Multimap<Attribute, AttributeModifier> smallSlot;

  public SilverArmorItem(IArmorMaterial materialIn, EquipmentSlotType slot, Properties builder) {
    super(materialIn, slot, builder);

    ImmutableMultimap.Builder<Attribute, AttributeModifier> map = ImmutableMultimap.builder();
    map.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Attack damage multiplier", 0.06f, AttributeModifier.Operation.MULTIPLY_TOTAL));
    map.put(ModModifiers.BLESSED, new AttributeModifier(ModModifiers.BLESSED, "Blessed addition", 1f, AttributeModifier.Operation.ADDITION));
  }

  @Override
  public Multimap<String, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot) {
    Multimap<String, AttributeModifier> map = super.getAttributeModifiers(equipmentSlot);

    if (this.slot == equipmentSlot) {
      if (slot == EquipmentSlotType.CHEST || slot == EquipmentSlotType.LEGS) {
        map.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), getOrCreateModifier(SharedMonsterAttributes.ATTACK_DAMAGE, () -> new AttributeModifier(MaterialType.ARMOR_MODIFIERS[slot.getIndex()], "Attack damage multiplier", 0.06f, AttributeModifier.Operation.MULTIPLY_TOTAL)));
      } else {
        map.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), getOrCreateModifier(SharedMonsterAttributes.ATTACK_DAMAGE, () -> new AttributeModifier(MaterialType.ARMOR_MODIFIERS[slot.getIndex()], "Attack damage multiplier", 0.02f, AttributeModifier.Operation.MULTIPLY_TOTAL)));
      }

      map.put(ModModifiers.BLESSED.getName(), getOrCreateModifier(ModModifiers.BLESSED, () -> new AttributeModifier(MaterialType.ARMOR_MODIFIERS[slot.getIndex()], "Blessed addition", 1f, AttributeModifier.Operation.ADDITION)));
    }

    return map;
  }
}
