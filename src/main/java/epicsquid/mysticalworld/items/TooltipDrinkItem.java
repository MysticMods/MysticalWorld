package epicsquid.mysticalworld.items;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.*;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import noobanidus.libs.noobutil.item.BaseItems;

import javax.annotation.Nullable;
import java.util.List;

public class TooltipDrinkItem extends BaseItems.DrinkItem {
  private String translationKey;


  public TooltipDrinkItem(Properties properties, String translationKey) {
    super(properties);
    this.translationKey = translationKey;
  }

  @Override
  @OnlyIn(Dist.CLIENT)
  public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
    super.addInformation(stack, worldIn, tooltip, flagIn);

    tooltip.add(new StringTextComponent(""));
    tooltip.add(new TranslationTextComponent(translationKey).setStyle(new Style().setColor(TextFormatting.YELLOW)));
  }
}
