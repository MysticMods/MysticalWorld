package epicsquid.mysticalworld.item;

import epicsquid.mysticallib.model.IModeledObject;
import epicsquid.mysticallib.util.Util;
import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.entity.model.armor.ModelBeetleMask;
import epicsquid.mysticalworld.init.ModItems;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.CriticalHitEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

@SuppressWarnings("NullableProblems")
@Mod.EventBusSubscriber(modid = MysticalWorld.MODID)
public class ItemBeetleMask extends ItemArmor implements IModeledObject {
  public ItemBeetleMask(ArmorMaterial materialIn, String name) {
    super(materialIn, 0, EntityEquipmentSlot.HEAD);
    setTranslationKey(name);
    setRegistryName(new ResourceLocation(MysticalWorld.MODID, name));
    setMaxDamage(399);
    setCreativeTab(MysticalWorld.tab);
  }

  @SubscribeEvent
  public static void onAttackEntity(AttackEntityEvent event) {
    EntityPlayer player = event.getEntityPlayer();
    if (player.world.isRemote) {
      return;
    }
    ItemStack mask = player.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
    if (mask.getItem() == ModItems.beetle_mask) {
      if (Util.rand.nextInt(30) == 0) {
        player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 160, 1, false, false));
      }
    }
  }

  @SubscribeEvent
  public static void onCriticalHit(CriticalHitEvent event) {
    EntityPlayer player = event.getEntityPlayer();
    if (player.world.isRemote) {
      return;
    }
    ItemStack mask = player.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
    if (mask.getItem() == ModItems.beetle_mask && player.getActivePotionEffect(MobEffects.STRENGTH) != null) {
      event.setResult(Event.Result.ALLOW);
      mask.damageItem(1, player);
    }
  }

  @Override
  public EnumRarity getRarity(ItemStack stack) {
    return EnumRarity.RARE;
  }

  @Override
  public void initModel() {
    ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "handler"));
  }

  @Nullable
  @Override
  public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
    return MysticalWorld.MODID + ":textures/model/armor/beetle_mask.png";
  }

  @Nullable
  @Override
  @SideOnly(Side.CLIENT)
  public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped _default) {
    return ModelBeetleMask.instance;
  }
}
