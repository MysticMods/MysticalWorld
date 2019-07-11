package epicsquid.mysticalworld;

import epicsquid.mysticalworld.setup.ModSetup;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("mysticalworld")
public class MysticalWorld {
	public static final String MODID = "mysticalworld";

//  public static CreativeTabs tab = new CreativeTabs("mysticalworld") {
//    @Override
//    public String getTabLabel() {
//      return "mysticalworld";
//    }
//
//    @Override
//    @SideOnly(Side.CLIENT)
//    public ItemStack createIcon() {
//      return new ItemStack(ModItems.carapace, 1);
//    }
//  };

	public static ModSetup setup = new ModSetup();

	public MysticalWorld() {
		FMLJavaModLoadingContext.get().getModEventBus().addListener(setup::init);
	}
}
