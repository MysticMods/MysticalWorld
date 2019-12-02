package epicsquid.mysticalworld.items;

import com.google.common.collect.Multimap;
import epicsquid.mysticallib.item.SpearItem;
import epicsquid.mysticallib.material.MaterialType;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;

import java.util.HashMap;
import java.util.Map;

public class ModifiedSpearItem extends SpearItem implements IModifiable {
  protected Map<IAttribute, AttributeModifier> modifiers = new HashMap<>();

  public ModifiedSpearItem(IItemTier tier, int attackDamageIn, float attackSpeedIn, Properties builder) {
    super(tier, attackDamageIn, attackSpeedIn, builder);
  }

  @Override
  public Map<IAttribute, AttributeModifier> getModifiers() {
    return modifiers;
  }

  @Override
  public Multimap<String, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot) {
    Multimap<String, AttributeModifier> map = super.getAttributeModifiers(equipmentSlot);

    if (equipmentSlot == EquipmentSlotType.MAINHAND) {
      map.put(PlayerEntity.REACH_DISTANCE.getName(), getOrCreateModifier(PlayerEntity.REACH_DISTANCE, () -> new AttributeModifier(MaterialType.MAIN_HAND_MODIFIER, "Reaching", 2.5, AttributeModifier.Operation.ADDITION)));
    }

    return map;
  }
}
