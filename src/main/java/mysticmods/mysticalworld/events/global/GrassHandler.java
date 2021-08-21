package mysticmods.mysticalworld.events.global;

import com.google.gson.JsonObject;
import mysticmods.mysticalworld.MysticalWorld;
import mysticmods.mysticalworld.config.ConfigManager;
import mysticmods.mysticalworld.init.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.event.RegistryEvent;

import javax.annotation.Nonnull;
import java.util.List;

// Information for determining how to do the following derived from ImmersiveEngineering from the file:
// https://github.com/BluSunrize/ImmersiveEngineering/blob/1.15/src/main/java/blusunrize/immersiveengineering/common/util/loot/GrassDrops.java
// While the code is not specifically copied from that, credit for the solution belongs to the Immersive Engineering team.
public class GrassHandler {
  public static void registerModifiers(RegistryEvent.Register<GlobalLootModifierSerializer<?>> event) {
    if (ConfigManager.SEED_INJECTION.get()) {
      event.getRegistry().register(
          new GrassDropSerializer().setRegistryName(MysticalWorld.MODID, "aubergine_seed_drops")
      );
    }
  }

  public static class GrassDropSerializer extends GlobalLootModifierSerializer<GrassDropModifier> {

    @Override
    public GrassDropModifier read(ResourceLocation location, JsonObject object, ILootCondition[] ailootcondition) {
      return new GrassDropModifier(ailootcondition);
    }

    // TODO
    @Override
    public JsonObject write(GrassDropModifier instance) {
      return null;
    }
  }

  public static class GrassDropModifier extends LootModifier {
    public GrassDropModifier(ILootCondition[] conditionsIn) {
      super(conditionsIn);
    }

    @Nonnull
    @Override
    protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
      generatedLoot.add(new ItemStack(ModItems.AUBERGINE_SEEDS.get()));
      return generatedLoot;
    }
  }
}