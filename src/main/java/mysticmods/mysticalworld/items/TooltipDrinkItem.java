package mysticmods.mysticalworld.items;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import noobanidus.libs.noobutil.item.BaseItems;

import javax.annotation.Nullable;
import java.util.List;

public class TooltipDrinkItem extends BaseItems.DrinkItem {
  private final String translationKey;

  public TooltipDrinkItem(Properties properties, String translationKey) {
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
