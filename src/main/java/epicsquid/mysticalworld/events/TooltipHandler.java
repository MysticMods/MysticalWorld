/*package epicsquid.mysticalworld.events;

import epicsquid.mysticalworld.init.ModItems;
import epicsquid.mysticalworld.item.ItemCharm;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mod.EventBusSubscriber
public class TooltipHandler {
  public static Map<String, String> TOOLTIP_MAP = new HashMap<>();
  public static List<String> ENCHANT_LIST = null;

  @SideOnly(Side.CLIENT)
  public static List<String> getEnchantList () {
    if (ENCHANT_LIST == null) {
      ENCHANT_LIST = new ArrayList<>();
      for (Enchantment ench : ItemCharm.ALLOWED_ENCHANTS) {
        for (int i = 1; i < ench.getMaxLevel(); i++) {
          ENCHANT_LIST.add(ench.getTranslatedName(i));
        }
      }
    }

    return ENCHANT_LIST;
  }

  @SubscribeEvent
  @SideOnly(Side.CLIENT)
  public static void onTooltip (ItemTooltipEvent event) {
    ItemStack stack = event.getItemStack();
    if (stack.getItem() != ModItems.charm || !stack.isItemEnchanted()) {
      return;
    }

    List<String> tooltip = event.getToolTip();
    
    tooltip.removeIf(s -> getEnchantList().contains(s));


  }
}*/
