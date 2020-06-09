package epicsquid.mysticalworld.item;

import epicsquid.mysticallib.model.IModeledObject;
import epicsquid.mysticallib.util.Util;
import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.entity.EntityDeer;
import epicsquid.mysticalworld.entity.EntitySpiritDeer;
import epicsquid.mysticalworld.entity.model.armor.ModelAntlerHat;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class ItemAntlerHat extends ItemArmor implements IModeledObject {
  public static DataParameter<Boolean> NO_GRAVITY = ObfuscationReflectionHelper.getPrivateValue(Entity.class, null, "field_189655_aD");

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
  public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
    if (player.getHealth() < player.getMaxHealth() && Util.rand.nextInt(80) == 0 && !world.isRemote) {
      BlockPos pos;
      BlockPos playerPos = player.getPosition();
      int tries = 100;
      while (true) {
        tries--;
        if (tries <= 0) {
          return;
        }
        pos = playerPos.add(Util.rand.nextInt(4) - 4, 0, Util.rand.nextInt(4) - 4);
        if (!world.isAirBlock(pos)) {
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
