package mysticmods.mysticalworld.items;

import com.google.common.collect.Multimap;
import com.google.common.collect.MultimapBuilder;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import noobanidus.libs.noobutil.item.BaseItems;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.world.item.Item.Properties;

public class ModifiedSpearItem extends BaseItems.SpearItem implements IModifiable {
  protected Map<Attribute, AttributeModifier> modifiers = new HashMap<>();

  public ModifiedSpearItem(Tier tier, int attackDamageIn, float attackSpeedIn, Properties builder) {
    super(tier, attackDamageIn, attackSpeedIn, builder);
  }

  @Override
  public Map<Attribute, AttributeModifier> getModifiers() {
    return modifiers;
  }

  @Override
  public UseAnim getUseAnimation(ItemStack stack) {
    return UseAnim.SPEAR;
  }

  @Override
  public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot equipmentSlot) {
    Multimap<Attribute, AttributeModifier> result = super.getDefaultAttributeModifiers(equipmentSlot);
    if (result.isEmpty()) {
      //noinspection UnstableApiUsage
      return MultimapBuilder.hashKeys().hashSetValues().build();
    } else {
      //noinspection UnstableApiUsage
      return MultimapBuilder.hashKeys().hashSetValues().build(result);
    }
  }
}
