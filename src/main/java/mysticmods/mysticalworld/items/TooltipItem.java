package mysticmods.mysticalworld.items;

import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.util.text.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

import net.minecraft.world.item.Item.Properties;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;

public class TooltipItem extends Item {
  private final String translationKey;


  public TooltipItem(Properties properties, String translationKey) {
    super(properties);
    this.translationKey = translationKey;
  }

  @Override
  @OnlyIn(Dist.CLIENT)
  public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
    super.appendHoverText(stack, worldIn, tooltip, flagIn);

    tooltip.add(new TextComponent(""));
    tooltip.add(new TranslatableComponent(translationKey).setStyle(Style.EMPTY.withColor(TextColor.fromLegacyFormat(ChatFormatting.YELLOW))));
  }
}
