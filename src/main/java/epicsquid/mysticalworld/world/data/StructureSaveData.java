package epicsquid.mysticalworld.world.data;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagLong;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.storage.WorldSavedData;
import net.minecraftforge.common.util.Constants;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StructureSaveData extends WorldSavedData {
  public static final String ID = "MysticalWorld-StructureSaveData";

  private Map<ResourceLocation, List<BlockPos>> generatorMap = new HashMap<>();

  public StructureSaveData(String name) {
    super(name);
  }

  public StructureSaveData () {
    super(ID);
  }

  public List<BlockPos> getGeneratorMap (ResourceLocation location) {
    return generatorMap.computeIfAbsent(location, k -> new ArrayList<>());
  }

  @Override
  public void readFromNBT(NBTTagCompound nbt) {
    NBTTagList keyList = nbt.getTagList("keyList", Constants.NBT.TAG_STRING);
    for (int i = 0; i < keyList.tagCount(); i++) {
      String key = keyList.getStringTagAt(i);
      ResourceLocation rl = new ResourceLocation(key);

      NBTTagList positions = nbt.getTagList(key, Constants.NBT.TAG_LONG);
      List<BlockPos> posList = generatorMap.get(rl);
      if (posList == null) {
        posList = new ArrayList<>();
        generatorMap.put(rl, posList);
      } else {
        posList.clear();
      }

      for (int j = 0; j < positions.tagCount(); j++) {
        long l = ((NBTTagLong) positions.get(j)).getLong();
        posList.add(BlockPos.fromLong(l));
      }
    }
  }

  @Override
  public NBTTagCompound writeToNBT(NBTTagCompound compound) {
    NBTTagList stringKeyList = new NBTTagList();
    for (Map.Entry<ResourceLocation, List<BlockPos>> entry : generatorMap.entrySet()) {
      ResourceLocation rl = entry.getKey();
      String name = rl.toString();
      stringKeyList.appendTag(new NBTTagString(name));
      NBTTagList positions = new NBTTagList();
      for (BlockPos p : entry.getValue()) {
        positions.appendTag(new NBTTagLong(p.toLong()));
      }
      compound.setTag(name, positions);
    }
    compound.setTag("keyList", stringKeyList);
    return compound;
  }
}
