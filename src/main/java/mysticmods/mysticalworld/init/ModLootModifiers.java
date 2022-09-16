package mysticmods.mysticalworld.init;

import com.mojang.serialization.Codec;
import com.tterrag.registrate.util.entry.RegistryEntry;
import mysticmods.mysticalworld.MysticalWorld;
import mysticmods.mysticalworld.loot.modifiers.AddLootTableModifier;
import mysticmods.mysticalworld.loot.modifiers.GrassDropModifier;
import mysticmods.mysticalworld.loot.modifiers.SilkwormEggModifier;
import net.minecraftforge.registries.ForgeRegistries;
import noobanidus.libs.noobutil.registrate.CustomRegistrate;

public class ModLootModifiers {

	public static final CustomRegistrate REGISTRATE = MysticalWorld.REGISTRATE;

	public static final RegistryEntry<Codec<AddLootTableModifier>> ADD_LOOT_TABLE = REGISTRATE.simple("add_loot_table", ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, AddLootTableModifier.CODEC);
	public static final RegistryEntry<Codec<GrassDropModifier>> AUBERGINE_SEED_DROPS = REGISTRATE.simple("aubergine_seed_drops", ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, GrassDropModifier.CODEC);
	public static final RegistryEntry<Codec<SilkwormEggModifier>> SILKWORM_EGG_DROPS = REGISTRATE.simple("silkworm_egg_drops", ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, SilkwormEggModifier.CODEC);

	public static void load() {
	}
}
