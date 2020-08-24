package epicsquid.mysticalworld.modifiers;

import epicsquid.mysticalworld.MysticalWorld;
import net.minecraft.entity.ai.attributes.AbstractAttributeMap;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid= MysticalWorld.MODID)
public class PlayerModifierRegistry {
  private static List<IAttribute> attributes = new ArrayList<>();

  public static IAttribute registerAttribute(IAttribute attribute) {
    attributes.add(attribute);
    return attribute;
  }

  @SubscribeEvent
  public static void onEntityConstructed(EntityEvent.EntityConstructing event) {
    if (event.getEntity() instanceof EntityPlayer) {
      EntityPlayer player = (EntityPlayer) event.getEntity();

      AbstractAttributeMap map = player.getAttributeMap();

      for (IAttribute attrib : attributes) {
        if (map.getAttributeInstanceByName(attrib.getName()) == null) {
          map.registerAttribute(attrib);
        }
      }
    }
  }
}
