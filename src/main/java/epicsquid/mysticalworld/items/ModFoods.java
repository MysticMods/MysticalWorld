package epicsquid.mysticalworld.items;

import net.minecraft.item.Food;

public class ModFoods {

	public static final Food VENISION = (new Food.Builder().hunger(3).meat().saturation(0.3f)).build();
	public static final Food COOKED_VENISION = (new Food.Builder().hunger(7).meat().saturation(0.8f)).build();
	public static final Food AUBERGINE = (new Food.Builder().hunger(4).saturation(0.3f)).build();
	public static final Food COOKED_AUBERGINE = (new Food.Builder().hunger(5).saturation(0.8f)).build();
	public static final Food STUFFED_AUBERGINE = (new Food.Builder().hunger(10).saturation(0.8f)).build();
}
