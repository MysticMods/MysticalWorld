package mysticmods.mysticalworld.gen;

import com.google.common.collect.Iterables;
import mysticmods.mysticalworld.MysticalWorld;
import mysticmods.mysticalworld.init.deferred.ModBlocks;
import mysticmods.mysticalworld.init.deferred.ModEffects;
import mysticmods.mysticalworld.init.deferred.ModEntities;
import mysticmods.mysticalworld.init.deferred.data.BlockData;
import mysticmods.mysticalworld.init.deferred.data.ItemData;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.registries.RegistryObject;
import org.codehaus.plexus.util.StringUtils;

import java.util.HashSet;
import java.util.Set;
import java.util.StringJoiner;

public class MWLangGenerator extends LanguageProvider {
  public MWLangGenerator(DataGenerator gen) {
    super(gen, MysticalWorld.MODID, "en_us");
  }

  private final Set<RegistryObject<? extends Block>> doneBlocks = new HashSet<>();
  private final Set<RegistryObject<? extends Item>> doneItems = new HashSet<>();

  protected void genBlock(RegistryObject<? extends Block> block) {
    if (doneBlocks.contains(block)) {
      throw new IllegalStateException("Already generated for " + block.getId());
    }

    doneBlocks.add(block);
  }

  protected void genItem(RegistryObject<? extends Item> item) {
    if (doneItems.contains(item)) {
      throw new IllegalStateException("Already generated language for " + item.getId());
    }

    doneItems.add(item);
  }

  protected void validate() {
    Set<RegistryObject<? extends Block>> missing = new HashSet<>(BlockData.getAllBlocks());
    missing.removeAll(doneBlocks);

    for (RegistryObject<? extends Block> block : missing) {
      MysticalWorld.LOG.error("Missing translation for block: " + block.getId());
    }

    Set<RegistryObject<? extends Item>> missingItems = new HashSet<>(ItemData.getAllItems());
    missingItems.removeAll(doneItems);

    for (RegistryObject<? extends Item> item : missingItems) {
      MysticalWorld.LOG.error("Missing translation for item: " + item.getId());
    }

    if (!missingItems.isEmpty() || !missing.isEmpty()) {
      throw new IllegalStateException("Missing translations for " + (missingItems.size() + missing.size()) + " entries");
    }
  }

