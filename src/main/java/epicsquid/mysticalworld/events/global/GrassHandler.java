package epicsquid.mysticalworld.events.global;

import com.google.gson.JsonObject;
import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.init.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.conditions.ILootCondition;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.event.RegistryEvent;

import javax.annotation.Nonnull;
import java.util.List;

public class GrassHandler {
  public static void registerModifiers (RegistryEvent .Register<GlobalLootModifierSerializer<?>> event) {
    event.getRegistry().register(
        new GrassDropSerializer().setRegistryName(MysticalWorld.MODID, "aubergine_seed_drops")
    );
  }

  public static class GrassDropSerializer extends GlobalLootModifierSerializer<GrassDropModifier> {

    @Override
    public GrassDropModifier read(ResourceLocation location, JsonObject object, ILootCondition[] ailootcondition) {
      return new GrassDropModifier(ailootcondition);
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
