package mysticmods.mysticalworld;

import com.tterrag.registrate.util.nullness.NonNullSupplier;
import mysticmods.mysticalworld.config.ConfigManager;
import mysticmods.mysticalworld.init.*;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.PlantType;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.loading.FMLPaths;
import noobanidus.libs.noobutil.data.generator.RecipeGenerator;
import noobanidus.libs.noobutil.modifier.PlayerModifierRegistry;
import noobanidus.libs.noobutil.reference.ModData;
import noobanidus.libs.noobutil.registrate.CustomRegistrate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("mysticalworld")
public class MysticalWorld {
  public static PlantType STONE_PLANT;

  public static final Logger LOG = LogManager.getLogger();
  public static final String MODID = "mysticalworld";

  public static CustomRegistrate REGISTRATE;
  public static RecipeGenerator RECIPES = new RecipeGenerator(MODID);

  public static final CreativeModeTab ITEM_GROUP = new CreativeModeTab(MODID) {
    @Override
    public ItemStack makeIcon() {
      return new ItemStack(ModBlocks.SAPPHIRE_BLOCK.get());
    }
  };

  public MysticalWorld() {
    ModData.setIdAndIdentifier(MODID, "MysticalWorld");
    ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ConfigManager.COMMON_CONFIG);
    ConfigManager.loadConfig(ConfigManager.COMMON_CONFIG, FMLPaths.CONFIGDIR.get().resolve(MODID + "-common.toml"));

    REGISTRATE = CustomRegistrate.create(MODID);
    REGISTRATE.creativeModeTab(NonNullSupplier.of(() -> ITEM_GROUP));

    ModBlocks.load();
    ModItems.load();
    ModEntities.load();
    ModRecipes.load();
    ModModifiers.load();
    ModSounds.load();
    ModEffects.load();
    ModLang.load();
    ModTags.load();

    PlayerModifierRegistry.addModifier(ModModifiers.SERENDIPITY);
    PlayerModifierRegistry.addModifier(ModModifiers.BLESSED);
    PlayerModifierRegistry.addModifier(ModModifiers.SMITE);
    PlayerModifierRegistry.addModifier(ModModifiers.CARAPAX);
  }
}
