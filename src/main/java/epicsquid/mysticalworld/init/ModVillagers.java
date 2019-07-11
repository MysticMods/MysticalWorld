package epicsquid.mysticalworld.init;

import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.items.ModItems;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerCareer;
import net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerProfession;

@Mod.EventBusSubscriber(modid= MysticalWorld.MODID)
@SuppressWarnings("unused")
public class ModVillagers {
  @SubscribeEvent
  public static void onProfessionRegister (RegistryEvent.Register<VillagerProfession> event) {
    // leatherworker
    VillagerProfession butcherProf = event.getRegistry().getValue(new ResourceLocation("minecraft:butcher"));
    assert butcherProf != null;
    VillagerCareer butcher = butcherProf.getCareer(0);
    butcher.addTrade(1, new EntityVillager.EmeraldForItems(ModItems.venison, new EntityVillager.PriceInfo(14, 18)));
    butcher.addTrade(2, new EntityVillager.ListItemForEmeralds(ModItems.cooked_venison, new EntityVillager.PriceInfo(1, 1)));
    VillagerCareer leatherworker = butcherProf.getCareer(1);
    leatherworker.addTrade(1, new EntityVillager.EmeraldForItems(ModItems.pelt, new EntityVillager.PriceInfo(9, 12)),
        new EntityVillager.EmeraldForItems(ModItems.carapace, new EntityVillager.PriceInfo(4, 9)),
        new EntityVillager.EmeraldForItems(ModItems.antlers, new EntityVillager.PriceInfo(1, 1)));
  }
}

