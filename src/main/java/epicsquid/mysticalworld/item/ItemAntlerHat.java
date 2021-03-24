package epicsquid.mysticalworld.item;

import com.google.common.collect.Multimap;
import epicsquid.mysticallib.model.IModeledObject;
import epicsquid.mysticallib.util.Util;
import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.config.ConfigManager;
import epicsquid.mysticalworld.entity.model.armor.ModelAntlerHat;
import epicsquid.mysticalworld.init.ModItems;
import epicsquid.mysticalworld.materials.Materials;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreIngredient;

import javax.annotation.Nullable;

@SuppressWarnings("deprecation")
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

  @Override
  public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot slot, ItemStack stack) {
    Multimap<String, AttributeModifier> map = super.getAttributeModifiers(slot, stack);

    if (slot == EntityEquipmentSlot.HEAD && ConfigManager.hats.antlerHealthBonus != -1) {
      map.put(SharedMonsterAttributes.MAX_HEALTH.getName(), new AttributeModifier(Materials.ARMOR_MODIFIERS[slot.getIndex()], "Healthiness", ConfigManager.hats.antlerHealthBonus, 0));
    }

    return map;
  }

  @Override
  public EnumRarity getRarity(ItemStack stack) {
    return EnumRarity.RARE;
  }

  public static AxisAlignedBB BOX = new AxisAlignedBB(-8, -8, -8, 9, 9, 9);

  @Override
  public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
    if (ConfigManager.hats.antlerFrequency == -1) {
      return;
    }

    if (!world.isRemote && player.getHealth() < (ConfigManager.hats.antlerThreshold == -1 ? player.getMaxHealth() : player.getMaxHealth() - ConfigManager.hats.antlerThreshold) && Util.rand.nextInt(ConfigManager.hats.antlerFrequency) == 0) {
      if (player.getActivePotionEffect(MobEffects.REGENERATION) != null) {
        return;
      }

      player.heal(ConfigManager.hats.antlerHealing);
      player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, ConfigManager.hats.antlerRegenDuration, ConfigManager.hats.antlerRegenAmplifier, false, false));

      ItemStack head = player.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
      if (ConfigManager.hats.antlerDamage != -1) {
        head.damageItem(ConfigManager.hats.antlerDamage, player);
      }
    }
  }

  @Nullable
  @Override
  public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
    return MysticalWorld.MODID + ":textures/model/armor/antler_hat.png";
  }

  @Nullable
  @Override
  @SideOnly(Side.CLIENT)
  public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped _default) {
    return ModelAntlerHat.instance;
  }

  @Override
  public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
    return toRepair.getItem() == this && repair.getItem() == ModItems.antlers;
  }
}
