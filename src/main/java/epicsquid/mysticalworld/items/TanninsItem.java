package epicsquid.mysticalworld.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class TanninsItem extends Item {
  public TanninsItem(Properties properties) {
    super(properties);
  }

  @Override
  public ItemStack getContainerItem(ItemStack itemStack) {
    return new ItemStack(Items.GLASS_BOTTLE);
  }

  @Override
  public boolean hasContainerItem(ItemStack stack) {
    return true;
  }
}
