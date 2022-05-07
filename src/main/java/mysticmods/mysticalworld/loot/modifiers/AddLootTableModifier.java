package mysticmods.mysticalworld.loot.modifiers;

import com.google.gson.JsonObject;
import mysticmods.mysticalworld.config.ConfigManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import org.jetbrains.annotations.NotNull;

import java.util.List;

// Credit to Commoble, copied from:
// https://github.com/vectorwing/FarmersDelight/blob/4471a928c13a018c6a488b40482e22baf8b14096/src/main/java/vectorwing/farmersdelight/common/loot/modifier/AddLootTableModifier.java
public class AddLootTableModifier extends LootModifier {
  private final ResourceLocation lootTable;

  protected AddLootTableModifier(LootItemCondition[] conditionsIn, ResourceLocation lootTable) {
    super(conditionsIn);
    this.lootTable = lootTable;
  }

  @SuppressWarnings("deprecation")
  @NotNull
  @Override
  protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
    if (ConfigManager.LOOT_INJECTION.get()) {
      LootTable extra = context.getLootTable(lootTable);
      extra.getRandomItems(context, generatedLoot::add);
    }
    return generatedLoot;
  }

  public static class Serializer extends GlobalLootModifierSerializer<AddLootTableModifier> {
    @Override
    public AddLootTableModifier read(ResourceLocation location, JsonObject object, LootItemCondition[] ailootcondition)  {
    return new AddLootTableModifier(ailootcondition, new ResourceLocation(GsonHelper.getAsString(object, "table")));
    }

    @Override
    public JsonObject write(AddLootTableModifier instance) {
      JsonObject object = this.makeConditions(instance.conditions);
      object.addProperty("table", instance.lootTable.toString());
      return object;
    }
  }
}
