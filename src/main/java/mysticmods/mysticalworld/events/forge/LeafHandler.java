package mysticmods.mysticalworld.events.forge;

import mysticmods.mysticalworld.MysticalWorld;
import mysticmods.mysticalworld.config.ConfigManager;
import mysticmods.mysticalworld.init.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MysticalWorld.MODID)
public class LeafHandler {
  // TODO: Should be a global loot modifier?

  @SubscribeEvent
  public static void onBlockDrops(BlockEvent.BreakEvent event) {
    if (ConfigManager.SILKWORM_CONFIG.getLeafDropsEnabled() && !event.getWorld().isClientSide()) {
      if (event.getState().is(BlockTags.LEAVES)) {
        if (event.getWorld().getRandom().nextInt(ConfigManager.SILKWORM_CONFIG.getLeafDropChance()) == 0) {
          BlockPos pos = event.getPos();
          event.getWorld().addFreshEntity(new ItemEntity((Level) event.getWorld(), pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, new ItemStack(ModItems.SILKWORM_EGG.get())));
        }
      }
    }
  }
}
