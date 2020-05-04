package epicsquid.mysticalworld.integration.roots;

import epicsquid.mysticallib.event.RegisterContentEvent;
import epicsquid.mysticallib.item.ItemKnifeBase;
import epicsquid.mysticallib.material.MaterialTypes;
import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.init.ModItems;
import net.minecraft.item.Item;

import javax.annotation.Nonnull;
import java.lang.reflect.Constructor;

public class Knives {
  public static boolean initKnives(@Nonnull RegisterContentEvent event) {
    Class<?> knife;

    try {
      knife = Class.forName("epicsquid.roots.item.ItemDruidKnife");
    } catch (ClassNotFoundException e) {
      return false;
    }

    Constructor<?> constructor;
    try {
      constructor = knife.getConstructor(String.class, Item.ToolMaterial.class);
    } catch (NoSuchMethodException e) {
      return false;
    }

    ItemKnifeBase amethyst_knife, copper_knife, silver_knife;

    try {
      amethyst_knife = (ItemKnifeBase) constructor.newInstance("amethyst_knife", MaterialTypes.material("mysticalworld:amethyst"));
    } catch (Exception e) {
      return false;
    }

    try {
      copper_knife = (ItemKnifeBase) constructor.newInstance("copper_knife", MaterialTypes.material("mysticalworld:copper"));
    } catch (Exception e) {
      return false;
    }

    try {
      silver_knife = (ItemKnifeBase) constructor.newInstance("silver_knife", MaterialTypes.material("mysticalworld:silver"));
    } catch (Exception e) {
      return false;
    }

    event.addItem(amethyst_knife.setModelCustom(true).setCreativeTab(MysticalWorld.tab));
    event.addItem(copper_knife.setModelCustom(true).setCreativeTab(MysticalWorld.tab));
    event.addItem(silver_knife.setModelCustom(true).setCreativeTab(MysticalWorld.tab));

    ModItems.amethyst_knife = amethyst_knife;
    ModItems.copper_knife = copper_knife;
    ModItems.silver_knife = silver_knife;

    return true;
  }
}
