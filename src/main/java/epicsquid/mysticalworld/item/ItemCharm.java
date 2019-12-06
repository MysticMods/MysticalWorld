/*package epicsquid.mysticalworld.item;

import epicsquid.mysticallib.item.ItemBase;
import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.init.ModItems;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class ItemCharm extends ItemBase {
  public static List<Enchantment> ALLOWED_ENCHANTS = new ArrayList<>();

  public ItemCharm(@Nonnull String name) {
    super(name);
    setHasSubtypes(true);
  }

  @Override
  public void initModel() {
    List<ResourceLocation> mrls = new ArrayList<>();
    for (CharmMetal metal : CharmMetal.values()) {
      for (CharmGem gem : CharmGem.values()) {
        mrls.add(new ResourceLocation(MysticalWorld.MODID, metal.getName() + "_" + gem.getName() + "_charm"));
      }
    }
    ModelBakery.registerItemVariants(this, mrls.toArray(new ResourceLocation[0]));

    ModelLoader.setCustomMeshDefinition(this, stack -> {
      NBTTagCompound tag = stack.getTagCompound();
      String metal = tag.getString("metal");
      String gem = tag.getString("gem");
      return new ModelResourceLocation(new ResourceLocation(MysticalWorld.MODID, metal + "_" + gem + "_charm"), "inventory");
    });
  }

  @Override
  public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
    if (tab == MysticalWorld.tab || tab == CreativeTabs.SEARCH) {
      for (CharmGem gem : CharmGem.values()) {
        for (CharmMetal metal : CharmMetal.values()) {
          items.add(createCharm(metal, gem));
        }
      }
    }
  }

  @Override
  public String getItemStackDisplayName(ItemStack stack) {
    NBTTagCompound tag = stack.getTagCompound();
    if (tag == null) {
      return I18n.format("item.charm.plain.name");
    }
    String metal = tag.getString("metal");
    String gem = tag.getString("gem");
    return I18n.format("item.charm." + metal + "." + gem + ".name");
  }

  @Override
  public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
    return ALLOWED_ENCHANTS.contains(enchantment);
  }

  @Override
  public boolean isEnchantable(ItemStack stack) {
    return true;
  }

  @Override
  public int getItemEnchantability(ItemStack stack) {
    NBTTagCompound tag = stack.getTagCompound();
    if (tag == null) {
      return 0;
    }
    CharmMetal metal = CharmMetal.byName(tag.getString("metal"));
    CharmGem gem = CharmGem.byName(tag.getString("gem"));
    return (int) (metal.getBaseEnchantability() * gem.getModifier());
  }

  public static ItemStack createCharm(CharmMetal metal, CharmGem gem) {
    ItemStack stack = new ItemStack(ModItems.charm);
    NBTTagCompound compound = new NBTTagCompound();
    compound.setString("metal", metal.getName());
    compound.setString("gem", gem.getName());
    stack.setTagCompound(compound);
    return stack;
  }

  public enum CharmMetal {
    IRON("iron", 10),
    COPPER("copper", 15),
    SILVER("silver", 20),
    GOLD("gold", 25);

    private String name;
    private int baseEnchantability;

    public String getName() {
      return name;
    }

    public int getBaseEnchantability() {
      return baseEnchantability;
    }

    CharmMetal(String name, int baseEnchantability) {
      this.name = name;
      this.baseEnchantability = baseEnchantability;
    }

    public static CharmMetal byName(String name) {
      for (CharmMetal metal : values()) {
        if (metal.getName().equals(name)) {
          return metal;
        }
      }

      return null;
    }
  }

  public enum CharmGem {
    EMERALD("emerald", 1.2f),
    DIAMOND("diamond", 1.5f),
    AMETHYST("amethyst", 1.6f);

    private String name;
    private float modifier;

    public String getName() {
      return name;
    }

    public float getModifier() {
      return modifier;
    }

    CharmGem(String name, float modifier) {
      this.name = name;
      this.modifier = modifier;
    }

    public static CharmGem byName(String name) {
      for (CharmGem gem : values()) {
        if (gem.getName().equals(name)) {
          return gem;
        }
      }

      return null;
    }
  }
}*/
