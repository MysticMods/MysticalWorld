package epicsquid.mysticalworld.events;

import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.init.ModModifiers;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = MysticalWorld.MODID)
public class DamageHandler {
  @SubscribeEvent
  public static void onLivingDamage(LivingDamageEvent event) {
    DamageSource source = event.getSource();
    EntityLivingBase target = event.getEntityLiving();
    if (target instanceof EntityPlayer) {
      if (source != null) {
        Entity trueAttacker = source.getTrueSource();
        if (trueAttacker instanceof EntityLivingBase) {
          if (((EntityLivingBase) trueAttacker).isEntityUndead()) {
            EntityPlayer player = (EntityPlayer) target;
            float blessedAmount = (float) player.getAttributeMap().getAttributeInstance(ModModifiers.BLESSED).getAttributeValue();
            if (blessedAmount > 0) {
              trueAttacker.attackEntityFrom(DamageSource.ON_FIRE, blessedAmount);
            }
          }
        }
      }
    }
  }
}
