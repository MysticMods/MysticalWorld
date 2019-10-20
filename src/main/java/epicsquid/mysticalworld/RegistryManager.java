package epicsquid.mysticalworld;

import epicsquid.mysticallib.material.IMaterial;
import epicsquid.mysticallib.material.MaterialGenerator;
import epicsquid.mysticalworld.blocks.AubergineCropBlock;
import epicsquid.mysticalworld.init.ModBlocks;
import epicsquid.mysticalworld.blocks.ThatchBlock;
import epicsquid.mysticalworld.entity.*;
import epicsquid.mysticalworld.init.ModEntities;
import epicsquid.mysticalworld.init.ModItems;
import epicsquid.mysticalworld.items.*;
import epicsquid.mysticalworld.items.materials.ModMaterials;
import epicsquid.mysticalworld.items.materials.QuicksilverMaterial;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Mod.EventBusSubscriber(modid = MysticalWorld.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegistryManager {

  private static final String SWORD = "SWORD";
  private static final String KNIFE = "KNIFE";
  private static final String PICKAXE = "PICKAXE";
  private static final String AXE = "AXE";
  private static final String SHOVEL = "SHOVEL";
  private static final String HOE = "HOE";
  private static final String SPEAR = "SPEAR";

  private static List<Block> blocks = new ArrayList<>();
  private static List<Block> metalBlocks = new ArrayList<>();

  private static IMaterial quicksilver = null;

  public static IMaterial getQuicksilver() {
    if (quicksilver == null) {
      quicksilver = new QuicksilverMaterial();
    }

    return quicksilver;
  }

  @SubscribeEvent
  public static void registerItems(RegistryEvent.Register<Item> event) {
    //event.getRegistry().register(new UnripePearlItem(new Item.Properties().group(MysticalWorld.ITEM_GROUP)).setRegistryName(MysticalWorld.MODID, "unripe_ender_pearl"));

    IMaterial quickMat = getQuicksilver();
    event.getRegistry().register(new Item(quickMat.getItemProps()).setRegistryName(MysticalWorld.MODID, quickMat.getName() + "_ingot"));
    event.getRegistry().register(new Item(quickMat.getItemProps()).setRegistryName(MysticalWorld.MODID, quickMat.getName() + "_nugget"));
    event.getRegistry().register(new Item(quickMat.getItemProps()).setRegistryName(MysticalWorld.MODID, quickMat.getName() + "_dust"));
    event.getRegistry().register(new QuicksilverSwordItem(quickMat.getTier(), (int) quickMat.getAttackDamage(SWORD), quickMat.getAttackSpeed(SWORD), quickMat.getItemProps()).setRegistryName(MysticalWorld.MODID, quickMat.getName() + "_sword"));
    event.getRegistry().register(new QuicksilverPickaxeItem(quickMat.getTier(), (int) quickMat.getAttackDamage(PICKAXE), quickMat.getAttackSpeed(PICKAXE), quickMat.getItemProps()).setRegistryName(MysticalWorld.MODID, quickMat.getName() + "_pickaxe"));
    event.getRegistry().register(new QuicksilverAxeItem(quickMat.getTier(), quickMat.getAttackDamage(AXE), quickMat.getAttackSpeed(AXE), quickMat.getItemProps()).setRegistryName(MysticalWorld.MODID, quickMat.getName() + "_axe"));
    event.getRegistry().register(new QuicksilverShovelItem(quickMat.getTier(), quickMat.getAttackDamage(SHOVEL), quickMat.getAttackSpeed(SHOVEL), quickMat.getItemProps()).setRegistryName(MysticalWorld.MODID, quickMat.getName() + "_shovel"));
    event.getRegistry().register(new QuicksilverKnifeItem(quickMat.getTier(), quickMat.getAttackDamage(KNIFE), quickMat.getAttackSpeed(KNIFE), quickMat.getItemProps()).setRegistryName(MysticalWorld.MODID, quickMat.getName() + "_knife"));
    event.getRegistry().register(new QuicksilverHoeItem(quickMat.getTier(), quickMat.getAttackSpeed(HOE), quickMat.getItemProps()).setRegistryName(MysticalWorld.MODID, quickMat.getName() + "_hoe"));
    event.getRegistry().register(new QuicksilverArmorItem(quickMat.getArmor(), EquipmentSlotType.HEAD, quickMat.getItemProps()).setRegistryName(MysticalWorld.MODID, quickMat.getName() + "_helmet"));
    event.getRegistry().register(new QuicksilverArmorItem(quickMat.getArmor(), EquipmentSlotType.CHEST, quickMat.getItemProps()).setRegistryName(MysticalWorld.MODID, quickMat.getName() + "_chestplate"));
    event.getRegistry().register(new QuicksilverArmorItem(quickMat.getArmor(), EquipmentSlotType.LEGS, quickMat.getItemProps()).setRegistryName(MysticalWorld.MODID, quickMat.getName() + "_leggings"));
    event.getRegistry().register(new QuicksilverArmorItem(quickMat.getArmor(), EquipmentSlotType.FEET, quickMat.getItemProps()).setRegistryName(MysticalWorld.MODID, quickMat.getName() + "_boots"));

    ModMaterials.getMaterials().forEach(mat -> MaterialGenerator.getInstance().generateItems(mat, event.getRegistry(), MysticalWorld.MODID));
    ModMaterials.addMaterial(getQuicksilver());

    blocks.forEach(block -> event.getRegistry().register(new BlockItem(block, new Item.Properties().group(MysticalWorld.ITEM_GROUP)).setRegistryName(Objects.requireNonNull(block.getRegistryName()))));
    metalBlocks.forEach(block -> event.getRegistry().register(new BlockItem(block, new Item.Properties().group(MysticalWorld.METAL_ITEM_GROUP)).setRegistryName(Objects.requireNonNull(block.getRegistryName()))));
  }

  @SubscribeEvent
  public static void registerBlocks(RegistryEvent.Register<Block> event) {
    IForgeRegistry<Block> registry = event.getRegistry();
    event.getRegistry().register(ModBlocks.AUBERGINE_CROP = new AubergineCropBlock(Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0f).sound(SoundType.CROP)).setRegistryName(new ResourceLocation(MysticalWorld.MODID, "aubergine_crop")));
    blocks.add(ModBlocks.THATCH = new ThatchBlock(Block.Properties.create(Material.WOOD).sound(SoundType.PLANT)).setRegistryName(new ResourceLocation(MysticalWorld.MODID, "thatch")));
    blocks.forEach(registry::register);

    // These register themselves just fine
    // TODO clean this up
    metalBlocks.addAll(MaterialGenerator.getInstance().generateBlocks(getQuicksilver(), event.getRegistry(), MysticalWorld.MODID));
    ModMaterials.getMaterials().forEach(mat -> metalBlocks.addAll(MaterialGenerator.getInstance().generateBlocks(mat, event.getRegistry(), MysticalWorld.MODID)));
  }

  @SubscribeEvent
  public static void registerEntities(RegistryEvent.Register<EntityType<?>> event) {
    ModEntities.registerMobSpawns();
  }
}
