package mysticmods.mysticalworld.init;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.EntityDamageSource;

import javax.annotation.Nullable;

public class ModDamage {
  public static DamageSource SPIRIT_DAMAGE = new DamageSource("spirit_damage").bypassArmor().setScalesWithDifficulty();

  public static DamageSource causeSpiritDamage(@Nullable Entity entity) {
    DamageSource source;
    if (entity == null) {
      source = SPIRIT_DAMAGE;
    } else {
      source = new EntityDamageSource("spirit_damage", entity);
      source.bypassArmor().setScalesWithDifficulty();
    }
    return source;
  }
}
