package mysticmods.mysticalworld.integration;

import com.ferreusveritas.dynamictrees.api.GatherDataHelper;
import com.ferreusveritas.dynamictrees.api.registry.RegistryHandler;
import mysticmods.mysticalworld.MysticalWorld;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

public class DTIntegration {

    public static final String MOD_ID = "dynamictrees";

    private static boolean isDTLoaded() {
        return ModList.get().isLoaded(MOD_ID);
    }

    public static final DTIntegration INSTANCE = isDTLoaded() ? new DTIntegration() : new DTIntegration.None();

    private static final class None extends DTIntegration {

        @Override
        public void load() {
        }

        @Override
        public void gatherData(GatherDataEvent event) {
        }

    }

    public void load() {
        RegistryHandler.setup(MysticalWorld.MODID);
    }

    public void gatherData(GatherDataEvent event) {
        GatherDataHelper.gatherTagData(MysticalWorld.MODID, event);
        GatherDataHelper.gatherLootData(MysticalWorld.MODID, event);
    }

}
