package epicsquid.mysticalworld.entity.model;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nonnull;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelEnderman;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SuppressWarnings("deprecation")
public class ModelHolder implements IResourceManagerReloadListener {

  public static Map<String, ModelBase> models = new HashMap<>();

  public static void init() {
    models.put("fox", new ModelFox());
    models.put("frog", new ModelFrog());
    models.put("beetle", new ModelBeetle());
    models.put("deer", new ModelDeer());
    models.put("sprout", new ModelSprout());
    models.put("endermini", new ModelEnderman(0f));
  }

  @SideOnly(Side.CLIENT)
  @Override
  public void onResourceManagerReload(@Nonnull IResourceManager resourceManager) {
    models.clear();
    init();
  }
}
