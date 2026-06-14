package patrolin.nodistractions.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.datafixers.util.Either;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import java.util.function.Consumer;

@Mixin(ServerPlayer.class)
public class ServerPlayerMixin {
  // night rework
  @WrapOperation(method="startSleepInBed", at=@At(value="INVOKE", target="Lnet/minecraft/server/level/ServerPlayer;setRespawnPosition(Lnet/minecraft/resources/ResourceKey;Lnet/minecraft/core/BlockPos;FZZ)V"))
  private void setRespawnPoint(ServerPlayer instance, ResourceKey<Level> resourceKey, BlockPos blockPos, float f, boolean bl, boolean bl2, Operation<Void> original) {}
  @WrapOperation(method="startSleepInBed", at= @At(value="INVOKE", target="Lnet/minecraft/world/level/Level;isDay()Z"))
  private boolean isDaytime(Level instance, Operation<Boolean> original, @Local(argsOnly = true) BlockPos blockPos) {
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
    player.setRespawnPosition(player.level.dimension(), blockPos, player.getYRot(), false, true);
  }
}