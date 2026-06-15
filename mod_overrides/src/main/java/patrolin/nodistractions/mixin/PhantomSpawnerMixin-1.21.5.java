package patrolin.nodistractions.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.levelgen.PhantomSpawner;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(PhantomSpawner.class)
public class PhantomSpawnerMixin {
  @WrapMethod(method="tick")
  private void maybeSpawnPhantoms(ServerLevel serverLevel, boolean bl, boolean bl2, Operation<Void> original) {}
}
