package patrolin.nodistractions.mixin;

import net.minecraft.server.level.ServerLevel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerLevel.class)
public class ServerLevelMixin {
  // sleep rework
  @Inject(method="canSleepThroughNights", at=@At("HEAD"), cancellable = true)
  private void canSleepThroughNights(CallbackInfoReturnable<Boolean> cir) {
    cir.setReturnValue(false);
  }
}