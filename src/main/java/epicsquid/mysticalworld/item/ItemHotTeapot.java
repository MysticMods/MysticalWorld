package epicsquid.mysticalworld.item;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import epicsquid.mysticallib.item.ItemBase;
import epicsquid.mysticalworld.init.ModItems;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;

public class ItemHotTeapot extends ItemBase {

  public ItemHotTeapot(@Nonnull String name) {
    super(name);
    setContainerItem(this);
    setMaxStackSize(1);
  }

  @Override
  public boolean hasContainerItem(@Nonnull ItemStack stack) {
    return true;
  }

  @Override
  @Nonnull
  public ItemStack getContainerItem(@Nonnull ItemStack inStack) {
    ItemStack stack = inStack.copy();
    stack.setItemDamage(Math.max(0, stack.getItemDamage() - 1));
    if (stack.getItemDamage() == 0) {
      return new ItemStack(ModItems.teapot);
    }
    return stack;
  }

  @Override
  public boolean showDurabilityBar(ItemStack stack){
    return stack.getMetadata() < 4;
  }

  @Override
  public double getDurabilityForDisplay(ItemStack stack){
    return 1.0 - (double)stack.getMetadata() / 4.0;
  }

  @Override
  public void addInformation(@Nonnull ItemStack stack, @Nullable World player, @Nonnull List<String> tooltip, @Nullable ITooltipFlag advanced){
    tooltip.add(I18n.format("mysticalworld.teapot.tooltip.boiling"));
  }

  @Override
  public void getSubItems(@Nonnull CreativeTabs tab, @Nonnull NonNullList<ItemStack> stacks){
    if (tab == this.getCreativeTab()){
      stacks.add(new ItemStack(this,1,4));
    }
  }
}
