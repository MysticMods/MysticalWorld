package mysticmods.mysticalworld.items;

import com.google.common.collect.Multimap;
import mysticmods.mysticalworld.MysticalWorld;
import mysticmods.mysticalworld.client.model.ModelHolder;
import mysticmods.mysticalworld.client.model.armor.BeetleArmorModel;
import mysticmods.mysticalworld.config.ConfigManager;
import mysticmods.mysticalworld.init.ModMaterials;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import noobanidus.libs.noobutil.material.MaterialType;

import javax.annotation.Nullable;
import java.util.Map;

import net.minecraft.world.item.Item.Properties;

public class BeetleArmorItem extends ModifiedArmorItem {
  public BeetleArmorItem(Properties builder, EquipmentSlot slot) {
    super(ModMaterials.CARAPACE.getArmorMaterial(), slot, builder);
  }

  @Nullable
  @Override
  public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
    return MysticalWorld.MODID + ":textures/models/armor/beetle_armor.png";
  }

  @SuppressWarnings("unchecked")
  @Nullable
  @Override
  @OnlyIn(Dist.CLIENT)
  public <A extends HumanoidModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlot armorSlot, A _default) {
    return (A) ModelHolder.modelForSlot(slot);
  }
}
