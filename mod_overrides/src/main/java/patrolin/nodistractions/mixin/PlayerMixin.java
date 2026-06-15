package patrolin.nodistractions.mixin;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Player.class)
public class PlayerMixin {
  // hunger rework
  @WrapMethod(method="causeFoodExhaustion")
  private void causeFoodExhaustion(float amount, Operation<Void> original) {
    Player player = (Player)(Object)this;
    FoodData foodData = player.getFoodData();
    boolean missingHealth = player.getHealth() < player.getMaxHealth();
    boolean missingHunger = foodData.getFoodLevel() < 20;
    boolean hasSaturation = foodData.getSaturationLevel() > 0;
    boolean hasHarmfulEffect = PlayerMixin.hasHarmfulMobEffect(player);
    if (missingHealth || missingHunger || hasSaturation || hasHarmfulEffect) {
      original.call(amount);
    }
  }
  @WrapMethod(method="canEat")
  private boolean canEat(boolean canAlwaysEat, Operation<Boolean> original) {
    Player player = (Player)(Object)this;
    FoodData foodData = player.getFoodData();
    boolean missingHealth = player.getHealth() < player.getMaxHealth();
    boolean missingSaturation = !(foodData.getSaturationLevel() > 0);
    return original.call(canAlwaysEat) || missingHealth || missingSaturation;
  }

  @Unique
  private static boolean hasHarmfulMobEffect(Player player) {
    for (MobEffectInstance effectInstance : player.getActiveEffects()) {
      MobEffectCategory category = effectInstance.getEffect().getCategory();
      if (category == MobEffectCategory.HARMFUL) return true;
    }
    return false;
  }

  @Definition(id = "getDifficulty", method = "Lnet/minecraft/world/level/Level;getDifficulty()Lnet/minecraft/world/Difficulty;")
  @Definition(id = "PEACEFUL", field = "Lnet/minecraft/world/Difficulty;PEACEFUL:Lnet/minecraft/world/Difficulty;")
  @Definition(id = "level", field = "Lnet/minecraft/world/entity/player/Player;level:Lnet/minecraft/world/level/Level;")
  @Expression("this.level.getDifficulty() == PEACEFUL")
  @ModifyExpressionValue(method = "aiStep", at = @At("MIXINEXTRAS:EXPRESSION"))
  private boolean doPeacefulRegeneration(boolean original) {
    return false;
  }
}