package epicsquid.mysticalworld;

import java.util.HashSet;
import java.util.Set;

import epicsquid.mysticallib.LibRegistry;
import epicsquid.mysticallib.event.RegisterContentEvent;
import epicsquid.mysticallib.event.RegisterModRecipesEvent;
import epicsquid.mysticallib.item.ItemBase;
import epicsquid.mysticallib.util.Util;
import epicsquid.mysticalworld.entity.EntityBeetle;
import epicsquid.mysticalworld.entity.EntityDeer;
import epicsquid.mysticalworld.entity.EntityFox;
import epicsquid.mysticalworld.entity.EntityFrog;
import epicsquid.mysticalworld.entity.render.RenderBeetle;
import epicsquid.mysticalworld.entity.render.RenderDeer;
import epicsquid.mysticalworld.entity.render.RenderFox;
import epicsquid.mysticalworld.entity.render.RenderFrog;
import epicsquid.mysticalworld.proxy.ClientProxy;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import net.minecraftforge.registries.IForgeRegistry;

public class RegistryManager {

  public static Item carapace, pelt;

  @SubscribeEvent
  public void init(RegisterContentEvent event) {
    LibRegistry.setActiveMod(MysticalWorld.MODID, MysticalWorld.CONTAINER);

    event.addItem(carapace = new ItemBase("carapace").setModelCustom(true).setCreativeTab(MysticalWorld.tab));
    event.addItem(pelt = new ItemBase("pelt").setModelCustom(true).setCreativeTab(MysticalWorld.tab));

    //EGG COLORS GO: MAIN THEN SPOTS
    LibRegistry.registerEntity(EntityFox.class, 0xD46724, 0xF5E0D3);
    if (MysticalWorld.proxy instanceof ClientProxy)
      LibRegistry.registerEntityRenderer(EntityFox.class, new RenderFox.Factory());
    LibRegistry.registerEntity(EntityFrog.class, 0x285234, 0xDBE697);
    if (MysticalWorld.proxy instanceof ClientProxy)
      LibRegistry.registerEntityRenderer(EntityFrog.class, new RenderFrog.Factory());
    LibRegistry.registerEntity(EntityBeetle.class, 0x418594, 0x211D15);
    if (MysticalWorld.proxy instanceof ClientProxy)
      LibRegistry.registerEntityRenderer(EntityBeetle.class, new RenderBeetle.Factory());

    if (!Loader.isModLoaded("roots")) {
      LibRegistry.registerEntity(EntityDeer.class, Util.intColor(161, 132, 88), Util.intColor(94, 77, 51));
      if (MysticalWorld.proxy instanceof ClientProxy)
        LibRegistry.registerEntityRenderer(EntityDeer.class, new RenderDeer.Factory());

      Set<Biome> biomes = new HashSet<>();
      biomes.addAll(BiomeDictionary.getBiomes(Type.FOREST));
      biomes.addAll(BiomeDictionary.getBiomes(Type.COLD));
      biomes.addAll(BiomeDictionary.getBiomes(Type.CONIFEROUS));
      biomes.addAll(BiomeDictionary.getBiomes(Type.PLAINS));
      EntityRegistry.addSpawn(EntityDeer.class, 12, 4, 6, EnumCreatureType.CREATURE, biomes.toArray(new Biome[0]));
    }

    {
      Set<Biome> biomes = new HashSet<>();
      biomes.addAll(BiomeDictionary.getBiomes(Type.FOREST));
      biomes.addAll(BiomeDictionary.getBiomes(Type.COLD));
      biomes.addAll(BiomeDictionary.getBiomes(Type.CONIFEROUS));
      EntityRegistry.addSpawn(EntityFox.class, 8, 1, 3, EnumCreatureType.CREATURE, biomes.toArray(new Biome[0]));
    }

    {
      Set<Biome> biomes = new HashSet<>();
      biomes.addAll(BiomeDictionary.getBiomes(Type.SWAMP));
      biomes.addAll(BiomeDictionary.getBiomes(Type.JUNGLE));
      EntityRegistry.addSpawn(EntityFrog.class, 12, 4, 7, EnumCreatureType.CREATURE, biomes.toArray(new Biome[0]));
    }

    {
      Set<Biome> biomes = new HashSet<>();
      biomes.addAll(BiomeDictionary.getBiomes(Type.FOREST));
      biomes.addAll(BiomeDictionary.getBiomes(Type.SWAMP));
      biomes.addAll(BiomeDictionary.getBiomes(Type.JUNGLE));
      biomes.addAll(BiomeDictionary.getBiomes(Type.PLAINS));
      EntityRegistry.addSpawn(EntityBeetle.class, 10, 2, 5, EnumCreatureType.CREATURE, biomes.toArray(new Biome[0]));
    }
  }

  public static ResourceLocation getRL(String s) {
    return new ResourceLocation(MysticalWorld.MODID + ":" + s);
  }

  public static void registerShapeless(IForgeRegistry<IRecipe> registry, String name, ItemStack result, Object... ingredients) {
    registry.register(new ShapelessOreRecipe(getRL(name), result, ingredients).setRegistryName(getRL(name)));
  }

  @SubscribeEvent
  public void initRecipes(RegisterModRecipesEvent event) {
    LibRegistry.setActiveMod(MysticalWorld.MODID, MysticalWorld.CONTAINER);
    registerShapeless(event.getRegistry(), "pelt", new ItemStack(Items.LEATHER, 1), new ItemStack(pelt, 1), new ItemStack(pelt, 1));
    registerShapeless(event.getRegistry(), "carapace", new ItemStack(Items.DYE, 1, 4), new ItemStack(carapace, 1));
  }
}
