package epicsquid.mysticalworld.init;

import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;

import javax.annotation.Nullable;

public class ModDamage {
  public static DamageSource SPIRIT_DAMAGE = new DamageSource("spirit_damage").setDamageBypassesArmor().setDifficultyScaled();

  public static DamageSource causeSpiritDamage(@Nullable Entity entity) {
    DamageSource source;
    if (entity == null) {
      source = SPIRIT_DAMAGE;
    } else {
      source = new EntityDamageSource("spirit_damage", entity);
      source.setDamageBypassesArmor().setDifficultyScaled();
    }
    return source;
  }
}
