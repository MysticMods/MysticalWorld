package epicsquid.mysticalworld.setup;

import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.capability.AnimalCooldownCapability;
import epicsquid.mysticalworld.capability.AnimalCooldownCapabilityStorage;
import epicsquid.mysticalworld.items.materials.*;
import epicsquid.mysticalworld.loot.conditions.HasHorns;
import epicsquid.mysticalworld.world.OreGen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.conditions.LootConditionManager;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class ModSetup {

	private static ResourceLocation BARROW = new ResourceLocation(MysticalWorld.MODID, "barrow");
	private static ResourceLocation HUT = new ResourceLocation(MysticalWorld.MODID, "hut");

//	private StructureGenerator hutGenerator;
//	private StructureGenerator barrowGenerator;


	public ModSetup() {
		ModMaterials.addMaterial(new CopperMaterial());
		ModMaterials.addMaterial(new SilverMaterial());
		ModMaterials.addMaterial(new LeadMaterial());
		ModMaterials.addMaterial(new TinMaterial());
		ModMaterials.addMaterial(new AmethystMaterial());
		ModMaterials.addMaterial(new CactusMaterial());
		ModMaterials.addMaterial(new DiamondMaterial());
		ModMaterials.addMaterial(new GoldMaterial());
		ModMaterials.addMaterial(new IronMaterial());
		ModMaterials.addMaterial(new WoodMaterial());
		ModMaterials.addMaterial(new StoneMaterial());
	}

	public void init(FMLCommonSetupEvent event) {
//		if (Loader.isModLoaded("jeresources")) {
//			JERIntegration.init();
//		}

		CapabilityManager.INSTANCE.register(AnimalCooldownCapability.class, new AnimalCooldownCapabilityStorage(), AnimalCooldownCapability::new);

		LootConditionManager.registerCondition(new HasHorns.Serializer());

		OreGen.registerOreGeneration();

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
