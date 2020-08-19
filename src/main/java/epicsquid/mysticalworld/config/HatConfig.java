package epicsquid.mysticalworld.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.config.ModConfig;

public class HatConfig implements IConfig {
  protected ForgeConfigSpec.IntValue configAntlerFrequency;
  protected ForgeConfigSpec.IntValue configAntlerThreshold;
  protected ForgeConfigSpec.DoubleValue configAntlerHealing;
  protected ForgeConfigSpec.IntValue configAntlerRegenDuration;
  protected ForgeConfigSpec.IntValue configAntlerRegenAmplifier;
  protected ForgeConfigSpec.DoubleValue configAntlerHealthBonus;
  protected ForgeConfigSpec.IntValue configAntlerDamage;
  protected ForgeConfigSpec.IntValue configMaskChance;
  protected ForgeConfigSpec.IntValue configMaskDamage;
  protected ForgeConfigSpec.IntValue configMaskDurability;
  protected ForgeConfigSpec.DoubleValue configMaskDamageBonus;

  public int antlerFrequency = 50;
  public int antlerThreshold = -1;
  public double antlerHealing = 2.0;
  public int antlerRegenDuration = 130;
  public int antlerRegenAmplifier = 1;
  public float antlerHealthBonus = 4f;
  public int antlerDamage = 1;
  public int maskChance = 4;
  public int maskAttackDamage = 1;
  public int maskDurabilityDamage = 1;
  public double maskBonusDamage = 0.3;

  public int cachedAntlerFrequency = -999;
  public int cachedAntlerThreshold = -999;
  public float cachedAntlerHealing = -999;
  public int cachedAntlerRegenDuration = -999;
  public int cachedAntlerRegenAmplifier = -999;
  public float cachedAntlerHealthBonus = -999;
  public int cachedAntlerDamage = -999;
  public int cachedMaskChance = -999;
  public int cachedMaskAttackDamage = -999;
  public int cachedMaskDurabilityDamage = -999;
  public double cachedMaskBonusDamage = -999;

  @Override
  public void apply(ForgeConfigSpec.Builder builder) {
    configAntlerFrequency = builder.comment("Spawn frequency (1 in X chances per tick while spawn conditions are met, -1 for not at all)").defineInRange("antler_frequency", antlerFrequency, 0, Integer.MAX_VALUE);
    configAntlerThreshold = builder.comment("How many hearts under maximum health the player needs to be for a deer to spawn (-1 for any value under maximum health").defineInRange("antler_threshold", antlerThreshold, -1, Integer.MAX_VALUE);
    configAntlerHealing = builder.comment("How much a Spirit Deer should heal for").defineInRange("antler_healing", antlerHealing, 0.5, Double.MAX_VALUE);
    configAntlerRegenDuration = builder.comment("How long a duration Regeneration should be applied for").defineInRange("antler_regen_duration", antlerRegenDuration, 1, Integer.MAX_VALUE);
    configAntlerRegenAmplifier = builder.comment("What amplifier should be applied to the Regeneration effect (0 = I, 1 = II, etc)").defineInRange("antler_regen_amplifier", antlerRegenAmplifier, 0, Integer.MAX_VALUE);
    configAntlerHealthBonus = builder.comment("How much of a health bonus wearing the hat should give (-1 for no bonus, 2 for a single heart, 4 for two hearts, etc").defineInRange("antler_health_bonus", antlerHealthBonus, 0.5, Double.MAX_VALUE);
    configAntlerDamage = builder.comment("How much damage to the antler hat spawning a spirit deer causes (-1 for no damage)").defineInRange("antler_damage", antlerDamage, -1, Integer.MAX_VALUE);
    configMaskChance = builder.comment("How frequency (1 in X per melee hit) Spirit Beetles should be spawned (-1 for not at all)").defineInRange("mask_chance", maskChance, -1, Integer.MAX_VALUE);
    configMaskDamage = builder.comment("How much damage Spirit Beetles should do").defineInRange("mask_damage", maskAttackDamage, 1, Integer.MAX_VALUE);
    configMaskDurability = builder.comment("How much durability damage should be done (-1 for none) per beetle spawned").defineInRange("mask_durability", maskDurabilityDamage, -1, Integer.MAX_VALUE);
    configMaskDamageBonus = builder.comment("How much of a damage to bonus should be provided by the mask (-1 for none)").defineInRange("mask_bonus_damage", maskBonusDamage, -1, Double.MAX_VALUE);
  }

  public int getAntlerFrequency() {
    if (cachedAntlerFrequency == -999) {
      cachedAntlerFrequency = configAntlerFrequency.get();
    }

    return cachedAntlerFrequency;
  }

  public int getAntlerThreshold() {
    if (cachedAntlerThreshold == -999) {
      cachedAntlerThreshold = configAntlerThreshold.get();
    }
    return cachedAntlerThreshold;
  }

  public float getAntlerHealing() {
    if (cachedAntlerHealing == -999) {
      cachedAntlerHealing = (float) (double) configAntlerHealing.get();
    }
    return cachedAntlerHealing;
  }

  public int getAntlerRegenDuration() {
    if (cachedAntlerRegenDuration == -999) {
      cachedAntlerRegenDuration = configAntlerRegenDuration.get();
    }
    return cachedAntlerRegenDuration;
  }

  public int getAntlerRegenAmplifier() {
    if (cachedAntlerRegenAmplifier == -999) {
      cachedAntlerRegenAmplifier = configAntlerRegenAmplifier.get();
    }
    return cachedAntlerRegenAmplifier;
  }

  public float getAntlerHealthBonus() {
    if (cachedAntlerHealthBonus == -999) {
      cachedAntlerHealthBonus = (float) (double) configAntlerHealthBonus.get();
    }
    return cachedAntlerHealthBonus;
  }

  public int getAntlerDamage() {
    if (cachedAntlerDamage == -999) {
      cachedAntlerDamage = configAntlerDamage.get();
    }
    return cachedAntlerDamage;
  }

  public int getMaskChance() {
    if (cachedMaskChance == -999) {
      cachedMaskChance = configMaskChance.get();
    }
    return cachedMaskChance;
  }

  public int getMaskAttackDamage() {
    if (cachedMaskAttackDamage == -999) {
      cachedMaskAttackDamage = configMaskDamage.get();
    }
    return cachedMaskAttackDamage;
  }

  public int getMaskDurabilityDamage() {
    if (cachedMaskDurabilityDamage == -999) {
      cachedMaskDurabilityDamage = configMaskDurability.get();
    }
    return cachedMaskDurabilityDamage;
  }

  public double getMaskBonusDamage() {
    if (cachedMaskBonusDamage == -999) {
      cachedMaskBonusDamage = configMaskDamageBonus.get();
    }
    return cachedMaskBonusDamage;
  }

  public void onConfigReload(ModConfig.ModConfigEvent event) {
    cachedAntlerFrequency = -999;
    cachedAntlerThreshold = -999;
    cachedAntlerHealing = -999;
    cachedAntlerRegenDuration = -999;
    cachedAntlerRegenAmplifier = -999;
    cachedAntlerHealthBonus = -999;
    cachedAntlerDamage = -999;
    cachedMaskChance = -999;
    cachedMaskAttackDamage = -999;
    cachedMaskDurabilityDamage = -999;
    cachedMaskBonusDamage = -999;
  }
}
