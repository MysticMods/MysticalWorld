package mysticmods.mysticalworld.loot.modifiers;

import com.google.gson.JsonObject;
import mysticmods.mysticalworld.config.ConfigManager;
import mysticmods.mysticalworld.init.ModItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SilkwormEggModifier extends LootModifier {
  protected SilkwormEggModifier(LootItemCondition[] conditionsIn) {
    super(conditionsIn);
  }

  @NotNull
  @Override
  protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
    if (context.getLevel().random.nextInt(ConfigManager.SILKWORM_CONFIG.getLeafDropChance()) == 0) {
      ItemStack tool = context.getParamOrNull(LootContextParams.TOOL);
      if (tool != null && !tool.is(Tags.Items.SHEARS)) {
        generatedLoot.add(new ItemStack(ModItems.SILKWORM_EGG.get()));
      }
    }
    return generatedLoot;
  }

  public static class Serializer extends GlobalLootModifierSerializer<SilkwormEggModifier> {
    @Override
    public SilkwormEggModifier read(ResourceLocation location, JsonObject object, LootItemCondition[] ailootcondition) {
      return new SilkwormEggModifier(ailootcondition);
    }

    @Override
    public JsonObject write(SilkwormEggModifier instance) {
      return null;
    }
  }
}
