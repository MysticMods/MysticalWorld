package mysticmods.mysticalworld.loot.modifiers;

import com.mojang.serialization.Codec;
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
    return null;
  }
}
