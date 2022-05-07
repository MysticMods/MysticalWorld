package mysticmods.mysticalworld.loot.modifiers;

import com.google.gson.JsonObject;
import mysticmods.mysticalworld.init.ModItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;

import javax.annotation.Nonnull;
import java.util.List;

public class GrassDropModifier extends LootModifier {
  public GrassDropModifier(LootItemCondition[] conditionsIn) {
    super(conditionsIn);
  }

  @Nonnull
  @Override
  protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
    generatedLoot.add(new ItemStack(ModItems.AUBERGINE_SEEDS.get()));
    return generatedLoot;
  }

  public static class Serializer extends GlobalLootModifierSerializer<GrassDropModifier> {

    @Override
    public GrassDropModifier read(ResourceLocation location, JsonObject object, LootItemCondition[] ailootcondition) {
      return new GrassDropModifier(ailootcondition);
    }

    @Override
    public JsonObject write(GrassDropModifier instance) {
      return null;
    }
  }
}
