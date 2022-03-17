package mysticmods.mysticalworld.init.deferred;

import mysticmods.mysticalworld.MysticalWorld;
import mysticmods.mysticalworld.blocks.*;
import mysticmods.mysticalworld.init.deferred.data.BlockData;
import mysticmods.mysticalworld.init.mod.ModMaterials;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import noobanidus.libs.noobutil.block.BaseBlocks;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class ModBlocks {
  private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MysticalWorld.MODID);
  private static final DeferredRegister<Item> BLOCK_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MysticalWorld.MODID);

  public static void register(IEventBus modBus) {
    BLOCKS.register(modBus);
    BLOCK_ITEMS.register(modBus);
  }

  public static <T extends Block> RegistryObject<T> registerWithBlockItem(String name, Supplier<T> block) {
    return registerWithBlockItem(name, block, new Item.Properties().tab(MysticalWorld.ITEM_GROUP));
  }

  public static <T extends Block> RegistryObject<T> registerWithoutBlockItem(String name, Supplier<T> block) {
    return registerWithBlockItem(name, block, null);
  }

  public static <T extends Block> RegistryObject<T> registerWithBlockItem(String name, Supplier<T> block, @Nullable Item.Properties properties) {
    RegistryObject<T> result = BLOCKS.register(name, block);
    if (properties != null) {
      RegistryObject<BlockItem> blockItem = BLOCK_ITEMS.register(name, () -> new BlockItem(result.get(), properties));
      if (MysticalWorld.DATA_GEN) {
        BlockData.mapItem(result, blockItem);
      }
    }
    if (MysticalWorld.DATA_GEN) {
      BlockData.storeBlock(result);
    }
    return result;
  }

  // MISC BLOCKS

  public static final RegistryObject<UncannyGravelBlock> UNCANNY_GRAVEL = registerWithBlockItem("uncanny_gravel", () -> new UncannyGravelBlock(Block.Properties.copy(Blocks.GRAVEL)));

  public static final RegistryObject<SandBlock> UNCANNY_SAND = registerWithBlockItem("uncanny_sand", () -> new SandBlock(0x6c36e0, BlockBehaviour.Properties.copy(Blocks.SAND)));

  public static final RegistryObject<WetMudBlock> WET_MUD_BLOCK = registerWithBlockItem("wet_mud_block", () -> new WetMudBlock(BlockBehaviour.Properties.of(Material.DIRT).sound(SoundType.SLIME_BLOCK).strength(1f)));

  public static final RegistryObject<WetMudBrick> WET_MUD_BRICK = registerWithBlockItem("wet_mud_brick", () -> new WetMudBrick(BlockBehaviour.Properties.of(Material.DIRT).sound(SoundType.SLIME_BLOCK).strength(1f)));

  // BONES

  public static final RegistryObject<BonesBlock> BONE_PILE_1 = registerWithBlockItem("bone_pile_1", () -> new BonesBlock(BlockProperties.BONE, BonesBlock.BoneType.PILE));

  public static final RegistryObject<BonesBlock> BONE_PILE_2 = registerWithBlockItem("bone_pile_2", () -> new BonesBlock(BlockProperties.BONE, BonesBlock.BoneType.PILE));

  public static final RegistryObject<BonesBlock> BONE_PILE_3 = registerWithBlockItem("bone_pile_3", () -> new BonesBlock(BlockProperties.BONE, BonesBlock.BoneType.PILE));

  public static final RegistryObject<BonesBlock> BONE_PILE_4 = registerWithBlockItem("bone_pile_4", () -> new BonesBlock(BlockProperties.BONE, BonesBlock.BoneType.PILE));

  public static final RegistryObject<BonesBlock> SKELETON_BOTTOM_1 = registerWithBlockItem("skeleton_bottom_1", () -> new BonesBlock(BlockProperties.BONE, BonesBlock.BoneType.BOTTOM));

  public static final RegistryObject<BonesBlock> SKELETON_BOTTOM_2 = registerWithBlockItem("skeleton_bottom_2", () -> new BonesBlock(BlockProperties.BONE, BonesBlock.BoneType.BOTTOM));

  public static final RegistryObject<BonesBlock> SKELETON_BOTTOM_3 = registerWithBlockItem("skeleton_bottom_3", () -> new BonesBlock(BlockProperties.BONE, BonesBlock.BoneType.BOTTOM));

  public static final RegistryObject<BonesBlock> SKELETON_TOP_1 = registerWithBlockItem("skeleton_top_1", () -> new BonesBlock(BlockProperties.BONE, BonesBlock.BoneType.TOP));

  public static final RegistryObject<BonesBlock> SKELETON_TOP_2 = registerWithBlockItem("skeleton_top_2", () -> new BonesBlock(BlockProperties.BONE, BonesBlock.BoneType.TOP));

  public static final RegistryObject<BonesBlock> SKELETON_TOP_3 = registerWithBlockItem("skeleton_top_3", () -> new BonesBlock(BlockProperties.BONE, BonesBlock.BoneType.TOP));

  public static final RegistryObject<BonesBlock> SKELETON_TOP_4 = registerWithBlockItem("skeleton_top_4", () -> new BonesBlock(BlockProperties.BONE, BonesBlock.BoneType.TOP));

  // PLANTS

  public static final RegistryObject<PetrifiedFlowerBlock> STONEPETAL = registerWithBlockItem("stonepetal", () -> new PetrifiedFlowerBlock(Block.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS)));

  public static final RegistryObject<GallAppleCropBlock> GALL_APPLE = registerWithoutBlockItem("gall_apple_crop", () -> new GallAppleCropBlock(BlockProperties.CROP));

  public static final RegistryObject<AubergineCropBlock> AUBERGINE_CROP = registerWithoutBlockItem("aubergine_crop", () -> new AubergineCropBlock(BlockProperties.CROP));

  public static final RegistryObject<WildCropBlock> WILD_AUBERGINE_CROP = registerWithoutBlockItem("wild_aubergine", () -> new WildCropBlock(BlockProperties.CROP));

  public static final RegistryObject<WildCropBlock> WILD_WART = registerWithoutBlockItem("wild_wart", () -> new WildCropBlock(BlockProperties.CROP));

  // POTTED PLANTS

  public static final RegistryObject<FlowerPotBlock> POTTED_STONEPETAL = registerWithoutBlockItem("potted_stonepetal", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, STONEPETAL, Block.Properties.copy(Blocks.OAK_SAPLING)));

  // HAS DECORATIVE VARIANTS

  public static final RegistryObject<ThatchBlock> THATCH = registerWithBlockItem("thatch", () -> new ThatchBlock(BlockProperties.THATCH));

  public static final RegistryObject<Block> RED_MUSHROOM_FULL = registerWithBlockItem("red_mushroom_full", () -> new Block(BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.GRASS)));

  public static final RegistryObject<Block> BROWN_MUSHROOM_FULL = registerWithBlockItem("brown_mushroom_full", () -> new Block(BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.GRASS)));

  public static final RegistryObject<Block> STEM_MUSHROOM_FULL = registerWithBlockItem("stem_mushroom_full", () -> new Block(BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.GRASS)));

  public static final RegistryObject<Block> MUSHROOM_INSIDE = registerWithBlockItem("mushroom_inside_block", () -> new Block(BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.GRASS)));

  public static final RegistryObject<Block> MUD_BLOCK = registerWithBlockItem("mud_block", () -> new Block(BlockProperties.STONE));

  public static final RegistryObject<Block> MUD_BRICK = registerWithBlockItem("mud_brick", () -> new Block(BlockProperties.STONE));

  public static final RegistryObject<CharredLogBlock> CHARRED_WOOD = registerWithBlockItem("charred_wood", () -> new CharredLogBlock(BlockProperties.WOOD, true));

  public static final RegistryObject<CharredLogBlock> CHARRED_LOG = registerWithBlockItem("charred_log", () -> new CharredLogBlock(BlockProperties.WOOD, false));

  public static final RegistryObject<RotatedPillarBlock> STRIPPED_CHARRED_WOOD = registerWithBlockItem("stripped_charred_wood", () -> new RotatedPillarBlock(BlockProperties.WOOD));

  public static final RegistryObject<RotatedPillarBlock> STRIPPED_CHARRED_LOG = registerWithBlockItem("stripped_charred_log", () -> new RotatedPillarBlock(BlockProperties.WOOD));

  public static final RegistryObject<Block> CHARRED_PLANKS = registerWithBlockItem("charred_planks", () -> new Block(BlockProperties.PLANKS));

  public static final RegistryObject<Block> TERRACOTTA_BRICK = registerWithBlockItem("terracotta_brick", () -> new Block(BlockProperties.STONE));

  public static final RegistryObject<Block> IRON_BRICK = registerWithBlockItem("iron_brick", () -> new Block(BlockProperties.IRON_BRICK));

  public static final RegistryObject<Block> SOFT_STONE = registerWithBlockItem("soft_stone", () -> new Block(BlockProperties.SOFT_STONE));

  public static final RegistryObject<Block> BLACKENED_STONE = registerWithBlockItem("blackened_stone", () -> new Block(BlockProperties.STONE));

  public static final RegistryObject<SoftObsidian.SoftObsidianBlock> SOFT_OBSIDIAN = registerWithBlockItem("soft_obsidian", () -> new SoftObsidian.SoftObsidianBlock(BlockProperties.SOFT_OBSIDIAN));

  public static final RegistryObject<Block> SAPPHIRE_BLOCK = registerWithBlockItem("sapphire_block", () -> new Block(ModMaterials.SAPPHIRE.getBlockProps(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK))));

  public static final RegistryObject<Block> LEAD_BLOCK = registerWithBlockItem("lead_block", () -> new Block(ModMaterials.LEAD.getBlockProps(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK))));

  public static final RegistryObject<Block> ORICHALCUM_BLOCK = registerWithBlockItem("orichalcum_block", () -> new Block(ModMaterials.ORICHALCUM.getBlockProps(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK))));

  public static final RegistryObject<Block> SILVER_BLOCK = registerWithBlockItem("silver_block", () -> new Block(ModMaterials.SILVER.getBlockProps(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK))));

  public static final RegistryObject<Block> TIN_BLOCK = registerWithBlockItem("tin_block", () -> new Block(ModMaterials.TIN.getBlockProps(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK))));

  public static final RegistryObject<Block> PEARL_BLOCK = registerWithBlockItem("pearl_block", () -> new Block(BlockProperties.PEARL));

  // ORES
  public static final RegistryObject<BaseBlocks.OreBlock> GRANITE_QUARTZ_ORE = registerWithBlockItem("granite_quartz_ore", () -> new BaseBlocks.OreBlock(ModMaterials.QUARTZ.getBlockProps(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)), ModMaterials.QUARTZ.getMinXP(), ModMaterials.QUARTZ.getMaxXP()));

  public static final RegistryObject<BaseBlocks.OreBlock> SAPPHIRE_ORE = registerWithBlockItem("sapphire_ore", () -> new BaseBlocks.OreBlock(ModMaterials.SAPPHIRE.getBlockProps(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)), ModMaterials.SAPPHIRE.getMinXP(), ModMaterials.SAPPHIRE.getMaxXP()));

  public static final RegistryObject<BaseBlocks.OreBlock> LEAD_ORE = registerWithBlockItem("lead_ore", () -> new BaseBlocks.OreBlock(ModMaterials.LEAD.getBlockProps(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)), ModMaterials.LEAD.getMinXP(), ModMaterials.LEAD.getMaxXP()));

  public static final RegistryObject<BaseBlocks.OreBlock> SILVER_ORE = registerWithBlockItem("silver_ore", () -> new BaseBlocks.OreBlock(ModMaterials.SILVER.getBlockProps(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)), ModMaterials.SILVER.getMinXP(), ModMaterials.SILVER.getMaxXP()));

  public static final RegistryObject<BaseBlocks.OreBlock> TIN_ORE = registerWithBlockItem("tin_ore", () -> new BaseBlocks.OreBlock(ModMaterials.TIN.getBlockProps(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)), ModMaterials.TIN.getMinXP(), ModMaterials.TIN.getMaxXP()));


}
