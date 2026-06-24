package patrolin.nodistractions.mixin.blocks;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.LadderBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.Property;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LadderBlock.class)
public class LadderBlockMixin {
  @Shadow @Final public static DirectionProperty FACING;

  @Inject(method="canAttachTo", at=@At("HEAD"), cancellable = true)
  void canAttachTo(BlockGetter blockReader, BlockPos pos, Direction direction, CallbackInfoReturnable<Boolean> cir) {
    cir.setReturnValue(true);
  }
  @Inject(method="canSurvive", at=@At("HEAD"), cancellable = true)
  void canSurvive(BlockState state, LevelReader level, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
    cir.setReturnValue(true);
  }
  @WrapOperation(method="getStateForPlacement", at= @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;setValue(Lnet/minecraft/world/level/block/state/properties/Property;Ljava/lang/Comparable;)Ljava/lang/Object;"))
  Object getFacingDirection(BlockState instance, Property<?> property, Comparable<?> comparable, Operation<Object> original, @Local(argsOnly = true) BlockPlaceContext context) {
    if (property == FACING) comparable = context.getHorizontalDirection().getOpposite();
    return original.call(instance, property, comparable);
  }
}
