package epicsquid.mysticalworld.init;

import com.tterrag.registrate.providers.ProviderType;
import net.minecraft.entity.ai.attributes.RangedAttribute;

import static epicsquid.mysticalworld.MysticalWorld.REGISTRATE;

public class ModLang {
  static {
    REGISTRATE.addDataGenerator(ProviderType.LANG, (p) -> {
      p.add("itemGroup.mysticalworld", "Mystical World");
      p.add("mysticalworld.drinks.slow_regen", "Gives a burst of revitalizing energy.");
      p.add("mysticalworld.drinks.wakefulness", "Perks you up, night or day! Shoos those scary phantoms away!");
      p.add("mysticalworld.drinks.sour", "Sour and awful to drink! Leaves you hungry.");
      p.add("mysticalworld.food.rotten_apple", "Has a chance to corrupt innocent villagers.");
      p.add("mysticalworld.entity.lava_cat", "Lava Cat");
      p.add("mysticalworld.entity.obsidian_cat", "Obsidian Cat");
      p.add("message.dandelion_cordial", "You feel well-rested!");
      p.add("message.squid.cooldown", "Give it time to produce more ink!");
      p.add("attribute.name.generic.reachDistance", "Reach");
      p.add("attribute.name.mysticalworld.blessed", "Blessed");
      p.add("attribute.name.mysticalworld.smite", "Smite");
      p.add("attribute.name.mysticalworld.serendipity", "Serendipity");
      p.add("attribute.name.forge.swimSpeed", "Swim Speed");
      p.add("advancement.mysticalworld.root", "Mystical World");
      p.add("advancement.mysticalworld.root.desc", "There's magic everywhere!");
      p.add("advancement.mysticalworld.amethyst", "Sobriety Test");
      p.add("advancement.mysticalworld.amethyst.desc", "Find a gem more divine than diamond!");
      p.add("advancement.mysticalworld.aubergine", "That's No Carrot...");
      p.add("advancement.mysticalworld.aubergine.desc", "Once, purple carrots were all the rage. But this is an aubergine.");
      p.add("advancement.mysticalworld.epic_squid", "Epic Squid!");
      p.add("advancement.mysticalworld.epic_squid.desc", "Partake in some of the delicious, purple-y goodness of Epic Squid.");
      p.add("message.shoulder.occupied", "Your shoulders are otherwise occupied!");
      p.add("mysticalworld.blessed", "Blessed");
      p.add("mysticalworld.smite", "Smite");
      p.add("mysticalworld.serendipity", "Serendipity");
      p.add("item.minecraft.potion.effect.serendipity", "Serendipity");
    });
  }

  public static void load() {

  }
}
