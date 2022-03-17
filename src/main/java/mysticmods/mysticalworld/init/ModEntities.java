/*
package mysticmods.mysticalworld.init;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.tterrag.registrate.util.LazySpawnEggItem;
import com.tterrag.registrate.util.entry.RegistryEntry;
import com.tterrag.registrate.util.nullness.NonNullFunction;
import mysticmods.mysticalworld.MysticalWorld;
import mysticmods.mysticalworld.config.ConfigManager;
import mysticmods.mysticalworld.config.MobConfig;
import mysticmods.mysticalworld.entity.*;
import mysticmods.mysticalworld.init.deferred.ModItems;
import mysticmods.mysticalworld.loot.conditions.*;
import net.minecraft.advancements.critereon.EntityFlagsPredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.LootingEnchantFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.functions.SmeltItemFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.*;

@SuppressWarnings({"WeakerAccess", "ConstantConditions", "unchecked", "deprecation"})
@Mod.EventBusSubscriber(modid=MysticalWorld.MODID, bus= Mod.EventBusSubscriber.Bus.MOD)
public class ModEntities {
  static {
    SPAWN_EGGS.add(MysticalWorld.REGISTRATE.item("beetle_spawn_egg", spawnEgg(ModEntities.BEETLE, 0x418594, 0x211D15)).properties((p) -> p.tab(CreativeModeTab.TAB_MISC)).model((ctx, prov) -> prov.withExistingParent(ctx.getName(), new ResourceLocation("item/template_spawn_egg"))).register());
    SPAWN_EGGS.add(MysticalWorld.REGISTRATE.item("deer_spawn_egg", spawnEgg(ModEntities.DEER, )).properties((p) -> p.tab(CreativeModeTab.TAB_MISC)).model((ctx, prov) -> prov.withExistingParent(ctx.getName(), new ResourceLocation("item/template_spawn_egg"))).register());
    SPAWN_EGGS.add(MysticalWorld.REGISTRATE.item("frog_spawn_egg", spawnEgg(ModEntities.FROG, )).properties((p) -> p.tab(CreativeModeTab.TAB_MISC)).model((ctx, prov) -> prov.withExistingParent(ctx.getName(), new ResourceLocation("item/template_spawn_egg"))).register());
    SPAWN_EGGS.add(MysticalWorld.REGISTRATE.item("silver_fox_spawn_egg", spawnEgg(ModEntities.SILVER_FOX, )).properties((p) -> p.tab(CreativeModeTab.TAB_MISC)).model((ctx, prov) -> prov.withExistingParent(ctx.getName(), new ResourceLocation("item/template_spawn_egg"))).register());
    SPAWN_EGGS.add(MysticalWorld.REGISTRATE.item("sprout_spawn_egg", spawnEgg(ModEntities.SPROUT, )).properties((p) -> p.tab(CreativeModeTab.TAB_MISC)).model((ctx, prov) -> prov.withExistingParent(ctx.getName(), new ResourceLocation("item/template_spawn_egg"))).register());
    SPAWN_EGGS.add(MysticalWorld.REGISTRATE.item("endermini_spawn_egg", spawnEgg(ModEntities.ENDERMINI, )).properties((p) -> p.tab(CreativeModeTab.TAB_MISC)).model((ctx, prov) -> prov.withExistingParent(ctx.getName(), new ResourceLocation("item/template_spawn_egg"))).register());
    SPAWN_EGGS.add(MysticalWorld.REGISTRATE.item("lava_cat_spawn_egg", spawnEgg(ModEntities.LAVA_CAT, )).properties((p) -> p.tab(CreativeModeTab.TAB_MISC)).model((ctx, prov) -> prov.withExistingParent(ctx.getName(), new ResourceLocation("item/template_spawn_egg"))).register());
    SPAWN_EGGS.add(MysticalWorld.REGISTRATE.item("owl_spawn_egg", spawnEgg(ModEntities.OWL, )).properties((p) -> p.tab(CreativeModeTab.TAB_MISC)).model((ctx, prov) -> prov.withExistingParent(ctx.getName(), new ResourceLocation("item/template_spawn_egg"))).register());
    SPAWN_EGGS.add(MysticalWorld.REGISTRATE.item("silkworm_spawn_egg", spawnEgg(ModEntities.SILKWORM, )).properties((p) -> p.tab(CreativeModeTab.TAB_MISC)).model((ctx, prov) -> prov.withExistingParent(ctx.getName(), new ResourceLocation("item/template_spawn_egg"))).register());
    SPAWN_EGGS.add(MysticalWorld.REGISTRATE.item("hell_sprout_spawn_egg", spawnEgg(ModEntities.HELL_SPROUT, )).properties((p) -> p.tab(CreativeModeTab.TAB_MISC)).model((ctx, prov) -> prov.withExistingParent(ctx.getName(), new ResourceLocation("item/template_spawn_egg"))).register());
    SPAWN_EGGS.add(MysticalWorld.REGISTRATE.item("duck_spawn_egg", spawnEgg(ModEntities.DUCK, )).properties((p) -> p.tab(CreativeModeTab.TAB_MISC)).model((ctx, prov) -> prov.withExistingParent(ctx.getName(), new ResourceLocation("item/template_spawn_egg"))).register());
    SPAWN_EGGS.add(MysticalWorld.REGISTRATE.item("clam_spawn_egg", spawnEgg(ModEntities.CLAM, )).properties((p) -> p.tab(CreativeModeTab.TAB_MISC)).model((ctx, prov) -> prov.withExistingParent(ctx.getName(), new ResourceLocation("item/template_spawn_egg"))).register());
  }



  public static void load() {
  }
}
*/
