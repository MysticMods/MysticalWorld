package mysticmods.mysticalworld.items;

import mysticmods.mysticalworld.MWTags;
import mysticmods.mysticalworld.config.ConfigManager;
import mysticmods.mysticalworld.entity.ClamEntity;
import mysticmods.mysticalworld.init.ModEntities;
import mysticmods.mysticalworld.init.ModItems;
import mysticmods.mysticalworld.init.ModSounds;
import mysticmods.mysticalworld.loot.Serendipity;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.*;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import noobanidus.libs.noobutil.type.LazySupplier;
import mysticmods.mysticalworld.init.ModModifiers;

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

  public ActionResultType interactLivingEntity(ItemStack stack, PlayerEntity playerIn, LivingEntity entity, Hand hand) {
    if (entity.getType() != ModEntities.CLAM.get()) {
      return ActionResultType.FAIL;
    }

    if (entity.level.isClientSide) {
      return ActionResultType.PASS;
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
        playerIn.level.playSound(null, entity.blockPosition(), ModSounds.PEARLEPORTER_USE.get(), SoundCategory.PLAYERS, 1f, 0.2f);
      }
    } else {
      int diff = ConfigManager.CLAM_CONFIG.getMaxAge() - clam.getEntityData().get(ClamEntity.age) / 20;
      playerIn.displayClientMessage(new TranslationTextComponent("mysticalworld.item.pearleporter_immature", diff).setStyle(Style.EMPTY.withColor(TextFormatting.DARK_PURPLE)), true);
    }

    return ActionResultType.SUCCESS;
  }

  @Override
  @OnlyIn(Dist.CLIENT)
  public void appendHoverText(ItemStack pStack, @Nullable World pLevel, List<ITextComponent> pTooltip, ITooltipFlag pFlag) {
    super.appendHoverText(pStack, pLevel, pTooltip, pFlag);

    pTooltip.add(new StringTextComponent(""));
    pTooltip.add(new TranslationTextComponent("mysticalworld.item.pearleporter").setStyle(Style.EMPTY.withBold(true)));
  }
}
