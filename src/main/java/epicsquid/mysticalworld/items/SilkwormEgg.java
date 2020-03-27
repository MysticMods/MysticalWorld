package epicsquid.mysticalworld.items;

import com.tterrag.registrate.util.LazySpawnEggItem;
import epicsquid.mysticalworld.config.ConfigManager;
import epicsquid.mysticalworld.entity.SilkwormEntity;
import epicsquid.mysticalworld.init.ModEntities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class SilkwormEgg extends LazySpawnEggItem<SilkwormEntity> {
  public SilkwormEgg(Properties builder) {
    super(ModEntities.SILKWORM, 0, 0, builder);
  }

  @Override
  public ActionResultType onItemUse(ItemUseContext context) {
    World world = context.getWorld();
    if (!world.isRemote()) {
      if (world.getRandom().nextInt(ConfigManager.SILKWORM_CONFIG.getSuccessChance()) == 0) {
        return super.onItemUse(context);
      } else {
        if (context.getPlayer() == null || !context.getPlayer().abilities.isCreativeMode) {
          context.getItem().shrink(1);
        }
        return ActionResultType.SUCCESS;
      }
    } else {
      return super.onItemUse(context);
    }
  }

  @Override
  public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
    ItemStack itemstack = playerIn.getHeldItem(handIn);
    if (!worldIn.isRemote()) {
      if (worldIn.getRandom().nextInt(ConfigManager.SILKWORM_CONFIG.getSuccessChance()) == 0) {
        return super.onItemRightClick(worldIn, playerIn, handIn);
      } else {
        if (!playerIn.abilities.isCreativeMode) {
          itemstack.shrink(1);
        }
        return new ActionResult<>(ActionResultType.SUCCESS, itemstack);
      }
    } else {
      return super.onItemRightClick(worldIn, playerIn, handIn);
    }
  }
}
