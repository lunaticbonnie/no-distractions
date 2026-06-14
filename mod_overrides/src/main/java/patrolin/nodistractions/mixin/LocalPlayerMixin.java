package patrolin.nodistractions.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.client.player.LocalPlayer;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(LocalPlayer.class)
public class LocalPlayerMixin {
  // night rework
  @WrapMethod(method="hasEnoughFoodToSprint")
  private boolean hasEnoughFoodToSprint(Operation<Boolean> original) {
    LocalPlayer player = (LocalPlayer)(Object)this;
    boolean missingHealth = player.getHealth() < player.getMaxHealth();
    return original.call() && !missingHealth;
  }
}
