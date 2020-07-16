package epicsquid.mysticalworld.item;

import com.google.common.collect.Multimap;
import epicsquid.mysticallib.model.IModeledObject;
import epicsquid.mysticallib.util.Util;
import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.config.ConfigManager;
import epicsquid.mysticalworld.entity.EntitySpiritDeer;
import epicsquid.mysticalworld.entity.model.armor.ModelAntlerHat;
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
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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
  public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot slot, ItemStack stack)   {
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

      BlockPos playerPos = player.getPosition();
      if (!world.getEntitiesWithinAABB(EntitySpiritDeer.class, BOX.offset(playerPos)).isEmpty()) {
        return;
      }

      BlockPos pos;

      int tries = 100;
      while (true) {
        tries--;
        if (tries <= 0) {
          return;
        }
        pos = playerPos.add(Util.rand.nextInt(8) - 8, 0, Util.rand.nextInt(8) - 8);
        if (!world.isAirBlock(pos)) {
          continue;
        }
        if (player.getDistanceSq(pos) < 9) {
          continue;
        }

        break;
      }
      EntitySpiritDeer spiritDeer = new EntitySpiritDeer(world);
      spiritDeer.setAttackTarget(player);
      spiritDeer.setDropItemsWhenDead(false);
      spiritDeer.setPositionAndRotation(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, player.rotationYaw, player.rotationPitch);
      spiritDeer.noClip = true;
      world.spawnEntity(spiritDeer);
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
}
