package epicsquid.mysticalworld.items;

import com.google.common.collect.Multimap;
import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.config.ConfigManager;
import epicsquid.mysticalworld.entity.SpiritDeerEntity;
import epicsquid.mysticalworld.entity.model.ModelHolder;
import epicsquid.mysticalworld.init.ModEntities;
import epicsquid.mysticalworld.init.ModMaterials;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import noobanidus.libs.noobutil.material.MaterialType;

import javax.annotation.Nullable;
import java.util.Map;

public class AntlerHatItem extends ModifiedArmorItem {
  public AntlerHatItem(Properties builder) {
    super(ModMaterials.ANTLER.getArmorMaterial(), EquipmentSlotType.HEAD, builder);
  }

  @Override
  public Map<IAttribute, AttributeModifier> getModifiers() {
    return super.getModifiers();
  }

  @Override
  public Multimap<String, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot) {
    Multimap<String, AttributeModifier> map = super.getAttributeModifiers(equipmentSlot);

    if (equipmentSlot == EquipmentSlotType.HEAD && ConfigManager.HAT_CONFIG.getAntlerHealthBonus() != -1) {
      map.put(SharedMonsterAttributes.MAX_HEALTH.getName(), this.getOrCreateModifier(SharedMonsterAttributes.MAX_HEALTH, () -> new AttributeModifier(MaterialType.ARMOR_MODIFIERS[slot.getIndex()], "Antler Health Boost", ConfigManager.HAT_CONFIG.getAntlerHealthBonus(), AttributeModifier.Operation.ADDITION)));
    }

    return map;
  }

  public static AxisAlignedBB BOX = new AxisAlignedBB(-8, -8, -8, 9, 9, 9);

  @Override
  public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
    if (ConfigManager.HAT_CONFIG.getAntlerFrequency() == -1) {
      return;
    }

    if (!world.isRemote && player.getHealth() < (ConfigManager.HAT_CONFIG.getAntlerThreshold() == -1 ? player.getMaxHealth() : player.getMaxHealth() - ConfigManager.HAT_CONFIG.getAntlerThreshold()) && world.rand.nextInt(ConfigManager.HAT_CONFIG.getAntlerFrequency()) == 0) {
      if (player.getActivePotionEffect(Effects.REGENERATION) != null) {
        return;
      }

      BlockPos playerPos = player.getPosition();
      if (!world.getEntitiesWithinAABB(SpiritDeerEntity.class, BOX.offset(playerPos)).isEmpty()) {
        return;
      }

      BlockPos pos;

      int tries = 100;
      while (true) {
        tries--;
        if (tries <= 0) {
          return;
        }
        pos = playerPos.add(world.rand.nextInt(8) - 4, 0, world.rand.nextInt(8) - 4);
        if (!world.isAirBlock(pos)) {
          continue;
        }
        if (player.getDistanceSq(pos.getX(), pos.getY(), pos.getZ()) < 9) {
          continue;
        }

        break;
      }
      SpiritDeerEntity spiritDeer = ModEntities.SPIRIT_DEER.get().create(world);
      if (spiritDeer != null) {
        spiritDeer.setAttackTarget(player);
        spiritDeer.setPositionAndRotation(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, player.rotationYaw, player.rotationPitch);
        spiritDeer.noClip = true;
        world.addEntity(spiritDeer);
        ItemStack head = player.getItemStackFromSlot(EquipmentSlotType.HEAD);
        if (ConfigManager.HAT_CONFIG.getAntlerDamage() != -1) {
          head.damageItem(ConfigManager.HAT_CONFIG.getAntlerDamage(), player, (breaker) -> {
            breaker.sendBreakAnimation(EquipmentSlotType.HEAD);
          });
        }
      }
    }
  }

  @Nullable
  @Override
  public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
    return MysticalWorld.MODID + ":textures/models/armor/antler_hat.png";
  }

  @SuppressWarnings("unchecked")
  @Nullable
  @Override
  @OnlyIn(Dist.CLIENT)
  public <A extends BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, A _default) {
    return (A) ModelHolder.antlerHatModel;
  }


}
