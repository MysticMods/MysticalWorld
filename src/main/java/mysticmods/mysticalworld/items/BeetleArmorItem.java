package mysticmods.mysticalworld.items;

import mysticmods.mysticalworld.MysticalWorld;
import mysticmods.mysticalworld.init.mod.ModMaterials;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nullable;

public class BeetleArmorItem extends ModifiedArmorItem {
  public BeetleArmorItem(Properties builder, EquipmentSlot slot) {
    super(ModMaterials.CARAPACE.getArmorMaterial(), slot, builder);
  }

  @Nullable
  @Override
  public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
    return MysticalWorld.MODID + ":textures/models/armor/beetle_armor.png";
  }

  // TODO: Handle how custom armor models are handled
/*  @SuppressWarnings("unchecked")
  @Nullable
  @Override
  @OnlyIn(Dist.CLIENT)
  public <A extends HumanoidModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlot armorSlot, A _default) {
    return (A) ModelHolder.modelForSlot(slot);
  }*/
}
