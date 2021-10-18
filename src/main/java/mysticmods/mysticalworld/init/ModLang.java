package mysticmods.mysticalworld.init;

import com.tterrag.registrate.providers.ProviderType;
import mysticmods.mysticalworld.MysticalWorld;

public class ModLang {
  static {
    MysticalWorld.REGISTRATE.addDataGenerator(ProviderType.LANG, (p) -> {
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
      p.add("item.minecraft.splash_potion.effect.serendipity", "Splash Potion of Serendipity");
      p.add("item.minecraft.tipped_arrow.effect.serendipity", "Arrow of Serendipity");
      p.add("item.minecraft.lingering_potion.effect.serendipity", "Lingering Potion of Serendipity");
      p.add("mysticalworld.subtitles.entity.fox.aggro", "Fox yips");
      p.add("mysticalworld.subtitles.entity.fox.bark", "Fox barks");
      p.add("mysticalworld.subtitles.entity.fox.bite", "Fox bites");
      p.add("mysticalworld.subtitles.entity.fox.death", "Fox dies");
      p.add("mysticalworld.subtitles.entity.fox.eat", "Fox eats");
      p.add("mysticalworld.subtitles.entity.fox.idle", "Fox yips");
      p.add("mysticalworld.subtitles.entity.fox.sleep", "Fox sleeps");
      p.add("mysticalworld.subtitles.entity.fox.sniff", "Fox sniffs");
      p.add("mysticalworld.subtitles.entity.fox.spit", "Fox spits");
      p.add("mysticalworld.subtitles.entity.sprout.ambient", "Sprout wanders");
      p.add("mysticalworld.subtitles.entity.endermini.death", "Endermini dies");
      p.add("mysticalworld.subtitles.entity.endermini.hit", "Endermini hurt");
      p.add("mysticalworld.subtitles.entity.endermini.idle", "Endermini chirps");
      p.add("mysticalworld.subtitles.entity.endermini.portal", "Endermini teleports");
      p.add("mysticalworld.subtitles.entity.endermini.scream", "Endermini screams");
      p.add("mysticalworld.subtitles.entity.endermini.stare", "Endermini stares");
      p.add("mysticalworld.subtitles.entity.silkworm.egg.use", "Silkworm Egg breaks");
      p.add("mysticalworld.subtitles.entity.lava_cat.sizzle", "Lava Cat sizzles");
      p.add("mysticalworld.subtitles.entity.frog.slime", "Frog spits out slime");
      p.add("mysticalworld.subtitles.entity.lava_cat.ambient", "Lava Cat meows");
      p.add("mysticalworld.subtitles.entity.lava_cat.death", "Lava Cat dies");
      p.add("mysticalworld.subtitles.entity.lava_cat.hurt", "Lava Cat hurt");
      p.add("mysticalworld.subtitles.entity.lava_cat.purr", "Lava Cat purrs");
      p.add("mysticalworld.subtitles.entity.lava_cat.purreow", "Lava Cat purreows");
      p.add("mysticalworld.subtitles.entity.silkworm.plop", "Silkworm plops");
      p.add("mysticalworld.subtitles.entity.squid.milk", "Squid milked");
      p.add("mysticalworld.subtitles.item.unripe_pearl.use", "Unripe Pearl Teleportation");
      p.add("mysticalworld.subtitles.item.pearleporter.use", "Pearleporter Activated");
      p.add("mysticalworld.subtitles.entity.silkworm.ambient", "Silkworm chirps");
      p.add("mysticalworld.subtitles.entity.silkworm.death", "Silkworm dies");
      p.add("mysticalworld.subtitles.entity.silkworm.hurt", "Silkworm hurt");
      p.add("mysticalworld.subtitles.entity.silkworm.step", "Silkworm walks");
      p.add("mysticalworld.subtitles.entity.silkworm.eat", "Silkworm eats");
      p.add("mysticalworld.subtitles.entity.frog.croak", "Frog croaks");
    });
  }

  public static void load() {

  }
}
