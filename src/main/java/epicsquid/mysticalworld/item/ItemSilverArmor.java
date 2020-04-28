package epicsquid.mysticalworld.item;

import epicsquid.mysticallib.item.ItemArmorBase;
import epicsquid.mysticallib.material.MaterialTypes;
import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.init.ModItems;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.ResourceLocation;

public class ItemSilverArmor extends ItemArmorBase {
  public ItemSilverArmor(String name, EntityEquipmentSlot equipmentSlotIn) {
    super(name, ModItems.silverArmor, equipmentSlotIn, new ResourceLocation(MysticalWorld.MODID, "textures/model/armor/silver_layer"));
  }
}
