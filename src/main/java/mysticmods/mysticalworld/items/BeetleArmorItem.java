package mysticmods.mysticalworld.items;

import com.google.common.collect.Multimap;
import mysticmods.mysticalworld.MysticalWorld;
import mysticmods.mysticalworld.client.model.ModelHolder;
import mysticmods.mysticalworld.client.model.armor.BeetleArmorModel;
import mysticmods.mysticalworld.config.ConfigManager;
import mysticmods.mysticalworld.init.ModMaterials;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import noobanidus.libs.noobutil.material.MaterialType;

import javax.annotation.Nullable;
import java.util.Map;

public class BeetleArmorItem extends ModifiedArmorItem {
  public BeetleArmorItem(Properties builder, EquipmentSlotType slot) {
    super(ModMaterials.CARAPACE.getArmorMaterial(), slot, builder);
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
    return (A) new BeetleArmorModel(slot);
  }
}
