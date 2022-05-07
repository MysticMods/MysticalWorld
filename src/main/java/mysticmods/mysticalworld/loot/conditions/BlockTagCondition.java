package mysticmods.mysticalworld.loot.conditions;

import com.google.common.collect.ImmutableSet;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import mysticmods.mysticalworld.init.ModLoot;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParam;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;

import java.util.Set;

public class BlockTagCondition implements LootItemCondition {
  final TagKey<Block> tag;

  BlockTagCondition(TagKey<Block> tag) {
    this.tag = tag;
  }

  public LootItemConditionType getType() {
    return ModLoot.BLOCK_TAG.get();
  }

  public Set<LootContextParam<?>> getReferencedContextParams() {
    return ImmutableSet.of(LootContextParams.BLOCK_STATE);
  }

  public boolean test(LootContext p_81772_) {
    BlockState blockstate = p_81772_.getParamOrNull(LootContextParams.BLOCK_STATE);
    return blockstate != null && blockstate.is(this.tag);
  }

  public static class Builder implements LootItemCondition.Builder {
    private final TagKey<Block> tag;

    public Builder(TagKey<Block> tag) {
      this.tag = tag;
    }

    public LootItemCondition build() {
      return new BlockTagCondition(this.tag);
    }
  }

  public static class Serializer implements net.minecraft.world.level.storage.loot.Serializer<BlockTagCondition> {
    public void serialize(JsonObject json, BlockTagCondition condition, JsonSerializationContext context) {
      json.addProperty("tag", condition.tag.location().toString());
    }

    public BlockTagCondition deserialize(JsonObject json, JsonDeserializationContext context) {
      return new BlockTagCondition(TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation(GsonHelper.getAsString(json, "tag"))));
    }
  }
}