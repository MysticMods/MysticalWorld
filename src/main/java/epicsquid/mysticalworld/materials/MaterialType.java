package epicsquid.mysticalworld.materials;

import epicsquid.mysticalworld.MysticalWorld;
import it.unimi.dsi.fastutil.objects.Object2FloatOpenHashMap;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;

public class MaterialType implements IItemTier, IArmorMaterial {
  public static final UUID[] ARMOR_MODIFIERS = new UUID[]{UUID.fromString("845DB27C-C624-495F-8C9F-6020A9A58B6B"), UUID.fromString("D8499B04-0E66-4726-AB29-64469D734E0D"), UUID.fromString("9F3D476D-C118-4544-8365-64846904B48E"), UUID.fromString("2AD3F246-FEE1-4E67-B886-69FD380BB150")};

  private final static List<MaterialType> MATERIAL_TYPES = new ArrayList<>();

  private static final int[] MAX_DAMAGE_ARRAY = new int[]{13, 15, 16, 11};

  private final String name;
  private IItemTier tier = null;
  private IArmorMaterial material = null;
  private int maxUses;
  private float efficiency;
  private float attackDamage;
  private int harvestLevel;
  private int enchantability;
  private Supplier<Ingredient> repairMaterial;

  private int maxDamageFactor;
  private int[] damageReductionAmountArray;
  private SoundEvent soundEvent;
  private float toughness;

  private Supplier<Supplier<? extends Item>> item;
  private Supplier<Supplier<? extends Item>> dust;
  private Supplier<Supplier<? extends Item>> nugget;
  private Supplier<Supplier<? extends Block>> block;
  private Supplier<Supplier<? extends Block>> ore;

  private int maxXP = 0;
  private int minXP = 0;

  private Object2FloatOpenHashMap<Type> damage = new Object2FloatOpenHashMap<>();
  private Object2FloatOpenHashMap<Type> speed = new Object2FloatOpenHashMap<>();

  private List<Type> itemTypes;

  public static List<MaterialType> getMaterialTypes() {
    return MATERIAL_TYPES;
  }

  public MaterialType(String name) {
    this.name = name;
    // Defaults
    this.putDamageSpeed(Type.SWORD, 3.0f, -2.4f, Type.SHOVEL, 1.5f, -3.0f, Type.PICKAXE, 1.0f, -2.8f, Type.HOE, 1.0f, -1.0f);
    MATERIAL_TYPES.add(this);
  }

  public MaterialType itemMaterial(int maxUses, float efficiency, float attackDamage, int harvestLevel, int enchantability) {
    this.maxUses = maxUses;
    this.efficiency = efficiency;
    this.attackDamage = attackDamage;
    this.harvestLevel = harvestLevel;
    this.enchantability = enchantability;
    this.repairMaterial = () -> Ingredient.fromItems(item.get().get());
    return this;
  }

  public MaterialType itemMaterial(int maxUses, float efficiency, float attackDamage, int harvestLevel, int enchantability, Supplier<Ingredient> repairMaterial) {
    return itemMaterial(maxUses, efficiency, attackDamage, harvestLevel, enchantability);
  }

  public MaterialType armorMaterial(int maxDamageFactor, int[] damageReductionAmountArray, SoundEvent soundEvent, float toughness) {
    this.maxDamageFactor = maxDamageFactor;
    this.damageReductionAmountArray = damageReductionAmountArray;
    this.soundEvent = soundEvent;
    this.toughness = toughness;
    return this;
  }

  public MaterialType setItemTier(IItemTier tier) {
    this.tier = tier;
    return this;
  }

  public MaterialType setArmorMaterial(IArmorMaterial material) {
    this.material = material;
    return this;
  }

  public int getMaxXP() {
    return maxXP;
  }

  public MaterialType setMaxXP(int maxXP) {
    this.maxXP = maxXP;
    return this;
  }

  public int getMinXP() {
    return minXP;
  }

  public MaterialType setMinXP(int minXP) {
    this.minXP = minXP;
    return this;
  }

  public MaterialType putDamageSpeed(Object... entries) {
    if (entries.length % 3 != 0) {
      throw new IllegalArgumentException("Invalid number of arguments to putDamageSpeed");
    }

    for (int i = 0; i < entries.length; i += 3) {
      Type type = (Type) entries[i];
      float damage = (float) entries[i + 1];
      float speed = (float) entries[i + 2];

      this.damage.put(type, damage);
      this.speed.put(type, speed);
    }

    return this;
  }

  public int getDamage(Type type) {
    return (int) damage.getOrDefault(type, 1.0f);
  }

  public int getDamage(String type) {
    return getDamage(Type.byName(type));
  }

  public float getSpeed(Type type) {
    return speed.getOrDefault(type, -1.0f);
  }

