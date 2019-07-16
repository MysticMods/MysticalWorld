package epicsquid.mysticalworld.items.materials;

import epicsquid.mysticallib.block.OreBlockProperties;
import epicsquid.mysticallib.material.BaseArmorMaterial;
import epicsquid.mysticallib.material.BaseItemTier;
import epicsquid.mysticallib.material.IMetalMaterial;
import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.items.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.common.ToolType;

import java.util.HashMap;
import java.util.Map;

public class TinMaterial implements IMetalMaterial {

	private static final String SWORD = "SWORD";
	private static final String KNIFE = "KNIFE";
	private static final String PICKAXE = "PICKAXE";
	private static final String AXE = "AXE";
	private static final String SHOVEL = "SHOVEL";
	private static final String HOE = "HOE";
	private static final String SPEAR = "SPEAR";

	private int enchantability = 7;

	private Map<String, Float> damage = new HashMap<>();
	private Map<String, Float> speed = new HashMap<>();

	public TinMaterial() {
		damage.put(SWORD, 3.5f);
		damage.put(SHOVEL, 1.5f);
		damage.put(PICKAXE, 1.0f);
		damage.put(AXE, 6.0f);
		damage.put(KNIFE, 2.5f);

		speed.put(SWORD, -2.4f);
		speed.put(SHOVEL, -3.0f);
		speed.put(PICKAXE, -2.8f);
		speed.put(AXE, -2.7f);
		speed.put(KNIFE, -1.2f);
		speed.put(HOE, -1.0f);
	}

	@Override
	public IItemTier getTier() {
		return new BaseItemTier(165, 6.5f, 2.0f, 2, enchantability, () -> Ingredient.fromItems(ModItems.TIN_INGOT));
	}

	@Override
	public IArmorMaterial getArmor() {
		return new BaseArmorMaterial(getName(), 10, new int[]{1, 4, 5, 2}, enchantability, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0f, () -> Ingredient.fromItems(ModItems.TIN_INGOT));
	}

	@Override
	public Item.Properties getItemProps() {
		return new Item.Properties().group(MysticalWorld.METAL_ITEM_GROUP).maxDamage(165);
	}

	@Override
	public Block.Properties getBlockProps() {
		return Block.Properties.create(Material.IRON).hardnessAndResistance(5.0F, 6.0F).sound(SoundType.METAL).harvestTool(ToolType.PICKAXE).harvestLevel(1);
	}

	@Override
	public OreBlockProperties getBlockOreProps() {
		return new OreBlockProperties(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0f, 3.0f).harvestTool(ToolType.PICKAXE).harvestLevel(1), 1, 4);
	}

	@Override
	public float getAttackSpeed(String name) {
		return speed.getOrDefault(name, 1.0f);
	}

	@Override
	public float getAttackDamage(String name) {
		return damage.getOrDefault(name, 1.0f);
	}

	@Override
	public String getName() {
		return "tin";
	}
}
