package mysticmods.mysticalworld.mixins;

import mysticmods.mysticalworld.init.ModModifiers;
import mysticmods.mysticalworld.loot.Serendipity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Random;

@Mixin(ApplyBonusCount.class)
public class MixinApplyBonusCount {
  @Unique
  private int serendipityValue = 0;

  @Inject(method = "run", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/world/item/enchantment/EnchantmentHelper;getItemEnchantmentLevel(Lnet/minecraft/world/item/enchantment/Enchantment;Lnet/minecraft/world/item/ItemStack;)I"), locals = LocalCapture.CAPTURE_FAILHARD)
  protected void MysticalWorldApplySerendipity(ItemStack stack, LootContext context, CallbackInfoReturnable<ItemStack> cir, ItemStack stack2, int enchantmentLevel) {
    Enchantment enchantment = ((ApplyBonusCount) (Object) this).enchantment;
    if (enchantment == Enchantments.BLOCK_FORTUNE) {
      Entity entity = context.getParamOrNull(LootContextParams.THIS_ENTITY);
      if (entity instanceof Player) {
        double serendipity = ((Player) entity).getAttributeValue(ModModifiers.SERENDIPITY.get());
        this.serendipityValue = Serendipity.calculateAdditional(serendipity);
      }
    }
  }

  @Redirect(method = "run", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/storage/loot/functions/ApplyBonusCount$Formula;calculateNewCount(Ljava/util/Random;II)I"))
  protected int redirectCount(ApplyBonusCount.Formula iFormula, Random random, int i, int i1) {
    if (this.serendipityValue != -1) {
      return iFormula.calculateNewCount(random, i, i1 + this.serendipityValue);
    } else {
      return iFormula.calculateNewCount(random, i, i1);
    }
  }
}
