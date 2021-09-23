package mysticmods.mysticalworld.items;

import com.google.common.collect.Multimap;
import mysticmods.mysticalworld.MysticalWorld;
import mysticmods.mysticalworld.config.ConfigManager;
import mysticmods.mysticalworld.entity.model.ModelHolder;
import mysticmods.mysticalworld.init.ModMaterials;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import noobanidus.libs.noobutil.material.MaterialType;

import javax.annotation.Nullable;
import java.util.Map;

import net.minecraft.item.Item.Properties;

public class AntlerHatItem extends ModifiedArmorItem {
  public AntlerHatItem(Properties builder) {
    super(ModMaterials.ANTLER.getArmorMaterial(), EquipmentSlotType.HEAD, builder);
  }

  @Override
  public Map<Attribute, AttributeModifier> getModifiers() {
    return super.getModifiers();
  }

  @Override
  public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlotType equipmentSlot) {
    Multimap<Attribute, AttributeModifier> map = super.getDefaultAttributeModifiers(equipmentSlot);

    if (equipmentSlot == EquipmentSlotType.HEAD && ConfigManager.HAT_CONFIG.getAntlerHealthBonus() != -1) {
      map.put(Attributes.MAX_HEALTH, this.getOrCreateModifier(Attributes.MAX_HEALTH, () -> new AttributeModifier(MaterialType.ARMOR_MODIFIERS[slot.getIndex()], "Antler Health Boost", ConfigManager.HAT_CONFIG.getAntlerHealthBonus(), AttributeModifier.Operation.ADDITION)));
    }

    return map;
  }

  @Override
  public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
    if (ConfigManager.HAT_CONFIG.getAntlerFrequency() == -1) {
      return;
    }

    if (!world.isClientSide && player.getHealth() < (ConfigManager.HAT_CONFIG.getAntlerThreshold() == -1 ? player.getMaxHealth() : player.getMaxHealth() - ConfigManager.HAT_CONFIG.getAntlerThreshold()) && world.random.nextInt(ConfigManager.HAT_CONFIG.getAntlerFrequency()) == 0) {
      if (player.getEffect(Effects.REGENERATION) != null) {
        return;
      }
      player.heal(ConfigManager.HAT_CONFIG.getAntlerHealing());
      player.addEffect(new EffectInstance(Effects.REGENERATION, ConfigManager.HAT_CONFIG.getAntlerRegenDuration(), ConfigManager.HAT_CONFIG.getAntlerRegenAmplifier()));

      ItemStack head = player.getItemBySlot(EquipmentSlotType.HEAD);
      if (ConfigManager.HAT_CONFIG.getAntlerDamage() != -1) {
        head.hurtAndBreak(ConfigManager.HAT_CONFIG.getAntlerDamage(), player, (breaker) -> {
          breaker.broadcastBreakEvent(EquipmentSlotType.HEAD);
        });
      }
    }
  }

  @Nullable
  @Override
  public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
    return MysticalWorld.MODID + ":textures/models/armor/antler_hat.png";
  }

  @SuppressWarnings("unchecked")
  @Nullable
  @Override
  @OnlyIn(Dist.CLIENT)
  public <A extends BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, A _default) {
    return (A) ModelHolder.antlerHatModel;
  }
}
