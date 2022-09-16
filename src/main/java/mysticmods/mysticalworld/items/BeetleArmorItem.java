package mysticmods.mysticalworld.items;

import mysticmods.mysticalworld.MysticalWorld;
import mysticmods.mysticalworld.client.model.armor.ArmorModel;
import mysticmods.mysticalworld.init.ModMaterials;
import mysticmods.mysticalworld.items.modified.ModifiedArmorItem;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Objects;
import java.util.function.Consumer;

public class BeetleArmorItem extends ModifiedArmorItem {
  public BeetleArmorItem(Properties builder, EquipmentSlot slot) {
    super(ModMaterials.CARAPACE.getArmorMaterial(), slot, builder);
  }

  @Nullable
  @Override
  public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
    return MysticalWorld.MODID + ":textures/models/armor/beetle_armor.png";
  }

  @Override
  public void initializeClient(Consumer<IClientItemExtensions> consumer) {
    consumer.accept(new IClientItemExtensions() {

      @Override
      public @NotNull HumanoidModel<?> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, HumanoidModel<?> original) {
        return Objects.requireNonNull(ArmorModel.getModel(itemStack));
      }
    });
  }
}
