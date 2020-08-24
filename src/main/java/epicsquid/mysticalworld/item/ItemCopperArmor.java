package epicsquid.mysticalworld.item;

import com.google.common.collect.Multimap;
import epicsquid.mysticallib.item.ItemArmorBase;
import epicsquid.mysticallib.material.MaterialTypes;
import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.config.ConfigManager;
import epicsquid.mysticalworld.init.ModItems;
import epicsquid.mysticalworld.materials.Material;
import epicsquid.mysticalworld.materials.Materials;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class ItemCopperArmor extends ItemArmorBase {
  public ItemCopperArmor(String name, EntityEquipmentSlot equipmentSlotIn) {
    super(name, ModItems.copperArmor, equipmentSlotIn, new ResourceLocation(MysticalWorld.MODID, "textures/model/armor/copper_layer"));
  }

  @Override
  public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot slot, ItemStack stack)   {
    Multimap<String, AttributeModifier> map = super.getAttributeModifiers(slot, stack);

    if (slot == this.getEquipmentSlot()) {
      map.put(SharedMonsterAttributes.MAX_HEALTH.getName(), new AttributeModifier(Materials.ARMOR_MODIFIERS[slot.getIndex()], "Healthiness", 2, 0));
    }

    return map;
  }
}
