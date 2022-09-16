package mysticmods.mysticalworld.loot.modifiers;

import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import mysticmods.mysticalworld.config.ConfigManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

// Credit to Commoble, copied from:
// https://github.com/vectorwing/FarmersDelight/blob/4471a928c13a018c6a488b40482e22baf8b14096/src/main/java/vectorwing/farmersdelight/common/loot/modifier/AddLootTableModifier.java
public class AddLootTableModifier extends LootModifier {

  public static final NonNullSupplier<Codec<AddLootTableModifier>> CODEC = NonNullSupplier.of(Suppliers.memoize(() ->
      RecordCodecBuilder.create(inst -> codecStart(inst)
          .and(ResourceLocation.CODEC.fieldOf("lootTable").forGetter((m) -> m.lootTable))
          .apply(inst, AddLootTableModifier::new))));
  private final ResourceLocation lootTable;

  protected AddLootTableModifier(LootItemCondition[] conditionsIn, ResourceLocation lootTable) {
    super(conditionsIn);
    this.lootTable = lootTable;
  }

  @SuppressWarnings("deprecation")
  @NotNull
  @Override
  protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
    if (ConfigManager.LOOT_INJECTION.get()) {
      LootTable extra = context.getLootTable(lootTable);
      extra.getRandomItems(context, generatedLoot::add);
    }
    return generatedLoot;
  }

  @Override
  public Codec<? extends IGlobalLootModifier> codec() {
    return CODEC.get();
  }
}
