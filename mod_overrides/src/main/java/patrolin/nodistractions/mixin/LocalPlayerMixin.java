package patrolin.nodistractions.mixin;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.player.LocalPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import patrolin.nodistractions.NoDistractions;

@Mixin(LocalPlayer.class)
public class LocalPlayerMixin {
  // night rework
  @Definition(id = "getFoodData", method = "Lnet/minecraft/client/player/LocalPlayer;getFoodData()Lnet/minecraft/world/food/FoodData;")
  @Definition(id = "getFoodLevel", method = "Lnet/minecraft/world/food/FoodData;getFoodLevel()I")
  @Expression("(float)this.getFoodData().getFoodLevel() > 6.0")
  @ModifyExpressionValue(method = "hasEnoughFoodToStartSprinting", at = @At("MIXINEXTRAS:EXPRESSION"))
  private boolean hasEnoughFoodToSprint(boolean original) {
    LocalPlayer player = (LocalPlayer)(Object)this;
    boolean missingHealth = NoDistractions.cantSprint(player.getHealth(), player.getMaxHealth());
    return original && !missingHealth;
  }
}
