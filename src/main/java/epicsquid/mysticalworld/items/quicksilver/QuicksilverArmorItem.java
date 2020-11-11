package epicsquid.mysticalworld.items.quicksilver;

import com.google.common.collect.Multimap;
import epicsquid.mysticalworld.items.ModifiedArmorItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeMod;
import noobanidus.libs.noobutil.material.MaterialType;

import java.util.Random;

// TODO find a way to block damaging the item
public class QuicksilverArmorItem extends ModifiedArmorItem implements IQuicksilverItem {
  private int counter;
  private Random random = new Random();

  public QuicksilverArmorItem(IArmorMaterial material, EquipmentSlotType slot, Properties props) {
    super(material, slot, props);
  }

  @Override
  public void inventoryTick(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
    counter = counter >= 20 ? 1 : counter + 1;
    if (counter % 20 == 0 && entity instanceof LivingEntity) {
      drip(stack, (LivingEntity) entity, random, getEquipmentSlot());
    }
  }

  @Override
  public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot) {
    Multimap<Attribute, AttributeModifier> map = super.getAttributeModifiers(equipmentSlot);

    if (this.slot == equipmentSlot) {
      float val = 0.05f;
      if (slot == EquipmentSlotType.CHEST || slot == EquipmentSlotType.LEGS) {
        val = 0.1f;
      }
      final float val2 = val;
      map.put(Attributes.MOVEMENT_SPEED, getOrCreateModifier(Attributes.MOVEMENT_SPEED, () -> new AttributeModifier(MaterialType.ARMOR_MODIFIERS[slot.getIndex()], "Movement speed", val2, AttributeModifier.Operation.MULTIPLY_BASE)));
      map.put(ForgeMod.SWIM_SPEED.get(), getOrCreateModifier(ForgeMod.SWIM_SPEED.get(), () -> new AttributeModifier(MaterialType.ARMOR_MODIFIERS[slot.getIndex()], "Movement speed", val2, AttributeModifier.Operation.MULTIPLY_BASE)));
    }

    return map;
  }
}
