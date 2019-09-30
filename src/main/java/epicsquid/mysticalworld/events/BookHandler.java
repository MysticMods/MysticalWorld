package epicsquid.mysticalworld.events;

import epicsquid.mysticallib.world.books.BookRegistry;
import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.config.ConfigManager;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import vazkii.patchouli.api.PatchouliAPI;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber(modid = MysticalWorld.MODID)
public class BookHandler {
  public static final String BOOK_IDENTIFIER = "mysticalworld";

  @SubscribeEvent
  public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
    if (ConfigManager.GiveBook) {
      if (event.player.isServerWorld()) {
        BookRegistry registry = BookRegistry.getBookRegistry(BOOK_IDENTIFIER, event.player);
        if (!registry.hasBook) {
          ItemStack stack = PatchouliAPI.instance.getBookStack("mysticalworld:world_guide");
          event.player.addItemStackToInventory(stack);
          registry.hasBook = true;
          registry.markDirty();
          event.player.world.getMapStorage().saveAllData();
        }
      }
    }
  }
}
