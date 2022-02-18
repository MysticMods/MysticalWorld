package mysticmods.mysticalworld.items;

import com.google.common.collect.Multimap;
import mysticmods.mysticalworld.MysticalWorld;
import mysticmods.mysticalworld.config.ConfigManager;
import mysticmods.mysticalworld.client.model.ModelHolder;
import mysticmods.mysticalworld.init.ModMaterials;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import noobanidus.libs.noobutil.material.MaterialType;

import javax.annotation.Nullable;
import java.util.Map;

import net.minecraft.world.item.Item.Properties;

public class BeetleMaskItem extends ModifiedArmorItem {
  public BeetleMaskItem(Properties builder) {
    super(ModMaterials.CARAPACE.getArmorMaterial(), EquipmentSlot.HEAD, builder);
  }

  @Override
  public Map<Attribute, AttributeModifier> getModifiers() {
    return super.getModifiers();
  }

  @Override
  public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot equipmentSlot) {
    Multimap<Attribute, AttributeModifier> map = super.getDefaultAttributeModifiers(equipmentSlot);

    if (equipmentSlot == EquipmentSlot.HEAD && ConfigManager.HAT_CONFIG.getMaskBonusDamage() != -1) {
      map.put(Attributes.ATTACK_DAMAGE, this.getOrCreateModifier(Attributes.ATTACK_DAMAGE, () -> new AttributeModifier(MaterialType.ARMOR_MODIFIERS[slot.getIndex()], "Beetle Mask Damage Bonus", ConfigManager.HAT_CONFIG.getMaskBonusDamage(), AttributeModifier.Operation.MULTIPLY_TOTAL)));
    }

    return map;
  }

  @Nullable
  @Override
  public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
    return MysticalWorld.MODID + ":textures/models/armor/beetle_mask.png";
  }

  @SuppressWarnings("unchecked")
  @Nullable
  @Override
  @OnlyIn(Dist.CLIENT)
  public <A extends HumanoidModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlot armorSlot, A _default) {
    return (A) ModelHolder.beetleHelmet;
  }
}
