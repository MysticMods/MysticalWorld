package epicsquid.mysticalworld.events;

import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

import java.util.List;

@OnlyIn(Dist.CLIENT)
public class TooltipHandler {
  public static void onTooltip (ItemTooltipEvent event) {
    ItemStack stack = event.getItemStack();
    Item item = stack.getItem();
    if (item.isFood()) {
      List<ITextComponent> tooltip = event.getToolTip();
      Food food = item.getFood();
      if (food != null) {
        tooltip.add(new StringTextComponent("Healing: " + food.getHealing()));
        tooltip.add(new StringTextComponent(String.format("Saturation: %.1f", food.getSaturation())));
      }
    }
  }
}
