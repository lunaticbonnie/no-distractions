package patrolin.nodistractions.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.datafixers.util.Either;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import java.util.function.Consumer;

@Mixin(ServerPlayer.class)
public class ServerPlayerMixin {
  @Redirect(method="startSleepInBed", at=@At(value="INVOKE", target="Lnet/minecraft/server/level/ServerPlayer;setRespawnPosition(Lnet/minecraft/server/level/ServerPlayer$RespawnConfig;Z)V"))
  private void setRespawnPoint(ServerPlayer instance, ServerPlayer.RespawnConfig respawnConfig, boolean bl) {}
  @WrapOperation(method="startSleepInBed", at= @At(value="INVOKE", target="Lnet/minecraft/server/level/ServerLevel;isBrightOutside()Z"))
  private boolean isDaytime(ServerLevel instance, Operation<Boolean> original, @Local(argsOnly = true) BlockPos blockPos) {
    boolean result = original.call(instance);
    if (result) setRespawnPosition(blockPos);
    return result;
  }
  @WrapOperation(method="startSleepInBed", at=@At(value="INVOKE", target="Lcom/mojang/datafixers/util/Either;ifRight(Ljava/util/function/Consumer;)Lcom/mojang/datafixers/util/Either;"))
  private <L,R>Either<L, R> onSleep(Either<L,R> instance, Consumer<? super R> consumer, Operation<Either<L, R>> original, @Local(argsOnly = true) BlockPos blockPos) {
    return instance.ifRight((unit) -> {
      setRespawnPosition(blockPos);
      consumer.accept(unit);
    });
  }

  @Unique
  private void setRespawnPosition(BlockPos blockPos) {
    ServerPlayer player = (ServerPlayer)(Object)this;
    player.setRespawnPosition(new ServerPlayer.RespawnConfig(player.level().dimension(), blockPos, player.getYRot(), false), true);
  }
}