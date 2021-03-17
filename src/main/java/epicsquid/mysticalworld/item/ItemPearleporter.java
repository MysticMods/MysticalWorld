package epicsquid.mysticalworld.item;

import epicsquid.mysticallib.item.ItemShearsBase;
import epicsquid.mysticalworld.config.ConfigManager;
import epicsquid.mysticalworld.entity.EntityClam;
import epicsquid.mysticalworld.init.ModItems;
import epicsquid.mysticalworld.init.ModSounds;
import epicsquid.mysticalworld.recipe.Ingredients;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class ItemPearleporter extends ItemShearsBase {
  public ItemPearleporter(@Nonnull String name) {
    super(name, () -> Ingredient.EMPTY);
    setMaxDamage(211);
    setMaxStackSize(1);
    setHasSubtypes(false);
  }

  @Override
  public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
    return toRepair.getItem() == this && Ingredients.PEARL.test(repair);
  }

  @Override
  public boolean itemInteractionForEntity(ItemStack itemstack, EntityPlayer player, EntityLivingBase entity, EnumHand hand) {
    World world = player.world;
    Random rand = itemRand;

    if (!(entity instanceof EntityClam)) {
      return true;
    }

    player.swingArm(hand);
    if (!world.isRemote) {
      EntityClam clam = (EntityClam) entity;
      if (clam.getDataManager().get(EntityClam.age) >= ConfigManager.clam.maturity) {
        Item item = ModItems.pearl;
        int count = 1 + rand.nextInt(2) + rand.nextInt(2);
        if (clam.getDataManager().get(EntityClam.ender)) {
          item = Items.ENDER_PEARL;
          count = 1 + rand.nextInt(2);
        }
        clam.getDataManager().set(EntityClam.age, rand.nextInt(ConfigManager.clam.initialAge));
        clam.getDataManager().set(EntityClam.ender, rand.nextInt(ConfigManager.clam.ender) == 0);
        ItemStack result = new ItemStack(item, count);
        EntityItem ent = clam.entityDropItem(result, 1.0f);
        ent.motionY += rand.nextFloat() * 0.05f;
        ent.motionX += (rand.nextFloat() - rand.nextFloat()) * 0.1f;
        ent.motionZ += (rand.nextFloat() - rand.nextFloat()) * 0.1f;
        if (!player.isCreative()) {
          ItemStack held = player.getHeldItem(hand);
          held.damageItem(1, player);
        }
        world.playSound(null, player.posX, player.posY, player.posZ, ModSounds.General.PEARLEPORTER, SoundCategory.PLAYERS , 1f, 0.2f);
      } else {
        player.sendStatusMessage(new TextComponentTranslation("message.pearleporter.immature").setStyle(new Style().setColor(TextFormatting.DARK_PURPLE)), true);
      }
    }

    return true;
  }


  @Override
  @SideOnly(Side.CLIENT)
  public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
    super.addInformation(stack, worldIn, tooltip, flagIn);

    tooltip.add(TextFormatting.BOLD + I18n.format("mysticalworld.tooltip.pearleporter"));
  }
}
