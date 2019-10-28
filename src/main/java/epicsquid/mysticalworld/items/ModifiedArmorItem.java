package epicsquid.mysticalworld.items;

import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

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