  @Override
  protected void addTranslations() {
    add("itemGroup.mysticalworld", "Mystical World");
    add("mysticalworld.drinks.slow_regen", "Gives a burst of revitalizing energy.");
    add("mysticalworld.drinks.wakefulness", "Perks you up, night or day! Shoos those scary phantoms away!");
    add("mysticalworld.drinks.sour", "Sour and awful to drink! Leaves you hungry.");
    add("mysticalworld.food.rotten_apple", "Has a chance to corrupt innocent villagers.");
    add("mysticalworld.entity.lava_cat", "Lava Cat");
    add("mysticalworld.entity.obsidian_cat", "Obsidian Cat");
    add("mysticalworld.item.pearleporter", "Right-Click to extract from a mature clam.");
    add("mysticalworld.item.pearleporter_immature", "This clam will mature in %s seconds.");
    add("message.dandelion_cordial", "You feel well-rested!");
    add("message.squid.cooldown", "Give it time to produce more ink!");
    add("attribute.name.generic.reachDistance", "Reach");
    add("attribute.name.mysticalworld.blessed", "Blessed");
    add("attribute.name.mysticalworld.smite", "Smite");
    add("attribute.name.mysticalworld.serendipity", "Serendipity");
    add("attribute.name.forge.swimSpeed", "Swim Speed");
    add("advancement.mysticalworld.root", "Mystical World");
    add("advancement.mysticalworld.root.desc", "There's magic everywhere!");
    add("advancement.mysticalworld.sapphire", "Serendipitous Sapphires");
    add("advancement.mysticalworld.sapphire.desc", "Be lucky enough to find a gem more divine than diamond!");
    add("advancement.mysticalworld.aubergine", "That's No Carrot...");
    add("advancement.mysticalworld.aubergine.desc", "Once, purple carrots were all the rage. But this is an aubergine.");
    add("advancement.mysticalworld.epic_squid", "Epic Squid!");
    add("advancement.mysticalworld.epic_squid.desc", "Partake in some of the delicious, purple-y goodness of Epic Squid.");
    add("message.shoulder.occupied", "Your shoulders are otherwise occupied!");
    add("mysticalworld.blessed", "Blessed");
    add("mysticalworld.smite", "Smite");
    add("mysticalworld.serendipity", "Serendipity");
    add("item.minecraft.potion.effect.serendipity", "Serendipity");
    add("item.minecraft.splash_potion.effect.serendipity", "Splash Potion of Serendipity");
    add("item.minecraft.tipped_arrow.effect.serendipity", "Arrow of Serendipity");
    add("item.minecraft.lingering_potion.effect.serendipity", "Lingering Potion of Serendipity");
    add("mysticalworld.subtitles.entity.fox.aggro", "Fox yips");
    add("mysticalworld.subtitles.entity.fox.bark", "Fox barks");
    add("mysticalworld.subtitles.entity.fox.bite", "Fox bites");
    add("mysticalworld.subtitles.entity.fox.death", "Fox dies");
    add("mysticalworld.subtitles.entity.fox.eat", "Fox eats");
    add("mysticalworld.subtitles.entity.fox.idle", "Fox yips");
    add("mysticalworld.subtitles.entity.fox.sleep", "Fox sleeps");
    add("mysticalworld.subtitles.entity.fox.sniff", "Fox sniffs");
    add("mysticalworld.subtitles.entity.fox.spit", "Fox spits");
    add("mysticalworld.subtitles.entity.sprout.ambient", "Sprout wanders");
    add("mysticalworld.subtitles.entity.endermini.death", "Endermini dies");
    add("mysticalworld.subtitles.entity.endermini.hit", "Endermini hurt");
    add("mysticalworld.subtitles.entity.endermini.idle", "Endermini chirps");
    add("mysticalworld.subtitles.entity.endermini.portal", "Endermini teleports");
    add("mysticalworld.subtitles.entity.endermini.scream", "Endermini screams");
    add("mysticalworld.subtitles.entity.endermini.stare", "Endermini stares");
    add("mysticalworld.subtitles.entity.silkworm.egg.use", "Silkworm Egg breaks");
    add("mysticalworld.subtitles.entity.lava_cat.sizzle", "Lava Cat sizzles");
    add("mysticalworld.subtitles.entity.frog.slime", "Frog spits out slime");
    add("mysticalworld.subtitles.entity.lava_cat.ambient", "Lava Cat meows");
    add("mysticalworld.subtitles.entity.lava_cat.death", "Lava Cat dies");
    add("mysticalworld.subtitles.entity.lava_cat.hurt", "Lava Cat hurt");
    add("mysticalworld.subtitles.entity.lava_cat.purr", "Lava Cat purrs");
    add("mysticalworld.subtitles.entity.lava_cat.purreow", "Lava Cat purreows");
    add("mysticalworld.subtitles.entity.silkworm.plop", "Silkworm plops");
    add("mysticalworld.subtitles.entity.squid.milk", "Squid milked");
    add("mysticalworld.subtitles.item.unripe_pearl.use", "Unripe Pearl Teleportation");
    add("mysticalworld.subtitles.item.pearleporter.use", "Pearleporter Activated");
    add("mysticalworld.subtitles.entity.silkworm.ambient", "Silkworm chirps");
    add("mysticalworld.subtitles.entity.silkworm.death", "Silkworm dies");
    add("mysticalworld.subtitles.entity.silkworm.hurt", "Silkworm hurt");
    add("mysticalworld.subtitles.entity.silkworm.step", "Silkworm walks");
    add("mysticalworld.subtitles.entity.silkworm.eat", "Silkworm eats");
    add("mysticalworld.subtitles.entity.frog.croak", "Frog croaks");
    add("mysticalworld.subtitles.entity.duck.quack", "Duck quacks");
    add("mysticalworld.subtitles.entity.deer.ambient", "Deer squeals");

    add(ModEffects.SERENDIPITY.get(), "Serendipity");
    add(ModEffects.SLOW_REGEN.get(), "Slow Regeneration");
    add(ModEffects.WAKEFUL.get(), "Wakefulness");

    add(ModBlocks.BONE_PILE_1.get(), "Bone Pile");
    genBlock(ModBlocks.BONE_PILE_1);
    add(ModBlocks.BONE_PILE_2.get(), "Bone Pile");
    genBlock(ModBlocks.BONE_PILE_2);
    add(ModBlocks.BONE_PILE_3.get(), "Bone Pile");
    genBlock(ModBlocks.BONE_PILE_3);
    add(ModBlocks.BONE_PILE_4.get(), "Bone Pile");
    genBlock(ModBlocks.BONE_PILE_4);
    add(ModBlocks.SKELETON_BOTTOM_1.get(), "Skeletal Remains");
    genBlock(ModBlocks.SKELETON_BOTTOM_1);
    add(ModBlocks.SKELETON_BOTTOM_2.get(), "Skeletal Remains");
    genBlock(ModBlocks.SKELETON_BOTTOM_2);
    add(ModBlocks.SKELETON_BOTTOM_3.get(), "Skeletal Remains");
    genBlock(ModBlocks.SKELETON_BOTTOM_3);
    add(ModBlocks.SKELETON_TOP_1.get(), "Skeletal Remains");
    genBlock(ModBlocks.SKELETON_TOP_1);
    add(ModBlocks.SKELETON_TOP_2.get(), "Skeletal Remains");
    genBlock(ModBlocks.SKELETON_TOP_2);
    add(ModBlocks.SKELETON_TOP_3.get(), "Skeletal Remains");
    genBlock(ModBlocks.SKELETON_TOP_3);
    add(ModBlocks.SKELETON_TOP_4.get(), "Skeletal Remains");
    genBlock(ModBlocks.SKELETON_TOP_4);

    for (RegistryObject<? extends Block> block : BlockData.getAllBlocks()) {
      if (doneBlocks.contains(block)) {
        continue;
      }

      String[] name = block.getId().getPath().split("_");
      StringJoiner sb = new StringJoiner(" ");
      for (String s : name) {
        sb.add(StringUtils.capitalise(s));
      }
      add(block.get(), sb.toString());
      genBlock(block);
    }

    for (RegistryObject<? extends Item> item : Iterables.concat(ItemData.getAllItems(), ModEntities.getSpawnEggs())) {
      if (doneItems.contains(item)) {
        continue;
      }

      String[] name = item.getId().getPath().split("_");
      StringJoiner sb = new StringJoiner(" ");
      for (String s : name) {
        sb.add(StringUtils.capitalise(s));
      }
      add(item.get(), sb.toString());
      genItem(item);
    }

    for (RegistryObject<? extends EntityType<?>> entity : ModEntities.getEntities()) {
      String[] name = entity.getId().getPath().split("_");
      StringJoiner sb = new StringJoiner(" ");
      for (String s : name) {
        sb.add(StringUtils.capitalise(s));
      }
      add(entity.get(), sb.toString());
    }

    validate();
  }
}
