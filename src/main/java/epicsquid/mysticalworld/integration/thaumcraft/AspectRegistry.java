package epicsquid.mysticalworld.integration.thaumcraft;

import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.init.ModBlocks;
import epicsquid.mysticalworld.init.ModItems;
import epicsquid.mysticalworld.materials.Materials;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectEventProxy;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.AspectRegistryEvent;

public class AspectRegistry {
  @SubscribeEvent
  public static void registerAspects(AspectRegistryEvent event) {
    AspectEventProxy proxy = event.register;
    proxy.registerObjectTag(new ItemStack(Materials.amethyst.getItem()), new AspectList().add(Aspect.CRYSTAL, 15).add(Aspect.DESIRE, 15));
    proxy.registerObjectTag(new ItemStack(Materials.amethyst.getOre()), new AspectList().add(Aspect.EARTH, 5).add(Aspect.CRYSTAL, 15).add(Aspect.DESIRE, 15));
    proxy.registerObjectTag(new ItemStack(ModItems.antler_hat), new AspectList().add(Aspect.LIFE, 5).add(Aspect.BEAST, 5).add(Aspect.SOUL, 5).add(Aspect.CRAFT, 5).add(Aspect.PROTECT, 10));
    proxy.registerObjectTag(new ItemStack(ModItems.antlers), new AspectList().add(Aspect.LIFE, 5).add(Aspect.BEAST, 10).add(Aspect.AVERSION, 5).add(Aspect.PROTECT, 5));
    proxy.registerObjectTag(new ItemStack(ModItems.aubergine), new AspectList().add(Aspect.LIFE, 5).add(Aspect.PLANT, 5));
    proxy.registerObjectTag(new ItemStack(ModItems.aubergine_salad), new AspectList().add(Aspect.PLANT, 5).add(Aspect.MAGIC, 1));
    proxy.registerObjectTag(new ItemStack(ModItems.beetle_mask), new AspectList().add(Aspect.BEAST, 5).add(Aspect.SOUL, 5).add(Aspect.AVERSION, 5).add(Aspect.CRAFT, 5).add(Aspect.PROTECT, 5).add(Aspect.SENSES, 10));
    proxy.registerObjectTag(new ItemStack(ModItems.carapace), new AspectList().add(Aspect.BEAST, 5).add(Aspect.PROTECT, 2).add(Aspect.SENSES, 5));
    proxy.registerObjectTag(new ItemStack(ModItems.cooked_aubergine), new AspectList().add(Aspect.LIFE, 5).add(Aspect.PLANT, 5).add(Aspect.CRAFT, 1));
    proxy.registerObjectTag(new ItemStack(ModItems.cooked_beetroot), new AspectList().add(Aspect.LIFE, 3).add(Aspect.PLANT, 3).add(Aspect.CRAFT, 1));
    proxy.registerObjectTag(new ItemStack(ModItems.cooked_carrot), new AspectList().add(Aspect.LIFE, 3).add(Aspect.PLANT, 3).add(Aspect.CRAFT, 1).add(Aspect.SENSES, 3));
    proxy.registerObjectTag(new ItemStack(ModItems.cooked_seeds), new AspectList().add(Aspect.PLANT, 1).add(Aspect.CRAFT, 1));
    proxy.registerObjectTag(new ItemStack(ModItems.cooked_squid), new AspectList().add(Aspect.LIFE, 10).add(Aspect.BEAST, 5).add(Aspect.CRAFT, 1));
    proxy.registerObjectTag(new ItemStack(ModItems.cooked_venison), new AspectList().add(Aspect.LIFE, 5).add(Aspect.BEAST, 5).add(Aspect.CRAFT, 1));
    proxy.registerObjectTag(new ItemStack(ModItems.gall_apple), new AspectList().add(Aspect.PLANT, 5).add(Aspect.ALCHEMY, 5));
    proxy.registerObjectTag(new ItemStack(ModItems.gold_dust), new AspectList().add(Aspect.ENTROPY, 1).add(Aspect.METAL, 10).add(Aspect.DESIRE, 5));
    proxy.registerObjectTag(new ItemStack(ModItems.gold_dust_tiny), new AspectList().add(Aspect.METAL, 1));
    proxy.registerObjectTag(new ItemStack(ModItems.iron_dust), new AspectList().add(Aspect.ENTROPY, 1).add(Aspect.METAL, 10));
    proxy.registerObjectTag(new ItemStack(ModItems.iron_dust_tiny), new AspectList().add(Aspect.METAL, 1));
    proxy.registerObjectTag(new ItemStack(ModBlocks.mud_block), new AspectList().add(Aspect.FIRE, 2).add(Aspect.ENTROPY, 10));
    proxy.registerObjectTag(new ItemStack(ModBlocks.mud_block_button), new AspectList().add(Aspect.FIRE, 1).add(Aspect.ENTROPY, 7).add(Aspect.MECHANISM, 5));
    proxy.registerObjectTag(new ItemStack(ModBlocks.mud_block_fence_gate), new AspectList().add(Aspect.FIRE, 3).add(Aspect.ENTROPY, 15).add(Aspect.PLANT, 3).add(Aspect.MECHANISM, 5).add(Aspect.SENSES, 5));
    proxy.registerObjectTag(new ItemStack(ModBlocks.mud_block_pressure_plate), new AspectList().add(Aspect.FIRE, 3).add(Aspect.ENTROPY, 15).add(Aspect.MECHANISM, 5).add(Aspect.SENSES, 5));
    proxy.registerObjectTag(new ItemStack(ModBlocks.mud_brick), new AspectList().add(Aspect.FIRE, 2).add(Aspect.ENTROPY, 10));
    proxy.registerObjectTag(new ItemStack(ModBlocks.mud_brick_button), new AspectList().add(Aspect.FIRE, 1).add(Aspect.ENTROPY, 7).add(Aspect.MECHANISM, 5));
    proxy.registerObjectTag(new ItemStack(ModBlocks.mud_brick_fence_gate), new AspectList().add(Aspect.FIRE, 3).add(Aspect.ENTROPY, 15).add(Aspect.PLANT, 3).add(Aspect.MECHANISM, 5).add(Aspect.SENSES, 5));
    proxy.registerObjectTag(new ItemStack(ModBlocks.mud_brick_pressure_plate), new AspectList().add(Aspect.FIRE, 3).add(Aspect.ENTROPY, 15).add(Aspect.MECHANISM, 5).add(Aspect.SENSES, 5));
    proxy.registerObjectTag(new ItemStack(ModItems.pearl), new AspectList().add(Aspect.WATER, 5).add(Aspect.CRYSTAL, 20).add(Aspect.DESIRE, 10));
    proxy.registerObjectTag(new ItemStack(ModBlocks.pearl_button), new AspectList().add(Aspect.WATER, 11).add(Aspect.CRYSTAL, 45).add(Aspect.DESIRE, 22).add(Aspect.MECHANISM, 5));
    proxy.registerObjectTag(new ItemStack(ModBlocks.pearl_fence_gate), new AspectList().add(Aspect.WATER, 37).add(Aspect.CRYSTAL, 150).add(Aspect.TRAP, 5).add(Aspect.DESIRE, 75).add(Aspect.MECHANISM, 5));
    proxy.registerObjectTag(new ItemStack(ModBlocks.pearl_pressure_plate), new AspectList().add(Aspect.WATER, 22).add(Aspect.CRYSTAL, 90).add(Aspect.DESIRE, 45).add(Aspect.MECHANISM, 5).add(Aspect.SENSES, 5));
    proxy.registerObjectTag(new ItemStack(ModItems.pearleporter), new AspectList().add(Aspect.METAL, 2).add(Aspect.TOOL, 15).add(Aspect.ELDRITCH, 5).add(Aspect.DESIRE, 10));
    proxy.registerObjectTag(new ItemStack(ModItems.pelt), new AspectList().add(Aspect.BEAST, 10).add(Aspect.PROTECT, 5));
    proxy.registerObjectTag(new ItemStack(ModItems.raw_squid), new AspectList().add(Aspect.LIFE, 10).add(Aspect.BEAST, 5));
    proxy.registerObjectTag(new ItemStack(ModItems.silk_cocoon), new AspectList().add(Aspect.BEAST, 5).add(Aspect.TRAP, 10).add(Aspect.FLIGHT, 10));
    proxy.registerObjectTag(new ItemStack(ModItems.silk_thread), new AspectList().add(Aspect.BEAST, 5).add(Aspect.FLIGHT, 1).add(Aspect.CRAFT, 1));
    proxy.registerObjectTag(new ItemStack(ModItems.silkworm_egg), new AspectList().add(Aspect.LIFE, 5).add(Aspect.CRYSTAL, 5).add(Aspect.TRAP, 1));
    proxy.registerObjectTag(new ItemStack(ModItems.unripe_pearl), new AspectList().add(Aspect.MOTION, 2).add(Aspect.ELDRITCH, 2));
    proxy.registerObjectTag(new ItemStack(ModItems.venison), new AspectList().add(Aspect.EARTH, 5).add(Aspect.LIFE, 5).add(Aspect.BEAST, 5));
    proxy.registerObjectTag(new ItemStack(ModItems.wasp_attractant), new AspectList().add(Aspect.LIFE, 2).add(Aspect.BEAST, 2).add(Aspect.TRAP, 2));
    proxy.registerObjectTag(new ItemStack(ModBlocks.wet_mud_block), new AspectList().add(Aspect.WATER, 5).add(Aspect.EARTH, 10));
    proxy.registerObjectTag(new ItemStack(ModBlocks.wet_mud_brick), new AspectList().add(Aspect.WATER, 5).add(Aspect.EARTH, 10));
    proxy.registerObjectTag(new ItemStack(ModBlocks.charred_log), new AspectList().add(Aspect.ENTROPY, 4).add(Aspect.PLANT, 16));
    proxy.registerObjectTag(new ItemStack(ModBlocks.charred_button), new AspectList().add(Aspect.PLANT, 2).add(Aspect.MECHANISM, 5));
    proxy.registerObjectTag(new ItemStack(ModBlocks.charred_pressure_plate), new AspectList().add(Aspect.PLANT, 4).add(Aspect.MECHANISM, 5).add(Aspect.SENSES, 5));
    proxy.registerObjectTag(new ItemStack(ModBlocks.charred_fence_gate), new AspectList().add(Aspect.PLANT, 7).add(Aspect.TRAP, 5).add(Aspect.MECHANISM, 5));
  }
}