  public float getSpeed(String type) {
    return getSpeed(Type.byName(type));
  }

  public List<Type> getItemTypes() {
    return itemTypes;
  }

  public MaterialType setItemTypes(Type... types) {
    this.itemTypes = Arrays.asList(types);
    return this;
  }

  @Override
  @OnlyIn(Dist.CLIENT)
  public String getName() {
    return MysticalWorld.MODID + ":" + getInternalName();
  }

  public String getInternalName() {
    return name;
  }

  public Supplier<? extends Item> getItem() {
    return item.get();
  }

  public Supplier<? extends Item> getDust() {
    return dust.get();
  }

  public Supplier<? extends Item> getNugget() {
    return nugget.get();
  }

  public Supplier<? extends Block> getBlock() {
    return block.get();
  }

  public Supplier<? extends Block> getOre() {
    return ore.get();
  }

  public MaterialType item(Supplier<Supplier<? extends Item>> ingot) {
    this.item = ingot;
    return this;
  }

  public MaterialType dust(Supplier<Supplier<? extends Item>> dust) {
    this.dust = dust;
    return this;
  }

  public MaterialType nugget(Supplier<Supplier<? extends Item>> nugget) {
    this.nugget = nugget;
    return this;
  }

  public MaterialType block(Supplier<Supplier<? extends Block>> block) {
    this.block = block;
    return this;
  }

  public MaterialType ore(Supplier<Supplier<? extends Block>> ore) {
    this.ore = ore;
    return this;
  }

  public Supplier<Block.Properties> getBlockProps() {
    return () -> Block.Properties.create(Material.IRON).hardnessAndResistance(5.0F, 6.0F).sound(SoundType.METAL).harvestTool(ToolType.PICKAXE).harvestLevel(1);
  }

  public Supplier<Block.Properties> getOreBlockProperties() {
    return () -> Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0f, 3.0f).harvestTool(ToolType.PICKAXE).harvestLevel(getHarvestLevel());
  }

  public String gemName() {
    return name + "_gem";
  }

  public String ingotName() {
    return name + "_ingot";
  }

  public String dustName() {
    return name + "_dust";
  }

  public String blockName() {
    return name + "_block";
  }

  public String oreName() {
    return name + "_ore";
  }

  public String nuggetName() {
    return name + "_nugget";
  }

  /* Implementation of Material and Armor Material interfaces:
   *   Note: the get-repair-item method inherently goes with the
   *         item supplied by the item tier.                      */

  @Override
  public float getToughness() {
    return this.material == null ? toughness : this.material.getToughness();
  }

  @Override
  public int getMaxUses() {
    return tier == null ? maxUses : tier.getMaxUses();
  }

  @Override
  public float getEfficiency() {
    return tier == null ? efficiency : tier.getEfficiency();
  }

  @Override
  public float getAttackDamage() {
    return tier == null ? attackDamage : tier.getAttackDamage();
  }

  @Override
  public int getHarvestLevel() {
    return tier == null ? harvestLevel : tier.getHarvestLevel();
  }

  @Override
  public int getDurability(EquipmentSlotType slotIn) {
    return material == null ? MAX_DAMAGE_ARRAY[slotIn.getIndex()] * this.maxDamageFactor : this.material.getDurability(slotIn);
  }

  @Override
  public int getDamageReductionAmount(EquipmentSlotType slotIn) {
    return material == null ? this.damageReductionAmountArray[slotIn.getIndex()] : this.material.getDamageReductionAmount(slotIn);
  }

  @Override
  public int getEnchantability() {
    return tier == null ? enchantability : tier.getEnchantability();
  }

  // Hopefully this hack is enough to resolve the above method being disambiguated
  // into only being the implementation for IItemTier.
  public int func_200900_a () {
    return material == null ? enchantability : material.getEnchantability();
  }

  @Override
  public SoundEvent getSoundEvent() {
    return material == null ? soundEvent : this.material.getSoundEvent();
  }

  @Override
  @Nonnull
  public Ingredient getRepairMaterial() {
    return tier == null ? repairMaterial.get() : tier.getRepairMaterial();
  }

  private Supplier<Ingredient> provider(Supplier<? extends Item> item) {
    return () -> Ingredient.fromItems(item.get());
  }

  public enum Type {
    SWORD("sword"),
    KNIFE("knife"),
    PICKAXE("pickaxe"),
    AXE("axe"),
    SHOVEL("shovel"),
    HOE("hoe"),
    SPEAR("spear");

    private final String name;

    Type(String name) {
      this.name = name;
    }

    public String getName() {
      return name;
    }

    @Nullable
    public static Type byName(String name) {
      for (Type i : values()) {
        if (i.getName().equals(name)) {
          return i;
        }
      }

      return null;
    }
  }
}
