package mysticmods.mysticalworld.items;

import mysticmods.mysticalworld.MWTags;
import mysticmods.mysticalworld.config.ConfigManager;
import mysticmods.mysticalworld.entity.ClamEntity;
import mysticmods.mysticalworld.init.ModEntities;
import mysticmods.mysticalworld.init.ModItems;
import mysticmods.mysticalworld.init.ModModifiers;
import mysticmods.mysticalworld.init.ModSounds;
import mysticmods.mysticalworld.loot.Serendipity;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.text.*;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import noobanidus.libs.noobutil.type.LazySupplier;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

public class PearleporterItem extends Item {
  public static LazySupplier<Ingredient> PEARL_INGREDIENT = new LazySupplier<>(() -> Ingredient.of(MWTags.Items.PEARL_GEM));

  public PearleporterItem(Properties properties) {
    super(properties);
  }

  @Override
  public boolean isValidRepairItem(ItemStack pToRepair, ItemStack pRepair) {
    return pToRepair.getItem() == this && PEARL_INGREDIENT.get().test(pRepair);
  }

  @Override
  public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
    return enchantment == Enchantments.FISHING_LUCK || enchantment == Enchantments.UNBREAKING;
  }

  @Override
  public boolean isEnchantable(ItemStack pStack) {
    return true;
  }

  @Override
  public int getEnchantmentValue() {
    return 32;
  }

  @Override
  public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
    Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(book);
    for (Enchantment enchantment : map.keySet()) {
      if (enchantment != Enchantments.FISHING_LUCK && enchantment != Enchantments.UNBREAKING) {
        return false;
      }
    }
    return super.isBookEnchantable(stack, book);
  }

  public InteractionResult interactLivingEntity(ItemStack stack, Player playerIn, LivingEntity entity, InteractionHand hand) {
    if (entity.getType() != ModEntities.CLAM.get()) {
      return InteractionResult.FAIL;
    }

    if (entity.level.isClientSide) {
      return InteractionResult.PASS;
    }

    ClamEntity clam = (ClamEntity) entity;
    if (clam.getEntityData().get(ClamEntity.age) >= ConfigManager.CLAM_CONFIG.getMaxAge()) {
      int luck = EnchantmentHelper.getEnchantmentLevel(Enchantments.FISHING_LUCK, playerIn) + (int) playerIn.getLuck();
      int count = 1 + Serendipity.calculateAdditional(playerIn.getAttributeValue(ModModifiers.SERENDIPITY.get())) + (luck / 2) + random.nextInt(1 + luck);
      ItemStack result;
      if (clam.getEntityData().get(ClamEntity.isEnder)) {
        result = new ItemStack(Items.ENDER_PEARL, count);
      } else {
        result = new ItemStack(ModItems.PEARL_GEM.get(), count);
      }
      clam.getEntityData().set(ClamEntity.age, random.nextInt(ConfigManager.CLAM_CONFIG.getInitialAge()));
      ItemEntity ent = entity.spawnAtLocation(result, 1.0F);
      if (ent != null) {
        ent.setDeltaMovement(ent.getDeltaMovement().add((random.nextFloat() - random.nextFloat()) * 0.1F, random.nextFloat() * 0.05F, (random.nextFloat() - random.nextFloat()) * 0.1F));
        if (!playerIn.isCreative()) {
          stack.hurtAndBreak(1, playerIn, e -> e.broadcastBreakEvent(hand));
        }
        playerIn.level.playSound(null, entity.blockPosition(), ModSounds.PEARLEPORTER_USE.get(), SoundSource.PLAYERS, 1f, 0.2f);
      }
    } else {
      int diff = ConfigManager.CLAM_CONFIG.getMaxAge() - clam.getEntityData().get(ClamEntity.age) / 20;
      playerIn.displayClientMessage(new TranslatableComponent("mysticalworld.item.pearleporter_immature", diff).setStyle(Style.EMPTY.withColor(ChatFormatting.DARK_PURPLE)), true);
    }

    return InteractionResult.SUCCESS;
  }

  @Override
  @OnlyIn(Dist.CLIENT)
  public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
    super.appendHoverText(pStack, pLevel, pTooltip, pFlag);

    pTooltip.add(new TextComponent(""));
    pTooltip.add(new TranslatableComponent("mysticalworld.item.pearleporter").setStyle(Style.EMPTY.withBold(true)));
  }
}
