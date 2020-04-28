package epicsquid.mysticalworld.item;

import epicsquid.mysticallib.item.ItemArmorBase;
import epicsquid.mysticallib.material.MaterialTypes;
import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.init.ModItems;
import epicsquid.mysticalworld.materials.Material;
import epicsquid.mysticalworld.materials.Materials;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.ResourceLocation;

public class ItemCopperArmor extends ItemArmorBase {
  public ItemCopperArmor(String name, EntityEquipmentSlot equipmentSlotIn) {
    super(name, ModItems.copperArmor, equipmentSlotIn, new ResourceLocation(MysticalWorld.MODID, "textures/model/armor/copper_layer"));
  }
}
