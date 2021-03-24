package epicsquid.mysticalworld.item;

import com.google.common.collect.Multimap;
import epicsquid.mysticallib.item.ItemArmorBase;
import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.init.ModItems;
import epicsquid.mysticalworld.init.ModModifiers;
import epicsquid.mysticalworld.materials.Materials;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.oredict.OreIngredient;

public class ItemSilverArmor extends ItemArmorBase {
  public static Ingredient silverIngot = null;

  public ItemSilverArmor(String name, EntityEquipmentSlot equipmentSlotIn) {
    super(name, Materials.silverArmor, equipmentSlotIn, new ResourceLocation(MysticalWorld.MODID, "textures/model/armor/silver_layer"));
  }

  @Override
  public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot slot, ItemStack stack) {
    Multimap<String, AttributeModifier> map = super.getAttributeModifiers(slot, stack);

    if (armorType == slot) {
      if (armorType == EntityEquipmentSlot.CHEST || armorType == EntityEquipmentSlot.LEGS) {
        map.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(Materials.ARMOR_MODIFIERS[slot.getIndex()], "Attack damage multiplier", 0.06f, 2));
      } else {
        map.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(Materials.ARMOR_MODIFIERS[slot.getIndex()], "Attack damage multiplier", 0.02f, 2));
      }

      map.put(ModModifiers.BLESSED.getName(), new AttributeModifier(Materials.ARMOR_MODIFIERS[slot.getIndex()], "Blessed addition", 1f, 0));
    }

    return map;
  }


  @Override
  public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
    if (silverIngot == null) {
      silverIngot = new OreIngredient("ingotSilver");
    }
    return toRepair.getItem() == this && silverIngot.test(repair);
  }
}
