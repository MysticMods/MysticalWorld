package mysticmods.mysticalworld.items;

import com.google.common.collect.Multimap;
import mysticmods.mysticalworld.MysticalWorld;
import mysticmods.mysticalworld.config.ConfigManager;
import mysticmods.mysticalworld.entity.model.ModelHolder;
import mysticmods.mysticalworld.entity.model.armor.BeetleArmorModel;
import mysticmods.mysticalworld.init.ModMaterials;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.LazyValue;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.DistExecutor;
import noobanidus.libs.noobutil.material.MaterialType;

import javax.annotation.Nullable;
import java.util.Map;

import net.minecraft.item.Item.Properties;

public class BeetleArmorItem extends ModifiedArmorItem {
  private final LazyValue<BipedModel<?>> model;

  public BeetleArmorItem(Properties builder, EquipmentSlotType slot) {
    super(ModMaterials.CARAPACE.getArmorMaterial(), slot, builder);

    this.model = DistExecutor.unsafeRunForDist(() -> () -> new LazyValue<>(() -> this.provideArmorModelForSlot(slot)), () -> () -> null);
  }

  @Override
  public Map<Attribute, AttributeModifier> getModifiers() {
    return super.getModifiers();
  }

  @Override
  public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlotType equipmentSlot) {
    Multimap<Attribute, AttributeModifier> map = super.getDefaultAttributeModifiers(equipmentSlot);

    if (equipmentSlot == this.slot && ConfigManager.HAT_CONFIG.getMaskBonusDamage() != -1) {
      map.put(Attributes.ATTACK_DAMAGE, this.getOrCreateModifier(Attributes.ATTACK_DAMAGE, () -> new AttributeModifier(MaterialType.ARMOR_MODIFIERS[slot.getIndex()], "Beetle Mask Damage Bonus", ConfigManager.HAT_CONFIG.getMaskBonusDamage(), AttributeModifier.Operation.MULTIPLY_TOTAL)));
    }

    return map;
  }

  @Nullable
  @Override
  public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
    return MysticalWorld.MODID + ":textures/models/armor/beetle_armor.png";
  }

  @SuppressWarnings("unchecked")
  @Nullable
  @Override
  @OnlyIn(Dist.CLIENT)
  public <A extends BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, A _default) {
    return (A) model.get();
  }

  @OnlyIn(Dist.CLIENT)
  public BipedModel<?> provideArmorModelForSlot(EquipmentSlotType slot) {
    return new BeetleArmorModel(slot);
  }
}
