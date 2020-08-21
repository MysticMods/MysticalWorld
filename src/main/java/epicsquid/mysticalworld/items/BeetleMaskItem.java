package epicsquid.mysticalworld.items;

import com.google.common.collect.Multimap;
import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.config.ConfigManager;
import epicsquid.mysticalworld.entity.model.ModelHolder;
import epicsquid.mysticalworld.init.ModMaterials;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import noobanidus.libs.noobutil.material.MaterialType;

import javax.annotation.Nullable;
import java.util.Map;

public class BeetleMaskItem extends ModifiedArmorItem {
  public BeetleMaskItem(Properties builder) {
    super(ModMaterials.CARAPACE.getArmorMaterial(), EquipmentSlotType.HEAD, builder);
  }

  @Override
  public Map<IAttribute, AttributeModifier> getModifiers() {
    return super.getModifiers();
  }

  @Override
  public Multimap<String, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot) {
    Multimap<String, AttributeModifier> map = super.getAttributeModifiers(equipmentSlot);

    if (equipmentSlot == EquipmentSlotType.HEAD && ConfigManager.HAT_CONFIG.getMaskBonusDamage() != -1) {
      map.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), this.getOrCreateModifier(SharedMonsterAttributes.ATTACK_DAMAGE, () -> new AttributeModifier(MaterialType.ARMOR_MODIFIERS[slot.getIndex()], "Beetle Mask Damage Bonus", ConfigManager.HAT_CONFIG.getMaskBonusDamage(), AttributeModifier.Operation.MULTIPLY_TOTAL)));
    }

    return map;
  }

  @Nullable
  @Override
  public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
    return MysticalWorld.MODID + ":textures/models/armor/beetle_mask.png";
  }

  @SuppressWarnings("unchecked")
  @Nullable
  @Override
  @OnlyIn(Dist.CLIENT)
  public <A extends BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, A _default) {
    return (A) ModelHolder.beetleMaskModel;
  }


}
