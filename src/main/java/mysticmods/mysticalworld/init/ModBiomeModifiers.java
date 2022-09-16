package mysticmods.mysticalworld.init;

import mysticmods.mysticalworld.MysticalWorld;
import mysticmods.mysticalworld.config.ConfigManager;
import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.data.event.GatherDataEvent;
import noobanidus.libs.noobutil.data.provider.Providers;

import java.util.ArrayList;

public class ModBiomeModifiers {

	public static void gatherData(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();

		// Features
		generator.addProvider(event.includeServer(), Providers.placedFeature(MysticalWorld.MODID, generator, event.getExistingFileHelper())
				.add("tin_ore", (ctx) -> ModFeatures.TIN_ORE.get())
				.add("silver_ore", (ctx) -> ModFeatures.SILVER_ORE.get())
				.add("lead_ore", (ctx) -> ModFeatures.LEAD_ORE.get())
				.add("sapphire_ore", (ctx) -> ModFeatures.SAPPHIRE_ORE.get())
				.add("granite_quartz_ore", (ctx) -> ModFeatures.GRANITE_QUARTZ_ORE.get())
				.build()
		);

		// TODO: Re-add config for ores if it is possible to do so
		// Actually we are removing ores anyway, this is just proof of concept for spawning

		// Biome Modifiers
		generator.addProvider(event.includeServer(), Providers.biomeModifer(MysticalWorld.MODID, generator, event.getExistingFileHelper())
				.add("tin_ore", (ctx) -> new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
						ctx.getHolderSet(BiomeTags.IS_OVERWORLD),
						ctx.getHolderSet(Registry.PLACED_FEATURE_REGISTRY, ModFeatures.TIN_ORE.getId()),
						GenerationStep.Decoration.UNDERGROUND_ORES))
				.add("silver_ore", (ctx) -> new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
						ctx.getHolderSet(BiomeTags.IS_OVERWORLD),
						ctx.getHolderSet(Registry.PLACED_FEATURE_REGISTRY, ModFeatures.SILVER_ORE.getId()),
						GenerationStep.Decoration.UNDERGROUND_ORES))
				.add("lead_ore", (ctx) -> new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
						ctx.getHolderSet(BiomeTags.IS_OVERWORLD),
						ctx.getHolderSet(Registry.PLACED_FEATURE_REGISTRY, ModFeatures.LEAD_ORE.getId()),
						GenerationStep.Decoration.UNDERGROUND_ORES))
				.add("sapphire_ore", (ctx) -> new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
						ctx.getHolderSet(BiomeTags.IS_OVERWORLD),
						ctx.getHolderSet(Registry.PLACED_FEATURE_REGISTRY, ModFeatures.SAPPHIRE_ORE.getId()),
						GenerationStep.Decoration.UNDERGROUND_ORES))
				.add("granite_quartz_ore", (ctx) -> new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
						ctx.getHolderSet(BiomeTags.IS_OVERWORLD),
						ctx.getHolderSet(Registry.PLACED_FEATURE_REGISTRY, ModFeatures.GRANITE_QUARTZ_ORE.getId()),
						GenerationStep.Decoration.UNDERGROUND_ORES))
				.build()
		);
	}
}
