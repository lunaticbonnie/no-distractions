package patrolin.nodistractions.mixin;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.world.Difficulty;
import net.minecraft.world.food.FoodData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(FoodData.class)
public class FoodDataMixin {
  // hunger rework
  @Definition(id = "difficulty", local = @Local(type = Difficulty.class))
  @Definition(id = "PEACEFUL", field = "Lnet/minecraft/world/Difficulty;PEACEFUL:Lnet/minecraft/world/Difficulty;")
  @Expression("difficulty != PEACEFUL")
  @ModifyExpressionValue(method = "tick", at = @At("MIXINEXTRAS:EXPRESSION"))
  private boolean shouldCauseHunger(boolean original) {
    return true;
  }
}