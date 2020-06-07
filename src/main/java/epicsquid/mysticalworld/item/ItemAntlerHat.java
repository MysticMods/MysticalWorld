package epicsquid.mysticalworld.item;

import epicsquid.mysticallib.model.IModeledObject;
import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.entity.model.armor.ModelDeerHat;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class ItemAntlerHat extends ItemArmor implements IModeledObject {
  public ItemAntlerHat(ArmorMaterial materialIn, String name) {
    super(materialIn, 0, EntityEquipmentSlot.HEAD);
    setTranslationKey(name);
    setRegistryName(new ResourceLocation(MysticalWorld.MODID, name));
    setMaxDamage(350);
    setCreativeTab(MysticalWorld.tab);
  }

  @Override
  public void initModel() {
    ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "handler"));
  }

  @Nullable
  @Override
  public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
    return MysticalWorld.MODID + ":textures/model/armor/deer_hat.png";
  }

  @Nullable
  @Override
  @SideOnly(Side.CLIENT)
  public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped _default) {
    return ModelDeerHat.instance;
  }
}
