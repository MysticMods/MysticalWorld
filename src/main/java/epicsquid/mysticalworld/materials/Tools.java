package epicsquid.mysticalworld.materials;

import epicsquid.mysticallib.event.RegisterContentEvent;
import epicsquid.mysticallib.item.*;
import epicsquid.mysticalworld.MysticalWorld;

import javax.annotation.Nonnull;

public class Tools {
  public static void registerTools(@Nonnull RegisterContentEvent event) {
    for (Material material : Materials.getMaterials()) {
      if (material.hasTool()) {
        event.addItem(material.setAxe(new ItemAxeBase(material.getMaterial(), material.name() + "_axe", material.getMaterial().getHarvestLevel(), material.getMaterial().getMaxUses(), material.getRepairIngredient()).setCreativeTab(MysticalWorld.tab).setMaxStackSize(1)));
        event.addItem(material.setHoe(new ItemHoeBase(material.getMaterial(), material.name() + "_hoe", material.getMaterial().getHarvestLevel(), material.getMaterial().getMaxUses(), material.getRepairIngredient()).setCreativeTab(MysticalWorld.tab).setMaxStackSize(1)));
        event.addItem(material.setPickaxe((new ItemPickaxeBase(material.getMaterial(), material.name() + "_pickaxe", material.getMaterial().getHarvestLevel(), material.getMaterial().getMaxUses(), material.getRepairIngredient()).setCreativeTab(MysticalWorld.tab).setMaxStackSize(1))));
        event.addItem(material.setShovel(new ItemShovelBase(material.getMaterial(), material.name() + "_shovel", material.getMaterial().getHarvestLevel(), material.getMaterial().getMaxUses(), material.getRepairIngredient()).setCreativeTab(MysticalWorld.tab).setMaxStackSize(1)));
        event.addItem(material.setSword(new ItemSwordBase(material.getMaterial(), material.name() + "_sword", material.getMaterial().getMaxUses(), material.getRepairIngredient()).setCreativeTab(MysticalWorld.tab).setMaxStackSize(1)));
      }
    }
  }
}
