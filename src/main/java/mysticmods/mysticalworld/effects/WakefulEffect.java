package mysticmods.mysticalworld.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.InstantEffect;
import net.minecraft.stats.Stats;
import net.minecraft.util.Util;
import net.minecraft.util.text.Color;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;

public class WakefulEffect extends InstantEffect {
  public WakefulEffect() {
    super(EffectType.BENEFICIAL, 0xffffff);
  }

  @Override
  public void performEffect(LivingEntity entity, int amplifier) {
    if (entity instanceof PlayerEntity) {
      PlayerEntity player = (PlayerEntity) entity;
      player.takeStat(Stats.CUSTOM.get(Stats.TIME_SINCE_REST));
      player.sendMessage(new TranslationTextComponent("message.dandelion_cordial").setStyle(Style.EMPTY.setColor(Color.fromTextFormatting(TextFormatting.YELLOW))), Util.DUMMY_UUID);
    }
  }
}
