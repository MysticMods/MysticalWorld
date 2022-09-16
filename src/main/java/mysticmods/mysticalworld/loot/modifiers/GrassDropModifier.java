package mysticmods.mysticalworld.loot.modifiers;

import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import mysticmods.mysticalworld.config.ConfigManager;
import mysticmods.mysticalworld.init.ModItems;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;

import javax.annotation.Nonnull;

public class GrassDropModifier extends LootModifier {

	public static final NonNullSupplier<Codec<GrassDropModifier>> CODEC = NonNullSupplier.of(Suppliers.memoize(() ->
			RecordCodecBuilder.create(inst -> codecStart(inst).apply(inst, GrassDropModifier::new))));

	public GrassDropModifier(LootItemCondition[] conditionsIn) {
		super(conditionsIn);
	}

	@Nonnull
	@Override
	protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
		if (ConfigManager.SEED_INJECTION.get()) {
			generatedLoot.add(new ItemStack(ModItems.AUBERGINE_SEEDS.get()));
		}
		return generatedLoot;
	}

	@Override
	public Codec<? extends IGlobalLootModifier> codec() {
		return CODEC.get();
	}
}
