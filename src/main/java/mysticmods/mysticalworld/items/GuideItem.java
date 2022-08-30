package mysticmods.mysticalworld.items;

import mysticmods.mysticalworld.MysticalWorld;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import vazkii.patchouli.api.PatchouliAPI;
import vazkii.patchouli.common.base.PatchouliSounds;
import vazkii.patchouli.common.book.Book;
import vazkii.patchouli.common.book.BookRegistry;

import java.util.List;

@SuppressWarnings("NullableProblems")
public class GuideItem extends Item {
  private static final ResourceLocation ENCYCLOPEDIA = new ResourceLocation(MysticalWorld.MODID, "world_guide");

  public GuideItem(Item.Properties properties) {
    super(properties);
  }

  public static Book getBook() {
    return BookRegistry.INSTANCE.books.get(ENCYCLOPEDIA);
  }

  @Override
  public Component getName(ItemStack stack) {
    Book book = getBook();
    return (book != null ? Component.translatable(book.name) : super.getName(stack));
  }

  @Override
  public void appendHoverText(ItemStack stack, Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
    super.appendHoverText(stack, worldIn, tooltip, flagIn);
    Book book = getBook();
    if (book != null && !book.getContents().isErrored()) {
      tooltip.add(book.getSubtitle().withStyle(ChatFormatting.GRAY));
    }
  }

  @Override
  public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
    ItemStack stack = playerIn.getItemInHand(handIn);
    Book book = getBook();
    if (book == null) {
      return InteractionResultHolder.fail(stack);
    } else {
      if (playerIn instanceof ServerPlayer) {
        PatchouliAPI.get().openBookGUI((ServerPlayer)playerIn, book.id);
        SoundEvent sfx = PatchouliSounds.getSound(book.openSound, PatchouliSounds.BOOK_OPEN);
        playerIn.playSound(sfx, 1.0F, (float)(0.7D + Math.random() * 0.4D));
      }

      return InteractionResultHolder.success(stack);
    }
  }
}
