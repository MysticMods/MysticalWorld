package epicsquid.mysticalworld.setup;

import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.loot.conditions.HasHorns;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.conditions.LootConditionManager;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class ModSetup {

	private static ResourceLocation BARROW = new ResourceLocation(MysticalWorld.MODID, "barrow");
	private static ResourceLocation HUT = new ResourceLocation(MysticalWorld.MODID, "hut");

//	private StructureGenerator hutGenerator;
//	private StructureGenerator barrowGenerator;

	public void init(FMLCommonSetupEvent event) {
//		if (Loader.isModLoaded("jeresources")) {
//			JERIntegration.init();
//		}

		LootConditionManager.registerCondition(new HasHorns.Serializer());

		// Register spawns for mobs


		// TODO world gen
//		if (ConfigManager.BarrowDistance != -1) {
//			GameRegistry.registerWorldGenerator(barrowGenerator = new StructureGenerator(BARROW,10, () -> {
//				switch (Util.rand.nextInt(6)) {
//					case 0:
//					case 1:
//						return EntitySkeleton.class;
//					default:
//						return EntityZombie.class;
//				}
//			}, ConfigManager.BarrowDistance), 400);
//		}
//
//		if (ConfigManager.HutDistance != -1) {
//			GameRegistry.registerWorldGenerator(hutGenerator = new StructureGenerator(HUT, 6, () -> {
//				if (Util.rand.nextInt(4) == 0) {
//					return EntityWitch.class;
//				}
//				return EntityZombie.class;
//			}, ConfigManager.HutDistance), 400);
//		}
	}
}
