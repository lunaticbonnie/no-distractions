package patrolin.nodistractions.mixin;

import net.minecraft.server.players.SleepStatus;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SleepStatus.class)
public class SleepStatusMixin {
  // sleep rework
  @Inject(method="areEnoughSleeping", at=@At("HEAD"), cancellable = true)
  private void areEnoughSleeping(CallbackInfoReturnable<Boolean> cir) {
    cir.setReturnValue(false);
  }
  @Inject(method="areEnoughDeepSleeping", at=@At("HEAD"), cancellable = true)
  private void areEnoughDeepSleeping(CallbackInfoReturnable<Boolean> cir) {
    cir.setReturnValue(false);
  }
}