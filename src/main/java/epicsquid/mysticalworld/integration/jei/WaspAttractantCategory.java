package epicsquid.mysticalworld.integration.jei;

import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.init.ModBlocks;
import epicsquid.mysticalworld.init.ModItems;
import epicsquid.mysticalworld.util.RenderUtil;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.LogBlock;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import noobanidus.libs.noobutil.client.CycleTimer;

import java.util.List;
import java.util.stream.Collectors;

public class WaspAttractantCategory implements IRecipeCategory<WaspAttractantCategory.WaspAttractantRecipe> {
  private static final CycleTimer timer = new CycleTimer(10);
  public static ResourceLocation UID = new ResourceLocation(MysticalWorld.MODID, "wasp_attractant");

  public static IDrawable background;
  public static IDrawable icon;
  public static BlockState appleStates;
  public static List<BlockState> logStates;

  public WaspAttractantCategory(IGuiHelper helper) {
    icon = helper.createDrawableIngredient(new ItemStack(ModItems.GALL_APPLE.get()));
    background = helper.createDrawable(new ResourceLocation(MysticalWorld.MODID, "textures/gui/jei/wasp_attractant_growth.png"), 0, 0, 88, 57);
    logStates = BlockTags.OAK_LOGS.getAllElements().stream().map(o -> o.getDefaultState().with(LogBlock.AXIS, Direction.Axis.Y)).collect(Collectors.toList());
    BlockTags.DARK_OAK_LOGS.getAllElements().forEach(o -> logStates.add(o.getDefaultState().with(LogBlock.AXIS, Direction.Axis.Y)));
    appleStates = ModBlocks.GALL_APPLE_CROP.get().getDefaultState().with(HorizontalBlock.HORIZONTAL_FACING, Direction.EAST).with(BlockStateProperties.AGE_0_3, 0);
  }

  @Override
  public ResourceLocation getUid() {
    return UID;
  }

  @Override
  public Class<? extends WaspAttractantRecipe> getRecipeClass() {
    return WaspAttractantRecipe.class;
  }

  @Override
  public String getTitle() {
    return I18n.format("mysticalworld.jei.wasp_attractant");
  }

  @Override
  public IDrawable getBackground() {
    return background;
  }

  @Override
  public IDrawable getIcon() {
    return icon;
  }

  @Override
  public void setIngredients(WaspAttractantRecipe gallAppleCropRecipe, IIngredients iIngredients) {
    iIngredients.setInput(VanillaTypes.ITEM, new ItemStack(ModItems.WASP_ATTRACTANT.get()));
  }

  @Override
  public void setRecipe(IRecipeLayout iRecipeLayout, WaspAttractantRecipe gallAppleCropRecipe, IIngredients iIngredients) {
    IGuiItemStackGroup groups = iRecipeLayout.getItemStacks();
    groups.init(0, true, 1, 20);
    groups.set(iIngredients);
  }

  @Override
  public void draw(WaspAttractantRecipe recipe, double mouseX, double mouseY) {
    BlockState log = timer.getCycledItem(logStates);
    BlockState apple = appleStates;
    timer.onDraw();
    RenderUtil.renderBlock(log, 58, 40, 10, 20f, 0.4f);
    RenderUtil.renderBlock(apple, 65, 45, 10, 20f, 0.4f);
  }

  public static WaspAttractantRecipe INSTANCE = new WaspAttractantRecipe();

  public static class WaspAttractantRecipe {
  }
}
