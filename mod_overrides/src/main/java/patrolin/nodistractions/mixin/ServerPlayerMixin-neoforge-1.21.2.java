package patrolin.nodistractions.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ServerPlayer.class)
public class ServerPlayerMixin {
  // hunger rework
  @WrapMethod(method="tickRegeneration")
  private void peacefulRegeneration(Operation<Void> original) {}
}