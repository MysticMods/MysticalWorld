package mysticmods.mysticalworld.items;

import com.google.common.collect.Multimap;
import com.google.common.collect.MultimapBuilder;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import noobanidus.libs.noobutil.item.BaseItems;

import java.util.HashMap;
import java.util.Map;

public class ModifiedSpearItem extends BaseItems.SpearItem implements IModifiable {
  protected Map<Attribute, AttributeModifier> modifiers = new HashMap<>();

  public ModifiedSpearItem(IItemTier tier, int attackDamageIn, float attackSpeedIn, Properties builder) {
    super(tier, attackDamageIn, attackSpeedIn, builder);
  }

  @Override
  public Map<Attribute, AttributeModifier> getModifiers() {
    return modifiers;
  }

  @Override
  public UseAction getUseAction(ItemStack stack) {
    return UseAction.SPEAR;
  }

  @Override
  public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot) {
    Multimap<Attribute, AttributeModifier> result = super.getAttributeModifiers(equipmentSlot);
    if (result.isEmpty()) {
      //noinspection UnstableApiUsage
      return MultimapBuilder.hashKeys().hashSetValues().build();
    } else {
      //noinspection UnstableApiUsage
      return MultimapBuilder.hashKeys().hashSetValues().build(result);
    }
  }
}
