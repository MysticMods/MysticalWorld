package mysticmods.mysticalworld.mixins;

import mysticmods.mysticalworld.init.ModModifiers;
import mysticmods.mysticalworld.loot.Serendipity;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameters;
import net.minecraft.loot.functions.ApplyBonus;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Random;

@Mixin(ApplyBonus.class)
public class MixinApplyBonus {
  @Unique
  private int serendipityValue = 0;

  @Inject(method="doApply", at=@At(value="INVOKE_ASSIGN", target="Lnet/minecraft/enchantment/EnchantmentHelper;getEnchantmentLevel(Lnet/minecraft/enchantment/Enchantment;Lnet/minecraft/item/ItemStack;)I"), locals = LocalCapture.CAPTURE_FAILHARD)
  protected void applySerendipity(ItemStack stack, LootContext context, CallbackInfoReturnable<ItemStack> cir, ItemStack stack2, int enchantmentLevel) {
    Enchantment enchantment = ((ApplyBonus) (Object) this).enchantment;
    if (enchantment == Enchantments.FORTUNE) {
      Entity entity = context.get(LootParameters.THIS_ENTITY);
      if (entity instanceof PlayerEntity) {
        double serendipity = ((PlayerEntity) entity).getAttributeValue(ModModifiers.SERENDIPITY.get());
        this.serendipityValue = Serendipity.calculateAdditional(serendipity);
      }
    }
  }

  @Redirect(method="doApply", at=@At(value="INVOKE", target="Lnet/minecraft/loot/functions/ApplyBonus$IFormula;func_216204_a(Ljava/util/Random;II)I"))
  protected int redirectCount(ApplyBonus.IFormula iFormula, Random random, int i, int i1)
  {
    if (this.serendipityValue != -1) {
      return iFormula.func_216204_a(random, i, i1 + this.serendipityValue);
    } else {
      return iFormula.func_216204_a(random, i, i1);
    }
  }
}
