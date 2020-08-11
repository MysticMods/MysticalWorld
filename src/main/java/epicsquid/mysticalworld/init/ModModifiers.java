package epicsquid.mysticalworld.init;

import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import noobanidus.libs.noobutil.modifiers.PlayerModifierRegistry;

// TODO: No Registrate conversion

public class ModModifiers {
  public static final IAttribute BLESSED = PlayerModifierRegistry.getInstance().registerAttribute(new RangedAttribute(null, "mysticalworld.blessed", 0D, 0D, 10D).setDescription("Causes undead attackers to be set on fire and take fire damage").setShouldWatch(true));
  public static final IAttribute SMITE = PlayerModifierRegistry.getInstance().registerAttribute(new RangedAttribute(null, "mysticalworld.smite", 0D, 0D, 5D).setDescription("Deals additional damage to undead entities").setShouldWatch(true));
  public static final IAttribute SERENDIPITY = PlayerModifierRegistry.getInstance().registerAttribute(new RangedAttribute(null, "mysticalworld.serendipity", 1D, 1D, 10D).setDescription("Increases your luck and looting levels").setShouldWatch(true));
  /*
  public static final IAttribute BOUYANT = PlayerModifierRegistry.getInstance().registerAttribute(new RangedAttribute(null, "mysticalworld.bouyant", 0D, 0D, 1D).setDescription("While underwater you float without sinking").setShouldWatch(true));
  public static final IAttribute LEADEN = PlayerModifierRegistry.getInstance().registerAttribute(new RangedAttribute(null, "mysticalworld.leaden", 0D, 0D, 1D).setDescription("While underwater, you sink faster").setShouldWatch(true));
  public static final IAttribute MAGNETIC = PlayerModifierRegistry.getInstance().registerAttribute(new RangedAttribute(null, "mysticalworld.magnetic", 0D, 0D, 1D).setDescription("Nearby experience orbs are drawn to you").setShouldWatch(true));
  public static final IAttribute STRONG_ARMS = PlayerModifierRegistry.getInstance().registerAttribute(new RangedAttribute(null, "mysticalworld.strong_arms", 0D, 0D, 3D).setDescription("Decreases draw speed per level").setShouldWatch(true));
  public static final IAttribute CABALS_RUIN = PlayerModifierRegistry.getInstance().registerAttribute(new RangedAttribute(null, "mysticalworld.cabals_ruin", 0D, 0D, 10D).setDescription("Applies healing over time when damaged").setShouldWatch(true)); // POTION
  public static final IAttribute SHIELD_OF_FAITH = PlayerModifierRegistry.getInstance().registerAttribute(new RangedAttribute(null, "mysticalworld.shield_of_faith", 0D, 0D, 1D).setDescription("Grants a permanent absorbption heart that regenerates every 10 seconds").setShouldWatch(true)); // POTION
  public static final IAttribute IMPULSE = PlayerModifierRegistry.getInstance().registerAttribute(new RangedAttribute(null, "mysticalworld.impulse", 0D, 0D, 5D).setDescription("Ranged attackers are pulled violently towards you").setShouldWatch(true)); // POTION
  public static final IAttribute IRON_LUNGS = PlayerModifierRegistry.getInstance().registerAttribute(new RangedAttribute(null, "mysticalworld.iron_lungs", 0D, 0D, 5D).setDescription("Running out of breath while underwater will refill your air").setShouldWatch(true)); // POTION
  public static final IAttribute SHEARING = PlayerModifierRegistry.getInstance().registerAttribute(new RangedAttribute(null, "mysticalworld.shearing", 0D, 0D, 1D).setDescription("You can shear IShearable with an empty hand").setShouldWatch(true)); // ???
  public static final IAttribute PIN_CUSHION = PlayerModifierRegistry.getInstance().registerAttribute(new RangedAttribute(null, "mysticalworld.pin_cushion", 0D, 0D, 5D).setDescription("You have a chance to catch arrows you were shot with and store them in your inventory").setShouldWatch(true)); // ???
  public static final IAttribute OBSIDIAN_DEVOURER = PlayerModifierRegistry.getInstance().registerAttribute(new RangedAttribute(null, "mysticalworld.obsidian_devourer", 0D, 0D, 1D).setDescription("Obsidian is faster to mine").setShouldWatch(true)); // ???
  public static final IAttribute FIERY_SOUL = PlayerModifierRegistry.getInstance().registerAttribute(new RangedAttribute(null, "mysticalworld.fiery_soul", 0D, 0D, 10D).setDescription("Ranged attackers take damage and are set on fire").setShouldWatch(true));
  public static final IAttribute GUARANTEED_CRIT = PlayerModifierRegistry.getInstance().registerAttribute(new RangedAttribute(null, "mysticalworld.guaranteed_crit", 0D, 0D, 1D).setDescription("Ranged attacks made within 3 seconds of jumping are an auto-crit, but trigger a cooldown").setShouldWatch(true)); // POTION
  public static final IAttribute ARACHNAPHOBIA = PlayerModifierRegistry.getInstance().registerAttribute(new RangedAttribute(null, "mysticalworld.arachnaphobia", 0D, 0D, 5D).setDescription("Critically striking an arthropod causes it and nearby arthropods to be stunned").setShouldWatch(true)); // POTION
  public static final IAttribute TAUNTING = PlayerModifierRegistry.getInstance().registerAttribute(new RangedAttribute(null, "mysticalworld.taunting", 0D, 0D, 1D).setDescription("Nearby mods are more attracted to you").setShouldWatch(true));
  public static final IAttribute BLEEDING = PlayerModifierRegistry.getInstance().registerAttribute(new RangedAttribute(null, "mysticalworld.bleeding", 0D, 0D, 5D).setDescription("Causes mobs struck by it to bleed").setShouldWatch(true)); // POTION */

  public static void load() {
  }
}
