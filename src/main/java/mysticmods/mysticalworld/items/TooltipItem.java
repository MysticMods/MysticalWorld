package mysticmods.mysticalworld.items;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.*;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

import net.minecraft.item.Item.Properties;

public class TooltipItem extends Item {
  private String translationKey;


  public TooltipItem(Properties properties, String translationKey) {
    super(properties);
    this.translationKey = translationKey;
  }

  @Override
  @OnlyIn(Dist.CLIENT)
  public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
    super.appendHoverText(stack, worldIn, tooltip, flagIn);

    tooltip.add(new StringTextComponent(""));
    tooltip.add(new TranslationTextComponent(translationKey).setStyle(Style.EMPTY.withColor(Color.fromLegacyFormat(TextFormatting.YELLOW))));
  }
}
