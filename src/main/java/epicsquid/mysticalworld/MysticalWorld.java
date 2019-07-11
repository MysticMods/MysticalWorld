package epicsquid.mysticalworld;

import epicsquid.mysticalworld.init.ModItems;
import epicsquid.mysticalworld.setup.ModSetup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("mysticalworld")
public class MysticalWorld {
	public static final String MODID = "mysticalworld";

	public static final ItemGroup ITEM_GROUP = new ItemGroup("mysticalworld") {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(ModItems.carapace);
		}
	};

	public static ModSetup setup = new ModSetup();

	public MysticalWorld() {
		FMLJavaModLoadingContext.get().getModEventBus().addListener(setup::init);
	}
}
