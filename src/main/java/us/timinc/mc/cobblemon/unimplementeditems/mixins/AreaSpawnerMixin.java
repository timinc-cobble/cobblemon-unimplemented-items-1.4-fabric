package us.timinc.mc.cobblemon.unimplementeditems.mixins;

import com.cobblemon.mod.common.api.spawning.spawner.AreaSpawner;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.chunk.ChunkAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import us.timinc.mc.cobblemon.unimplementeditems.blocks.UnimplementedItemsBlocks;

@Mixin(value = AreaSpawner.class, remap = false)
public abstract class AreaSpawnerMixin {
    @Inject(method = "isValidStartPoint", at = @At("TAIL"), cancellable = true)
    private void checkValidSpawn(Level world, ChunkAccess chunk, BlockPos.MutableBlockPos startPos, CallbackInfoReturnable<Boolean> cir) {
        for (int xOff = -10; xOff <= 10; xOff++) {
            for (int yOff = -10; yOff <= 10; yOff++) {
                for (int zOff = -10; zOff <= 10; zOff++) {
                    BlockPos pos = startPos.offset(xOff, yOff, zOff);
                    if (world.getBlockState(pos).is(UnimplementedItemsBlocks.INSTANCE.getREPEL())) {
                        cir.setReturnValue(false);
                        return;
                    }
                }
            }
        }
    }
}